package com.example.Hierarchicalboard.controller;

import com.example.Hierarchicalboard.domain.Board;
import com.example.Hierarchicalboard.dto.FileDto;
import com.example.Hierarchicalboard.dto.MemberInfo;
import com.example.Hierarchicalboard.service.BoardService;
import com.example.Hierarchicalboard.service.FileService;
import com.example.Hierarchicalboard.util.MD5Generator;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final FileService fileService;

    @GetMapping("/list")
    public String list(@RequestParam(name="page",defaultValue = "0") int page, HttpSession session, Model model){
        MemberInfo memberInfo = (MemberInfo)session.getAttribute("memberInfo");
        if(memberInfo == null){
            return "redirect:/";
        }
        long totalCount = boardService.getTotalCount();
        List<Board> boards = boardService.showAll(page);
        long pageCount = totalCount/ 10;
        if(totalCount % 10 > 0 ){
            pageCount++;
        }
        int currentPage = page;

//        for(Board board : boards){
//            board.setTitle(board.getTitle().replace("\t", "&nbsp;"));
//        }
        model.addAttribute("memberInfo",memberInfo);
        model.addAttribute("boards",boards);
        model.addAttribute("pageCount",pageCount);
        model.addAttribute("currentPage",currentPage);

        return "/board/list";
    }

    @GetMapping("/writeForm")
    public String writeForm(HttpSession session, Model model){
        MemberInfo memberInfo = (MemberInfo)session.getAttribute("memberInfo");
        if(memberInfo == null){
            return "redirect:/";
        }

        model.addAttribute("memberInfo",memberInfo);
        return "/board/write";
    }

    @PostMapping("/write")
    public String write(@RequestParam(value ="file", required=false) MultipartFile file,
                        @RequestParam("title") String title,
                        @RequestParam("content") String content,
                        HttpSession session
    ){
        MemberInfo memberInfo = (MemberInfo)session.getAttribute("memberInfo");
        if(memberInfo == null){
            return "redirect:/";
        }
        Board board = boardService.addBoard(memberInfo.getMemberNo(),title,content);
        try{
            String origFileName = file.getOriginalFilename();
            String filename = new MD5Generator(origFileName).toString();
            String savePath = System.getProperty("user.dir")+"/files";
            if(!new File(savePath).exists()){
                try{
                    new File(savePath).mkdir();
                }catch(Exception e){
                    e.getStackTrace();
                }
            }
            String filePath = savePath + "/" + filename;
            file.transferTo(new File(filePath));

            FileDto fileDto = new FileDto();
            fileDto.setOrigFilename(origFileName);
            fileDto.setFilename(filename);
            fileDto.setFilePath(filePath);
            fileDto.setBoard(board);

            Long fileId = fileService.saveFile(fileDto);
            System.out.println(fileId);

        }catch(Exception e){
            e.printStackTrace();
        }

        return "redirect:/list"; //수정해야함.
    }
}

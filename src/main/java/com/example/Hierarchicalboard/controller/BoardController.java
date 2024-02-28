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

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final FileService fileService;

    @GetMapping("/list")
    public String list(HttpSession session, Model model){
        MemberInfo memberInfo = (MemberInfo)session.getAttribute("memberInfo");
        if(memberInfo == null){
            return "redirect:/";
        }
        model.addAttribute("memberInfo",memberInfo);
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

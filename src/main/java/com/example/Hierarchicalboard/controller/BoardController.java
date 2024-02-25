package com.example.Hierarchicalboard.controller;

import com.example.Hierarchicalboard.dto.MemberInfo;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @GetMapping("/list")
    public String list(HttpSession session, Model model){
        MemberInfo memberInfo = (MemberInfo)session.getAttribute("memberInfo");
        model.addAttribute("memberInfo",memberInfo);
        return "/board/list";
    }
}

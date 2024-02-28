package com.example.Hierarchicalboard.controller;

import com.example.Hierarchicalboard.domain.Member;
import com.example.Hierarchicalboard.dto.MemberDto;
import com.example.Hierarchicalboard.dto.MemberInfo;
import com.example.Hierarchicalboard.repository.MemberRepository;
import com.example.Hierarchicalboard.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    @GetMapping("/signup")
    public String signUp( @RequestParam(required = false) boolean error, Model model){
        model.addAttribute("error",error);
        return "/member/signup";
    }

    @PostMapping("/join")
    public String join(HttpServletRequest request, MemberDto memberDto, Model model){
        Member member = memberService.addMember(memberDto);
        if(member == null){
            return "redirect:signup?error=true";
        }else{
            return "redirect:/";
        }
    }

    @PostMapping("/login")
    public String login(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam(value = "remember-check", defaultValue ="false") String rememberCheck,
            HttpServletResponse response, // 쿠키를 전송하기위해 사용
            HttpSession httpSession

    ){
        try{
            Member member = memberService.getMember(email);
            if(member.getPassword().equals(password)){
                MemberInfo memberInfo = new MemberInfo(member.getMemberNo(), member.getEmail(),member.getName());
                httpSession.setAttribute("memberInfo",memberInfo);
                if(rememberCheck.equals("true")){
                    Cookie cookie = new Cookie("savedEmail",email);
                    cookie.setMaxAge(30*24*60*60);
                    response.addCookie(cookie);
                }else{
                    Cookie cookie = new Cookie("savedEmail", null);
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }else{
                throw new RuntimeException("이메일이나 암호가 일치하지 않습니다.");
            }
        }catch(Exception ex){
            return "redirect:?error=true";
        }
        return "redirect:list";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession){

        httpSession.removeAttribute("memberInfo");

        return "redirect:/";
    }
}

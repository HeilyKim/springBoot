package com.jpa.org.controller;

import com.jpa.org.entity.Member;
import com.jpa.org.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("Member")
public class MemberController {
    @Autowired
    MemberService memberService;

    // 회원가입 하면
    @GetMapping("Signup")
    public String signup() {
        return "member/signup";
    }

    //회원가입 해서 테이블에 저장
    @PostMapping("Signup")
    public String psignup(Member member) {
        memberService.save(member);
        return "redirect:/";
    }
}

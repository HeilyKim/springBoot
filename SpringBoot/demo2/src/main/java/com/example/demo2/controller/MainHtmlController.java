package com.example.demo2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller // html파일을 찾는것
public class MainHtmlController {
    @GetMapping("aa") //  ../aa로 요청시
    public String aa(Model model, String num1, String num2) {
        System.out.println("출력");
        System.out.println(num1);
        System.out.println(num2);
        if (num1 == null) { // num1이 null이면 0으로 초기화
            num1 = "0";
        }
        if (num2 == null) { // num2이 null이면 0으로 초기화
            num2 = "0";
        }
        model.addAttribute("data", "mydata"); // 키값 : data, value값 : mydata
        model.addAttribute("result", Integer.parseInt(num1) + Integer.parseInt(num2));
        return "aa";  //aa.html로 감
    }
}
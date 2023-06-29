package com.example.demo2.controller;

import com.example.demo2.obj.AA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    @Autowired //객체 초기화
    AA aa;

    @GetMapping("/")
    public String index() {
        System.out.println(aa);
        return "index";
    }
}

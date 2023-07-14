package com.jpa.org.controller;

import com.jpa.org.entity.FreeBoard;
import com.jpa.org.repository.FreeBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    FreeBoardRepository freeBoardRepository;

    @GetMapping("/")
    public String index() {
        FreeBoard f1 = new FreeBoard().builder()
                .name("홍길동")
                .content("내요내욘ㅇ")
                .build();

        freeBoardRepository.save(f1); //insert가 자동으로 됨
        List<FreeBoard> list = freeBoardRepository.findAll(); //테이블 모든 데이터 불러오기
        System.out.println(list);
        return "index";
    }
}
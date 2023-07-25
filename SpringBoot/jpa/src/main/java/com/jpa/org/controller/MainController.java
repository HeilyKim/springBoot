package com.jpa.org.controller;

import com.jpa.org.entity.Role;
import com.jpa.org.repository.FreeBoardRepository;
import com.jpa.org.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequiredArgsConstructor // with private final == @Autowired
public class MainController {

    private final FreeBoardRepository freeBoardRepository;
    private final RoleRepository roleRepository;

    @GetMapping("/")
    public String index(
//            @Autowired Authentication authentication
            ){
        roleRepository.save(new Role(1,"User"));
        roleRepository.save(new Role(2,"Admin"));
        roleRepository.save(new Role(3,"Manager"));
//        System.out.println("로그인 유무"+authentication.isAuthenticated());
//        System.out.println("getPrincipal"+authentication.getPrincipal());
//        System.out.println("userDetails"+authentication.getDetails());
//        FreeBoard f1 = new FreeBoard().builder()
//                .name("홍길동")
//                .content("내요내욘ㅇ")
//                .build();
//
//        freeBoardRepository.save(f1); //insert가 자동으로 됨
//        List<FreeBoard> list = freeBoardRepository.findAll(); //테이블 모든 데이터 불러오기
//        System.out.println(list);
        return "index";
    }
}

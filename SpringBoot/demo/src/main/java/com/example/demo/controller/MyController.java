package com.example.demo.controller;

import com.example.demo.myClass.AA;
import com.example.demo.myClass.BB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*@RestController -> 주소를 호출하면 응답하는 클래스
@GetMapping -> 어떤 주소를 요청하면 어떤결과 줌
@Component -> 스프링객체통안에 있는 객체를 가져와서 초기화함
@Autowired -> Component한 객체에서 만들어서 초기화
*/
@RestController
public class MyController {
    @Autowired //aa3, aa4 같이 바뀜
    AA aa3;
    @Autowired
    AA aa4;
    @Autowired
    BB bb1;
    @Autowired
    BB bb2;


    @GetMapping("aa") //주소에 aa로 요청을 하면 밑에 시행
    public String aa() {
        AA aa = new AA();
        AA aa2 = new AA();
        System.out.println("aa=" + aa);
        System.out.println("aa2=" + aa2);
        System.out.println("aa3=" + aa3);
        System.out.println("aa4=" + aa4);

        System.out.println("aa.a = " + aa.a);
        System.out.println("aa2.a = " + aa2.a);

        aa.a = 20;
        System.out.println("aa.a=20일때");
        System.out.println("aa1.a = " + aa.a);
        System.out.println("aa2.a = " + aa2.a);
        System.out.println("aa3 = " + aa3.a);
        System.out.println("aa4 = " + aa4.a);

        aa3.a = 40;
        System.out.println("aa3.a=40일때");
        System.out.println("aa3 = " + aa3.a);
        System.out.println("aa4 = " + aa4.a);

        return "aa";
    }

    @GetMapping("bb") //주소에 bb로 요청을 하면 밑에 시행
    public String b() {
        System.out.println("bb1 = " + bb1);
        System.out.println("bb2 = " + bb2);
        return "bb";
    }
}

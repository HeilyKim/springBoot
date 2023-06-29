package com.example.demo2.obj;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString //prints each field and its value when its called
@AllArgsConstructor  //모든 필드 값을 파라미터로 받는 생성자를 만듦
public class AA {
    private String name;
}

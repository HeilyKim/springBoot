package com.example.demo3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/")
    public String result(@RequestParam(defaultValue = "0") String num1,
                         @RequestParam(defaultValue = "0") String num2,
                         @RequestParam(defaultValue = "+") String cal,
                         Model model) {
        System.out.println("num1 = " + num1 + " num2 = " + num2 + " cal = " + cal);
        int result = 0;
        if (cal.equals("+"))
            result = Integer.parseInt(num1) + Integer.parseInt(num2);
        else if (cal.equals("-"))
            result = Integer.parseInt(num1) - Integer.parseInt(num2);
        else if (cal.equals("*"))
            result = Integer.parseInt(num1) * Integer.parseInt(num2);
        else if (cal.equals("/"))
            result = Integer.parseInt(num1) / Integer.parseInt(num2);
        model.addAttribute("result", result);
        return "index";
    }
}

package com.jpabook.jpashop;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "열심히 해보쟈!");
        return "hello"; //화면 이름을 뜻하고, resources에 templates에서 정해진 view이름이 hello인 곳으로 이동합니다.
    }
}

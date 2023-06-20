package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
<<<<<<< HEAD

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
=======
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data","Hello!!");
        return "hello";

>>>>>>> 9943b6ba7a697cc2f9a4639cdf90a947da87e9e0
    }
}

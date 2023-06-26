package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model ) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    //ResponseBody는 http에서 헤더부와 바디부가 있는데 body부분에 해당 데이터를 직접 넣겠다는 의미 / URL에서 name 값에 spring이라고 name을 넣는다면 "hello spring"으로 요청한 클라이언트로 바로 변경
    //페이지 소스보기를 한다면 html 구조가 아닌 바로 해당 문자가 입력된 걸 확인 가능
    //이전의 템플릿 엔진은 뷰라는 템플릿 엔진에서 조작하는 방식이었는데 @ResponseBody로 입력되는 데이터가 그대로 띄어줄 수 있게 됨
    public String helloString(@RequestParam ("name") String name) {
        return " hello " + name;
    }

    //가장 많이 쓰는 경우 -> 만약 데이터 출력을 원할 때, 문자가 아닌 getter와 setter를 사용하여 객체를 넘겨서 작성해본 상황
    //아래와 같이 객체를 생성한 후 그 객체를 return하게 되면 key:value로 이루어진 json 형식의 파일이 브라우저에 출력
    @GetMapping ("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

};

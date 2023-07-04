package hello.hellospring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    //정적 컨텐츠 설명시 ,아무것도 없는 기본 경로이면, static 파일에 있는 index.html이 실행
    //하지만 이 때, 우선순위가 있는데, 실행 요청을 하게 되면 스프링 컨테이너 안에 있는 해당 이름의 컨트롤러가 있는지를 찾게되는데 , 현재 HomeController 화면에 맵핑된 url이 있기 때문에 해당 컨트롤러가 호출됩니다. (index.html의 파일은 무시)
    @GetMapping("/")
    public String home() {
        return "home";
    }
}

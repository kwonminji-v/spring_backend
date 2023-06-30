package hello.hellospring.controller;


import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

//스프링이 처음에 뜰 때, 스프링 컨테이너라는 박스가 생기는데 만약 그곳에 컨트롤러 애노테이션이 있으면 MemberController객체를 생성해서 담아준 후 스프링 빈이 관리해줍니다.
@Controller
public class MemberController {
    //스프링이 관리하게 되면 아래처럼 객체를 생성하는 것이 아니라, 스프링 컨테이너에 모든게 등록이 되고 스프링 컨테이너에서 받아서 쓸 수있도록 하는 것이 좋습니다.
    //스프링 컨테이너에 등록은 딱 1개의 객체만 등록됩니다.
    private final MemberService memberService;

    //생성자를 만든 후 스프링 컨테이너가 실행될 때, 생성을 해주는데,@Autowired를 입력하면 스프링 컨테이너에 있는 memberService를 가지고와서 연결을 해줍니다.
    //멤버 컨트롤러가 생성이 될 때, 스프링 빈에 등록되어있는 멤버 서비스객체를 넣어주는데 , 이를 DI라고합니다. (Dependency Injection) : 의존성 주입
    @Autowired
    public MemberController (MemberService memberService) {
        this.memberService = memberService;
    }
}

package hello.hellospring.controller;


import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

//스프링이 처음에 뜰 때, 스프링 컨테이너라는 박스가 생기는데 만약 그곳에 컨트롤러 애노테이션이 있으면 MemberController 객체를 생성해서 담아준 후 스프링 빈이 관리해줍니다.
@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController (MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("members/new")
    public String createForm() {
        return "members/createMemberForm";
    }


    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        //join 로직으로 이동하여 save 메서드로 이동하여 입력된 값이 전달됩니다.
        System.out.println("member.getName() = " + member.getName());
        memberService.join(member);

        return "redirect:/";
    }

}

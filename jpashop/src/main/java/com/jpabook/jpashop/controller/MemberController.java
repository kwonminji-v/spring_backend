package com.jpabook.jpashop.controller;


import com.jpabook.jpashop.domain.Address;
import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /** Model은 컨트롤러에서 뷰로 넘어갈 때, ( )안에 작성된 데이터들을 담아서 넘겨줌
     * 아래의 Get 방식으로 이동하여 createMemberForm이 열리면서 렌더링이 됨 */
    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        //컨트롤러가 화면을 이동할 때, addAttribute로 MemberForm() 빈 껍데기의 객체를 가지고 감
        //렌더링이 될 때, MemberForm을 넘겨줬기 때문에 화면에서 MemberForm 객체에 접근이 가능
        return "members/createMemberForm";
    }

    /** Post 방식으로 작성하여 members/new로 경로를 매핑하였음
     * Get과 url은 같지만 Get은 단순히 Form 화면을 열어보고, post는 작성되어 전송된 데이터를 실제로 등록하는 것 */
    @PostMapping("/members/new")
    /* 매개변수로 받아올 MemberForm 앞에 @Valid 어노테이션을 작성해주면, MemberForm에 작성된 @NotEmpty 등 validation과 관련된 어노테이션을 인식함
    * 회원 이름이 필수 값으로 입력되어야하는 제약 조건을 정해주기 위해 @Valid 조건 */
    public String create(@Valid MemberForm form, BindingResult result) { // BindingResult에 에러 확인이 가능함

        /** 이름을 입력하지 않은 채로 submit하게 되면 MemberForm에 NotEmpty 메세지로 적어두었던 message가 출력되고
         * post메서드로 전송시도는 되었으나, 다시 createMemberForm으로 돌아가게 됨*/
        if (result.hasErrors()) {
            return "members/createMemberForm";
        }
        
        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());
        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);

        memberService.join(member);
        return "redirect:/";
    }

    /** MemberServiec에 만들어진 findMembers 메서드를 이용해 모든 멤버를 조회하고 가져온 후, members에 담음
     *  model에 addAttribute로 key : "members" , value : members (List)를 꺼낼 수 있도록 작성 */
    @GetMapping("/members")
    public String list(Model model) {
        model.addAttribute("members", memberService.findMembers());
        //아래의 코드를 위의 코드로 인라인화 (1줄로) 작성할 수 있다. 단축키 ctrl+alt+n
        /*
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members); */
        return "members/memberList";
    }

}

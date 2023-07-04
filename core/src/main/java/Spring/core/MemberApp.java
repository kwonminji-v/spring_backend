package Spring.core;

import Spring.core.member.Grade;
import Spring.core.member.Member;
import Spring.core.member.MemberService;
import Spring.core.member.MemberServiceImple;

public class MemberApp {

    public static void main(String[] args) {

        MemberService memberService = new MemberServiceImple();
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
    }
}

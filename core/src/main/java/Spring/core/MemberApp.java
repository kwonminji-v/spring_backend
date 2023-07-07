package Spring.core;

import Spring.core.member.Grade;
import Spring.core.member.Member;
import Spring.core.member.MemberService;
import Spring.core.member.MemberServiceImple;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {

//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        //AppConfig 클래스를 설정으로 사용하여, AnnotationConfigApplicationContext를 생성하고,
        //애플리케이션에서 필요한 빈을 applicationContext를 통해 가져와서 사용이 가능합니다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);


        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
    }
}

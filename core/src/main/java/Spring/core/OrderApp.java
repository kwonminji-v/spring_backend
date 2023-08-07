package Spring.core;

import Spring.core.member.Grade;
import Spring.core.member.Member;
import Spring.core.member.MemberService;
import Spring.core.order.OrderDTO;
import Spring.core.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class OrderApp {

    public static void main(String[] args) {

//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        //memberId를 1로 선언하고 만든 후, Member 객체에 매개변수 id , name , grade를 전달
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        OrderDTO order = orderService.createOrder(memberId, "itemA", 20000);

        System.out.println("order = " + order);
    }

}

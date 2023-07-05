package Spring.core;

import Spring.core.member.Grade;
import Spring.core.member.Member;
import Spring.core.member.MemberService;
import Spring.core.member.MemberServiceImple;
import Spring.core.order.OrderDTO;
import Spring.core.order.OrderService;
import Spring.core.order.OrderServiceImpl;


public class OrderApp {

    public static void main(String[] args) {

        //MemberService 와 OrderSercive 객체를 생성
        MemberService memberService = new MemberServiceImple();
        OrderService orderService = new OrderServiceImpl();

        //memberId를 1로 선언하고 만든 후, Member 객체에 매개변수 id , name , grade를 전달
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        OrderDTO order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order = " + order);
    }

}

package Spring.core;

import Spring.core.discount.DiscountPolicy;
import Spring.core.discount.FixDiscountPolicy;
import Spring.core.discount.RateDiscountPolicy;
import Spring.core.member.MemberRepository;
import Spring.core.member.MemberService;
import Spring.core.member.MemberServiceImple;
import Spring.core.member.MemoryMemberRepository;
import Spring.core.order.OrderService;
import Spring.core.order.OrderServiceImpl;


//AppConfig에 MemberService와 OrderServive의 역할은 잘 보여지지만 MemberRepository와 DiscountPolicy의 역할을 같이 구현
//아래와 같은 코드 변경의 장점은 각 메서드의 역할명만 보아도 역할을 알 수 있으며, 역할과 구현 클래스들을 한눈에 알 수 있습니다.
//애플리케이션 전체 구성이 어떻게 되어있는지 빠르게 파악이 가능
public class AppConfig {
    //↓ 클라이언트 (주문생성 -회원 ID)
    public MemberService memberService() {
        return new MemberServiceImple(memberRepository());
    }

    //↓ 회원 저장소 역할 (메모리 회원 저장소 / DB 회원 저장소)
    private  MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    //↓ 주문 서비스 역할 (회원 조회 / 할인 적용)
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    //↓ 할인 정책 역할 (정액 할인 정책 / 정률 할인 정책)
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

}

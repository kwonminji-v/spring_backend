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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AppConfig {
    @Bean
    //↓ 클라이언트 (주문생성 -회원 ID)
    public MemberService memberService() {
        return new MemberServiceImple(memberRepository());
    }
    @Bean
    //↓ 회원 저장소 역할 (메모리 회원 저장소 / DB 회원 저장소)
    public  MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
    @Bean
    //↓ 주문 서비스 역할 (회원 조회 / 할인 적용)
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
    @Bean
    //↓ 할인 정책 역할 (정액 할인 정책 / 정률 할인 정책)
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

}

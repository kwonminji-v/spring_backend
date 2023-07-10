package Spring.core;

import Spring.core.discount.DiscountPolicy;
import Spring.core.discount.RateDiscountPolicy;
import Spring.core.member.MemberRepository;
import Spring.core.member.MemberService;
import Spring.core.member.MemberServiceImpl;
import Spring.core.member.MemoryMemberRepository;
import Spring.core.order.OrderService;
import Spring.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AppConfig {

    //@Bean이 되어있는데 memberService 메서드가 실행되며, memberRepository가 호출되는데
    //결과적으로는 new MemoryMemberRepository()를 호출해줍니다.

    //@Bean orderService -> new OrderServiceImpl을 호출하며, ( )안에 주어진
    //메서드들이 불러와지는데, 이 때, 앞서 호출된 memberRepository()가 반복되어 호출되며
    //memberRepository()가 불러오는 MemoryMemberRepository()가 두번이나 반복되어 호출됨

    //ㅡㅡㅡㅡㅡㅡㅡㅡㅡ결론ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
    //memberService() 메서드와 memberRepository() 메서드가 실행되며 MemoryMemberRepository()가 1번 불러와짐
    //orderService()가 호출되며 memberRepository()가 실행되어 또 한번 MemoryMemberRepository()가 2번 호출됨
    //이렇게 되면 싱글톤이 깨진다고 생각할 수 있는 상황에 스프링 컨테이너의 해결방법
    @Bean
    //↓ 클라이언트 (주문생성 -회원 ID)
    public MemberService memberService() {
        System.out.println("AppConfig.memberService 호출");
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    //↓ 회원 저장소 역할 (메모리 회원 저장소 / DB 회원 저장소)
    public  MemberRepository memberRepository() {
        System.out.println("AppConfig.memberRepository 호출");
        return new MemoryMemberRepository();
    }
    @Bean
    //↓ 주문 서비스 역할 (회원 조회 / 할인 적용)
    public OrderService orderService() {
        System.out.println("AppConfig.orderService 호출");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
    @Bean
    //↓ 할인 정책 역할 (정액 할인 정책 / 정률 할인 정책)
    public DiscountPolicy discountPolicy() {
        System.out.println("AppConfig.discountPolicy 호출");
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

}

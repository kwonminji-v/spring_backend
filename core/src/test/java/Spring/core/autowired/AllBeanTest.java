package Spring.core.autowired;

import Spring.core.AutoAppConfig;
import Spring.core.discount.DiscountPolicy;
import Spring.core.member.Grade;
import Spring.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


public class AllBeanTest {

    @Test
    //fintAllBean() 메서드는 스프링 애플리케이션 컨텍스트를 생성하고, DicountService 클래스의 빈들을 조회하는 테스트를 수행
    void findAllBean() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);
        int discountPrice = discountService.discount(member, 10000,"fixDiscountPolicy");

        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discountPrice).isEqualTo(1000);

        int rateDiscountPrice = discountService.discount(member, 20000, "rateDiscountPolicy");
        assertThat(rateDiscountPrice).isEqualTo(2000);
    }

    /*Test가 실행되면 스프링 컨테이너는 DiscountService 클래스의 빈을 생성 -> DiscountService 클래스의 생성자는 Map<String, DiscountPolicy>과 List<DiscountPolicy>를 주입받습니다.
      스프링은 애플리케이션 컨텍스트에 등록된 모든 DiscountPolicy 타입의 빈들을 조회하고 policyMap에는 빈 이름을 키로, 빈 인스턴스를 값으로 하는 맵을 생성하며,
      policies 리스트에는 빈 인스턴스들을 리스트로 담습니다.
       
      테스트가 완료되면 policyMap과 policies의 내용이 콘솔에 출력 (아직 DiscountService만 호출했기 때문에 null 값이 출력됨 -> DiscountPolicy를 불러와야함.) */


    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }

        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(member, price);
        }
    }
}

/* 스프링 애플리케이션 컨텍스트를 사용하여 DiscountService 클래스의 빈들을 조회하는 테스트 !
   스프링 애플리케이션 컨텍스트는 스프링 빈(Bean)들을 생성하고 관리하는 역할을 담당합니다.
   애플리케이션의 구성 요소들을 연결하고, 의존성을 주입하며, 런타임 시에 스프링 빈들을 찾아서 제공하는 중앙 저장소 역할을 수행합니다.
   이를 통해 애플리케이션의 구성과 라이프사이클 관리를 용이하게 해줍니다.
   DiscountService는 두개의 필드를 가지고 있는데 Map<String , DiscountPolicy> 타입의 PolicyMap필드와
   List<DiscountPolicy> 타입의 policies 필드입니다. 이 두 생성자를 통해 해당 필드를 주입받습니다.*/
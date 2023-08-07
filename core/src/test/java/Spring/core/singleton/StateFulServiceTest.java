package Spring.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.*;

class StateFulServiceTest {

    //해당 스프링 컨테이너는 하단에 생성된 TestConfig안의 1개의 Bean만 생성해서 사용


    @Test
    void statefulServiceSingleton() {

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StateFulService stateFulService1 = ac.getBean(StateFulService.class);
        StateFulService stateFulService2 = ac.getBean(StateFulService.class);

        //ThreadA : userA사용자는 10000원어치 주문
        stateFulService1.order("userA", 10000);
        //ThreadB : B 사용자는 20000원을 주문
        stateFulService2.order("userB", 20000);

        //ThreadA : 사용자  A가 주문 금액을 조회하고 싶을때 사용하는 메서드 작성
        //A가 주문을 하고, 금액을 조회하는 사이에 B의 주문이 들어온 상황
        int price = stateFulService1.getPrice();
        System.out.println("price = " + price); //출력 price=20000(중간에 B의 주문이 들어오게 되며 price의 값이 변경)


        //A사용자는 10000원을 주문했는데, 중간의 가격 변경으로 price가 20000으로 변경되었는데도 Test는 통과됨
        assertThat(stateFulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig {

        @Bean
        public StateFulService stateFulService() {
            return new StateFulService();
        }
    }

}
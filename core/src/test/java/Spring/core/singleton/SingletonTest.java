package Spring.core.singleton;

import Spring.core.AppConfig;
import Spring.core.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {
    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        //DI컨테이너에 있는 Bean객체의 사용이 필요하여 AppConfig 객체를 생성
        AppConfig appConfig = new AppConfig();

        //1. 조회: 호출할 때마다 객체를 생성하는지 조회
        MemberService memberService1 = appConfig.memberService();

        //2. 조회: 호출할 때마다 객체를 생성하는지 조회
        MemberService memberService2 = appConfig.memberService();

        //참조 값이 다른 것을 확인해 보도록 합니다.
        //AppcConfig한테서 memberService를 호출을 힐 때마다 참조값(주소가) 다르게 호출
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        //memberService1 != memberService2
        assertThat(memberService1).isNotSameAs(memberService2);

    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
        SingletonService singletonService1 = SingletonService.getInstance();
        System.out.println("singletonService1 = " + singletonService1);
        
        SingletonService singletonService2 = SingletonService.getInstance();
        System.out.println("singletonService2 = " + singletonService2);

        assertThat(singletonService1).isSameAs(singletonService2);
        // same == : 두 객체가 정확히 동일한 객체인지를 확인 / 두 객체가 메모리에서 같은 위치를 참조하는지를 비교
        // equal : 두 객체의 값이 동등한지를 확인 / 객체의 실제 값을 비교하여 동등성을 판단
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {

        //AppConfig appConfig = new AppConfig();
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);


        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);


        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        assertThat(memberService1).isSameAs(memberService2);
    }


}

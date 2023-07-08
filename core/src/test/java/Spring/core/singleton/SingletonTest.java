package Spring.core.singleton;

import Spring.core.AppConfig;
import Spring.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}

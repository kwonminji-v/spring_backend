package Spring.core.beanfind;

import Spring.core.AppConfig;
import Spring.core.member.MemberService;
import Spring.core.member.MemberServiceImple;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        @Test
        @DisplayName("빈 이름으로 조회")
        void findBeanByName () {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
            System.out.println("memberService = " + memberService);
            System.out.println("memberService = " + memberService.getClass());
            assertThat(memberService).isInstanceOf(MemberServiceImple.class);

        }

        @Test
        @DisplayName("이름없이 타입으로만 조회해보기")
        void findBeanByType () {
        MemberService memberService = ac.getBean( MemberService.class);
            assertThat(memberService).isInstanceOf(MemberServiceImple.class);
        }

    @Test
    @DisplayName("구체 타입 이름으로 조회")
    void findBeanByName2 () {
        MemberServiceImple memberService = ac.getBean("memberService", MemberServiceImple.class);
        assertThat(memberService).isInstanceOf(MemberServiceImple.class);

    }

    @Test
    @DisplayName("빈 이름을 조회할게 없다X")
    void findBeanByNameX() {
            //MemberService xxxx = ac.getBean("xxxx", MemberService.class);
        assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("xxxx", MemberService.class));
    }


}
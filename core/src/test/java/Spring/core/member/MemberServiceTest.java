package Spring.core.member;

import Spring.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService;

    //테스트 코드 작성 시 테스트가 실행되기전 무조건 실행되는 코드를 작성할 때 앞에 @Beforeach 어노테이션을 입력
    @BeforeEach
    public  void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join() {
        //given
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then
        Assertions.assertThat(member).isEqualTo(findMember);
    }

    @Test
    void findId() {
        //given

        //when

        //then
    }
}

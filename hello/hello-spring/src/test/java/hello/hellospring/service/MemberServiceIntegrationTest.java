package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;



    //회원가입 서비스가 제대로 작동되는 지 확인해보기
    //test는 한글로 적어도 괜찮다
    @Test
    void 회원가입() {

        //given(어떤데이터) : 테스트 코드를 작성할 땐 어떠한 상황에 주어지는데
        Member member = new Member();
        member.setName("spring!!");

        //when : 해당 상황을 실행했을 때 (memberService의 join 메서드를 검증)
        Long saveId = memberService.join(member);

        //then(검증부) : 여기 결과가 주어지도록 코드를 작성한다.
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("Spring");

        Member member2 = new Member();
        member2.setName("Spring");



        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");


        //then
    }


}
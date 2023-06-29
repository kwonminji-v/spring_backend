package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService = new MemberService();
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    public void afterEach(){
        memberRepository.clearStore();
    }

    //회원가입 서비스가 제대로 작동되는 지 확인해보기
    //test는 한글로 적어도 괜찮다
    @Test
    void 회원가입() {

        //given(어떤데이터) : 테스트 코드를 작성할 땐 어떠한 상황에 주어지는데
        Member member = new Member();
        member.setName("hello");

        //when : 해당 상황을 실행했을 때
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

        //저장되는 이름의 값이 같은 상황에서 join을 두번 했을 때, validate에서 검증되어 이미 존재하는 회원이기 때문에 터지게 됩니다.
        Member member2 = new Member();
        member2.setName("Spring");



        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        try {
//            memberService.join(member2);
//            fail();
//        }catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//            //예외 메세지가 validate 메서드에 있는 문구랑 일치해야 테스트 성공 / 다르면 실패입니다.
//        }


        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}
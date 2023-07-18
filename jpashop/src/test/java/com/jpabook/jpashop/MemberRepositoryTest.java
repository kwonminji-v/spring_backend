package com.jpabook.jpashop;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@SpringBootTest
class MemberRepositoryTest {

    private final MemberRepository memberRepository;
    
    @Test
    @Transactional
    @Rollback(false)
    //Test 케이스에 @Transactional이 있으면 Test가 끝난 후 DB를 롤백하여 Test이전의 상태로 되돌림
    public void testMember()  {
            
            //given
            Member member = new Member();
            member.setUsername("memberA");

            //when
            Long saveId = memberRepository.save(member);
            Member findMember= memberRepository.find(saveId);

            //then
            Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
            Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
    }


}
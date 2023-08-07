package com.jpabook.jpashop.service;


import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional //JPA의 모든 데이터 변경이나 로직은 가급적 Transactional 안에서 실행되어야 함 (
@RequiredArgsConstructor
public class MemberService {


    private final MemberRepository memberRepository;

    /**회원 가입 save() - 중복회원검증도 같이 작성해보기*/
    @Transactional
    public Long join(Member member) {

        validateDuplicateMember(member); //중복 회원 검증 검증 후 문제가 없다면 save가 실행되고,
                                        // return 값으로 해당 id를 member에 전달
        memberRepository.save(member);
        return member.getId();

    }

    //기본적인 중복값 검증을 위한 메서드
    private void validateDuplicateMember(Member member) {
        //예외 처리가 필요
        List<Member> findMembers = memberRepository.findByName(member.getName());
        //만약 가입된 멤버가 아니면 (비어있는 값이 아니라면) 이미 존재하는 회원이라는 예외처리 진행
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        } 
    }


    //회원 전체 조회 findAll()

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    //1명의 회원 id 조회 (단건 조회)
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }


    @Transactional
    public void update(Long id, String name) {

        Member member = memberRepository.findOne(id);
        member.setName(name);
    }
    /** 트랜잭션이 시작이 되며 jpa가 영속성 컨텍스트에서 id를 DB에서 가져와 DB에서 영속성 컨텍스트 member올린 것을 반환해주고,
     * 영속상태의 member를 setName으로 이름을 변경해주면 종료와 동시에 Commit이 되며, jpa가 플러쉬 하고 DB가 커밋 됩니다.   */
}

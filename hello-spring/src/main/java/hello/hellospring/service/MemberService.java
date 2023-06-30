package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    //memberRepository를 외부에서 받아올 수 있도록 생성자를 만들어서 입력합니다.
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    /**
     * 회원 가입
     */
    public Long join(Member member) {
        //같은 이름이 있는 중복회원은 안되는 코드를 작성
        validateDuplicateMember(member);
        //findByName을 했는데, 만약 그 결과가 optional 멤버니깐 바로 ifPresent메서드로 검사.
        //Optional로 감싸서 작성했기 때문에 가능합니다. optional안의 여러 메서드를 사용할 수 있습니다.
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember (Member member) {
        memberRepository.findByName(member.getName())
       .ifPresent(m -> {
           throw  new IllegalStateException("이미 존재하는 회원입니다.");
       });
    }

    /**
     * 전체 회원 조회 기능
     */

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}

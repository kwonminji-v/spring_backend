package Spring.core.member;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//MemberServiceImple은 MemberServie 인터페이스를 상속받아 구현
@Component
public class MemberServiceImpl implements MemberService{


    //↓ MemberRepository 인터페이스를 구현한 구체적인 클래스의 객체를 저장하는 멤버변수
    private final MemberRepository memberRepository;

    //생성자를 통해 MemberRepository 구현체를 주입 받는다.(의존성 주입)
    //런타임에 구체적인 MemberRepository 객체 제공이 가능
    @Autowired //같은 의미 = (ac.getBean(MemberRepository.class)) : 의존관계 저동으로 주입!
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);

    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
    
    //Test 용도로 작성
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}

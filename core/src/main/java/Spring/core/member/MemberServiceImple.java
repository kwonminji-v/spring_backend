package Spring.core.member;


//MemberServiceImple은 MemberServie 인터페이스를 상속받아 구현
public class MemberServiceImple implements MemberService{


    //↓ MemberRepository 인터페이스를 구현한 구체적인 클래스의 객체를 저장하는 멤버변수
    private final MemberRepository memberRepository;

    //생성자를 통해 MemberRepository 구현체를 주입 받는다.(의존성 주입)
    //런타임에 구체적인 MemberRepository 객체 제공이 가능
    public MemberServiceImple(MemberRepository memberRepository) {
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
}

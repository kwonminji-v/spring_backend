package Spring.core.order;
import Spring.core.discount.DiscountPolicy;
import Spring.core.discount.RateDiscountPolicy;
import Spring.core.member.Member;
import Spring.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


//OrderService 인터페이스를 구현해 줄 코드!
//OrderService는 DisCountPolicy가 변경되더라도 아무런 영향을 받지 않음
@Component
public class OrderServiceImpl implements OrderService{

    //인터페이스에만 의존하도록 수정되어 DIP를 지킬 수 있음
    private final DiscountPolicy discountPolicy;
    private final MemberRepository memberRepository;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy ) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;

    }

    @Override
    public OrderDTO createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new OrderDTO(memberId, itemName, itemPrice, discountPrice);
    }
    //Test 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}

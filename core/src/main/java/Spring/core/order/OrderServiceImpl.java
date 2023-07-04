package Spring.core.order;
import Spring.core.discount.DiscountPolicy;
import Spring.core.discount.FixDiscountPolicy;
import Spring.core.member.Member;
import Spring.core.member.MemberRepository;
import Spring.core.member.MemoryMemberRepository;


//OrderService 인터페이스를 구현해 줄 코드!
//OrderService는 DisCountPolicy가 변경되더라도 아무런 영향을 받지 않음
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();


    //주문을 만들어서 반환을 해주면 해당 파일의 실행이 종료됨
    @Override
    public OrderDTO createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new OrderDTO(memberId, itemName, itemPrice, discountPrice);
    }
}

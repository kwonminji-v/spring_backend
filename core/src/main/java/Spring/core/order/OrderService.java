package Spring.core.order;
import Spring.core.discount.DiscountPolicy;
import Spring.core.member.MemberRepository;
import org.springframework.core.annotation.Order;


//인터페이스를 통해 OrderService(주문서비스역할)에 주문 생성 기능을 하는 메서드를 구현


//1. 주문 생성
public interface OrderService {


    OrderDTO createOrder(Long memberId, String itemName, int itemPrice);

}

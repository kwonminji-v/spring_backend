package Spring.core.discount;

import Spring.core.member.Grade;
import Spring.core.member.Member;
import org.springframework.stereotype.Component;


//DisCountPolicy를 구현 받는 구현체인 FixDisCountPolicy 클래스를 생성
/*@Component*/
public class FixDiscountPolicy implements DiscountPolicy {

    //정액할인으로 무조건 1000원만 할인해주는 조건을 작성
    private int discountFixAmount = 1000; //1000원 할인

    //등급이 VIP인 사람에게만 적용되어야 하기때문에 조건문을 작성
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            //VIP이면 1000원을 할인하는 걸 return
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}

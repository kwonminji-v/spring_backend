package Spring.core.discount;

import Spring.core.member.Grade;
import Spring.core.member.Member;

//정률 할인 정책 코드
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10;



    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            //고객의 등급이 VIP일 경우에는 아래의 조건문이 실행된다.
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}

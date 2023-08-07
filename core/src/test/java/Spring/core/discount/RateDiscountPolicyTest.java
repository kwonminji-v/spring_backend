package Spring.core.discount;

import Spring.core.member.Grade;
import Spring.core.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

//Test를 구현할때는 실패했을 때의 상황도 구현
class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    //↓ Junit5부터 지원해주는 Test 완료 시 출력되는 이름을 정할 수 있는 메서드
    @DisplayName("VIP는 10% 할인이 적용되어야 한다.")
    void vip_o() {
        //given
        Member member = new Member(1L, "memberVIP", Grade.VIP);

        //when
        int discount = discountPolicy.discount(member, 10000);

        //then
        //상단에서 discount에 member객체와 int price로 10000원을 전달하여 할인률 10%가 적용되었다면
        //discount의 값은 1000원이 출력되는지를 검증
        assertThat(discount).isEqualTo(1000);
        //static
    }

    @Test
    @DisplayName("등급이 VIP가 아니면 할인이 적용되지 않아야 한다.")
    void vip_x() {
        //given
        Member member = new Member(2L, "NotVip", Grade.BASIC);

        //when
        int discount = discountPolicy.discount(member, 10000);
        //then
        assertThat(discount).isEqualTo(0);
    }
}
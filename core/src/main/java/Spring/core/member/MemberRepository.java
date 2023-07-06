package Spring.core.member;

import Spring.core.discount.DiscountPolicy;

public interface MemberRepository {


    void save(Member member);
    Member findById(Long memberId);
}

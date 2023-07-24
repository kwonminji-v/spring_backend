package com.jpabook.jpashop.service;

import com.jpabook.jpashop.domain.Delivery;
import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.domain.Order;
import com.jpabook.jpashop.domain.OrderItem;
import com.jpabook.jpashop.item.Item;
import com.jpabook.jpashop.repository.ItemRepository;
import com.jpabook.jpashop.repository.MemberRepository;
import com.jpabook.jpashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor //롬복으로 인해 final로 선언된 객체 생성자 생성이 수월
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    /** 주문 */
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {

        //각각의 필요한 엔티티를 조회
        Member member = memberRepository.findOne(memberId); //member 엔티티 조회
        Item item = itemRepository.findOne(itemId); //item 엔티티 조회

        /**배송 정보 생성  */
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        //회원의 주소지에 있는 값으로 배송을 하도록 delivery의 address 값을 member.getAddress로 저장
        //실제로는 입력된 배송 정보가 입력되지만 간단한 예제를 위해 작성

        /**주문 상품 생성 (static 생성 메서드를 이용) 주문 상품은 한개만 받을 수 있도록 제약 */
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);


        /**주문 생성*/
        Order order = Order.createOrder(member, delivery, orderItem);

        /** 주문 저장 시 Cascade 옵션이 있기 때문에 orderItem과 Delivery는 자동으로 함께 persist되면서 DB테이블에 인서트가 됨  */
        orderRepository.save(order);

        return order.getId();
    }

    /** 주문 취소 */
    @Transactional
    public void cancelOrder(Long orderId) {
        //주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);
        // 주문 취소
        order.cancel();
    }

    //검색
/*    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findAll(orderSearch);
    }*/
}

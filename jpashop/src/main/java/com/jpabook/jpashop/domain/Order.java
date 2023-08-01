package com.jpabook.jpashop.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//Order Entity 클래스 생성
@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 클래스 접근 범위를 PROTECTED로 설정하여 직접 생성하지 않고,
// 클래스 내부에 만들어 둔 생성메서드로만 사용하도록 제약조건을 만들어 줌
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    /** CascadeType으로 인해 orderRepository.save(order)로 orderItems와 Delivery가 전부 OrderService에서 참조가 가능 */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL) //Orderitems에 데이터를 저장하고 Order를 jpa.persist
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate; //주문 시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status; //주문 상태 [ORDER , CANCEL]

    // ==== 연관관계 메서드 ==== 위치는 보통 컨트롤 하는쪽에 작성 //
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    // ====생성 메서드 ====
    /**
     * 주문 생성과 관련된 코드는 복잡
     복잡한 기능들의 연관관계의 생성은 별도의 생성 메서드를 작성한 후 진행 */
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) {

        /** Order 생성 메서드를 만들어질 때 createOrder를 무조건 호출하여 매개변수로 Member, Delivery, OrderItem 등을 작성하여
         주문생성에 대한 복잡한 메서드들을 모음 / 주문 생성과 관련하여 수정이 필요하면 해당 메서드 부분만 수정하면됨 */
        Order order = new Order();
        /** OrderItem 객체를 생성 */
        order.setMember(member);
        /** setMember로  */
        order.setDelivery(delivery);
        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    /**
     * Order과 연관 관계를 걸면서 설정해주고, 상태와 주문시간까지 생성이 가능합니다.
     * 주문과 관련하여 생성하는 시점을 변경해야 한다면 , 상단의 별도의 생성메서드만 변경해주면 됨*/

    // ==== 비즈니스 로직을 작성 ==== //
    /**
     * 주문 취소 기능을 하는 코드를 작성
     * 이미 배송된 상품은 주문 취소가 될 수 없다는 비즈니스 로직에 대한 체크 로직이 Entity안에 존재하고,
     * setStatus로 상태를 바꿔주고, Loop를 돌면서 orderItem에 대해서 Cancel 메서드가 실행되면 stock의 Item의 재고를 되돌려 줌 */
    public void cancel() {
        if(delivery.getStatus() == DeliveryStatus.COMP) {
            throw new IllegalStateException("이미 배송이 완료된 상품은 취소가 불가능💥");
        }
        this.setStatus(OrderStatus.CANCEL); //현재 상태를 Cancel로 변경
        for (OrderItem orderItem : orderItems){ // orderItem도 cancel 메서드를 적용하여 이전 재고로 되돌려 줌
            orderItem.cancel();
        }
    }


    // ==== 조회 로직을 작성 ==== //
    /**
     * 장바구니에 담긴 전체 주문 가격을 조회하는 기능 구현
     * orderItems의 배열 데이터가 반복되며 orderItem에 있는 totalPrice를 불러오는데, 주문할 때,
     * 주문 가격과 수량이 있는데, 두개를 곱하여 총 가격을 알 수 있기 때문 */
    public int getTotalPrice() {
        return orderItems.stream()
                .mapToInt(OrderItem::getTotalPrice).sum();

/*      int totalPrice = 0;
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
        //*/
    }
}




















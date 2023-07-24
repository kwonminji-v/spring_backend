package com.jpabook.jpashop.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//Order Entity 클래스 생성
@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL) //Orderitems에 데이터를 저장하고 Order를 jpa.persist
    private List<OrderItem> orderItems = new ArrayList<>();



    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate; //주문 시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status; //주문 상태 [ORDER , CANCEL]

    // -- 연관관계 메서드 -- 위치는 보통 컨트롤 하는쪽에 작성 //
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

        Order order = new Order();
        order.setMember(member);
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

    // ==== 비즈니스 로직을 작성 ====
    /**
     * 주문 취소 기능을 하는 코드를 작성*/
    public void cancel() {
        if(delivery.getStatus() == DeliveryStatus.COMP) {
            throw new IllegalStateException("이미 배송이 완료된 상품은 취소가 불가능💥");
        }
        this.setStatus(OrderStatus.CANCEL);
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }

    // ==== 조회 로직을 작성 ====
    /**
     * 장바구니에 담긴 전체 주문 가격을 조회하는 기능 구현*/
    public int getTotalPrice() {
        int totalPrice = 0;
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }
}




















package com.jpabook.jpashop.domain;


import com.jpabook.jpashop.item.Item;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice; //주문 당시 가격
    private int count;      //주문 당시 수량


    // ==== 생성 메서드 작성 (OrderItem) ==== //
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {

        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        /** setItem으로 item을 가져온 후 저장*/
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count);
        /** OrderItem생성 시 Iteml의 재고를 빼주어야 하기때문에 넘어온 값 만큼 줄어들도록 설정 */
        return orderItem;
    }


    // ==== 비즈니스 로직을 작성 ==== //
    /**
     * 재고수량 원복 : cancel 메서드는 Item을 가져와서 재고를 주문 수량 만큼 눌려줘야 함 */
    public void cancel() {
        getItem().addStock(count);
    }
    // ==== 조회 로직을 작성 ==== //
    /** 주문 상품 전체 가격 조회 */
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }

}

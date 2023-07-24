package com.jpabook.jpashop.domain;


import com.jpabook.jpashop.item.Item;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
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

    // ==== 비즈니스 로직을 작성 ====
    /**
     * 재고수량 원복 : cancel 메서드는 Item을 가져와서 재고를 주문 수량 만큼 눌려줘야 함 */
    public void cancel() {
        getItem().addStock(count);
    }

    public int getTotalPrice() {
        return 0;
    }
}

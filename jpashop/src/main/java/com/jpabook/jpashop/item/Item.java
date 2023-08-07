package com.jpabook.jpashop.item;


import com.jpabook.jpashop.domain.Category;
import com.jpabook.jpashop.exception.NotEnoughStockException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items", fetch = LAZY)
    private List<Category> categories = new ArrayList<>();

    /** ㅡㅡ 상품 관리 기능 관련 비즈니스 로직을 작성 ㅡㅡ
        1. 재고 증가 감소가 가능한 로직
        데이터를 가지고 있는 쪽에 비즈니스 로직을 작성하는것이 가장 좋습니다.
        도메인을 설계할 때 엔티티 자체가 해결할 수 있는 것들은 엔티티 안에 비즈니스 로직을 설계합니다.
        여기서 stockQuantity(재고수량)이 작성된 곳은 item Entity안에 있으므로 해당 클래스 안에 비즈니스 로직을 설계합니다.
    */

    /** ↓ 재고(stock) 수량 증가 로직 */
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    /** ↓ 재고(stock) 수량 감소 로직 */
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }
}

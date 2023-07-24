package com.jpabook.jpashop.repository;

import com.jpabook.jpashop.item.Item;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
/** @RequiredArgsConstructor는 Lombok 라이브러리에서 제공하는 애노테이션으로, 주로 생성자를 자동으로 생성해주는 기능을 제공 */
public class ItemRepository {

    private final EntityManager em;

    /**
    상품을 저장하는 비즈니스 로직 작성 saveItem
     item.getId가 null 값이면 em.persist로 item을 저장 / 처음에 데이터를 저장할때는 item의 id가 존재하지 않기때문에 null로 확인

     */
    public void saveItem(Item item) {
        if (item.getId() == null ) {
            em.persist(item);
        } else {
            em.merge(item);
        }
    }
}

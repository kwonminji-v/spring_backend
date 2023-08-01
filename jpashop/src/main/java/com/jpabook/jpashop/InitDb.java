package com.jpabook.jpashop;


import com.jpabook.jpashop.domain.*;
import com.jpabook.jpashop.item.Book;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 총 2개의 주문을 만들고자 함
 * userA
     * JPA1 BOOK
     * JPA2 BOOK
 * userB
     * SPRING1 BOOK
     * SPRING2 BOOK
 * */
@Component
@RequiredArgsConstructor
public class InitDb {

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService { /** static 클래스를 만든 후 entityManager를 가지고 샘플데이터를 생성*/

        private final InitService initService;


        @PostConstruct
        public void init() {
            initService.dbInit1();
            /**애플리케이션 로딩 시점에 호출*/
        }


        private final EntityManager em;
        /** dbinit1 메서드에 Member객체를 호출한 후 , 데이터를 저장*/
        public void dbInit1() {
            Member member = new Member();
            member.setName("userA");
            member.setAddress(new Address("서울","28","111-256"));
            em.persist(member); //member를 영속상태로 만듬

            Book book1 = new Book();
            book1.setName("JPA1 Book");
            book1.setPrice(10000);
            book1.setStockQuantity(100);
            em.persist(book1);

            Book book2 = new Book();
            book2.setName("JPA2 Book");
            book2.setPrice(20000);
            book2.setStockQuantity(100);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);

            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            Order.createOrder(member, delivery, orderItem1,orderItem2);
        }
    }

}

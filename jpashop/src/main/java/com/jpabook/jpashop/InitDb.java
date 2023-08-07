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

    private final InitService initService;


    public void init() {
        initService.dbInit1();
        initService.dbInit2();
        /**애플리케이션 로딩 시점에 호출*/
    }


    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService { /** static 클래스를 만든 후 entityManager를 가지고 샘플데이터를 생성*/

        private final EntityManager em;
        /** dbinit1 메서드에 Member객체를 호출한 후 , 데이터를 저장*/
        public void dbInit1() {
            Member member = createMember("userA","서울","1","2222");
            em.persist(member); //member를 영속상태로 만듬

            Book book1 = createBook("JPA1 BOOK", 10000, 100);
            em.persist(book1);

            Book book2 = createBook("JPA2 BOOK", 20000, 100);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);

            Delivery delivery = createDelivery(member);
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);
        }


        public void dbInit2() {
            Member member = createMember("userB", "진주", "2","7777");
            em.persist(member); //member를 영속상태로 만듬

            Book book1 = createBook("SPRING1 BOOK", 20000, 200);
            em.persist(book1);

            Book book2 = createBook("SPRING2 BOOK", 40000, 300);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 20000, 3);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 40000, 4);

            Delivery delivery = createDelivery(member);
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);
        }

        private static Delivery createDelivery(Member member) {
            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            return delivery;
        }

        private static Book createBook(String s, int i, int stockQuantity) {
            Book book1 = new Book();
            book1.setName(s);
            book1.setPrice(i);
            book1.setStockQuantity(stockQuantity);
            return book1;
        }

        private static Member createMember(String name, String city, String street, String zipcode) {
            Member member = new Member();
            member.setName(name);
            member.setAddress(new Address(city,street,zipcode));
            return member;
        }
    }

}

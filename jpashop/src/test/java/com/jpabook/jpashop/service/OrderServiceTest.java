package com.jpabook.jpashop.service;

import com.jpabook.jpashop.domain.Address;
import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.domain.Order;
import com.jpabook.jpashop.domain.OrderStatus;
import com.jpabook.jpashop.exception.NotEnoughStockException;
import com.jpabook.jpashop.item.Book;
import com.jpabook.jpashop.item.Item;
import com.jpabook.jpashop.repository.OrderRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired EntityManager em;
    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception {

        Member member = createMember();

        Book book = createBook("JPA쉽게 배우기", 10000, 10);

        int orderCount = 2;

        //when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        //then
        Order getOrder = orderRepository.findOne(orderId);
        assertEquals(OrderStatus.ORDER,getOrder.getStatus(),"상품 주문 시 상태는 ORDER");
        assertEquals(1,getOrder.getOrderItems().size(),"주문한 상품의 종류 수가 정확해야 한다.");
        assertEquals(10000*orderCount,getOrder.getTotalPrice(),"주문 가격은 가격 * 수량이다");
        assertEquals(8,book.getStockQuantity(),"주문 수량 만큼 재고가 줄어야한다");
    }


    @Test
    public void 상품주문_재고수량초과() {

        assertThrows(NotEnoughStockException.class, () -> {
            //given
            Member member = createMember();
            Item item = createBook("JPA쉽게 배우기", 10000, 10);

            int orderCount = 11; //재고가 10개인 상황에서 주문 수량이 11개이면 예외가 발생

            //when
            orderService.order(member.getId(), item.getId(), orderCount);

            //then
            fail("재고 수량 부족 예외가 발생해야 한다.");
        });
    }

    @Test
    public void 주문취소() throws Exception{

            //given
            Member member = createMember();
            Book item = createBook("JPA 책", 20000,10);

            int orderCount = 2;
            Long  orderId = orderService.order(member.getId(), item.getId(), orderCount);

            //when
            orderService.cancelOrder(orderId);

            //then
            Order getOrder = orderRepository.findOne(orderId);

            assertEquals(OrderStatus.CANCEL, getOrder.getStatus(),"주문 취소 시 상태는 CANCEL 이다.");
            assertEquals(10, item.getStockQuantity(),"주문이 취소된 상품은 그만큼 재고가 증가해야 한다.");
    }



    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        //given
        Member member = new Member();
        member.setName("권민지");
        member.setAddress(new Address("서울","한강","123-123"));
        em.persist(member);
        return member;
    }
}
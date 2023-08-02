package com.jpabook.jpashop.api;


import com.jpabook.jpashop.domain.Address;
import com.jpabook.jpashop.domain.Order;
import com.jpabook.jpashop.domain.OrderStatus;
import com.jpabook.jpashop.repository.OrderRepository;
import com.jpabook.jpashop.repository.OrderSearch;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 1. XToOne의 특징
 * 컬렉션이 아닌것 : 객체 간의 관계에서 일대일(OneToOne) 또는 다대일(ManyToOne) 관계를 가지는 경우의 성능 최적화 방법
 * Order를 조회하고 Order에서 Member(회원)와 연관이 걸리고
 * Order에서 Delivery(배송정보)와 연관관계를 갖도록 작성
 * */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;
    /**
     * V1. 엔티티 직접 노출의 위험성
     * Order와 Order->Member , Order-> Delivery와 관계된 데이터만 필요하나, 다른 데이터들도 함께 불러오는 성능 낭비가 발생
     * - Hibernate5Module 모듈 등록, LAZY=null 처리
     * - 양방향 관계 문제 발생 -> @JsonIgnore
     */
    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        for (Order order : all) {
            order.getMember().getName(); //LAZY 강제 초기화
            /**order.getMember()까지는 Proxy 객체로 db에 쿼리문이 도달하지 않으나, getName()으로 특정 데이터(실제 name)까지 선택하면
             * LAZY가 강제 초기화가 되며 Member에 쿼리문을 날려서 데이터를 전부 가져옴
             * */
            order.getDelivery().getAddress(); //LAZY 강제 초기화
        }
        return all;
    }


    /**
     * V2. 엔티티를 조회해서 DTO로 변환(fetch join 사용X)
     * - 단점: 지연로딩으로 쿼리 N번 호출
     */

    @GetMapping("/api/v2/simple-orders")
    public List<SimpleOrderDto> ordersV2()  {
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());
        List<SimpleOrderDto> result = orders.stream()
                .map(o -> new SimpleOrderDto(o)).collect(Collectors.toList());

        return result;

        /**orderRepository.findAllByString(new OrderSearch()): orderRepository에서 findAllByString 메서드를 호출하여 데이터베이스로부터 Order 엔티티의 리스트를 조회합니다.
         orders.stream(): List<Order>를 스트림으로 변환합니다.
         .map(o -> new SimpleOrderDto(o)): 각 Order 엔티티를 SimpleOrderDto로 변환합니다. 이 때, 람다 표현식을 사용하여 Order 엔티티를 SimpleOrderDto로 매핑합니다.
         .collect(Collectors.toList()): 변환된 SimpleOrderDto들을 리스트로 수집합니다.*/

    }

    @Data
    static class SimpleOrderDto {
        private Long orderId;
        private String name;
        private LocalDateTime orderDate; //주문 시간
        private OrderStatus orderStatus;
        private Address address;

        public SimpleOrderDto(Order order) {
            /** 생성자에서 바로 완성을 시키도록 작성 ↑ 상단의 변수를 사용 */
            orderId= order.getId();
            name = order.getMember().getName(); //LAZY가 초기화
            orderDate = order.getOrderDate();
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress();

        }
    }
}



















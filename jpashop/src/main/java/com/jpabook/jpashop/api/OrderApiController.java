package com.jpabook.jpashop.api;


import com.jpabook.jpashop.domain.Address;
import com.jpabook.jpashop.domain.Order;
import com.jpabook.jpashop.domain.OrderItem;
import com.jpabook.jpashop.domain.OrderStatus;
import com.jpabook.jpashop.repository.OrderRepository;
import com.jpabook.jpashop.repository.OrderSearch;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderApiController {
/**
 * V1. 엔티티 직접 노출
 * - 엔티티가 변하면 API 스펙이 변함, 트랜잭션 안에서 지연 로딩 필요 , 양방향 연관관계 문제 */

        private final OrderRepository orderRepository;

        @GetMapping("/api/v1/orders")
        public List<Order> ordersV1() {

            List<Order> all = orderRepository.findAllByString(new OrderSearch());
            for (Order order : all) {
                order.getMember().getName();
                order.getDelivery().getAddress();

                //주문과 관련된 orderItems를 다 가져와서 forEach 반복으로 주문서 안에 있는 item의 이름을 가져옴
                List<OrderItem> orderItems = order.getOrderItems();
                orderItems.stream().forEach(o -> o.getItem().getName());

            }
            return all;
        }

        /**RESTful API를 제공하는 메서드 해당 API는 주문(Order) 엔티티를 조회하고, 조회된 주문들을 OrderDto로 변환하여 반환 */
        @GetMapping("/api/v2/orders")
        /** 1.  이 메서드는 List<OrderDto> 타입의 결과를 반환합니다. 즉, 조회된 주문을 OrderDto 리스트로 반환합니다.*/
        public List<OrderDto> ordersV2() {
            /**2.orderRepository를 사용하여 데이터베이스에서 주문(Order) 엔티티들을 조회합니다.
             * findAllByString(new OrderSearch())는 orderRepository에 정의된 메서드 중 하나로, 주문 정보를 검색하기 위한 조건을 OrderSearch 객체를 통해 지정하는 방법입니다.*/
            List<Order> orders = orderRepository.findAllByString(new OrderSearch());
            /** 3. : 조회된 주문 엔티티들을 OrderDto로 변환하는 과정입니다. orders.stream()을 사용하여 주문 엔티티들을 스트림으로 변환하고,
             * .map(o -> new OrderDto(o))를 사용하여 각 주문 엔티티를 OrderDto로 변환합니다. 이때 람다 표현식을 사용하여 각 주문 엔티티를 OrderDto로 매핑합니다.
             * 변환된 OrderDto들은 .collect(Collectors.toList())를 사용하여 List로 수집합니다. */
            List<OrderDto> result = orders.stream()
                    .map(o -> new OrderDto(o))
                    .collect(Collectors.toList());


            return result;
        }


        /** 4.static class OrderDto { ... }: OrderDto는 Order 엔티티의 정보를 가지고 필요한 정보만을 선택하여 DTO(Data Transfer Object)로 표현하는 클래스입니다.
         * DTO는 엔티티의 정보를 외부로 전달할 때 사용되며, API 응답 등에서 엔티티의 데이터를 노출하지 않고 필요한 정보만 전달하는 데 유용합니다. */
        @Data
        static class OrderDto {

            private Long orderId;
            private String name;
            private LocalDateTime orderDate;
            private OrderStatus orderStatus;
            private Address address;
            private List<OrderItemDto> orderItems;

            /** 5.public OrderDto(Order o) { ... }: OrderDto 클래스의 생성자는 Order 엔티티를 인자로 받아서 OrderDto 객체로 변환하는 역할을 합니다.
             * 따라서 이 생성자에서 Order 엔티티의 정보를 활용하여 OrderDto의 필드들을 초기화하는 로직을 작성해야 합니다.
             * 현재 코드에서는 생성자 내용이 작성되어 있으며, Order 엔티티의 필드들을 활용하여 OrderDto를 적절하게 구성하는 로직을 추가했습니다. */
            public OrderDto(Order order) {
                orderId = order.getId();
                name = order.getMember().getName();
                orderDate = order.getOrderDate();
                orderStatus = order.getStatus();
                address = order.getDelivery().getAddress();
                orderItems = order.getOrderItems().stream()
                        .map(orderItem -> new OrderItemDto(orderItem))
                        .collect(Collectors.toList());
                //order.getOrderItems().stream().forEach(o-> o.getItem().getName());
                //orderItems = order.getOrderItems(); //orderItems는 현재 entity이기 때문에 null이 출력
            }
        }

        @Getter
        static class  OrderItemDto{

            private String itemName; //상품명
            private int orderPrice;  //상품 가격
            private int count;       //주문 수량

            public OrderItemDto(OrderItem orderItem) {

                itemName = orderItem.getItem().getName();
                orderPrice = orderItem.getOrderPrice();
                count = orderItem.getCount();
            }
        }
}

















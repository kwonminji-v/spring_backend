package com.jpabook.jpashop.repository.order.simplequery;

import com.jpabook.jpashop.domain.Address;
import com.jpabook.jpashop.domain.Order;
import com.jpabook.jpashop.domain.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderSimpleQueryDto {
    private Long orderId;
    private String name;
    private LocalDateTime orderDate; //주문 시간
    private OrderStatus orderStatus;
    private Address address;

    public OrderSimpleQueryDto(Long orderId, String name, LocalDateTime orderDate, OrderStatus orderStatus ,Address address) {
        /** 생성자에서 바로 완성을 시키도록 작성 ↑ 상단의 변수를 사용 */
        this.orderId=orderId;
        this.name =  name;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.address = address;

    }
}



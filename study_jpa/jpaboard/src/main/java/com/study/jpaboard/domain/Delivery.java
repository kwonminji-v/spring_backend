package com.study.jpaboard.domain;


import jakarta.persistence.*;

import static jakarta.persistence.FetchType.*;

@Entity
public class Delivery extends BaseEntity {

    @Id @GeneratedValue
    private Long id;
    private String city;
    private String street;
    private String zipcode;

    private DeliveryStatus deliveryStatus;

    @OneToOne(mappedBy = "delivery" , fetch = LAZY)
    private Order order;



}

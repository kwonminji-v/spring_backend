package com.study.jpaboard.domain;


import jakarta.persistence.*;

@Entity
public class Delivery {


    @Id
    @GeneratedValue
    private Long id;

    private String city;
    private String street;
    private String zipcode;
    private DeliveryStatus status;

    @OneToOne(mappedBy = "Delivery")
    private Order order;
}


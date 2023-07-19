package com.jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

//Address라는 값의 타입으로 클래스가 만들어졌을 때의 예시
@Embeddable
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;
}

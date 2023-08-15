package com.study.jpa;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
public class Item {

    @Id @GeneratedValue
    private Long id;

    private String name; //상품명
    private int price; //상품 가격


}

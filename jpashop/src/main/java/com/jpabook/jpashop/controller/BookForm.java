package com.jpabook.jpashop.controller;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;

@Getter @Setter
public class BookForm {

    /** 상품의 공통 속성 */
    private Long id;
    private String name;
    private int price;
    private  int stockQuantity;

    /** 책과 관련된 특성 */
    private String author;
    private String isbn;
}

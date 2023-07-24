package com.jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

//JPA를 이용하여 DB와 연동할 수 있는 Entity클래스 Member 생성
@Entity //JPA Entity임을 표시하는 어노테이션
@Getter @Setter
public class Member {

    @Id  //아래에 선언된 id 변수를 기본키로 사용한다는 의미의 어노테이션
    @GeneratedValue //기본 키 값을 자동으로 생성하는 전략을 지정해주는 어노테이션
    //자동 생성 전략을 사용하여 새로운 Entity가 저장될 때 자동으로 기본 키 값이 할당
    @Column(name = "member_id")
    //DB의 테이블에서 해당 Entity의 컬럼이름을 지정 , 여기서는 member_id로 해당 컬럼과 매핑
    private Long id;

    private String name;

    @Embedded
    //객체를 값 타입으로 포함함을 명시 / Address 클래스는 별도의 테이블이 아니라 Member테이블에 포함되는 값 타임으로 사용
    private Address address;

    @OneToMany(mappedBy = "member")
    //회원과 주문 Entity간의 관계를 나타내주는 어노테이션
    //mappedBy 속성은 주인이 되는 쪽의 연관관계를 지정해주며, 여기서는 order Entity 클래스의 member 필드와
    //매핑되어, order Entity의 member 필드를 사용하여 양방향 관계를 설정
    private List<Order> orders = new ArrayList<>();


}



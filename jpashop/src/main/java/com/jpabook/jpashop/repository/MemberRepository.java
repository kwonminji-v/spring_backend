package com.jpabook.jpashop.repository;


import com.jpabook.jpashop.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@Component를 내장하고 있기때문에 해당 클래스를 전부 빈으로 자동 등록
public class MemberRepository {

    @PersistenceContext //스프링 Entity 매니저를 주입해주는 어노테이션
    private EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
        //단건 조회로 find(타입,PK) -> 이렇게 입력
    }

    public List<Member> findAll() {
        //createQuery메서드에 JPQL로 쿼리문을 입력해줍니다. SQL과 유사한데, from의 대상이 테이블이 아니라 Entity
        return em.createQuery("select m from MemberJPA m", Member.class).getResultList();
    }

    //파라미터 바인딩으로 특정 이름으로 검색하는 것
    public List<Member> findByName(String name) {
        return em.createQuery("select m from MemberJPA m where m.name = :name", Member.class)
                .setParameter("name", name).getResultList();
    }

}

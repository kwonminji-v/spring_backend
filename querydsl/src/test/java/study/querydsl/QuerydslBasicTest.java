package study.querydsl;


import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.entity.Member;
import study.querydsl.entity.QMember;
import study.querydsl.entity.Team;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class QuerydslBasicTest {

    @Autowired
    EntityManager em;

    @BeforeEach
    public void before() {

        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 5, teamA);
        Member member3 = new Member("member3", 12, teamB);
        Member member4 = new Member("member4", 42, teamB);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);
    }

    @Test
    public void startJPQL() {
        //member1을 DB에서 조회
        String qlString =
                "select m from Member m " +
                 "where m.username = :username";
        Member findMember = em.createQuery(qlString, Member.class)
                .setParameter("username", "member1")
                .getSingleResult();

        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    @Test
    public void startQuerydsl() {

        /**Querydsl을 사용하기 위해 JPAQueryFactory 객체를 생성 , JPA엔티티를 조회하고 조작하는데 사용*/
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        QMember m = new QMember("m"); //Member 엔티티를 위한 Q 클래스입니다.
        // m은 QMember의 별칭(alias)로, 이후에 쿼리에서 m을 사용하여 엔티티에 접근
        Member findMember = queryFactory
                .select(m) //QMember의 별칭인 m을 전달하여 Member엔티티 선택
                .from(m)   //해당 엔티티를 대상으로 조회
                .where(m.username.eq("member1")) //Member엔티티의 username을 "member1"인 조건설정
                .fetchOne(); //쿼리를 실행하고 결과를 하나의 엔티티로 반환 즉, 조건을 만족하는 회원 정보 중 하나를 조회

        assertThat(findMember.getUsername()).isEqualTo("member1");
    }
}

package study.querydsl;


import com.querydsl.core.QueryFactory;
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
import static study.querydsl.entity.QMember.*;

@SpringBootTest
@Transactional
public class QuerydslBasicTest {

    @Autowired
    EntityManager em;

    JPAQueryFactory queryFactory;

    @BeforeEach
    public void before() {

        queryFactory = new JPAQueryFactory(em);

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
        //파라미터 바인딩을 setParameter로 전달
        Member findMember = em.createQuery(qlString, Member.class)
                .setParameter("username", "member1")
                .getSingleResult();

        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

/*    @Test
    public void startQuerydsl() {

        *//**Querydsl을 사용하기 위해 JPAQueryFactory 객체를 생성 , JPA엔티티를 조회하고 조작하는데 사용*//*
        //entitymanager를 변수로 넘겨받아서 사용
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        QMember m = new QMember("m"); //Member 엔티티를 위한 Q 클래스입니다.
        // m은 QMember의 별칭(alias)로, 이후에 쿼리에서 m을 사용하여 엔티티에 접근
        Member findMember = queryFactory
                .select(m) //QMember의 별칭인 m을 전달하여 Member엔티티 선택
                .from(m)   //해당 엔티티를 대상으로 조회
                .where(m.username.eq("member1")) //Member엔티티의 username을 "member1"인 조건설정 (eq = equals)
                .fetchOne(); //쿼리를 실행하고 결과를 하나의 엔티티로 반환 즉, 조건을 만족하는 회원 정보 중 하나를 조회

        assertThat(findMember.getUsername()).isEqualTo("member1");
    }*/

    //↑ 상단 코드 축약해보기 (JPAQueryFactroy는 필드값으로 선언 가능)
    @Test
    public void startQuerydsl() {

        //QMember m = QMember.member;
        //↑ 위코드를 static 임포트를 이용하면 더 축약이 가능 , (QMember.member)를 바로 query문에 매개변수로 전달한 후,
        //alt + enter를 사용하여 static 임포트로 변경하여 member만 매개변수에 남겨둘 수 있도록 함
        Member findMember = queryFactory
                .select(member)
                .from(member)
                .where(member.username.eq("member1"))
                .fetchOne();

        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    @Test
    public void search() {
        Member findMember = queryFactory
                .selectFrom(member)
                .where(member.username.eq("member1")
                        .and(member.age.eq(10)))
                .fetchOne();

        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    @Test
    public void searchAndParam() {
        Member findMember = queryFactory
                .selectFrom(member)
                //여러 개의 조건을 AND 연산으로 이어서 사용하려면 쉼표 ,를 사용하여 조건을 나열
                //이렇게 나열된 조건들은 AND 연산으로 연결되어 해당 조건들이 모두 만족되어야 결과가 반환
                .where(
                        member.username.eq("member1"),
                        member.age.eq(10)
                )
                .fetchOne();

        assertThat(findMember.getUsername()).isEqualTo("member1");
    }
}

/**Querydsl을 사용하게 되면 문장에 오류가 발생했을 시 컴파일 오류로 실행전에 오류를 알 수 있음
 * 쿼리도 자바코드로 작성할 수 있어 편합니다.
 * Querydsl은 결국 JPQL의 빌더 역할을 합니다. Querydsl은 JPQL(Java Persistence Query Language)의 빌더 역할을 수행합니다.
 * Querydsl은 JPQL을 보다 편리하게 작성할 수 있도록 해주는 라이브러리로서,
 * JPQL의 쿼리 작성을 위한 자바 코드를 더욱 직관적이고 타입 안정성 있게 만들어줍니다.
 * 빌더 패턴은 복잡한 객체의 생성 과정을 추상화하고, 객체의 속성을 설정하는 방법을 제공하여 객체 생성을 단순화하는 디자인 패턴
 * 객체 생성과 속성 설정: 빌더 패턴은 객체 생성과 동시에 필요한 속성을 설정하면서 객체를 생성합니다. 이를 JPQL과 연결하면,
 * Querydsl은 JPQL 쿼리를 생성하는 동시에 해당 쿼리의 조건 및 필터링 속성을 설정하면서 객체를 "생성"하는 역할을 수행합니다.
 *
 * 가독성과 직관성: 빌더 패턴은 메서드 체인을 통해 속성을 설정하므로, 어떤 속성을 설정하는지 명확하게 알아볼 수 있습니다.
 * Querydsl도 마찬가지로 자바 코드를 사용하여 쿼리를 작성하므로, 어떤 필드나 조건을 설정하는지 코드로 표현되어 가독성과 직관성이 향상됩니다.
 *
 * 타입 안정성: 빌더 패턴은 컴파일 시에 타입 체크가 가능하므로, 오타나 잘못된 설정을 사전에 방지할 수 있습니다.
 * Querydsl도 마찬가지로 자바 코드를 사용하므로 컴파일 시에 타입 체크가 가능하며, JPQL의 문자열 기반 쿼리보다 안전한 쿼리 작성을 도와줍니다.
 *
 * 동적 쿼리 작성: 빌더 패턴을 사용하면 동적으로 객체를 생성하면서 필요한 속성을 설정할 수 있습니다. Querydsl도 동적으로 쿼리를 작성하거나 조건을 추가할 수 있는 유연성을 제공합니다.*/

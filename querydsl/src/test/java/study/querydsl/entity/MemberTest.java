package study.querydsl.entity;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
@Commit
class MemberTest {

    @Autowired
    EntityManager em;

    @Test
    public void testEntity() {

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

        //영속성 컨텍스트를 깔끔하게 초기화
        //1.em.flush(); 영속성 컨텍스트에 담겨져 있는 객체(object)들을 실제 쿼리를 만들어서 DB에 전달
        //2.em.clear(); : 영속성 컨텍스트를 초기화하여 1차 캐시를 비워준 후, 이 후 쿼리를 전달해 깔끔하게 출력 가능
        em.flush();
        em.clear();

        //확인 및 검증
        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();

        for (Member member : members) {
            System.out.println("member = " + member);
            System.out.println("-> member.getTeam() = " + member.getTeam());
        }

    }

}
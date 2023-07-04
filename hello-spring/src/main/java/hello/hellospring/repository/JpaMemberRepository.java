package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.hibernate.annotations.common.reflection.XMember;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository  implements MemberRepository {

    //jpa는 EntityManager로 모든 동작이 진행됩니다.
    //jpa 라이브러리를 다운받으면서 EntityManager를 자동으로 생성
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    //↓ save 메서드는 아래와 같이만 작성해주면 jpa가 인서트 쿼리를 전부 만든 후 DB에 넘겨주고 setId까지 전부 진행을 해줌
    //1개의 데이터를 저장하거나 찾는 코드에는 pk기반으로 작성이 가능   
    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }


    //↓ find 메서드는 EntityManger 안에 em.find()로 매개변수로 조회할 타입과 식별자를 입력해주면 조회 가능
    //return에는 Optional로 반환하고, 값이 없을 수도 있기 때문에 ofNullable()사용
    //1개의 데이터를 저장하거나 찾는 코드에는 pk기반으로 작성이 가능
    @Override
    public Optional<Member> findById(Long id) {
       Member member =  em.find(Member.class, id);
        return Optional.ofNullable(member);

    }

    //findByName 같은 경우에는 JPQL이라는 객체지향 언어를 사용
    //1-2개의 데이터가 아닌 대량의 데이터를 활용하는 코드는 jpql을 작성해주어야 함
    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class).setParameter("name",name).getResultList();

        return result.stream().findAny();
    }

    //1-2개의 데이터가 아닌 대량의 데이터를 활용하는 코드는 jpql을 작성해주어야 함
    @Override
    public List<Member> findAll() {
        //"select m from Member m" 이 문장이 jpql이라는 쿼리 언어인데, 보통 테이블에서 sql을 출력하는데 여기선 select의 대상이 보통 *이나 id, name이 되는데 여기선 Member 엔티티 자체를 선택
        //맵핑이 다 되어있고, 해당 쿼리문만 작성해주면 됨
        return em.createQuery("select m from Member m", Member.class).getResultList();
    //JPQL이라는 쿼리 언어
    //객체를 대상으로 쿼리를 전달해주면 sql로 번역이 됨
    }
}

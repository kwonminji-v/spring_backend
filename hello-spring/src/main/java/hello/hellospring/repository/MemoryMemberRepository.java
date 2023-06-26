package hello.hellospring.repository;


//작성한 코드가 제대로 작동하는 기능인지 실행해서 테스트 할 때 , 테스트 케이스를 이용합니다. java는 JUnit이라는 프레임워크로 테스트를 실행해서 문제를 해결합니다.
import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {


    private static Map<Long, Member> store = new HashMap<>();
    private  static long sequence = 0L; //0,1,2 등 key 값을 생성해주는 역할을 합니다.
    @Override
    public Member save(Member member) {
        //store에 넣기전에 member에 ID값을 세팅해주고, 그 id값을 store에 저장을 하면 그 값이 Map에 저장됩니다.
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
        //결과가 없으면 null이 나오게 되는데, null이 반환될 가능성이 있으면 Optional로 감싸줌
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream().filter(member -> member.getName().equals(name)).findAny();

    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
        //store.values : member들이 반환이 됩니다.
    }

    public void clearStore() {
        store.clear();
    }
}




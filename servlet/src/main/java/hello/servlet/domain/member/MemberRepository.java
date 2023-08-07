package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 동시성 문제가 고려되어 있지 않기때문에, 실무에서는 ConcurrentHashMap , AtomicLong을 사용하는 것을 고려해야 함*/

public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static Long sequence = 0L;  //id가 하나씩 증가하는 sequence를 사용

    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance() {
        return instance;
    }

    //싱글톤으로 생성시에는 아무나 접근이 가능하지 않도록 private로 만들어 주어야 함
    private MemberRepository() { }

    public Member save (Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() { //store에 있는 모든 값들을 꺼내서 새로운 ArrayList에 담음
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}

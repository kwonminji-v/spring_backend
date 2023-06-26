package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member); //회원을 저장소에 저장이 됨
    Optional<Member> findById(Long id); //저장된 회원의 id데이터를 찾아올 수 있음
    Optional<Member> findByName(String name); //저장된 회원의 name데이터를 찾아올 수 있음
    List<Member> findAll(); //저장된 모든 회원의 리스트를 반환해줌

}

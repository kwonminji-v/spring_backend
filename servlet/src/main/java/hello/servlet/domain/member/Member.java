package hello.servlet.domain.member;


import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class Member { //식별자 id, username, age 3가지의 필드값을 설정

    private Long id; //repository에 저장하면 id 발급이 가능해짐
    private String username;
    private int age;

    public Member() { }

    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }
}

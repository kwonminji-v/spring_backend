package hello.hellospring.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//jpa를 사용하려면 Entity를 맵핑해주어야 함
//Entity 어노테이션을 입력하게되면 아래의 멤버 변수는 jpa가 관리하는 멤버변수로 여겨짐
@Entity
public class Member {

    //PK를 맵핑해주어야 합니다 H2 db에서 sql insert 문을 작성하면 db가 id를 자동으로 생성 / 이런 상황은 @GeneratedValue (strategy = GenerationType.IDENTITY) 이렇게 작성필요
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    //현재 db의 value의 컬럼이름은 name이라 그대로 두어도 되지만
    //만약 username이라면 @Column(name = "username") 이라고 입력

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

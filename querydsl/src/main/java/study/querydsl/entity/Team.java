package study.querydsl.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//protected 접근 제한자로 기본 생성자를 만듬
public class Team {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    /**Member에 있는 Team 객체가 연관관계의 주인이 됨
     * 해당 필드는 연관관계의 주인이 아니기때문에 여기서 외래키의 값을 업데이트 하진 않음*/
    @OneToMany(mappedBy = "team") //반대 방향 세팅 (주인이 아님을 의미하는 mappedBy!)
    private List<Member> members = new ArrayList<>();


    public Team(String name) {
        this.name = name;
    }
}

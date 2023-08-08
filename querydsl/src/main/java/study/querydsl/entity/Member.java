package study.querydsl.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id","username","age"})
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String username;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY) //ManyToOne에서는 항상 fetch를 LAZY로 설정!
    @JoinColumn(name = "team_id") //외래 키 이름!
    private Team team;

    public Member(String username) {
        this(username,0,null);
    }

    public Member(String username, int age) {
        this(username, age, null);
    }

    public Member(String username, int age, Team team) {
        this.username = username;
        this.age = age;
        if (team != null) {
            changeTeam(team);
        }
    }

    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this); //양방향 연관관계로서 반대편에 this 값 전달하기 위한 코드
    }
}

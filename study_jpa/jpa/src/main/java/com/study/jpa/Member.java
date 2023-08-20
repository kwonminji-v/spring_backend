
package com.study.jpa;

import jakarta.persistence.*;

/*
 * @TableGenerator(name = "MEMBER_SEQ_GENERATOR", table = "MY_SEQUENCES", // 매핑할
 * 데이터 베이스 시퀀스 이름 pkColumnValue = "MEMBER_SEQ", allocationSize = 1)
 */
@Entity
public class Member extends BaseEntity {

	@Id 
	@GeneratedValue
	@Column(name = "MEMBER_ID")
	private Long id;

	@Column(name = "USERNAME")
	private String username;


	@ManyToOne(fetch = FetchType.EAGER) //지연로딩으로 설정하게되면 Team 객체를 proxy 객체로 조회하게 됩니다.
	@JoinColumn(name = "TEAM_ID") //어떤 컬럼과 조인할 지 (Team과 Member 테이블의 TEAM_ID(FK)를 매핑)
	private Team team;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
}

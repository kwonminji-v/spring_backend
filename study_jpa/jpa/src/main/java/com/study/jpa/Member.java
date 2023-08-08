
package com.study.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

/*
 * @TableGenerator(name = "MEMBER_SEQ_GENERATOR", table = "MY_SEQUENCES", // 매핑할
 * 데이터 베이스 시퀀스 이름 pkColumnValue = "MEMBER_SEQ", allocationSize = 1)
 */
@Entity
@SequenceGenerator(name = "MEMBER_A_SEQ_GENERATOR",
					sequenceName = "MEMBER_A_SEQ", initialValue = 1, allocationSize = 1		)
public class Member {

	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_A_SEQ_GENERATOR")
	@Column(name = "MEMBER_ID")
	private Long id;

	@Column(name = "USERNAME")
	private String username;
	
//	@Column(name = "TEAM_ID")
//	private Long teamId;
	
	@ManyToOne //Member가 N , Team은 1 (1개의 팀에 여러 member가 소속)
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

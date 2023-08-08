package com.study.jpa;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity

@SequenceGenerator(name = "TEAM_SEQ_GENERATOR", 
					sequenceName = "TEAM_SEQ", initialValue = 1, allocationSize = 1)

// name=식별자 생성기 이름, sequenceName=DB에 등록될 시퀀스이름, initialValue=최초시작하는 수, allocationSize=증가하는수)
public class Team {
	
	
	@Id 
	@GeneratedValue(strategy = GenerationType. SEQUENCE, generator = "TEAM_SEQ_GENERATOR")
	@Column(name = "TEAM_id")
	private Long id;
	private String name;
	
	@OneToMany(mappedBy = "team") //mappedBy : 일대다 매핑에서 어던 객체와 연결되어 있는지 (반대편 방향을 의미) 알려주는 어노테이션
	//team으로 매핑이 되어있음을 의미
	private List<Member> members = new ArrayList<>();

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

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}
	

	
	
	
}

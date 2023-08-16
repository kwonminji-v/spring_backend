package com.study.jpa;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
// name=식별자 생성기 이름, sequenceName=DB에 등록될 시퀀스이름, initialValue=최초시작하는 수, allocationSize=증가하는수)
public class Team extends BaseEntity{
	
	
	@Id
	@Column(name = "TEAM_id")
	private Long id;

	@Column(name = "USERNAME")
	private String name;
	
	@OneToMany
	@JoinColumn(name = "TEAM_ID")
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

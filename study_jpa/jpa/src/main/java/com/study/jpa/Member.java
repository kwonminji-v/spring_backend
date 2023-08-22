
package com.study.jpa;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/*
 * @TableGenerator(name = "MEMBER_SEQ_GENERATOR", table = "MY_SEQUENCES", // 매핑할
 * 데이터 베이스 시퀀스 이름 pkColumnValue = "MEMBER_SEQ", allocationSize = 1)
 */
@Entity
public class Member {

	@Id 
	@GeneratedValue
	@Column(name = "MEMBER_ID")
	private Long id;

	@Column(name = "USERNAME")
	private String username;

	//기간에 대한 공통설정
	@Embedded
	private Period workPeriod;

	//주소에 대한 속성
	@Embedded
	private Address homeAddress;

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

	public Period getWorkPeriod() {
		return workPeriod;
	}

	public void setWorkPeriod(Period workPeriod) {
		this.workPeriod = workPeriod;
	}

	public Address getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}
}

/*
 * package com.study.jpa;
 * 
 * 
 * 
 * import jakarta.persistence.Column; import jakarta.persistence.Entity; import
 * jakarta.persistence.GeneratedValue; import
 * jakarta.persistence.GenerationType; import jakarta.persistence.Id; import
 * jakarta.persistence.TableGenerator;
 * 
 * @Entity
 * 
 * @TableGenerator( name = "MEMBER_SEQ_GENERATOR", table = "MY_SEQUENCES", //매핑할
 * 데이터 베이스 시퀀스 이름 pkColumnValue = "MEMBER_SEQ", allocationSize = 1) public class
 * Member {
 * 
 * @Id
 * 
 * @GeneratedValue(strategy = GenerationType.TABLE, generator =
 * "MEMBER_SEQ_GENERATOR") private Long id;
 * 
 * @Column(name="name", nullable = false) private String username;
 * 
 * 
 * public Member() { }
 * 
 * 
 * public Long getId() { return id; }
 * 
 * 
 * public void setId(Long id) { this.id = id; }
 * 
 * public String getUsername() { return username; }
 * 
 * public void setUsername(String username) { this.username = username; }
 * 
 * }
 */
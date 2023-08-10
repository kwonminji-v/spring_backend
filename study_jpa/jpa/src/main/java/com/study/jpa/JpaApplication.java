package com.study.jpa;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

@SpringBootApplication
public class JpaApplication {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	

		try {

			Member member = new Member();
			member.setUsername("MemberA");

			em.persist(member);

			//Team테이블에 아래 하단내용에 인서트 가능
			Team team = new Team();
			team.setName("TeamA");

			//team테이블에 저장되지 않습니다! 팀테이블에 해당 내용의 외래키가 없기 때문에
			team.getMembers().add(member);

			em.persist(team);

			tx.commit();
			
		} catch (Exception e) {
		    e.printStackTrace();//추가
			tx.rollback();
		}finally {
			em.close();
		}
		
		emf.close();

		}
		
	}

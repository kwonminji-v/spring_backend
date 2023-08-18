package com.study.jpa;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

@SpringBootApplication
public class JpaApplication {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	

		try {

			Team team = new Team();
			team.setName("teamA");
			em.persist(team);

			Member member1 = new Member();
			member1.setUsername("Hello1");
			member1.setTeam(team);
			em.persist(member1);

			em.flush();
			em.clear();

			Member m = em.find(Member.class, member1.getId());

			System.out.println("m = " + m.getTeam().getClass());

			System.out.println("==============");
			m.getTeam().getName();
			System.out.println("==============");


			tx.commit();
			
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();//추가
		}finally {
			em.close();
		}
		
		emf.close();

		}

}

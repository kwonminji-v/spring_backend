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

			Team team = new Team();
			team.setName("TeamA");
			//team.getMembers().add(member);
			em.persist(team);
			
			Member member = new Member();
			member.setUsername("회원 1");
			member.setTeam(team);
			em.persist(member);


			//em.flush();
			//em.clear();

			Team findTeam = em.find(Team.class, team.getId());
			List<Member> members = findTeam.getMembers();

			for (Member m : members) {
				System.out.println("m = " + m.getUsername());
			}

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

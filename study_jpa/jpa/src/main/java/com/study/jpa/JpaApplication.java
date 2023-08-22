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
			member.setUsername("안녕반가워");
			member.setHomeAddress(new Address("도시", "거리","번지수"));
			member.setWorkPeriod(new Period());

			em.persist(member);

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

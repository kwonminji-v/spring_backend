package com.study.jpaboard;

import com.study.jpaboard.domain.Member;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

@SpringBootApplication
public class JpaboardApplication {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {

			Member member = em.find(Member.class, 1L);


			tx.commit();

		} catch (Exception e) {
			tx.rollback();
		}finally {
			em.close();
		}

		emf.close();

		}

	}

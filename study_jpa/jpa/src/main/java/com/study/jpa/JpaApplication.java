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

			Movie movie = new Movie();
			movie.setDirector("정우성");
			movie.setActor("한효주");
			movie.setName("무빙");
			movie.setPrice(10000);

			em.persist(movie);

			em.flush();
			em.clear();

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

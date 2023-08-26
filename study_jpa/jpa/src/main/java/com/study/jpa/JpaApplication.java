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

			Member member = new Member();
			member.setUsername("멤버 1");
			member.setHomeAddress(new Address("도시1", "길거리1", "123-123"));

			member.getFavoriteFoods().add("치킨");
			member.getFavoriteFoods().add("바나나");
			member.getFavoriteFoods().add("고기");

			member.getAddressHistory().add(new Address("이전도시", "이전길거리", "이전번지"));
			member.getAddressHistory().add(new Address("이전도시1", "이전길거리1", "이전번지1"));
			em.persist(member);

			em.flush();
			em.clear();

			System.out.println("==============시작==============");
			Member findMember = em.find(Member.class, member.getId());


			System.out.println("findMember = " + findMember);


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

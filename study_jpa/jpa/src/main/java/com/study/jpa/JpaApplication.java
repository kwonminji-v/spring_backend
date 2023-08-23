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

			Address address = new Address("city", "street", "100-1000");

			Member member1 = new Member();
			member1.setUsername("Member 1");
			member1.setHomeAddress(address);
			em.persist(member1);

			Address copyAddress = new Address(address.getCity(), address.getStreet(), address.getZipcode());
			Member member2 = new Member();
			member2.setUsername("Member 2");
			member2.setHomeAddress(copyAddress);
			em.persist(member2);

			member1.getHomeAddress().setCity("새로운 도시");
			/**Setter를 제거함으로써 값 변경이 불가능해지며 side effect 발생을 막을 수 있습니다.
			 * 컴파일 에러가 발생하며, 사전에 에러를 발견하여 수정할 수 있습니다. */


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

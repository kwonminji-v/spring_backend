package com.study.jpaboard;

import com.study.jpaboard.domain.Order;
import com.study.jpaboard.domain.OrderItem;
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

			Order order = new Order();
			em.persist(order);

			OrderItem orderItem = new OrderItem();
			orderItem.setOrder(order);
			em.persist(orderItem);


			order.addOrderItem(new OrderItem());

			
			tx.commit();
			
		} catch (Exception e) {
			tx.rollback();
		}finally {
			em.close();
		}
		
		emf.close();

		}
		
	}

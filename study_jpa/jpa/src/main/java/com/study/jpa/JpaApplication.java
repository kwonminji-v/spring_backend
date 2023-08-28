package com.study.jpa;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Set;


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

			member.getAddressHistory().add(new AddressEntity("이전도시", "이전길거리", "이전번지"));
			member.getAddressHistory().add(new AddressEntity("이전도시1", "이전길거리1", "이전번지1"));
			em.persist(member);

			em.flush();
			em.clear();

			System.out.println("==============시작==============");
			//Member findMember = em.find(Member.class, member.getId());

			//ex. 도시1에서 도시2로 이사감
			//아래와 같은 방식은 사용하면 XX
			//findMember.getHomeAddress().setCity("도시2");
/*			Address address = findMember.getHomeAddress();
			findMember.setHomeAddress(new Address("도시2",address.getStreet(),address.getZipcode()
			));

			//컬렉션내부의 치킨을 -> 떡볶이로 변경
			findMember.getFavoriteFoods().remove("치킨");
			findMember.getFavoriteFoods().add("떡볶이");*/

			//주소지를 변경해보자
//			findMember.getAddressHistory().remove(new AddressEntity("이전도시", "이전길거리", "이전번지"));
//			findMember.getAddressHistory().add(new AddressEntity("새도시1","새길거리","새번지"));
//
//			System.out.println("findMember = " + findMember);


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

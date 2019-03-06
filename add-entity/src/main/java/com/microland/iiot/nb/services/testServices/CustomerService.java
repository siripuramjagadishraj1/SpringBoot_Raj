package com.microland.iiot.nb.services.testServices;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microland.iiot.nb.services.dto.UserInfo;

@Service
public class CustomerService {
	
	@Autowired
    private InventoryRepository repository;
	
	@PersistenceContext
	private EntityManager entityManager;    //1. Hibernate with JPA Implementation(Latest Way)
	
	public List<UserInfo> findAllDemo() {
		ArrayList<Integer> nums = new ArrayList<>();
		nums.add(1);
		nums.add(2);
		return (List<UserInfo>)repository.findAllById(nums);
	}
	
	@Transactional  //this is required with entityManager else will get error
	public void entityInsertion() {
		
		UserInfo info1 = new UserInfo(); info1.setUserName("Info 1");
		UserInfo info2 = new UserInfo(); info2.setUserName("Info 2");
		
		System.out.println(info1);
		entityManager.persist(info1);
		System.out.println(info1);
		info1.setUserName("Jagadish");
		
		System.out.println(info2);
		Session sessionTest = this.entityManager.unwrap(Session.class);
		sessionTest.save(info2);
		//if(1==1) 	throw new RuntimeException(); //Transaction rollback's due to exception 
		System.out.println(info2);
		info2.setUserName("Jagadish Edited");
	}
	

	

}

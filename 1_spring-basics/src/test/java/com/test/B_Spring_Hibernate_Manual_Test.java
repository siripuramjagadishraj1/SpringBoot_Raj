package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.application.HibernateEntityManagerConfiguration;
import com.jpa.db.CustomerService;

@RunWith(SpringRunner.class)
//@Import(value= {HibernateSessionFactoryConfiguration.class})  //SessionFactory
@Import(value= {HibernateEntityManagerConfiguration.class})		//EntityManager
@PropertySource("classpath:application.properties")
public class B_Spring_Hibernate_Manual_Test {
	
	@Autowired
	private CustomerService service;

	@Test
	public void testPersistance() {
		service.persist();
		System.out.println("Done");
	}
}
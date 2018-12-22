package com.application;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.jpa.db.CustomerService;

public class B_Spring_Hibernate_Manual_MAIN {
	
	//For xml configuration ceck out these links.
	//http://www.mybatis.org/spring/getting-started.html
	//https://www.baeldung.com/transaction-configuration-with-jpa-and-spring
	//https://www.baeldung.com/the-persistence-layer-with-spring-and-jpa
	//https://www.baeldung.com/hibernate-4-spring
	
	public static Logger log= LoggerFactory.getLogger(B_Spring_Hibernate_Manual_MAIN.class);
	
	public static void main(String[] args) throws Exception{
		//ApplicationContext ctx = new AnnotationConfigApplicationContext(HibernateSessionFactoryConfiguration.class);  //Hibernate Session (You have to make some changes. in CustomerService)
		ApplicationContext ctx = new AnnotationConfigApplicationContext(HibernateEntityManagerConfiguration.class);     //Hibernate EntityManager
		CustomerService service = (CustomerService) ctx.getBean("customerService");
		//service.saveSpringTransaction();
		//service.saveManualTransaction();
		service.persist();
		((ConfigurableApplicationContext)ctx).close();
	}
}

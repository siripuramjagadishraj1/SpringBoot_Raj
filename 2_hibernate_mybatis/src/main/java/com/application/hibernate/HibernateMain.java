package com.application.hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateMain {
	public static void main(String[] args) throws Exception {
		
		// AnnotationConfiguration conf = new AnnotationConfiguration().configure("hibernate.cfg.xml"); //Will work in 4.0 not in 5.0
		// 1. Will work both for XML and Annotation. (4.0 & 5.0)
		Configuration conf = new Configuration().configure("hibernate/hibernate.cfg.xml");
		SessionFactory factory = conf.buildSessionFactory(); // Start Session session
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
			System.out.println(session.save(new Employee("Jagadish", "Raj", 5000)));
		tx.commit();
		session.close();
		factory.close(); 
		
		// 2. Will work in 5.0 only (Using session) |@Autowire Session
		System.out.println("==============================================");
		StandardServiceRegistry conf2 = new StandardServiceRegistryBuilder().configure("hibernate/hibernate.cfg.xml").build(); // To be created once & destroyed
		SessionFactory factory2 = new MetadataSources(conf2).buildMetadata().buildSessionFactory();
		Session session2 = factory2.openSession();
		session2.beginTransaction();
			session2.save(new Employee("Jagadish", "Raj", 5000));
			session2.save(new Employee("Sateesh", "Sai", 6000));
		session2.getTransaction().commit();
		session2.close();
		factory2.close();
		StandardServiceRegistryBuilder.destroy(conf2);
		
		// 3. Will work in 5.0 only (Alternate way using entityManager) |@Autowire EntityManager
		System.out.println("==============================================");
		StandardServiceRegistry conf3 = new StandardServiceRegistryBuilder().configure("hibernate/hibernate.cfg.xml").build(); // To be created once & destroyed
		SessionFactory factory3 = new MetadataSources(conf3).buildMetadata().buildSessionFactory();
		Session session3 = factory3.openSession();
		EntityManagerFactory entityManagerFactory = session3.getEntityManagerFactory();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(new Employee("Ajay", "Raj", 5000));
			entityManager.persist(new Employee("Jagadish", "Raj", 5000));
			entityManager.persist(new Employee("Third", "Raj", 5000));
			entityManager.getTransaction().commit();
			entityManager.close();
		session3.close();
		factory3.close();
		StandardServiceRegistryBuilder.destroy(conf3);
	}
}

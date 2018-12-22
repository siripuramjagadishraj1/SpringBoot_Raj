package com.jpa.db;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
	
	@PersistenceContext
	private EntityManager entityManager;    //1. Hibernate with JPA Implementation(Latest Way)
	
	@Transactional  //this is required with entityManager else will get error
	public void persist() {
		this.entityManager.persist(new Employee("Saikumar", "Last", 1231));
		Session sessionTest = this.entityManager.unwrap(Session.class);
		sessionTest.save(new Employee("SaikumarAgain", "Last", 1231));
	}
	
	//-------------------------------------------------------------
	
	/*@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;  //2. Hibernate with SessionFactory (Older way)
	
	@Transactional
	public void saveSpringTransaction() {
		Session session = sessionFactory.getCurrentSession();
			session.save(new Employee("Veeru", "Last", 1231));
			System.out.println("Lock");
	}
	
	//@Transactional --No error even if @Transactional not used.
	public void saveManualTransaction() {
		Session session = sessionFactory.openSession();
		Transaction tranx = session.beginTransaction();
			session.save(new Employee("Veeru Test", "Last", 1231));
		tranx.commit();
		session.close();
	}
	*/
	
}

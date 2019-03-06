package com.microland.iiot.nb.addentity;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.microland.iiot.nb.services.dto.UserInfo;
import com.microland.iiot.nb.services.testServices.FooterService;
import com.microland.iiot.nb.services.testServices.FooterServiceMocked;
import com.zaxxer.hikari.HikariDataSource;

@RunWith(SpringRunner.class)
@ComponentScan(basePackages= {"com.microland.iiot.nb.services"
							 })
//@Import(value= {com.microland.iiot.nb.configuration.HibernateSessionFactoryConfiguration.class})
@TestPropertySource(locations="classpath:application.properties")
public class SessionFactoryTesting {
	//Note: user either sessionFactory or EntityManager, not both(EntityManager is preferable)
	
	private static final Logger logger = LoggerFactory.getLogger(SessionFactoryTesting.class);
	
	@MockBean
	private FooterServiceMocked footerServiceMocked;
	
	@Autowired
	private FooterService footerService;
	
	@Autowired
	private HikariDataSource datasource;
	
	@Autowired
	private JdbcTemplate template;
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	@Before
	public void doBefore() throws Exception{
		Mockito.when(footerServiceMocked.getFooter()).thenReturn("Its Mocked");
	}
	

	@Test
	public void testSessionFactory() throws Exception{
		//1. Autowire
		logger.info(footerService.getFooter());
		//2. Mocking
		logger.info(footerServiceMocked.getFooter());
		//3. JDBC Database Connectivity.
		Connection conn = datasource.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from users");
		while(rs.next()) {
			System.out.println(rs.getObject(1));
		}
		
		//4. SpringJDBC Template
		List<Map<String, Object>> completeList = template.queryForList("select * from users");
		completeList.forEach((item)->{
			System.out.println(item);
		});
		
		
		//5. Hibernate sessions. with Manual Transaction
		UserInfo info = new UserInfo();
		info.setUserName("Joga dish");

		Session session = sessionFactory.openSession();
		Transaction tranx = session.beginTransaction();
			session.save(info);
		tranx.commit();
		session.close();
		
		
	}
	
	
	
	
}

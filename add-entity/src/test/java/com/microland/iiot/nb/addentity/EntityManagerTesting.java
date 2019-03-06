package com.microland.iiot.nb.addentity;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;

import com.microland.iiot.nb.services.dto.UserInfo;
import com.microland.iiot.nb.services.testServices.CustomerService;
import com.microland.iiot.nb.services.testServices.FooterService;
import com.microland.iiot.nb.services.testServices.FooterServiceMocked;
import com.microland.iiot.nb.services.testServices.InventoryRepository;
import com.zaxxer.hikari.HikariDataSource;

public class EntityManagerTesting extends BasicTests{
	//Note: user either sessionFactory or EntityManager, not both(EntityManager is preferable)
	
	private static final Logger logger = LoggerFactory.getLogger(EntityManagerTesting.class);
	
	@MockBean
	private FooterServiceMocked footerServiceMocked;
	
	@Autowired
	private FooterService footerService;
	
	@Autowired
	private HikariDataSource datasource;
	
	@Autowired
	private JdbcTemplate template;
	
	@Autowired
	private CustomerService service;
	
	@Autowired
    private InventoryRepository jpaRepository;
	
	@Before
	public void doBefore() throws Exception{
		logger.info("Mocking...");
		Mockito.when(footerServiceMocked.getFooter()).thenReturn("Its Mocked");
	}
	
	//@Ignore
	@Test
	public void a_testMocking() throws Exception{
		//1. Autowire
		System.out.println(footerService.getFooter());
		//2. Mocking
		System.out.println(footerServiceMocked.getFooter());
		System.out.println("==================a_testMocking==================");
	}
	
	//@Ignore
	@Test
	public void b_JDBCTemplateTest() throws Exception{
		//3. JDBC Database Connectivity.
		Connection conn = datasource.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from UserInfo");
		while(rs.next()) {
			System.out.println(rs.getObject(2).toString());
		}
		System.out.println("=================b_JDBCTemplateTest===================");
	}
	

	//@Ignore
	@Test
	public void c_springJDBCTemplateTest() throws Exception{
		//4. SpringJDBC Template
		List<Map<String, Object>> completeList = template.queryForList("select * from UserInfo");
		completeList.forEach((item)->{
			System.out.println(item.toString());
		});
		System.out.println("=================c_springJDBCTemplateTest===================");
	}
	
	//@Ignore
	@Test
	public void d_EntityManagerAndSessionsTest() throws Exception{
		service.entityInsertion();
		System.out.println("==================d_EntityManagerAndSessionsTest==================");
	}
	
	//@Ignore
	@Test
	public void e_repositoryTest() {
		System.out.println("1. deleteAll:");
		jpaRepository.deleteAll();
		
		UserInfo u1=new UserInfo(1, "Shivam");
		UserInfo u2=new UserInfo(2, "Raghav");
		UserInfo u3=new UserInfo(3, "Rohit");
		
		System.out.println("2. save: "+u1);
		jpaRepository.save(u1);
		u1.setUserName("Rahu Ketu");
		List<UserInfo> saveList = new ArrayList<UserInfo>();
		
		saveList.add(u2);saveList.add(u3);saveList.add(u1);
		System.out.println("3. saveAll: "+saveList);
		jpaRepository.saveAll(saveList);
		
		ArrayList<Integer> nums = new ArrayList<>();
		nums.add(1);
		nums.add(2);

		System.out.println("4. findAllDemo"+(List<UserInfo>)jpaRepository.findAllDemo("_hiv%"));
		System.out.println("5. findAll"+(List<UserInfo>)jpaRepository.findAll());
		System.out.println("6. findAllById: "+(List<UserInfo>)jpaRepository.findAllById(nums));
		System.out.println("7. count: "+jpaRepository.count());
		UserInfo inf = new UserInfo();
		inf.setUserNo(1);
		jpaRepository.delete(inf);
		
		UserInfo infy = new UserInfo();
		infy.setUserName("Shivam");
		Example<UserInfo> example = Example.of(infy);
		System.out.println("8. findAllExample: "+jpaRepository.findAll(example));
		
		@SuppressWarnings("deprecation")
		Pageable page = new PageRequest(0, 3, Sort.Direction.DESC, "userName");
		System.out.println("9. findAll-Pagable: "+(jpaRepository.findAll(page)).getContent());
		System.out.println("==================e_repositoryTest==================");
	}
	
	
}

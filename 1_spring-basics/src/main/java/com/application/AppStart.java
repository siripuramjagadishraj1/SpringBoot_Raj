package com.application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.beans.SpringBeanConfiguration;

public class AppStart {
	
	public static Logger log= LoggerFactory.getLogger(AppStart.class);
	
	
	public static void main(String[] args) throws Exception{
		//1. XML Configuration
		//ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml","spring.xml");
		//2. Zero-XML Configuration
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringBeanConfiguration.class, SpringBeanConfiguration.class);
		for (String each : ctx.getBeanDefinitionNames()) {
			System.out.println(each+"	:"+ctx.getBean(each));
		}
		
		//DataSource Configuration (DataSource and Hikari)
		DataSource dataSource = (DataSource)ctx.getBean("dataSource");
		//DataSource dataSource = (DataSource)ctx.getBean("hikariDataSource");
		Connection conn = dataSource.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from country");
		while(rs.next()) {
			System.out.println(rs.getObject(3));
		}
		rs.close();
		stmt.close();
		conn.close();
		
		//Spring JDBC Configuration
		JdbcTemplate template = (JdbcTemplate)ctx.getBean("jdbcTemplate");
		List<Map<String, Object>> completeList = template.queryForList("select * from country");
		completeList.forEach((item)->{
			System.out.println(item);
		});
		
		((ConfigurableApplicationContext)ctx).close();
	}
}

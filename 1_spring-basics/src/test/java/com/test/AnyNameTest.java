package com.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.beans.SpringBeanConfiguration;

@RunWith(SpringRunner.class)
//1. XML Way
@ContextConfiguration(locations = "classpath:spring.xml" )

//2. Zero XML way
//@Import(value= {SpringBeanConfiguration.class})
//@PropertySource("classpath:application.properties")
public class AnyNameTest {
	
	@Autowired
	private ApplicationContext ctx;
	
	@Autowired
	private DataSource dataSource;
	
	@Test
	public void testDataSource() throws Exception{
		System.out.println(ctx.getBean("apple3"));
		System.out.println(dataSource.getConnection());
	}

}

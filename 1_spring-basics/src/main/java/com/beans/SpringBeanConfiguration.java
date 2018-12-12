package com.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("classpath:application.properties")
public class SpringBeanConfiguration {
	
	@Autowired
	private Environment env;
	
	@Override
	public String toString() {
		return env.getProperty("server.port");
	}
	
	@Bean(name="seed1")
	//@Scope(value="prototype")
	public Seeds getSeeds1() {
		Seeds seed = new Seeds();
		seed.setColor("ONE");
		seed.setCount(1);
		return seed;
	}
	
	@Bean(name="seed2")
	public Seeds getSeeds2() {
		Seeds seed = new Seeds();
		seed.setColor("TWO");
		seed.setCount(2);
		return seed;
	}
	
	//Setter Injection
	@Bean(name="apple1")
	public Apple getApple() {
		Apple apple = new Apple();
		apple.setCost(1000);
		apple.setName("APPLE-ONE");
		apple.setSeeds(getSeeds1());
		return apple;
	}
	
	@Bean(name="apple2")
	public Apple getApple2() {
		Apple apple = new Apple();
		apple.setCost(2000);
		apple.setName("APPLE-TWO");
		apple.setSeeds(getSeeds2());
		return apple;
	}
	
	//Constructor Injection
	@Bean(name="apple3")
	public Apple getApple3() {
		Apple apple = new Apple(3000, "APPLE-THREE", getSeeds1());
		return apple;
	}
	
	@Bean(name="apple4")
	public Apple getApple4() {
		Apple apple = new Apple(4000, "APPLE-FOUR", getSeeds2());
		return apple;
	}
	
	//DataSource
	@Bean(name="dataSource")
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(env.getProperty("spring.datasource.url"));
		dataSource.setPassword(env.getProperty("spring.datasource.password"));
		dataSource.setUsername(env.getProperty("spring.datasource.username"));
		dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
		return dataSource;
	}
	
	//hikariDatasource
	@Bean(name="hikariDataSource")
	public HikariDataSource hikariDataSource() {
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setJdbcUrl(env.getProperty("spring.datasource.url"));
		dataSource.setPassword(env.getProperty("spring.datasource.password"));
		dataSource.setUsername(env.getProperty("spring.datasource.username"));
		dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
		return dataSource;
	}
	
	//JDBC Template
	@Bean(name="jdbcTemplate")
	public JdbcTemplate jdbcTemplate() {
		JdbcTemplate template = new JdbcTemplate();
			template.setDataSource(hikariDataSource());
		return template;
	}
	
	
	

}

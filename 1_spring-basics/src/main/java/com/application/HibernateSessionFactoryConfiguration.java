package com.application;

import java.util.Properties;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement //this is required with entityManager else will get error
@ComponentScan(basePackages = { "com.jpa.db" })
@PropertySource("classpath:application.properties")
public class HibernateSessionFactoryConfiguration {

	@Autowired
	private Environment env;

	// 1.DataSource
	@Bean(name = "dataSource")
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(env.getProperty("spring.datasource.url"));
		dataSource.setPassword(env.getProperty("spring.datasource.password"));
		dataSource.setUsername(env.getProperty("spring.datasource.username"));
		dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
		return dataSource;
	}

	// 2.hikariDatasource
	@Bean(name = "hikariDataSource")
	public HikariDataSource hikariDataSource() {
		HikariDataSource dataSource = new HikariDataSource();
		/*dataSource.setJdbcUrl(env.getProperty("spring.datasource.url"));
		dataSource.setPassword(env.getProperty("spring.datasource.password"));
		dataSource.setUsername(env.getProperty("spring.datasource.username"));
		dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));*/
		dataSource.setDataSource(dataSource());
		return dataSource;
	}

	// 3.JDBC Template
	@Bean(name = "jdbcTemplate")
	public JdbcTemplate jdbcTemplate() {
		JdbcTemplate template = new JdbcTemplate();
		template.setDataSource(hikariDataSource());
		return template;
	}

	//This has to be used if you want to use HibernateSession, comment entityManagerFactory
	@Bean
	   public LocalSessionFactoryBean sessionFactory() {
	      LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
	      sessionFactory.setDataSource(hikariDataSource());
	      sessionFactory.setPackagesToScan(
	        new String[] { "com.jpa.db" });
	      
	      Properties properties = new Properties();
			properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL94Dialect");
			properties.setProperty("hibernate.hbm2ddl.auto", "create");
			properties.setProperty("hibernate.show_sql", "true");
			properties.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
	      sessionFactory.setHibernateProperties(properties);
	 
	      return sessionFactory;
	   }
	
	  @Bean
	   @Autowired
	   public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
	      HibernateTransactionManager txManager = new HibernateTransactionManager();
	      txManager.setSessionFactory(sessionFactory);
	      return txManager;
	   }
	 
	   @Bean
	   public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
	      return new PersistenceExceptionTranslationPostProcessor();
	   }

}

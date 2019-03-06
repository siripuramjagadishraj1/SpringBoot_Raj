package com.microland.iiot.nb;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.SpringApplication;

@SpringBootApplication
/*@ComponentScan(basePackages= {
							//"com.microland.iiot.nb",
							//"com.microland.iiot.nb.controllers",
							//"com.microland.iiot.nb.services",
							})*/
//@Import(value= {com.microland.iiot.nb.configuration.HibernateEntityManagerConfiguration.class})
//@EnableJpaRepositories
public class AddEntityApplication {
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(AddEntityApplication.class, args);
		for(String each: ctx.getBeanDefinitionNames()) {
			System.out.println(each);
		}
	}
}

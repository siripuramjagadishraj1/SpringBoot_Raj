package com.cloud.demo.defaultapplication;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
public class DefaultApplication {
	public static void main(String[] args) {
		SpringApplication.run(DefaultApplication.class, args);
	}
}

@RestController
class ServiceInstanceRestController {
	
	private Logger log = LoggerFactory.getLogger(ServiceInstanceRestController.class);

	@Autowired
	private DiscoveryClient discoveryClient;

	// localhost:9000/service-instances/default-application
	@RequestMapping("/service-instances/{applicationName}")
	public List<ServiceInstance> serviceInstancesByApplicationName(@PathVariable String applicationName) {
		log.info("serviceInstancesByApplicationName..");
		return this.discoveryClient.getInstances(applicationName);
	}
	
	@RequestMapping("/hello")
	public String hello() {
		return "hello";
	}
}
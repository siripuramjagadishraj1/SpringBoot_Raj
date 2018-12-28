package com;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class ThymeleafApplication {
	public static void main(String[] args) {
		SpringApplication.run(ThymeleafApplication.class, args);
	}
}

@Controller
class TestController{
	//CORS pending
	//Listener pending.
	@GetMapping(value="/hello")
	public String getIndex(Map<String, Object> model) { 
		List<String> fruitList = new ArrayList<String>();
			fruitList.add("Apple");
			fruitList.add("Ball");
			fruitList.add("Car");
		model.put("fruits",fruitList);
		model.put("message", "Hello world"); return "index";
	}

	@ResponseBody
	@GetMapping(value="/helloText", produces=MediaType.APPLICATION_JSON_VALUE)
	public String getText() { return "{\"message\":\"Succeess Message\"\n}"; }
}


//Filter
@Component
@Order(1)
class TransactionFilter implements Filter {
	@Override
	public void destroy() {System.out.println("TransactionFilter.destroy()");}
	@Override
	public void init(FilterConfig arg0) throws ServletException {System.out.println("TransactionFilter.init()");}
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filter)
			throws IOException, ServletException {
		System.out.println("TransactionFilter.doFilter()");
		filter.doFilter(req, res);
	}
}
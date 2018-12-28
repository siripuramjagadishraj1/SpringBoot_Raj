package com;

import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class WebMvcApplication {
	public static void main(String[] args) {
		SpringApplication.run(WebMvcApplication.class, args);
	}
}

@Controller
class TestController{
	@GetMapping("/hello")
	public String getHello(Map<String, Object> map) {
		map.put("name", "Jagadish");
		return "index";
	}
}


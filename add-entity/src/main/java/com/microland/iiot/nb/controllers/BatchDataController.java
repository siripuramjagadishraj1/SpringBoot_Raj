package com.microland.iiot.nb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.microland.iiot.nb.services.dto.DBEntity;
import com.microland.iiot.nb.services.testServices.CustomerService;

@RestController
@RequestMapping(value="/add")
public class BatchDataController {
	
	@Autowired
	private CustomerService service;
	
	@ResponseBody
	@RequestMapping(value="/querryFromDB/{querryPathVariable}",
					method=RequestMethod.PUT,
					consumes = MediaType.APPLICATION_JSON_VALUE,
					produces = MediaType.APPLICATION_JSON_VALUE
					)
	public DBEntity getBatchData(@PathVariable String querryPathVariable , 
							  @RequestBody DBEntity jsonApple, 
							  @RequestParam String param1, 
							  @RequestParam String param2) {
		System.out.println("@PathVariable: "+querryPathVariable);
		System.out.println("RequestParam: "+jsonApple);
		System.out.println("param1: "+ param1);
		System.out.println("param2: "+ param2);
		//service.persist();
		System.out.println(service.findAllDemo());
		return new DBEntity(100, "Test Apple");
	}

}
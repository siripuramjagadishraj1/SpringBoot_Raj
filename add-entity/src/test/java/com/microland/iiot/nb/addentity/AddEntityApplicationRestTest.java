package com.microland.iiot.nb.addentity;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.microland.iiot.nb.AddEntityApplication;
import com.microland.iiot.nb.services.dto.DBEntity;
import com.microland.iiot.nb.services.testServices.FooterServiceMocked;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment=WebEnvironment.RANDOM_PORT,
		classes= {AddEntityApplication.class}
)
@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:application.properties")
public class AddEntityApplicationRestTest {
	
	private static final Logger logger = LoggerFactory.getLogger(AddEntityApplicationRestTest.class);
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private FooterServiceMocked footerService;
	
	@Before
	public void doBefore() throws Exception{
		Mockito.when(footerService.getFooter()).thenReturn("Its Mocked");
	}
	
	//@Ignore
	@Test
	public void testService() throws Exception{
		logger.info("Default Port: "+port); 
		logger.info("Rest Template: "+restTemplate);  
		logger.info("mockMvc: "+mockMvc);
		System.out.println(footerService.getFooter());
	}

	//@Ignore
	@Test
	public void testRestTemplate() throws Exception {
		//Header, content, param, print and expect..
		DBEntity testApple = new DBEntity();
			testApple.setCost(1000);
			testApple.setName("1000 Apple");
		
		//1. Method 1
		this.restTemplate.put("http://localhost:" + port + "/add/querryFromDB/pathVariableTeste", testApple);
		
		//2. Method 2
		Map<String, String> params = new HashMap<String, String>();
	    params.put("param1", "first Paramenter");
	    params.put("param2", "Second Paramenter");
	    this.restTemplate.put("http://localhost:" + port + "/add/querryFromDB/pathVariableTeste", testApple, params);
	}
	
	

	//@Ignore
	@Test
	public void testMockMvc() throws Exception {
		//Header, content body, param, print and expect..
		this.mockMvc.perform(put("/add/querryFromDB/pathVariableTeste")
					.header("Authorization", "Test Token")
					.header("Content-Type", "application/json")
					.content("{\"cost\":100,\"name\":\"Test Apple\"}")
					.param("param1", "first Paramenter")
					.param("param2", "second Parameter"))
					.andDo(print()).andExpect(status().isOk());
	}
	
}

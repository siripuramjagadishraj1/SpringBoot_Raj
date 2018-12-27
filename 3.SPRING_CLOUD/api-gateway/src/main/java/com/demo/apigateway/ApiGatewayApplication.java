package com.demo.apigateway;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.solr.SolrAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

//import brave.sampler.Sampler;

@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication(exclude={SolrAutoConfiguration.class})
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}
	
	//Logging to ELK
	/*@Bean
	public Sampler defaultSampler(){
		return Sampler.ALWAYS_SAMPLE;
	}*/
}

@RestController
class TestController{
	@GetMapping(value="/hello")
	public String hello() {
		return "hello";
	}
}

/*@Component
class ZuulLoggingFilter extends ZuulFilter{
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public Object run() throws ZuulException {
		HttpServletRequest request = 
				RequestContext.getCurrentContext().getRequest();
		logger.info("request -> {} request uri -> {}", 
				request, request.getRequestURI());
		return null;
	}
	@Override
	public boolean shouldFilter() {	return true;}
	@Override
	public int filterOrder() {	return 1;}
	@Override
	public String filterType() { return "pre";	}

}
*/
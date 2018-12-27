<html>
	<head>
	</head>
	<body>
		<h3>LINKS</h3>
		<ol>
			<li>application.properties (<a href="https://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html">All properties</a>)</li>
		</ol>
		<h3>SPRING BOOT</h3>
		<ol style="margin-top:0px;">
			<li>Basics</li>
			<li>Actuators</li>
			<li>DevTools</li>
			<li>Swagger</li>
			<li>HAL Browser</li>
			<li>Security</li>
			<li>ConfigServer (Spring-Cloud)</li>
			<li>ConfigClient (Spring-Cloud)</li>
		</ol>
		
		<table border="1">
			<tr>
				<td>sl no</td><td>Feature</td><td>How</td><td>verify</td><td>Comments</td>
			</tr>
			<tr>
				<td>1</td>
				<td>Basics</td>
				<td>
					<u>pom.xml</u><br>
					org.springframework.boot:spring-boot-starter-web<br>
					org.springframework.boot:spring-boot-starter-data-jpa<br>
					org.springframework.boot:spring-boot-starter-test<br>
					<u>application.properties</u><br>
					## SERVER<br>
					server.port=8888<br><br>
					
					## DATA_BASE<br>
					spring.datasource.url=jdbc:postgresql://localhost:5432/GE_RENEWABLES<br>
					spring.datasource.username=postgres<br>
					spring.datasource.password=password<br>
					spring.datasource.driver-class-name=org.postgresql.Driver<br>
					spring.datasource.hikari.maximum-pool-size=5<br><br>
					
					## HIBERNATE <br>
					spring.jpa.database-platform = org.hibernate.dialect.PostgreSQL94Dialect<br>
					spring.jpa.show-sql = true<br>
					spring.jpa.hibernate.ddl-auto=create<br>
					spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=false<br>
					spring.jpa.properties.hibernate.temp.use_jdbc_metadata_defaults=false<br>
				</td>
				<td>http://localhost:8888/end_point</td>
				<td>Basic requirement for web-app, if data-jpa is present<br> then DATA_BASE and HIBERNATE properties are required.</td>
			</tr>
			<tr>
				<td>2</td>
				<td>Actuators</td>
				<td>
					<u>pom.xml</u><br>
					org.springframework.boot:spring-boot-starter-actuator<br>
					<u>application.properties</u><br>
					management.endpoints.web.exposure.include=*
						
				</td>
				<td>http://localhost:8888/actuator</td>
				<td>Used to show various aspects of webservice like health, memory consumption, etc..</td>
			</tr>
			<tr>
				<td>3</td>
				<td>HAL-Browser</td>
				<td>
					<u>pom.xml</u><br>
					org.springframework.boot:spring-boot-starter-actuator<br>
					org.springframework.data:spring-data-rest-hal-browser<br>
					<u>application.properties</u><br>
					management.endpoints.web.exposure.include=*
						
				</td>
				<td>http://localhost:8888/</td>
				<td>Used to see Actuator endpoints in a borowser</td>
			</tr>
			<tr>
				<td>4</td>
				<td>DevTools</td>
				<td>
					<u>pom.xml</u><br>
					org.springframework.boot:spring-boot-devtools(optional:true)<br>
				</td>
				<td>Incomplete...</td>
				<td>Server restarts on change of code, remote debugging, etc..</td>
			</tr>
			<tr>
				<td>5</td>
				<td>Swagger</td>
				<td>
					<u>pom.xml</u><br>
					io.springfox:springfox-swagger-ui:2.9.2<br>
					io.springfox:springfox-swagger2:2.9.2<br>
					<u>main.java</u><br>
					@EnableSwagger2(Over class) <br>
					<pre style="margin:0;border:0;">
@Bean public Docket api() { 
	return new Docket(DocumentationType.SWAGGER_2)  
	  .select()                                  
	  .apis(RequestHandlerSelectors.any())              
	  .paths(PathSelectors.any())                          
	  .build();                                           
}
					</pre>
				</td>
				<td>http://localhost:8888/swagger-ui.html</td>
				<td>Used to see API documentation.</td>
			</tr>
			<tr>
				<td>6</td>
				<td>Security</td>
				<td>
					<u>pom.xml</u><br>
					org.springframework.boot:spring-boot-starter-security<br>
					<u>application.properties</u><br>
					spring.security.user.name=user<br>
					spring.security.user.password=password
				</td>
				<td>http://localhost:8888/any_endpoint</td>
				<td>Used to setup basic Authentication, for all endpoints.</td>
			</tr>
			<tr>
				<td>7</td>
				<td>Con-fig Server</td>
				<td>
					<u>pom.xml</u><br>
					PROPERTIES:<b>spring-cloud.version:Finchley.M8</b><br>
					DEPENCENCIES<br>
					org.springframework.cloud:spring-cloud-config-server<br>
					<b>NOTE:</b>Please generate the pox.xml from http://start.spring.io/ <br>
					
					<u>application.properties</u><br>
					## config server<br>
					spring.config.name=configserver<br>
					spring.cloud.config.server.git.uri=file:///C:/Users/siripuramrj/Desktop/git_repo/<br>
					spring.cloud.config.server.git.clone-on-start=true<br>
					
					<u>Main.java</u><br>
					@EnableConfigServer (Over Class)
				</td>
				<td>
				http://localhost:8888/properties_file/dev<br>
				http://localhost:8888/properties_file/qa<br>
				http://localhost:8888/properties_file/default
				</td>
				<td>Used to pick up property files from git repo, <br>HAL browser stop working with this.</td>
			</tr>
		</table>
		
		<h3>SPRING CLOUD (Complete Project Set)</h3>
		<h4>SPRING CLOUD ARCHITECTURE</h4>
		<img src="diagram.png"></img>
		<ol style="margin-top:0px;">
			<!-- <li>Config-Server (Already Described)</li>
			<li>Log Tracing</li> -->
			<li>Eureka Naming Server (<b>DONE</b>)</li>
			<li>Api Gateway (<b>TODO</b>)</li>
			<li>Default Application (DONE)</li>
			<li>spring boot admin (<b>DONE</b>)</li>
		</ol>
		Note: from gateway querry http://localhost:5000/default-application/hello instead of http://localhost:5000/hello
		
		<h3>Eureka Naming Server</h2>
		1. http://start.spring.io (EurekaServer,web)<br>
		2. pom.xml:(Check list)
		<pre style="margin:0px;background:#DDDDDD;">
spring-boot-starter-actuator
spring-boot-starter-web
spring-cloud-starter-netflix-eureka-server (Must)
spring-boot-devtools(Optional)
spring-boot-starter-test
		</pre>
		3. Add @EnableEurekaServer over main.java class<br>
		4. spring-cloud.version: Finchley.RELEASE  (or use Latest version)<br>
		5. application.properties<br>
		<pre style="margin:0px;background:#DDDDDD;">
server.port=8761
eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false
management.endpoints.web.exposure.include=*
		</pre>
		6. Run the Application.
		
		<h3>Register a Default Application with Eureka Naming Server</h2>
		1. http://start.spring.io (web,EurekaDiscovery)<br>
		2. pom.xml:(Check list)
		<pre style="margin:0px;background:#DDDDDD;">
spring-boot-starter-actuator
spring-boot-starter-web
spring-cloud-starter-netflix-eureka-server (Must)
spring-boot-devtools(Optional)
spring-boot-starter-test
		</pre>
		3. Add @EnableDiscoveryClient over main.java class<br>
		4. spring-cloud.version: Finchley.RELEASE  (or use Latest version)<br>
		5. application.properties<br>
		<pre style="margin:0px;background:#DDDDDD;">
## POINTING to Eureka Server.
eureka.client.register-with-eureka=true
eureka.client.fetch-registry=true
eureka.client.service-url.defaultZone=http://localhost:8761/eureka
eureka.instance.health-check-url-path=/actuator/health
		</pre>
		6. Run the Application, and check if it registers.
		
		<h3>Pointing admin to Eureka</h2>
		1. http://start.spring.io (web,EurekaDiscovery)<br>
		2. pom.xml:(Check list)
		<pre style="margin:0px;background:#DDDDDD;">
spring-boot-starter-actuator
spring-boot-starter-web
spring-cloud-starter-netflix-eureka-server (Must)
spring-boot-devtools(Optional)
spring-boot-starter-test
		</pre>
		3. Add @EnableDiscoveryClient over main.java class<br>
		4. spring-cloud.version: Finchley.RELEASE  (or use Latest version)<br>
		5. application.properties<br>
		<pre style="margin:0px;background:#DDDDDD;">
## POINTING to Eureka Server.
eureka.client.register-with-eureka=true
eureka.client.fetch-registry=true
eureka.client.service-url.defaultZone=http://localhost:8761/eureka
eureka.instance.health-check-url-path=/actuator/health
		</pre>
		6. Run the Application, and check if it registers.
	</body>
</html>

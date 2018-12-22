Spring Basics:
==============
1. XML Configuration:(spring.xml)
	a. Dependency injection (Setter, Constructor)
	b. Wiring(3) (NAME,TYPE,CONSTUCTOR)
	c. Bean Scopes(5) (singleton,prototype,request,session,global-session)
	d. Bean LifeCycle Methods, Annotations, Interfaces, Hooks (Please check Apple.java)
		[BeanNameAware, BeanFactoryAware, BeanPostProcessor, InitializingBean, DisposableBean and lot more interfaces...]
		(https://howtodoinjava.com/spring-core/spring-bean-life-cycle)
	e. Enable @Autowiring <context:annotation-config/>
		@Qualifier(value="ambigousBean"), @Resource(name="amibigousBean")
	f. Enable Properties <context:property-placeholder location="classpath:application.properties"/>
		@Value("${server.port}") private String testEnv;
	g. Import Spring resources <import resource="spring2.xml"/>
	h. DriverManagerDataSource, HikariDataSource, JdbcTemplate

2. Zero-XML Configuration:(SpringBeanConfiguration.java)
	a. Dependency injection (Setter, Constructor)
	b. Wiring - No Importance
	c. @Configuration, @Autowired, @Bean, @Scope (SpringBeanConfiguration.java)
	d. Bean LifeCycle Methods, as in XML.
	e. Auto-wiring enabled by default.
	f. @PropertySource("classpath:application.properties")
		@Value("${server.port}") private String testEnv;
		@Autowired private Environment env;
	g. @Import(value= {Apple.class})
	h. DriverManagerDataSource, HikariDataSource, JdbcTemplate
	
3. Enable/Disable Log back Logging:
	a. logback-test.xml
4. Spring+Hibernate with EntityManager(New way) vs Session(Old Way).
5. Spring+MyBatis (TODO)
6. Spring test cases mocking..(TODO)
	

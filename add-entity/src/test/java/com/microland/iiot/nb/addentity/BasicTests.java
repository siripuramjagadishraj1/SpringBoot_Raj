package com.microland.iiot.nb.addentity;

import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@RunWith(SpringRunner.class)
@ComponentScan(basePackages= {"com.microland.iiot.nb"})
@TestPropertySource(locations="classpath:application.properties")
@EnableTransactionManagement  //Required for entity manager to work..
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BasicTests {}

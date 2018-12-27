/*package com.cloud.demo.admin;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import de.codecentric.boot.admin.server.config.AdminServerProperties;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final String adminContextPath;
    
    public SecurityConfig(AdminServerProperties adminServerProperties) {
        this.adminContextPath = adminServerProperties.getContextPath();
    }
    
    //Disables security and login page
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().permitAll()  
            .and().csrf().disable();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        //successHandler.setDefaultTargetUrl(adminContextPath+"/");;
        successHandler.setTargetUrlParameter("redirectTo");

        http.authorizeRequests()
                .antMatchers(adminContextPath + "/assets/**").permitAll()
                .antMatchers(adminContextPath + "/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage(adminContextPath + "/login").successHandler(successHandler).and()
                .logout().logoutUrl(adminContextPath + "/logout").and()
                .httpBasic().and()
                .csrf().disable();
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setTargetUrlParameter("redirectTo");
        successHandler.setDefaultTargetUrl(adminContextPath + "/");

        http.authorizeRequests()
            .antMatchers(adminContextPath + "/assets/**").permitAll() 
            .antMatchers(adminContextPath + "/login").permitAll()
            .anyRequest().authenticated() 
            .and()
        .formLogin().loginPage(adminContextPath + "/login").successHandler(successHandler).and() 
        .logout().logoutUrl(adminContextPath + "/logout").and()
        .httpBasic().and() 
        .csrf()
            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())  
            .ignoringAntMatchers(
                adminContextPath + "/instances",   
                adminContextPath + "/actuator/**"  
            );
        // @formatter:on
    }
	
	//this adds Login and logout pages.. Its important
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
          .formLogin()
          .loginPage("/login")
          .loginProcessingUrl("/login")
          .permitAll();
        http
          .logout().logoutUrl("/logout");
        http
          .csrf().disable();
        http
          .authorizeRequests()
          .permitAll();
        http
          .authorizeRequests()
          .antMatchers("/**")
          .authenticated();
        http.httpBasic();
    }
}*/
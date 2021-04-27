package com.bee.hello.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// Following : https://spring.io/guides/gs/securing-web/
// https://docs.spring.io/spring-security/site/docs/5.4.2/reference/html5/#servlet-hello-auto-configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        .antMatchers("/").permitAll()
        .antMatchers("/actuator/health").permitAll()
        .antMatchers("/actuator/info").permitAll()
        .antMatchers("/actuator/**").hasAuthority("ROLE_ADMIN")
        .anyRequest().authenticated()
        .and()
        .httpBasic();
  }
}

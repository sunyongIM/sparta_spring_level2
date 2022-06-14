//package com.example.level2.security;
//
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfiguration {
//
//    protected void configure(HttpSecurity http) throws Exception{
//        http.httpBasic()
//                .and()
//                .authorizeRequests()
//                .antMatchers("/admin/**").hasRole("Admin");
//    }
//}

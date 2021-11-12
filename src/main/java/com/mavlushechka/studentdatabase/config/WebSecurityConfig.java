package com.mavlushechka.studentdatabase.config;

import com.mavlushechka.studentdatabase.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Bean
    public UserDetailsService mongoUserDetails() {
        return new CustomUserDetailsService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        UserDetailsService userDetailsService = mongoUserDetails();
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/admin-panel/**").hasAuthority("ADMIN")
                    .anyRequest().authenticated()
                    .and()
                .csrf()
                    .disable()
                    .formLogin()
                    .loginPage("/authentication/login")
                    .permitAll()
                    .and()
                .logout()
                    .logoutSuccessUrl("/")
                    .permitAll()
                    .and()
                .exceptionHandling();
    }

    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring()
                    .antMatchers("/js/**", "/css/**", "/img/**", "/fonts/**");
    }
}
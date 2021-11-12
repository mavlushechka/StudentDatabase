package com.mavlushechka.studentdatabase.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/authentication/login").setViewName("authentication/login");
        registry.addViewController("/authentication/change-password").setViewName("authentication/change-password");
        registry.addViewController("/admin-panel/add-users").setViewName("/admin-panel/add-users");
        registry.addViewController("/admin-panel/information/search").setViewName("/admin-panel/information/search");
    }
}
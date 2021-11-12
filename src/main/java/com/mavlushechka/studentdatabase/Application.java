package com.mavlushechka.studentdatabase;

import com.mavlushechka.studentdatabase.domain.Role;
import com.mavlushechka.studentdatabase.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    @Autowired
    private RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    private void saveRole(String role) {
        if (roleRepository.findByRole(role) == null) {
            roleRepository.save(new Role(role));
        }
    }

    @Bean
    CommandLineRunner init() {
        return args -> {
            saveRole("ADMIN");
            saveRole("USER");
        };
    }
}
package com.mavlushechka.studentdatabase.controller;

import com.mavlushechka.studentdatabase.domain.Health;
import com.mavlushechka.studentdatabase.domain.User;
import com.mavlushechka.studentdatabase.repository.HealthRepository;
import com.mavlushechka.studentdatabase.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;
import java.util.Optional;

@Controller
public class HealthController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HealthRepository healthRepository;

    @GetMapping("/information/health")
    public String getUserAndHealth(Authentication authentication, Map<String, Object> model) {
        User user = userRepository.findByUsername(authentication.getName());
        Health health = healthRepository.findById(user.getId()).orElse(new Health());
        model.put("user", user);
        model.put("health", health);
        return "information/health";
    }

    @PostMapping("/information/health/save")
    public String saveHealth(Health health) {
        healthRepository.save(health);
        return "redirect:/";
    }
}

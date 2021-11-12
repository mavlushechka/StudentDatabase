package com.mavlushechka.studentdatabase.controller;

import com.mavlushechka.studentdatabase.domain.EducationalSystem;
import com.mavlushechka.studentdatabase.domain.User;
import com.mavlushechka.studentdatabase.repository.EducationalSystemRepository;
import com.mavlushechka.studentdatabase.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;
import java.util.Optional;

@Controller
public class EducationalSystemController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EducationalSystemRepository educationalSystemRepository;

    @GetMapping("/information/educational-system")
    public String getUserIdAndEducationalSystem(Authentication authentication, Map<String, Object> model) {
        User user = userRepository.findByUsername(authentication.getName());
        EducationalSystem educationalSystem = educationalSystemRepository.findById(user.getId()).orElse(new EducationalSystem());
        model.put("user", user);
        model.put("educationalSystem", educationalSystem);
        return "information/educational-system";
    }

    @PostMapping("/information/educational-system/save")
    public String saveEducationalSystemRepository(EducationalSystem educationalSystem) {
        educationalSystemRepository.save(educationalSystem);
        return "redirect:/";
    }
}

package com.mavlushechka.studentdatabase.controller;

import com.mavlushechka.studentdatabase.domain.Family;
import com.mavlushechka.studentdatabase.domain.User;
import com.mavlushechka.studentdatabase.repository.FamilyRepository;
import com.mavlushechka.studentdatabase.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;
import java.util.Optional;

@Controller
public class FamilyController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FamilyRepository familyRepository;

    @GetMapping("/information/family")
    public String getUserAndFamily(Authentication authentication, Map<String, Object> model) {
        User user = userRepository.findByUsername(authentication.getName());
        Family family = familyRepository.findById(user.getId()).orElse(new Family());
        model.put("user", user);
        model.put("family", family);
        return "information/family";
    }

    @PostMapping("/information/family/save")
    public String saveFamily(Family family) {
        familyRepository.save(family);
        return "redirect:/";
    }
}

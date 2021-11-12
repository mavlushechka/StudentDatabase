package com.mavlushechka.studentdatabase.controller;

import com.mavlushechka.studentdatabase.domain.GraduatedInstitution;
import com.mavlushechka.studentdatabase.domain.User;
import com.mavlushechka.studentdatabase.repository.GraduatedInstitutionRepository;
import com.mavlushechka.studentdatabase.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;
import java.util.Optional;

@Controller
public class GraduatedInstitutionController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GraduatedInstitutionRepository graduatedInstitutionRepository;

    @GetMapping("/information/graduated-institution")
    public String getUserAndGraduatedInstitution(Authentication authentication, Map<String, Object> model) {
        User user = userRepository.findByUsername(authentication.getName());
        GraduatedInstitution graduatedInstitution = graduatedInstitutionRepository.findById(user.getId()).orElse(new GraduatedInstitution());
        model.put("user", user);
        model.put("graduatedInstitution", graduatedInstitution);
        return "information/graduated-institution";
    }

    @PostMapping("/information/graduated-institution/save")
    public String saveGraduatedInstitution(GraduatedInstitution graduatedInstitution) {
        graduatedInstitutionRepository.save(graduatedInstitution);
        return "redirect:/";
    }
}

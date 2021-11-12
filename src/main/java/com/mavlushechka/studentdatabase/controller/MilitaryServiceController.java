package com.mavlushechka.studentdatabase.controller;

import com.mavlushechka.studentdatabase.domain.MilitaryService;
import com.mavlushechka.studentdatabase.domain.User;
import com.mavlushechka.studentdatabase.repository.MilitaryServiceRepository;
import com.mavlushechka.studentdatabase.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;
import java.util.Optional;

@Controller
public class MilitaryServiceController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MilitaryServiceRepository militaryServiceRepository;

    @GetMapping("/information/military-service")
    public String getUserIdAndMilitaryService(Authentication authentication, Map<String, Object> model) {
        User user = userRepository.findByUsername(authentication.getName());
        MilitaryService militaryService = militaryServiceRepository.findById(user.getId()).orElse(new MilitaryService());
        model.put("user", user);
        model.put("militaryService", militaryService);
        return "information/military-service";
    }

    @PostMapping("/information/military-service/save")
    public String saveMilitaryService(MilitaryService militaryService) {
        militaryServiceRepository.save(militaryService);
        return "redirect:/";
    }
}

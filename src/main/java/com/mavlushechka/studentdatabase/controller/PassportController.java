package com.mavlushechka.studentdatabase.controller;

import com.mavlushechka.studentdatabase.domain.Passport;
import com.mavlushechka.studentdatabase.domain.User;
import com.mavlushechka.studentdatabase.repository.PassportRepository;
import com.mavlushechka.studentdatabase.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;
import java.util.Optional;

@Controller
public class PassportController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PassportRepository passportRepository;

    @GetMapping("/information/passport")
    public String getUserIdAndPassport(Authentication authentication, Map<String, Object> model) {
        User user = userRepository.findByUsername(authentication.getName());
        Passport passport = passportRepository.findById(user.getId()).orElse(new Passport());
        model.put("user", user);
        model.put("passport", passport);
        return "information/passport";
    }

    @PostMapping("/information/passport/save")
    public String savePassport(Passport passport) {
        passportRepository.save(passport);
        return "redirect:/";
    }
}

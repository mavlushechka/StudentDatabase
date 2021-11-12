package com.mavlushechka.studentdatabase.controller;

import com.mavlushechka.studentdatabase.domain.User;
import com.mavlushechka.studentdatabase.repository.UserRepository;
import com.mavlushechka.studentdatabase.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;
import java.util.Objects;

@Controller
public class PasswordController {
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/authentication/update-password")
    public String updatePassword(Authentication authentication, String password, String newPassword, String newPassword2, Map<String, Object> model) {
        User user = userRepository.findByUsername(authentication.getName());
        boolean incorrectPassword = !bCryptPasswordEncoder.matches(password, user.getPassword());
        boolean incorrectNewPassword = !Objects.equals(newPassword, newPassword2);

        model.put("incorrectPassword", incorrectPassword);
        model.put("incorrectNewPassword", incorrectNewPassword);

        if (!incorrectPassword && !incorrectNewPassword) {
            user.setPassword(newPassword);
            userDetailsService.saveUser(user);
            return "redirect:/";
        } else {
            return "/authentication/change-password";
        }
    }
}

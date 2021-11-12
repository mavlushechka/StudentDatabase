package com.mavlushechka.studentdatabase.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class HomeController {
    @GetMapping("/")
    public String getLoginButtonText(Authentication authentication, Map<String, Object> model) {
        boolean authorized = authentication != null;
        String loginButtonText = (authorized) ? authentication.getName() : "Kirish";
        model.put("authorized", authorized);
        model.put("loginButtonText", loginButtonText);
        return "index";
    }
}

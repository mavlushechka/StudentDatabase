package com.mavlushechka.studentdatabase.controller;

import com.mavlushechka.studentdatabase.domain.Job;
import com.mavlushechka.studentdatabase.domain.User;
import com.mavlushechka.studentdatabase.repository.JobRepository;
import com.mavlushechka.studentdatabase.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;
import java.util.Optional;

@Controller
public class JobController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JobRepository jobRepository;

    @GetMapping("/information/job")
    public String getUserAndJob(Authentication authentication, Map<String, Object> model) {
        User user = userRepository.findByUsername(authentication.getName());
        Job job = jobRepository.findById(user.getId()).orElse(new Job());
        model.put("user", user);
        model.put("job", job);
        return "information/job";
    }

    @PostMapping("/information/job/save")
    public String saveJob(Job job) {
        jobRepository.save(job);
        return "redirect:/";
    }
}

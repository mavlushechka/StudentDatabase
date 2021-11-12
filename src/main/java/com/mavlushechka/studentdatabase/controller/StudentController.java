package com.mavlushechka.studentdatabase.controller;

import com.mavlushechka.studentdatabase.domain.Student;
import com.mavlushechka.studentdatabase.domain.User;
import com.mavlushechka.studentdatabase.repository.StudentRepository;
import com.mavlushechka.studentdatabase.repository.UserRepository;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Controller
public class StudentController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/information/student")
    public String getUserAndStudent(Authentication authentication, Map<String, Object> model) {
        User user = userRepository.findByUsername(authentication.getName());
        Student student = studentRepository.findById(user.getId()).orElse(new Student());
        model.put("user", user);
        model.put("student", student);
        return "information/student";
    }

    @PostMapping("/information/student/save")
    public String saveStudent(String id, String diploma, long telephoneNumber, String religion, String car, String house, MultipartFile photo) throws IOException {
        Student student = new Student(id, diploma, telephoneNumber, religion, car, house, new Binary(BsonBinarySubType.BINARY, photo.getBytes()));
        studentRepository.save(student);
        return "redirect:/";
    }
}
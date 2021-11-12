package com.mavlushechka.studentdatabase.controller;

import com.mavlushechka.studentdatabase.domain.*;
import com.mavlushechka.studentdatabase.repository.*;
import com.mavlushechka.studentdatabase.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class AdminPanelController {
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private EducationalSystemRepository educationalSystemRepository;
    @Autowired
    private PassportRepository passportRepository;
    @Autowired
    private GraduatedInstitutionRepository graduatedInstitutionRepository;
    @Autowired
    private FamilyRepository familyRepository;
    @Autowired
    private HealthRepository healthRepository;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private MilitaryServiceRepository militaryServiceRepository;

    @GetMapping("/admin-panel/create-users")
    public String createUsers(@RequestParam String group, @RequestParam int number, Map<String, Object> model) {
        for (int i = 1; i <= number; i ++) {
            Role userRole = roleRepository.findByRole("USER");
            User user = new User(i + "student" + group, i + "student" + group, true, new HashSet<>(List.of(userRole)));
            User userFromDb = userRepository.findByUsername(user.getUsername());

            if (userFromDb != null) {
                model.put("message", "User exists!");
                return "/admin-panel/add-users";
            }

            userDetailsService.saveUser(user);
        }
        return "redirect:/admin-panel/all-users";
    }

    @GetMapping("/admin-panel/all-users")
    public String getUsers(Map<String, Object> model) {
        Iterable<User> users = userRepository.findAll();
        model.put("users", users);
        return "admin-panel/all-users";
    }

    @GetMapping("/admin-panel/information/all")
    public String getAll(String id, String fullName, String group, Map<String, Object> model) {
        Iterable<User> users = null;
        Iterable<Student> students = null;
        Iterable<Passport> passports = null;
        Iterable<EducationalSystem> educationalSystems = null;
        Iterable<GraduatedInstitution> graduatedInstitutions = null;
        Iterable<Health> healths = null;
        Iterable<Family> families = null;
        Iterable<Job> jobs = null;
        Iterable<MilitaryService> militaryServices = null;

        List<User> userList = new ArrayList<>();
        List<Student> studentList = new ArrayList<>();
        List<Passport> passportList = new ArrayList<>();
        List<EducationalSystem> educationalSystemList = new ArrayList<>();
        List<GraduatedInstitution> graduatedInstitutionList = new ArrayList<>();
        List<Health> healthList = new ArrayList<>();
        List<Family> familyList = new ArrayList<>();
        List<Job> jobList = new ArrayList<>();
        List<MilitaryService> militaryServiceList = new ArrayList<>();

        if (id != null) {
            userList.add(userRepository.findById(id).orElse(new User()));
            Student student = studentRepository.findById(id).orElse(new Student());
            if (student.getPhoto() != null) {
                student.setEncodedPhoto(Base64.getEncoder().encodeToString(student.getPhoto().getData()));
            }
            studentList.add(student);
            passportList.add(passportRepository.findById(id).orElse(new Passport()));
            educationalSystemList.add(educationalSystemRepository.findById(id).orElse(new EducationalSystem()));
            graduatedInstitutionList.add(graduatedInstitutionRepository.findById(id).orElse(new GraduatedInstitution()));
            healthList.add(healthRepository.findById(id).orElse(new Health()));
            familyList.add(familyRepository.findById(id).orElse(new Family()));
            jobList.add(jobRepository.findById(id).orElse(new Job()));
            militaryServiceList.add(militaryServiceRepository.findById(id).orElse(new MilitaryService()));
        } else if (fullName != null || group != null) {
            ArrayList<String> ids = new ArrayList<>();
            if (fullName != null) {
                Iterable<Passport> passportIterable;
                try {
                    passportIterable = passportRepository.findByFirstNameAndLastName(fullName.split(" ")[0], fullName.split(" ")[1]);
                } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
                    passportIterable = passportRepository.findByFirstName(fullName.split(" ")[0]);
                }
                for (Passport passport : passportIterable) {
                    ids.add(passport.getId());
                }
            } else {
                Iterable<EducationalSystem> educationalSystemIterable;
                educationalSystemIterable = educationalSystemRepository.findByGroup(group);
                for (EducationalSystem educationalSystem : educationalSystemIterable) {
                    ids.add(educationalSystem.getId());
                }
            }

            for (String eachId : ids) {
                userList.add(userRepository.findById(eachId).orElse(new User()));
                Student student = studentRepository.findById(eachId).orElse(new Student());
                if (student.getPhoto() != null) {
                    student.setEncodedPhoto(Base64.getEncoder().encodeToString(student.getPhoto().getData()));
                }
                studentList.add(student);
                passportList.add(passportRepository.findById(eachId).orElse(new Passport()));
                educationalSystemList.add(educationalSystemRepository.findById(eachId).orElse(new EducationalSystem()));
                graduatedInstitutionList.add(graduatedInstitutionRepository.findById(eachId).orElse(new GraduatedInstitution()));
                healthList.add(healthRepository.findById(eachId).orElse(new Health()));
                familyList.add(familyRepository.findById(eachId).orElse(new Family()));
                jobList.add(jobRepository.findById(eachId).orElse(new Job()));
                militaryServiceList.add(militaryServiceRepository.findById(eachId).orElse(new MilitaryService()));
            }
        } else {
            users = userRepository.findAll();
            students = studentRepository.findAll();
            passports = passportRepository.findAll();
            educationalSystems = educationalSystemRepository.findAll();
            graduatedInstitutions = graduatedInstitutionRepository.findAll();
            healths = healthRepository.findAll();
            families = familyRepository.findAll();
            jobs = jobRepository.findAll();
            militaryServices = militaryServiceRepository.findAll();
        }
        if (id != null || fullName != null || group != null) {
            users = userList;
            students = studentList;
            passports = passportList;
            educationalSystems = educationalSystemList;
            graduatedInstitutions = graduatedInstitutionList;
            healths = healthList;
            families = familyList;
            jobs = jobList;
            militaryServices = militaryServiceList;
        }

        model.put("users", users);
        model.put("students", students);
        model.put("passports", passports);
        model.put("educationalSystems", educationalSystems);
        model.put("graduatedInstitutions", graduatedInstitutions);
        model.put("healths", healths);
        model.put("families", families);
        model.put("jobs", jobs);
        model.put("militaryServices", militaryServices);
        return "admin-panel/information/all";
    }

    @GetMapping("/admin-panel/information/students")
    public String getStudents(Map<String, Object> model) {
        Iterable<Student> students = studentRepository.findAll();
        for (Student student : students) {
            student.setEncodedPhoto(Base64.getEncoder().encodeToString(student.getPhoto().getData()));
        }
        model.put("students", students);
        return "admin-panel/information/students";
    }

    @GetMapping("/admin-panel/information/educational-systems")
    public String getEducationalSystems(Map<String, Object> model) {
        Iterable<EducationalSystem> educationalSystems = educationalSystemRepository.findAll();
        model.put("educationalSystems", educationalSystems);
        return "admin-panel/information/educational-systems";
    }

    @GetMapping("/admin-panel/information/passports")
    public String getPassports(Map<String, Object> model) {
        Iterable<Passport> passports = passportRepository.findAll();
        model.put("passports", passports);
        return "admin-panel/information/passports";
    }

    @GetMapping("/admin-panel/information/graduated-institutions")
    public String getGraduatedInstitutions(Map<String, Object> model) {
        Iterable<GraduatedInstitution> graduatedInstitutions = graduatedInstitutionRepository.findAll();
        model.put("graduatedInstitutions", graduatedInstitutions);
        return "admin-panel/information/graduated-institutions";
    }

    @GetMapping("/admin-panel/information/families")
    public String getFamilies(Map<String, Object> model) {
        Iterable<Family> families = familyRepository.findAll();
        model.put("families", families);
        return "admin-panel/information/families";
    }

    @GetMapping("/admin-panel/information/health")
    public String getHealth(Map<String, Object> model) {
        Iterable<Health> health = healthRepository.findAll();
        model.put("health", health);
        return "admin-panel/information/health";
    }

    @GetMapping("/admin-panel/information/jobs")
    public String getJobs(Map<String, Object> model) {
        Iterable<Job> jobs = jobRepository.findAll();
        model.put("jobs", jobs);
        return "admin-panel/information/jobs";
    }

    @GetMapping("/admin-panel/information/military-services")
    public String getMilitaryServices(Map<String, Object> model) {
        Iterable<MilitaryService> militaryServices = militaryServiceRepository.findAll();
        model.put("militaryServices", militaryServices);
        return "admin-panel/information/military-services";
    }
}

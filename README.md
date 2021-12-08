# StudentDatabase

A website for comfortable collecting data about students. 

## Spring

Project is based on Spring framework.

### Aplication

On start application checks if any roles in database, if there is not any, then creates `ADMIN` and `USER` roles.

```java
@Autowired
private RoleRepository roleRepository;

public static void main(String[] args) {
	SpringApplication.run(Application.class, args);
}

@Bean
CommandLineRunner init() {
	return args -> {
		saveRole("ADMIN");
		saveRole("USER");
	};
}

private void saveRole(String role) {
	if (roleRepository.findByRole(role) == null) {
		roleRepository.save(new Role(role));
	}
}
```

### CustomAuthenticationSuccessHandler

If visitor is an user he will be redirected to home page, otherwise if visitor is an admin he will be redirected to admin panel.

```java
@Override
public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
	response.setStatus(HttpServletResponse.SC_OK);

	for (GrantedAuthority auth : authentication.getAuthorities()) {
		switch (auth.getAuthority()) {
			case "USER" -> response.sendRedirect("/");
			case "ADMIN" -> response.sendRedirect("/admin-panel/information/students");
		}
	}
}
```

### WebSecurityConfig

Spring Security secures your password by `BCryptPasswordEncoder` method.

```java
@Autowired
private BCryptPasswordEncoder bCryptPasswordEncoder;
@Autowired
CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

@Bean
public UserDetailsService mongoUserDetails() {
	return new CustomUserDetailsService();
}

@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	UserDetailsService userDetailsService = mongoUserDetails();
	auth
			.userDetailsService(userDetailsService)
			.passwordEncoder(bCryptPasswordEncoder);
}
```

### AdminPanelController

Application creates student accounts by given arguments.\
Example: 310-20 (`group`) and 10 (`number`). It will create 10 accounts with default login and password for 310-20 group students.

```java
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
```

## Gradle

Project uses Gradle as build automation tool.

Dependencies section in `build.gradle` file:
```gradle
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-data-mongodb:2.5.6'
    implementation 'org.springframework.boot:spring-boot-starter-security:2.5.6'
    implementation 'org.springframework.boot:spring-boot-starter-thymeleaf:2.5.6'
    implementation 'org.springframework.boot:spring-boot-starter-web:2.5.6'
    implementation 'org.springframework.boot:spring-boot-starter-tomcat:2.5.6'
    testImplementation('org.springframework.boot:spring-boot-starter-test:2.5.6') {
        exclude group: 'org.junit.vintage', module: 'junit-vintage-engine'
    }
    testImplementation 'org.springframework.security:spring-security-test:5.5.2'
}
```

## Screenshots

![Home page](https://user-images.githubusercontent.com/82092080/145265119-7c8466cb-5116-4b14-b591-12abc5e94529.jpg)
![Health form](https://user-images.githubusercontent.com/82092080/145266105-917316f8-47f1-4361-a90b-744f16d991e1.jpg)

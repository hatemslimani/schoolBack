package com.pfa.pfa;

import com.pfa.pfa.Model.User;
import com.pfa.pfa.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.util.List;

@SpringBootApplication
public class PfaApplication implements CommandLineRunner {
	@Autowired
	private UserService userService;
	public static void main(String[] args) {
		SpringApplication.run(PfaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		addAdmin();
	}
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(-1);
		return multipartResolver;
	}

	public void addAdmin()
	{
		List<User> users=userService.getUsers();
		if (users.isEmpty()) {
			User u = new User();
			u.setNom("admin");
			u.setPrenom("admin");
			u.setPassword("123456");
			u.setRole("ADMIN");
			u.setFirstLogin(true);
			u.setUserName("admin@gmail.com");
			userService.CreateUser(u);
		}
	}
}

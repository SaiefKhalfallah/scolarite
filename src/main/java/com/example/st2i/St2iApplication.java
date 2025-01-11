package com.example.st2i;



import com.example.st2i.entity.Personne;
import com.example.st2i.entity.RegisterRequest;
import com.example.st2i.entity.Role;
import com.example.st2i.repository.PersonneRepository;
import com.example.st2i.service.AuthenticationService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.Optional;

@SpringBootApplication
@OpenAPIDefinition
@ComponentScan(basePackages = "com.example.st2i")
public class St2iApplication {

	public static void main(String[] args) {
		SpringApplication.run(St2iApplication.class, args);
	}

	@Bean
	CommandLineRunner init(AuthenticationService authenticationService, PersonneRepository userRepository) {
		return args -> {
			String email = "admin@gmail.com";
			Optional<Personne> existingPersonne = userRepository.findByEmail(email);
			if (existingPersonne.isEmpty()) {
				RegisterRequest request = new RegisterRequest();
				request.setFirstname("admin");
				request.setLastname("admin");
				request.setEmail(email);
				request.setPhoneNumber(46999998);
				request.setPassword("admin");
				request.setRoles(Role.ADMINISTRATEUR);

				authenticationService.register(request);
			} else {
				System.out.println("L'utilisateur avec l'email " + email + " existe déjà.");
			}
		};
	}

}


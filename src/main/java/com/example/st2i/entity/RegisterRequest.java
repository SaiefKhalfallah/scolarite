package com.example.st2i.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private int phoneNumber;
	private Date Datenaissance;
	private String Sexe;
	private Role roles;
	@Lob
	private byte[] image;
	private String parentEmail;
	private String imageContentType;
}
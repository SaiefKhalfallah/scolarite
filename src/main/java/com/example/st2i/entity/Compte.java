package com.example.st2i.entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table (name="Comptes")
public class Compte  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_compte")
	private Long id_compte;
	@Column(name = "Email")
	private String email;
	@Column(name = "Motdepasse")
	private String Motdepasse;
	
	}

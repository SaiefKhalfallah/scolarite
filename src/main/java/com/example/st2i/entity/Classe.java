package com.example.st2i.entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table (name="Classes")
public class Classe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Column(name = "Id_classe")
	private Long Id_classe;
	@Column(unique = true)
	private String nom;

	@ManyToMany
	private List<Matiere> matieres;
}
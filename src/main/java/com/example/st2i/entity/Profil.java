package com.example.st2i.entity;



import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table (name = "Profils")
public class Profil {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Column(name = "id")
	private Long id_profil;



	@ManyToMany
	@JoinTable(
			name = "profil_fonctionnalite",
			joinColumns = @JoinColumn(name = "id_profil"),
			inverseJoinColumns = @JoinColumn(name = "id_fonctionnalite")
	)
	private Set<Fonctionnalite> fonctionnalites;


}

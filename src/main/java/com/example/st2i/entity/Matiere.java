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
@Table(name = "Matieres")
public class Matiere {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Column(name = "Id_mat")
	private Long id;
	//@Column(name = "Nom")
	private String nom;

}
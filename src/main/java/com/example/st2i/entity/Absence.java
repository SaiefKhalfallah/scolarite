package com.example.st2i.entity;



import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name="Absences")
public class Absence {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Column(name = "Id_absence")
	private Long Id_absence;
	//@Column(name = "Id_eleve")
	private Long Id_eleve;
	//@Column(name = "Id_enseignant")
	private Long Id_enseignant;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date Date_debut;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date Date_fin;
	//@Column(name = "Type")
	private String Type ;
	//@Column(name = "Nbre_absence")
	private int Nbre_absence ;
	//@Column(name = "Justificatif")
	private String Justificatif;

}
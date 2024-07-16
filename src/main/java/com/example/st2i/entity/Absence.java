package com.example.st2i.entity;



import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
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
	private Long id_absence;
	//@Column(name = "Id_eleve")
	private Long id_eleve;
	//@Column(name = "Id_enseignant")
	private Long id_enseignant;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date date;

	//@Column(name = "Nbre_absence")
	private  String horaire ;
	//@Column(name = "Justificatif")
	private String justificatif;
	private String matiere;
	private String file;
	@Lob
	@Column(name = "fileupload", nullable = true)
	private byte[] fileupload;

}
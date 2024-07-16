package com.example.st2i.entity;



import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table (name="Sanctions")
public class Sanction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Column(name = "Id_sanction")
	private Long id_sanction;
	//@Column(name = "Id_eleve")
	private Long id_eleve;
	//@Column(name = "Id_enseinant")
	private Long id_enseignant;
	//@Column(name = "Type")
	private String type;
	//@Column(name = "Date")
	@JsonFormat(pattern="dd/MM/yyyy hh:mm")
	private LocalDateTime date;
	//@Column(name = "Motif")
	private String motif ;
	//@Column(name = "Description")
	private String description;

	private int confirmation;


	

}
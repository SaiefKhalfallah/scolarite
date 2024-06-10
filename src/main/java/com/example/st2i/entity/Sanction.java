package com.example.st2i.entity;



import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table (name="Sanctions")
public class Sanction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Column(name = "Id_sanction")
	private Long Id_sanction;
	//@Column(name = "Id_eleve")
	private Long Id_eleve;
	//@Column(name = "Id_enseinant")
	private Long Id_enseignant;
	//@Column(name = "Type")
	private String Type;
	//@Column(name = "Date")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date Date;
	//@Column(name = "Motif")
	private String Motif ;
	//@Column(name = "Description")
	private String Description;
	

}
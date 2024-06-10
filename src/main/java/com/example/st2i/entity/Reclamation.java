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
@Table(name="Reclamations")
public class Reclamation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Column(name = "Id_Reclamation")
	private Long Id_Reclamation;
	//@Column(name = "Titre")
	private String Titre;
	//@Column(name = "Date")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date Date;
	//@Column(name = "Description")
	private String Description;
	//@Column(name = "Heure")
	private String Heure ;
	//@Column(name = "Image")
	private String Image ;
	//@Column(name = "Type")
	private String Type ;
		

}
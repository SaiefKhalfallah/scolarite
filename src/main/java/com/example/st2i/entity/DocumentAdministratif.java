package com.example.st2i.entity;



import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "DocumentsAdministratifs")
public class DocumentAdministratif {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Column(name = "Id_document")
	private Long id_document;
	//@Column(name = "Titre")
	private String titre;
	//@Column(name = "Date_reception")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date Date_reception;

	//@Column(name = "Type")
	private String Type ;
	private Long id_eleve;
	@Lob
	@Column(name = "fileupload", nullable = true)
	private byte[] fileupload;

}
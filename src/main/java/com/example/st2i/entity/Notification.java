package com.example.st2i.entity;



import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "Notifications")
public class Notification {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Column(name = "Id_notification")
	private Long Id_notification;
	@ManyToOne
	@JoinColumn(name = "id_parent")
	private Personne parent;
	//@Column(name = "Contenu")
	private String Contenu;
	//@Column(name = "Date_envoi")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date Date_envoi;
	
	

}
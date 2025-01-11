package com.example.st2i.entity;



import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name="Reclamations")
public class Reclamation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Column(name = "Id_Reclamation")
	private Long idReclamation;
	//@Column(name = "Titre")
	private String Titre;
	//@Column(name = "Date")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date Date;
	//@Column(name = "Description")
	private String Description;
	//@Column(name = "Heure")
	private String Heure ;
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] image;
	//@Column(name = "Type")
	private String Type ;

	@ManyToOne
	@JoinColumn(name = "personne_id")
	private Personne personne;

	@OneToMany(mappedBy = "reclamation", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Commentaire> commentaires;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "Reclamation_Likes",
			joinColumns = @JoinColumn(name = "reclamation_id"),
			inverseJoinColumns = @JoinColumn(name = "personne_id")
	)
	private Set<Personne> likes = new HashSet<>();
}
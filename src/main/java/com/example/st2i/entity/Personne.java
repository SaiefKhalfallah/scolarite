package com.example.st2i.entity;



import java.util.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Personnes")
public class Personne implements UserDetails{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id_Personne")
	private Long Id_personne;
	@Column(name = "Username")
	private String username;
	@Column(name = "Nom")
	private String Nom;
	@Column(name = "Prenom")
	private String Prenom;
	@Column(name = "Datenaissance")
	private Date Datenaissance ;
	@Column(name = "sexe")
	private String Sexe ;
	@Column(name = "Adresse")
	private String Adresse;
	@Column(name="Téléphone")
	private int Telephone ;

	@Column(name = "Email")
	private String email;

	private Boolean actif;

	@Column(name = "Password")
	private String password;

	private Role roles;
	private String parentEmail;
	@Lob
	@Column(name = "image", nullable = true)
	private byte[] image;

	@Column(name = "image_content_type", nullable = true)
	private String imageContentType;

	private String nomclasse;
	@JsonIgnore
	@OneToMany(mappedBy = "personne", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Reclamation> reclamations;
	@JsonIgnore
	@OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Commentaire> commentaires;
	/*@JsonIgnore
	@ManyToMany(mappedBy = "likes")
	private Set<Reclamation> likedReclamations = new HashSet<>();*/
	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@JsonIgnore
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}




}
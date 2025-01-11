package com.example.st2i.DTOs;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import java.util.Date;

@Data
public class PersonneDTO {
    @JsonView(View.base.class)
    private Long Id_personne;
    @JsonView(View.base.class)
    private String username;
    @JsonView(View.base.class)
    private String firstname;
    @JsonView(View.base.class)
    private String lastname;
    @JsonView(View.base.class)
    private Date Datenaissance;
    @JsonView(View.base.class)
    private String Sexe;
    @JsonView(View.base.class)
    private String Adresse;
    @JsonView(View.base.class)
    private int Telephone;
    @JsonView(View.base.class)
    private String email;
    @JsonView(View.base.class)
    private Boolean actif;
    @JsonView(View.base.class)
    private String nomclasse;

    private String image;
}

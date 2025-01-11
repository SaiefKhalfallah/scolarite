package com.example.st2i.DTOs;

import com.example.st2i.entity.Personne;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class ReclamationDTO {
    @JsonView(View.base.class)
    private Long idReclamation;
    private String Titre;
    private Date date;
    private String Description;
    private String Heure;
    private String Image;
    private String Type;
    @JsonView(View.base.class)
    private PersonneDTO personne;
   private List<CommentaireDTO> commentaires;
    @JsonView(View.base.class)
    private Set<PersonneDTO> likes= new HashSet<>();
}

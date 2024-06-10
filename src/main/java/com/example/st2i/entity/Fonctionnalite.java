package com.example.st2i.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "Fonctionnalites")
public class Fonctionnalite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "Id_fonc")
    private Long Id_fonct;
    //@Column(name = "Libellé")
    private String Libellé;
    //@Column(name = "Path")
    private String Path;
    //@Column(name = "IsActive")
    private boolean IsActive ;

    @ManyToMany(mappedBy = "fonctionnalites")
    private Set<Profil> profils;
}

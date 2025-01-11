package com.example.st2i.conversion;

import com.example.st2i.DTOs.CommentaireDTO;
import com.example.st2i.DTOs.PersonneDTO;
import com.example.st2i.DTOs.ReclamationDTO;
import com.example.st2i.DTOs.View;
import com.example.st2i.entity.Commentaire;
import com.example.st2i.entity.Personne;
import com.example.st2i.entity.Reclamation;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.Hibernate;

import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class EntityToDTOConverter {
    public static  PersonneDTO convertToPersonneDTO(Personne personne) {
        PersonneDTO dto = new PersonneDTO();
        dto.setId_personne(personne.getId_personne());
        dto.setUsername(personne.getUsername());
        dto.setFirstname(personne.getNom());
        dto.setLastname(personne.getPrenom());
        dto.setDatenaissance(personne.getDatenaissance());
        dto.setSexe(personne.getSexe());
        dto.setAdresse(personne.getAdresse());
        dto.setTelephone(personne.getTelephone());
        dto.setEmail(personne.getEmail());
        dto.setActif(personne.getActif());
        dto.setNomclasse(personne.getNomclasse());
        if (personne.getImage() != null) {
            String base64Image = Base64.getEncoder().encodeToString(personne.getImage());
            dto.setImage(base64Image);
        }
        return dto;
    }

    public static  ReclamationDTO convertToReclamationDTO(Reclamation reclamation) {
        ReclamationDTO dto = new ReclamationDTO();
        dto.setIdReclamation(reclamation.getIdReclamation());
        dto.setTitre(reclamation.getTitre());
        dto.setDate(reclamation.getDate());
        dto.setDescription(reclamation.getDescription());
        dto.setHeure(reclamation.getHeure());
        if (reclamation.getImage() != null) {
            String base64Image = Base64.getEncoder().encodeToString(reclamation.getImage());
            dto.setImage(base64Image);
        }
        dto.setType(reclamation.getType());
        dto.setPersonne(convertToPersonneDTO(reclamation.getPersonne()));
        if (reclamation.getCommentaires() != null && !reclamation.getCommentaires().isEmpty()) {
            List<CommentaireDTO> commentaireDTOs = convertToCommentaireDTOs(reclamation.getCommentaires());
            dto.setCommentaires(commentaireDTOs);
        }

        if (reclamation.getLikes() == null || reclamation.getLikes().isEmpty()) {
            dto.setLikes(Collections.emptySet());
            System.out.println("No likes found for reclamation ID: " + reclamation.getIdReclamation());
        } else {
            try {
                Set<PersonneDTO> likesDTO = reclamation.getLikes().stream()
                        .map(EntityToDTOConverter::convertToPersonneDTO)
                        .collect(Collectors.toSet());
                dto.setLikes(likesDTO);
            } catch (Exception e) {
                // Loggez ou gérez l'exception si la conversion échoue
                System.err.println("Error while converting likes for reclamation ID: " + reclamation.getIdReclamation());
                e.printStackTrace();
                dto.setLikes(Collections.emptySet());  // Si une exception se produit, on évite l'ajout de likes
            }
        }

        return dto;
    }

    public static CommentaireDTO convertToCommentaireDTO(Commentaire commentaire) {
        CommentaireDTO dto = new CommentaireDTO();
        dto.setId(commentaire.getId());
        dto.setContent(commentaire.getContent());
        dto.setCreatedAt(commentaire.getCreatedAt());
        dto.setPersonne(convertToPersonneDTO(commentaire.getAuthor()));
        return dto;
    }

    public  static  List<ReclamationDTO> convertToReclamationDTOs(List<Reclamation> reclamations) {
        return reclamations.stream().map(EntityToDTOConverter::convertToReclamationDTO).collect(Collectors.toList());
    }

    public static List<CommentaireDTO> convertToCommentaireDTOs(List<Commentaire> commentaires) {
        return commentaires.stream().map(EntityToDTOConverter::convertToCommentaireDTO).collect(Collectors.toList());
    }
}

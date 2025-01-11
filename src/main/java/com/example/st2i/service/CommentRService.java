package com.example.st2i.service;

import com.example.st2i.entity.Commentaire;
import com.example.st2i.entity.Personne;
import com.example.st2i.entity.Reclamation;
import com.example.st2i.repository.CommentRRepository;
import com.example.st2i.repository.PersonneRepository;
import com.example.st2i.repository.ReclamationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentRService {
    private final CommentRRepository commentaireRepository;
    private final ReclamationRepository reclamationRepository;
    private final PersonneRepository personneRepository;

    public CommentRService(CommentRRepository commentaireRepository, ReclamationRepository reclamationRepository, PersonneRepository personneRepository) {
        this.commentaireRepository = commentaireRepository;
        this.reclamationRepository = reclamationRepository;
        this.personneRepository = personneRepository;
    }

    public Commentaire createCommentaire(String content, Long reclamationId, Long authorId) {
        Reclamation reclamation = reclamationRepository.findById(reclamationId)
                .orElseThrow(() -> new RuntimeException("Réclamation introuvable"));
        Personne author = personneRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Auteur introuvable"));

        Commentaire commentaire = new Commentaire();
        commentaire.setContent(content);
        commentaire.setReclamation(reclamation);
        commentaire.setAuthor(author);
        commentaire.setCreatedAt(LocalDateTime.now());

        return commentaireRepository.save(commentaire);
    }


    public Commentaire updateCommentaire(Long commentaireId, String newContent) {
        Commentaire commentaire = commentaireRepository.findById(commentaireId)
                .orElseThrow(() -> new RuntimeException("Commentaire introuvable"));
        commentaire.setContent(newContent);
        return commentaireRepository.save(commentaire);
    }


    public void deleteCommentaire(Long commentaireId) {
        Commentaire commentaire = commentaireRepository.findById(commentaireId)
                .orElseThrow(() -> new RuntimeException("Commentaire introuvable"));
        commentaireRepository.delete(commentaire);
    }


    public List<Commentaire> getCommentairesByReclamation(Long reclamationId) {
        return commentaireRepository.findByReclamationId(reclamationId);
    }
}

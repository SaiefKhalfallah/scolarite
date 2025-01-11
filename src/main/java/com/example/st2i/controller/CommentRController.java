package com.example.st2i.controller;

import com.example.st2i.DTOs.CommentaireDTO;
import com.example.st2i.conversion.EntityToDTOConverter;
import com.example.st2i.entity.Commentaire;
import com.example.st2i.service.CommentRService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/commentaires")
public class CommentRController {
    private final CommentRService commentaireService;

    public CommentRController(CommentRService commentaireService) {
        this.commentaireService = commentaireService;
    }

    @PostMapping("/{reclamationId}/{authorId}")
    public ResponseEntity<CommentaireDTO> createCommentaire(
            @PathVariable Long reclamationId,
            @PathVariable Long authorId,
            @RequestBody String content) {
        Commentaire commentaire = commentaireService.createCommentaire(content, reclamationId, authorId);
        CommentaireDTO commentaireDTO = EntityToDTOConverter.convertToCommentaireDTO(commentaire);
        return ResponseEntity.ok(commentaireDTO);
    }

    @PutMapping("/{commentaireId}")
    public ResponseEntity<CommentaireDTO> updateCommentaire(
            @PathVariable Long commentaireId,
            @RequestBody String newContent) {
        Commentaire updatedCommentaire = commentaireService.updateCommentaire(commentaireId, newContent);
        CommentaireDTO commentaireDTO = EntityToDTOConverter.convertToCommentaireDTO(updatedCommentaire);
        return ResponseEntity.ok(commentaireDTO);
    }


    @DeleteMapping("/{commentaireId}")
    public ResponseEntity<Void> deleteCommentaire(@PathVariable Long commentaireId) {
        commentaireService.deleteCommentaire(commentaireId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/reclamation/{reclamationId}")
    public ResponseEntity<List<CommentaireDTO>> getCommentairesByReclamation(@PathVariable Long reclamationId) {
        List<Commentaire> commentaires = commentaireService.getCommentairesByReclamation(reclamationId);
        List<CommentaireDTO> commentaireDTOs = EntityToDTOConverter.convertToCommentaireDTOs(commentaires);
        return ResponseEntity.ok(commentaireDTOs);
    }
}

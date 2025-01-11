package com.example.st2i.repository;

import com.example.st2i.entity.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CommentRRepository extends JpaRepository<Commentaire, Long> {
    @Query("SELECT c FROM Commentaire c WHERE c.reclamation.idReclamation = :idReclamation")
    List<Commentaire> findByReclamationId(@Param("idReclamation") Long idReclamation);
}
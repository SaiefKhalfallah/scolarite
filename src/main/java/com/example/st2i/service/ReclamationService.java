package com.example.st2i.service;


import com.example.st2i.entity.Personne;
import com.example.st2i.repository.PersonneRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.st2i.entity.Reclamation;
import com.example.st2i.repository.ReclamationRepository;

import java.util.List;
import java.util.Optional;


@Service
public class ReclamationService{
    @Autowired
    private  ReclamationRepository reclamationRepository;
    @Autowired
    private  PersonneRepository personneRepository;

    public Reclamation createReclamation(Reclamation reclamation, Long personId) {
        Personne personne = personneRepository.findById(personId)
                .orElseThrow(() -> new RuntimeException("Personne introuvable"));
        reclamation.setPersonne(personne);
        return reclamationRepository.save(reclamation);
    }
    @Transactional
    public void likeReclamation(Long reclamationId, Long personId) {
        Reclamation reclamation = reclamationRepository.findById(reclamationId)
                .orElseThrow(() -> new RuntimeException("Reclamation introuvable"));
        Personne personne = personneRepository.findById(personId)
                .orElseThrow(() -> new RuntimeException("Personne introuvable"));
        reclamation.getLikes().add(personne);
        reclamationRepository.save(reclamation);
    }

    public void unlikeReclamation(Long reclamationId, Long personId) {
        Reclamation reclamation = reclamationRepository.findById(reclamationId)
                .orElseThrow(() -> new RuntimeException("Reclamation introuvable"));
        Personne personne = personneRepository.findById(personId)
                .orElseThrow(() -> new RuntimeException("Personne introuvable"));
        reclamation.getLikes().remove(personne);
        reclamationRepository.save(reclamation);
    }
    @Transactional
    public List<Reclamation> getAllReclamations() {
        return reclamationRepository.findAll(Sort.by(Sort.Direction.DESC, "idReclamation"));
    }

}

package com.example.st2i.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.st2i.entity.Reclamation;
import com.example.st2i.repository.ReclamationRepository;

import java.util.List;
import java.util.Optional;


@Service
public class ReclamationService{
    @Autowired
    private ReclamationRepository ReclamationRepo;

    public Reclamation addReclamation(Reclamation Reclamation) {
        return ReclamationRepo.save(Reclamation);
    }


    public List<Reclamation> getAllReclamation(){
        return ReclamationRepo.findAll();
    }
    public Reclamation getReclamationById(Long id) {
        Optional<Reclamation> e= ReclamationRepo.findById(id);
        return e.isPresent() ? e.get() : null;
    }


    public void deleteById(Long id){
        ReclamationRepo.deleteById(id);
    }
}

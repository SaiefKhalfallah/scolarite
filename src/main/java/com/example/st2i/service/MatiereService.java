package com.example.st2i.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.st2i.entity.Matiere;
import com.example.st2i.repository.MatiereRepository;

import java.util.List;
import java.util.Optional;


@Service
public class MatiereService{
    @Autowired
    private MatiereRepository MatiereRepo;

    public Matiere addMatiere(Matiere Matiere) {
        return MatiereRepo.save(Matiere);
    }
    public Matiere getMatiereById(Long id) {
        Optional<Matiere> e= MatiereRepo.findById(id);
        return e.isPresent() ? e.get() : null;
    }


    public List<Matiere> getAllMatiere(){
        return MatiereRepo.findAll();
    }


    public void deleteById(Long id){
        MatiereRepo.deleteById(id);
    }
}

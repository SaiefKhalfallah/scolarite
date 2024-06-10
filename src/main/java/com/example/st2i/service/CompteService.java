package com.example.st2i.service;


import com.example.st2i.repository.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.st2i.entity.Compte;

import java.util.List;
import java.util.Optional;


@Service
public class CompteService{
    @Autowired
    private CompteRepository CompteRepo;

    public Compte addCompte(Compte Compte) {
        return CompteRepo.save(Compte);
    }


    public List<Compte> getAllCompte(){
        return CompteRepo.findAll();
    }
    public Compte getCompteById(Long id) {
        Optional<Compte> e= CompteRepo.findById(id);
        return e.isPresent() ? e.get() : null;
    }

    public void deleteById(Long id){
        CompteRepo.deleteById(id);
    }
}

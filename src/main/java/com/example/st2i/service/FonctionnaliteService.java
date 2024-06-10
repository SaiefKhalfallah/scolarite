package com.example.st2i.service;


import com.example.st2i.entity.Fonctionnalite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.st2i.repository.FonctionnaliteRepository;

import java.util.List;
import java.util.Optional;


@Service
public class FonctionnaliteService{
    @Autowired
    private FonctionnaliteRepository FonctionnaliteRepo;

    public Fonctionnalite addFonctionnalite(Fonctionnalite Fonctionnalite) {
        return FonctionnaliteRepo.save(Fonctionnalite);
    }


    public List<Fonctionnalite> getAllFonctionnalite(){
        return FonctionnaliteRepo.findAll();
    }

    public Fonctionnalite getFonctionnaliteById(Long id) {
        Optional<Fonctionnalite> e= FonctionnaliteRepo.findById(id);
        return e.isPresent() ? e.get() : null;
    }
    public void deleteById(Long id){
        FonctionnaliteRepo.deleteById(id);
    }
}

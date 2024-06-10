package com.example.st2i.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.st2i.entity.Profil;
import com.example.st2i.repository.ProfilRepository;

import java.util.List;
import java.util.Optional;


@Service
public class ProfilService{
    @Autowired
    private ProfilRepository ProfilRepo;

    public Profil addProfil(Profil Profil) {
        return ProfilRepo.save(Profil);
    }
    public Profil getProfilById(Long id) {
        Optional<Profil> e= ProfilRepo.findById(id);
        return e.isPresent() ? e.get() : null;
    }


    public List<Profil> getAllProfil(){
        return ProfilRepo.findAll();
    }


    public void deleteById(Long id){
        ProfilRepo.deleteById(id);
    }
}

package com.example.st2i.service;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.example.st2i.entity.Personne;
import com.example.st2i.repository.PersonneRepository;

import java.util.List;
import java.util.Optional;


@Service
public class PersonneService{
    @Autowired
    private PersonneRepository PersonneRepo;

    @Autowired
    public PersonneService(PersonneRepository PersonneRepo) {

        this.PersonneRepo = PersonneRepo;
    }

    public Personne addPersonne(Personne Personne) {
        return PersonneRepo.save(Personne);
    }



    public Personne getPersonneById(Long id) {
        Optional<Personne> e= PersonneRepo.findById(id);
        return e.isPresent() ? e.get() : null;
    }
    @Transactional
    public List<Personne> getAllPersonne(){
        return PersonneRepo.findAll();
    }


    public void deleteById(Long id){
        PersonneRepo.deleteById(id);
    }
}

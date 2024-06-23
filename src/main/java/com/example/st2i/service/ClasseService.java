package com.example.st2i.service;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.st2i.entity.Classe;
import com.example.st2i.repository.ClasseRepository;

import java.util.List;
import java.util.Optional;


@Service
public class ClasseService{
    @Autowired
    private ClasseRepository ClasseRepo;
    @Transactional
    public Classe addClasse(Classe Classe) {
        return ClasseRepo.save(Classe);
    }
    public Classe getClasseById(Long id) {
        Optional<Classe> e= ClasseRepo.findById(id);
        return e.isPresent() ? e.get() : null;
    }

    public List<Classe> getAllClasse(){
        return ClasseRepo.findAll();
    }


    public void deleteById(Long id){
        ClasseRepo.deleteById(id);
    }
}

package com.example.st2i.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.st2i.entity.DocumentAdministratif;
import com.example.st2i.repository.DocumentAdministratifRepository;

import java.util.List;
import java.util.Optional;


@Service
public class DocumentAdministratifService{
    @Autowired
    private DocumentAdministratifRepository DocumentAdministratifRepo;

    public DocumentAdministratif addDocumentAdministratif(DocumentAdministratif DocumentAdministratif) {
        return DocumentAdministratifRepo.save(DocumentAdministratif);
    }


    public List<DocumentAdministratif> getAllDocumentAdministratif(){
        return DocumentAdministratifRepo.findAll();
    }

    public DocumentAdministratif getDocumentAdministratifById(Long id) {
        Optional<DocumentAdministratif> e= DocumentAdministratifRepo.findById(id);
        return e.isPresent() ? e.get() : null;
    }
    public void deleteById(Long id){
        DocumentAdministratifRepo.deleteById(id);
    }
}

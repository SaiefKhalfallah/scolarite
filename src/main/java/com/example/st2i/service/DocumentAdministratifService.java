package com.example.st2i.service;


import com.example.st2i.entity.*;
import com.example.st2i.repository.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.st2i.repository.DocumentAdministratifRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class DocumentAdministratifService{
    @Autowired
    private DocumentAdministratifRepository DocumentAdministratifRepo;
    @Autowired
    private PersonneRepository personneRepository;
    public DocumentAdministratif addDocumentAdministratif(DocumentAdministratif DocumentAdministratif) {
        return DocumentAdministratifRepo.save(DocumentAdministratif);
    }

    public List<DocResponse> getAllDocumentAdministratifdto(){
        List<DocumentAdministratif> docs= DocumentAdministratifRepo.findAll();
        return docs.stream()
                .map(doc -> {
                    // Get idPersonne from Sanction
                    Long idPersonne = doc.getId_eleve();

                    // Fetch Personne from PersonneRepository
                    Personne personne = personneRepository.findById(idPersonne)
                            .orElse(null); // Handle if personne is not found


                    // Create SanctionResponse and set Personne
                    DocResponse response = new DocResponse();
                    response.setId(doc.getId_document());
                    response.setDocument(doc);
                    response.setEleve(personne); // Set the fetched Personne

                    return response;
                })
                .collect(Collectors.toList());
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

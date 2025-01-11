package com.example.st2i.service;


import com.example.st2i.entity.*;
import com.example.st2i.repository.PersonneRepository;
import jakarta.transaction.Transactional;
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
                    Long idPersonne = doc.getIdEleve();

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

    public DocumentAdministratif changeStatus(Long id,String status) {
        Optional<DocumentAdministratif> e= DocumentAdministratifRepo.findById(id);
        if (e.isPresent()) {
            DocumentAdministratif document = e.get();
            switch (status.toUpperCase()) {
                case "ACCEPTED":
                    document.setStatusDoc(StatusDoc.ACCEPTED);
                    break;
                case "REJECTED":
                    document.setStatusDoc(StatusDoc.REJECTED);
                    break;
                default:
                    throw new IllegalArgumentException("Statut inconnu: " + status);
            }
            return DocumentAdministratifRepo.save(document);
        }else {
            throw new RuntimeException("Document non trouvé avec l'ID: " + id);
        }

    }
    @Transactional
    public List<DocumentAdministratif> getDocumentsForUser(Long userId) {
        return DocumentAdministratifRepo.findByIdEleve(userId);  // Exemple de méthode pour récupérer les documents d'un utilisateur
    }

}

package com.example.st2i.service;


import com.example.st2i.entity.Personne;
import com.example.st2i.entity.SanctionResponse;
import com.example.st2i.repository.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.st2i.entity.Sanction;
import com.example.st2i.repository.SanctionRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class SanctionService{
    @Autowired
    private SanctionRepository SanctionRepo;
    @Autowired
    private PersonneRepository personneRepository;

    public List<SanctionResponse> getAllSanction(){
         List<Sanction> sanctions= SanctionRepo.findAll();
        return sanctions.stream()
                .map(sanction -> {
                    // Get idPersonne from Sanction
                    Long idPersonne = sanction.getId_eleve();
                    Long idEnseignant = sanction.getId_enseignant();

                    // Fetch Personne from PersonneRepository
                    Personne personne = personneRepository.findById(idPersonne)
                            .orElse(null); // Handle if personne is not found

                    Personne enseignant = personneRepository.findById(idEnseignant)
                            .orElse(null); // Handle if personne is not found

                    // Create SanctionResponse and set Personne
                    SanctionResponse response = new SanctionResponse();
                    response.setId(sanction.getId_sanction());
                    response.setSanction(sanction);
                    response.setEleve(personne); // Set the fetched Personne
                    response.setEnseignant(enseignant);

                    return response;
                })
                .collect(Collectors.toList());
    }


    public Sanction addSanction(Sanction Sanction) {
        return SanctionRepo.save(Sanction);
    }



    public Sanction getSanctionById(Long id) {
        Optional<Sanction> e= SanctionRepo.findById(id);
        return e.isPresent() ? e.get() : null;
    }


    public void deleteById(Long id){
        SanctionRepo.deleteById(id);
    }
}

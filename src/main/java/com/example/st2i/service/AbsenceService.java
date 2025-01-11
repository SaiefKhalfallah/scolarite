package com.example.st2i.service;


import com.example.st2i.entity.*;
import com.example.st2i.repository.PersonneRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.st2i.repository.AbsenceRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class AbsenceService{
    @Autowired
    private PersonneRepository personneRepository;
    @Autowired
    private AbsenceRepository absenceRepo;
    @Autowired
    private EmailService emailService;

    public Absence addAbsence(Absence absence) {
        try {
           Personne user = personneRepository.findById(absence.getId_eleve()).orElseThrow(
                   ()-> new EntityNotFoundException(" user not found with id :: ")
           );
           Personne ensignant = personneRepository.findById(absence.getId_enseignant()).orElseThrow(
                   ()-> new EntityNotFoundException(" user not found with id :: ")
           );
           emailService.sendEmailNotifConfirmation(user.getParentEmail(),"Absence de votre enfant", LocalDate.now() ,"Absence ", ensignant.getNom(), ensignant.getPrenom(), user.getNom(), user.getPrenom(), absence.getDate(), absence.getHoraire(), user.getNomclasse(),absence.getMatiere());
            return absenceRepo.save(absence);
        }catch (Exception ex){
            throw new EntityNotFoundException("Exception For add absence :: "+ex);
        }

    }


    public Absence getAbsenceById(Long id) {
        Optional<Absence> e= absenceRepo.findById(id);
        return e.isPresent() ? e.get() : null;
    }
    public List<AbsenceResponse> getAllAbsence(){
        List<Absence> absences= absenceRepo.findAll();
        return absences.stream()
                .map(absence -> {
                    // Get idPersonne from Sanction
                    Long idPersonne = absence.getId_eleve();
                    Long idEnseignant = absence.getId_enseignant();

                    // Fetch Personne from PersonneRepository
                    Personne personne = personneRepository.findById(idPersonne)
                            .orElse(null); // Handle if personne is not found

                    Personne enseignant = personneRepository.findById(idEnseignant)
                            .orElse(null); // Handle if personne is not found

                    // Create SanctionResponse and set Personne
                    AbsenceResponse response = new AbsenceResponse();
                    response.setId(absence.getId_absence());
                    response.setAbsence(absence);
                    response.setEleve(personne); // Set the fetched Personne
                    response.setEnseignant(enseignant);

                    return response;
                })
                .collect(Collectors.toList());
    }


    public void deleteById(Long id){
        absenceRepo.deleteById(id);
    }
}

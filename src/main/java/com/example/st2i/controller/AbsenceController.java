package com.example.st2i.controller;


import com.example.st2i.service.AbsenceService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.st2i.entity.Absence;


import java.util.List;

@RequiredArgsConstructor

@CrossOrigin("*")
@RestController
@RequestMapping("/absence")
public class AbsenceController {

    @Autowired
    private AbsenceService absenceService;


    @GetMapping("/all")
    public ResponseEntity<List<Absence>> getAllAbsences() {
        List<Absence> Absences = absenceService.getAllAbsence();
        return new ResponseEntity<>(Absences, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Absence> getAbsenceById(@PathVariable("id") Long id) {
        Absence Absence = absenceService.getAbsenceById(id);
        return new ResponseEntity<>(Absence, HttpStatus.OK);
    }

    @PostMapping("/add")

    public ResponseEntity<Absence> addAbsence(@RequestBody Absence Absence) {

        System.out.println("absence1"+Absence);

        return new ResponseEntity<>(absenceService.addAbsence(Absence),HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Absence> updateAbsence(@PathVariable("id") Long id, @RequestBody Absence Absence) {


        Absence updatedAbsence = absenceService.getAbsenceById(id);


        updatedAbsence.setId_eleve(Absence.getId_eleve());
        updatedAbsence.setId_enseignant(Absence.getId_enseignant());
        updatedAbsence.setNbre_absence(Absence.getNbre_absence());
        updatedAbsence.setType(Absence.getType());
        updatedAbsence.setDate_debut(Absence.getDate_debut());
        updatedAbsence.setDate_fin(Absence.getDate_fin());
        updatedAbsence.setJustificatif(Absence.getJustificatif());

        absenceService.addAbsence(updatedAbsence);
        return new ResponseEntity<>(updatedAbsence, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        absenceService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}

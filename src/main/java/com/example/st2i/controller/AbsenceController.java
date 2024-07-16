package com.example.st2i.controller;


import com.example.st2i.entity.*;
import com.example.st2i.repository.AbsenceRepository;
import com.example.st2i.service.AbsenceService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ansi.Ansi8BitColor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor

@CrossOrigin("*")
@RestController
@RequestMapping("/absence")
public class AbsenceController {
    @Autowired
    private AbsenceRepository absenceRepo;

    @Autowired
    private AbsenceService absenceService;


    @PatchMapping("/upload/patch/{id}")
    @Transactional
    public ResponseEntity<Absence> registerfile(@PathVariable("id") Long id,
                                                  @RequestParam("file") MultipartFile file) {
        Absence updateAbsence = absenceService.getAbsenceById(id);



        // Handle the image file
        if (!file.isEmpty()) {
            try {
                byte[] imageData = file.getBytes();
                String name= file.getName();
                // Assuming you have a setter method for image and imageContentType
                updateAbsence.setFileupload(imageData);
                updateAbsence.setFile(name);
            } catch (IOException e) {
                System.err.println("problem occured");

            }
        }

        absenceRepo.save(updateAbsence);
        return new ResponseEntity<>(updateAbsence, HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<AbsenceResponse>> getAllSanctions() {
        List<AbsenceResponse> absences = absenceService.getAllAbsence();
        return new ResponseEntity<>(absences, HttpStatus.OK);
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
        updatedAbsence.setDate(Absence.getDate());
        updatedAbsence.setJustificatif("non");
        updatedAbsence.setHoraire(Absence.getHoraire());
        absenceService.addAbsence(updatedAbsence);
        return new ResponseEntity<>(updatedAbsence, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        absenceService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/patch/{id}")
    public ResponseEntity<Absence> changerStatus(@PathVariable("id") Long id, @RequestBody Absence absence) {

        Absence updateAbsence = absenceService.getAbsenceById(id);
        updateAbsence.setJustificatif(absence.getJustificatif());
        absenceRepo.save(updateAbsence);
        return new ResponseEntity<>(updateAbsence, HttpStatus.OK);

    }
    @PatchMapping("/file/patch/{id}")
    public ResponseEntity<Absence> changerFile(@PathVariable("id") Long id, @RequestBody Absence absence) {

        Absence updateAbsence = absenceService.getAbsenceById(id);
        updateAbsence.setFile(absence.getFile());
        absenceRepo.save(updateAbsence);
        return new ResponseEntity<>(updateAbsence, HttpStatus.OK);

    }
}

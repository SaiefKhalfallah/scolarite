package com.example.st2i.controller;


import com.example.st2i.service.ReclamationService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.st2i.entity.Reclamation;


import java.util.List;

@RequiredArgsConstructor

@CrossOrigin("*")
@RestController
@RequestMapping("/reclamation")
public class ReclamationController {
    @Autowired
    private ReclamationService reclamationService;


    @GetMapping("/all")
    public ResponseEntity<List<Reclamation>> getAllReclamations() {
        List<Reclamation> Reclamations = reclamationService.getAllReclamation();
        return new ResponseEntity<>(Reclamations, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Reclamation> getReclamationById(@PathVariable("id") Long id) {
        Reclamation Reclamation = reclamationService.getReclamationById(id);
        return new ResponseEntity<>(Reclamation, HttpStatus.OK);
    }

    @PostMapping("/add")

    public ResponseEntity<Reclamation> addReclamation(@RequestBody Reclamation Reclamation) {

        System.out.println("Reclamation1"+Reclamation);

        return new ResponseEntity<>(reclamationService.addReclamation(Reclamation),HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Reclamation> updateReclamation(@PathVariable("id") Long id, @RequestBody Reclamation Reclamation) {


        Reclamation updatedReclamation = reclamationService.getReclamationById(id);

        updatedReclamation.setDate(Reclamation.getDate());
        updatedReclamation.setType(Reclamation.getType());
        updatedReclamation.setHeure(Reclamation.getHeure());
        updatedReclamation.setTitre(Reclamation.getTitre());
        updatedReclamation.setImage(Reclamation.getImage());
        updatedReclamation.setDescription(Reclamation.getDescription());



        reclamationService.addReclamation(updatedReclamation);
        return new ResponseEntity<>(updatedReclamation, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        reclamationService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}

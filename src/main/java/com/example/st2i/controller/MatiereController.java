package com.example.st2i.controller;


import com.example.st2i.service.MatiereService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.st2i.entity.Matiere;


import java.util.List;

@RequiredArgsConstructor

@CrossOrigin("*")
@RestController
@RequestMapping("/matiere")
public class MatiereController {
    @Autowired
    private MatiereService matiereService;


    @GetMapping("/all")
    public ResponseEntity<List<Matiere>> getAllMatieres() {
        List<Matiere> Matieres = matiereService.getAllMatiere();
        return new ResponseEntity<>(Matieres, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Matiere> getMatiereById(@PathVariable("id") Long id) {
        Matiere Matiere = matiereService.getMatiereById(id);
        return new ResponseEntity<>(Matiere, HttpStatus.OK);
    }

    @PostMapping("/add")

    public ResponseEntity<Matiere> addMatiere(@RequestBody Matiere Matiere) {

        System.out.println("Matiere1"+Matiere);

        return new ResponseEntity<>(matiereService.addMatiere(Matiere),HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Matiere> updateMatiere(@PathVariable("id") Long id, @RequestBody Matiere Matiere) {


        Matiere updatedMatiere = matiereService.getMatiereById(id);

        updatedMatiere.setNom(Matiere.getNom());


        matiereService.addMatiere(updatedMatiere);
        return new ResponseEntity<>(updatedMatiere, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        matiereService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}

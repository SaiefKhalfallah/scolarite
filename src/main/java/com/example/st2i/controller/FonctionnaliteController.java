package com.example.st2i.controller;


import com.example.st2i.entity.Fonctionnalite;
import com.example.st2i.service.FonctionnaliteService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;

@RequiredArgsConstructor

@CrossOrigin("*")
@RestController
@RequestMapping("/fonctionnalite")
public class FonctionnaliteController {
    @Autowired
    private FonctionnaliteService fonctionnaliteService;


    @GetMapping("/all")
    public ResponseEntity<List<Fonctionnalite>> getAllFonctionnalites() {
        List<Fonctionnalite> Fonctionnalites = fonctionnaliteService.getAllFonctionnalite();
        return new ResponseEntity<>(Fonctionnalites, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Fonctionnalite> getFonctionnaliteById(@PathVariable("id") Long id) {
        Fonctionnalite Fonctionnalite = fonctionnaliteService.getFonctionnaliteById(id);
        return new ResponseEntity<>(Fonctionnalite, HttpStatus.OK);
    }

    @PostMapping("/add")

    public ResponseEntity<Fonctionnalite> addFonctionnalite(@RequestBody Fonctionnalite Fonctionnalite) {

        System.out.println("Fonctionnalite1"+Fonctionnalite);

        return new ResponseEntity<>(fonctionnaliteService.addFonctionnalite(Fonctionnalite),HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Fonctionnalite> updateFonctionnalite(@PathVariable("id") Long id, @RequestBody Fonctionnalite Fonctionnalite) {


        Fonctionnalite updatedFonctionnalite = fonctionnaliteService.getFonctionnaliteById(id);

        updatedFonctionnalite.setPath(Fonctionnalite.getPath());
        updatedFonctionnalite.setLibellé(Fonctionnalite.getLibellé());
        updatedFonctionnalite.setProfils(Fonctionnalite.getProfils());


        fonctionnaliteService.addFonctionnalite(updatedFonctionnalite);
        return new ResponseEntity<>(updatedFonctionnalite, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        fonctionnaliteService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}

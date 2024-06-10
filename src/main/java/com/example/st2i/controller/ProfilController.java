package com.example.st2i.controller;


import com.example.st2i.service.ProfilService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.st2i.entity.Profil;


import java.util.List;

@RequiredArgsConstructor

@CrossOrigin("*")
@RestController
@RequestMapping("/profil")
public class ProfilController {
    @Autowired
    private ProfilService profilService;


    @GetMapping("/all")
    public ResponseEntity<List<Profil>> getAllProfils() {
        List<Profil> Profils = profilService.getAllProfil();
        return new ResponseEntity<>(Profils, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Profil> getProfilById(@PathVariable("id") Long id) {
        Profil Profil = profilService.getProfilById(id);
        return new ResponseEntity<>(Profil, HttpStatus.OK);
    }

    @PostMapping("/add")

    public ResponseEntity<Profil> addProfil(@RequestBody Profil Profil) {

        System.out.println("Profil1"+Profil);

        return new ResponseEntity<>(profilService.addProfil(Profil),HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Profil> updateProfil(@PathVariable("id") Long id, @RequestBody Profil Profil) {


        Profil updatedProfil = profilService.getProfilById(id);

        updatedProfil.setFonctionnalites(Profil.getFonctionnalites());
        updatedProfil.setId_profil(Profil.getId_profil());


        profilService.addProfil(updatedProfil);
        return new ResponseEntity<>(updatedProfil, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        profilService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}

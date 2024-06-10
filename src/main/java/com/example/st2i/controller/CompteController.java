package com.example.st2i.controller;


import com.example.st2i.service.CompteService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.st2i.entity.Compte;


import java.util.List;

@RequiredArgsConstructor

@CrossOrigin("*")
@RestController
@RequestMapping("/compte")
public class CompteController {
    @Autowired
    private CompteService compteService;


    @GetMapping("/all")
    public ResponseEntity<List<Compte>> getAllComptes() {
        List<Compte> Comptes = compteService.getAllCompte();
        return new ResponseEntity<>(Comptes, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Compte> getCompteById(@PathVariable("id") Long id) {
        Compte Compte = compteService.getCompteById(id);
        return new ResponseEntity<>(Compte, HttpStatus.OK);
    }

    @PostMapping("/add")

    public ResponseEntity<Compte> addCompte(@RequestBody Compte Compte) {

        System.out.println("Compte1"+Compte);

        return new ResponseEntity<>(compteService.addCompte(Compte),HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Compte> updateCompte(@PathVariable("id") Long id, @RequestBody Compte Compte) {


        Compte updatedCompte = compteService.getCompteById(id);

        updatedCompte.setEmail(Compte.getEmail());
        updatedCompte.setMotdepasse(Compte.getMotdepasse());

        compteService.addCompte(updatedCompte);
        return new ResponseEntity<>(updatedCompte, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        compteService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}

package com.example.st2i.controller;


import com.example.st2i.entity.Personne;
import com.example.st2i.entity.SanctionResponse;
import com.example.st2i.repository.SanctionRepository;
import com.example.st2i.service.SanctionService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.st2i.entity.Sanction;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor

@CrossOrigin("*")
@RestController
@RequestMapping("/sanction")
public class SanctionController {
    @Autowired
    private SanctionService sanctionService;

    @Autowired
    private SanctionRepository sanctionRepo;
    @GetMapping("/all")
    public ResponseEntity<List<SanctionResponse>> getAllSanctions() {
        List<SanctionResponse> Sanctions = sanctionService.getAllSanction();
        return new ResponseEntity<>(Sanctions, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Sanction> getSanctionById(@PathVariable("id") Long id) {
        Sanction Sanction = sanctionService.getSanctionById(id);
        return new ResponseEntity<>(Sanction, HttpStatus.OK);
    }

    @PostMapping("/add")

    public ResponseEntity<Sanction> addSanction(@RequestBody Sanction Sanction) {

        System.out.println("Sanction1"+Sanction);
        Sanction.setDate(LocalDateTime.now());

        return new ResponseEntity<>(sanctionService.addSanction(Sanction),HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Sanction> updateSanction(@PathVariable("id") Long id, @RequestBody Sanction Sanction) {


        Sanction updatedSanction = sanctionService.getSanctionById(id);

        updatedSanction.setDate(Sanction.getDate());
        updatedSanction.setType(Sanction.getType());
        updatedSanction.setMotif(Sanction.getMotif());
        updatedSanction.setDescription(Sanction.getDescription());
        updatedSanction.setId_eleve(Sanction.getId_eleve());
        updatedSanction.setId_enseignant(Sanction.getId_enseignant());


        sanctionService.addSanction(updatedSanction);
        return new ResponseEntity<>(updatedSanction, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        sanctionService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PatchMapping("/patch/{id}")
    public ResponseEntity<Sanction> changerStatus(@PathVariable("id") Long id, @RequestBody Sanction sanction) {

        Sanction updatedSanction = sanctionService.getSanctionById(id);
        updatedSanction.setConfirmation(sanction.getConfirmation());
        sanctionRepo.save(updatedSanction);
        return new ResponseEntity<>(updatedSanction, HttpStatus.OK);

    }


}

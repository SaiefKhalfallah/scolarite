package com.example.st2i.controller;

import com.example.st2i.entity.Personne;
import com.example.st2i.repository.PersonneRepository;
import com.example.st2i.service.PersonneService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("/personne")
public class PersonneController {

    @Autowired
    private PersonneService personneService;
    @Autowired
    private PersonneRepository personneRepo;

    @GetMapping("/all")
    public ResponseEntity<List<Personne>> getAllPersonnes() {
        List<Personne> personnes = personneService.getAllPersonne();
        return new ResponseEntity<>(personnes, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Personne> getPersonneById(@PathVariable("id") Long id) {

            Personne personne = personneService.getPersonneById(id);
            return new ResponseEntity<>(personne, HttpStatus.OK);
        }


    @GetMapping("/currentUserId")
    public ResponseEntity<Long> getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        Optional<Personne> personne = personneRepo.findByEmail(userEmail);
        Long userId = personne.get().getId_personne();
        return new ResponseEntity<>(userId, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Personne> addPersonne(@RequestBody Personne personne) {
        return new ResponseEntity<>(personneService.addPersonne(personne), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Personne> updatePersonne(@PathVariable("id") Long id,
            @RequestParam("firstname") String firstname,
                                                   @RequestParam("lastname") String lastname,
                                                   @RequestParam("datenaissance") @DateTimeFormat(pattern = "yyyy-MM-dd") Date datenaissance,
                                                   @RequestParam("sexe") String sexe,
                                                   @RequestParam("email") String email,
                                                   @RequestParam("phoneNumber") int phoneNumber,
                                                   @RequestParam("password") String password,
                                                   @RequestParam("roles") String roles,
                                                   @RequestParam(value = "image", required = false) MultipartFile imageFile) throws IOException {

            Personne updatedPersonne = personneService.getPersonneById(id);
            updatedPersonne.setNom(firstname);
            updatedPersonne.setPrenom(lastname);
            updatedPersonne.setSexe(sexe);
            updatedPersonne.setTelephone(phoneNumber);
            updatedPersonne.setDatenaissance(datenaissance);
        if (imageFile != null && !imageFile.isEmpty()) {
            byte[] imageData = imageFile.getBytes();
            String contentType = imageFile.getContentType();
            updatedPersonne.setImage(imageData);
            updatedPersonne.setImageContentType(contentType);
        }

        personneRepo.save(updatedPersonne);
            return new ResponseEntity<>(updatedPersonne, HttpStatus.OK);

        }
    @PatchMapping("/patch/{id}")
    public ResponseEntity<Personne> DeactivatePersonne(@PathVariable("id") Long id, @RequestBody Personne personne) {

        Personne updatedPersonne = personneService.getPersonneById(id);
        updatedPersonne.setActif(personne.getActif());
        personneRepo.save(updatedPersonne);
        return new ResponseEntity<>(updatedPersonne, HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
            personneService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }


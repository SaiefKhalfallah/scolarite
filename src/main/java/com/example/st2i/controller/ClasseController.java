package com.example.st2i.controller;


import com.example.st2i.service.ClasseService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.st2i.entity.Classe;


import java.util.List;

@RequiredArgsConstructor

@CrossOrigin("*")
@RestController
@RequestMapping("/classe")
public class ClasseController {
    @Autowired
    private ClasseService classeService;


    @GetMapping("/all")
    public ResponseEntity<List<Classe>> getAllClasses() {
        List<Classe> Classes = classeService.getAllClasse();
        return new ResponseEntity<>(Classes, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Classe> getClasseById(@PathVariable("id") Long id) {
        Classe Classe = classeService.getClasseById(id);
        return new ResponseEntity<>(Classe, HttpStatus.OK);
    }

  /*  @PostMapping("/add")

    public ResponseEntity<Classe> addClasse(@RequestBody Classe Classe) {

        System.out.println("Classe1"+Classe);

        return new ResponseEntity<>(classeService.addClasse(Classe),HttpStatus.CREATED);
    }
        */
      @PostMapping("/add")
      public ResponseEntity<Classe> addOrUpdateClasse(@RequestBody Classe classe) {
          Classe updatedClasse = classeService.addOrUpdateClasse(classe);
          return new ResponseEntity<>(updatedClasse, HttpStatus.CREATED);
      }
    @PutMapping("/update/{id}")
    public ResponseEntity<Classe> updateClasse(@PathVariable("id") Long id, @RequestBody Classe Classe) {


        Classe updatedClasse = classeService.getClasseById(id);

        updatedClasse.setNom(Classe.getNom());

        classeService.addClasse(updatedClasse);
        return new ResponseEntity<>(updatedClasse, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        classeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/count-classes")
    public long getCountClasses() {
        return classeService.countClasses();
    }

}

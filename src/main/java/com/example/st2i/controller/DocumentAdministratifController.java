package com.example.st2i.controller;


import com.example.st2i.entity.Absence;
import com.example.st2i.entity.DocResponse;
import com.example.st2i.repository.DocumentAdministratifRepository;
import com.example.st2i.service.DocumentAdministratifService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.st2i.entity.DocumentAdministratif;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor

@CrossOrigin("*")
@RestController
@RequestMapping("/documentAdministratif")
public class DocumentAdministratifController {
    @Autowired
    private DocumentAdministratifService documentAdministratifService;
    @Autowired
    private DocumentAdministratifRepository documentAdministratifRepository;

    @PatchMapping("/upload/patch/{id}")
    @Transactional
    public ResponseEntity<DocumentAdministratif> registerfile(@PathVariable("id") Long id,
                                                @RequestParam("file") MultipartFile file) {
        DocumentAdministratif updateDocumentAdministratif = documentAdministratifService.getDocumentAdministratifById(id);



        // Handle the image file
        if (!file.isEmpty()) {
            try {
                byte[] imageData = file.getBytes();
                String name= file.getName();
                // Assuming you have a setter method for image and imageContentType
                updateDocumentAdministratif.setFileupload(imageData);
            } catch (IOException e) {
                System.err.println("problem occured");

            }
        }

        documentAdministratifRepository.save(updateDocumentAdministratif);
        return new ResponseEntity<>(updateDocumentAdministratif, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<DocumentAdministratif>> getAllDocumentAdministratifs() {
        List<DocumentAdministratif> DocumentAdministratifs = documentAdministratifService.getAllDocumentAdministratif();
        return new ResponseEntity<>(DocumentAdministratifs, HttpStatus.OK);
    }

    @GetMapping("/dto/all")
    public ResponseEntity<List<DocResponse>> getAllDocumentAdministratifsDto() {
        List<DocResponse> DocumentAdministratifs = documentAdministratifService.getAllDocumentAdministratifdto();
        return new ResponseEntity<>(DocumentAdministratifs, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<DocumentAdministratif> getDocumentAdministratifById(@PathVariable("id") Long id) {
        DocumentAdministratif DocumentAdministratif = documentAdministratifService.getDocumentAdministratifById(id);
        return new ResponseEntity<>(DocumentAdministratif, HttpStatus.OK);
    }

    @PostMapping("/add")

    public ResponseEntity<DocumentAdministratif> addDocumentAdministratif(@RequestBody DocumentAdministratif DocumentAdministratif) {

        System.out.println("DocumentAdministratif1"+DocumentAdministratif);

        return new ResponseEntity<>(documentAdministratifService.addDocumentAdministratif(DocumentAdministratif),HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DocumentAdministratif> updateDocumentAdministratif(@PathVariable("id") Long id, @RequestBody DocumentAdministratif DocumentAdministratif) {


        DocumentAdministratif updatedDocumentAdministratif = documentAdministratifService.getDocumentAdministratifById(id);

        updatedDocumentAdministratif.setType(DocumentAdministratif.getType());
        updatedDocumentAdministratif.setTitre(DocumentAdministratif.getTitre());
        updatedDocumentAdministratif.setDate_reception(DocumentAdministratif.getDate_reception());
        updatedDocumentAdministratif.setDate_reception(DocumentAdministratif.getDate_reception());


        documentAdministratifService.addDocumentAdministratif(updatedDocumentAdministratif);
        return new ResponseEntity<>(updatedDocumentAdministratif, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        documentAdministratifService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}

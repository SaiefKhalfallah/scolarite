package com.example.st2i.controller;


import com.example.st2i.DTOs.ReclamationDTO;
import com.example.st2i.DTOs.View;
import com.example.st2i.conversion.EntityToDTOConverter;
import com.example.st2i.service.ReclamationService;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.st2i.entity.Reclamation;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;



@CrossOrigin("*")
@RestController
@RequestMapping("/api/reclamation")
public class ReclamationController {
    private final ReclamationService reclamationService;


    public ReclamationController(ReclamationService reclamationService) {
        this.reclamationService = reclamationService;
    }

    @GetMapping

    public ResponseEntity<List<ReclamationDTO>> getAllReclamations() {
        List<Reclamation> reclamations =reclamationService.getAllReclamations();
        return ResponseEntity.ok(EntityToDTOConverter.convertToReclamationDTOs(reclamations));
    }
    @PostMapping("/{personId}")
    public ResponseEntity<ReclamationDTO> createReclamation(
            @PathVariable Long personId,
            @RequestParam("titre") String titre,
            @RequestParam("description") String description,
            @RequestParam("date") String date,
            @RequestParam("heure") String heure,
            @RequestParam("type") String type,
            @RequestParam(value = "image", required = false) MultipartFile imageFile) throws IOException {

        Reclamation reclamation = new Reclamation();
        reclamation.setTitre(titre);
        reclamation.setDescription(description);
        try {
            Date parsedDate = new SimpleDateFormat("dd-MM-yyyy").parse(date);
            reclamation.setDate(parsedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        reclamation.setHeure(heure);
        reclamation.setType(type);

        if (imageFile != null && !imageFile.isEmpty()) {
            byte[] imageData = imageFile.getBytes();
            String contentType = imageFile.getContentType();
            reclamation.setImage(imageData);
        }

        Reclamation createdReclamation = reclamationService.createReclamation(reclamation, personId);

        ReclamationDTO reclamationDTO = EntityToDTOConverter.convertToReclamationDTO(createdReclamation);

        return ResponseEntity.ok(reclamationDTO);
    }


    @PostMapping("/{reclamationId}/like/{personId}")
    public ResponseEntity<Void> likeReclamation(@PathVariable Long reclamationId, @PathVariable Long personId) {
        reclamationService.likeReclamation(reclamationId, personId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{reclamationId}/unlike/{personId}")
    public ResponseEntity<Void> unlikeReclamation(@PathVariable Long reclamationId, @PathVariable Long personId) {
        reclamationService.unlikeReclamation(reclamationId, personId);
        return ResponseEntity.ok().build();
    }


}

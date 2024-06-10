package com.example.st2i.controller;

import com.example.st2i.entity.AuthenticationRequest;
import com.example.st2i.entity.RegisterRequest;
import com.example.st2i.entity.Role;
import com.example.st2i.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping({"/api/v1/auth"})
@RequiredArgsConstructor
@CrossOrigin({"*"})
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestParam("firstname") String firstname,
            @RequestParam("lastname") String lastname,
            @RequestParam("datenaissance") @DateTimeFormat(pattern = "yyyy-MM-dd") Date datenaissance,
            @RequestParam("sexe") String sexe,
            @RequestParam("email") String email,
            @RequestParam("phoneNumber") int phoneNumber,
            @RequestParam("password") String password,
            @RequestParam("roles") String roles,
            @RequestParam("image") MultipartFile imageFile) {

        RegisterRequest request = new RegisterRequest();
        request.setFirstname(firstname);
        request.setLastname(lastname);
        request.setDatenaissance(datenaissance);
        request.setSexe(sexe);
        request.setEmail(email);
        request.setPhoneNumber(phoneNumber);
        request.setPassword(password);
        if (roles.equals("ETUDIANT")) {
            request.setRoles(Role.ETUDIANT);
        }
        else if (roles.equals("ADMINISTRATEUR")) {
            request.setRoles(Role.ADMINISTRATEUR);
        }

        else {
            request.setRoles(Role.ENSEIGNANT);
        }

        // Handle the image file
        if (!imageFile.isEmpty()) {
            try {
                byte[] imageData = imageFile.getBytes();
                String contentType = imageFile.getContentType();
                // Assuming you have a setter method for image and imageContentType
                System.out.println(imageFile.getContentType());
                request.setImage(imageData);
                request.setImageContentType(contentType);
            } catch (IOException e) {
                System.err.println("problem occured");

            }
        }

        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {

        return ResponseEntity.ok(service.authenticate(request));
    }
}

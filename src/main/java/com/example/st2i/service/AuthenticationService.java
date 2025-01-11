package com.example.st2i.service;

import com.example.st2i.config.JwtService;
import com.example.st2i.controller.AuthenticationResponse;
import com.example.st2i.entity.AuthenticationRequest;
import com.example.st2i.entity.Compte;
import com.example.st2i.entity.Personne;
import com.example.st2i.entity.RegisterRequest;
import com.example.st2i.repository.PersonneRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final PersonneRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Transactional
    public AuthenticationResponse register(RegisterRequest request) {
        var personne = Personne.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .Nom(request.getLastname())
                .Prenom(request.getFirstname())
                .Datenaissance(request.getDatenaissance())
                .Sexe(request.getSexe())
                .Telephone(request.getPhoneNumber())
                .image(request.getImage())
                .imageContentType(request.getImageContentType())
                .username(request.getEmail())
                .roles(request.getRoles()).actif(true)
                .parentEmail(request.getParentEmail())
                .build();

        personne = repository.save(personne);
        var jwtToken = jwtService.generateToken(personne.getEmail(), personne.getId_personne());
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
    @Transactional
    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        System.out.println("request retrieved");

        Personne user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        if(user.getActif()==false){
            throw new RuntimeException("Account Deactivated. please contact your administrator");
        }
        System.out.println("request retrieved");

        if (passwordEncoder.matches(request.getPassword(),user.getPassword())){
            var jwtToken = jwtService.generateToken(user.getUsername(), user.getId_personne());
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .user(user)
                    .build();
        }
     else {
        throw new RuntimeException("Invalid credentials"); // Replace with a specific exception if needed
    }



    }
}

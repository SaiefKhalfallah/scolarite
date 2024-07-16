package com.example.st2i.controller;

import com.example.st2i.entity.Personne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

        private String token;
        private Personne user;

}

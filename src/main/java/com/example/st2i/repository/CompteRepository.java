package com.example.st2i.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.st2i.entity.Compte;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompteRepository extends JpaRepository<Compte,Long> {

    Optional<Compte> findByEmail(String email);

}

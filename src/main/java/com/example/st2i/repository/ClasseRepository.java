package com.example.st2i.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.st2i.entity.Classe;

import java.util.Optional;


@Repository
public interface ClasseRepository extends JpaRepository<Classe,Long>{
    Optional<Classe> findByNom(String nom);
}

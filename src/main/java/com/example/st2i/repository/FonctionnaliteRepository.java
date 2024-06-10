package com.example.st2i.repository;

import com.example.st2i.entity.Fonctionnalite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FonctionnaliteRepository extends JpaRepository<Fonctionnalite,Long> {
}

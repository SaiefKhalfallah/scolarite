package com.example.st2i.repository;

import java.util.Optional;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.st2i.entity.Personne;

@Transactional
@Repository
public interface PersonneRepository extends JpaRepository<Personne,Long>{


	Optional <Personne> findByEmail(String email);

	Optional<Personne> findById(Long id);

	@Query("SELECT COUNT(p) FROM Personne p WHERE p.roles = com.example.st2i.entity.Role.ETUDIANT")
	long countEleves();

	@Query("SELECT COUNT(p) FROM Personne p WHERE p.roles = com.example.st2i.entity.Role.ENSEIGNANT")
	long countEnseignants();



}

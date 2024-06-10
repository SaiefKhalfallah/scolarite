package com.example.st2i.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.st2i.entity.Classe;



@Repository
public interface ClasseRepository extends JpaRepository<Classe,Long>{

}

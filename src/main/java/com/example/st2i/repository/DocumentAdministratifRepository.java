package com.example.st2i.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.st2i.entity.DocumentAdministratif;

import java.util.List;


@Repository
public interface DocumentAdministratifRepository extends JpaRepository<DocumentAdministratif,Long>{
    List<DocumentAdministratif> findByIdEleve(Long idEleve);
}

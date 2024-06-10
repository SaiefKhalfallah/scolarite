package com.example.st2i.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.st2i.entity.Sanction;


@Repository
public interface SanctionRepository extends JpaRepository<Sanction,Long>{

}

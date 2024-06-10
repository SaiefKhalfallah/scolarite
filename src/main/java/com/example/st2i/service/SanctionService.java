package com.example.st2i.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.st2i.entity.Sanction;
import com.example.st2i.repository.SanctionRepository;

import java.util.List;
import java.util.Optional;


@Service
public class SanctionService{
    @Autowired
    private SanctionRepository SanctionRepo;

    public Sanction addSanction(Sanction Sanction) {
        return SanctionRepo.save(Sanction);
    }


    public List<Sanction> getAllSanction(){
        return SanctionRepo.findAll();
    }
    public Sanction getSanctionById(Long id) {
        Optional<Sanction> e= SanctionRepo.findById(id);
        return e.isPresent() ? e.get() : null;
    }


    public void deleteById(Long id){
        SanctionRepo.deleteById(id);
    }
}

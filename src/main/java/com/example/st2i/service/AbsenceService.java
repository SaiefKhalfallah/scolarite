package com.example.st2i.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.st2i.entity.Absence;
import com.example.st2i.repository.AbsenceRepository;

import java.util.List;
import java.util.Optional;


@Service
public class AbsenceService{
    @Autowired
    private AbsenceRepository absenceRepo;

    public Absence addAbsence(Absence Absence) {
        return absenceRepo.save(Absence);
    }


    public Absence getAbsenceById(Long id) {
        Optional<Absence> e= absenceRepo.findById(id);
        return e.isPresent() ? e.get() : null;
    }
    public List<Absence> getAllAbsence(){
        return absenceRepo.findAll();
    }


    public void deleteById(Long id){
        absenceRepo.deleteById(id);
    }
}

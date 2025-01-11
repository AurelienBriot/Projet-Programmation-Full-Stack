package org.vaccination.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.FlashMapManager;
import org.vaccination.entities.Creneau;
import org.vaccination.repositories.CreneauRepository;

@Service
public class CreneauService {
    
    @Autowired
    private CreneauRepository creneauRepository;
    
    public CreneauService() {

    }

    public List<Creneau> findAll() {
        return creneauRepository.findAll();
    }  

    public Creneau findOneById(Integer id) throws Exception {
        return creneauRepository.findOneById(id);
    }

    public Creneau create(Creneau c) {
        return creneauRepository.save(c);
    }

    public void delete(Integer id) throws Exception {
        creneauRepository.deleteById(id);
    }
    
    public Creneau validerVaccination(Integer id) {
        Creneau creneau = creneauRepository.findOneById(id);
        creneau.setEstTermine(true);
        return creneauRepository.save(creneau);
    }
}

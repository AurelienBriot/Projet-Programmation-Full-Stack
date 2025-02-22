package org.vaccination.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaccination.entities.Centre;
import org.vaccination.entities.Creneau;
import org.vaccination.entities.Patient;
import org.vaccination.exceptions.CreneauNotFoundException;
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

    public List<Creneau> findAllByCentreAndDateAndEstReserve(Centre centre, LocalDate date, Boolean estReserve) throws CreneauNotFoundException {
        return creneauRepository.findAllByCentreAndDateAndEstReserve(centre, date, estReserve);
    }

    public Creneau findOneById(Integer id) throws CreneauNotFoundException {
        return creneauRepository.findOneById(id);
    }

    public Creneau create(Creneau c) {
        return creneauRepository.save(c);
    }

    public void delete(Integer id) throws CreneauNotFoundException {
        creneauRepository.deleteById(id);
    }
    
    public Creneau validerVaccination(Integer id) throws CreneauNotFoundException {
        Creneau creneau = creneauRepository.findOneById(id);
        creneau.setEstTermine(true);
        return creneauRepository.save(creneau);
    }

    public Creneau updateCreneauPatient(Integer id, Patient p) throws CreneauNotFoundException {
        Creneau creneau = creneauRepository.findOneById(id);
        creneau.setPatient(p);
        creneau.setEstReserve(true);
        return creneauRepository.save(creneau);
    }

    public List<Creneau> findAllByEstReserve(Boolean estReserve) {
        return creneauRepository.findAllByEstReserve(estReserve);
    }
}

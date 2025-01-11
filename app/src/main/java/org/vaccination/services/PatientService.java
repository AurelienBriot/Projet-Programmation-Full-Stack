package org.vaccination.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaccination.entities.Patient;
import org.vaccination.exceptions.PatientNotFoundException;
import org.vaccination.repositories.PatientRepository;

@Service
public class PatientService {

    @Autowired
    PatientRepository patientRepository;
    
    public PatientService() {

    }

    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    public List<Patient> findAllByNomAndPrenom(String nom, String prenom) {
        if(prenom != null && nom != null) {
            return patientRepository.findAllByNomLikeIgnoringCaseAndPrenomLikeIgnoringCase(nom, prenom);
        }
        else if (prenom != null && nom == null) {
            return patientRepository.findAllByPrenomLikeIgnoringCase(prenom);
        }
        else if (prenom == null && nom != null) {
            return patientRepository.findAllByNomLikeIgnoringCase(nom);
        }
        else {
            return patientRepository.findAll();
        }
    }

    public Patient findOneById(Integer id) throws PatientNotFoundException {
        return patientRepository.findOneById(id);    
    }

    public Patient create(Patient user) {
        return patientRepository.save(user);
    }

    public void delete(Integer id) {
        patientRepository.deleteById(id); 
    }

    public Patient validerVaccination(Integer id) throws PatientNotFoundException {
        Patient patient = patientRepository.findOneById(id);
        patient.setEstVaccine(true);
        return patientRepository.save(patient);
    }

    

}

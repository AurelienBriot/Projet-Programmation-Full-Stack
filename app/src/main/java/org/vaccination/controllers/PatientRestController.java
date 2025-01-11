package org.vaccination.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.vaccination.entities.Patient;
import org.vaccination.exceptions.PatientNotFoundException;
import org.vaccination.services.PatientService;

@RestController
public class PatientRestController {

    @Autowired
    private PatientService patientService;
    
    public PatientRestController() {

    }

    @PostMapping(path = "/api/public/patient")
    public Patient create(@RequestBody Patient patient) {
        return patientService.create(patient);
    }

    @GetMapping(path = "/api/medecin/patients")
    public List<Patient> findAll(@RequestParam(name = "nom", required = false) String nom, 
                                        @RequestParam(name = "prenom", required = false) String prenom) {
        return patientService.findAllByNomAndPrenom(nom, prenom);
    }

    @GetMapping(path = "/api/medecin/patient/{id}")
    public Patient findOne(@PathVariable(name = "id") Integer id,
                            @RequestParam(name = "estVaccine") Boolean estVaccine) throws PatientNotFoundException {
        
        if (estVaccine == true) {
            return patientService.validerVaccination(id);
        }
        else {
            return patientService.findOneById(id);
        }
        
    }

    @DeleteMapping(path = "/api/super-admin/patient/{id}")
    public void deletePatient(@PathVariable(name = "id") Integer id) throws PatientNotFoundException {
        patientService.delete(id);
    }

}

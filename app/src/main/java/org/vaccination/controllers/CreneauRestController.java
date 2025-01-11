package org.vaccination.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.vaccination.entities.Creneau;
import org.vaccination.services.CreneauService;

@RestController
public class CreneauRestController {

    @Autowired
    private CreneauService creneauService;

    public CreneauRestController() {

    }

    @GetMapping(path = {"/api/medecin/creneaux", "/api/admin/creneaux"})
    public List<Creneau> findAll() {
        return this.creneauService.findAll();
    }

    @GetMapping(path = {"/api/medecin/creneau/{id}"})
    public Creneau getOneById(@PathVariable("id") Integer id, 
                            @RequestParam(name = "validerVac", required = false) Boolean validerVac) throws Exception {
        
        if(validerVac == true) {
            return this.creneauService.validerVaccination(id);
        }
        else {
            return this.creneauService.findOneById(id);
        }
        
        
    }



}

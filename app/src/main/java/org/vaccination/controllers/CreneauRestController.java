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
import org.vaccination.entities.Creneau;
import org.vaccination.exceptions.CreneauNotFoundException;
import org.vaccination.services.CreneauService;

@RestController
public class CreneauRestController {

    @Autowired
    private CreneauService creneauService;

    public CreneauRestController() {

    }

    @GetMapping(path = {"/api/public/creneaux", "/api/medecin/creneaux", "/api/admin/creneaux"})
    public List<Creneau> findAll() {
        return this.creneauService.findAll();
    }

    @GetMapping(path = {"/api/medecin/creneau/{id}"})
    public Creneau getOneById(@PathVariable("id") Integer id, 
                            @RequestParam(name = "validerVac", required = false) Boolean validerVac) throws CreneauNotFoundException {
        
        if(validerVac == true) {
            return this.creneauService.validerVaccination(id);
        }
        else {
            return this.creneauService.findOneById(id);
        }
        
        
    }

    @PostMapping(path = "/api/admin/creneau")
    public Creneau createCreneau(@RequestBody Creneau creneau) {
        return creneauService.create(creneau);
    }

    @DeleteMapping(path = "/api/admin/creneau") 
    public void deletCreneau(Integer id) throws CreneauNotFoundException {
        creneauService.delete(id);
    }


}

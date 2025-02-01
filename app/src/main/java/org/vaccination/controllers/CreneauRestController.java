package org.vaccination.controllers;


import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.vaccination.entities.Centre;
import org.vaccination.entities.Creneau;
import org.vaccination.exceptions.CentreNotFoundException;
import org.vaccination.exceptions.CreneauNotFoundException;
import org.vaccination.services.CentreService;
import org.vaccination.services.CreneauService;

@RestController
public class CreneauRestController {

    @Autowired
    private CreneauService creneauService;

    @Autowired
    private CentreService centreService;

    public CreneauRestController() {

    }

    @GetMapping(path = {"/api/medecin/creneaux", "/api/admin/creneaux"})
    public List<Creneau> findAll() {
        return this.creneauService.findAll();
    }

    @GetMapping(path = {"/api/public/creneaux"})
    public List<Creneau> findAllByVilleAndDate(@RequestParam(name = "centre", required = true) Integer centre_id, @RequestParam(name = "date", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) throws CreneauNotFoundException {
        Centre centre;
        try {
            centre = centreService.findOneById(centre_id);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Centre non trouvé");
        }      
        return this.creneauService.findAllByCentreAndDate(centre, date);
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

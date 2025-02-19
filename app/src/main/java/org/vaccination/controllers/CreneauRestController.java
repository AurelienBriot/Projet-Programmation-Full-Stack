package org.vaccination.controllers;

import java.time.LocalDate;
import java.util.List;

import javax.annotation.ParametersAreNonnullByDefault;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.vaccination.entities.Centre;
import org.vaccination.entities.Creneau;
import org.vaccination.entities.Patient;
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

    // Récupérer tous les créneaux (medecin)
    @GetMapping(path = {"/api/touslescreneaux"})
    @PreAuthorize("hasAnyRole('SUPERADMIN', 'ADMIN', 'MEDECIN')")
    public List<Creneau> findAll() {
        return this.creneauService.findAll();
    }

    // Récupérer tous les créneaux par ville et date (public)
    @GetMapping(path = {"/api/creneaux"})
    public List<Creneau> findAllByVilleAndDateAndEstReserve(@RequestParam(name = "centre", required = true) Integer centre_id, @RequestParam(name = "date", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, @RequestParam(name = "estReserve", required = true) Boolean estReserve) throws CreneauNotFoundException {
        Centre centre;
        try {
            centre = centreService.findOneById(centre_id);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Centre non trouvé");
        }      
        return this.creneauService.findAllByCentreAndDateAndEstReserve(centre, date, estReserve);
    }

    // Lier un nouveau patient à un créneau = mettre à jour le patient (public)
    @PutMapping(path = "/api/creneau/{id}/patient")
    public Creneau updateCreneauWithNewPatient(@PathVariable("id") Integer id, @RequestBody Patient patient) throws CreneauNotFoundException {
        return creneauService.updateCreneauPatient(id, patient);
    }

    // Valider un créneau (medecin)
    @GetMapping(path = {"/api/creneau/{id}"})
    @PreAuthorize("hasAnyRole('SUPERADMIN', 'ADMIN', 'MEDECIN')")
    public Creneau getOneById(@PathVariable("id") Integer id, 
                            @RequestParam(name = "validerVac", required = false) Boolean validerVac) throws CreneauNotFoundException {
        
        if(validerVac == true) {
            return this.creneauService.validerVaccination(id);
        }
        else {
            return this.creneauService.findOneById(id);
        }
        
        
    }

    // Créer un créneau (admin)
    @PostMapping(path = "/api/creneau")
    @PreAuthorize("hasAnyRole('SUPERADMIN', 'ADMIN')")
    public Creneau createCreneau(@RequestBody Creneau creneau) {
        return creneauService.create(creneau);
    }

    // Supprimer un créneau (admin)
    @DeleteMapping(path = "/api/creneau")
    @PreAuthorize("hasAnyRole('SUPERADMIN', 'ADMIN')")
    public void deletCreneau(Integer id) throws CreneauNotFoundException {
        creneauService.delete(id);
    }


}

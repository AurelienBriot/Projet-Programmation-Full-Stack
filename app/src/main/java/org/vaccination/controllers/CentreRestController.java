package org.vaccination.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.vaccination.entities.Centre;
import org.vaccination.exceptions.CentreNotFoundException;
import org.vaccination.services.CentreService;

@RestController
public class CentreRestController {

    @Autowired
    private CentreService centerService;
    
    public CentreRestController() {

    }

    // Récupérer tous les centres (public)
    @GetMapping(path = {"/api/centres"})
    public List<Centre> getAll(@RequestParam(name = "ville", required = false) String ville) {
        return centerService.findAllByVille(ville);
    }

    // Récupérer un centre (public)
    @GetMapping(path = "/api/centre/{id}")
    public Centre getOneById(@PathVariable("id") Integer id) throws CentreNotFoundException {
        return centerService.findOneById(id);
    }

    // Ajouter un centre (superadmin)
    @PostMapping(path = "/api/centre")
    @PreAuthorize("hasRole('SUPERADMIN')")
    public Centre create(@RequestBody Centre c) {
        return centerService.create(c);
    }

    // Mettre à jour un centre (nom, ville, adresse) (superadmin)
    @PutMapping(path = "api/centre/{id}")
    @PreAuthorize("hasRole('SUPERADMIN')")
    public Centre updateCentre(@PathVariable("id") Integer id, @RequestBody Centre c) throws CentreNotFoundException {
        return centerService.update(id, c);
    }
    
    // Supprimer un centre (superadmin)
    @DeleteMapping(path = "/api/centre/{id}")
    @PreAuthorize("hasRole('SUPERADMIN')")
    public void delete(@PathVariable("id") Integer id) throws CentreNotFoundException {
        centerService.delete(id);
    }

    @ExceptionHandler
    public ResponseEntity<String> handle(CentreNotFoundException ex){
        return ResponseEntity.badRequest().body("Le centre n'existe pas");
    }

}

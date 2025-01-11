package org.vaccination.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import org.vaccination.services.CenterService;

@RestController
public class CentreRestController {

    @Autowired
    private CenterService centerService;
    
    public CentreRestController() {

    }

    @GetMapping(path = {"/api/public/centres"})
    public List<Centre> getAll(@RequestParam(name = "ville", required = false) String ville) {
        return centerService.findAllByVille(ville);
    }

    @GetMapping(path = "/api/public/centre/{id}")
    public Centre getOneById(@PathVariable("id") Integer id) throws CentreNotFoundException {
        return centerService.findOneById(id);
    }

    // Ajouter un centre
    @PostMapping(path = "/api/super-admin/centre")
    public Centre create(@RequestBody Centre c) {
        return centerService.create(c);
    }

    // Mettre à jour un centre (nom, ville, adresse)
    @PutMapping(path = "api/super-admin/centre/{id}")
    public Centre updateCentre(@PathVariable("id") Integer id, @RequestBody Centre c) throws CentreNotFoundException {
        return centerService.update(id, c);
    }
    
    // Supprimer un centre
    @DeleteMapping(path = "/api/super-admin/centre/{id}")
    public void delete(@PathVariable("id") Integer id) throws CentreNotFoundException {
        centerService.delete(id);
    }

    @ExceptionHandler
    public ResponseEntity<String> handle(CentreNotFoundException ex){
        return ResponseEntity.badRequest().body("Le centre n'existe pas");
    }

}

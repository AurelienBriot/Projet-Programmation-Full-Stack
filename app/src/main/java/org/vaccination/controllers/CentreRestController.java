package org.vaccination.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.vaccination.entities.Centre;
import org.vaccination.services.CenterService;

@RestController
public class CentreRestController {

    @Autowired
    private CenterService centerService;
    
    public CentreRestController() {

    }

    @GetMapping(path = {"/api/public/centres", "/api/admin/centres"}, produces = {"application/json"})
    public List<Centre> getAll(@RequestParam(name = "ville", required = false) String ville) throws Exception {
        return centerService.findAllByVille(ville);
    }

    @GetMapping(path = "/api/public/centre/{id}", produces = {"application/json"})
    public Centre getOneById(@PathVariable("id") Integer id) throws Exception {
        Centre test = centerService.findOneById(id);
        System.out.println(test);
        return centerService.findOneById(id);
    }

    // Ajouter un centre
    @PostMapping(path = "/api/admin/centre")
    public Centre create(@RequestBody Centre c) throws URISyntaxException {
        return centerService.create(c);
    }

    // Mettre à jour un centre (nom, ville, adresse)
    @PutMapping(path = "api/admin/centre/{id}")
    public Centre updateCentre(@PathVariable("id") Integer id, @RequestBody Centre c) throws Exception {
        return centerService.update(id, c);
    }
    
    // Supprimer un centre
    @DeleteMapping(path = "/api/admin/centre/{id}")
    public void delete(@PathVariable("id") Integer id) {
        centerService.delete(id);
    }

    // @PutMapping(path = "/apiadmin/centre/{id}/admin")
    // public void updateAdmin(@PathVariable("id") Integer id, @RequestBody User user) throws URISyntaxException {
    //     centerService.addAdmin(id, user);
    // }

    // @DeleteMapping(path = "/api/admin/centre/{id}/admin")
    // public void deleteAdmin(@PathVariable("id") Integer id) {
    //     centerService.deleteAdmin(id);
    // }

    // @PostMapping(path = "/api/admin/centre/{id}/adresse")
    // public void updateAdresse(@PathVariable("id") Integer id, @RequestBody String adresse) throws URISyntaxException {
    //     centerService.updateAdresse(id, adresse);
    // }

    // @PostMapping(path = "/api/admin/centre/{id}/ville")
    // public void updateVille(@PathVariable("id") Integer id, @RequestBody String ville) throws URISyntaxException {
    //     centerService.updateVille(id, ville);
    // }

    // @PostMapping(path = "/api/admin/centre/{id}/medecin")
    // public void addMedecin(@PathVariable("id") Integer id, @RequestBody User medecin) throws URISyntaxException {
    //     centerService.addMedecin(id, medecin);
    // }

    // @DeleteMapping(path = "/api/admin/centre/{id}/medecin/{userId}")
    // public void removeMedecin(@PathVariable("id") Integer id, @PathVariable("userId") Integer userId) throws URISyntaxException {
    //     centerService.removeMedecin(id, userId);
    // }

}

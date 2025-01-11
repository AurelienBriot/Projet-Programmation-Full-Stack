package org.vaccination.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.vaccination.services.UtilisateurService;

import org.vaccination.entities.Utilisateur;

@RestController
public class UtilisateurRestController {

    @Autowired
    UtilisateurService utilisateurService;

    public UtilisateurRestController() {

    }

    @GetMapping(path = "/api/super-admin/administrateurs")
    public List<Utilisateur> findAdministrateurs() {
        return utilisateurService.findAdministrateurs();
    }

    @GetMapping(path = "/api/admin/medecins")
    public List<Utilisateur> findMedecins() {
        return utilisateurService.findMedecins();
    }

    @GetMapping(path = "/api/super-admin/administrateur/{id}")
    public Utilisateur findOneAdministrateur(@PathVariable(name = "id") Integer id) {
        return utilisateurService.findOneAdministrateur(id);
    }

    @GetMapping(path = "/api/admin/medecin/{id}")
    public Utilisateur findOneMedecin(@PathVariable(name = "id") Integer id) {
        return utilisateurService.findOneMedecin(id);
    }

    @PostMapping(path = "/api/super-admin/administrateur")
    public Utilisateur createAdministrateur(@RequestBody Utilisateur utilisateur) {
        return utilisateurService.createAdministrateur(utilisateur);
    }

    @PostMapping(path = "/api/admin/medecin")
    public Utilisateur createMedecin(@RequestBody Utilisateur utilisateur) {
        return utilisateurService.createMedecin(utilisateur);
    }

    @PutMapping(path = "/api/super-admin/administrateur/{id}")
    public Utilisateur updateAdministrateur(@PathVariable(name = "id") Integer id, @RequestBody Utilisateur utilisateur) {
        return utilisateurService.updateAdministrateur(id, utilisateur);
    }

    @PutMapping(path = "/api/admin/medecin/{id}")
    public Utilisateur updateMedecin(@PathVariable(name = "id") Integer id, @RequestBody Utilisateur utilisateur) {
        return utilisateurService.updateMedecin(id, utilisateur);
    }
    
    @DeleteMapping(path = "/api/super-admin/administrateur/{id}")
    public void deleteAdministrateur(@PathVariable(name = "id") Integer id) {
        utilisateurService.deleteAdministrateur(id);
    }

    @DeleteMapping(path = "/api/super-admin/medecin/{id}")
    public void deleteMedecin(@PathVariable(name = "id") Integer id) {
        utilisateurService.deleteMedecin(id);
    }


    

}

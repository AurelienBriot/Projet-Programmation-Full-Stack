package org.vaccination.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.vaccination.services.UtilisateurService;

import org.vaccination.entities.Utilisateur;
import org.vaccination.exceptions.UtilisateurNotFoundException;

@RestController
public class UtilisateurRestController {

    @Autowired
    UtilisateurService utilisateurService;

    public UtilisateurRestController() {

    }

    // Récupérer tous les superadmins (superadmin)
    @GetMapping(path = "/api/super-admins")
    @PreAuthorize("hasRole('SUPERADMIN')")
    public List<Utilisateur> findAllSuperAdmins(@RequestParam(name = "nom", required = false) String nom) {
        return utilisateurService.findAllByNomAndRole(nom, "SUPERADMIN");
    }

    // Récupérer tous les admins (superadmin)
    @GetMapping(path = "/api/admins")
    @PreAuthorize("hasRole('SUPERADMIN')")
    public List<Utilisateur> findAllAdmins(@RequestParam(name = "nom", required = false) String nom) {
        return utilisateurService.findAllByNomAndRole(nom, "ADMIN");
    }

    // Récupérer tous les medecins (superadmin, admin)
    @GetMapping(path = "/api/medecins")
    @PreAuthorize("hasAnyRole('SUPERADMIN', 'ADMIN')")
    public List<Utilisateur> findAllMedecins(@RequestParam(name = "nom", required = false) String nom) {
        return utilisateurService.findAllByNomAndRole(nom, "MEDECIN");
    }

    // Récupérer un superadmin par id (superadmin)
    @GetMapping(path = "/api/super-admin/{id}")
    @PreAuthorize("hasRole('SUPERADMIN')")
    public Utilisateur findSuperAdminById(@PathVariable(name = "id") Integer id) throws UtilisateurNotFoundException {
        return utilisateurService.findOneByIdAndRole(id, "SUPERADMIN");
    }

     // Récupérer un admin par id (superadmin)
    @GetMapping(path = "/api/admin/{id}")
    @PreAuthorize("hasRole('SUPERADMIN')")
    public Utilisateur findAdminById(@PathVariable(name = "id") Integer id) throws UtilisateurNotFoundException {
        return utilisateurService.findOneByIdAndRole(id, "ADMIN");
    }

    // Récupérer un medecin par id (superadmin, admin)
    @GetMapping(path = "/api/medecin/{id}")
    @PreAuthorize("hasAnyRole('SUPERADMIN', 'ADMIN')")
    public Utilisateur findMedecinById(@PathVariable(name = "id") Integer id) throws UtilisateurNotFoundException {
        return utilisateurService.findOneByIdAndRole(id, "MEDECIN");
    }

    // Créer un superadmin (superadmin)
    @PostMapping(path = "/api/super-admin")
    @PreAuthorize("hasRole('SUPERADMIN')")
    public Utilisateur createSuperAdmin(@RequestBody Utilisateur utilisateur) {
        if (!utilisateur.getRole().equals("SUPERADMIN")) {
            throw new IllegalArgumentException("L'utilisateur créé doit être un super admin");
        }
        return utilisateurService.create(utilisateur);
    }

    // Créer un admin (superadmin)
    @PostMapping(path = "/api/admin")
    @PreAuthorize("hasAnyRole('SUPERADMIN')")
    public Utilisateur createAdmin(@RequestBody Utilisateur utilisateur) {
        if (!utilisateur.getRole().equals("ADMIN")) {
            throw new IllegalArgumentException("L'utilisateur créé doit être un admin");
        }
        return utilisateurService.create(utilisateur);
    }

    // Créer un médecin (superadmin, admin)
    @PostMapping(path = "/api/medecin")
    @PreAuthorize("hasAnyRole('SUPERADMIN', 'ADMIN')")
    public Utilisateur createMedecin(@RequestBody Utilisateur utilisateur) {
        if (!utilisateur.getRole().equals("MEDECIN")) {
            throw new IllegalArgumentException("L'utilisateur créé doit être un médecin");
        }
        return utilisateurService.create(utilisateur);
    }

    // Mettre à jour un superadmin (superadmin)
    @PutMapping(path = "/api/super-admin/{id}")
    @PreAuthorize("hasRole('SUPERADMIN')")
    public Utilisateur updateSuperAdmin(@PathVariable(name = "id") Integer id, @RequestBody Utilisateur utilisateur) throws UtilisateurNotFoundException {
        if (!utilisateur.getRole().equals("SUPERADMIN")) {
            throw new IllegalArgumentException("L'utilisateur mis à jour doit être un super admin");
        }
        return utilisateurService.update(id, utilisateur);
    }

    // Mettre à jour un admin (superadmin)
    @PutMapping(path = "/api/admin/{id}")
    @PreAuthorize("hasRole('SUPERADMIN')")
    public Utilisateur updateAdmin(@PathVariable(name = "id") Integer id, @RequestBody Utilisateur utilisateur) throws UtilisateurNotFoundException {
        if (!utilisateur.getRole().equals("ADMIN")) {
            throw new IllegalArgumentException("L'utilisateur mis à jour doit être un admin");
        }
        return utilisateurService.update(id, utilisateur);
    }

    // Mettre à jour un médecin (superadmin, admin)
    @PutMapping(path = "/api/medecin/{id}")
    @PreAuthorize("hasAnyRole('SUPERADMIN', 'ADMIN')")
    public Utilisateur updateMedecin(@PathVariable(name = "id") Integer id, @RequestBody Utilisateur utilisateur) throws UtilisateurNotFoundException {
        if (!utilisateur.getRole().equals("MEDECIN")) {
            throw new IllegalArgumentException("L'utilisateur mis à jour doit être un médecin");
        }
        return utilisateurService.update(id, utilisateur);
    }

    // Mettre à jour le mot de passe d'un super admin (superadmin)
    @PutMapping(path = "/api/super-admin/{id}/password")
    @PreAuthorize("hasRole('SUPERADMIN')")
    public Utilisateur updateSuperAdminPassword(@PathVariable(name = "id") Integer id, @RequestBody Utilisateur utilisateur) {
        if (!utilisateur.getRole().equals("SUPERADMIN")) {
            throw new IllegalArgumentException("L'utilisateur mis à jour doit être super admin");
        }
        String password = utilisateur.getPassword();
        return utilisateurService.updatePassword(id, password);
    }

    // Mettre à jour le mot de passe d'un admin (superadmin)
    @PutMapping(path = "/api/admin/{id}/password")
    @PreAuthorize("hasRole('SUPERADMIN')")
    public Utilisateur updateAdminPassword(@PathVariable(name = "id") Integer id, @RequestBody Utilisateur utilisateur) {
        if (!utilisateur.getRole().equals("ADMIN")) {
            throw new IllegalArgumentException("L'utilisateur mis à jour doit être admin");
        }
        String password = utilisateur.getPassword();
        return utilisateurService.updatePassword(id, password);
    }

    // Mettre à jour le mot de passe d'un médecin (superadmin, admin)
    @PutMapping(path = "/api/medecin/{id}/password")
    @PreAuthorize("hasAnyRole('SUPERADMIN', 'ADMIN')")
    public Utilisateur updateMedecinPassword(@PathVariable(name = "id") Integer id, @RequestBody Utilisateur utilisateur) {
        if (!utilisateur.getRole().equals("MEDECIN")) {
            throw new IllegalArgumentException("L'utilisateur mis à jour doit être médecin");
        }
        String password = utilisateur.getPassword();
        return utilisateurService.updatePassword(id, password);
    }

    // Supprimer un utilisateur (superadmin)
    @DeleteMapping(path = "/api/utilisateur/{id}")
    @PreAuthorize("hasRole('SUPERADMIN')")
    @Transactional
    public void delete(@PathVariable(name = "id") Integer id) throws UtilisateurNotFoundException {
        utilisateurService.delete(id);
    }
 

}

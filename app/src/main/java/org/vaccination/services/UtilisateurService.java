package org.vaccination.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.vaccination.entities.Utilisateur;
import org.vaccination.repositories.UtilisateurRepository;

@Service
public class UtilisateurService implements UserDetailsService {

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));

        return User.withUsername(utilisateur.getLogin())
                .password(utilisateur.getPassword())
                .roles(utilisateur.getRole()) // Convertir le rôle unique en tableau
                .build();
        
    }

    // public List<Utilisateur> findAdministrateurs() {
    //     return utilisateurRepository.findByRole("ADMIN");
    // }

    // public List<Utilisateur> findMedecins() {
    //     return utilisateurRepository.findByRole("MEDECIN");
    // }

    // public Utilisateur findOneAdministrateur(Integer id) {
    //     return utilisateurRepository.findOneByRoleAndId("ADMIN", id);
    // }

    // public Utilisateur findOneMedecin(Integer id) {
    //     return utilisateurRepository.findOneByRoleAndId("MEDECIN", id);
    // }

    // public Utilisateur createAdministrateur(Utilisateur utilisateur) {
    //     utilisateur.setRole("ADMIN");
    //     return utilisateurRepository.save(utilisateur);
    // }

    // public Utilisateur createMedecin(Utilisateur utilisateur) {
    //     utilisateur.setRole("MEDECIN");
    //     return utilisateurRepository.save(utilisateur);
    // }

    // public Utilisateur updateAdministrateur(Integer id, Utilisateur utilisateur) {
    //     Utilisateur utilisateurExistant = utilisateurRepository.findOneByRoleAndId("ADMIN", id);
        
    //     if (utilisateur.getNom() != null) {
    //         utilisateurExistant.setNom(utilisateur.getNom()); 
    //     }

    //     if (utilisateur.getPrenom() != null) {
    //         utilisateurExistant.setPrenom(utilisateur.getPrenom());
    //     }

    //     if (utilisateur.getAdresse() != null) {
    //         utilisateurExistant.setAdresse(utilisateur.getAdresse());
    //     }

    //     if (utilisateur.getVille() != null) {
    //         utilisateurExistant.setVille(utilisateur.getVille());
    //     }

    //     if (utilisateur.getEmail() != null) {
    //         utilisateurExistant.setEmail(utilisateur.getEmail());
    //     }

    //     if (utilisateur.getTelephone() != null) {
    //         utilisateurExistant.setTelephone(utilisateur.getTelephone());
    //     }

    //     if (utilisateur.getCentre() != null) {
    //         utilisateurExistant.setCentre(utilisateur.getCentre());
    //     }

    //     return utilisateurRepository.save(utilisateurExistant);
    // }

    // public Utilisateur updateMedecin(Integer id, Utilisateur utilisateur) {
    //     Utilisateur utilisateurExistant = utilisateurRepository.findOneByRoleAndId("MEDECIN", id);
        
    //     if (utilisateur.getNom() != null) {
    //         utilisateurExistant.setNom(utilisateur.getNom()); 
    //     }

    //     if (utilisateur.getPrenom() != null) {
    //         utilisateurExistant.setPrenom(utilisateur.getPrenom());
    //     }

    //     if (utilisateur.getAdresse() != null) {
    //         utilisateurExistant.setAdresse(utilisateur.getAdresse());
    //     }

    //     if (utilisateur.getVille() != null) {
    //         utilisateurExistant.setVille(utilisateur.getVille());
    //     }

    //     if (utilisateur.getEmail() != null) {
    //         utilisateurExistant.setEmail(utilisateur.getEmail());
    //     }

    //     if (utilisateur.getTelephone() != null) {
    //         utilisateurExistant.setTelephone(utilisateur.getTelephone());
    //     }

    //     if (utilisateur.getCentre() != null) {
    //         utilisateurExistant.setCentre(utilisateur.getCentre());
    //     }

    //     return utilisateurRepository.save(utilisateurExistant);
    // }

    // public void deleteAdministrateur(Integer id) {
    //     utilisateurRepository.deleteById(id);
    // }

    // public void deleteMedecin(Integer id) {
    //     utilisateurRepository.deleteById(id);
    // }
}

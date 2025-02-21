package org.vaccination.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.vaccination.entities.Utilisateur;
import org.vaccination.exceptions.UtilisateurNotFoundException;
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

    public List<Utilisateur> findAllByNomAndRole(String nom, String role) {
        if (nom == null || nom == "") {
            return utilisateurRepository.findByRole(role);
        }
        else {
            return utilisateurRepository.findByNomAndRole(nom, role);
        }
        
    }

    public Utilisateur findOneByIdAndRole(Integer id, String role) throws UtilisateurNotFoundException {
        return utilisateurRepository.findOneByIdAndRole(id, role);
    }

    public Utilisateur create(Utilisateur utilisateur) {
        String password = utilisateur.getPassword();
        utilisateur.setPassword(new BCryptPasswordEncoder().encode(password));
        return utilisateurRepository.save(utilisateur);
    }

    public Utilisateur update(Integer id, Utilisateur utilisateur) throws UtilisateurNotFoundException {
        Utilisateur utilisateurExistant = utilisateurRepository.findOneById(id);
        
        if (utilisateur.getNom() != null) {
            utilisateurExistant.setNom(utilisateur.getNom()); 
        }

        if (utilisateur.getPrenom() != null) {
            utilisateurExistant.setPrenom(utilisateur.getPrenom());
        }

        if (utilisateur.getAdresse() != null) {
            utilisateurExistant.setAdresse(utilisateur.getAdresse());
        }

        if (utilisateur.getVille() != null) {
            utilisateurExistant.setVille(utilisateur.getVille());
        }

        if (utilisateur.getEmail() != null) {
            utilisateurExistant.setEmail(utilisateur.getEmail());
        }

        if (utilisateur.getTelephone() != null) {
            utilisateurExistant.setTelephone(utilisateur.getTelephone());
        }

        if (utilisateur.getCentre() != null) {
            utilisateurExistant.setCentre(utilisateur.getCentre());
        }

        if (utilisateur.getLogin() != null) {
            utilisateurExistant.setLogin(utilisateur.getLogin());
        }

        return utilisateurRepository.save(utilisateurExistant);
    }

    public Utilisateur updatePassword(Integer id, String password) {
        Utilisateur utilisateurExistant = utilisateurRepository.findOneById(id);
        utilisateurExistant.setPassword(new BCryptPasswordEncoder().encode(password));
        return utilisateurRepository.save(utilisateurExistant);
    }

    public void delete(Integer id) {
        utilisateurRepository.deleteById(id);
    }

}

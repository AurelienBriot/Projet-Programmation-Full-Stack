package org.vaccination.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaccination.entities.Centre;
import org.vaccination.exceptions.CentreNotFoundException;
import org.vaccination.repositories.CentreRepository;


@Service
public class CentreService {
    @Autowired
    private CentreRepository centerRepository;
    
    public CentreService() {
        
    }

    public List<Centre> findAll() {
        return centerRepository.findAll();
    }

    public List<Centre> findAllByVille(String ville) {
        if(ville == null || ville.isEmpty()) {
            return centerRepository.findAll();
        }
        else {
            return centerRepository.findAllByVilleContainingIgnoringCase(ville);
        }
        
    }

    public Centre findOneById(Integer id) throws CentreNotFoundException {
        return centerRepository.findOneById(id); 
    }

    public Centre create(Centre center) {
        return centerRepository.save(center);
    }

    public Centre update(Integer id, Centre centreMaj) throws CentreNotFoundException {
        Centre centreExistant = findOneById(id);
        if (centreExistant == null) {
            throw new CentreNotFoundException();
        }

        if(centreMaj.getNom() != null) {
            centreExistant.setNom(centreMaj.getNom());
        }

        if(centreMaj.getAdresse() != null) {
            centreExistant.setAdresse(centreMaj.getAdresse());

        }

        if(centreMaj.getVille() != null) {
            centreExistant.setVille(centreMaj.getVille());

        }

        if(centreMaj.getAdministrateur() != null) {
            centreExistant.setAdministrateur(centreMaj.getAdministrateur());
        }

        return centerRepository.save(centreExistant);
    }

    public void delete(Integer id) throws CentreNotFoundException {
        centerRepository.deleteById(id);

    }

    // public void addAdmin(Integer id, User user) {
    //     this.centers.stream()
    //     .filter(c -> c.getId().equals(id))
    //     .findFirst()
    //     .ifPresent(c -> c.setAdministrateur(user));
        
    // }

    // public void deleteAdmin(Integer id) {
    //     this.centers.stream()
    //     .filter(c -> c.getId().equals(id))
    //     .findFirst()
    //     .ifPresent(c -> c.setAdministrateur(null));    

    // }

    // public void updateAdresse(Integer id, String adresse) {
    //     this.centers.stream()
    //     .filter(c -> c.getId().equals(id))
    //     .findFirst()
    //     .ifPresent(c -> c.setAdresse(adresse));
    // }

    // public void updateVille(Integer id, String ville) {
    //     this.centers.stream()
    //     .filter(c -> c.getId().equals(id))
    //     .findFirst()
    //     .ifPresent(c -> c.setVille(ville));
    // }

    // public void addMedecin(Integer id, User m) {
    //     this.centers.stream()
    //     .filter(c -> c.getId().equals(id))
    //     .findFirst()
    //     .ifPresent(c -> c.addMedecins(m));
    // }

    // public void removeMedecin(Integer id, Integer userId) {
    //     this.centers.stream()
    //     .filter(c -> c.getId().equals(id))
    //     .findFirst()
    //     .ifPresent(c -> c.removeMedecin(userId));    
    // }

}

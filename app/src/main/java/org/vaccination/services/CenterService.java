package org.vaccination.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaccination.entities.Centre;
import org.vaccination.repositories.CenterRepository;

import jakarta.transaction.Transactional;

@Service
public class CenterService {
    @Autowired
    private CenterRepository centerRepository;
    
    public CenterService() {

    }

    public List<Centre> findAll() throws Exception {
        return centerRepository.findAll();
    }

    public List<Centre> findAllByVille(String ville) throws Exception {
        if(ville == null || ville.isEmpty()) {
            return centerRepository.findAll();
        }
        else {
            return centerRepository.findAllByVilleLikeIgnoringCase(ville);
        }
        
    }

    public Centre findOneById(Integer id) throws Exception {
        return centerRepository.findOneById(id); 
    }

    public Centre create(Centre center) {
        return centerRepository.save(center);
    }

    public Centre update(Integer id, Centre centreMaj) throws Exception {
        Centre centreExistant = findOneById(id);
        if (centreExistant == null) {
            throw new Exception();
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

    public void delete(Integer id) {
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

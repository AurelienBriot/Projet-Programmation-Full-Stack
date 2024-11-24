package org.vaccination;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CenterService {
    private List<Center> centers = new ArrayList<Center>();
    
    public CenterService() {
        this.centers.add(new Center(1, "Rue Jean Lamour", "Vandoeuvre-lès-Nancy", new ArrayList<User>(), new ArrayList<Timeslot>(), new User(1, "test", "test", "mail")));
        this.centers.add(new Center(2, "Rue Jean Lamour", "Nancy", new ArrayList<User>(), new ArrayList<Timeslot>(), new User(1, "test", "test", "mail")));
        this.centers.add(new Center(3, "Rue Jean Lamodsfdsur", "Nancy", new ArrayList<User>(), new ArrayList<Timeslot>(), new User(1, "test", "test", "mail")));
        this.centers.add(new Center(4, "Rue Jean Lamouffr", "Vandoeuvre-lès-Nancy", new ArrayList<User>(), new ArrayList<Timeslot>(), new User(1, "test", "test", "mail")));

    }

    public List<Center> findAll() throws Exception {
        return this.centers.stream().toList();
    }

    public List<Center> findAllByCity(String ville) throws Exception {
        return this.centers.stream().filter(c->c.getVille().equals(ville)).toList();
    }

    public Center findOneById(Integer id) throws Exception {
        return this.centers.stream()
        .filter(p->p.getId().equals(id))
        .findFirst()
        .orElseThrow(Exception::new);    
    }

    public void create(Center center) {
        this.centers.add(center);
    }

    public void delete(Integer id) {
        this.centers.removeIf(c->c.getId().equals(id));    

    }

    public void addAdmin(Integer id, User user) {
        this.centers.stream()
        .filter(c -> c.getId().equals(id))
        .findFirst()
        .ifPresent(c -> c.setAdministrateur(user));
        
    }

    public void deleteAdmin(Integer id) {
        this.centers.stream()
        .filter(c -> c.getId().equals(id))
        .findFirst()
        .ifPresent(c -> c.setAdministrateur(null));    

    }

    public void updateAdresse(Integer id, String adresse) {
        this.centers.stream()
        .filter(c -> c.getId().equals(id))
        .findFirst()
        .ifPresent(c -> c.setAdresse(adresse));
    }

    public void updateVille(Integer id, String ville) {
        this.centers.stream()
        .filter(c -> c.getId().equals(id))
        .findFirst()
        .ifPresent(c -> c.setVille(ville));
    }

    public void addMedecin(Integer id, User m) {
        this.centers.stream()
        .filter(c -> c.getId().equals(id))
        .findFirst()
        .ifPresent(c -> c.addMedecins(m));
    }

    public void removeMedecin(Integer id, Integer userId) {
        this.centers.stream()
        .filter(c -> c.getId().equals(id))
        .findFirst()
        .ifPresent(c -> c.removeMedecin(userId));    
    }

}

package org.vaccination;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="t_center")
public class Center {
    @Id
    private Integer id;
    private String nom;
    private String adresse;
    private String ville;

    @OneToMany
    private List<User> medecins;

    @OneToMany(mappedBy = "centre")
    private List<Timeslot> creneaux;

    @OneToOne
    private User adminstrateur;
    
    public Center() {

    }

    public Center(Integer id, String nom, String adresse, String ville, List<User> medecins, List<Timeslot> creneaux, User admin) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.ville = ville;
        this.medecins = medecins;
        this.creneaux = creneaux;
        this.adminstrateur = admin;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return this.nom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getAdresse() {
        return this.adresse;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getVille() {
        return this.ville;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setAdministrateur(User admin) {
        this.adminstrateur = admin;
    }

    public User getAdministrateur() {
        return this.adminstrateur;
    }

    public List<User> getMedecins() {
        return this.medecins;
    }

    public void addMedecins(User m) {
        this.medecins.add(m);
    }

    public void removeMedecin(Integer userId) {
        this.medecins.removeIf(m -> m.getId().equals(id));    
    }

}

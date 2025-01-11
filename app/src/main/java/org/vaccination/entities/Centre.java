package org.vaccination.entities;

import java.util.List;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;

import ch.qos.logback.classic.pattern.Util;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="t_center")
public class Centre {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String nom;
    private String adresse;
    private String ville;

    @OneToMany(mappedBy = "centre")
    private List<Utilisateur> medecins;

    @OneToMany(mappedBy = "centre")
    private List<Creneau> creneaux;

    @OneToOne
    private Utilisateur adminstrateur;
    
    public Centre() {

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

    public void setAdministrateur(Utilisateur admin) {
        this.adminstrateur = admin;
    }

    public Utilisateur getAdministrateur() {
        return this.adminstrateur;
    }

    public List<Utilisateur> getMedecins() {
        return this.medecins;
    }


}

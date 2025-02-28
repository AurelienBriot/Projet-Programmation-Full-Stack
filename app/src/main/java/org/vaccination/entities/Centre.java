package org.vaccination.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="t_centre")
public class Centre {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String nom;
    private String adresse;
    private String codePostal;
    private String ville;

    @OneToMany()
    @JoinTable(
        name = "t_centre_medecins",
        joinColumns = @JoinColumn(name = "centre_id"),
        inverseJoinColumns = @JoinColumn(name = "medecin_id")
    )
    private List<Utilisateur> medecins;

    @JsonIgnoreProperties("creneaux")
    @OneToMany(mappedBy = "centre")
    private List<Creneau> creneaux;

    @OneToOne
    @JoinColumn(name = "administrateur_id", unique = true)
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

    public void setCodePostal(String code) {
        this.codePostal = code;
    }

    public String getCodePostal() {
        return this.codePostal;
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

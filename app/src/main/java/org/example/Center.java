package org.example;

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
    private String adresse;
    private String ville;

    @OneToMany
    private List<User> medecins;

    @OneToMany(mappedBy = "centre")
    private List<Timeslot> creneaux;

    @OneToOne
    @JoinColumn(name = "id_administrateur",
    foreignKey = @ForeignKey(name="administrateur_fk"),
    nullable =  false)
    private User adminstrateur;
    
    public Center() {

    }

    public Center(Integer id, String adresse, String ville, List<User> medecins, List<Timeslot> creneaux) {
        this.id = id;
        this.adresse = adresse;
        this.ville = ville;
        this.medecins = medecins;
        this.creneaux = creneaux;
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

}

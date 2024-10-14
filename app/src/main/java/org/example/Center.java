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
@Table(name="center")
public class Center {
    @Id
    private Integer id;
    private String adresse;
    private String ville;

    @OneToMany
    private List<User> medecins;

    @OneToMany(mappedBy = "timeslot")
    private List<Timeslot> creneaux;

    @OneToOne
    @JoinColumn(name = "id_administrateur",
    foreignKey = @ForeignKey(name="administrateur_fk"),
    nullable =  false)
    private User adminstrateur;
    
}

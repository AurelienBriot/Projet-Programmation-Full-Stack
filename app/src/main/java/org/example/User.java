package org.example;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="user")
public class User {
    @Id
    private Integer id;

    private String nom;
    private String prenom;
    private String role;
    private String email;
    private String telephone;
    private String adresse;
    private String ville;

    @OneToMany(mappedBy = "timeslot")
    private List<Timeslot> creneaux;

    public User() {

    }


}

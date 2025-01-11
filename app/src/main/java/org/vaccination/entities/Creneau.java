package org.vaccination.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_creneau")
public class Creneau {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @ManyToOne
    @JoinColumn(name = "centre_id") 
    private Centre centre;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    private Integer jour;
    private Integer mois;
    private Integer annee;

    private Integer heure;
    private Integer minute;

    private Boolean estReserve;
    private Boolean estTermine;

    public Creneau() {

    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setCentre(Centre c) {
        this.centre = c;
    }

    public Centre getCentre() {
        return this.centre;
    }

    public void setPatient(Patient p) {
        this.patient = p;
    }

    public Patient getPatient() {
        return this.patient;
    }
    
    public void setEstReserve(Boolean estReserve) {
        this.estReserve = estReserve;
    }

    public Boolean getEstReserve() {
        return this.estReserve;
    }

    public void setEstTermine(Boolean estTermine) {
        this.estTermine = estTermine;
    }

    public Boolean getEstTermine() {
        return this.estTermine;
    }

    public Integer getJour() {
        return jour;
    }
    
    public void setJour(Integer jour) {
        this.jour = jour;
    }
    
    public Integer getMois() {
        return mois;
    }
    
    public void setMois(Integer mois) {
        this.mois = mois;
    }
    
    public Integer getAnnee() {
        return annee;
    }
    
    public void setAnnee(Integer annee) {
        this.annee = annee;
    }
    
    public Integer getHeure() {
        return heure;
    }
    
    public void setHeure(Integer heure) {
        this.heure = heure;
    }
    
    public Integer getMinute() {
        return minute;
    }
    
    public void setMinute(Integer minute) {
        this.minute = minute;
    }
}

package org.example;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_timeslot")
public class Timeslot {
    @Id
    private Integer id;

    @ManyToOne
    private Center centre;

    @ManyToOne
    private User patient;

    private Date date;
    private Boolean estReserve;
    private Boolean estTermine;

    public Timeslot() {

    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return this.date;
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
}

package org.example;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "timeslot")
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
}

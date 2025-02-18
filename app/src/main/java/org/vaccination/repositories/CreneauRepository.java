package org.vaccination.repositories;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vaccination.entities.Centre;
import org.vaccination.entities.Creneau;

public interface CreneauRepository extends JpaRepository<Creneau, Integer> {
    public Creneau findOneById(Integer id);
    public List<Creneau> findAllByCentreAndDate(Centre c, LocalDate d);
}

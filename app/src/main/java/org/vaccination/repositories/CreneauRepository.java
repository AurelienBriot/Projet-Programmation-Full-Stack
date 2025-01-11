package org.vaccination.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vaccination.entities.Creneau;

public interface CreneauRepository extends JpaRepository<Creneau, Integer> {
    public Creneau findOneById(Integer id);
}

package org.vaccination.repositories;

import org.springframework.stereotype.Repository;
import org.vaccination.entities.Centre;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CenterRepository extends JpaRepository<Centre, Integer> {
    public List<Centre> findAllByVilleLikeIgnoringCase(String ville);

    public Centre findOneById(Integer id);


}

package org.vaccination;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CenterRepository extends JpaRepository<Center, Integer> {
    public List<Center> findAllByVilleLikeIgnoringCase(String ville);

    public Center findOneById(Integer id);


}

package org.vaccination.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vaccination.entities.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

    public List<Utilisateur> findByRole(String role);

    public Utilisateur findOneByRoleAndId(String role, Integer id);

}

package org.vaccination.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.vaccination.entities.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    // public List<Utilisateur> findByRole(String role);

    // public Utilisateur findOneByRoleAndId(String role, Integer id);

    public Optional<Utilisateur> findByLogin(String login);

    public List<Utilisateur> findByRole(String role);

    public List<Utilisateur> findByNomAndRole(String nom, String string);

    public Utilisateur findOneByIdAndRole(Integer id, String string);

    public Utilisateur findOneById(Integer id);

    public void deleteById(Integer id);

}

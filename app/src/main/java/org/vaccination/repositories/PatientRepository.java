package org.vaccination.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vaccination.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    public Patient findOneById(Integer id);
    
    public List<Patient> findAllByNomLikeIgnoringCase(String nom);

    public List<Patient> findAllByPrenomLikeIgnoringCase(String prenom);

    public List<Patient> findAllByNomLikeIgnoringCaseAndPrenomLikeIgnoringCase(String nom, String prenom);
}

package org.simplon.permisdrive.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import org.simplon.permisdrive.models.entities.Candidat;

@Repository
public interface ICandidatRepository extends JpaRepository<Candidat, Long>{

}

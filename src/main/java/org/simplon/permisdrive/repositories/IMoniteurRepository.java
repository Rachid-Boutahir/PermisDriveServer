package org.simplon.permisdrive.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

import org.simplon.permisdrive.models.entities.Moniteur;


@Repository
public interface IMoniteurRepository extends JpaRepository<Moniteur, Long> {


    List<Moniteur> findByDeletedFalse();

    Optional<Moniteur> findByMoniteurIdAndDeletedFalse(long moniteurId);



}

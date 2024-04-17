package org.simplon.permisdrive.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

import org.simplon.permisdrive.models.entities.Administrateur;
public interface IAdministrateurRepository
        extends JpaRepository<Administrateur, Long>, PagingAndSortingRepository<Administrateur, Long> {

    long countByDeletedFalse();



    Page<Administrateur> findByDeletedFalse(Pageable pageable);

    Optional<Administrateur> findByAdministrateurIdAndDeletedFalse(long moniteurId);


}

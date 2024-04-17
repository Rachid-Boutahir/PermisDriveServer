package org.simplon.permisdrive.repositories;

import org.simplon.permisdrive.models.entities.Sujet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISujetRepository extends JpaRepository<Sujet, Long> {
    long count();
}

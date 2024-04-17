package org.simplon.permisdrive.repositories;

import org.simplon.permisdrive.models.entities.Cours;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICoursRepository extends JpaRepository<Cours, Long> {
}

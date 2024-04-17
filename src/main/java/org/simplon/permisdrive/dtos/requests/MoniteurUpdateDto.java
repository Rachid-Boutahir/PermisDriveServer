package org.simplon.permisdrive.dtos.requests;

import lombok.Value;
import org.simplon.permisdrive.models.enums.Sexe;
import org.simplon.permisdrive.models.enums.TypeMoniteurs;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link org.simplon.permisdrive.models.entities.Moniteur}
 */
@Value
public class MoniteurUpdateDto implements Serializable {
    String nom;
    String prenom;
    Sexe sexe;
    String adresse;
    String ville;
    String telephone;
    String photo;
    String nomUtilisateur;
    String email;
    String motsDePasse;
    TypeMoniteurs typeMoniteur;
    LocalDate dateRejoindre;
    double salaire;
}

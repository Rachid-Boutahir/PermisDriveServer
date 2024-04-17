package org.simplon.permisdrive.dtos.requests;


import lombok.Value;
import org.simplon.permisdrive.models.enums.Sexe;
import org.simplon.permisdrive.models.enums.TypeMoniteurs;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link org.simplon.permisdrive.models.entities.Moniteur}
 */
@Value
public class MoniteurRequestDto implements Serializable {

    @NotNull(message = "le nom ne doit pas être null !")
    @Size(min = 3, max = 50)
    String nom;

    @NotNull(message = "le prenom ne doit pas être null !")
    @Size(min = 3, max = 50)
    String prenom;

    @NotNull(message = "le sexe ne doit pas être null !")
    Sexe sexe;

    String adresse;

    @NotNull(message = "le ville ne doit pas être null !")
    String ville;

    String telephone;

    String photo;

    @NotNull(message = "le nomUtilisateur ne doit pas être null !")
    String nomUtilisateur;

    @NotNull(message = "le Email ne doit pas être null !")
    @Email(message = "L'Email doit être un Email bien formaté !")
    String email;

    @NotNull(message = "le motsDePasse ne doit pas être null !")
    String motsDePasse;

    @NotNull(message = "le typeMoniteur ne doit pas être null !")
    TypeMoniteurs typeMoniteur;

    LocalDate dateRejoindre;

    double salaire;
}

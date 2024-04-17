package org.simplon.permisdrive.dtos.requests;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.simplon.permisdrive.models.enums.Sexe;

import javax.validation.constraints.Email;
import java.io.Serializable;

/**
 * DTO for {@link org.simplon.permisdrive.models.entities.Administrateur}
 */
@NoArgsConstructor
@Data
public class AdministrateurUpdateDto implements Serializable {
    String nom;
    String prenom;
    Sexe sexe;
    String adresse;
    String ville;
    String telephone;
    String photo;
    String nomUtilisateur;
    @Email(message = "L'Email doit être un Email bien formaté !")
    String email;
}

package org.simplon.permisdrive.models.entities;


import lombok.*;

import org.simplon.permisdrive.models.enums.Sexe;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;


@Getter
@Setter
@ToString
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public abstract class Personne {

    @Size(min = 3, max = 50)
    @Column(name = "nom", length = 50)
    @NotNull(message = "le nom ne doit pas être null !")
    private String nom;

    @Size(min = 3, max = 50)
    @Column(name = "prenom", length = 50)
    @NotNull(message = "le prenom ne doit pas être null !")
    private String prenom;

    @Transient
    private String fullName;

    @Enumerated(EnumType.STRING)
    @Column(name = "sexe", length = 10)
    @NotNull(message = "le sexe ne doit pas être null !")
    private Sexe sexe;

    @Column(name = "adresse", length = 100)
    private String adresse;

    @Column(name = "ville", length = 50)
    @NotNull(message = "le ville ne doit pas être null !")
    private String ville;

    @Column(name = "telephone", length = 20)
    private String telephone;

    @Column(name = "photos", length = 50)
    private String photo;

    //TODO:FAIRE UNE METHODE QUI ENVOYER TRUE SI LE MONITEUR A ETE SUPPRIMER
    @Column(name = "is_deleted")
    private boolean deleted;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @Column(name = "deleted_at")
    private LocalDate deletedAt;
}
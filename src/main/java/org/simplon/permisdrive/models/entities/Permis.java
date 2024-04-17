package org.simplon.permisdrive.models.entities;

import lombok.*;
import javax.validation.constraints.NotNull;


import java.util.Set;
import java.util.LinkedHashSet;


import org.simplon.permisdrive.models.enums.TypePermis;

import javax.persistence.*;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "permis")
public class Permis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permis_id", nullable = false)
    private Long permisId;

    //TODO: Rappeler ayoub pour l'enummeration , et comment differencier les permis qui la eux deja "permisEnCours"
    @Enumerated(EnumType.STRING)
    @Column(name = "type_permis", length = 5)
    @NotNull(message = "le TypePermis ne doit pas être null !")
    private TypePermis typePermis;

    private String description;

    @ManyToMany(mappedBy = "permis")
    @ToString.Exclude
    private Set<Candidat> candidats = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "permis")
    @ToString.Exclude
    private Set<Moniteur> moniteurs = new LinkedHashSet<>();

}

package org.simplon.permisdrive.models.entities;

import lombok.*;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "administrateurs")
public class Administrateur extends Personne {
    @ToString.Exclude
    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "administrateur_id", nullable = false)
    private Long administrateurId;

}

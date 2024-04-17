package org.simplon.permisdrive.models.entities;

import lombok.*;



import java.util.Set;
import java.time.LocalDate;
import java.util.LinkedHashSet;

import org.simplon.permisdrive.models.enums.TypeMoniteurs;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "moniteurs")
public class Moniteur extends Personne{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "moniteur_id", nullable = false)
    private Long moniteurId;

    @ToString.Exclude
    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_moniteur", length = 20)
    @NotNull(message = "le typeMoniteur ne doit pas être null !")
    private TypeMoniteurs typeMoniteur;

    @Column(name = "date_rejoindre")
//    @NotNull(message = "la date de rejoin ne doit pas être null !")
    private LocalDate dateRejoindre;

    @Column(name = "salaire", length = 30, precision = 2)
    private double salaire;

    @ToString.Exclude
    @OneToMany(mappedBy = "moniteur", fetch = FetchType.LAZY)
    private Set<Cours> courses = new LinkedHashSet<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "moniteur", fetch = FetchType.LAZY)
    private Set<Planification> planifications = new LinkedHashSet<>();

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "moniteurs_permis",
            joinColumns = @JoinColumn(name = "moniteur_id"),
            inverseJoinColumns = @JoinColumn(name = "permis_id"))
    private Set<Permis> permis = new LinkedHashSet<>();


}

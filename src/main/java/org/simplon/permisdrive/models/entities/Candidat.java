package org.simplon.permisdrive.models.entities;

import lombok.*;



import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

import org.simplon.permisdrive.models.enums.TypeSanguins;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "candidats")
public class Candidat extends Personne{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candidat_id", nullable = false)
    private Long candidatId;

    @Column(name = "date_rejoindre")
    private LocalDate dateJointures;

    @Enumerated(EnumType.STRING)
    @Column(name = "groupe_sanguin", length = 5)
    private TypeSanguins groupeSanguin;

    @ToString.Exclude
    @OneToMany(mappedBy = "candidat", fetch = FetchType.LAZY)
    private Set<Planification> planifications = new LinkedHashSet<>();

    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paiment_id")
    private Paiment paiment;

    @ToString.Exclude
    @OneToMany(mappedBy = "candidat", fetch = FetchType.LAZY)
    private Set<Reclamation> reclamations = new LinkedHashSet<>();

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "candidats_permis",
            joinColumns = @JoinColumn(name = "candidat_candidat_id"),
            inverseJoinColumns = @JoinColumn(name = "permis_permis_id"))
    private Set<Permis> permis = new LinkedHashSet<>();

    @ToString.Exclude
    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

}

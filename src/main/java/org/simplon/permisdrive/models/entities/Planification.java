package org.simplon.permisdrive.models.entities;


import lombok.*;


import org.simplon.permisdrive.models.enums.StatutPlanification;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "planifications")
public class Planification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "planification_id", nullable = false)
    private Long planificationId;

    @Size(min = 5, message = "le nom d'evenement ne doit doit contenir ")
    @Column(name = "nom_evenement", length = 50)
    @NotNull(message = "le nom d'evenement ne doit pas être null !")
    private String nomEvenement;

    @Column(name = "date_ajout")
    private LocalDate datePlanification;

    @Column(name = "heur_debut")
    private LocalTime heurDebut;

    @Column(name = "heur_fin")
    private LocalTime heurFin;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut_planification", length = 20)
    private StatutPlanification statutPlanification;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "candidat_id")
    private Candidat candidat;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "moniteur_id")
    private Moniteur moniteur;

}

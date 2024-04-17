package org.simplon.permisdrive.models.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Table(name = "reclamations")
public class Reclamation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reclamation_id", nullable = false)
    private Long reclamationId;

    @Column(name = "objet", length = 40)
    @Size(min = 5, max = 40, message = "l'objet doit contenir au moin 5 caractère")
    @NotNull(message = "l'objet ne doit pas être null !")
    private String objet;

    @Size(min = 5, max = 40, message = "la description doit contenir au moin 5 caractère")
    @NotNull(message = "la description ne doit pas être null !")
    private String description;

    @Column(name = "date_ajout")
    private LocalDate dateAjout;

    @ManyToOne
    @JoinColumn(name = "candidat_id")
    private Candidat candidat;

}

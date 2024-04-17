package org.simplon.permisdrive.models.entities;

import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cours")
public class Cours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cours_id", nullable = false)
    private Long coursId;

    @Column(name = "lesson")
    @NotNull(message = "la lesson ne doit pas être nul !")
    private String lesson;

    @Column(name = "prix", precision = 2)
    @NotNull(message = "le prix ne doit pas être nul !")
    private Double prix;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "moniteur_id")
    private Moniteur moniteur;


    @ToString.Exclude
    @OneToMany(mappedBy = "cours", fetch = FetchType.EAGER)
    private Set<Sujet> sujets = new LinkedHashSet<>();

}

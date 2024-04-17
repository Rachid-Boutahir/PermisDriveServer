package org.simplon.permisdrive.models.entities;

import lombok.*;


import java.time.LocalDate;

import org.simplon.permisdrive.models.entities.Candidat;
import org.simplon.permisdrive.models.enums.MethodePaiement;

import javax.persistence.*;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "paiments")
public class Paiment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paiment_id", nullable = false)
    private Long paimentId;

    //TODO:AJOUTER UNE METHODE AU NIVEAU PACKAGE UTIL QUI GENERE LE NUMERO DE PAIMENT AUTOMATIQUE
    @Column(name = "numero_paiment", length = 50)
    private String numeroPaiment;

    @Column(name = "date_paiment")
    private LocalDate datePaiment;

    //TODO:FAIRE UNE METHODE QUI VERIFIER LE TYPE DE PERMIS ET ENVOYER LE MONTANT AUTOMATIQUEMENT
    @Column(name = "montant", precision = 2)
    private Double montant;

    @Column(name = "avance", precision = 2)
    private Double avance;

    @Column(name = "reste", precision = 2)
    private Double reste;

    @Column(name = "isPaye")
    private Boolean isPaye;

    @Enumerated(EnumType.STRING)
    @Column(name = "methode_paiement")
    private MethodePaiement methodePaiement;

    @OneToOne(mappedBy = "paiment")
    private Candidat candidat;

}

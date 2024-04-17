package org.simplon.permisdrive.models.enums;

public enum StatutPlanification {
    PREVU("Prévu"),
    TERMINE("Terminé"),
    ANNULE("Annulé");
    private final String valeur;

    StatutPlanification(String valeur) {
        this.valeur = valeur;
    }

    public String getValeur() {
        return valeur;
    }
}

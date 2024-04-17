package org.simplon.permisdrive.models.enums;

public enum MethodePaiement {
    CARTE("Carte de crédit"),
    CACHE("Espèce"),
    PAYPAL("PayPal"),
    VIREMENT("Virement bancaire");
    private final String valeur;

    MethodePaiement(String valeur) {
        this.valeur = valeur;
    }

    public String getValeur() {
        return valeur;
    }
}

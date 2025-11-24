package com.model;

public class Réceptionniste extends Personne{
    private String mot_de_passe;
    private char type_utilisateur = 'R';

    public Réceptionniste() {
        super();
    }
    public Réceptionniste(String nom, String prenom, int age, String sexe, String groupe_sanguin, String adresse, int tel) {
        super(nom, prenom, age, sexe, groupe_sanguin, adresse, tel);
    }
    public String getMot_de_passe() {
        return mot_de_passe;
    }
    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }
    public char getType_utilisateur() {
        return type_utilisateur;
    }
}

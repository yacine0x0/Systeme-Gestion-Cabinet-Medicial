package com.gestion_cabinet_medical.couche_metier.model;

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

    // ---- S'AUTHENTIFIER ---- //
    public boolean sAuthentifier(String nom_utilisateur, String mot_de_passe){
        //code d'authentification
        return true;
    }

    // ------------- GESTION DES RENDEZ-VOUS ------------- //
    
    public void nouveauRendezVous(){
        //code de creation de nouveau rendez-vous
    }

    public void supprimerRendezVous(){
        //code de suppression de rendez-vous
    }

    public void modifierRendezVous(){
        //code de modification de rendez-vous
    }

    public void afficherNbrRendezVous(){
        //code d'affichage du nombre de rendez-vous
    }

    public void afficherListeRendezVous(){
        //code d'affichage de la liste des rendez-vous
    }

    public void verifierDisponibiliteMedecin(){
        //code de verification de disponibilite du medecin
    }


}

package com.model;

public class Patient extends Personne {

    public Patient() {
        super();
    }

    public Patient(String nom, String prenom, int age, String sexe, String groupe_sanguin, String adresse, int tel) {
        super(nom, prenom, age, sexe, groupe_sanguin, adresse, tel);
    }

    // -------------Facture------------- //
    
    public void reglerTotalFacture(){
        //code de paiement de la facture
    }

    public void reglerFacture(){
        //code de reglement de la facture
    }

    // -------------Prise de Rendez-Vous------------- //
    public void prendreRendezVous(){
        //code de prise de rendez-vous
    }

    public void annulerRendezVous(){
        //code d'annulation de rendez-vous
    }

    public void remporterRendezVous(){
        //code de modification de rendez-vous
    }
}

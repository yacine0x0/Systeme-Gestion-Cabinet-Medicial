package com.gestion_cabinet_medical.couche_metier.model;

public class Medecin extends Personne {
    private String mot_de_passe;
    private final char type_utilisateur = 'M';

    public Medecin() {
        super();
    }

    public Medecin(String nom, String prenom, int age, String sexe, String groupe_sanguin, String adresse, int tel) {
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
    public boolean sAuthentifier(String nom_utilisateur, String mot_de_passe) {
        // code d'authentification
        return true;
    }

    // ------------- GESTION DES PATIENTS ------------- //
    public void AjoutPatient() {
        // code d'ajout patient
    }

    public void SupprimerPatient() {
        // code de suppression patient
    }

    public void ModifierPatient() {
        // code de modification patient
    }

    public void verifierPatientExiste() {
        // code de verification patient
    }

    public void AfficherNbrPatients() {
        // code d'affichage nombre de patients
    }

    public void AfficherListePatients() {
        // code d'affichage liste de patients
    }

    public void ConsulterDossierMedical() {
        // code de consultation dossier medical
    }

    // ------------- GESTION DES ORDONNANCES ------------- //

    public void CreerOrdonnance() {
        // code de creation ordonnance
    }

    public void supprimerOrdonnance() {
        // code de suppression ordonnance
    }

    public void modifierOrdonnance() {
        // code de modification ordonnance
    }

    public void afficherNbrOrdonnances() {
        // code d'affichage nombre d'ordonnances patient
    }

    public void afficherOrdonnance() {
        // code d'affichage ordonnances patient
    }

    public void imprimerOrdonnance() {
        // code d'impression ordonnance
    }

    // ------------- GESTION DES Employé ------------- //
    public void AjouterEmploye() {
        // code d'ajout employe
    }

    public void SupprimerEmploye() {
        // code de suppression employe
    }

    public void modifierEmploye() {
        // code de modification employe
    }

    public void afficherListeEmployes() {
        // code d'affichage liste employes
    }

    public void consulterProfilEmploye() {
        // code de consultation profil employe
    }

    // ------------- GESTION DES FACTURE ------------- //
    public void creerFacture() {
        // code de creation facture
    }

    public void supprimerFacture() {
        // code de suppression facture
    }

    public void modifierFacture() {
        // code de modification facture
    }

    public void afficherNbrFactures() {
        // code d'affichage nombre de factures
    }

    public void afficherListeFactures() {
        // code d'affichage liste de factures
    }

    public void suivrePaiementFacture() {
        // code de suivi de paiement de la facture
    }

    public void imprimerFacture() {
        // code d'impression facture
    }

}

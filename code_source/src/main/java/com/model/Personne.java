package com.model;

public class Personne {
    protected int    id_personne ;
    protected String nom;
    protected String prenom;
    protected int    age;
    protected String sexe;
    protected String Groupe_sanguin;
    protected String adresse;
    protected int    tel;
    protected static int countID = 0;
    
    public Personne() {
        countID++;
        this.id_personne = countID;
    }
    public Personne( String nom, String prenom, int age, String sexe, String groupe_sanguin, String adresse, int tel) {
        countID++;
        this.id_personne = countID;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.sexe = sexe;
        this.Groupe_sanguin = groupe_sanguin;
        this.adresse = adresse;
        this.tel = tel;
    }

    //getters
    public int getId_personne() {
        return id_personne;
    }
    public String getNom() {
        return nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public int getAge() {
        return age;
    }
    public String getSexe() {
        return sexe;
    }
    public String getGroupe_sanguin() {
        return Groupe_sanguin;
    }
    public String getAdresse() {
        return adresse;
    }
    public int getTel() {
        return tel;
    }

    //setters
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setSexe(String sexe) {
        this.sexe = sexe;
    }
    public void setGroupe_sanguin(String groupe_sanguin) {
        Groupe_sanguin = groupe_sanguin;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    public void setTel(int tel) {
        this.tel = tel;
    }
    
}

package com.gestion_cabinet_medical.couche_metier.model;

public class Personne {
    protected int    id_personne ;
    protected String nom;
    protected String prenom;
    protected String dateNaissance;
    protected int   age;
    protected String sexe;
    protected String Groupe_sanguin;
    protected String adresse;
    protected String    tel;
    protected static int countID = 0;
    
    public Personne() {
        countID++;
        this.id_personne = countID;
    }
    public Personne( String nom, String prenom, String dateNaissance ,int age, String sexe, String groupe_sanguin, String adresse, String tel) {
        countID++;
        this.id_personne = countID;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
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
    public String getdateNaissance(){
        return dateNaissance;
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
    public String getTel() {
        return tel;
    }

    //setters
    public void setID(int id){
        this.id_personne = id;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public void setdateNaissance(String date){
        this.dateNaissance = date;
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
    public void setTel(String tel) {
        this.tel = tel;
    }
    
}

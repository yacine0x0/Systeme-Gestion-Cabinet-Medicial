package com.gestion_cabinet_medical.couche_metier.model;

public class Rendezvous {
    private int num_rdv;
    private String date_prise;
    private String date_passage;
    private String heure_passage;
    private static int countRDV = 0;

    public Rendezvous(){
        countRDV++;
        this.num_rdv = countRDV;
        this.date_prise = "";
        this.date_passage = "";
        this.heure_passage = "";
    }
    public Rendezvous(String date_prise, String date_passage, String heure_passage){
        countRDV++;
        this.num_rdv = countRDV;
        this.date_prise = date_prise;
        this.date_passage = date_passage;
        this.heure_passage = heure_passage;
    }


    //getters
    public int getNum_rdv() {
        return num_rdv;
    }
    public String getDate_prise() {
        return date_prise;
    }
    public String getDate_passage() {
        return date_passage;
    }
    public String getHeure_passage() {
        return heure_passage;
    } 

    //setters
    public void setDate_prise(String date_prise) {
        this.date_prise = date_prise;
    }
    public void setDate_passage(String date_passage) {
        this.date_passage = date_passage;   
    }
    public void setHeure_passage(String heure_passage) {
        this.heure_passage = heure_passage;
    }


    //-------HISTORIQUE-------//

    

}

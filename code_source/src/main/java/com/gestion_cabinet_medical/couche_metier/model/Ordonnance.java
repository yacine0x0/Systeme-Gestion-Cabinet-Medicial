package com.gestion_cabinet_medical.couche_metier.model;

public class Ordonnance {
    private int num_ordonnance;
    private String date_ordonnance;
    private String details_ordonnance;
    private static int countOrdonnance = 0;

    public Ordonnance(){
        countOrdonnance++;
        this.num_ordonnance = countOrdonnance;
        date_ordonnance = "";
        details_ordonnance = "";
    }

    public Ordonnance(String date_ordonnance, String details_ordonnance){
        countOrdonnance++;
        this.num_ordonnance = countOrdonnance;
        this.date_ordonnance = date_ordonnance;
        this.details_ordonnance = details_ordonnance;
    }

    //getters
    public int getNum_ordonnance() {   
        return num_ordonnance;
    }
    public String getDate_ordonnance() {
        return date_ordonnance;
    }
    public String getDetails_ordonnance() {
        return details_ordonnance;
    }

    //setters
    public void setDate_ordonnance(String date_ordonnance) {
        this.date_ordonnance = date_ordonnance;
    }
    public void setDetails_ordonnance(String details_ordonnance) {
        this.details_ordonnance = details_ordonnance;
    }
    
}

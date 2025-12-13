package com.gestion_cabinet_medical.couche_metier.model;

public class Facture {
    private int num_facture;
    private String date_fact;
    private String dateprévu;
    private double[] montantElementaire;
    private double montantTotal;
    private static int countFacture = 0;

    public Facture(){
        countFacture++;
        this.num_facture = countFacture;
        this.date_fact = "";
        this.dateprévu = "";
        this.montantElementaire = new double[0];
        this.montantTotal = 0.0;
    }
    public Facture(String date_fact, String dateprévu, double[] montantElementaire){
        countFacture++;
        this.num_facture = countFacture;
        this.date_fact = date_fact;
        this.dateprévu = dateprévu;
        this.montantElementaire = montantElementaire;
        this.montantTotal = calculerMontantTotal(montantElementaire);
    }

    private static double calculerMontantTotal(double[] montantElementaire) {
        double total = 0.0;
        for (double montant : montantElementaire) {
            total += montant;
        }
        return total;
    }
  

    //getters
    public int getNum_facture() {
        return num_facture;
    }
    public String getDate_fact() {
        return date_fact;
    }
    public String getDateprévu() {
        return dateprévu;
    }
    public double[] getMontantElementaire() {
        return montantElementaire;
    }
    public double getMontantTotal() {
        return montantTotal;
    }

    //setters
    public void setDate_fact(String date_fact) {
        this.date_fact = date_fact;
    }
    public void setDateprévu(String dateprévu) {
        this.dateprévu = dateprévu;
    }
    public void setMontantElementaire(double[] montantElementaire) {
        this.montantElementaire = montantElementaire;
        this.montantTotal = calculerMontantTotal(montantElementaire);
    }


    //-------HISTORIQUE-------//
    public static void HISTORIQUE(){
        //code historique
    }
}

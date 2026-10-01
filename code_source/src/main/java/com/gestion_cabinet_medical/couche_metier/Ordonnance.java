package com.gestion_cabinet_medical.couche_metier;

import com.gestion_cabinet_medical.couche_DAO.OrdonnanceDAO.Afficher_Ordonnance;
import com.gestion_cabinet_medical.couche_DAO.OrdonnanceDAO.Ajouter_Ordonnance;
import com.gestion_cabinet_medical.couche_DAO.OrdonnanceDAO.Supprimer_Ordonnance;

public class Ordonnance {
    Afficher_Ordonnance afficher = new Afficher_Ordonnance();
    Ajouter_Ordonnance ajouter = new Ajouter_Ordonnance();
    Supprimer_Ordonnance supprimer_Ordonnance = new Supprimer_Ordonnance();

    public boolean Ajouter_Ordonnance(String nommed,String prenommed,String patnom,String patprenom,String détails,String date ){
        return ajouter.ajouterOrdonnance(nommed,prenommed,patnom,patprenom,détails,date);
    }

    public boolean Supprimer_Ordonnance(int num){
        return supprimer_Ordonnance.supprimerOrdonnance(num);
    }
    
}

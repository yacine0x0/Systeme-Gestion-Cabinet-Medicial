package com.gestion_cabinet_medical.couche_metier;

import com.gestion_cabinet_medical.couche_DAO.PatientDAO.Ajout_Patient_DAO;
import com.gestion_cabinet_medical.couche_DAO.PatientDAO.Afficher_Patient_DAO;
import com.gestion_cabinet_medical.couche_DAO.PatientDAO.Supprimer_Patient_DAO;
import com.gestion_cabinet_medical.couche_DAO.PatientDAO.Modifier_Patient_DAO;
import com.gestion_cabinet_medical.couche_DAO.PatientDAO.Rechercher_Patient_DAO;
import com.gestion_cabinet_medical.couche_metier.model.Personne;
import javax.swing.*;
import java.awt.*;

public class Patient {

    Ajout_Patient_DAO ajout = new Ajout_Patient_DAO();
    Afficher_Patient_DAO afficher = new Afficher_Patient_DAO();
    Supprimer_Patient_DAO supprimer = new Supprimer_Patient_DAO();
    Modifier_Patient_DAO modifier = new Modifier_Patient_DAO();
    Rechercher_Patient_DAO rechercher = new Rechercher_Patient_DAO();

    public boolean Ajouter_Patient(Personne personne){
            return ajout.ajoutPersonne(personne);
    }

    public Object[] Afficher_Patient(int id){
        return afficher.AfficherPatient(id);
    }

    public void Afficher_tout_Patient(JTable table){
        afficher.afficherTousPatients(table);
    }
    
    public boolean Supprimer_Patient(int id){
        return supprimer.supprimerPatient(id);
    }

    public boolean Modifier_Patient(Personne personne){
        return modifier.modifierPatient(personne);
    }


    public boolean RechercherSimple(String nom,String prenom){
        return rechercher.rechercheSimple(nom,prenom );
    }

    public boolean RechercherAvancée(Personne personne){
        return rechercher.rechercheAvancee(personne);
    }

}

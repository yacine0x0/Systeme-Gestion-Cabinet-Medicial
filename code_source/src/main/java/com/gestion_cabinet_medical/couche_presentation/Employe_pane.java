package com.gestion_cabinet_medical.couche_presentation;

import javax.swing.*;
import java.awt.*;

public class Employe_pane extends JTabbedPane{
    private JPanel Affichage_patient,total_panel,ajout_employé,recherche_employé;
    private JLabel Total_patient;
    public Employe_pane(){
            Affichage_patient = new JPanel();
        total_panel = new JPanel();
        ajout_employé = new JPanel();
        recherche_employé = new JPanel();
        Total_patient = new JLabel();
        
        this.addTab("Ajout/Suppression", ajout_employé);

        this.addTab("Recherche", recherche_employé);


    }

}

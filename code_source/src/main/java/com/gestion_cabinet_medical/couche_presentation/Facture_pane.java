package com.gestion_cabinet_medical.couche_presentation;

import javax.swing.*;
import java.awt.*;

public class Facture_pane extends JTabbedPane{
     private JPanel Affichage_patient,total_panel,ajout_patient,recherche_patient;
     private JLabel Total_patient;
    public Facture_pane(){

            Affichage_patient = new JPanel();
        total_panel = new JPanel();
        ajout_patient = new JPanel();
        recherche_patient = new JPanel();
        Total_patient = new JLabel();

         Affichage_patient.setLayout(new BorderLayout());

        this.addTab("Affichage", Affichage_patient);
        Total_patient.setFont(new Font("Liberation Sans", Font.BOLD, 15));
        Total_patient.setText("Total : ");
        total_panel.add(Total_patient);
        Affichage_patient.add(total_panel, BorderLayout.NORTH);
        


    }

}

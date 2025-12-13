package com.gestion_cabinet_medical.couche_presentation;

import javax.swing.*;
import  java.awt.*;

public class Ordonnance_pane extends JTabbedPane {
     private JPanel Affichage_patient,total_panel,ajout_patient,recherche_patient;
    private JTable Patient_affichage_table;
    private JScrollPane affichage_table_scroll;
    private JLabel Total_patient;
     private JButton modification_button;
    private JTextField nom_ajout_field;
    private JLabel nom_ajout_label;
    private JTextField prenom_ajout_field;
    private JLabel prenom_ajout_label;
    private JButton rechercher_button;
    private JTextField sexe_ajout_field;
    private JLabel sexe_ajout_label;
    private JButton suppression_button;
    private JTextField tel_ajout_field;
    private JLabel tel_ajout_label;

    private JTextField adresse_ajout_field;
    private JTextField adresse_ajout_field1;
    private JLabel adresse_ajout_label;
    private JLabel adresse_ajout_label1;
    private JTextField age_ajout_field;
    private JTextField age_ajout_field1;
    private JLabel age_ajout_label;
    private JLabel age_ajout_label1;
    private JButton ajout_button;
    private JPanel ajout_patient1;
    private JTextField groupage_ajout_field;
    private JTextField groupage_ajout_field1;
    private JLabel groupage_ajout_label;
    private JLabel groupage_ajout_label1;
    
    public Ordonnance_pane(){

   Affichage_patient = new JPanel();
        total_panel = new JPanel();
        ajout_patient = new JPanel();
        recherche_patient = new JPanel();
        Patient_affichage_table = new JTable();
        affichage_table_scroll = new JScrollPane();
        Total_patient = new JLabel();

  Affichage_patient.setLayout(new BorderLayout());

        this.addTab("Affichage", Affichage_patient);


        this.addTab("Ajout/Suppression", ajout_patient);

        this.addTab("Recherche", recherche_patient);



    }
}

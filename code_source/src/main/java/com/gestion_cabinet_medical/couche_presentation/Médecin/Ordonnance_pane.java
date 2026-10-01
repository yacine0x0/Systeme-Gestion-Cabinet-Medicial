package com.gestion_cabinet_medical.couche_presentation.Médecin;

import com.gestion_cabinet_medical.couche_metier.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Ordonnance_pane extends JTabbedPane {
    private JPanel Affichage_ordonnance, total_panel, nouveau_ordonnance, recherche_ordonnance, ordonnance_type_panel;
    private JTable ordonnance_table, type_table;
    private JScrollPane affichage_table_scroll, type_table_scroll;
    private JLabel total_ordonnance_label, total_type_label;
    private JButton supprimerButton, modifierButton, ajouterButton;
    private JButton rechercheSimpleButton, rechercheAvanceButton;
    private JButton type_modifierButton, type_supprimerButton, type_ajouterButton, type_afficherButton;
    
    // Nouveau Ordonnance - Médecin fields
    private JLabel medecin_nom_label, medecin_prenom_label;
    private JTextField medecin_nom_field, medecin_prenom_field;
    
    // Nouveau Ordonnance - Patient fields
    private JLabel patient_nom_label, patient_prenom_label, patient_datenaiss_label, 
                   patient_age_label, patient_sexe_label, patient_groupage_label;
    private JTextField patient_nom_field, patient_prenom_field, patient_age_field, 
                       patient_sexe_field, patient_groupage_field;
    private JSpinner patient_date_spinner;
    
    // Nouveau Ordonnance - Ordonnance fields
    private JLabel ordonnance_date_label;
    private JSpinner ordonnance_date_spinner;
    private JTextArea details_textarea;
    private JScrollPane details_scrollpane;
    
    // Recherche fields
    private JTextField recherche_medecin_nom_field, recherche_medecin_prenom_field;
    private JTextField recherche_patient_nom_field, recherche_patient_prenom_field;
    private JTextField recherche_patient_age_field, recherche_patient_sexe_field, recherche_patient_groupage_field;
    private JSpinner recherche_ordonnance_date_spinner;
    private JTextArea recherche_details_textarea;
    private JScrollPane recherche_details_scrollpane;
    
    private DefaultTableModel tableModel, typeTableModel;

    public Ordonnance_pane() {
        // Initialize panels and components
        Affichage_ordonnance = new JPanel();
        total_panel = new JPanel();
        nouveau_ordonnance = new JPanel();
        recherche_ordonnance = new JPanel();
        ordonnance_type_panel = new JPanel();
        ordonnance_table = new JTable();
        type_table = new JTable();
        affichage_table_scroll = new JScrollPane();
        type_table_scroll = new JScrollPane();
        total_ordonnance_label = new JLabel();
        total_type_label = new JLabel();
        
        // Initialize buttons
        supprimerButton = new JButton("Supprimer");
        modifierButton = new JButton("Modifier");
        ajouterButton = new JButton("Ajouter Ordonnance");
        rechercheSimpleButton = new JButton("Rechercher (Simple)");
        rechercheAvanceButton = new JButton("Rechercher (Avancée)");
        
        // Initialize type buttons
        type_modifierButton = new JButton("Modifier");
        type_supprimerButton = new JButton("Supprimer");
        type_ajouterButton = new JButton("Ajouter");
        type_afficherButton = new JButton("Afficher");
        
        // Initialize labels and fields for Nouveau Ordonnance
        initNouveauOrdonnanceFields();
        
        // Initialize labels and fields for Recherche
        initRechercheFields();
        
        // Set up the table models
        tableModel = new DefaultTableModel(new Object[][] {},
            new String[] {
                "Num", "Médecin", "Patient", "Détails", "Date"
            }) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Make all cells non-editable
                return false;
            }
        };
        
        typeTableModel = new DefaultTableModel(new Object[][] {},
            new String[] {
                "numOrdonnance", "Type", "Détails"
            }) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Make all cells non-editable
                return false;
            }
        };

        // SET UP AFFICHAGE TAB
        setupAffichageTab();
        
        // SET UP NOUVEAU TAB
        setupNouveauTab();
        
        // SET UP RECHERCHE TAB
        setupRechercheTab();
        
        // SET UP ORDONNANCE TYPE TAB
        setupOrdonnanceTypeTab();

        this.addTab("Affichage", Affichage_ordonnance);
        this.addTab("Nouveau", nouveau_ordonnance);
        this.addTab("Recherche", recherche_ordonnance);
        this.addTab("Ordonnance type", ordonnance_type_panel);
    }
    
    private void initNouveauOrdonnanceFields() {
        // Médecin fields
        medecin_nom_label = new JLabel("Nom: ");
        medecin_prenom_label = new JLabel("Prénom: ");
        medecin_nom_field = new JTextField(15);
        medecin_prenom_field = new JTextField(15);
        
        // Patient fields
        patient_nom_label = new JLabel("Nom: ");
        patient_prenom_label = new JLabel("Prénom: ");
        patient_datenaiss_label = new JLabel("Date de Naissance: ");
        patient_age_label = new JLabel("Age: ");
        patient_sexe_label = new JLabel("Sexe: ");
        patient_groupage_label = new JLabel("Groupage: ");
        
        patient_nom_field = new JTextField(15);
        patient_prenom_field = new JTextField(15);
        patient_age_field = new JTextField(15);
        patient_sexe_field = new JTextField(15);
        patient_groupage_field = new JTextField(15);
        
        // Patient date spinner
        LocalDate defaultPatientDate = LocalDate.now().minusYears(20);
        SpinnerDateModel patientDateModel = new SpinnerDateModel(
            java.sql.Date.valueOf(defaultPatientDate),
            null,
            null,
            java.util.Calendar.DAY_OF_MONTH
        );
        patient_date_spinner = new JSpinner(patientDateModel);
        JSpinner.DateEditor patientDateEditor = new JSpinner.DateEditor(patient_date_spinner, "dd/MM/yyyy");
        patient_date_spinner.setEditor(patientDateEditor);
        patient_date_spinner.setPreferredSize(new Dimension(120, 25));
        
        // Ordonnance date
        ordonnance_date_label = new JLabel("Date Ordonnance: ");
        SpinnerDateModel ordonnanceDateModel = new SpinnerDateModel(
            java.sql.Date.valueOf(LocalDate.now()),
            null,
            null,
            java.util.Calendar.DAY_OF_MONTH
        );
        ordonnance_date_spinner = new JSpinner(ordonnanceDateModel);
        JSpinner.DateEditor ordonnanceDateEditor = new JSpinner.DateEditor(ordonnance_date_spinner, "dd/MM/yyyy");
        ordonnance_date_spinner.setEditor(ordonnanceDateEditor);
        ordonnance_date_spinner.setPreferredSize(new Dimension(120, 25));
        
        // Détails text area
        details_textarea = new JTextArea(8, 30);
        details_textarea.setLineWrap(true);
        details_textarea.setWrapStyleWord(true);
        details_scrollpane = new JScrollPane(details_textarea);
        details_scrollpane.setBorder(BorderFactory.createTitledBorder("Détails"));
    }
    
    private void initRechercheFields() {
        // Recherche Médecin fields
        recherche_medecin_nom_field = new JTextField(15);
        recherche_medecin_prenom_field = new JTextField(15);
        
        // Recherche Patient fields
        recherche_patient_nom_field = new JTextField(15);
        recherche_patient_prenom_field = new JTextField(15);
        recherche_patient_age_field = new JTextField(15);
        recherche_patient_sexe_field = new JTextField(15);
        recherche_patient_groupage_field = new JTextField(15);
        
        // Recherche Ordonnance date
        SpinnerDateModel rechercheDateModel = new SpinnerDateModel(
            java.sql.Date.valueOf(LocalDate.now()),
            null,
            null,
            java.util.Calendar.DAY_OF_MONTH
        );
        recherche_ordonnance_date_spinner = new JSpinner(rechercheDateModel);
        JSpinner.DateEditor rechercheDateEditor = new JSpinner.DateEditor(recherche_ordonnance_date_spinner, "dd/MM/yyyy");
        recherche_ordonnance_date_spinner.setEditor(rechercheDateEditor);
        recherche_ordonnance_date_spinner.setPreferredSize(new Dimension(120, 25));
        
        // Recherche Détails text area
        recherche_details_textarea = new JTextArea(5, 25);
        recherche_details_textarea.setLineWrap(true);
        recherche_details_textarea.setWrapStyleWord(true);
        recherche_details_scrollpane = new JScrollPane(recherche_details_textarea);
        recherche_details_scrollpane.setBorder(BorderFactory.createTitledBorder("Détails"));
    }
    
    private void setupAffichageTab() {
        Affichage_ordonnance.setLayout(new BorderLayout());
        
        // Configure the table
        ordonnance_table.setModel(tableModel);
        ordonnance_table.setFillsViewportHeight(true);
        ordonnance_table.setRowHeight(25);
        ordonnance_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ordonnance_table.setAutoCreateRowSorter(true);
        
        // Configure table header
        JTableHeader header = ordonnance_table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 12));
        header.setBackground(new Color(220, 220, 220));
        header.setReorderingAllowed(false);
        
        // Configure table appearance
        ordonnance_table.setFont(new Font("Arial", Font.PLAIN, 11));
        ordonnance_table.setGridColor(Color.LIGHT_GRAY);
        ordonnance_table.setShowGrid(true);
        ordonnance_table.setIntercellSpacing(new Dimension(1, 1));
        
        // Set column widths
        ordonnance_table.getColumnModel().getColumn(0).setPreferredWidth(60);   // Num
        ordonnance_table.getColumnModel().getColumn(1).setPreferredWidth(150);  // Médecin
        ordonnance_table.getColumnModel().getColumn(2).setPreferredWidth(150);  // Patient
        ordonnance_table.getColumnModel().getColumn(3).setPreferredWidth(250);  // Détails
        ordonnance_table.getColumnModel().getColumn(4).setPreferredWidth(100);  // Date
        
        // Add scroll pane to table
        affichage_table_scroll.setViewportView(ordonnance_table);
        affichage_table_scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        affichage_table_scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        affichage_table_scroll.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        
        Affichage_ordonnance.add(affichage_table_scroll, BorderLayout.CENTER);

        // Set up the total panel with buttons
        total_panel.setLayout(new BorderLayout());
        
        // Create left panel for total label
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        total_ordonnance_label.setFont(new Font("Arial", Font.BOLD, 15));
        total_ordonnance_label.setText("Total Ordonnances: 0");
        leftPanel.add(total_ordonnance_label);
        
        // Create right panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        buttonPanel.add(supprimerButton);
        buttonPanel.add(modifierButton);
        
        // Add components to total panel
        total_panel.add(leftPanel, BorderLayout.WEST);
        total_panel.add(buttonPanel, BorderLayout.EAST);
        total_panel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        Affichage_ordonnance.add(total_panel, BorderLayout.NORTH);
    }
    
    private void setupNouveauTab() {
        nouveau_ordonnance.setLayout(new BorderLayout());
        
        // Create title label
        JLabel nouveauTitleLabel = new JLabel("Nouvelle Ordonnance", SwingConstants.CENTER);
        nouveauTitleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        nouveauTitleLabel.setForeground(new Color(0, 70, 140));
        nouveauTitleLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 20, 0));
        
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.add(nouveauTitleLabel, BorderLayout.CENTER);
        nouveau_ordonnance.add(titlePanel, BorderLayout.NORTH);
        
        // Create main container panel
        JPanel mainContainer = new JPanel(new BorderLayout());
        mainContainer.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        
        // Create panel for Médecin and Patient side by side
        JPanel topContainer = new JPanel(new GridLayout(1, 2, 20, 0));
        
        // Médecin Panel
        JPanel medecinPanel = new JPanel(new GridBagLayout());
        medecinPanel.setBorder(BorderFactory.createTitledBorder("Médecin"));
        GridBagConstraints gMed = new GridBagConstraints();
        gMed.insets = new Insets(5, 10, 5, 10);
        gMed.anchor = GridBagConstraints.WEST;
        
        int medRow = 0;
        
        gMed.gridx = 0; gMed.gridy = medRow; gMed.weightx = 0.0; gMed.fill = GridBagConstraints.NONE;
        medecinPanel.add(medecin_nom_label, gMed);
        gMed.gridx = 1; gMed.fill = GridBagConstraints.NONE; gMed.weightx = 0.0;
        medecinPanel.add(medecin_nom_field, gMed);
        
        medRow++;
        gMed.gridx = 0; gMed.gridy = medRow;
        medecinPanel.add(medecin_prenom_label, gMed);
        gMed.gridx = 1;
        medecinPanel.add(medecin_prenom_field, gMed);
        
        // Patient Panel
        JPanel patientPanel = new JPanel(new GridBagLayout());
        patientPanel.setBorder(BorderFactory.createTitledBorder("Patient"));
        GridBagConstraints gPat = new GridBagConstraints();
        gPat.insets = new Insets(5, 10, 5, 10);
        gPat.anchor = GridBagConstraints.WEST;
        
        int patRow = 0;
        
        gPat.gridx = 0; gPat.gridy = patRow; gPat.weightx = 0.0; gPat.fill = GridBagConstraints.NONE;
        patientPanel.add(patient_nom_label, gPat);
        gPat.gridx = 1; gPat.fill = GridBagConstraints.NONE; gPat.weightx = 0.0;
        patientPanel.add(patient_nom_field, gPat);
        
        patRow++;
        gPat.gridx = 0; gPat.gridy = patRow;
        patientPanel.add(patient_prenom_label, gPat);
        gPat.gridx = 1;
        patientPanel.add(patient_prenom_field, gPat);
        
        patRow++;
        gPat.gridx = 0; gPat.gridy = patRow;
        patientPanel.add(patient_datenaiss_label, gPat);
        gPat.gridx = 1;
        patientPanel.add(patient_date_spinner, gPat);
        
        patRow++;
        gPat.gridx = 0; gPat.gridy = patRow;
        patientPanel.add(patient_age_label, gPat);
        gPat.gridx = 1;
        patientPanel.add(patient_age_field, gPat);
        
        patRow++;
        gPat.gridx = 0; gPat.gridy = patRow;
        patientPanel.add(patient_sexe_label, gPat);
        gPat.gridx = 1;
        patientPanel.add(patient_sexe_field, gPat);
        
        patRow++;
        gPat.gridx = 0; gPat.gridy = patRow;
        patientPanel.add(patient_groupage_label, gPat);
        gPat.gridx = 1;
        patientPanel.add(patient_groupage_field, gPat);
        
        // Wrap panels
        JPanel medecinWrapper = new JPanel(new BorderLayout());
        medecinWrapper.add(medecinPanel, BorderLayout.NORTH);
        
        JPanel patientWrapper = new JPanel(new BorderLayout());
        patientWrapper.add(patientPanel, BorderLayout.NORTH);
        
        topContainer.add(medecinWrapper);
        topContainer.add(patientWrapper);
        
        // Create bottom panel for details and date
        JPanel bottomContainer = new JPanel(new BorderLayout());
        bottomContainer.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        
        // Details panel
        JPanel detailsContainer = new JPanel(new BorderLayout());
        detailsContainer.add(details_scrollpane, BorderLayout.CENTER);
        
        // Date panel
        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        datePanel.add(ordonnance_date_label);
        datePanel.add(ordonnance_date_spinner);
        
        // Add date panel to details container (north)
        detailsContainer.add(datePanel, BorderLayout.NORTH);
        
        bottomContainer.add(detailsContainer, BorderLayout.CENTER);
        
        // Add button panel
        JPanel ajoutButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        ajoutButtonPanel.add(ajouterButton);
        bottomContainer.add(ajoutButtonPanel, BorderLayout.SOUTH);
        
        // Add all to main container
        mainContainer.add(topContainer, BorderLayout.NORTH);
        mainContainer.add(bottomContainer, BorderLayout.CENTER);
        
        nouveau_ordonnance.add(mainContainer, BorderLayout.CENTER);
    }
    
    private void setupRechercheTab() {
        recherche_ordonnance.setLayout(new BorderLayout());
        
        // Create title label
        JLabel rechercheTitleLabel = new JLabel("Recherche Ordonnance", SwingConstants.CENTER);
        rechercheTitleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        rechercheTitleLabel.setForeground(new Color(0, 70, 140));
        rechercheTitleLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 20, 0));
        
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.add(rechercheTitleLabel, BorderLayout.CENTER);
        recherche_ordonnance.add(titlePanel, BorderLayout.NORTH);
        
        // Main container panel
        JPanel rechercheContainer = new JPanel(new BorderLayout());
        
        // Simple Recherche Panel
        JPanel simpleRecherchePanel = new JPanel(new GridBagLayout());
        simpleRecherchePanel.setBorder(BorderFactory.createTitledBorder("Recherche Simple"));
        GridBagConstraints gSimple = new GridBagConstraints();
        gSimple.insets = new Insets(5, 10, 5, 10);
        gSimple.anchor = GridBagConstraints.WEST;
        
        int simpleRow = 0;
        
        // Médecin fields in simple recherche
        gSimple.gridx = 0; gSimple.gridy = simpleRow; gSimple.weightx = 0.0; gSimple.fill = GridBagConstraints.NONE;
        simpleRecherchePanel.add(new JLabel("Nom Médecin: "), gSimple);
        gSimple.gridx = 1; gSimple.fill = GridBagConstraints.NONE; gSimple.weightx = 0.0;
        simpleRecherchePanel.add(recherche_medecin_nom_field, gSimple);
        
        simpleRow++;
        gSimple.gridx = 0; gSimple.gridy = simpleRow;
        simpleRecherchePanel.add(new JLabel("Prénom Médecin: "), gSimple);
        gSimple.gridx = 1;
        simpleRecherchePanel.add(recherche_medecin_prenom_field, gSimple);
        
        // Button for simple recherche
        simpleRow++;
        JPanel simpleButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        simpleButtonPanel.add(rechercheSimpleButton);
        gSimple.gridx = 0; gSimple.gridy = simpleRow; gSimple.gridwidth = 2; gSimple.fill = GridBagConstraints.CENTER;
        simpleRecherchePanel.add(simpleButtonPanel, gSimple);
        
        // Advanced Recherche Panel
        JPanel advancedRecherchePanel = new JPanel(new GridBagLayout());
        advancedRecherchePanel.setBorder(BorderFactory.createTitledBorder("Recherche Avancée"));
        GridBagConstraints gAdv = new GridBagConstraints();
        gAdv.insets = new Insets(5, 10, 5, 10);
        gAdv.anchor = GridBagConstraints.WEST;
        
        int advRow = 0;
        
        // Patient fields
        gAdv.gridx = 0; gAdv.gridy = advRow; gAdv.weightx = 0.0; gAdv.fill = GridBagConstraints.NONE;
        advancedRecherchePanel.add(new JLabel("Nom Patient: "), gAdv);
        gAdv.gridx = 1; gAdv.fill = GridBagConstraints.NONE; gAdv.weightx = 0.0;
        advancedRecherchePanel.add(recherche_patient_nom_field, gAdv);
        
        advRow++;
        gAdv.gridx = 0; gAdv.gridy = advRow;
        advancedRecherchePanel.add(new JLabel("Prénom Patient: "), gAdv);
        gAdv.gridx = 1;
        advancedRecherchePanel.add(recherche_patient_prenom_field, gAdv);
        
        advRow++;
        gAdv.gridx = 0; gAdv.gridy = advRow;
        advancedRecherchePanel.add(new JLabel("Age Patient: "), gAdv);
        gAdv.gridx = 1;
        advancedRecherchePanel.add(recherche_patient_age_field, gAdv);
        
        advRow++;
        gAdv.gridx = 0; gAdv.gridy = advRow;
        advancedRecherchePanel.add(new JLabel("Sexe Patient: "), gAdv);
        gAdv.gridx = 1;
        advancedRecherchePanel.add(recherche_patient_sexe_field, gAdv);
        
        advRow++;
        gAdv.gridx = 0; gAdv.gridy = advRow;
        advancedRecherchePanel.add(new JLabel("Groupage Patient: "), gAdv);
        gAdv.gridx = 1;
        advancedRecherchePanel.add(recherche_patient_groupage_field, gAdv);
        
        // Ordonnance date
        advRow++;
        gAdv.gridx = 0; gAdv.gridy = advRow;
        advancedRecherchePanel.add(new JLabel("Date Ordonnance: "), gAdv);
        gAdv.gridx = 1;
        advancedRecherchePanel.add(recherche_ordonnance_date_spinner, gAdv);
        
        // Détails search (optional)
        advRow++;
        gAdv.gridx = 0; gAdv.gridy = advRow;
        advancedRecherchePanel.add(new JLabel("Détails contient: "), gAdv);
        gAdv.gridx = 1;
        advancedRecherchePanel.add(recherche_details_scrollpane, gAdv);
        
        // Button for advanced recherche
        advRow++;
        JPanel advancedButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        advancedButtonPanel.add(rechercheAvanceButton);
        gAdv.gridx = 0; gAdv.gridy = advRow; gAdv.gridwidth = 2; gAdv.fill = GridBagConstraints.CENTER;
        advancedRecherchePanel.add(advancedButtonPanel, gAdv);
        
        // Create a panel to hold both search panels side by side
        JPanel searchPanelsContainer = new JPanel(new GridLayout(1, 2, 20, 0));
        searchPanelsContainer.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        
        // Wrap each panel for better centering
        JPanel simpleWrapper = new JPanel(new BorderLayout());
        simpleWrapper.add(simpleRecherchePanel, BorderLayout.NORTH);
        
        JPanel advancedWrapper = new JPanel(new BorderLayout());
        advancedWrapper.add(advancedRecherchePanel, BorderLayout.NORTH);
        
        searchPanelsContainer.add(simpleWrapper);
        searchPanelsContainer.add(advancedWrapper);
        
        rechercheContainer.add(searchPanelsContainer, BorderLayout.CENTER);
        recherche_ordonnance.add(rechercheContainer, BorderLayout.CENTER);
    }
    
    private void setupOrdonnanceTypeTab() {
        ordonnance_type_panel.setLayout(new BorderLayout());
        
        // Configure the type table
        type_table.setModel(typeTableModel);
        type_table.setFillsViewportHeight(true);
        type_table.setRowHeight(25);
        type_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        type_table.setAutoCreateRowSorter(true);
        
        // Configure table header
        JTableHeader typeHeader = type_table.getTableHeader();
        typeHeader.setFont(new Font("Arial", Font.BOLD, 12));
        typeHeader.setBackground(new Color(220, 220, 220));
        typeHeader.setReorderingAllowed(false);
        
        // Configure table appearance
        type_table.setFont(new Font("Arial", Font.PLAIN, 11));
        type_table.setGridColor(Color.LIGHT_GRAY);
        type_table.setShowGrid(true);
        type_table.setIntercellSpacing(new Dimension(1, 1));
        
        // Set column widths
        type_table.getColumnModel().getColumn(0).setPreferredWidth(100);   // numOrdonnance
        type_table.getColumnModel().getColumn(1).setPreferredWidth(120);   // Type
        type_table.getColumnModel().getColumn(2).setPreferredWidth(300);   // Détails
        
        // Add scroll pane to table
        type_table_scroll.setViewportView(type_table);
        type_table_scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        type_table_scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        type_table_scroll.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        
        ordonnance_type_panel.add(type_table_scroll, BorderLayout.CENTER);

        // Set up the control panel with buttons and label
        JPanel control_panel = new JPanel(new BorderLayout());
        
        // Create left panel for total label
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        total_type_label.setFont(new Font("Arial", Font.BOLD, 15));
        total_type_label.setText("Total Types: 0");
        leftPanel.add(total_type_label);
        
        // Create right panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        buttonPanel.add(type_modifierButton);
        buttonPanel.add(type_supprimerButton);
        buttonPanel.add(type_ajouterButton);
        buttonPanel.add(type_afficherButton);
        
        // Add components to control panel
        control_panel.add(leftPanel, BorderLayout.WEST);
        control_panel.add(buttonPanel, BorderLayout.EAST);
        control_panel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        ordonnance_type_panel.add(control_panel, BorderLayout.NORTH);

        supprimerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                Ordonnance or = new Ordonnance();
                int id = 0;
                or.Supprimer_Ordonnance(id);
            }
        });
    }
    
    // Helper methods similar to Patient_pane
    public void updateTotalOrdonnances(int count) {
        total_ordonnance_label.setText("Total Ordonnances: " + count);
    }
    
    public void updateTotalTypes(int count) {
        total_type_label.setText("Total Types: " + count);
    }
    
    public Integer getSelectedOrdonnanceNum() {
        int selectedRow = ordonnance_table.getSelectedRow();
        if (selectedRow != -1) {
            return (Integer) ordonnance_table.getValueAt(selectedRow, 0);
        }
        return null;
    }
    
    public Integer getSelectedTypeOrdonnanceNum() {
        int selectedRow = type_table.getSelectedRow();
        if (selectedRow != -1) {
            return (Integer) type_table.getValueAt(selectedRow, 0);
        }
        return null;
    }
    
    public DefaultTableModel getTableModel() {
        return tableModel;
    }
    
    public DefaultTableModel getTypeTableModel() {
        return typeTableModel;
    }
    
    public JTable getOrdonnanceTable() {
        return ordonnance_table;
    }
    
    public JTable getTypeTable() {
        return type_table;
    }
    
    // Getters for buttons
    public JButton getSupprimerButton() { return supprimerButton; }
    public JButton getModifierButton() { return modifierButton; }
    public JButton getAjouterButton() { return ajouterButton; }
    public JButton getRechercheSimpleButton() { return rechercheSimpleButton; }
    public JButton getRechercheAvanceButton() { return rechercheAvanceButton; }
    
    // Getters for type buttons
    public JButton getTypeModifierButton() { return type_modifierButton; }
    public JButton getTypeSupprimerButton() { return type_supprimerButton; }
    public JButton getTypeAjouterButton() { return type_ajouterButton; }
    public JButton getTypeAfficherButton() { return type_afficherButton; }
    
    // Getters for text fields in Nouveau tab
    public JTextField getMedecinNomField() { return medecin_nom_field; }
    public JTextField getMedecinPrenomField() { return medecin_prenom_field; }
    public JTextField getPatientNomField() { return patient_nom_field; }
    public JTextField getPatientPrenomField() { return patient_prenom_field; }
    public JTextField getPatientAgeField() { return patient_age_field; }
    public JTextField getPatientSexeField() { return patient_sexe_field; }
    public JTextField getPatientGroupageField() { return patient_groupage_field; }
    public JSpinner getPatientDateSpinner() { return patient_date_spinner; }
    public JSpinner getOrdonnanceDateSpinner() { return ordonnance_date_spinner; }
    public JTextArea getDetailsTextArea() { return details_textarea; }
    
    // Getters for text fields in Recherche tab
    public JTextField getRechercheMedecinNomField() { return recherche_medecin_nom_field; }
    public JTextField getRechercheMedecinPrenomField() { return recherche_medecin_prenom_field; }
    public JTextField getRecherchePatientNomField() { return recherche_patient_nom_field; }
    public JTextField getRecherchePatientPrenomField() { return recherche_patient_prenom_field; }
    public JTextField getRecherchePatientAgeField() { return recherche_patient_age_field; }
    public JTextField getRecherchePatientSexeField() { return recherche_patient_sexe_field; }
    public JTextField getRecherchePatientGroupageField() { return recherche_patient_groupage_field; }
    public JSpinner getRechercheOrdonnanceDateSpinner() { return recherche_ordonnance_date_spinner; }
    public JTextArea getRechercheDetailsTextArea() { return recherche_details_textarea; }
    
    // Helper methods for dates
    public String getSelectedOrdonnanceDate() {
        java.util.Date date = (java.util.Date) ordonnance_date_spinner.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate().format(formatter);
    }
    
    public String getSelectedPatientDate() {
        java.util.Date date = (java.util.Date) patient_date_spinner.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate().format(formatter);
    }
    
    public String getRechercheSelectedDate() {
        java.util.Date date = (java.util.Date) recherche_ordonnance_date_spinner.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate().format(formatter);
    }
}
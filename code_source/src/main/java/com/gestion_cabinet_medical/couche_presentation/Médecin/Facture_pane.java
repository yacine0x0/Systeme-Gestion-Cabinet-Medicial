package com.gestion_cabinet_medical.couche_presentation.Médecin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Facture_pane extends JTabbedPane {
    private JPanel Affichage_facture, total_panel, Nouveau_facture, recherche_facture, suivre_reglement, facture_type_panel;
    private JTable affichage_table, reglement_table, type_table;
    private JScrollPane affichage_scroll, reglement_scroll, type_scroll;
    private JLabel total_facture_label, total_reglement_label, total_type_label;
    private JButton afficherButton, supprimerButton, modifierButton, ajouterButton;
    private JButton verserButton, regleButton;
    private JButton rechercheSimpleButton, rechercheAvanceButton;
    private JButton ordonnanceButton, afficherFactureButton, patientButton;
    private JButton type_modifierButton, type_supprimerButton, type_ajouterButton, type_afficherButton;
    
    // Nouveau Facture - Médecin fields
    private JLabel medecin_nom_label, medecin_prenom_label, medecin_tel_label;
    private JTextField medecin_nom_field, medecin_prenom_field, medecin_tel_field;
    
    // Nouveau Facture - Patient fields
    private JLabel patient_nom_label, patient_prenom_label, patient_sexe_label, patient_tel_label;
    private JTextField patient_nom_field, patient_prenom_field, patient_sexe_field, patient_tel_field;
    
    // Nouveau Facture - Facture fields
    private JLabel facture_date_label, paiement_date_label;
    private JSpinner facture_date_spinner, paiement_date_spinner;
    private JTextArea details_textarea;
    private JScrollPane details_scrollpane;
    
    // Recherche fields
    private JTextField recherche_medecin_nom_field, recherche_medecin_prenom_field;
    private JTextField recherche_patient_nom_field, recherche_patient_prenom_field;
    private JSpinner recherche_facture_date_spinner, recherche_paiement_date_spinner;
    
    // Type fields (for Facture type tab)
    private JTextField type_num_field, type_type_field;
    private JTextArea type_details_textarea;
    private JScrollPane type_details_scrollpane;
    
    private DefaultTableModel affichageTableModel, reglementTableModel, typeTableModel;

    public Facture_pane() {
        // Initialize panels and components
        Affichage_facture = new JPanel();
        total_panel = new JPanel();
        Nouveau_facture = new JPanel();
        recherche_facture = new JPanel();
        suivre_reglement = new JPanel();
        facture_type_panel = new JPanel();
        
        // Initialize tables and scroll panes
        affichage_table = new JTable();
        reglement_table = new JTable();
        type_table = new JTable();
        affichage_scroll = new JScrollPane();
        reglement_scroll = new JScrollPane();
        type_scroll = new JScrollPane();
        
        // Initialize labels
        total_facture_label = new JLabel();
        total_reglement_label = new JLabel();
        total_type_label = new JLabel();
        
        // Initialize buttons
        afficherButton = new JButton("Afficher");
        supprimerButton = new JButton("Supprimer");
        modifierButton = new JButton("Modifier");
        ajouterButton = new JButton("Ajouter Facture");
        verserButton = new JButton("Verser");
        regleButton = new JButton("Réglé !");
        rechercheSimpleButton = new JButton("Rechercher (Simple)");
        rechercheAvanceButton = new JButton("Rechercher (Avancée)");
        ordonnanceButton = new JButton("Ordonnance");
        afficherFactureButton = new JButton("Afficher Facture");
        patientButton = new JButton("Patient");
        type_modifierButton = new JButton("Modifier");
        type_supprimerButton = new JButton("Supprimer");
        type_ajouterButton = new JButton("Ajouter");
        type_afficherButton = new JButton("Afficher");
        
        // Initialize fields
        initNouveauFields();
        initRechercheFields();
        initTypeFields();
        
        // Set up table models
        setupTableModels();
        
        // SET UP AFFICHAGE TAB
        setupAffichageTab();
        
        // SET UP NOUVEAU TAB
        setupNouveauTab();
        
        // SET UP RECHERCHE TAB
        setupRechercheTab();
        
        // SET UP SUIVRE REGLEMENT TAB
        setupReglementTab();
        
        // SET UP FACTURE TYPE TAB
        setupTypeTab();

        this.addTab("Affichage", Affichage_facture);
        this.addTab("Nouveau", Nouveau_facture);
        this.addTab("Recherche", recherche_facture);
        this.addTab("Suivre Réglement", suivre_reglement);
        this.addTab("Facture type", facture_type_panel);
    }
    
    private void initNouveauFields() {
        // Médecin fields
        medecin_nom_label = new JLabel("Nom: ");
        medecin_prenom_label = new JLabel("Prénom: ");
        medecin_tel_label = new JLabel("Tel: ");
        medecin_nom_field = new JTextField(15);
        medecin_prenom_field = new JTextField(15);
        medecin_tel_field = new JTextField(15);
        
        // Patient fields
        patient_nom_label = new JLabel("Nom: ");
        patient_prenom_label = new JLabel("Prénom: ");
        patient_sexe_label = new JLabel("Sexe: ");
        patient_tel_label = new JLabel("Tel: ");
        patient_nom_field = new JTextField(15);
        patient_prenom_field = new JTextField(15);
        patient_sexe_field = new JTextField(15);
        patient_tel_field = new JTextField(15);
        
        // Facture date spinner
        SpinnerDateModel factureDateModel = new SpinnerDateModel(
            java.sql.Date.valueOf(LocalDate.now()),
            null,
            null,
            java.util.Calendar.DAY_OF_MONTH
        );
        facture_date_spinner = new JSpinner(factureDateModel);
        JSpinner.DateEditor factureDateEditor = new JSpinner.DateEditor(facture_date_spinner, "dd/MM/yyyy");
        facture_date_spinner.setEditor(factureDateEditor);
        facture_date_spinner.setPreferredSize(new Dimension(120, 25));
        
        // Paiement date spinner (default: today + 30 days)
        SpinnerDateModel paiementDateModel = new SpinnerDateModel(
            java.sql.Date.valueOf(LocalDate.now().plusDays(30)),
            null,
            null,
            java.util.Calendar.DAY_OF_MONTH
        );
        paiement_date_spinner = new JSpinner(paiementDateModel);
        JSpinner.DateEditor paiementDateEditor = new JSpinner.DateEditor(paiement_date_spinner, "dd/MM/yyyy");
        paiement_date_spinner.setEditor(paiementDateEditor);
        paiement_date_spinner.setPreferredSize(new Dimension(120, 25));
        
        // Labels
        facture_date_label = new JLabel("Date de Facture: ");
        paiement_date_label = new JLabel("Date prévue paiement: ");
        
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
        
        // Recherche date spinners
        SpinnerDateModel rechercheFactureDateModel = new SpinnerDateModel(
            java.sql.Date.valueOf(LocalDate.now()),
            null,
            null,
            java.util.Calendar.DAY_OF_MONTH
        );
        recherche_facture_date_spinner = new JSpinner(rechercheFactureDateModel);
        JSpinner.DateEditor rechercheFactureDateEditor = new JSpinner.DateEditor(recherche_facture_date_spinner, "dd/MM/yyyy");
        recherche_facture_date_spinner.setEditor(rechercheFactureDateEditor);
        recherche_facture_date_spinner.setPreferredSize(new Dimension(120, 25));
        
        SpinnerDateModel recherchePaiementDateModel = new SpinnerDateModel(
            java.sql.Date.valueOf(LocalDate.now()),
            null,
            null,
            java.util.Calendar.DAY_OF_MONTH
        );
        recherche_paiement_date_spinner = new JSpinner(recherchePaiementDateModel);
        JSpinner.DateEditor recherchePaiementDateEditor = new JSpinner.DateEditor(recherche_paiement_date_spinner, "dd/MM/yyyy");
        recherche_paiement_date_spinner.setEditor(recherchePaiementDateEditor);
        recherche_paiement_date_spinner.setPreferredSize(new Dimension(120, 25));
    }
    
    private void initTypeFields() {
        // Type fields
        type_num_field = new JTextField(15);
        type_type_field = new JTextField(15);
        
        // Type details text area
        type_details_textarea = new JTextArea(6, 25);
        type_details_textarea.setLineWrap(true);
        type_details_textarea.setWrapStyleWord(true);
        type_details_scrollpane = new JScrollPane(type_details_textarea);
        type_details_scrollpane.setBorder(BorderFactory.createTitledBorder("Détails du Type"));
    }
    
    private void setupTableModels() {
        // Affichage table model
        affichageTableModel = new DefaultTableModel(new Object[][] {},
            new String[] {
                "Num", "Médecin", "Patient", "Date", "Date prévue paiement", "Détails"
            }) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        // Réglement table model
        reglementTableModel = new DefaultTableModel(new Object[][] {},
            new String[] {
                "Num", "Date", "Date prévue", "Total à payer", "Versement", "Date versement", "Reste", "Réglé ?"
            }) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        // Type table model
        typeTableModel = new DefaultTableModel(new Object[][] {},
            new String[] {
                "Num Facture", "Type", "Détails"
            }) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }
    
    private void configureTable(JTable table, DefaultTableModel model) {
        table.setModel(model);
        table.setFillsViewportHeight(true);
        table.setRowHeight(25);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setAutoCreateRowSorter(true);
        
        // Configure table header
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 12));
        header.setBackground(new Color(220, 220, 220));
        header.setReorderingAllowed(false);
        
        // Configure table appearance
        table.setFont(new Font("Arial", Font.PLAIN, 11));
        table.setGridColor(Color.LIGHT_GRAY);
        table.setShowGrid(true);
        table.setIntercellSpacing(new Dimension(1, 1));
    }
    
    private JScrollPane createScrollPane(JTable table) {
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        return scrollPane;
    }
    
    private void setupAffichageTab() {
        Affichage_facture.setLayout(new BorderLayout());
        
        // Configure the table
        configureTable(affichage_table, affichageTableModel);
        
        // Set column widths for affichage table
        affichage_table.getColumnModel().getColumn(0).setPreferredWidth(60);   // Num
        affichage_table.getColumnModel().getColumn(1).setPreferredWidth(120);  // Médecin
        affichage_table.getColumnModel().getColumn(2).setPreferredWidth(120);  // Patient
        affichage_table.getColumnModel().getColumn(3).setPreferredWidth(100);  // Date
        affichage_table.getColumnModel().getColumn(4).setPreferredWidth(120);  // Date prévue paiement
        affichage_table.getColumnModel().getColumn(5).setPreferredWidth(200);  // Détails
        
        affichage_scroll = createScrollPane(affichage_table);
        Affichage_facture.add(affichage_scroll, BorderLayout.CENTER);

        // Set up the control panel with buttons and label
        JPanel control_panel = new JPanel(new BorderLayout());
        
        // Create left panel for total label
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        total_facture_label.setFont(new Font("Arial", Font.BOLD, 15));
        total_facture_label.setText("Total Factures: 0");
        leftPanel.add(total_facture_label);
        
        // Create right panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        buttonPanel.add(afficherButton);
        buttonPanel.add(supprimerButton);
        buttonPanel.add(modifierButton);
        
        // Add components to control panel
        control_panel.add(leftPanel, BorderLayout.WEST);
        control_panel.add(buttonPanel, BorderLayout.EAST);
        control_panel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        Affichage_facture.add(control_panel, BorderLayout.NORTH);
    }
    
    private void setupNouveauTab() {
        Nouveau_facture.setLayout(new BorderLayout());
        
        // Create title label
        JLabel nouveauTitleLabel = new JLabel("Nouvelle Facture", SwingConstants.CENTER);
        nouveauTitleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        nouveauTitleLabel.setForeground(new Color(0, 70, 140));
        nouveauTitleLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 20, 0));
        
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.add(nouveauTitleLabel, BorderLayout.CENTER);
        Nouveau_facture.add(titlePanel, BorderLayout.NORTH);
        
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
        
        medRow++;
        gMed.gridx = 0; gMed.gridy = medRow;
        medecinPanel.add(medecin_tel_label, gMed);
        gMed.gridx = 1;
        medecinPanel.add(medecin_tel_field, gMed);
        
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
        patientPanel.add(patient_sexe_label, gPat);
        gPat.gridx = 1;
        patientPanel.add(patient_sexe_field, gPat);
        
        patRow++;
        gPat.gridx = 0; gPat.gridy = patRow;
        patientPanel.add(patient_tel_label, gPat);
        gPat.gridx = 1;
        patientPanel.add(patient_tel_field, gPat);
        
        // Wrap panels
        JPanel medecinWrapper = new JPanel(new BorderLayout());
        medecinWrapper.add(medecinPanel, BorderLayout.NORTH);
        
        JPanel patientWrapper = new JPanel(new BorderLayout());
        patientWrapper.add(patientPanel, BorderLayout.NORTH);
        
        topContainer.add(medecinWrapper);
        topContainer.add(patientWrapper);
        
        // Create bottom panel for details and dates
        JPanel bottomContainer = new JPanel(new BorderLayout());
        bottomContainer.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        
        // Details panel
        JPanel detailsContainer = new JPanel(new BorderLayout());
        detailsContainer.add(details_scrollpane, BorderLayout.CENTER);
        
        // Dates panel
        JPanel datesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        datesPanel.add(facture_date_label);
        datesPanel.add(facture_date_spinner);
        datesPanel.add(Box.createHorizontalStrut(20)); // Spacer
        datesPanel.add(paiement_date_label);
        datesPanel.add(paiement_date_spinner);
        
        // Add dates panel to details container (north)
        detailsContainer.add(datesPanel, BorderLayout.NORTH);
        
        bottomContainer.add(detailsContainer, BorderLayout.CENTER);
        
        // Add button panel
        JPanel ajoutButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        ajoutButtonPanel.add(ajouterButton);
        bottomContainer.add(ajoutButtonPanel, BorderLayout.SOUTH);
        
        // Add all to main container
        mainContainer.add(topContainer, BorderLayout.NORTH);
        mainContainer.add(bottomContainer, BorderLayout.CENTER);
        
        Nouveau_facture.add(mainContainer, BorderLayout.CENTER);
    }
    
    private void setupRechercheTab() {
        recherche_facture.setLayout(new BorderLayout());
        
        // Create title label
        JLabel rechercheTitleLabel = new JLabel("Recherche Facture", SwingConstants.CENTER);
        rechercheTitleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        rechercheTitleLabel.setForeground(new Color(0, 70, 140));
        rechercheTitleLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 20, 0));
        
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.add(rechercheTitleLabel, BorderLayout.CENTER);
        recherche_facture.add(titlePanel, BorderLayout.NORTH);
        
        // Main container panel for the search forms
        JPanel rechercheContainer = new JPanel(new BorderLayout());
        
        // Normal Recherche Panel
        JPanel normalRecherchePanel = new JPanel(new GridBagLayout());
        normalRecherchePanel.setBorder(BorderFactory.createTitledBorder("Recherche Simple"));
        GridBagConstraints gNormal = new GridBagConstraints();
        gNormal.insets = new Insets(5, 10, 5, 10);
        gNormal.anchor = GridBagConstraints.WEST;
        
        int normalRow = 0;
        
        // Médecin field
        gNormal.gridx = 0; gNormal.gridy = normalRow; gNormal.weightx = 0.0; gNormal.fill = GridBagConstraints.NONE;
        normalRecherchePanel.add(new JLabel("Nom Médecin: "), gNormal);
        gNormal.gridx = 1; gNormal.fill = GridBagConstraints.NONE; gNormal.weightx = 0.0;
        normalRecherchePanel.add(recherche_medecin_nom_field, gNormal);
        
        // Patient field
        normalRow++;
        gNormal.gridx = 0; gNormal.gridy = normalRow;
        normalRecherchePanel.add(new JLabel("Nom Patient: "), gNormal);
        gNormal.gridx = 1;
        normalRecherchePanel.add(recherche_patient_nom_field, gNormal);
        
        // Button for normal recherche
        normalRow++;
        JPanel normalButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        normalButtonPanel.add(rechercheSimpleButton);
        gNormal.gridx = 0; gNormal.gridy = normalRow; gNormal.gridwidth = 2; gNormal.fill = GridBagConstraints.CENTER;
        normalRecherchePanel.add(normalButtonPanel, gNormal);
        
        // Advanced Recherche Panel
        JPanel advancedRecherchePanel = new JPanel(new GridBagLayout());
        advancedRecherchePanel.setBorder(BorderFactory.createTitledBorder("Recherche Avancée"));
        GridBagConstraints gAdv = new GridBagConstraints();
        gAdv.insets = new Insets(5, 10, 5, 10);
        gAdv.anchor = GridBagConstraints.WEST;
        
        int advRow = 0;
        
        // Prénom Médecin field
        gAdv.gridx = 0; gAdv.gridy = advRow; gAdv.weightx = 0.0; gAdv.fill = GridBagConstraints.NONE;
        advancedRecherchePanel.add(new JLabel("Prénom Médecin: "), gAdv);
        gAdv.gridx = 1; gAdv.fill = GridBagConstraints.NONE; gAdv.weightx = 0.0;
        advancedRecherchePanel.add(recherche_medecin_prenom_field, gAdv);
        
        // Prénom Patient field
        advRow++;
        gAdv.gridx = 0; gAdv.gridy = advRow;
        advancedRecherchePanel.add(new JLabel("Prénom Patient: "), gAdv);
        gAdv.gridx = 1;
        advancedRecherchePanel.add(recherche_patient_prenom_field, gAdv);
        
        // Date Facture field
        advRow++;
        gAdv.gridx = 0; gAdv.gridy = advRow;
        advancedRecherchePanel.add(new JLabel("Date Facture: "), gAdv);
        gAdv.gridx = 1;
        advancedRecherchePanel.add(recherche_facture_date_spinner, gAdv);
        
        // Date Paiement field
        advRow++;
        gAdv.gridx = 0; gAdv.gridy = advRow;
        advancedRecherchePanel.add(new JLabel("Date Paiement: "), gAdv);
        gAdv.gridx = 1;
        advancedRecherchePanel.add(recherche_paiement_date_spinner, gAdv);
        
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
        JPanel normalWrapper = new JPanel(new BorderLayout());
        normalWrapper.add(normalRecherchePanel, BorderLayout.NORTH);
        
        JPanel advancedWrapper = new JPanel(new BorderLayout());
        advancedWrapper.add(advancedRecherchePanel, BorderLayout.NORTH);
        
        searchPanelsContainer.add(normalWrapper);
        searchPanelsContainer.add(advancedWrapper);
        
        rechercheContainer.add(searchPanelsContainer, BorderLayout.CENTER);
        
        // Add the three buttons panel at the bottom
        JPanel specialButtonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        specialButtonsPanel.add(ordonnanceButton);
        specialButtonsPanel.add(afficherFactureButton);
        specialButtonsPanel.add(patientButton);
        specialButtonsPanel.setBorder(BorderFactory.createTitledBorder("Actions"));
        
        rechercheContainer.add(specialButtonsPanel, BorderLayout.SOUTH);
        
        recherche_facture.add(rechercheContainer, BorderLayout.CENTER);
    }
    
    private void setupReglementTab() {
        suivre_reglement.setLayout(new BorderLayout());
        
        // Configure the table
        configureTable(reglement_table, reglementTableModel);
        
        // Set column widths for reglement table
        reglement_table.getColumnModel().getColumn(0).setPreferredWidth(60);   // Num
        reglement_table.getColumnModel().getColumn(1).setPreferredWidth(100);  // Date
        reglement_table.getColumnModel().getColumn(2).setPreferredWidth(100);  // Date prévue
        reglement_table.getColumnModel().getColumn(3).setPreferredWidth(100);  // Total à payer
        reglement_table.getColumnModel().getColumn(4).setPreferredWidth(100);  // Versement
        reglement_table.getColumnModel().getColumn(5).setPreferredWidth(100);  // Date versement
        reglement_table.getColumnModel().getColumn(6).setPreferredWidth(100);  // Reste
        reglement_table.getColumnModel().getColumn(7).setPreferredWidth(80);   // Réglé ?
        
        reglement_scroll = createScrollPane(reglement_table);
        suivre_reglement.add(reglement_scroll, BorderLayout.CENTER);

        // Set up the control panel with buttons and label
        JPanel control_panel = new JPanel(new BorderLayout());
        
        // Create left panel for total label
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        total_reglement_label.setFont(new Font("Arial", Font.BOLD, 15));
        total_reglement_label.setText("Total Factures en cours: 0");
        leftPanel.add(total_reglement_label);
        
        // Create right panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        buttonPanel.add(verserButton);
        buttonPanel.add(regleButton);
        
        // Add components to control panel
        control_panel.add(leftPanel, BorderLayout.WEST);
        control_panel.add(buttonPanel, BorderLayout.EAST);
        control_panel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        suivre_reglement.add(control_panel, BorderLayout.NORTH);
    }
    
    private void setupTypeTab() {
        facture_type_panel.setLayout(new BorderLayout());
        
        // Configure the type table
        configureTable(type_table, typeTableModel);
        
        // Set column widths for type table
        type_table.getColumnModel().getColumn(0).setPreferredWidth(100);   // Num Facture
        type_table.getColumnModel().getColumn(1).setPreferredWidth(120);   // Type
        type_table.getColumnModel().getColumn(2).setPreferredWidth(300);   // Détails
        
        type_scroll = createScrollPane(type_table);
        facture_type_panel.add(type_scroll, BorderLayout.CENTER);

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
        
        facture_type_panel.add(control_panel, BorderLayout.NORTH);
    }
    
    // Helper methods
    public void updateTotalFactures(int count) {
        total_facture_label.setText("Total Factures: " + count);
    }
    
    public void updateTotalReglements(int count) {
        total_reglement_label.setText("Total Factures en cours: " + count);
    }
    
    public void updateTotalTypes(int count) {
        total_type_label.setText("Total Types: " + count);
    }
    
    // Getters for selected rows
    public Integer getSelectedAffichageNum() {
        int selectedRow = affichage_table.getSelectedRow();
        if (selectedRow != -1) {
            return (Integer) affichage_table.getValueAt(selectedRow, 0);
        }
        return null;
    }
    
    public Integer getSelectedReglementNum() {
        int selectedRow = reglement_table.getSelectedRow();
        if (selectedRow != -1) {
            return (Integer) reglement_table.getValueAt(selectedRow, 0);
        }
        return null;
    }
    
    public Integer getSelectedTypeNum() {
        int selectedRow = type_table.getSelectedRow();
        if (selectedRow != -1) {
            return (Integer) type_table.getValueAt(selectedRow, 0);
        }
        return null;
    }
    
    // Getters for table models
    public DefaultTableModel getAffichageTableModel() {
        return affichageTableModel;
    }
    
    public DefaultTableModel getReglementTableModel() {
        return reglementTableModel;
    }
    
    public DefaultTableModel getTypeTableModel() {
        return typeTableModel;
    }
    
    // Getters for tables
    public JTable getAffichageTable() {
        return affichage_table;
    }
    
    public JTable getReglementTable() {
        return reglement_table;
    }
    
    public JTable getTypeTable() {
        return type_table;
    }
    
    // Getters for buttons
    public JButton getAfficherButton() { return afficherButton; }
    public JButton getSupprimerButton() { return supprimerButton; }
    public JButton getModifierButton() { return modifierButton; }
    public JButton getAjouterButton() { return ajouterButton; }
    public JButton getVerserButton() { return verserButton; }
    public JButton getRegleButton() { return regleButton; }
    public JButton getRechercheSimpleButton() { return rechercheSimpleButton; }
    public JButton getRechercheAvanceButton() { return rechercheAvanceButton; }
    public JButton getOrdonnanceButton() { return ordonnanceButton; }
    public JButton getAfficherFactureButton() { return afficherFactureButton; }
    public JButton getPatientButton() { return patientButton; }
    public JButton getTypeModifierButton() { return type_modifierButton; }
    public JButton getTypeSupprimerButton() { return type_supprimerButton; }
    public JButton getTypeAjouterButton() { return type_ajouterButton; }
    public JButton getTypeAfficherButton() { return type_afficherButton; }
    
    // Getters for text fields in Nouveau tab
    public JTextField getMedecinNomField() { return medecin_nom_field; }
    public JTextField getMedecinPrenomField() { return medecin_prenom_field; }
    public JTextField getMedecinTelField() { return medecin_tel_field; }
    public JTextField getPatientNomField() { return patient_nom_field; }
    public JTextField getPatientPrenomField() { return patient_prenom_field; }
    public JTextField getPatientSexeField() { return patient_sexe_field; }
    public JTextField getPatientTelField() { return patient_tel_field; }
    public JSpinner getFactureDateSpinner() { return facture_date_spinner; }
    public JSpinner getPaiementDateSpinner() { return paiement_date_spinner; }
    public JTextArea getDetailsTextArea() { return details_textarea; }
    
    // Getters for text fields in Recherche tab
    public JTextField getRechercheMedecinNomField() { return recherche_medecin_nom_field; }
    public JTextField getRechercheMedecinPrenomField() { return recherche_medecin_prenom_field; }
    public JTextField getRecherchePatientNomField() { return recherche_patient_nom_field; }
    public JTextField getRecherchePatientPrenomField() { return recherche_patient_prenom_field; }
    public JSpinner getRechercheFactureDateSpinner() { return recherche_facture_date_spinner; }
    public JSpinner getRecherchePaiementDateSpinner() { return recherche_paiement_date_spinner; }
    
    // Getters for type fields
    public JTextField getTypeNumField() { return type_num_field; }
    public JTextField getTypeTypeField() { return type_type_field; }
    public JTextArea getTypeDetailsTextArea() { return type_details_textarea; }
    
    // Helper methods for dates
    public String getSelectedFactureDate() {
        java.util.Date date = (java.util.Date) facture_date_spinner.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate().format(formatter);
    }
    
    public String getSelectedPaiementDate() {
        java.util.Date date = (java.util.Date) paiement_date_spinner.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate().format(formatter);
    }
    
    public String getRechercheSelectedFactureDate() {
        java.util.Date date = (java.util.Date) recherche_facture_date_spinner.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate().format(formatter);
    }
    
    public String getRechercheSelectedPaiementDate() {
        java.util.Date date = (java.util.Date) recherche_paiement_date_spinner.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate().format(formatter);
    }
}
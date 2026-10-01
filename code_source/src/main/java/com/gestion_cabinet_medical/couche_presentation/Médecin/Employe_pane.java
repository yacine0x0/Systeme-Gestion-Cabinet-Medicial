package com.gestion_cabinet_medical.couche_presentation.Médecin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Employe_pane extends JTabbedPane {
    private JPanel Affichage_employe, total_panel, ajout_employe, recherche_employe, disponibilite_panel;
    private JTable affichage_table, disponibilite_table;
    private JScrollPane affichage_scroll, disponibilite_scroll;
    private JLabel total_employe_label, total_disponibilite_label;
    private JButton afficherButton, supprimerButton, modifierButton, ajouterButton;
    private JButton rechercheSimpleButton, rechercheAvanceButton;
    private JButton disponibilite_modifierButton, disponibilite_supprimerButton;
    
    // Ajout Employé fields
    private JLabel nom_label, prenom_label, cin_label, tel_label, email_label;
    private JLabel date_naissance_label, date_embauche_label, poste_label, salaire_label;
    private JTextField nom_field, prenom_field, cin_field, tel_field, email_field;
    private JTextField poste_field, salaire_field;
    private JSpinner date_naissance_spinner, date_embauche_spinner;
    
    // Recherche fields
    private JTextField recherche_nom_field, recherche_prenom_field, recherche_cin_field;
    private JTextField recherche_poste_field, recherche_salaire_field;
    private JSpinner recherche_date_naissance_spinner, recherche_date_embauche_spinner;
    
    // Disponibilité fields
    private JLabel dispo_nom_label, dispo_prenom_label, dispo_poste_label;
    private JLabel dispo_jour_label, dispo_heure_debut_label, dispo_heure_fin_label;
    private JTextField dispo_nom_field, dispo_prenom_field, dispo_poste_field;
    private JTextField dispo_jour_field, dispo_heure_debut_field, dispo_heure_fin_field;
    private JButton dispo_ajouterButton;
    
    private DefaultTableModel affichageTableModel, disponibiliteTableModel;

    public Employe_pane() {
        // Initialize panels and components
        Affichage_employe = new JPanel();
        total_panel = new JPanel();
        ajout_employe = new JPanel();
        recherche_employe = new JPanel();
        disponibilite_panel = new JPanel();
        
        // Initialize tables and scroll panes
        affichage_table = new JTable();
        disponibilite_table = new JTable();
        affichage_scroll = new JScrollPane();
        disponibilite_scroll = new JScrollPane();
        
        // Initialize labels
        total_employe_label = new JLabel();
        total_disponibilite_label = new JLabel();
        
        // Initialize buttons
        afficherButton = new JButton("Afficher");
        supprimerButton = new JButton("Supprimer");
        modifierButton = new JButton("Modifier");
        ajouterButton = new JButton("Ajouter Employé");
        rechercheSimpleButton = new JButton("Rechercher (Simple)");
        rechercheAvanceButton = new JButton("Rechercher (Avancée)");
        disponibilite_modifierButton = new JButton("Modifier");
        disponibilite_supprimerButton = new JButton("Supprimer");
        dispo_ajouterButton = new JButton("Ajouter Disponibilité");
        
        // Initialize fields
        initAjoutFields();
        initRechercheFields();
        initDisponibiliteFields();
        
        // Set up table models
        setupTableModels();
        
        // SET UP AFFICHAGE TAB
        setupAffichageTab();
        
        // SET UP AJOUT TAB
        setupAjoutTab();
        
        // SET UP RECHERCHE TAB
        setupRechercheTab();
        
        // SET UP DISPONIBILITE TAB
        setupDisponibiliteTab();

        this.addTab("Affichage", Affichage_employe);
        this.addTab("Ajout", ajout_employe);
        this.addTab("Recherche", recherche_employe);
        this.addTab("Disponibilité", disponibilite_panel);
    }
    
    private void initAjoutFields() {
        // Basic info fields
        nom_label = new JLabel("Nom: ");
        prenom_label = new JLabel("Prénom: ");
        cin_label = new JLabel("CIN: ");
        tel_label = new JLabel("Tel: ");
        email_label = new JLabel("Email: ");
        
        nom_field = new JTextField(15);
        prenom_field = new JTextField(15);
        cin_field = new JTextField(15);
        tel_field = new JTextField(15);
        email_field = new JTextField(15);
        
        // Date fields
        date_naissance_label = new JLabel("Date de Naissance: ");
        date_embauche_label = new JLabel("Date d'Embauche: ");
        
        SpinnerDateModel naissanceModel = new SpinnerDateModel(
            java.sql.Date.valueOf(LocalDate.now().minusYears(25)),
            null,
            null,
            java.util.Calendar.DAY_OF_MONTH
        );
        date_naissance_spinner = new JSpinner(naissanceModel);
        JSpinner.DateEditor naissanceEditor = new JSpinner.DateEditor(date_naissance_spinner, "dd/MM/yyyy");
        date_naissance_spinner.setEditor(naissanceEditor);
        date_naissance_spinner.setPreferredSize(new Dimension(120, 25));
        
        SpinnerDateModel embaucheModel = new SpinnerDateModel(
            java.sql.Date.valueOf(LocalDate.now()),
            null,
            null,
            java.util.Calendar.DAY_OF_MONTH
        );
        date_embauche_spinner = new JSpinner(embaucheModel);
        JSpinner.DateEditor embaucheEditor = new JSpinner.DateEditor(date_embauche_spinner, "dd/MM/yyyy");
        date_embauche_spinner.setEditor(embaucheEditor);
        date_embauche_spinner.setPreferredSize(new Dimension(120, 25));
        
        // Job fields
        poste_label = new JLabel("Poste: ");
        salaire_label = new JLabel("Salaire: ");
        poste_field = new JTextField(15);
        salaire_field = new JTextField(15);
    }
    
    private void initRechercheFields() {
        // Recherche basic fields
        recherche_nom_field = new JTextField(15);
        recherche_prenom_field = new JTextField(15);
        recherche_cin_field = new JTextField(15);
        recherche_poste_field = new JTextField(15);
        recherche_salaire_field = new JTextField(15);
        
        // Recherche date spinners
        SpinnerDateModel rechercheNaissanceModel = new SpinnerDateModel(
            java.sql.Date.valueOf(LocalDate.now().minusYears(25)),
            null,
            null,
            java.util.Calendar.DAY_OF_MONTH
        );
        recherche_date_naissance_spinner = new JSpinner(rechercheNaissanceModel);
        JSpinner.DateEditor rechercheNaissanceEditor = new JSpinner.DateEditor(recherche_date_naissance_spinner, "dd/MM/yyyy");
        recherche_date_naissance_spinner.setEditor(rechercheNaissanceEditor);
        recherche_date_naissance_spinner.setPreferredSize(new Dimension(120, 25));
        
        SpinnerDateModel rechercheEmbaucheModel = new SpinnerDateModel(
            java.sql.Date.valueOf(LocalDate.now()),
            null,
            null,
            java.util.Calendar.DAY_OF_MONTH
        );
        recherche_date_embauche_spinner = new JSpinner(rechercheEmbaucheModel);
        JSpinner.DateEditor rechercheEmbaucheEditor = new JSpinner.DateEditor(recherche_date_embauche_spinner, "dd/MM/yyyy");
        recherche_date_embauche_spinner.setEditor(rechercheEmbaucheEditor);
        recherche_date_embauche_spinner.setPreferredSize(new Dimension(120, 25));
    }
    
    private void initDisponibiliteFields() {
        // Disponibilité fields
        dispo_nom_label = new JLabel("Nom: ");
        dispo_prenom_label = new JLabel("Prénom: ");
        dispo_poste_label = new JLabel("Poste: ");
        dispo_jour_label = new JLabel("Jour: ");
        dispo_heure_debut_label = new JLabel("Heure début: ");
        dispo_heure_fin_label = new JLabel("Heure fin: ");
        
        dispo_nom_field = new JTextField(15);
        dispo_prenom_field = new JTextField(15);
        dispo_poste_field = new JTextField(15);
        dispo_jour_field = new JTextField(15);
        dispo_heure_debut_field = new JTextField(15);
        dispo_heure_fin_field = new JTextField(15);
    }
    
    private void setupTableModels() {
        // Affichage table model
        affichageTableModel = new DefaultTableModel(new Object[][] {},
            new String[] {
                "ID", "Nom", "Prénom", "CIN", "Tel", "Email", "Date Naissance", "Date Embauche", "Poste", "Salaire"
            }) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        // Disponibilité table model
        disponibiliteTableModel = new DefaultTableModel(new Object[][] {},
            new String[] {
                "ID Employé", "Nom", "Prénom", "Poste", "Jour", "Heure début", "Heure fin"
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
        Affichage_employe.setLayout(new BorderLayout());
        
        // Configure the table
        configureTable(affichage_table, affichageTableModel);
        
        // Set column widths for affichage table
        affichage_table.getColumnModel().getColumn(0).setPreferredWidth(50);   // ID
        affichage_table.getColumnModel().getColumn(1).setPreferredWidth(100);  // Nom
        affichage_table.getColumnModel().getColumn(2).setPreferredWidth(100);  // Prénom
        affichage_table.getColumnModel().getColumn(3).setPreferredWidth(80);   // CIN
        affichage_table.getColumnModel().getColumn(4).setPreferredWidth(100);  // Tel
        affichage_table.getColumnModel().getColumn(5).setPreferredWidth(150);  // Email
        affichage_table.getColumnModel().getColumn(6).setPreferredWidth(100);  // Date Naissance
        affichage_table.getColumnModel().getColumn(7).setPreferredWidth(100);  // Date Embauche
        affichage_table.getColumnModel().getColumn(8).setPreferredWidth(100);  // Poste
        affichage_table.getColumnModel().getColumn(9).setPreferredWidth(80);   // Salaire
        
        affichage_scroll = createScrollPane(affichage_table);
        Affichage_employe.add(affichage_scroll, BorderLayout.CENTER);

        // Set up the control panel with buttons and label
        JPanel control_panel = new JPanel(new BorderLayout());
        
        // Create left panel for total label
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        total_employe_label.setFont(new Font("Arial", Font.BOLD, 15));
        total_employe_label.setText("Total Employés: 0");
        leftPanel.add(total_employe_label);
        
        // Create right panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        buttonPanel.add(afficherButton);
        buttonPanel.add(supprimerButton);
        buttonPanel.add(modifierButton);
        
        // Add components to control panel
        control_panel.add(leftPanel, BorderLayout.WEST);
        control_panel.add(buttonPanel, BorderLayout.EAST);
        control_panel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        Affichage_employe.add(control_panel, BorderLayout.NORTH);
    }
    
    private void setupAjoutTab() {
        ajout_employe.setLayout(new BorderLayout());
        
        // Create title label
        JLabel ajoutTitleLabel = new JLabel("Ajout Employé", SwingConstants.CENTER);
        ajoutTitleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        ajoutTitleLabel.setForeground(new Color(0, 70, 140));
        ajoutTitleLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 20, 0));
        
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.add(ajoutTitleLabel, BorderLayout.CENTER);
        ajout_employe.add(titlePanel, BorderLayout.NORTH);
        
        // Create main container panel
        JPanel mainContainer = new JPanel(new GridBagLayout());
        mainContainer.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5, 10, 5, 10);
        g.anchor = GridBagConstraints.WEST;
        
        int row = 0;
        
        // Row 0: Nom
        g.gridx = 0; g.gridy = row; g.weightx = 0.0; g.fill = GridBagConstraints.NONE;
        mainContainer.add(nom_label, g);
        g.gridx = 1; g.fill = GridBagConstraints.NONE; g.weightx = 0.0;
        mainContainer.add(nom_field, g);
        
        // Row 1: Prénom
        row++;
        g.gridx = 0; g.gridy = row;
        mainContainer.add(prenom_label, g);
        g.gridx = 1;
        mainContainer.add(prenom_field, g);
        
        // Row 2: CIN
        row++;
        g.gridx = 0; g.gridy = row;
        mainContainer.add(cin_label, g);
        g.gridx = 1;
        mainContainer.add(cin_field, g);
        
        // Row 3: Tel
        row++;
        g.gridx = 0; g.gridy = row;
        mainContainer.add(tel_label, g);
        g.gridx = 1;
        mainContainer.add(tel_field, g);
        
        // Row 4: Email
        row++;
        g.gridx = 0; g.gridy = row;
        mainContainer.add(email_label, g);
        g.gridx = 1;
        mainContainer.add(email_field, g);
        
        // Row 5: Date Naissance
        row++;
        g.gridx = 0; g.gridy = row;
        mainContainer.add(date_naissance_label, g);
        g.gridx = 1;
        mainContainer.add(date_naissance_spinner, g);
        
        // Row 6: Date Embauche
        row++;
        g.gridx = 0; g.gridy = row;
        mainContainer.add(date_embauche_label, g);
        g.gridx = 1;
        mainContainer.add(date_embauche_spinner, g);
        
        // Row 7: Poste
        row++;
        g.gridx = 0; g.gridy = row;
        mainContainer.add(poste_label, g);
        g.gridx = 1;
        mainContainer.add(poste_field, g);
        
        // Row 8: Salaire
        row++;
        g.gridx = 0; g.gridy = row;
        mainContainer.add(salaire_label, g);
        g.gridx = 1;
        mainContainer.add(salaire_field, g);
        
        // Row 9: Button panel
        row++;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        buttonPanel.add(ajouterButton);
        g.gridx = 0; g.gridy = row; g.gridwidth = 2; g.fill = GridBagConstraints.CENTER;
        mainContainer.add(buttonPanel, g);
        
        // Wrap mainContainer in another panel for centering
        JPanel wrapperPanel = new JPanel(new GridBagLayout());
        wrapperPanel.add(mainContainer);
        
        ajout_employe.add(wrapperPanel, BorderLayout.CENTER);
    }
    
    private void setupRechercheTab() {
        recherche_employe.setLayout(new BorderLayout());
        
        // Create title label
        JLabel rechercheTitleLabel = new JLabel("Recherche Employé", SwingConstants.CENTER);
        rechercheTitleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        rechercheTitleLabel.setForeground(new Color(0, 70, 140));
        rechercheTitleLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 20, 0));
        
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.add(rechercheTitleLabel, BorderLayout.CENTER);
        recherche_employe.add(titlePanel, BorderLayout.NORTH);
        
        // Main container panel for the search forms
        JPanel rechercheContainer = new JPanel(new BorderLayout());
        
        // Normal Recherche Panel
        JPanel normalRecherchePanel = new JPanel(new GridBagLayout());
        normalRecherchePanel.setBorder(BorderFactory.createTitledBorder("Recherche Simple"));
        GridBagConstraints gNormal = new GridBagConstraints();
        gNormal.insets = new Insets(5, 10, 5, 10);
        gNormal.anchor = GridBagConstraints.WEST;
        
        int normalRow = 0;
        
        // Nom field
        gNormal.gridx = 0; gNormal.gridy = normalRow; gNormal.weightx = 0.0; gNormal.fill = GridBagConstraints.NONE;
        normalRecherchePanel.add(new JLabel("Nom: "), gNormal);
        gNormal.gridx = 1; gNormal.fill = GridBagConstraints.NONE; gNormal.weightx = 0.0;
        normalRecherchePanel.add(recherche_nom_field, gNormal);
        
        // Prénom field
        normalRow++;
        gNormal.gridx = 0; gNormal.gridy = normalRow;
        normalRecherchePanel.add(new JLabel("Prénom: "), gNormal);
        gNormal.gridx = 1;
        normalRecherchePanel.add(recherche_prenom_field, gNormal);
        
        // Poste field
        normalRow++;
        gNormal.gridx = 0; gNormal.gridy = normalRow;
        normalRecherchePanel.add(new JLabel("Poste: "), gNormal);
        gNormal.gridx = 1;
        normalRecherchePanel.add(recherche_poste_field, gNormal);
        
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
        
        // CIN field
        gAdv.gridx = 0; gAdv.gridy = advRow; gAdv.weightx = 0.0; gAdv.fill = GridBagConstraints.NONE;
        advancedRecherchePanel.add(new JLabel("CIN: "), gAdv);
        gAdv.gridx = 1; gAdv.fill = GridBagConstraints.NONE; gAdv.weightx = 0.0;
        advancedRecherchePanel.add(recherche_cin_field, gAdv);
        
        // Salaire field
        advRow++;
        gAdv.gridx = 0; gAdv.gridy = advRow;
        advancedRecherchePanel.add(new JLabel("Salaire: "), gAdv);
        gAdv.gridx = 1;
        advancedRecherchePanel.add(recherche_salaire_field, gAdv);
        
        // Date Naissance field
        advRow++;
        gAdv.gridx = 0; gAdv.gridy = advRow;
        advancedRecherchePanel.add(new JLabel("Date Naissance: "), gAdv);
        gAdv.gridx = 1;
        advancedRecherchePanel.add(recherche_date_naissance_spinner, gAdv);
        
        // Date Embauche field
        advRow++;
        gAdv.gridx = 0; gAdv.gridy = advRow;
        advancedRecherchePanel.add(new JLabel("Date Embauche: "), gAdv);
        gAdv.gridx = 1;
        advancedRecherchePanel.add(recherche_date_embauche_spinner, gAdv);
        
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
        recherche_employe.add(rechercheContainer, BorderLayout.CENTER);
    }
    
    private void setupDisponibiliteTab() {
        disponibilite_panel.setLayout(new BorderLayout());
        
        // Configure the table
        configureTable(disponibilite_table, disponibiliteTableModel);
        
        // Set column widths for disponibilite table
        disponibilite_table.getColumnModel().getColumn(0).setPreferredWidth(80);   // ID Employé
        disponibilite_table.getColumnModel().getColumn(1).setPreferredWidth(100);  // Nom
        disponibilite_table.getColumnModel().getColumn(2).setPreferredWidth(100);  // Prénom
        disponibilite_table.getColumnModel().getColumn(3).setPreferredWidth(100);  // Poste
        disponibilite_table.getColumnModel().getColumn(4).setPreferredWidth(80);   // Jour
        disponibilite_table.getColumnModel().getColumn(5).setPreferredWidth(80);   // Heure début
        disponibilite_table.getColumnModel().getColumn(6).setPreferredWidth(80);   // Heure fin
        
        disponibilite_scroll = createScrollPane(disponibilite_table);
        disponibilite_panel.add(disponibilite_scroll, BorderLayout.CENTER);

        // Create top panel for form and controls
        JPanel topPanel = new JPanel(new BorderLayout());
        
        // Create form panel for adding disponibilité
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Ajouter Disponibilité"));
        GridBagConstraints gForm = new GridBagConstraints();
        gForm.insets = new Insets(5, 10, 5, 10);
        gForm.anchor = GridBagConstraints.WEST;
        
        int formRow = 0;
        
        // Row 0: Nom
        gForm.gridx = 0; gForm.gridy = formRow; gForm.weightx = 0.0; gForm.fill = GridBagConstraints.NONE;
        formPanel.add(dispo_nom_label, gForm);
        gForm.gridx = 1; gForm.fill = GridBagConstraints.NONE; gForm.weightx = 0.0;
        formPanel.add(dispo_nom_field, gForm);
        
        // Row 1: Prénom
        formRow++;
        gForm.gridx = 0; gForm.gridy = formRow;
        formPanel.add(dispo_prenom_label, gForm);
        gForm.gridx = 1;
        formPanel.add(dispo_prenom_field, gForm);
        
        // Row 2: Poste
        formRow++;
        gForm.gridx = 0; gForm.gridy = formRow;
        formPanel.add(dispo_poste_label, gForm);
        gForm.gridx = 1;
        formPanel.add(dispo_poste_field, gForm);
        
        // Row 3: Jour
        formRow++;
        gForm.gridx = 0; gForm.gridy = formRow;
        formPanel.add(dispo_jour_label, gForm);
        gForm.gridx = 1;
        formPanel.add(dispo_jour_field, gForm);
        
        // Row 4: Heure début
        formRow++;
        gForm.gridx = 0; gForm.gridy = formRow;
        formPanel.add(dispo_heure_debut_label, gForm);
        gForm.gridx = 1;
        formPanel.add(dispo_heure_debut_field, gForm);
        
        // Row 5: Heure fin
        formRow++;
        gForm.gridx = 0; gForm.gridy = formRow;
        formPanel.add(dispo_heure_fin_label, gForm);
        gForm.gridx = 1;
        formPanel.add(dispo_heure_fin_field, gForm);
        
        // Row 6: Ajouter button
        formRow++;
        JPanel formButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        formButtonPanel.add(dispo_ajouterButton);
        gForm.gridx = 0; gForm.gridy = formRow; gForm.gridwidth = 2; gForm.fill = GridBagConstraints.CENTER;
        formPanel.add(formButtonPanel, gForm);
        
        // Add form panel to top panel
        topPanel.add(formPanel, BorderLayout.CENTER);
        
        // Create control panel with label and buttons
        JPanel control_panel = new JPanel(new BorderLayout());
        
        // Create left panel for total label
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        total_disponibilite_label.setFont(new Font("Arial", Font.BOLD, 15));
        total_disponibilite_label.setText("Total Disponibilités: 0");
        leftPanel.add(total_disponibilite_label);
        
        // Create right panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        buttonPanel.add(disponibilite_modifierButton);
        buttonPanel.add(disponibilite_supprimerButton);
        
        // Add components to control panel
        control_panel.add(leftPanel, BorderLayout.WEST);
        control_panel.add(buttonPanel, BorderLayout.EAST);
        control_panel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        topPanel.add(control_panel, BorderLayout.NORTH);
        
        disponibilite_panel.add(topPanel, BorderLayout.NORTH);
    }
    
    // Helper methods
    public void updateTotalEmployes(int count) {
        total_employe_label.setText("Total Employés: " + count);
    }
    
    public void updateTotalDisponibilites(int count) {
        total_disponibilite_label.setText("Total Disponibilités: " + count);
    }
    
    // Getters for selected rows
    public Integer getSelectedAffichageId() {
        int selectedRow = affichage_table.getSelectedRow();
        if (selectedRow != -1) {
            return (Integer) affichage_table.getValueAt(selectedRow, 0);
        }
        return null;
    }
    
    public Integer getSelectedDisponibiliteId() {
        int selectedRow = disponibilite_table.getSelectedRow();
        if (selectedRow != -1) {
            return (Integer) disponibilite_table.getValueAt(selectedRow, 0);
        }
        return null;
    }
    
    // Getters for table models
    public DefaultTableModel getAffichageTableModel() {
        return affichageTableModel;
    }
    
    public DefaultTableModel getDisponibiliteTableModel() {
        return disponibiliteTableModel;
    }
    
    // Getters for tables
    public JTable getAffichageTable() {
        return affichage_table;
    }
    
    public JTable getDisponibiliteTable() {
        return disponibilite_table;
    }
    
    // Getters for buttons
    public JButton getAfficherButton() { return afficherButton; }
    public JButton getSupprimerButton() { return supprimerButton; }
    public JButton getModifierButton() { return modifierButton; }
    public JButton getAjouterButton() { return ajouterButton; }
    public JButton getRechercheSimpleButton() { return rechercheSimpleButton; }
    public JButton getRechercheAvanceButton() { return rechercheAvanceButton; }
    public JButton getDisponibiliteModifierButton() { return disponibilite_modifierButton; }
    public JButton getDisponibiliteSupprimerButton() { return disponibilite_supprimerButton; }
    public JButton getDispoAjouterButton() { return dispo_ajouterButton; }
    
    // Getters for text fields in Ajout tab
    public JTextField getNomField() { return nom_field; }
    public JTextField getPrenomField() { return prenom_field; }
    public JTextField getCinField() { return cin_field; }
    public JTextField getTelField() { return tel_field; }
    public JTextField getEmailField() { return email_field; }
    public JTextField getPosteField() { return poste_field; }
    public JTextField getSalaireField() { return salaire_field; }
    public JSpinner getDateNaissanceSpinner() { return date_naissance_spinner; }
    public JSpinner getDateEmbaucheSpinner() { return date_embauche_spinner; }
    
    // Getters for text fields in Recherche tab
    public JTextField getRechercheNomField() { return recherche_nom_field; }
    public JTextField getRecherchePrenomField() { return recherche_prenom_field; }
    public JTextField getRechercheCinField() { return recherche_cin_field; }
    public JTextField getRecherchePosteField() { return recherche_poste_field; }
    public JTextField getRechercheSalaireField() { return recherche_salaire_field; }
    public JSpinner getRechercheDateNaissanceSpinner() { return recherche_date_naissance_spinner; }
    public JSpinner getRechercheDateEmbaucheSpinner() { return recherche_date_embauche_spinner; }
    
    // Getters for text fields in Disponibilité tab
    public JTextField getDispoNomField() { return dispo_nom_field; }
    public JTextField getDispoPrenomField() { return dispo_prenom_field; }
    public JTextField getDispoPosteField() { return dispo_poste_field; }
    public JTextField getDispoJourField() { return dispo_jour_field; }
    public JTextField getDispoHeureDebutField() { return dispo_heure_debut_field; }
    public JTextField getDispoHeureFinField() { return dispo_heure_fin_field; }
    
    // Helper methods for dates
    public String getSelectedDateNaissance() {
        java.util.Date date = (java.util.Date) date_naissance_spinner.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate().format(formatter);
    }
    
    public String getSelectedDateEmbauche() {
        java.util.Date date = (java.util.Date) date_embauche_spinner.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate().format(formatter);
    }
    
    public String getRechercheSelectedDateNaissance() {
        java.util.Date date = (java.util.Date) recherche_date_naissance_spinner.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate().format(formatter);
    }
    
    public String getRechercheSelectedDateEmbauche() {
        java.util.Date date = (java.util.Date) recherche_date_embauche_spinner.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate().format(formatter);
    }
}
package com.gestion_cabinet_medical.couche_presentation.Médecin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Consultation_pane extends JTabbedPane {
    private JPanel Affichage_consultation, Consultation_afaire, Consultation_faites, recherche_consultation;
    private JTable affichage_table, afaire_table, faites_table;
    private JScrollPane affichage_scroll, afaire_scroll, faites_scroll;
    private JLabel total_consultation_label, total_afaire_label, total_faites_label;
    private JButton afficherButton, faitButton, reporterButton;
    private JButton rechercheSimpleButton, rechercheAvanceButton;
    private JButton ordonnanceButton, rendezvousButton, patientButton;
    
    // Recherche Consultation fields
    private JTextField recherche_medecin_field, recherche_type_field;
    private JSpinner recherche_date_spinner, recherche_dateRDV_spinner;
    
    private DefaultTableModel affichageTableModel, afaireTableModel, faitesTableModel;

    public Consultation_pane() {
        // Initialize panels and components
        Affichage_consultation = new JPanel();
        Consultation_afaire = new JPanel();
        Consultation_faites = new JPanel();
        recherche_consultation = new JPanel();
        
        // Initialize tables and scroll panes
        affichage_table = new JTable();
        afaire_table = new JTable();
        faites_table = new JTable();
        affichage_scroll = new JScrollPane();
        afaire_scroll = new JScrollPane();
        faites_scroll = new JScrollPane();
        
        // Initialize labels
        total_consultation_label = new JLabel();
        total_afaire_label = new JLabel();
        total_faites_label = new JLabel();
        
        // Initialize buttons
        afficherButton = new JButton("Afficher");
        faitButton = new JButton("Fait !");
        reporterButton = new JButton("Reporter");
        rechercheSimpleButton = new JButton("Rechercher (Simple)");
        rechercheAvanceButton = new JButton("Rechercher (Avancée)");
        ordonnanceButton = new JButton("Ordonnance");
        rendezvousButton = new JButton("Rendez-vous");
        patientButton = new JButton("Patient");
        
        // Initialize recherche fields
        initRechercheFields();
        
        // Set up table models
        setupTableModels();
        
        // SET UP AFFICHAGE TAB
        setupAffichageTab();
        
        // SET UP À FAIRE TAB
        setupAfaireTab();
        
        // SET UP FAITES TAB
        setupFaitesTab();
        
        // SET UP RECHERCHE TAB
        setupRechercheTab();

        this.addTab("Affichage", Affichage_consultation);
        this.addTab("À faire", Consultation_afaire);
        this.addTab("Faites", Consultation_faites);
        this.addTab("Recherche", recherche_consultation);
    }
    
    private void initRechercheFields() {
        // Recherche text fields (using only Médecin, date, type, dateRDV)
        recherche_medecin_field = new JTextField(15);
        recherche_type_field = new JTextField(15);
        
        // Date spinner for consultation date
        SpinnerDateModel dateModel = new SpinnerDateModel(
            java.sql.Date.valueOf(LocalDate.now()),
            null,
            null,
            java.util.Calendar.DAY_OF_MONTH
        );
        recherche_date_spinner = new JSpinner(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(recherche_date_spinner, "dd/MM/yyyy");
        recherche_date_spinner.setEditor(dateEditor);
        recherche_date_spinner.setPreferredSize(new Dimension(120, 25));
        
        // Date spinner for RDV date
        SpinnerDateModel dateRDVModel = new SpinnerDateModel(
            java.sql.Date.valueOf(LocalDate.now()),
            null,
            null,
            java.util.Calendar.DAY_OF_MONTH
        );
        recherche_dateRDV_spinner = new JSpinner(dateRDVModel);
        JSpinner.DateEditor dateRDVEditor = new JSpinner.DateEditor(recherche_dateRDV_spinner, "dd/MM/yyyy");
        recherche_dateRDV_spinner.setEditor(dateRDVEditor);
        recherche_dateRDV_spinner.setPreferredSize(new Dimension(120, 25));
    }
    
    private void setupTableModels() {
        String[] columnNames = {"Num", "ID Médecin", "Médecin", "Num RDV", "Date", "Type"};
        
        affichageTableModel = new DefaultTableModel(new Object[][] {}, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        afaireTableModel = new DefaultTableModel(new Object[][] {}, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        faitesTableModel = new DefaultTableModel(new Object[][] {}, columnNames) {
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
        
        // Set column widths
        table.getColumnModel().getColumn(0).setPreferredWidth(60);   // Num
        table.getColumnModel().getColumn(1).setPreferredWidth(80);   // ID Médecin
        table.getColumnModel().getColumn(2).setPreferredWidth(120);  // Médecin
        table.getColumnModel().getColumn(3).setPreferredWidth(80);   // Num RDV
        table.getColumnModel().getColumn(4).setPreferredWidth(100);  // Date
        table.getColumnModel().getColumn(5).setPreferredWidth(100);  // Type
    }
    
    private JScrollPane createScrollPane(JTable table) {
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        return scrollPane;
    }
    
    private void setupAffichageTab() {
        Affichage_consultation.setLayout(new BorderLayout());
        
        // Configure the table
        configureTable(affichage_table, affichageTableModel);
        affichage_scroll = createScrollPane(affichage_table);
        Affichage_consultation.add(affichage_scroll, BorderLayout.CENTER);

        // Set up the total panel with button
        JPanel total_panel = new JPanel(new BorderLayout());
        
        // Create left panel for total label
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        total_consultation_label.setFont(new Font("Arial", Font.BOLD, 15));
        total_consultation_label.setText("Total Consultations: 0");
        leftPanel.add(total_consultation_label);
        
        // Create right panel for button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        buttonPanel.add(afficherButton);
        
        // Add components to total panel
        total_panel.add(leftPanel, BorderLayout.WEST);
        total_panel.add(buttonPanel, BorderLayout.EAST);
        total_panel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        Affichage_consultation.add(total_panel, BorderLayout.NORTH);
    }
    
    private void setupAfaireTab() {
        Consultation_afaire.setLayout(new BorderLayout());
        
        // Configure the table
        configureTable(afaire_table, afaireTableModel);
        afaire_scroll = createScrollPane(afaire_table);
        Consultation_afaire.add(afaire_scroll, BorderLayout.CENTER);

        // Set up the total panel with buttons
        JPanel total_afaire_panel = new JPanel(new BorderLayout());
        
        // Create left panel for total label
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        total_afaire_label.setFont(new Font("Arial", Font.BOLD, 15));
        total_afaire_label.setText("Total à faire: 0");
        leftPanel.add(total_afaire_label);
        
        // Create right panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        buttonPanel.add(faitButton);
        buttonPanel.add(reporterButton);
        
        // Add components to total panel
        total_afaire_panel.add(leftPanel, BorderLayout.WEST);
        total_afaire_panel.add(buttonPanel, BorderLayout.EAST);
        total_afaire_panel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        Consultation_afaire.add(total_afaire_panel, BorderLayout.NORTH);
    }
    
    private void setupFaitesTab() {
        Consultation_faites.setLayout(new BorderLayout());
        
        // Configure the table
        configureTable(faites_table, faitesTableModel);
        faites_scroll = createScrollPane(faites_table);
        Consultation_faites.add(faites_scroll, BorderLayout.CENTER);

        // Set up the total panel with label
        JPanel total_faites_panel = new JPanel(new BorderLayout());
        
        // Create left panel for total label
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        total_faites_label.setFont(new Font("Arial", Font.BOLD, 15));
        total_faites_label.setText("Total Faites: 0");
        leftPanel.add(total_faites_label);
        
        // Add components to total panel
        total_faites_panel.add(leftPanel, BorderLayout.WEST);
        total_faites_panel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        Consultation_faites.add(total_faites_panel, BorderLayout.NORTH);
    }
    
    private void setupRechercheTab() {
        recherche_consultation.setLayout(new BorderLayout());
        
        // Create title label
        JLabel rechercheTitleLabel = new JLabel("Recherche Consultation", SwingConstants.CENTER);
        rechercheTitleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        rechercheTitleLabel.setForeground(new Color(0, 70, 140));
        rechercheTitleLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 20, 0));
        
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.add(rechercheTitleLabel, BorderLayout.CENTER);
        recherche_consultation.add(titlePanel, BorderLayout.NORTH);
        
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
        normalRecherchePanel.add(new JLabel("Médecin: "), gNormal);
        gNormal.gridx = 1; gNormal.fill = GridBagConstraints.NONE; gNormal.weightx = 0.0;
        normalRecherchePanel.add(recherche_medecin_field, gNormal);
        
        // Type field
        normalRow++;
        gNormal.gridx = 0; gNormal.gridy = normalRow;
        normalRecherchePanel.add(new JLabel("Type: "), gNormal);
        gNormal.gridx = 1;
        normalRecherchePanel.add(recherche_type_field, gNormal);
        
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
        
        // Date field (Consultation Date)
        gAdv.gridx = 0; gAdv.gridy = advRow; gAdv.weightx = 0.0; gAdv.fill = GridBagConstraints.NONE;
        advancedRecherchePanel.add(new JLabel("Date Consultation: "), gAdv);
        gAdv.gridx = 1; gAdv.fill = GridBagConstraints.NONE; gAdv.weightx = 0.0;
        advancedRecherchePanel.add(recherche_date_spinner, gAdv);
        
        // Date RDV field
        advRow++;
        gAdv.gridx = 0; gAdv.gridy = advRow;
        advancedRecherchePanel.add(new JLabel("Date RDV: "), gAdv);
        gAdv.gridx = 1;
        advancedRecherchePanel.add(recherche_dateRDV_spinner, gAdv);
        
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
        specialButtonsPanel.add(rendezvousButton);
        specialButtonsPanel.add(patientButton);
        specialButtonsPanel.setBorder(BorderFactory.createTitledBorder("Actions"));
        
        rechercheContainer.add(specialButtonsPanel, BorderLayout.SOUTH);
        
        recherche_consultation.add(rechercheContainer, BorderLayout.CENTER);
    }
    
    // Helper methods
    public void updateTotalConsultations(int count) {
        total_consultation_label.setText("Total Consultations: " + count);
    }
    
    public void updateTotalAfaire(int count) {
        total_afaire_label.setText("Total à faire: " + count);
    }
    
    public void updateTotalFaites(int count) {
        total_faites_label.setText("Total Faites: " + count);
    }
    
    // Getters for selected consultation numbers
    public Integer getSelectedAffichageNum() {
        int selectedRow = affichage_table.getSelectedRow();
        if (selectedRow != -1) {
            return (Integer) affichage_table.getValueAt(selectedRow, 0);
        }
        return null;
    }
    
    public Integer getSelectedAfaireNum() {
        int selectedRow = afaire_table.getSelectedRow();
        if (selectedRow != -1) {
            return (Integer) afaire_table.getValueAt(selectedRow, 0);
        }
        return null;
    }
    
    public Integer getSelectedFaitesNum() {
        int selectedRow = faites_table.getSelectedRow();
        if (selectedRow != -1) {
            return (Integer) faites_table.getValueAt(selectedRow, 0);
        }
        return null;
    }
    
    // Getters for table models
    public DefaultTableModel getAffichageTableModel() {
        return affichageTableModel;
    }
    
    public DefaultTableModel getAfaireTableModel() {
        return afaireTableModel;
    }
    
    public DefaultTableModel getFaitesTableModel() {
        return faitesTableModel;
    }
    
    // Getters for tables
    public JTable getAffichageTable() {
        return affichage_table;
    }
    
    public JTable getAfaireTable() {
        return afaire_table;
    }
    
    public JTable getFaitesTable() {
        return faites_table;
    }
    
    // Getters for buttons
    public JButton getAfficherButton() { return afficherButton; }
    public JButton getFaitButton() { return faitButton; }
    public JButton getReporterButton() { return reporterButton; }
    public JButton getRechercheSimpleButton() { return rechercheSimpleButton; }
    public JButton getRechercheAvanceButton() { return rechercheAvanceButton; }
    public JButton getOrdonnanceButton() { return ordonnanceButton; }
    public JButton getRendezvousButton() { return rendezvousButton; }
    public JButton getPatientButton() { return patientButton; }
    
    // Getters for recherche fields
    public JTextField getRechercheMedecinField() { return recherche_medecin_field; }
    public JTextField getRechercheTypeField() { return recherche_type_field; }
    public JSpinner getRechercheDateSpinner() { return recherche_date_spinner; }
    public JSpinner getRechercheDateRDVSpinner() { return recherche_dateRDV_spinner; }
    
    // Helper methods for dates
    public String getRechercheSelectedDate() {
        java.util.Date date = (java.util.Date) recherche_date_spinner.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate().format(formatter);
    }
    
    public String getRechercheSelectedDateRDV() {
        java.util.Date date = (java.util.Date) recherche_dateRDV_spinner.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate().format(formatter);
    }
}
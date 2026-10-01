package com.gestion_cabinet_medical.couche_presentation.Réceptioniste;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RendezVous_pane extends JTabbedPane {
    private JPanel Affichage_rendezvous, total_panel, ajout_rendezvous, recherche_rendezvous;
    private JTable affichage_table;
    private JScrollPane affichage_scroll;
    private JLabel total_rendezvous_label;
    private JButton afficherButton, supprimerButton, modifierButton, ajouterButton;
    private JButton rechercheSimpleButton, rechercheAvanceButton;
    
    // Ajout RendezVous fields
    private JLabel receptioniste_label, patient_label, date_prise_label, date_passage_label;
    private JTextField receptioniste_field, patient_field;
    private JSpinner date_prise_spinner, date_passage_spinner;
    
    // Recherche fields
    private JTextField recherche_receptioniste_field, recherche_patient_field;
    private JSpinner recherche_date_prise_spinner, recherche_date_passage_spinner;
    
    private DefaultTableModel affichageTableModel;

    public RendezVous_pane() {
        // Set modern look for tabbed pane
        this.setBackground(new Color(245, 247, 250));
        
        // Initialize panels and components
        Affichage_rendezvous = new JPanel();
        total_panel = new JPanel();
        ajout_rendezvous = new JPanel();
        recherche_rendezvous = new JPanel();
        
        // Initialize tables and scroll panes
        affichage_table = new JTable();
        affichage_scroll = new JScrollPane();
        
        // Initialize labels
        total_rendezvous_label = new JLabel();
        
        // Initialize buttons with improved styling
        afficherButton = createStyledButton("Afficher", new Color(52, 152, 219));
        supprimerButton = createStyledButton("Supprimer", new Color(231, 76, 60));
        modifierButton = createStyledButton("Modifier", new Color(241, 196, 15));
        ajouterButton = createStyledButton("Ajouter Rendez-vous", new Color(46, 204, 113));
        rechercheSimpleButton = createStyledButton("Rechercher (Simple)", new Color(52, 152, 219));
        rechercheAvanceButton = createStyledButton("Rechercher (Avancée)", new Color(155, 89, 182));
        
        // Initialize fields
        initAjoutFields();
        initRechercheFields();
        
        // Set up table models
        setupTableModels();
        
        // SET UP AFFICHAGE TAB
        setupAffichageTab();
        
        // SET UP AJOUT TAB
        setupAjoutTab();
        
        // SET UP RECHERCHE TAB
        setupRechercheTab();

        // Add tabs with icons
        this.addTab("📋 Affichage", Affichage_rendezvous);
        this.addTab("➕ Ajout", ajout_rendezvous);
        this.addTab("🔍 Recherche", recherche_rendezvous);
        
        // Set tab colors
        this.setBackgroundAt(0, new Color(236, 240, 241));
        this.setBackgroundAt(1, new Color(236, 240, 241));
        this.setBackgroundAt(2, new Color(236, 240, 241));
    }
    
    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(color.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(color);
            }
        });
        
        return button;
    }
    
    private JTextField createStyledTextField() {
        JTextField field = new JTextField();
        field.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        return field;
    }
    
    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        label.setForeground(new Color(44, 62, 80));
        return label;
    }
    
    private void initAjoutFields() {
        // Réceptioniste field
        receptioniste_label = createStyledLabel("Réceptioniste: ");
        receptioniste_field = createStyledTextField();
        
        // Patient field
        patient_label = createStyledLabel("Patient: ");
        patient_field = createStyledTextField();
        
        // Date de Prise field
        date_prise_label = createStyledLabel("Date de Prise: ");
        SpinnerDateModel datePriseModel = new SpinnerDateModel(
            java.sql.Date.valueOf(LocalDate.now()),
            null,
            null,
            java.util.Calendar.DAY_OF_MONTH
        );
        date_prise_spinner = new JSpinner(datePriseModel);
        JSpinner.DateEditor datePriseEditor = new JSpinner.DateEditor(date_prise_spinner, "dd/MM/yyyy");
        date_prise_spinner.setEditor(datePriseEditor);
        date_prise_spinner.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        date_prise_spinner.setPreferredSize(new Dimension(120, 30));
        
        // Date de Passage field (default: today + 7 days)
        date_passage_label = createStyledLabel("Date de Passage: ");
        SpinnerDateModel datePassageModel = new SpinnerDateModel(
            java.sql.Date.valueOf(LocalDate.now().plusDays(7)),
            null,
            null,
            java.util.Calendar.DAY_OF_MONTH
        );
        date_passage_spinner = new JSpinner(datePassageModel);
        JSpinner.DateEditor datePassageEditor = new JSpinner.DateEditor(date_passage_spinner, "dd/MM/yyyy");
        date_passage_spinner.setEditor(datePassageEditor);
        date_passage_spinner.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        date_passage_spinner.setPreferredSize(new Dimension(120, 30));
    }
    
    private void initRechercheFields() {
        // Recherche fields
        recherche_receptioniste_field = createStyledTextField();
        recherche_patient_field = createStyledTextField();
        
        // Recherche date spinners
        SpinnerDateModel rechercheDatePriseModel = new SpinnerDateModel(
            java.sql.Date.valueOf(LocalDate.now()),
            null,
            null,
            java.util.Calendar.DAY_OF_MONTH
        );
        recherche_date_prise_spinner = new JSpinner(rechercheDatePriseModel);
        JSpinner.DateEditor rechercheDatePriseEditor = new JSpinner.DateEditor(recherche_date_prise_spinner, "dd/MM/yyyy");
        recherche_date_prise_spinner.setEditor(rechercheDatePriseEditor);
        recherche_date_prise_spinner.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        recherche_date_prise_spinner.setPreferredSize(new Dimension(120, 30));
        
        SpinnerDateModel rechercheDatePassageModel = new SpinnerDateModel(
            java.sql.Date.valueOf(LocalDate.now()),
            null,
            null,
            java.util.Calendar.DAY_OF_MONTH
        );
        recherche_date_passage_spinner = new JSpinner(rechercheDatePassageModel);
        JSpinner.DateEditor rechercheDatePassageEditor = new JSpinner.DateEditor(recherche_date_passage_spinner, "dd/MM/yyyy");
        recherche_date_passage_spinner.setEditor(rechercheDatePassageEditor);
        recherche_date_passage_spinner.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        recherche_date_passage_spinner.setPreferredSize(new Dimension(120, 30));
    }
    
    private void setupTableModels() {
        // Affichage table model
        affichageTableModel = new DefaultTableModel(new Object[][] {},
            new String[] {
                "Num", "Réceptioniste", "Patient", "Date de Prise", "Date de Passage"
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
        table.setRowHeight(30);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setAutoCreateRowSorter(true);
        
        // Configure table header
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 12));
        header.setBackground(new Color(52, 73, 94));
        header.setForeground(Color.WHITE);
        header.setReorderingAllowed(false);
        
        // Configure table appearance
        table.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        table.setGridColor(Color.LIGHT_GRAY);
        table.setShowGrid(true);
        table.setIntercellSpacing(new Dimension(1, 1));
        table.setSelectionBackground(new Color(220, 237, 255));
        table.setSelectionForeground(Color.BLACK);
        
        // Alternate row colors
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(248, 248, 248));
                }
                setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 8));
                return c;
            }
        });
    }
    
    private JScrollPane createScrollPane(JTable table) {
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        return scrollPane;
    }
    
    private void setupAffichageTab() {
        Affichage_rendezvous.setLayout(new BorderLayout());
        Affichage_rendezvous.setBackground(new Color(245, 247, 250));
        
        // Create header panel with gradient
        JPanel headerPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(52, 152, 219), 
                                                    getWidth(), 0, new Color(41, 128, 185));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        headerPanel.setPreferredSize(new Dimension(0, 80));
        
        JLabel titleLabel = new JLabel("📋 Gestion des Rendez-vous", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        
        // Stats panel
        JPanel statsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statsPanel.setOpaque(false);
        statsPanel.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 0));
        
        total_rendezvous_label.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
        total_rendezvous_label.setText("📊 Total Rendez-vous: 0");
        total_rendezvous_label.setForeground(Color.WHITE);
        statsPanel.add(total_rendezvous_label);
        
        headerPanel.add(statsPanel, BorderLayout.SOUTH);
        Affichage_rendezvous.add(headerPanel, BorderLayout.NORTH);
        
        // Configure the table
        configureTable(affichage_table, affichageTableModel);
        
        // Set column widths for affichage table
        affichage_table.getColumnModel().getColumn(0).setPreferredWidth(60);   // Num
        affichage_table.getColumnModel().getColumn(1).setPreferredWidth(120);  // Réceptioniste
        affichage_table.getColumnModel().getColumn(2).setPreferredWidth(120);  // Patient
        affichage_table.getColumnModel().getColumn(3).setPreferredWidth(100);  // Date de Prise
        affichage_table.getColumnModel().getColumn(4).setPreferredWidth(100);  // Date de Passage
        
        affichage_scroll = createScrollPane(affichage_table);
        Affichage_rendezvous.add(affichage_scroll, BorderLayout.CENTER);

        // Set up the control panel with buttons
        JPanel control_panel = new JPanel(new BorderLayout());
        control_panel.setBackground(Color.WHITE);
        control_panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220, 220, 220)),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        
        // Create button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.add(afficherButton);
        buttonPanel.add(modifierButton);
        buttonPanel.add(supprimerButton);
        
        control_panel.add(buttonPanel, BorderLayout.EAST);
        
        Affichage_rendezvous.add(control_panel, BorderLayout.SOUTH);
    }
    
    private void setupAjoutTab() {
        ajout_rendezvous.setLayout(new BorderLayout());
        ajout_rendezvous.setBackground(new Color(245, 247, 250));
        
        // Create header panel with gradient
        JPanel headerPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(46, 204, 113), 
                                                    getWidth(), 0, new Color(39, 174, 96));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        headerPanel.setPreferredSize(new Dimension(0, 80));
        
        JLabel ajoutTitleLabel = new JLabel("➕ Ajouter un Rendez-vous", SwingConstants.CENTER);
        ajoutTitleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        ajoutTitleLabel.setForeground(Color.WHITE);
        headerPanel.add(ajoutTitleLabel, BorderLayout.CENTER);
        
        ajout_rendezvous.add(headerPanel, BorderLayout.NORTH);
        
        // Create main form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(230, 230, 230)),
            BorderFactory.createEmptyBorder(30, 30, 30, 30)
        ));
        
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(12, 15, 12, 15);
        g.anchor = GridBagConstraints.WEST;
        
        int row = 0;
        
        // Row 0: Réceptioniste
        g.gridx = 0; g.gridy = row; g.weightx = 0.0; g.fill = GridBagConstraints.NONE;
        formPanel.add(receptioniste_label, g);
        g.gridx = 1; g.fill = GridBagConstraints.HORIZONTAL; g.weightx = 1.0;
        JPanel receptionistePanel = new JPanel(new BorderLayout());
        receptionistePanel.add(receptioniste_field, BorderLayout.CENTER);
        formPanel.add(receptionistePanel, g);
        
        // Row 1: Patient
        row++;
        g.gridx = 0; g.gridy = row;
        formPanel.add(patient_label, g);
        g.gridx = 1;
        JPanel patientPanel = new JPanel(new BorderLayout());
        patientPanel.add(patient_field, BorderLayout.CENTER);
        formPanel.add(patientPanel, g);
        
        // Row 2: Date de Prise
        row++;
        g.gridx = 0; g.gridy = row;
        formPanel.add(date_prise_label, g);
        g.gridx = 1;
        JPanel datePrisePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        datePrisePanel.add(date_prise_spinner);
        formPanel.add(datePrisePanel, g);
        
        // Row 3: Date de Passage
        row++;
        g.gridx = 0; g.gridy = row;
        formPanel.add(date_passage_label, g);
        g.gridx = 1;
        JPanel datePassagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        datePassagePanel.add(date_passage_spinner);
        formPanel.add(datePassagePanel, g);
        
        // Row 4: Button panel
        row++;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.setOpaque(false);
        buttonPanel.add(ajouterButton);
        g.gridx = 0; g.gridy = row; g.gridwidth = 2; g.fill = GridBagConstraints.CENTER;
        formPanel.add(buttonPanel, g);
        
        // Center the form
        JPanel wrapperPanel = new JPanel(new GridBagLayout());
        wrapperPanel.setBackground(new Color(245, 247, 250));
        wrapperPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        wrapperPanel.add(formPanel);
        
        ajout_rendezvous.add(wrapperPanel, BorderLayout.CENTER);
    }
    
    private void setupRechercheTab() {
        recherche_rendezvous.setLayout(new BorderLayout());
        recherche_rendezvous.setBackground(new Color(245, 247, 250));
        
        // Create header panel with gradient
        JPanel headerPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(155, 89, 182), 
                                                    getWidth(), 0, new Color(142, 68, 173));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        headerPanel.setPreferredSize(new Dimension(0, 80));
        
        JLabel rechercheTitleLabel = new JLabel("🔍 Recherche de Rendez-vous", SwingConstants.CENTER);
        rechercheTitleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        rechercheTitleLabel.setForeground(Color.WHITE);
        headerPanel.add(rechercheTitleLabel, BorderLayout.CENTER);
        
        recherche_rendezvous.add(headerPanel, BorderLayout.NORTH);
        
        // Main container panel for the search forms
        JPanel rechercheContainer = new JPanel(new GridLayout(1, 2, 20, 0));
        rechercheContainer.setBackground(new Color(245, 247, 250));
        rechercheContainer.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        // Normal Recherche Panel
        JPanel normalRecherchePanel = createSearchPanel(
            "Recherche Simple", 
            new Color(52, 152, 219),
            new String[]{"Réceptioniste:", "Patient:"},
            new Component[]{recherche_receptioniste_field, recherche_patient_field},
            rechercheSimpleButton
        );
        
        // Advanced Recherche Panel
        JPanel advancedRecherchePanel = createSearchPanel(
            "Recherche Avancée", 
            new Color(155, 89, 182),
            new String[]{"Date de Prise:", "Date de Passage:"},
            new Component[]{recherche_date_prise_spinner, recherche_date_passage_spinner},
            rechercheAvanceButton
        );
        
        rechercheContainer.add(normalRecherchePanel);
        rechercheContainer.add(advancedRecherchePanel);
        
        recherche_rendezvous.add(rechercheContainer, BorderLayout.CENTER);
    }
    
    private JPanel createSearchPanel(String title, Color color, String[] labels, Component[] fields, JButton button) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(color, 2),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        
        // Title
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(color);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        // Form
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(10, 5, 10, 5);
        g.anchor = GridBagConstraints.WEST;
        
        for (int i = 0; i < labels.length; i++) {
            g.gridx = 0; g.gridy = i; g.weightx = 0.0; g.fill = GridBagConstraints.NONE;
            formPanel.add(createStyledLabel(labels[i]), g);
            
            g.gridx = 1; g.fill = GridBagConstraints.HORIZONTAL; g.weightx = 1.0;
            if (fields[i] instanceof JSpinner) {
                JPanel spinnerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
                spinnerPanel.add(fields[i]);
                formPanel.add(spinnerPanel, g);
            } else {
                formPanel.add(fields[i], g);
            }
        }
        
        // Button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        buttonPanel.setOpaque(false);
        buttonPanel.add(button);
        
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    // Helper methods
    public void updateTotalRendezvous(int count) {
        total_rendezvous_label.setText("📊 Total Rendez-vous: " + count);
    }
    
    // Getters for selected rows
    public Integer getSelectedAffichageNum() {
        int selectedRow = affichage_table.getSelectedRow();
        if (selectedRow != -1) {
            return (Integer) affichage_table.getValueAt(selectedRow, 0);
        }
        return null;
    }
    
    // Getters for table models
    public DefaultTableModel getAffichageTableModel() {
        return affichageTableModel;
    }
    
    // Getters for tables
    public JTable getAffichageTable() {
        return affichage_table;
    }
    
    // Getters for buttons
    public JButton getAfficherButton() { return afficherButton; }
    public JButton getSupprimerButton() { return supprimerButton; }
    public JButton getModifierButton() { return modifierButton; }
    public JButton getAjouterButton() { return ajouterButton; }
    public JButton getRechercheSimpleButton() { return rechercheSimpleButton; }
    public JButton getRechercheAvanceButton() { return rechercheAvanceButton; }
    
    // Getters for text fields in Ajout tab
    public JTextField getReceptionisteField() { return receptioniste_field; }
    public JTextField getPatientField() { return patient_field; }
    public JSpinner getDatePriseSpinner() { return date_prise_spinner; }
    public JSpinner getDatePassageSpinner() { return date_passage_spinner; }
    
    // Getters for text fields in Recherche tab
    public JTextField getRechercheReceptionisteField() { return recherche_receptioniste_field; }
    public JTextField getRecherchePatientField() { return recherche_patient_field; }
    public JSpinner getRechercheDatePriseSpinner() { return recherche_date_prise_spinner; }
    public JSpinner getRechercheDatePassageSpinner() { return recherche_date_passage_spinner; }
    
    // Helper methods for dates
    public String getSelectedDatePrise() {
        java.util.Date date = (java.util.Date) date_prise_spinner.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate().format(formatter);
    }
    
    public String getSelectedDatePassage() {
        java.util.Date date = (java.util.Date) date_passage_spinner.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate().format(formatter);
    }
    
    public String getRechercheSelectedDatePrise() {
        java.util.Date date = (java.util.Date) recherche_date_prise_spinner.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate().format(formatter);
    }
    
    public String getRechercheSelectedDatePassage() {
        java.util.Date date = (java.util.Date) recherche_date_passage_spinner.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate().format(formatter);
    }
}
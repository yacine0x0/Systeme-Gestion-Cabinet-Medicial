package com.gestion_cabinet_medical.couche_presentation.Médecin;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import com.gestion_cabinet_medical.couche_presentation.Aide_pane;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Médecin_acceuil extends JFrame {

    private static final java.util.logging.Logger logger =
            java.util.logging.Logger.getLogger(Médecin_acceuil.class.getName());

    // ---- Colors for professional medical theme ----
    private final Color PRIMARY_COLOR = new Color(13, 71, 161); // Dark blue
    private final Color SECONDARY_COLOR = new Color(2, 136, 209); // Light blue
    private final Color ACCENT_COLOR = new Color(0, 150, 136); // Teal
    private final Color BACKGROUND_COLOR = new Color(250, 250, 255); // Light blueish white
    private final Color SIDEBAR_COLOR = new Color(25, 25, 35); // Dark sidebar
    private final Color MENU_HOVER = new Color(40, 40, 50); // Menu hover
    private final Color MENU_SELECTED = new Color(13, 71, 161); // Menu selected
    private final Color DASHBOARD_CARD_1 = new Color(227, 242, 253); // Light blue
    private final Color DASHBOARD_CARD_2 = new Color(232, 245, 233); // Light green
    private final Color DASHBOARD_CARD_3 = new Color(255, 243, 224); // Light orange
    private final Color DASHBOARD_CARD_4 = new Color(252, 228, 236); // Light pink
    private final Color DASHBOARD_CARD_5 = new Color(243, 229, 245); // Light purple
    private final Color DASHBOARD_CARD_6 = new Color(224, 242, 241); // Light teal

    // ---- Constructor ----
    public Médecin_acceuil() {
        initComponents();

        // ---- Make window full screen on any computer ----
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    // ---- Init Components (cleaned version) ----
    private void initComponents() {

        Menu_side = new JPanel();
        Session_panel = new JPanel();
        Médecin_label = new JLabel();
        Choice_menu_panel = new JPanel();
        Patient_button = new JToggleButton();
        Ordo_button = new JToggleButton();
        Consul_button = new JToggleButton();
        Fact_button = new JToggleButton();
        Emp_button = new JToggleButton();
        Aide_button = new JToggleButton();
        logo_auteur_panel = new JPanel();
        Clinicapro_logo = new JLabel();
        auteur_button = new JButton();
        Main_panel = new JPanel();
        Patient_tabs = new Patient_pane();
        Acceuil = new JToggleButton();
        Acceuil_panel = new JPanel();
        visiblePanel = new JPanel();
        visisbleTabbed = new JTabbedPane();
        Ordonnance_tabs = new Ordonnance_pane();
        Consultation_tabs = new Consultation_pane();
        Facture_tabs = new Facture_pane();
        Employe_tabs = new Employe_pane();
        Aide_tabs = new Aide_pane();

        // Set application icon
        try {
            setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon.png")));
        } catch (Exception e) {
            // Icon not found, continue without it
        }

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Clinica Pro - Logiciel Médical");
        getContentPane().setLayout(new BorderLayout());
        getContentPane().setBackground(BACKGROUND_COLOR);

        // ---------------- LEFT MENU ----------------
        Menu_side.setBackground(SIDEBAR_COLOR);
        Menu_side.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(50, 50, 60)));
        Menu_side.setLayout(new BorderLayout());
        Menu_side.setPreferredSize(new Dimension(220, getHeight()));

        // Session panel with doctor info
        Session_panel.setBackground(new Color(30, 30, 40));
        Session_panel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        Session_panel.setLayout(new BorderLayout());
        Session_panel.setPreferredSize(new Dimension(220, 120));
        
        // Doctor icon and name
        JPanel doctorInfoPanel = new JPanel(new BorderLayout(10, 10));
        doctorInfoPanel.setOpaque(false);
        
        // Doctor icon (using Unicode or emoji if available)
        JLabel doctorIcon = new JLabel("👨‍⚕️");
        doctorIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 36));
        doctorIcon.setHorizontalAlignment(SwingConstants.CENTER);
        
        Médecin_label.setHorizontalAlignment(SwingConstants.CENTER);
        Médecin_label.setText("<html><div style='text-align: center;'><b>Dr. Smith</b><br><small>Médecin Généraliste</small></div></html>");
        Médecin_label.setForeground(Color.WHITE);
        Médecin_label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        doctorInfoPanel.add(doctorIcon, BorderLayout.NORTH);
        doctorInfoPanel.add(Médecin_label, BorderLayout.CENTER);
        Session_panel.add(doctorInfoPanel, BorderLayout.CENTER);
        Menu_side.add(Session_panel, BorderLayout.NORTH);

        // Menu buttons panel
        Choice_menu_panel.setBackground(SIDEBAR_COLOR);
        Choice_menu_panel.setLayout(new BoxLayout(Choice_menu_panel, BoxLayout.Y_AXIS));
        Choice_menu_panel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        // Style menu buttons
        JToggleButton[] menuButtons = {Acceuil, Patient_button, Ordo_button, Consul_button, Fact_button, Emp_button, Aide_button};
        String[] buttonIcons = {"🏠", "👥", "📋", "🩺", "💰", "👨‍💼", "❓"};
        String[] buttonTexts = {"Accueil", "Patients", "Ordonnances", "Consultations", "Factures", "Employés", "Aide"};

        for (int i = 0; i < menuButtons.length; i++) {
            menuButtons[i].setText("  " + buttonIcons[i] + "  " + buttonTexts[i]);
            menuButtons[i].setHorizontalAlignment(SwingConstants.LEFT);
            menuButtons[i].setFont(new Font("Segoe UI", Font.PLAIN, 14));
            menuButtons[i].setBackground(SIDEBAR_COLOR);
            menuButtons[i].setForeground(new Color(200, 200, 210));
            menuButtons[i].setBorderPainted(false);
            menuButtons[i].setFocusPainted(false);
            menuButtons[i].setContentAreaFilled(false);
            menuButtons[i].setOpaque(true);
            menuButtons[i].setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
            menuButtons[i].setPreferredSize(new Dimension(200, 50));
            menuButtons[i].setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            
            // Add hover effect
            menuButtons[i].addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    if (!((JToggleButton)evt.getSource()).isSelected()) {
                        ((JToggleButton)evt.getSource()).setBackground(MENU_HOVER);
                    }
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    if (!((JToggleButton)evt.getSource()).isSelected()) {
                        ((JToggleButton)evt.getSource()).setBackground(SIDEBAR_COLOR);
                    }
                }
            });
            
            Choice_menu_panel.add(menuButtons[i]);
            if (i < menuButtons.length - 1) {
                Choice_menu_panel.add(Box.createRigidArea(new Dimension(0, 5)));
            }
        }

        Acceuil.setSelected(true);
        Acceuil.setBackground(MENU_SELECTED);
        Acceuil.setForeground(Color.WHITE);
        visiblePanel = Acceuil_panel;

        bg1.add(Ordo_button); bg1.add(Patient_button); bg1.add(Consul_button);
        bg1.add(Fact_button); bg1.add(Emp_button); bg1.add(Aide_button);
        bg1.add(Acceuil);

        Menu_side.add(Choice_menu_panel, BorderLayout.CENTER);

        // Logo and author panel
        logo_auteur_panel.setBackground(new Color(30, 30, 40));
        logo_auteur_panel.setLayout(new BorderLayout());
        logo_auteur_panel.setPreferredSize(new Dimension(220, 150));
        logo_auteur_panel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        
        // Clinicapro logo with medical symbol
        JPanel logoPanel = new JPanel(new BorderLayout());
        logoPanel.setOpaque(false);
        
        JLabel medicalIcon = new JLabel("➕");
        medicalIcon.setFont(new Font("Segoe UI", Font.PLAIN, 48));
        medicalIcon.setHorizontalAlignment(SwingConstants.CENTER);
        medicalIcon.setForeground(SECONDARY_COLOR);
        
        Clinicapro_logo.setHorizontalAlignment(SwingConstants.CENTER);
        Clinicapro_logo.setText("<html><div style='text-align: center; color: #4FC3F7; font-size: 18px; font-weight: bold;'>CLINICA<br>PRO</div></html>");
        Clinicapro_logo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        
        JLabel versionLabel = new JLabel("v2.0.1", SwingConstants.CENTER);
        versionLabel.setForeground(new Color(150, 150, 160));
        versionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        
        logoPanel.add(medicalIcon, BorderLayout.NORTH);
        logoPanel.add(Clinicapro_logo, BorderLayout.CENTER);
        logoPanel.add(versionLabel, BorderLayout.SOUTH);
        
        auteur_button.setText("© Équipe Développement");
        auteur_button.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        auteur_button.setForeground(new Color(180, 180, 190));
        auteur_button.setBackground(new Color(40, 40, 50));
        auteur_button.setBorderPainted(false);
        auteur_button.setFocusPainted(false);
        auteur_button.setContentAreaFilled(true);
        auteur_button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        auteur_button.setBorder(BorderFactory.createEmptyBorder(8, 5, 8, 5));

        logo_auteur_panel.add(logoPanel, BorderLayout.CENTER);
        logo_auteur_panel.add(auteur_button, BorderLayout.SOUTH);

        Menu_side.add(logo_auteur_panel, BorderLayout.SOUTH);
        getContentPane().add(Menu_side, BorderLayout.WEST);

        // ---------------- HOME PANEL ----------------
        Acceuil_panel.setBackground(BACKGROUND_COLOR);
        Acceuil_panel.setLayout(new BorderLayout());
        
        // Welcome header
        JPanel welcomeHeader = new JPanel(new BorderLayout());
        welcomeHeader.setBackground(Color.WHITE);
        welcomeHeader.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 240)),
            BorderFactory.createEmptyBorder(25, 30, 25, 30)
        ));
        
        JLabel welcomeLabel = new JLabel("Tableau de Bord Médical");
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        welcomeLabel.setForeground(PRIMARY_COLOR);
        
        JLabel dateLabel = new JLabel(new java.text.SimpleDateFormat("EEEE d MMMM yyyy").format(new java.util.Date()));
        dateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        dateLabel.setForeground(new Color(100, 100, 120));
        
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.setOpaque(false);
        JLabel timeLabel = new JLabel(new java.text.SimpleDateFormat("HH:mm").format(new java.util.Date()));
        timeLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        timeLabel.setForeground(ACCENT_COLOR);
        rightPanel.add(timeLabel);
        
        welcomeHeader.add(welcomeLabel, BorderLayout.WEST);
        welcomeHeader.add(dateLabel, BorderLayout.CENTER);
        welcomeHeader.add(rightPanel, BorderLayout.EAST);
        
        // Dashboard content
        JPanel dashboardContent = new JPanel(new GridBagLayout());
        dashboardContent.setBackground(BACKGROUND_COLOR);
        dashboardContent.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.BOTH;
        
        // Create 6 dashboard panels
        JPanel[] dashboardPanels = new JPanel[6];
        String[] panelTitles = {
            "Gestion des Patients",
            "Ordonnances en Cours",
            "Factures du Mois",
            "Statistiques Rapides",
            "Consultations du Jour",
            "Actions Rapides"
        };
        Color[] panelColors = {DASHBOARD_CARD_1, DASHBOARD_CARD_2, DASHBOARD_CARD_3, DASHBOARD_CARD_4, DASHBOARD_CARD_5, DASHBOARD_CARD_6};
        String[] panelIcons = {"👥", "📋", "💰", "📊", "🩺", "⚡"};
        
        // Panel 1: Patient Management
        dashboardPanels[0] = createEnhancedDashboardPanel(panelTitles[0], panelColors[0], panelIcons[0]);
        JPanel patientStatsPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        patientStatsPanel.setOpaque(false);
        
        JLabel totalPatients = createStatLabel("Patients totaux", "156", PRIMARY_COLOR);
        JLabel nouveauxPatients = createStatLabel("Nouveaux ce mois", "12", SECONDARY_COLOR);
        JLabel rdvAujourdhui = createStatLabel("RDV aujourd'hui", "8", ACCENT_COLOR);
        
        JButton btnVoirPatients = createStyledButton("👁 Voir Tous les Patients", PRIMARY_COLOR);
        btnVoirPatients.addActionListener(e -> Patient_button.doClick());
        
        patientStatsPanel.add(totalPatients);
        patientStatsPanel.add(nouveauxPatients);
        patientStatsPanel.add(rdvAujourdhui);
        patientStatsPanel.add(btnVoirPatients);
        dashboardPanels[0].add(patientStatsPanel, BorderLayout.CENTER);
        
        // Panel 2: Current Prescriptions
        dashboardPanels[1] = createEnhancedDashboardPanel(panelTitles[1], panelColors[1], panelIcons[1]);
        DefaultListModel<String> prescriptionModel = new DefaultListModel<>();
        prescriptionModel.addElement("🟢 Paracétamol 500mg - M. Dupont");
        prescriptionModel.addElement("🟡 Amoxicilline - Mme. Martin");
        prescriptionModel.addElement("🔴 Antihistaminique - M. Leroy");
        prescriptionModel.addElement("📋 ... 5 autres ordonnances");
        
        JList<String> prescriptionList = new JList<>(prescriptionModel);
        prescriptionList.setBackground(panelColors[1]);
        prescriptionList.setCellRenderer(new CustomListRenderer());
        JScrollPane scrollPane1 = new JScrollPane(prescriptionList);
        scrollPane1.setBorder(BorderFactory.createEmptyBorder());
        
        dashboardPanels[1].add(scrollPane1, BorderLayout.CENTER);
        
        JButton btnNouvelleOrdonnance = createStyledButton("➕ Nouvelle Ordonnance", new Color(76, 175, 80));
        btnNouvelleOrdonnance.addActionListener(e -> Ordo_button.doClick());
        dashboardPanels[1].add(btnNouvelleOrdonnance, BorderLayout.SOUTH);
        
        // Panel 3: Monthly Invoices
        dashboardPanels[2] = createEnhancedDashboardPanel(panelTitles[2], panelColors[2], panelIcons[2]);
        JPanel facturePanel = new JPanel(new BorderLayout(5, 5));
        facturePanel.setOpaque(false);
        
        JLabel montantTotal = new JLabel("€ 12,450.00", SwingConstants.CENTER);
        montantTotal.setFont(new Font("Segoe UI", Font.BOLD, 20));
        montantTotal.setForeground(new Color(46, 125, 50));
        montantTotal.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        
        DefaultListModel<String> factureModel = new DefaultListModel<>();
        factureModel.addElement("📄 Facture #245 - M. Bernard - 150.00€");
        factureModel.addElement("📄 Facture #246 - Mme. Petit - 85.00€");
        factureModel.addElement("📄 Facture #247 - M. Dubois - 200.00€");
        
        JList<String> factureList = new JList<>(factureModel);
        factureList.setBackground(panelColors[2]);
        factureList.setCellRenderer(new CustomListRenderer());
        
        facturePanel.add(montantTotal, BorderLayout.NORTH);
        facturePanel.add(new JScrollPane(factureList), BorderLayout.CENTER);
        
        JButton btnGestionFactures = createStyledButton("💳 Gérer Factures", new Color(121, 85, 72));
        btnGestionFactures.addActionListener(e -> Fact_button.doClick());
        facturePanel.add(btnGestionFactures, BorderLayout.SOUTH);
        
        dashboardPanels[2].add(facturePanel);
        
        // Panel 4: Quick Statistics
        dashboardPanels[3] = createEnhancedDashboardPanel(panelTitles[3], panelColors[3], panelIcons[3]);
        JPanel statsPanel = new JPanel(new GridLayout(4, 2, 10, 15));
        statsPanel.setOpaque(false);
        statsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        String[][] statsData = {
            {"Consultations:", "124", "📈"},
            {"Ordonnances:", "89", "📋"},
            {"Patients/mois:", "45", "👥"},
            {"Taux Occupation:", "78%", "📊"}
        };
        
        for (String[] stat : statsData) {
            JLabel label = new JLabel(stat[0]);
            label.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            JLabel value = new JLabel(stat[1] + "  " + stat[2]);
            value.setFont(new Font("Segoe UI", Font.BOLD, 14));
            value.setForeground(PRIMARY_COLOR);
            value.setHorizontalAlignment(SwingConstants.RIGHT);
            
            statsPanel.add(label);
            statsPanel.add(value);
        }
        
        dashboardPanels[3].add(statsPanel);
        
        // Panel 5: Today's Consultations
        dashboardPanels[4] = createEnhancedDashboardPanel(panelTitles[4], panelColors[4], panelIcons[4]);
        String[][] rdvData = {
            {"09:00", "M. Martin", "🟢"},
            {"10:30", "Mme. Durand", "🟡"},
            {"11:45", "M. Lefèvre", "🟢"},
            {"14:00", "Mme. Garcia", "🔴"},
            {"15:30", "M. Rousseau", "🟡"}
        };
        
        JPanel rdvPanel = new JPanel(new GridLayout(rdvData.length, 1, 5, 5));
        rdvPanel.setOpaque(false);
        
        for (String[] rdv : rdvData) {
            JPanel rdvItem = new JPanel(new BorderLayout());
            rdvItem.setOpaque(false);
            
            JLabel time = new JLabel(rdv[0]);
            time.setFont(new Font("Segoe UI", Font.BOLD, 12));
            time.setForeground(PRIMARY_COLOR);
            
            JLabel name = new JLabel(rdv[1]);
            name.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            
            JLabel status = new JLabel(rdv[2]);
            
            rdvItem.add(time, BorderLayout.WEST);
            rdvItem.add(name, BorderLayout.CENTER);
            rdvItem.add(status, BorderLayout.EAST);
            rdvPanel.add(rdvItem);
        }
        
        dashboardPanels[4].add(new JScrollPane(rdvPanel), BorderLayout.CENTER);
        
        JButton btnNouvelleConsultation = createStyledButton("🩺 Nouvelle Consultation", new Color(156, 39, 176));
        btnNouvelleConsultation.addActionListener(e -> Consul_button.doClick());
        dashboardPanels[4].add(btnNouvelleConsultation, BorderLayout.SOUTH);
        
        // Panel 6: Quick Actions
        dashboardPanels[5] = createEnhancedDashboardPanel(panelTitles[5], panelColors[5], panelIcons[5]);
        JPanel quickActionsPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        quickActionsPanel.setOpaque(false);
        quickActionsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        Object[][] quickActions = {
            {"➕", "Nouveau Patient", PRIMARY_COLOR},
            {"📝", "Prescrire", new Color(76, 175, 80)},
            {"💰", "Facturer", new Color(121, 85, 72)},
            {"📅", "Planning", new Color(255, 152, 0)},
            {"📈", "Rapports", new Color(156, 39, 176)},
            {"⚙️", "Paramètres", new Color(96, 125, 139)}
        };
        
        for (Object[] action : quickActions) {
            JButton btn = new JButton(action[0] + " " + action[1]);
            btn.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            btn.setBackground((Color)action[2]);
            btn.setForeground(Color.WHITE);
            btn.setBorderPainted(false);
            btn.setFocusPainted(false);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btn.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
            
            // Add hover effect
            btn.addMouseListener(new java.awt.event.MouseAdapter() {
                Color originalColor = (Color)action[2];
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    btn.setBackground(originalColor.brighter());
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    btn.setBackground(originalColor);
                }
            });
            
            // Add action listeners
            if (action[1].equals("Nouveau Patient")) btn.addActionListener(e -> Patient_button.doClick());
            else if (action[1].equals("Prescrire")) btn.addActionListener(e -> Ordo_button.doClick());
            else if (action[1].equals("Facturer")) btn.addActionListener(e -> Fact_button.doClick());
            else btn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Fonctionnalité à implémenter"));
            
            quickActionsPanel.add(btn);
        }
        
        dashboardPanels[5].add(quickActionsPanel);
        
        // Arrange panels in 2x3 grid
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        
        // Row 1
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        dashboardContent.add(dashboardPanels[0], gbc);
        
        gbc.gridx = 1;
        dashboardContent.add(dashboardPanels[1], gbc);
        
        gbc.gridx = 2;
        dashboardContent.add(dashboardPanels[2], gbc);
        
        // Row 2
        gbc.gridx = 0;
        gbc.gridy = 1;
        dashboardContent.add(dashboardPanels[3], gbc);
        
        gbc.gridx = 1;
        dashboardContent.add(dashboardPanels[4], gbc);
        
        gbc.gridx = 2;
        dashboardContent.add(dashboardPanels[5], gbc);
        
        Acceuil_panel.add(welcomeHeader, BorderLayout.NORTH);
        Acceuil_panel.add(dashboardContent, BorderLayout.CENTER);
        
        // Add a subtle footer
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footer.setBackground(Color.WHITE);
        footer.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(230, 230, 240)));
        JLabel footerLabel = new JLabel("© 2024 Clinica Pro - Logiciel Médical Professionnel");
        footerLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        footerLabel.setForeground(new Color(150, 150, 160));
        footer.add(footerLabel);
        Acceuil_panel.add(footer, BorderLayout.SOUTH);
        
        // ---------------- MAIN CONTENT ----------------
        Main_panel.setBackground(BACKGROUND_COLOR);
        Main_panel.setLayout(new BorderLayout());
        Main_panel.add(Acceuil_panel, BorderLayout.CENTER);
        getContentPane().add(Main_panel, BorderLayout.CENTER);

        Acceuil_panel.setVisible(true);
        Patient_tabs.setVisible(false);
        Ordonnance_tabs.setVisible(false);
        Consultation_tabs.setVisible(false);
        Facture_tabs.setVisible(false);
        Employe_tabs.setVisible(false);
        Aide_tabs.setVisible(false);

        pack();

        //------------------EVENTS------------------//

        Acceuil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg){
                updateMenuButtonStyle(Acceuil);
                switchPanel(Acceuil_panel);
            }
        });

        Patient_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg){
                updateMenuButtonStyle(Patient_button);
                switchToTabbedPanel(Patient_tabs);
            }
        });

        Ordo_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg){
                updateMenuButtonStyle(Ordo_button);
                switchToTabbedPanel(Ordonnance_tabs);
            }
        });

        Consul_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg){
                updateMenuButtonStyle(Consul_button);
                switchToTabbedPanel(Consultation_tabs);
            }
        });

        Fact_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg){
                updateMenuButtonStyle(Fact_button);
                switchToTabbedPanel(Facture_tabs);
            }
        });

        Emp_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg){
                updateMenuButtonStyle(Emp_button);
                switchToTabbedPanel(Employe_tabs);
            }
        });

        Aide_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg){
                updateMenuButtonStyle(Aide_button);
                switchToTabbedPanel(Aide_tabs);
            }
        });

        // Add keyboard shortcuts
        setupKeyboardShortcuts();
    }

    // Helper method to update menu button styles when selected
    private void updateMenuButtonStyle(JToggleButton selectedButton) {
        JToggleButton[] menuButtons = {Acceuil, Patient_button, Ordo_button, Consul_button, Fact_button, Emp_button, Aide_button};
        
        for (JToggleButton button : menuButtons) {
            if (button == selectedButton) {
                button.setBackground(MENU_SELECTED);
                button.setForeground(Color.WHITE);
                button.setFont(new Font("Segoe UI", Font.BOLD, 14));
            } else {
                button.setBackground(SIDEBAR_COLOR);
                button.setForeground(new Color(200, 200, 210));
                button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            }
        }
    }

    // Helper method to switch panels
    private void switchPanel(JPanel panel) {
        if (visisbleTabbed != null) {
            Main_panel.remove(visisbleTabbed);
        }
        if (visiblePanel != null) {
            Main_panel.remove(visiblePanel);
        }
        
        Main_panel.add(panel);
        panel.setVisible(true);
        Main_panel.revalidate();
        Main_panel.repaint();
        visiblePanel = panel;
    }

    // Helper method to switch to tabbed panels
    private void switchToTabbedPanel(JComponent tabbedPanel) {
        if (visiblePanel != null) {
            Main_panel.remove(visiblePanel);
        }
        if (visisbleTabbed != null) {
            Main_panel.remove(visisbleTabbed);
        }

        Main_panel.add(tabbedPanel);
        tabbedPanel.setVisible(true);
        Main_panel.revalidate();
        Main_panel.repaint();
        visisbleTabbed = (JTabbedPane) tabbedPanel;
    }

    // Enhanced helper method to create dashboard panels
    private JPanel createEnhancedDashboardPanel(String title, Color backgroundColor, String icon) {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(backgroundColor);
        
        // Create header with icon and title
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        titleLabel.setForeground(new Color(50, 50, 50));
        
        headerPanel.add(iconLabel, BorderLayout.WEST);
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        
        // Main content area
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setOpaque(false);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(contentPanel, BorderLayout.CENTER);
        
        // Add rounded border
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 230), 1),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        return panel;
    }

    // Helper method to create styled buttons
    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        
        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(color.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(color);
            }
        });
        
        return button;
    }

    // Helper method to create stat labels
    private JLabel createStatLabel(String label, String value, Color color) {
        JPanel statPanel = new JPanel(new BorderLayout());
        statPanel.setOpaque(false);
        
        JLabel nameLabel = new JLabel(label);
        nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        nameLabel.setForeground(new Color(100, 100, 120));
        
        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        valueLabel.setForeground(color);
        valueLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        
        statPanel.add(nameLabel, BorderLayout.WEST);
        statPanel.add(valueLabel, BorderLayout.EAST);
        
        JLabel container = new JLabel();
        container.setLayout(new BorderLayout());
        container.add(statPanel);
        return container;
    }

    // Custom list renderer for better looking lists
    class CustomListRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, 
                int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            if (!isSelected) {
                label.setBackground(list.getBackground());
            }
            return label;
        }
    }

    // Setup keyboard shortcuts
    private void setupKeyboardShortcuts() {
        // Add keyboard shortcuts for common actions
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getRootPane().getActionMap();
        
        // Ctrl+1 for Patients
        inputMap.put(KeyStroke.getKeyStroke("control 1"), "showPatients");
        actionMap.put("showPatients", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                Patient_button.doClick();
            }
        });
        
        // Ctrl+2 for Prescriptions
        inputMap.put(KeyStroke.getKeyStroke("control 2"), "showPrescriptions");
        actionMap.put("showPrescriptions", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                Ordo_button.doClick();
            }
        });
        
        // Ctrl+H for Home
        inputMap.put(KeyStroke.getKeyStroke("control H"), "goHome");
        actionMap.put("goHome", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                Acceuil.doClick();
            }
        });
    }

    // ---- MAIN ----
    public static void main(String[] args) {
        try {
            // Set professional look and feel
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            
            // Customize UI defaults for better appearance
            UIManager.put("TabbedPane.selected", new Color(13, 71, 161));
            UIManager.put("TabbedPane.selectHighlight", new Color(13, 71, 161));
            UIManager.put("TabbedPane.background", Color.WHITE);
            UIManager.put("TabbedPane.foreground", new Color(50, 50, 50));
            UIManager.put("TabbedPane.borderHightlightColor", new Color(13, 71, 161));
            
        } catch (Exception ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        EventQueue.invokeLater(() -> {
            Médecin_acceuil frame = new Médecin_acceuil();
            frame.setVisible(true);
            
            // Center the window
            frame.setLocationRelativeTo(null);
        });
    }

    // ---- Variables ----
    private JPanel visiblePanel;
    private JTabbedPane visisbleTabbed;
    private JToggleButton Aide_button;
    private JPanel Choice_menu_panel;
    private JToggleButton Consul_button;
    private JToggleButton Emp_button;
    private JToggleButton Fact_button;
    private JToggleButton Acceuil;
    private JPanel Main_panel;
    private JPanel Menu_side;
    private JToggleButton Ordo_button;
    private JToggleButton Patient_button;
    private Patient_pane Patient_tabs;
    private Ordonnance_pane Ordonnance_tabs;
    private Consultation_pane Consultation_tabs;
    private Facture_pane Facture_tabs;
    private Employe_pane Employe_tabs;
    private Aide_pane Aide_tabs;
    private JPanel Session_panel;
    private JButton auteur_button;
    private JLabel Clinicapro_logo;
    private JLabel Médecin_label;
    private JPanel logo_auteur_panel;
    private JPanel Acceuil_panel;
   
    private ButtonGroup bg1 = new ButtonGroup();
}
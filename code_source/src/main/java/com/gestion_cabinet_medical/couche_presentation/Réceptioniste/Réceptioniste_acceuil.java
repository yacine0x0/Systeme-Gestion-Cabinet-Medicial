package com.gestion_cabinet_medical.couche_presentation.Réceptioniste;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import com.gestion_cabinet_medical.couche_presentation.Aide_pane;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Réceptioniste_acceuil extends JFrame {
    
    private static final java.util.logging.Logger logger =
        java.util.logging.Logger.getLogger(Réceptioniste_acceuil.class.getName());


    public Réceptioniste_acceuil(){
             initComponents();

        // ---- Make window full screen on any computer ----
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }


      private void initComponents() {

        Menu_side = new JPanel();
        Session_panel = new JPanel();
        Réceptioniste_label = new JLabel();
        Choice_menu_panel = new JPanel();
        RDV_button = new JToggleButton();
        Aide_button = new JToggleButton();
        logo_auteur_panel = new JPanel();
        Clinicapro_logo = new JLabel();
        auteur_button = new JButton();
        Main_panel = new JPanel();
        Acceuil = new JToggleButton();
        Acceuil_panel = new JPanel();
        visiblePanel = new JPanel();
        visisbleTabbed = new JTabbedPane();
        Aide_tabs = new Aide_pane();
        RendezVous_tabs = new RendezVous_pane();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Clinica Pro - Réception");
        getContentPane().setLayout(new BorderLayout());

        // ---------------- LEFT MENU ----------------
        Menu_side.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Menu_side.setLayout(new BorderLayout());
        Menu_side.setBackground(new Color(52, 73, 94));

        Session_panel.setBorder(BorderFactory.createEtchedBorder());
        Session_panel.setLayout(new BorderLayout());
        Session_panel.setPreferredSize(new Dimension(150, 50));
        Session_panel.setBackground(new Color(44, 62, 80));
        Réceptioniste_label.setHorizontalAlignment(SwingConstants.CENTER);
        Réceptioniste_label.setText("Réceptioniste");
        Réceptioniste_label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        Réceptioniste_label.setForeground(Color.WHITE);
        Session_panel.add(Réceptioniste_label, BorderLayout.CENTER);
        Menu_side.add(Session_panel,BorderLayout.NORTH);

        
        Choice_menu_panel.setLayout(new BoxLayout(Choice_menu_panel, BoxLayout.Y_AXIS));
        Choice_menu_panel.setBackground(new Color(52, 73, 94));
        Choice_menu_panel.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));

        Acceuil.setText("🏠 Acceuil");
        Acceuil.setSelected(true);
        visiblePanel = Acceuil_panel;

        RDV_button.setText("📅 Rendez-Vous");
        Aide_button.setText("❓ Aide");

        // Style buttons
        styleMenuButton(Acceuil);
        styleMenuButton(RDV_button);
        styleMenuButton(Aide_button);

        bg1.add(RDV_button);
        bg1.add(Aide_button);
        bg1.add(Acceuil);

        RDV_button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        Aide_button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        Acceuil.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));

        Choice_menu_panel.add(Box.createVerticalStrut(10));
        Choice_menu_panel.add(Acceuil);
        Choice_menu_panel.add(Box.createVerticalStrut(5));
        Choice_menu_panel.add(RDV_button);
        Choice_menu_panel.add(Box.createVerticalStrut(5));
        Choice_menu_panel.add(Aide_button);

        Menu_side.add(Choice_menu_panel,BorderLayout.CENTER);

        logo_auteur_panel.setLayout(new BorderLayout());
        logo_auteur_panel.setPreferredSize(new Dimension(150,200));
        logo_auteur_panel.setBackground(new Color(52, 73, 94));
        Clinicapro_logo.setHorizontalAlignment(SwingConstants.CENTER);
        Clinicapro_logo.setText("CLINICA PRO");
        Clinicapro_logo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        Clinicapro_logo.setForeground(Color.WHITE);
        logo_auteur_panel.add(Clinicapro_logo,BorderLayout.CENTER);

        auteur_button.setText("👤 Auteur");
        auteur_button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        auteur_button.setBackground(new Color(41, 128, 185));
        auteur_button.setForeground(Color.WHITE);
        auteur_button.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        logo_auteur_panel.add(auteur_button,BorderLayout.SOUTH);

        Menu_side.add(logo_auteur_panel,BorderLayout.SOUTH);
        getContentPane().add(Menu_side, BorderLayout.WEST);

        // ---------------- DASHBOARD PANEL ----------------
        Acceuil_panel.setLayout(new GridBagLayout());
        Acceuil_panel.setBackground(new Color(245, 247, 250));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.BOTH;
        
        // Create 6 dashboard panels for receptionist
        JPanel[] dashboardPanels = new JPanel[6];
        String[] panelTitles = {
            "📊 Agenda du Jour",
            "👥 Nouveaux Patients",
            "📋 Rendez-vous En Attente",
            "💰 Facturation Rapide",
            "📞 Appels Récentes",
            "⚡ Actions Rapides"
        };
        
        // Panel 1: Today's Agenda
        dashboardPanels[0] = createDashboardPanel(panelTitles[0], Color.decode("#E3F2FD"));
        DefaultListModel<String> agendaModel = new DefaultListModel<>();
        agendaModel.addElement("08:30 - M. Dupont (Consultation)");
        agendaModel.addElement("10:00 - Mme. Martin (Suivi)");
        agendaModel.addElement("11:15 - M. Leroy (Première visite)");
        agendaModel.addElement("14:30 - Mme. Garcia (Vaccination)");
        agendaModel.addElement("16:00 - M. Bernard (Contrôle)");
        
        JList<String> agendaList = new JList<>(agendaModel);
        agendaList.setBackground(Color.decode("#E3F2FD"));
        agendaList.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        JScrollPane agendaScroll = new JScrollPane(agendaList);
        dashboardPanels[0].add(agendaScroll, BorderLayout.CENTER);
        
        JPanel agendaStats = new JPanel(new GridLayout(1, 2, 5, 5));
        agendaStats.setOpaque(false);
        JLabel totalRDV = new JLabel("Total: 8", SwingConstants.CENTER);
        JLabel completedRDV = new JLabel("Effectués: 3", SwingConstants.CENTER);
        totalRDV.setFont(new Font("Segoe UI", Font.BOLD, 11));
        completedRDV.setFont(new Font("Segoe UI", Font.BOLD, 11));
        agendaStats.add(totalRDV);
        agendaStats.add(completedRDV);
        dashboardPanels[0].add(agendaStats, BorderLayout.SOUTH);
        
        // Panel 2: New Patients
        dashboardPanels[1] = createDashboardPanel(panelTitles[1], Color.decode("#E8F5E9"));
        JPanel newPatientsPanel = new JPanel(new BorderLayout(5, 5));
        newPatientsPanel.setOpaque(false);
        
        JLabel newPatientsCount = new JLabel("Ce mois: 12 patients", SwingConstants.CENTER);
        newPatientsCount.setFont(new Font("Segoe UI", Font.BOLD, 13));
        
        DefaultListModel<String> newPatientsModel = new DefaultListModel<>();
        newPatientsModel.addElement("• M. Robert (05/11)");
        newPatientsModel.addElement("• Mme. Petit (07/11)");
        newPatientsModel.addElement("• M. Dubois (10/11)");
        newPatientsModel.addElement("• ... 9 autres");
        
        JList<String> newPatientsList = new JList<>(newPatientsModel);
        newPatientsList.setBackground(Color.decode("#E8F5E9"));
        newPatientsList.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        
        newPatientsPanel.add(newPatientsCount, BorderLayout.NORTH);
        newPatientsPanel.add(new JScrollPane(newPatientsList), BorderLayout.CENTER);
        
        JButton btnNouveauPatient = new JButton("Nouveau");
        styleDashboardButton(btnNouveauPatient);
        btnNouveauPatient.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Fonctionnalité nouveau patient à implémenter");
        });
        newPatientsPanel.add(btnNouveauPatient, BorderLayout.SOUTH);
        
        dashboardPanels[1].add(newPatientsPanel);
        
        // Panel 3: Pending Appointments
        dashboardPanels[2] = createDashboardPanel(panelTitles[2], Color.decode("#FFF3E0"));
        JPanel pendingPanel = new JPanel(new BorderLayout());
        pendingPanel.setOpaque(false);
        
        JLabel pendingCount = new JLabel("En attente: 4 RDV", SwingConstants.CENTER);
        pendingCount.setFont(new Font("Segoe UI", Font.BOLD, 13));
        
        String[] pendingAppointments = {
            "M. Martin - En attente",
            "Mme. Durand - Confirmé",
            "M. Lefèvre - À confirmer",
            "Mme. Garcia - En attente"
        };
        
        JList<String> pendingList = new JList<>(pendingAppointments);
        pendingList.setBackground(Color.decode("#FFF3E0"));
        pendingList.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        
        pendingPanel.add(pendingCount, BorderLayout.NORTH);
        pendingPanel.add(new JScrollPane(pendingList), BorderLayout.CENTER);
        
        JButton btnGestionRDV = createStyledButton("Gérer RDV", new Color(52, 152, 219));
        btnGestionRDV.addActionListener(e -> RDV_button.doClick());
        pendingPanel.add(btnGestionRDV, BorderLayout.SOUTH);
        
        dashboardPanels[2].add(pendingPanel);
        
        // Panel 4: Quick Billing
        dashboardPanels[3] = createDashboardPanel(panelTitles[3], Color.decode("#F3E5F5"));
        JPanel billingPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        billingPanel.setOpaque(false);
        
        JLabel totalFactures = new JLabel("Factures du jour: 5", SwingConstants.CENTER);
        JLabel montantTotal = new JLabel("Montant: 850.00€", SwingConstants.CENTER);
        JLabel facturesEnAttente = new JLabel("En attente: 2", SwingConstants.CENTER);
        
        totalFactures.setFont(new Font("Segoe UI", Font.BOLD, 12));
        montantTotal.setFont(new Font("Segoe UI", Font.BOLD, 12));
        facturesEnAttente.setFont(new Font("Segoe UI", Font.BOLD, 12));
        
        JButton btnNouvelleFacture = createStyledButton("Nouvelle Facture", new Color(155, 89, 182));
        btnNouvelleFacture.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Fonctionnalité facturation à implémenter");
        });
        
        billingPanel.add(totalFactures);
        billingPanel.add(montantTotal);
        billingPanel.add(facturesEnAttente);
        billingPanel.add(btnNouvelleFacture);
        
        dashboardPanels[3].add(billingPanel);
        
        // Panel 5: Recent Calls
        dashboardPanels[4] = createDashboardPanel(panelTitles[4], Color.decode("#FCE4EC"));
        DefaultListModel<String> callsModel = new DefaultListModel<>();
        callsModel.addElement("📞 09:15 - M. Bernard");
        callsModel.addElement("📞 10:30 - Mme. Petit");
        callsModel.addElement("📞 11:45 - Dr. Martin");
        callsModel.addElement("📞 14:20 - Clinique");
        callsModel.addElement("📞 15:40 - M. Rousseau");
        
        JList<String> callsList = new JList<>(callsModel);
        callsList.setBackground(Color.decode("#FCE4EC"));
        callsList.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        dashboardPanels[4].add(new JScrollPane(callsList));
        
        JPanel callStats = new JPanel(new FlowLayout(FlowLayout.CENTER));
        callStats.setOpaque(false);
        callStats.add(new JLabel("Total appels: 12"));
        dashboardPanels[4].add(callStats, BorderLayout.SOUTH);
        
        // Panel 6: Quick Actions
        dashboardPanels[5] = createDashboardPanel(panelTitles[5], Color.decode("#E0F2F1"));
        JPanel quickActionsPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        quickActionsPanel.setOpaque(false);
        
        JButton btnPrendreRDV = new JButton("📅 Prendre RDV");
        JButton btnAnnulerRDV = new JButton("❌ Annuler RDV");
        JButton btnConfirmerRDV = new JButton("✅ Confirmer RDV");
        JButton btnRappeler = new JButton("📞 Rappeler");
        JButton btnFacturer = new JButton("💰 Facturer");
        JButton btnImprimer = new JButton("🖨️ Imprimer");
        
        JButton[] actionButtons = {btnPrendreRDV, btnAnnulerRDV, btnConfirmerRDV, btnRappeler, btnFacturer, btnImprimer};
        
        for (JButton btn : actionButtons) {
            styleQuickActionButton(btn);
            quickActionsPanel.add(btn);
        }
        
        // Add action listeners
        btnPrendreRDV.addActionListener(e -> RDV_button.doClick());
        
        dashboardPanels[5].add(quickActionsPanel);
        
        // Arrange panels in 2x3 grid
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        
        // Row 1
        gbc.gridx = 0;
        gbc.gridy = 0;
        Acceuil_panel.add(dashboardPanels[0], gbc);
        
        gbc.gridx = 1;
        Acceuil_panel.add(dashboardPanels[1], gbc);
        
        gbc.gridx = 2;
        Acceuil_panel.add(dashboardPanels[2], gbc);
        
        // Row 2
        gbc.gridx = 0;
        gbc.gridy = 1;
        Acceuil_panel.add(dashboardPanels[3], gbc);
        
        gbc.gridx = 1;
        Acceuil_panel.add(dashboardPanels[4], gbc);
        
        gbc.gridx = 2;
        Acceuil_panel.add(dashboardPanels[5], gbc);
        
        // Welcome header
        JPanel welcomePanel = new JPanel(new BorderLayout());
        welcomePanel.setBackground(new Color(52, 152, 219));
        welcomePanel.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));
        
        JLabel welcomeLabel = new JLabel("🏥 Bienvenue au Poste de Réception - CLINICA PRO");
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        welcomeLabel.setForeground(Color.WHITE);
        
        JLabel dateLabel = new JLabel(java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy")));
        dateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        dateLabel.setForeground(Color.WHITE);
        dateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        
        welcomePanel.add(welcomeLabel, BorderLayout.WEST);
        welcomePanel.add(dateLabel, BorderLayout.EAST);
        
        // Stats bar
        JPanel statsBar = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 5));
        statsBar.setBackground(new Color(236, 240, 241));
        statsBar.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        
        JLabel[] statsLabels = {
            new JLabel("👥 Patients aujourd'hui: 15"),
            new JLabel("📅 RDV total: 24"),
            new JLabel("✅ RDV confirmés: 18"),
            new JLabel("💰 Chiffre jour: 1,250€")
        };
        
        for (JLabel stat : statsLabels) {
            stat.setFont(new Font("Segoe UI", Font.BOLD, 12));
            stat.setForeground(new Color(52, 73, 94));
            statsBar.add(stat);
        }
        
        // ---------------- MAIN CONTENT ----------------
        Main_panel.setLayout(new BorderLayout());
        Main_panel.add(welcomePanel, BorderLayout.NORTH);
        Main_panel.add(statsBar, BorderLayout.CENTER);
        Main_panel.add(Acceuil_panel, BorderLayout.SOUTH);
        
        getContentPane().add(Main_panel, BorderLayout.CENTER);
        
        // Visibility setup
        Acceuil_panel.setVisible(true);
        RendezVous_tabs.setVisible(false);
        Aide_tabs.setVisible(false);
        
        pack();

    //------------------EVENTS------------------//

        Acceuil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg){
                 if (visisbleTabbed != null) {
                    Main_panel.remove(visisbleTabbed);
                    Main_panel.revalidate();
                    Main_panel.repaint();   
                }

                Main_panel.add(Acceuil_panel);
                Acceuil_panel.setVisible(true);
                Main_panel.revalidate();
                Main_panel.repaint();
                visiblePanel = Acceuil_panel;
            }
        });

        RDV_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg){
                if (visiblePanel != null) {
                    Main_panel.remove(visiblePanel);
                    Main_panel.revalidate();
                    Main_panel.repaint();
                }
                if (visisbleTabbed != null) {
                    Main_panel.remove(visisbleTabbed);
                    Main_panel.revalidate();
                    Main_panel.repaint();   
                }

                Main_panel.add(RendezVous_tabs);
                RendezVous_tabs.setVisible(true);
                Main_panel.revalidate();
                Main_panel.repaint();
                visisbleTabbed = RendezVous_tabs;
            }
        });

        Aide_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg){
                if (visiblePanel != null) {
                    Main_panel.remove(visiblePanel);
                    Main_panel.revalidate();
                    Main_panel.repaint();
                }
                if (visisbleTabbed != null) {
                    Main_panel.remove(visisbleTabbed);
                    Main_panel.revalidate();
                    Main_panel.repaint();   
                }

                Main_panel.add(Aide_tabs);
                Aide_tabs.setVisible(true);
                Main_panel.revalidate();
                Main_panel.repaint();
                visisbleTabbed = Aide_tabs;
            }
        });
    }

    private void styleMenuButton(JToggleButton button) {
        button.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setBackground(new Color(52, 73, 94));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (!button.isSelected()) {
                    button.setBackground(new Color(41, 128, 185));
                }
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (!button.isSelected()) {
                    button.setBackground(new Color(52, 73, 94));
                }
            }
        });
        
        button.addChangeListener(e -> {
            if (button.isSelected()) {
                button.setBackground(new Color(41, 128, 185));
                button.setFont(new Font("Segoe UI", Font.BOLD, 13));
            } else {
                button.setBackground(new Color(52, 73, 94));
                button.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            }
        });
    }

    private JPanel createDashboardPanel(String title, Color backgroundColor) {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(backgroundColor);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180), 1),
                title,
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                new Font("Segoe UI", Font.BOLD, 13),
                new Color(44, 62, 80)
            ),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        return panel;
    }

    private void styleDashboardButton(JButton button) {
        button.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        button.setBackground(new Color(52, 152, 219));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    private void styleQuickActionButton(JButton button) {
        button.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        button.setBackground(new Color(236, 240, 241));
        button.setForeground(new Color(44, 62, 80));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

  
    // ---- MAIN ----
    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        EventQueue.invokeLater(() -> new Réceptioniste_acceuil().setVisible(true));
    }


        private JPanel visiblePanel;
    private JTabbedPane visisbleTabbed;
    private JToggleButton Aide_button;
    private JPanel Choice_menu_panel;
    private JToggleButton RDV_button;
    private JToggleButton Acceuil;
    private JPanel Main_panel;
    private JPanel Menu_side;
    private Aide_pane Aide_tabs;
    private JPanel Session_panel;
    private JButton auteur_button;
    private JLabel Clinicapro_logo;
    private JLabel Réceptioniste_label;
    private JPanel logo_auteur_panel;
    private JPanel Acceuil_panel;
    private RendezVous_pane RendezVous_tabs;

    private ButtonGroup bg1 = new ButtonGroup();
}
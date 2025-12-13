package com.gestion_cabinet_medical.couche_presentation;

import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Médecin_acceuil extends JFrame {

    private static final java.util.logging.Logger logger =
            java.util.logging.Logger.getLogger(Médecin_acceuil.class.getName());

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

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        // ---------------- LEFT MENU ----------------
        Menu_side.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Menu_side.setLayout(new BorderLayout());
        //Menu_side.setLayout(new GridLayout(3, 1, 0, 20));

        Session_panel.setBorder(BorderFactory.createEtchedBorder());
        Session_panel.setLayout(new BorderLayout());
        Session_panel.setPreferredSize(new Dimension(150, 50)); // Increase height
        Médecin_label.setHorizontalAlignment(SwingConstants.CENTER);
        Médecin_label.setText("Médecin");
        Session_panel.add(Médecin_label, BorderLayout.CENTER);
        Menu_side.add(Session_panel,BorderLayout.NORTH);

        
        Choice_menu_panel.setLayout(new BoxLayout(Choice_menu_panel, BoxLayout.Y_AXIS));

        Acceuil.setText("Acceuil");
        Acceuil.setSelected(true);
        visiblePanel = Acceuil_panel;

        Patient_button.setText("Patient");

  

        

        Ordo_button.setText("Ordonnance");
        Consul_button.setText("Consultation");
        Fact_button.setText("Facture");
        Emp_button.setText("Employé");
        Aide_button.setText("Aide");

        bg1.add(Ordo_button); bg1.add(Patient_button); bg1.add(Consul_button);
        bg1.add(Fact_button); bg1.add(Emp_button); bg1.add(Aide_button);
        bg1.add(Acceuil);


        Patient_button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        Ordo_button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        Consul_button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        Fact_button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        Emp_button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        Aide_button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        Acceuil.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));


     
        Choice_menu_panel.add(Acceuil);
        Choice_menu_panel.add(Patient_button);
        Choice_menu_panel.add(Ordo_button);
        Choice_menu_panel.add(Consul_button);
        Choice_menu_panel.add(Fact_button);
        Choice_menu_panel.add(Emp_button);
        Choice_menu_panel.add(Aide_button);

        Menu_side.add(Choice_menu_panel,BorderLayout.CENTER);

        logo_auteur_panel.setLayout(new BorderLayout());
        logo_auteur_panel.setPreferredSize(new Dimension(150,200));
        Clinicapro_logo.setHorizontalAlignment(SwingConstants.CENTER);
        Clinicapro_logo.setText("CLINICA PRO");
        logo_auteur_panel.add(Clinicapro_logo,BorderLayout.CENTER);

        auteur_button.setText("Auteur");
        auteur_button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        logo_auteur_panel.add(auteur_button,BorderLayout.SOUTH);

        Menu_side.add(logo_auteur_panel,BorderLayout.SOUTH);
        getContentPane().add(Menu_side, BorderLayout.WEST);

        // ---------------- MAIN CONTENT ----------------
        Main_panel.setLayout(new BorderLayout());

        // ---- Tab 1: Affichage ----
      

        Main_panel.add(Acceuil_panel,BorderLayout.CENTER);
      
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
                visisbleTabbed.setVisible(false);
                 visiblePanel.setVisible(true);
                
            }
        });


         Patient_button.addActionListener(new ActionListener() {
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

                Main_panel.add(Patient_tabs);
                Patient_tabs.setVisible(true);
                Main_panel.revalidate();
                Main_panel.repaint();
                visisbleTabbed = Patient_tabs;

            }
        });



         Ordo_button.addActionListener(new ActionListener() {
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

                Main_panel.add(Ordonnance_tabs);
                Ordonnance_tabs.setVisible(true);
                Main_panel.revalidate();
                Main_panel.repaint();
                visisbleTabbed = Ordonnance_tabs;

            }
        });

           Consul_button.addActionListener(new ActionListener() {
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

                Main_panel.add(Consultation_tabs);
                Consultation_tabs.setVisible(true);
                Main_panel.revalidate();
                Main_panel.repaint();
                visisbleTabbed = Consultation_tabs;

            }
        });


         Fact_button.addActionListener(new ActionListener() {
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

                Main_panel.add(Facture_tabs);
                Facture_tabs.setVisible(true);
                Main_panel.revalidate();
                Main_panel.repaint();
                visisbleTabbed = Facture_tabs;

            }
        });

         Emp_button.addActionListener(new ActionListener() {
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

                Main_panel.add(Employe_tabs);
                Employe_tabs.setVisible(true);
                Main_panel.revalidate();
                Main_panel.repaint();
                visisbleTabbed = Employe_tabs;

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

        EventQueue.invokeLater(() -> new Médecin_acceuil().setVisible(true));
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

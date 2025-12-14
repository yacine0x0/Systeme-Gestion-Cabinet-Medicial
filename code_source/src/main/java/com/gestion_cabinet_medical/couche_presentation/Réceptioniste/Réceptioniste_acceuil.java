package com.gestion_cabinet_medical.couche_presentation.Réceptioniste;

import javax.swing.*;

import com.gestion_cabinet_medical.couche_presentation.Aide_pane;
import com.gestion_cabinet_medical.couche_presentation.Médecin.Consultation_pane;
import com.gestion_cabinet_medical.couche_presentation.Médecin.Employe_pane;
import com.gestion_cabinet_medical.couche_presentation.Médecin.Facture_pane;
import com.gestion_cabinet_medical.couche_presentation.Médecin.Médecin_acceuil;
import com.gestion_cabinet_medical.couche_presentation.Médecin.Ordonnance_pane;
import com.gestion_cabinet_medical.couche_presentation.Médecin.Patient_pane;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Réceptioniste_acceuil extends JFrame {
    
    private static final java.util.logging.Logger logger =
        java.util.logging.Logger.getLogger(Médecin_acceuil.class.getName());


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
        setTitle("Clinica Pro");
        getContentPane().setLayout(new BorderLayout());

        // ---------------- LEFT MENU ----------------
        Menu_side.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Menu_side.setLayout(new BorderLayout());
        //Menu_side.setLayout(new GridLayout(3, 1, 0, 20));

        Session_panel.setBorder(BorderFactory.createEtchedBorder());
        Session_panel.setLayout(new BorderLayout());
        Session_panel.setPreferredSize(new Dimension(150, 50)); // Increase height
        Réceptioniste_label.setHorizontalAlignment(SwingConstants.CENTER);
        Réceptioniste_label.setText("Réceptioniste");
        Session_panel.add(Réceptioniste_label, BorderLayout.CENTER);
        Menu_side.add(Session_panel,BorderLayout.NORTH);

        
        Choice_menu_panel.setLayout(new BoxLayout(Choice_menu_panel, BoxLayout.Y_AXIS));

        Acceuil.setText("Acceuil");
        Acceuil.setSelected(true);
        visiblePanel = Acceuil_panel;

  


        RDV_button.setText("Rendez-Vous");
        Aide_button.setText("Aide");

        bg1.add(RDV_button);
        bg1.add(Aide_button);
        bg1.add(Acceuil);


        RDV_button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        Aide_button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        Acceuil.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));


     
        Choice_menu_panel.add(Acceuil);
        Choice_menu_panel.add(RDV_button);
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
        RendezVous_tabs.setVisible(false);
        Aide_tabs.setVisible(false);
        

        pack();


    //------------------EVENTS------------------//

        Acceuil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg){
                visisbleTabbed.setVisible(false);
                 visiblePanel.setVisible(true);
                
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

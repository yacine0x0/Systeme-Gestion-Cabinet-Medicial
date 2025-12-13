package com.gestion_cabinet_medical.couche_presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Auth_fenetre extends JFrame {

    // Components
    private JComboBox<String> rolls_box;
    private JLabel clinicapro_text;
    private JPanel main_panel;
    private JPanel saisie_panel;
    private JTextField mot_pass_field;
    private JTextField nom_field;
    private JTextField prenom_field;
    private JButton seconnecterbutton;
    private boolean cleared_nom = false,cleared_prenom=false,cleared_motpasse = false;

    public Auth_fenetre() {
        initComponents();
    }

    private void initComponents() {

        main_panel = new JPanel();
        clinicapro_text = new JLabel();
        saisie_panel = new JPanel();
        mot_pass_field = new JTextField(15);
        nom_field = new JTextField(10);
        prenom_field = new JTextField(10);
        rolls_box = new JComboBox<>();
        seconnecterbutton = new JButton("Se connecter");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBackground(Color.WHITE);
        setMinimumSize(new Dimension(500, 200));
        setPreferredSize(new Dimension(600, 300));
        setLocationRelativeTo(null);
        setResizable(false);

        main_panel.setBackground(Color.WHITE);
        main_panel.setLayout(new BorderLayout());

        clinicapro_text.setFont(new Font("Likhan", Font.BOLD, 24));
        clinicapro_text.setHorizontalAlignment(SwingConstants.CENTER);
        clinicapro_text.setText("Clinica Pro");
        main_panel.add(clinicapro_text, BorderLayout.PAGE_START);

        saisie_panel.setBackground(Color.WHITE);
        GridBagLayout layout = new GridBagLayout();
        layout.columnWidths = new int[]{0, 5, 0, 5, 0, 5, 0, 5, 0};
        layout.rowHeights = new int[]{0, 5, 0, 5, 0};
        saisie_panel.setLayout(layout);

        mot_pass_field.setText("Mot de passe");
        mot_pass_field.setFont(new Font("Arial",Font.ITALIC,15));
        mot_pass_field.setForeground(Color.LIGHT_GRAY);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        saisie_panel.add(seconnecterbutton,gbc);

        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(9, 11, 9, 11);
        saisie_panel.add(mot_pass_field, gbc);
       

        nom_field.setText("Nom");
        nom_field.setFont(new Font("Arial",Font.ITALIC,15));
        nom_field.setForeground(Color.LIGHT_GRAY);
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipadx = 4;
        gbc.insets = new Insets(5, 3, 5, 4);
        saisie_panel.add(nom_field, gbc);

        prenom_field.setText("Prénom");
        prenom_field.setFont(new Font("Arial",Font.ITALIC,15));
        prenom_field.setForeground(Color.LIGHT_GRAY);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.ipadx = 1;
        gbc.insets = new Insets(6, 3, 6, 7);
        saisie_panel.add(prenom_field, gbc);

        rolls_box.setModel(new DefaultComboBoxModel<>(new String[]{
            "Réceptioniste", "Médecin"
        }));

        rolls_box.setSelectedIndex(-1);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 3, 5, 3);
        saisie_panel.add(rolls_box, gbc);

        main_panel.add(saisie_panel, BorderLayout.CENTER);
        getContentPane().add(main_panel, BorderLayout.CENTER);

        pack();


        nom_field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e){
          
                if (!cleared_nom) {
             nom_field.setText("");
            nom_field.setFont(new Font("Arial", Font.PLAIN, 15));
            nom_field.setForeground(Color.BLACK);
            cleared_nom = true;
                }
          
             }

               public void focusLost(FocusEvent e) {
        if (nom_field.getText().isEmpty()) {
            nom_field.setText("Nom");
            nom_field.setFont(new Font("Arial", Font.ITALIC, 15));
            nom_field.setForeground(Color.GRAY);
            cleared_nom = false;
        }
    }
                });

        mot_pass_field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e){
            if (!cleared_motpasse) {
                 mot_pass_field.setText("");
            mot_pass_field.setFont(new Font("Arial", Font.PLAIN, 15));
            mot_pass_field.setForeground(Color.BLACK);
            cleared_motpasse = true;
            }
            }

              public void focusLost(FocusEvent e) {
        if (mot_pass_field.getText().isEmpty()) {
            mot_pass_field.setText("Mot de passe");
            mot_pass_field.setFont(new Font("Arial", Font.ITALIC, 15));
            mot_pass_field.setForeground(Color.GRAY);
            cleared_motpasse = false;
        }
    }
        });        


        prenom_field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e){
                if (!cleared_prenom) {
             prenom_field.setText("");
            prenom_field.setFont(new Font("Arial", Font.PLAIN, 15));
            prenom_field.setForeground(Color.BLACK);
            cleared_prenom = true;
                }
            }

              public void focusLost(FocusEvent e) {
        if (prenom_field.getText().isEmpty()) {
            prenom_field.setText("Prénom");
            prenom_field.setFont(new Font("Arial", Font.ITALIC, 15));
            prenom_field.setForeground(Color.GRAY);
            cleared_prenom = false;
        }
    }
        });        

    }

    public static void main(String[] args) {

        // Optional: Nimbus look and feel
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        EventQueue.invokeLater(() -> new Auth_fenetre().setVisible(true));
    }
}

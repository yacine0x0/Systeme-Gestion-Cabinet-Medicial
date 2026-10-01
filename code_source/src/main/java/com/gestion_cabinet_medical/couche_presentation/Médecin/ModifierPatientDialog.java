package com.gestion_cabinet_medical.couche_presentation.Médecin;

import com.gestion_cabinet_medical.couche_metier.model.Personne;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ModifierPatientDialog extends JDialog {
    private JTextField nomField, prenomField, ageField, sexeField, groupageField, adresseField, telField;
    private JSpinner dateSpinner;
    private JButton confirmButton, cancelButton;
    private boolean modified = false;
    private Personne updatedPersonne;
    private int patientId;
    
    public ModifierPatientDialog(int patientId, String nom, String prenom, String dateNaissance, 
                                 int age, String sexe, String groupage, String adresse, String tel) {
        this.patientId = patientId;
        
        setTitle("Modifier Patient - ID: " + patientId);
        setModal(true);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        // Create main panel
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5, 10, 5, 10);
        g.anchor = GridBagConstraints.WEST;
        
        int row = 0;
        
        // ID (read-only)
        g.gridx = 0; g.gridy = row; g.weightx = 0.0;
        mainPanel.add(new JLabel("ID Patient:"), g);
        g.gridx = 1; g.fill = GridBagConstraints.HORIZONTAL;
        JTextField idField = new JTextField(String.valueOf(patientId), 15);
        idField.setEditable(false);
        idField.setBackground(Color.LIGHT_GRAY);
        mainPanel.add(idField, g);
        
        row++;
        
        // Nom
        g.gridx = 0; g.gridy = row; g.fill = GridBagConstraints.NONE;
        mainPanel.add(new JLabel("Nom:"), g);
        g.gridx = 1; g.fill = GridBagConstraints.HORIZONTAL;
        nomField = new JTextField(nom, 15);
        mainPanel.add(nomField, g);
        
        row++;
        
        // Prénom
        g.gridx = 0; g.gridy = row;
        mainPanel.add(new JLabel("Prénom:"), g);
        g.gridx = 1;
        prenomField = new JTextField(prenom, 15);
        mainPanel.add(prenomField, g);
        
        row++;
        
        // Date de Naissance
        g.gridx = 0; g.gridy = row;
        mainPanel.add(new JLabel("Date Naissance (jj/mm/aaaa):"), g);
        g.gridx = 1;
        
        // Parse the date string to set up spinner
        LocalDate date = LocalDate.now().minusYears(20); // default
        try {
            if (dateNaissance != null && !dateNaissance.trim().isEmpty()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                date = LocalDate.parse(dateNaissance, formatter);
            }
        } catch (DateTimeParseException e) {
            // If parsing fails, use default
            System.out.println("Erreur parsing date: " + dateNaissance);
        }
        
        SpinnerDateModel dateModel = new SpinnerDateModel(
            java.sql.Date.valueOf(date),
            null,
            null,
            java.util.Calendar.DAY_OF_MONTH
        );
        dateSpinner = new JSpinner(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
        dateSpinner.setEditor(dateEditor);
        dateSpinner.setPreferredSize(new Dimension(120, 25));
        mainPanel.add(dateSpinner, g);
        
        row++;
        
        // Âge
        g.gridx = 0; g.gridy = row;
        mainPanel.add(new JLabel("Âge:"), g);
        g.gridx = 1;
        ageField = new JTextField(String.valueOf(age), 15);
        mainPanel.add(ageField, g);
        
        row++;
        
        // Sexe
        g.gridx = 0; g.gridy = row;
        mainPanel.add(new JLabel("Sexe:"), g);
        g.gridx = 1;
        sexeField = new JTextField(sexe, 15);
        mainPanel.add(sexeField, g);
        
        row++;
        
        // Groupage
        g.gridx = 0; g.gridy = row;
        mainPanel.add(new JLabel("Groupage:"), g);
        g.gridx = 1;
        groupageField = new JTextField(groupage, 15);
        mainPanel.add(groupageField, g);
        
        row++;
        
        // Adresse
        g.gridx = 0; g.gridy = row;
        mainPanel.add(new JLabel("Adresse:"), g);
        g.gridx = 1;
        adresseField = new JTextField(adresse, 15);
        mainPanel.add(adresseField, g);
        
        row++;
        
        // Téléphone
        g.gridx = 0; g.gridy = row;
        mainPanel.add(new JLabel("Téléphone:"), g);
        g.gridx = 1;
        telField = new JTextField(tel, 15);
        mainPanel.add(telField, g);
        
        row++;
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        confirmButton = new JButton("Confirmer");
        cancelButton = new JButton("Annuler");
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);
        
        g.gridx = 0; g.gridy = row; g.gridwidth = 2; g.fill = GridBagConstraints.CENTER;
        mainPanel.add(buttonPanel, g);
        
        // Add main panel to dialog
        add(mainPanel, BorderLayout.CENTER);
        
        // Add title
        JLabel titleLabel = new JLabel("Modification du Patient", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(new Color(0, 70, 140));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        add(titleLabel, BorderLayout.NORTH);
        
        // Add action listeners
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (validateFields()) {
                    // Create updated Personne object
                    updatedPersonne = createUpdatedPersonne();
                    modified = true;
                    dispose();
                }
            }
        });
        
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modified = false;
                dispose();
            }
        });
    }
    
    private boolean validateFields() {
        // Validate nom
        if (nomField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Le nom est obligatoire", 
                "Erreur de validation", 
                JOptionPane.ERROR_MESSAGE);
            nomField.requestFocus();
            return false;
        }
        
        // Validate prenom
        if (prenomField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Le prénom est obligatoire", 
                "Erreur de validation", 
                JOptionPane.ERROR_MESSAGE);
            prenomField.requestFocus();
            return false;
        }
        
        // Validate age
        try {
            int age = Integer.parseInt(ageField.getText().trim());
            if (age < 0 || age > 150) {
                JOptionPane.showMessageDialog(this, 
                    "L'âge doit être entre 0 et 150", 
                    "Erreur de validation", 
                    JOptionPane.ERROR_MESSAGE);
                ageField.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "L'âge doit être un nombre valide", 
                "Erreur de validation", 
                JOptionPane.ERROR_MESSAGE);
            ageField.requestFocus();
            return false;
        }
        
        // Validate telephone
        String tel = telField.getText().trim();
        if (!tel.isEmpty() && !tel.matches("^[0-9+\\s\\-()]{8,20}$")) {
            JOptionPane.showMessageDialog(this, 
                "Numéro de téléphone invalide", 
                "Erreur de validation", 
                JOptionPane.WARNING_MESSAGE);
            telField.requestFocus();
            return false;
        }
        
        return true;
    }
    
    private Personne createUpdatedPersonne() {
        // Get values from fields
        String nom = nomField.getText().trim();
        String prenom = prenomField.getText().trim();
        
        // Format date from spinner
        String dateNaissance = "";
        if (dateSpinner.getValue() != null) {
            Object spinnerValue = dateSpinner.getValue();
            if (spinnerValue instanceof java.util.Date) {
                java.util.Date date = (java.util.Date) spinnerValue;
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                dateNaissance = sdf.format(date);
            }
        }
        
        int age = Integer.parseInt(ageField.getText().trim());
        String sexe = sexeField.getText().trim();
        String groupage = groupageField.getText().trim();
        String adresse = adresseField.getText().trim();
        String tel = telField.getText().trim();
        
        // Create Personne object with ID
        Personne personne = new Personne();
        personne.setID(patientId);
        personne.setNom(nom);
        personne.setPrenom(prenom);
        personne.setdateNaissance(dateNaissance);
        personne.setAge(age);
        personne.setSexe(sexe);
        personne.setGroupe_sanguin(groupage);
        personne.setAdresse(adresse);
        personne.setTel(tel);
        
        return personne;
    }
    
    public boolean isModified() {
        return modified;
    }
    
    public Personne getUpdatedPersonne() {
        return updatedPersonne;
    }
}
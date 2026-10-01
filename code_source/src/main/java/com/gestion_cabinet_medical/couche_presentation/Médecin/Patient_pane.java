package com.gestion_cabinet_medical.couche_presentation.Médecin;

import com.gestion_cabinet_medical.couche_metier.*;
import com.gestion_cabinet_medical.couche_metier.model.Personne;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class Patient_pane extends JTabbedPane {
    private JPanel Affichage_patient,total_panel,ajout_patient,recherche_patient;
    private JTable Patient_affichage_table;
    private JScrollPane affichage_table_scroll;
    private JLabel Total_patient;
    private JButton supprimerButton, modifierButton;

    private JLabel ajout_nom_Label,ajout_prenom_Label,ajout_datenaiss_label,ajout_age_Label,ajout_sexe_Label,ajout_groupage_Label,ajout_adresse_Label,ajout_tel_Label;
    private JTextField ajout_nom_Field,ajout_prenom_Field,ajout_age_Field,ajout_sexe_Field,ajout_groupage_Field,ajout_adresse_Field,ajout_tel_Field;
    private JTextField recherche_nom_Field,recherche_prenom_Field,recherche_age_Field,recherche_sexe_Field,recherche_groupage_Field,recherche_adresse_Field,recherche_tel_Field;
    private JButton ajouterButton, rechercheNormalButton, rechercheAvanceButton;
    private JSpinner dateSpinner, rechercheDateSpinner; // For date selection

    private static int currentCount = 0;

    private DefaultTableModel tabelMODEL;



private void clearFields() {
    ajout_nom_Field.setText("");
    ajout_prenom_Field.setText("");
    // Reset spinner to default (20 years ago from today)
    LocalDate defaultDate = LocalDate.now().minusYears(20);
    dateSpinner.setValue(java.sql.Date.valueOf(defaultDate));
    ajout_age_Field.setText("");
    ajout_sexe_Field.setText("");
    ajout_groupage_Field.setText("");
    ajout_adresse_Field.setText("");
    ajout_tel_Field.setText("");
    ajout_nom_Field.requestFocus(); // Set focus back to first field
}

// Helper method to refresh patient table (add this to Patient_pane class)
private void refreshPatientTable() {
    // Clear existing rows
    tabelMODEL.setRowCount(0);
    
    Patient p = new Patient();
    p.Afficher_tout_Patient(Patient_affichage_table);
    // For now, we'll just update the count
     currentCount = Patient_affichage_table.getRowCount();
    updateTotalPatients(currentCount);
}



    public Patient_pane(){
        // Set modern look for tabbed pane
        this.setBackground(new Color(245, 247, 250));
        this.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        //-------EVENTS--------//

        Affichage_patient = new JPanel();
        total_panel = new JPanel();
        ajout_patient = new JPanel();
        recherche_patient = new JPanel();
        Patient_affichage_table = new JTable();
        affichage_table_scroll = new JScrollPane();
        Total_patient = new JLabel();

        // Initialize buttons with improved styling
        supprimerButton = createStyledButton("🗑️ Supprimer", new Color(231, 76, 60));
        modifierButton = createStyledButton("✏️ Modifier", new Color(241, 196, 15));
        ajouterButton = createStyledButton("➕ Ajouter Patient", new Color(46, 204, 113));
        rechercheNormalButton = createStyledButton("🔍 Rechercher (Simple)", new Color(52, 152, 219));
        rechercheAvanceButton = createStyledButton("🔍 Rechercher (Avancée)", new Color(155, 89, 182));

        // Set up the table model
        tabelMODEL = new DefaultTableModel(new Object [][] {
            },
            new String [] {
                "ID", "Nom", "Prénom","Date Naissance" ,"Age", "Sexe", "Groupage", "Adresse", "Tel"
            }) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Make all cells non-editable
                return false;
            }
        };

        // Initialize labels with better styling
        ajout_nom_Label = createStyledLabel("Nom: ");    
        ajout_prenom_Label = createStyledLabel("Prénom: ");
        ajout_datenaiss_label = createStyledLabel("Date de Naissance: ");
        ajout_age_Label = createStyledLabel("Age: ");
        ajout_sexe_Label = createStyledLabel("Sexe: ");    
        ajout_groupage_Label = createStyledLabel("Groupage: ");    
        ajout_adresse_Label = createStyledLabel("Adresse: ");
        ajout_tel_Label = createStyledLabel("Tel: ");

        // Create styled text fields
        ajout_nom_Field = createStyledTextField();
        ajout_prenom_Field = createStyledTextField();
        ajout_age_Field = createStyledTextField();
        ajout_sexe_Field = createStyledTextField();
        ajout_groupage_Field = createStyledTextField();
        ajout_adresse_Field = createStyledTextField();
        ajout_tel_Field = createStyledTextField();
        
        // Recherche text fields
        recherche_nom_Field = createStyledTextField();
        recherche_prenom_Field = createStyledTextField();
        recherche_age_Field = createStyledTextField();
        recherche_sexe_Field = createStyledTextField();
        recherche_groupage_Field = createStyledTextField();
        recherche_adresse_Field = createStyledTextField();
        recherche_tel_Field = createStyledTextField();

        // Create a date spinner for date of birth (Ajout tab)
        LocalDate defaultDate = LocalDate.now().minusYears(20);
        SpinnerDateModel dateModel = new SpinnerDateModel(
            java.sql.Date.valueOf(defaultDate),
            null,
            null,
            java.util.Calendar.DAY_OF_MONTH
        );
        
        dateSpinner = new JSpinner(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
        dateSpinner.setEditor(dateEditor);
        dateSpinner.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        dateSpinner.setPreferredSize(new Dimension(120, 30));
        
        // Create a date spinner for recherche tab
        SpinnerDateModel rechercheDateModel = new SpinnerDateModel(
            java.sql.Date.valueOf(defaultDate),
            null,
            null,
            java.util.Calendar.DAY_OF_MONTH
        );
        
        rechercheDateSpinner = new JSpinner(rechercheDateModel);
        JSpinner.DateEditor rechercheDateEditor = new JSpinner.DateEditor(rechercheDateSpinner, "dd/MM/yyyy");
        rechercheDateSpinner.setEditor(rechercheDateEditor);
        rechercheDateSpinner.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        rechercheDateSpinner.setPreferredSize(new Dimension(120, 30));

        // SET UP THE AFFICHAGE (DISPLAY) TAB
        Affichage_patient.setLayout(new BorderLayout());
        Affichage_patient.setBackground(new Color(245, 247, 250));
        
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
        
        JLabel titleLabel = new JLabel("👥 Gestion des Patients", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        
        // Stats panel
        JPanel statsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statsPanel.setOpaque(false);
        statsPanel.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 0));
        
        Total_patient.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
        Total_patient.setText("📊 Total Patients: 0");
        Total_patient.setForeground(Color.WHITE);
        statsPanel.add(Total_patient);
        
        headerPanel.add(statsPanel, BorderLayout.SOUTH);
        Affichage_patient.add(headerPanel, BorderLayout.NORTH);
        
        // Configure the table
        configureTable(Patient_affichage_table, tabelMODEL);
        
        // Set column widths for affichage table
        Patient_affichage_table.getColumnModel().getColumn(0).setPreferredWidth(50);   // ID
        Patient_affichage_table.getColumnModel().getColumn(1).setPreferredWidth(120);  // Nom
        Patient_affichage_table.getColumnModel().getColumn(2).setPreferredWidth(120);  // Prénom
        Patient_affichage_table.getColumnModel().getColumn(3).setPreferredWidth(120);  // Date Naissance
        Patient_affichage_table.getColumnModel().getColumn(4).setPreferredWidth(60);   // Age
        Patient_affichage_table.getColumnModel().getColumn(5).setPreferredWidth(70);   // Sexe
        Patient_affichage_table.getColumnModel().getColumn(6).setPreferredWidth(90);   // Groupage
        Patient_affichage_table.getColumnModel().getColumn(7).setPreferredWidth(180);  // Adresse
        Patient_affichage_table.getColumnModel().getColumn(8).setPreferredWidth(120);  // Tel
        
        // Add scroll pane to table
        affichage_table_scroll = createStyledScrollPane(Patient_affichage_table);
        Affichage_patient.add(affichage_table_scroll, BorderLayout.CENTER);

        // Set up the control panel with buttons
        total_panel.setLayout(new BorderLayout());
        total_panel.setBackground(Color.WHITE);
        total_panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220, 220, 220)),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        
        // Create button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.add(modifierButton);
        buttonPanel.add(supprimerButton);
        
        total_panel.add(buttonPanel, BorderLayout.EAST);
        
        Affichage_patient.add(total_panel, BorderLayout.SOUTH);

        this.addTab("📋 Affichage", Affichage_panel());

        // AJOUT TAB
        setupAjoutTab();
        
        // RECHERCHE TAB
        setupRechercheTab();

        // Add tabs with icons
        this.addTab("📋 Affichage", Affichage_patient);
        this.addTab("➕ Ajout", ajout_patient);
        this.addTab("🔍 Recherche", recherche_patient);
        
        // Set tab colors
        this.setBackgroundAt(0, new Color(236, 240, 241));
        this.setBackgroundAt(1, new Color(236, 240, 241));
        this.setBackgroundAt(2, new Color(236, 240, 241));

        // EVENT HANDLERS
        ajouterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Get values from input fields
                    String nom = ajout_nom_Field.getText().trim();
                    String prenom = ajout_prenom_Field.getText().trim();
                    
                    // Handle JSpinner for dateNaissance
                    String dateNaissance = "";
                    if (dateSpinner.getValue() != null) {
                        // Convert JSpinner date to String format dd/MM/yyyy
                        Object spinnerValue = dateSpinner.getValue();
                        
                        if (spinnerValue instanceof java.util.Date) {
                            java.util.Date date = (java.util.Date) spinnerValue;
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                            dateNaissance = sdf.format(date);
                        } else if (spinnerValue instanceof Calendar) {
                            Calendar cal = (Calendar) spinnerValue;
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                            dateNaissance = sdf.format(cal.getTime());
                        } else {
                            // If it's a string or other format
                            dateNaissance = spinnerValue.toString();
                        }
                    }
                    
                    // Get other fields
                    String ageText = ajout_age_Field.getText().trim();
                    int age = ageText.isEmpty() ? 0 : Integer.parseInt(ageText);
                    
                    String sexe = ajout_sexe_Field.getText().trim();
                    String groupeSanguin = ajout_groupage_Field.getText().trim();
                    String adresse = ajout_adresse_Field.getText().trim();
                    String tel = ajout_tel_Field.getText().trim();
                    
                    // Create Personne object
                    Personne pers = new Personne(nom, prenom, dateNaissance, 
                                                 age, sexe, groupeSanguin, adresse, tel);
                    
                    // Call Ajouter_Patient method
                    Patient p = new Patient();
                    boolean success = p.Ajouter_Patient(pers);
                    
                    if (success) {
                        // Success message
                        JOptionPane.showMessageDialog(null, 
                            "✅ Patient ajouté avec succès!\nID: " + pers.getId_personne(), 
                            "Succès", 
                            JOptionPane.INFORMATION_MESSAGE);
                        
                        // Clear fields after successful addition
                        clearFields();
                        
                        // Refresh patient table if you have one
                        refreshPatientTable();
                    } else {
                        JOptionPane.showMessageDialog(null, 
                            "❌ Échec de l'ajout du patient", 
                            "Erreur", 
                            JOptionPane.ERROR_MESSAGE);
                    }
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, 
                        "⚠️ Âge doit être un nombre valide", 
                        "Erreur de saisie", 
                        JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, 
                        "❌ Erreur: " + ex.getMessage(), 
                        "Erreur", 
                        JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

        supprimerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Check if a row is selected
                int selectedRow = Patient_affichage_table.getSelectedRow();
                
                if (selectedRow == -1) {
                    // No row selected, show message
                    JOptionPane.showMessageDialog(null, 
                        "⚠️ Veuillez sélectionner un patient à supprimer", 
                        "Aucune sélection", 
                        JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                // Get the patient ID from the selected row
                // Since the table might be sorted, convert to model index
                int modelRow = Patient_affichage_table.convertRowIndexToModel(selectedRow);
                
                // Get the ID from the first column (index 0)
                Object idObject = Patient_affichage_table.getValueAt(selectedRow, 0);
                
                if (idObject == null) {
                    JOptionPane.showMessageDialog(null, 
                        "❌ Impossible de récupérer l'ID du patient", 
                        "Erreur", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                int id = 0;
                try {
                    id = Integer.parseInt(idObject.toString());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, 
                        "❌ ID de patient invalide: " + idObject, 
                        "Erreur", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // Get patient info for confirmation message
                String nom = Patient_affichage_table.getValueAt(selectedRow, 1).toString();
                String prenom = Patient_affichage_table.getValueAt(selectedRow, 2).toString();
                
                // Show confirmation dialog
                int confirmation = JOptionPane.showConfirmDialog(null, 
                    "Voulez-vous vraiment supprimer le patient?\n\n" +
                    "👤 ID: " + id + "\n" +
                    "📛 Nom: " + nom + " " + prenom + "\n\n" +
                    "⚠️ Cette action est irréversible!",
                    "Confirmation de suppression",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);
                
                if (confirmation == JOptionPane.YES_OPTION) {
                    try {
                        // Call the delete method
                        Patient p = new Patient();
                        boolean success = p.Supprimer_Patient(id);
                        
                        if (success) {
                            // Remove from JTable
                            tabelMODEL.removeRow(modelRow);
                            
                            // Update total count
                            updateTotalPatients(Patient_affichage_table.getRowCount());
                            
                            JOptionPane.showMessageDialog(null, 
                                "✅ Patient supprimé avec succès!", 
                                "Succès", 
                                JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, 
                                "❌ Échec de la suppression du patient.\n" +
                                "Le patient a peut-être des dossiers médicaux associés.", 
                                "Erreur", 
                                JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, 
                            "❌ Erreur lors de la suppression: " + ex.getMessage(), 
                            "Erreur", 
                            JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                    }
                }
            }
        });

        modifierButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Check if a row is selected
                int selectedRow = Patient_affichage_table.getSelectedRow();
                
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, 
                        "⚠️ Veuillez sélectionner un patient à modifier", 
                        "Aucune sélection", 
                        JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                // Get the patient ID from selected row
                int modelRow = Patient_affichage_table.convertRowIndexToModel(selectedRow);
                Object idObject = Patient_affichage_table.getValueAt(selectedRow, 0);
                
                if (idObject == null) {
                    JOptionPane.showMessageDialog(null, 
                        "❌ Impossible de récupérer l'ID du patient", 
                        "Erreur", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                int patientId = Integer.parseInt(idObject.toString());
                
                // Get current patient data from table
                String currentNom = Patient_affichage_table.getValueAt(selectedRow, 1).toString();
                String currentPrenom = Patient_affichage_table.getValueAt(selectedRow, 2).toString();
                String currentDateNaissance = Patient_affichage_table.getValueAt(selectedRow, 3).toString();
                int currentAge = Integer.parseInt(Patient_affichage_table.getValueAt(selectedRow, 4).toString());
                String currentSexe = Patient_affichage_table.getValueAt(selectedRow, 5).toString();
                String currentGroupage = Patient_affichage_table.getValueAt(selectedRow, 6).toString();
                String currentAdresse = Patient_affichage_table.getValueAt(selectedRow, 7).toString();
                String currentTel = Patient_affichage_table.getValueAt(selectedRow, 8).toString();
                
                // Create and show modification dialog
                ModifierPatientDialog modifierDialog = new ModifierPatientDialog(
                    patientId, currentNom, currentPrenom, currentDateNaissance, 
                    currentAge, currentSexe, currentGroupage, currentAdresse, currentTel
                );
                
                modifierDialog.setVisible(true);
                
                // Check if modification was successful
                if (modifierDialog.isModified()) {
                    // Get updated patient data
                    Personne updatedPatient = modifierDialog.getUpdatedPersonne();
                    
                    // Update in database
                    Patient p = new Patient();
                    boolean success = p.Modifier_Patient(updatedPatient);
                    
                    if (success) {
                        // Update the JTable
                        tabelMODEL.setValueAt(updatedPatient.getNom(), modelRow, 1);
                        tabelMODEL.setValueAt(updatedPatient.getPrenom(), modelRow, 2);
                        tabelMODEL.setValueAt(updatedPatient.getdateNaissance(), modelRow, 3);
                        tabelMODEL.setValueAt(updatedPatient.getAge(), modelRow, 4);
                        tabelMODEL.setValueAt(updatedPatient.getSexe(), modelRow, 5);
                        tabelMODEL.setValueAt(updatedPatient.getGroupe_sanguin(), modelRow, 6);
                        tabelMODEL.setValueAt(updatedPatient.getAdresse(), modelRow, 7);
                        tabelMODEL.setValueAt(updatedPatient.getTel(), modelRow, 8);
                        
                        // Refresh table display
                        tabelMODEL.fireTableRowsUpdated(modelRow, modelRow);
                        
                        JOptionPane.showMessageDialog(null, 
                            "✅ Patient modifié avec succès!", 
                            "Succès", 
                            JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, 
                            "❌ Échec de la modification du patient", 
                            "Erreur", 
                            JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        refreshPatientTable();
        updateTotalPatients(Patient_affichage_table.getRowCount());

    }
    
    private JPanel Affichage_panel() {
        return Affichage_patient;
    }
    
    private void setupAjoutTab() {
        ajout_patient.setLayout(new BorderLayout());
        ajout_patient.setBackground(new Color(245, 247, 250));
        
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
        
        JLabel ajoutTitleLabel = new JLabel("➕ Ajouter un Patient", SwingConstants.CENTER);
        ajoutTitleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        ajoutTitleLabel.setForeground(Color.WHITE);
        headerPanel.add(ajoutTitleLabel, BorderLayout.CENTER);
        
        ajout_patient.add(headerPanel, BorderLayout.NORTH);
        
        // Create form panel
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
        
        // Row 0: Nom
        g.gridx = 0; g.gridy = row; g.weightx = 0.0; g.fill = GridBagConstraints.NONE;
        formPanel.add(ajout_nom_Label, g);
        g.gridx = 1; g.fill = GridBagConstraints.HORIZONTAL; g.weightx = 1.0;
        formPanel.add(ajout_nom_Field, g);
        
        // Row 1: Prénom
        row++;
        g.gridx = 0; g.gridy = row;
        formPanel.add(ajout_prenom_Label, g);
        g.gridx = 1;
        formPanel.add(ajout_prenom_Field, g);
        
        // Row 2: Date de Naissance
        row++;
        g.gridx = 0; g.gridy = row;
        formPanel.add(ajout_datenaiss_label, g);
        g.gridx = 1;
        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        datePanel.add(dateSpinner);
        formPanel.add(datePanel, g);
        
        // Row 3: Age
        row++;
        g.gridx = 0; g.gridy = row;
        formPanel.add(ajout_age_Label, g);
        g.gridx = 1;
        formPanel.add(ajout_age_Field, g);
        
        // Row 4: Sexe
        row++;
        g.gridx = 0; g.gridy = row;
        formPanel.add(ajout_sexe_Label, g);
        g.gridx = 1;
        formPanel.add(ajout_sexe_Field, g);
        
        // Row 5: Groupage
        row++;
        g.gridx = 0; g.gridy = row;
        formPanel.add(ajout_groupage_Label, g);
        g.gridx = 1;
        formPanel.add(ajout_groupage_Field, g);
        
        // Row 6: Adresse
        row++;
        g.gridx = 0; g.gridy = row;
        formPanel.add(ajout_adresse_Label, g);
        g.gridx = 1;
        formPanel.add(ajout_adresse_Field, g);
        
        // Row 7: Tel
        row++;
        g.gridx = 0; g.gridy = row;
        formPanel.add(ajout_tel_Label, g);
        g.gridx = 1;
        formPanel.add(ajout_tel_Field, g);
        
        // Row 8: Button
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
        
        ajout_patient.add(wrapperPanel, BorderLayout.CENTER);
    }
    
    private void setupRechercheTab() {
        recherche_patient.setLayout(new BorderLayout());
        recherche_patient.setBackground(new Color(245, 247, 250));
        
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
        
        JLabel rechercheTitleLabel = new JLabel("🔍 Recherche de Patients", SwingConstants.CENTER);
        rechercheTitleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        rechercheTitleLabel.setForeground(Color.WHITE);
        headerPanel.add(rechercheTitleLabel, BorderLayout.CENTER);
        
        recherche_patient.add(headerPanel, BorderLayout.NORTH);
        
        // Main container panel for the search forms
        JPanel rechercheContainer = new JPanel(new GridLayout(1, 2, 20, 0));
        rechercheContainer.setBackground(new Color(245, 247, 250));
        rechercheContainer.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        // Normal Recherche Panel
        JPanel normalRecherchePanel = createSearchPanel(
            "Recherche Simple", 
            new Color(52, 152, 219),
            new String[]{"Nom:", "Prénom:"},
            new Component[]{recherche_nom_Field, recherche_prenom_Field},
            rechercheNormalButton
        );
        
        // Advanced Recherche Panel
        JPanel advancedRecherchePanel = createSearchPanel(
            "Recherche Avancée", 
            new Color(155, 89, 182),
            new String[]{"Date Naissance:", "Age:", "Sexe:", "Groupage:", "Adresse:", "Tel:"},
            new Component[]{rechercheDateSpinner, recherche_age_Field, recherche_sexe_Field, 
                          recherche_groupage_Field, recherche_adresse_Field, recherche_tel_Field},
            rechercheAvanceButton
        );
        
        rechercheContainer.add(normalRecherchePanel);
        rechercheContainer.add(advancedRecherchePanel);
        
        recherche_patient.add(rechercheContainer, BorderLayout.CENTER);
    }
    
    // Helper methods for styling
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
    
    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        label.setForeground(new Color(44, 62, 80));
        return label;
    }
    
    private JTextField createStyledTextField() {
        JTextField field = new JTextField(15);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        return field;
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
    
    private JScrollPane createStyledScrollPane(JTable table) {
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        return scrollPane;
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
    
    // Helper method to update the total patients count
    public void updateTotalPatients(int count) {
        Total_patient.setText("📊 Total Patients: " + count);
    }
    
    // Getter for selected patient ID from table
    public Integer getSelectedPatientId() {
        int selectedRow = Patient_affichage_table.getSelectedRow();
        if (selectedRow != -1) {
            return (Integer) Patient_affichage_table.getValueAt(selectedRow, 0);
        }
        return null;
    }
    
    // Getter for the table model to add/remove rows
    public DefaultTableModel getTableModel() {
        return tabelMODEL;
    }
    
    // Getter for the table
    public JTable getPatientTable() {
        return Patient_affichage_table;
    }
    
    // Getter for buttons
    public JButton getSupprimerButton() {
        return supprimerButton;
    }
    
    public JButton getModifierButton() {
        return modifierButton;
    }
    
    public JButton getAjouterButton() {
        return ajouterButton;
    }
    
    public JButton getRechercheNormalButton() {
        return rechercheNormalButton;
    }
    
    public JButton getRechercheAvanceButton() {
        return rechercheAvanceButton;
    }
    
    // Helper method to get the selected date as a formatted string (Ajout tab)
    public String getSelectedDate() {
        java.util.Date date = (java.util.Date) dateSpinner.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate().format(formatter);
    }
    
    // Helper method to set the date (Ajout tab)
    public void setSelectedDate(LocalDate date) {
        dateSpinner.setValue(java.sql.Date.valueOf(date));
    }
    
    // Helper method to get the selected date from recherche tab
    public String getRechercheSelectedDate() {
        java.util.Date date = (java.util.Date) rechercheDateSpinner.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate().format(formatter);
    }
    
    // Helper method to set the date in recherche tab
    public void setRechercheSelectedDate(LocalDate date) {
        rechercheDateSpinner.setValue(java.sql.Date.valueOf(date));
    }
    
    // Getters for text fields in Ajout tab
    public JTextField getAjoutNomField() { return ajout_nom_Field; }
    public JTextField getAjoutPrenomField() { return ajout_prenom_Field; }
    public JTextField getAjoutAgeField() { return ajout_age_Field; }
    public JTextField getAjoutSexeField() { return ajout_sexe_Field; }
    public JTextField getAjoutGroupageField() { return ajout_groupage_Field; }
    public JTextField getAjoutAdresseField() { return ajout_adresse_Field; }
    public JTextField getAjoutTelField() { return ajout_tel_Field; }
    
    // Getters for text fields in Recherche tab
    public JTextField getRechercheNomField() { return recherche_nom_Field; }
    public JTextField getRecherchePrenomField() { return recherche_prenom_Field; }
    public JTextField getRechercheAgeField() { return recherche_age_Field; }
    public JTextField getRechercheSexeField() { return recherche_sexe_Field; }
    public JTextField getRechercheGroupageField() { return recherche_groupage_Field; }
    public JTextField getRechercheAdresseField() { return recherche_adresse_Field; }
    public JTextField getRechercheTelField() { return recherche_tel_Field; }
}
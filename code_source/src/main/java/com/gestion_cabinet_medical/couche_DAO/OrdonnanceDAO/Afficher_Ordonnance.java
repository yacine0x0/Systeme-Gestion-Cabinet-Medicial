package com.gestion_cabinet_medical.couche_DAO.OrdonnanceDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.text.SimpleDateFormat;

public class Afficher_Ordonnance {
    private final String url = "jdbc:mysql://localhost:3306/ClinicaPro";
    private final String user = "root";
    private final String password = "";

    // Method to get and display a specific ordonnance
    public void afficherOrdonnance(int numOrdonnance, JTable table) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(url, user, password);
            
            // Query to get ordonnance details along with médecin and patient info
            String query = "SELECT o.numOrdonnance, o.details, o.datePresc, " +
                          "m.nom AS med_nom, m.prenom AS med_prenom, " +
                          "p.nom AS pat_nom, p.prenom AS pat_prenom, " +
                          "p.age, p.dateNaissance, p.sexe, p.groupeSanguin, " +
                          "p.adresse, p.tel " +
                          "FROM Ordonnance o " +
                          "JOIN Personne m ON o.idMedecin = m.idPersonne AND m.typePersonne = 'Médecin' " +
                          "JOIN Personne p ON o.idPatient = p.idPersonne AND p.typePersonne = 'Patient' " +
                          "WHERE o.numOrdonnance = ?";
            
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, numOrdonnance);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                // Create frame to display ordonnance
                JFrame frame = new JFrame("Détails de l'Ordonnance N°" + numOrdonnance);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setLayout(new BorderLayout(10, 10));
                frame.setSize(900, 700);
                
                // Main panel with GridBagLayout for better organization
                JPanel mainPanel = new JPanel(new GridBagLayout());
                mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(5, 5, 5, 5);
                gbc.fill = GridBagConstraints.HORIZONTAL;
                
                // Title
                gbc.gridx = 0; gbc.gridy = 0;
                gbc.gridwidth = 2;
                JLabel titleLabel = new JLabel("ORDONNANCE N°" + numOrdonnance);
                titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
                titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
                mainPanel.add(titleLabel, gbc);
                
                gbc.gridwidth = 1;
                gbc.gridy = 1;
                mainPanel.add(new JLabel(" "), gbc); // Empty space
                
                // Médecin Info Section
                gbc.gridx = 0; gbc.gridy = 2;
                gbc.gridwidth = 2;
                JLabel medSectionLabel = new JLabel("MÉDECIN PRESCRIPTEUR");
                medSectionLabel.setFont(new Font("Arial", Font.BOLD, 14));
                mainPanel.add(medSectionLabel, gbc);
                
                // Médecin Nom
                gbc.gridx = 0; gbc.gridy = 3;
                gbc.gridwidth = 1;
                mainPanel.add(new JLabel("Nom:"), gbc);
                gbc.gridx = 1;
                String medNomComplet = rs.getString("med_nom") + " " + rs.getString("med_prenom");
                mainPanel.add(new JLabel(medNomComplet), gbc);
                
                // Empty space
                gbc.gridx = 0; gbc.gridy = 4;
                mainPanel.add(new JLabel(" "), gbc);
                
                // Patient Info Section
                gbc.gridx = 0; gbc.gridy = 5;
                gbc.gridwidth = 2;
                JLabel patSectionLabel = new JLabel("INFORMATIONS DU PATIENT");
                patSectionLabel.setFont(new Font("Arial", Font.BOLD, 14));
                mainPanel.add(patSectionLabel, gbc);
                
                // Patient Info
                gbc.gridx = 0; gbc.gridy = 6;
                gbc.gridwidth = 1;
                mainPanel.add(new JLabel("Nom:"), gbc);
                gbc.gridx = 1;
                String patNomComplet = rs.getString("pat_nom") + " " + rs.getString("pat_prenom");
                mainPanel.add(new JLabel(patNomComplet), gbc);
                
                // Age
                gbc.gridx = 0; gbc.gridy = 7;
                mainPanel.add(new JLabel("Âge:"), gbc);
                gbc.gridx = 1;
                mainPanel.add(new JLabel(String.valueOf(rs.getInt("age")) + " ans"), gbc);
                
                // Date de naissance
                gbc.gridx = 0; gbc.gridy = 8;
                mainPanel.add(new JLabel("Date de naissance:"), gbc);
                gbc.gridx = 1;
                Date dateNaissance = rs.getDate("dateNaissance");
                if (dateNaissance != null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    mainPanel.add(new JLabel(sdf.format(dateNaissance)), gbc);
                } else {
                    mainPanel.add(new JLabel("Non spécifié"), gbc);
                }
                
                // Sexe
                gbc.gridx = 0; gbc.gridy = 9;
                mainPanel.add(new JLabel("Sexe:"), gbc);
                gbc.gridx = 1;
                mainPanel.add(new JLabel(rs.getString("sexe")), gbc);
                
                // Groupe Sanguin
                gbc.gridx = 0; gbc.gridy = 10;
                mainPanel.add(new JLabel("Groupe Sanguin:"), gbc);
                gbc.gridx = 1;
                mainPanel.add(new JLabel(rs.getString("groupeSanguin")), gbc);
                
                // Adresse
                gbc.gridx = 0; gbc.gridy = 11;
                mainPanel.add(new JLabel("Adresse:"), gbc);
                gbc.gridx = 1;
                mainPanel.add(new JLabel(rs.getString("adresse")), gbc);
                
                // Téléphone
                gbc.gridx = 0; gbc.gridy = 12;
                mainPanel.add(new JLabel("Téléphone:"), gbc);
                gbc.gridx = 1;
                mainPanel.add(new JLabel(String.valueOf(rs.getInt("tel"))), gbc);
                
                // Empty space
                gbc.gridx = 0; gbc.gridy = 13;
                mainPanel.add(new JLabel(" "), gbc);
                
                // Date ordonnance
                gbc.gridx = 0; gbc.gridy = 14;
                gbc.gridwidth = 2;
                JLabel dateLabel = new JLabel();
                Date dateOrdonnance = rs.getDate("datePresc");
                if (dateOrdonnance != null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    dateLabel.setText("Date de prescription: " + sdf.format(dateOrdonnance));
                } else {
                    dateLabel.setText("Date de prescription: Non spécifié");
                }
                dateLabel.setFont(new Font("Arial", Font.ITALIC, 12));
                mainPanel.add(dateLabel, gbc);
                
                // Détails - TextArea with scroll
                gbc.gridx = 0; gbc.gridy = 15;
                gbc.gridwidth = 2;
                gbc.fill = GridBagConstraints.BOTH;
                gbc.weightx = 1.0;
                gbc.weighty = 1.0;
                gbc.insets = new Insets(10, 5, 5, 5);
                
                JLabel detailsLabel = new JLabel("PRESCRIPTION:");
                detailsLabel.setFont(new Font("Arial", Font.BOLD, 14));
                mainPanel.add(detailsLabel, gbc);
                
                gbc.gridy = 16;
                gbc.insets = new Insets(5, 5, 20, 5);
                JTextArea detailsArea = new JTextArea(15, 50);
                String details = rs.getString("details");
                detailsArea.setText(details != null ? details : "Aucun détail fourni");
                detailsArea.setLineWrap(true);
                detailsArea.setWrapStyleWord(true);
                detailsArea.setEditable(false);
                detailsArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
                JScrollPane scrollPane = new JScrollPane(detailsArea);
                scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                mainPanel.add(scrollPane, gbc);
                
                frame.add(mainPanel, BorderLayout.CENTER);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                
            } else {
                JOptionPane.showMessageDialog(table.getParent(), 
                    "Ordonnance non trouvée avec le numéro: " + numOrdonnance, 
                    "Erreur", 
                    JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(table.getParent(), 
                "Erreur lors de la récupération de l'ordonnance: " + e.getMessage(), 
                "Erreur Base de Données", 
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to display all ordonnances in your existing JTable
    public void AfficherTousOrdonnances(JTable table) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
            
            // Query to get all ordonnances
            String query = "SELECT o.numOrdonnance, " +
                          "CONCAT(m.nom, ' ', m.prenom, ' (Médecin)') AS Type, " +
                          "SUBSTRING(o.details, 1, 50) AS details_abrégés " +
                          "FROM Ordonnance o " +
                          "JOIN Personne m ON o.idMedecin = m.idPersonne AND m.typePersonne = 'Médecin' " +
                          "ORDER BY o.numOrdonnance DESC";
            
            rs = stmt.executeQuery(query);
            
            // Get the table model
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            
            // Clear existing rows
            model.setRowCount(0);
            
            // Add rows to model
            while (rs.next()) {
                Object[] row = new Object[3];
                row[0] = rs.getInt("numOrdonnance");
                row[1] = rs.getString("Type");
                
                String details = rs.getString("details_abrégés");
                row[2] = (details != null && details.length() > 0) ? details : "Aucun détail";
                
                model.addRow(row);
            }
            
            // Configure table properties
            table.setAutoCreateRowSorter(true);
            table.setRowHeight(25);
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            
            // Set column widths
            if (table.getColumnCount() >= 3) {
                table.getColumnModel().getColumn(0).setPreferredWidth(80);   // numOrdonnance
                table.getColumnModel().getColumn(1).setPreferredWidth(200);  // Type (Médecin)
                table.getColumnModel().getColumn(2).setPreferredWidth(400);  // Détails
            }
            
            // Add double-click listener to the table
            table.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    if (evt.getClickCount() == 2) {
                        int row = table.rowAtPoint(evt.getPoint());
                        if (row >= 0) {
                            DefaultTableModel model = (DefaultTableModel) table.getModel();
                            int numOrdonnance = (int) model.getValueAt(row, 0);
                            afficherOrdonnance(numOrdonnance, table);
                        }
                    }
                }
            });
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(table.getParent(), 
                "Erreur lors de la récupération des ordonnances: " + e.getMessage(), 
                "Erreur Base de Données", 
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Overloaded method for AfficherTousOrdonnances that can be called without a table
    public void AfficherTousOrdonnances() {
        // Create a default table if none is provided
        DefaultTableModel model = new DefaultTableModel(new Object[][] {},
            new String[] {"numOrdonnance", "Type", "Détails"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        
        JFrame frame = new JFrame("Liste de toutes les Ordonnances");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(800, 600);
        frame.add(scrollPane, BorderLayout.CENTER);
        
        // Add button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton refreshButton = new JButton("Actualiser");
        refreshButton.addActionListener(e -> AfficherTousOrdonnances(table));
        buttonPanel.add(refreshButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        
        // Add info label
        JLabel infoLabel = new JLabel("Double-cliquez sur une ligne pour voir les détails complets");
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        infoLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        frame.add(infoLabel, BorderLayout.NORTH);
        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        // Load data into the table
        AfficherTousOrdonnances(table);
    }
}
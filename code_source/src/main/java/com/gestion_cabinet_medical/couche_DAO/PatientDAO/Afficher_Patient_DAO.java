package com.gestion_cabinet_medical.couche_DAO.PatientDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class Afficher_Patient_DAO {
    private final String url = "jdbc:mysql://localhost:3306/ClinicaPro";
    private final String user = "root";
    private final String password = "";
    
    // Method to get ONE patient by ID - returns Object[] in EXACT JTable column order
    public Object[] AfficherPatient(int idPatient) {
        String query = "SELECT idPersonne, nom, prenom, dateNaissance, age, sexe, groupeSanguin, adresse, tel " +
                      "FROM Personne WHERE idPersonne = ? AND typePersonne = 'Patient'";
        
        try (
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(query)
        ) {
            pst.setInt(1, idPatient);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                // Create Object[] with 9 elements matching your EXACT JTable column order:
                Object[] patient = new Object[9];
                
                // ⭐⭐⭐ IMPORTANT: This order MUST match your JTable columns ⭐⭐⭐
                // Column 0: "ID"
                patient[0] = rs.getInt("idPersonne");           // ID (Integer)
                
                // Column 1: "Nom"
                patient[1] = rs.getString("nom");               // Nom (String)
                
                // Column 2: "Prénom"
                patient[2] = rs.getString("prenom");            // Prénom (String)
                
                // Column 3: "Date Naissance" - format from YYYY-MM-DD to dd/MM/yyyy
                java.sql.Date dateSql = rs.getDate("dateNaissance");
                if (dateSql != null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    patient[3] = sdf.format(dateSql);           // Date Naissance (String formatted)
                } else {
                    patient[3] = "";                            // Empty string if null
                }
                
                // Column 4: "Age"
                patient[4] = rs.getInt("age");                  // Age (Integer)
                
                // Column 5: "Sexe"
                patient[5] = rs.getString("sexe");              // Sexe (String)
                
                // Column 6: "Groupage"
                patient[6] = rs.getString("groupeSanguin");     // Groupage (String)
                
                // Column 7: "Adresse"
                patient[7] = rs.getString("adresse");           // Adresse (String)
                
                // Column 8: "Tel"
                patient[8] = rs.getString("tel");               // Tel (String)
                
                rs.close();
                return patient;
            }
            
            rs.close();
            return null; // Patient not found
            
        } catch (SQLException ex) {
            System.out.println("Erreur SQL: " + ex.getMessage());
            return null;
        }
    }
    
    // Method to get ALL patients and populate your JTable
    public void afficherTousPatients(javax.swing.JTable table) {
        String query = "SELECT idPersonne, nom, prenom, dateNaissance, age, sexe, groupeSanguin, adresse, tel " +
                      "FROM Personne WHERE typePersonne = 'Patient' ORDER BY nom, prenom";
        
        // Get the DefaultTableModel from your JTable
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) table.getModel();
        
        // Clear existing rows
        model.setRowCount(0);
        
        try (
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery()
        ) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            
            while (rs.next()) {
                // Create Object[] for EACH patient - SAME order as JTable columns
                Object[] row = new Object[9];
                
                // ⭐⭐⭐ FILL IN EXACT JTable COLUMN ORDER ⭐⭐⭐
                row[0] = rs.getInt("idPersonne");           // Column 0: ID
                row[1] = rs.getString("nom");               // Column 1: Nom
                row[2] = rs.getString("prenom");            // Column 2: Prénom
                
                // Column 3: Date Naissance - format for display
                java.sql.Date dateSql = rs.getDate("dateNaissance");
                if (dateSql != null) {
                    row[3] = sdf.format(dateSql);           // Column 3: Date Naissance (dd/MM/yyyy)
                } else {
                    row[3] = "";                            // Empty if null
                }
                
                row[4] = rs.getInt("age");                  // Column 4: Age
                row[5] = rs.getString("sexe");              // Column 5: Sexe
                row[6] = rs.getString("groupeSanguin");     // Column 6: Groupage
                row[7] = rs.getString("adresse");           // Column 7: Adresse
                row[8] = rs.getString("tel");               // Column 8: Tel
                
                // ADD THIS ROW TO YOUR JTABLE
                model.addRow(row);
            }
            
            // Refresh the table to show new data
            model.fireTableDataChanged();
            
        } catch (SQLException ex) {
            System.out.println("Erreur lors du chargement des patients: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
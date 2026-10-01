package com.gestion_cabinet_medical.couche_DAO.OrdonnanceDAO;

import java.sql.*;
import javax.swing.JOptionPane;

public class Ajouter_Ordonnance {
    private final String url = "jdbc:mysql://localhost:3306/ClinicaPro";
    private final String user = "root";
    private final String password = "";

    /**
     * Adds a new ordonnance to the database
     * 
     * @param medNom        Nom du médecin
     * @param medPrenom     Prénom du médecin
     * @param patNom        Nom du patient
     * @param patPrenom     Prénom du patient
     * @param details       Détails de l'ordonnance
     * @param datePresc     Date de prescription (java.sql.Date)
     * @return              true si l'ajout est réussi, false sinon
     */
    public boolean ajouterOrdonnance(String medNom, String medPrenom, 
                                     String patNom, String patPrenom, 
                                     String details, Date datePresc) {
        Connection conn = null;
        PreparedStatement pstmtMed = null;
        PreparedStatement pstmtPat = null;
        PreparedStatement pstmtInsert = null;
        ResultSet rsMed = null;
        ResultSet rsPat = null;

        try {
            // Establish database connection
            conn = DriverManager.getConnection(url, user, password);
            conn.setAutoCommit(false); // Start transaction

            // 1. Get Médecin ID
            String queryMed = "SELECT idPersonne FROM Personne " +
                             "WHERE nom = ? AND prenom = ? AND typePersonne = 'Médecin'";
            pstmtMed = conn.prepareStatement(queryMed);
            pstmtMed.setString(1, medNom);
            pstmtMed.setString(2, medPrenom);
            rsMed = pstmtMed.executeQuery();

            int idMedecin = -1;
            if (rsMed.next()) {
                idMedecin = rsMed.getInt("idPersonne");
            } else {
                JOptionPane.showMessageDialog(null, 
                    "Médecin non trouvé: " + medNom + " " + medPrenom, 
                    "Erreur", 
                    JOptionPane.ERROR_MESSAGE);
                conn.rollback();
                return false;
            }

            // 2. Get Patient ID
            String queryPat = "SELECT idPersonne FROM Personne " +
                             "WHERE nom = ? AND prenom = ? AND typePersonne = 'Patient'";
            pstmtPat = conn.prepareStatement(queryPat);
            pstmtPat.setString(1, patNom);
            pstmtPat.setString(2, patPrenom);
            rsPat = pstmtPat.executeQuery();

            int idPatient = -1;
            if (rsPat.next()) {
                idPatient = rsPat.getInt("idPersonne");
            } else {
                JOptionPane.showMessageDialog(null, 
                    "Patient non trouvé: " + patNom + " " + patPrenom, 
                    "Erreur", 
                    JOptionPane.ERROR_MESSAGE);
                conn.rollback();
                return false;
            }

            // 3. Insert new ordonnance
            String queryInsert = "INSERT INTO Ordonnance (idMedecin, idPatient, details, datePresc) " +
                                "VALUES (?, ?, ?, ?)";
            pstmtInsert = conn.prepareStatement(queryInsert, Statement.RETURN_GENERATED_KEYS);
            pstmtInsert.setInt(1, idMedecin);
            pstmtInsert.setInt(2, idPatient);
            pstmtInsert.setString(3, details);
            pstmtInsert.setDate(4, datePresc);

            int rowsAffected = pstmtInsert.executeUpdate();

            if (rowsAffected > 0) {
                // Get the generated numOrdonnance
                ResultSet generatedKeys = pstmtInsert.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int numOrdonnance = generatedKeys.getInt(1);
                    JOptionPane.showMessageDialog(null, 
                        "Ordonnance ajoutée avec succès!\nNuméro d'ordonnance: " + numOrdonnance, 
                        "Succès", 
                        JOptionPane.INFORMATION_MESSAGE);
                }
                conn.commit();
                return true;
            } else {
                JOptionPane.showMessageDialog(null, 
                    "Erreur lors de l'ajout de l'ordonnance", 
                    "Erreur", 
                    JOptionPane.ERROR_MESSAGE);
                conn.rollback();
                return false;
            }

        } catch (SQLException e) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            
            JOptionPane.showMessageDialog(null, 
                "Erreur SQL: " + e.getMessage(), 
                "Erreur Base de Données", 
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
            
        } finally {
            // Close all resources
            try {
                if (rsMed != null) rsMed.close();
                if (rsPat != null) rsPat.close();
                if (pstmtMed != null) pstmtMed.close();
                if (pstmtPat != null) pstmtPat.close();
                if (pstmtInsert != null) pstmtInsert.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Overloaded method with String date parameter
     * 
     * @param medNom        Nom du médecin
     * @param medPrenom     Prénom du médecin
     * @param patNom        Nom du patient
     * @param patPrenom     Prénom du patient
     * @param details       Détails de l'ordonnance
     * @param datePrescStr  Date de prescription (format: yyyy-MM-dd)
     * @return              true si l'ajout est réussi, false sinon
     */
    public boolean ajouterOrdonnance(String medNom, String medPrenom, 
                                     String patNom, String patPrenom, 
                                     String details, String datePrescStr) {
        try {
            Date datePresc = Date.valueOf(datePrescStr); // Convert String to java.sql.Date
            return ajouterOrdonnance(medNom, medPrenom, patNom, patPrenom, details, datePresc);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, 
                "Format de date invalide. Utilisez le format: yyyy-MM-dd", 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Overloaded method with java.util.Date parameter
     * 
     * @param medNom        Nom du médecin
     * @param medPrenom     Prénom du médecin
     * @param patNom        Nom du patient
     * @param patPrenom     Prénom du patient
     * @param details       Détails de l'ordonnance
     * @param datePrescUtil Date de prescription (java.util.Date)
     * @return              true si l'ajout est réussi, false sinon
     */
    public boolean ajouterOrdonnance(String medNom, String medPrenom, 
                                     String patNom, String patPrenom, 
                                     String details, java.util.Date datePrescUtil) {
        Date datePresc = new Date(datePrescUtil.getTime()); // Convert java.util.Date to java.sql.Date
        return ajouterOrdonnance(medNom, medPrenom, patNom, patPrenom, details, datePresc);
    }
}
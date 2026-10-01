package com.gestion_cabinet_medical.couche_DAO.OrdonnanceDAO;

import java.sql.*;
import javax.swing.JOptionPane;

public class Supprimer_Ordonnance {
    private final String url = "jdbc:mysql://localhost:3306/ClinicaPro";
    private final String user = "root";
    private final String password = "";

    /**
     * Supprime une ordonnance de la base de données
     * 
     * @param numOrdonnance Le numéro de l'ordonnance à supprimer
     * @return true si la suppression est réussie, false sinon
     */
    public boolean supprimerOrdonnance(int numOrdonnance) {
        Connection conn = null;
        PreparedStatement pstmtCheck = null;
        PreparedStatement pstmtDelete = null;
        ResultSet rs = null;

        try {
            // Établir la connexion à la base de données
            conn = DriverManager.getConnection(url, user, password);
            
            // 1. Vérifier si l'ordonnance existe
            String queryCheck = "SELECT o.numOrdonnance, o.datePresc, " +
                               "CONCAT(m.nom, ' ', m.prenom) AS medecin, " +
                               "CONCAT(p.nom, ' ', p.prenom) AS patient " +
                               "FROM Ordonnance o " +
                               "JOIN Personne m ON o.idMedecin = m.idPersonne " +
                               "JOIN Personne p ON o.idPatient = p.idPersonne " +
                               "WHERE o.numOrdonnance = ?";
            
            pstmtCheck = conn.prepareStatement(queryCheck);
            pstmtCheck.setInt(1, numOrdonnance);
            rs = pstmtCheck.executeQuery();

            if (!rs.next()) {
                JOptionPane.showMessageDialog(null, 
                    "Aucune ordonnance trouvée avec le numéro: " + numOrdonnance, 
                    "Erreur", 
                    JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            // Récupérer les informations pour confirmation
            String datePresc = rs.getString("datePresc");
            String medecin = rs.getString("medecin");
            String patient = rs.getString("patient");
            
            // 2. Demander confirmation à l'utilisateur
            String message = "Êtes-vous sûr de vouloir supprimer cette ordonnance?\n\n" +
                           "Numéro: " + numOrdonnance + "\n" +
                           "Date: " + datePresc + "\n" +
                           "Médecin: " + medecin + "\n" +
                           "Patient: " + patient;
            
            int confirmation = JOptionPane.showConfirmDialog(null, 
                message, 
                "Confirmation de suppression", 
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
            
            if (confirmation != JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(null, 
                    "Suppression annulée", 
                    "Annulation", 
                    JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
            
            // 3. Supprimer l'ordonnance
            String queryDelete = "DELETE FROM Ordonnance WHERE numOrdonnance = ?";
            pstmtDelete = conn.prepareStatement(queryDelete);
            pstmtDelete.setInt(1, numOrdonnance);
            
            int rowsAffected = pstmtDelete.executeUpdate();
            
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, 
                    "Ordonnance numéro " + numOrdonnance + " supprimée avec succès!", 
                    "Succès", 
                    JOptionPane.INFORMATION_MESSAGE);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, 
                    "Échec de la suppression de l'ordonnance", 
                    "Erreur", 
                    JOptionPane.ERROR_MESSAGE);
                return false;
            }

        } catch (SQLException e) {
            // Vérifier si c'est une violation de contrainte de clé étrangère
            if (e.getMessage().contains("foreign key constraint")) {
                JOptionPane.showMessageDialog(null, 
                    "Impossible de supprimer cette ordonnance.\n" +
                    "Elle est référencée dans d'autres tables (consultations, etc.).", 
                    "Violation de contrainte", 
                    JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, 
                    "Erreur SQL lors de la suppression: " + e.getMessage(), 
                    "Erreur Base de Données", 
                    JOptionPane.ERROR_MESSAGE);
            }
            e.printStackTrace();
            return false;
            
        } finally {
            // Fermer toutes les ressources
            try {
                if (rs != null) rs.close();
                if (pstmtCheck != null) pstmtCheck.close();
                if (pstmtDelete != null) pstmtDelete.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Supprime une ordonnance sans demander de confirmation
     * (Utile pour les opérations par lots ou les tests)
     * 
     * @param numOrdonnance Le numéro de l'ordonnance à supprimer
     * @return true si la suppression est réussie, false sinon
     */
    public boolean supprimerOrdonnanceSansConfirmation(int numOrdonnance) {
        Connection conn = null;
        PreparedStatement pstmtDelete = null;

        try {
            conn = DriverManager.getConnection(url, user, password);
            String queryDelete = "DELETE FROM Ordonnance WHERE numOrdonnance = ?";
            
            pstmtDelete = conn.prepareStatement(queryDelete);
            pstmtDelete.setInt(1, numOrdonnance);
            
            int rowsAffected = pstmtDelete.executeUpdate();
            
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (pstmtDelete != null) pstmtDelete.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Vérifie si une ordonnance existe
     * 
     * @param numOrdonnance Le numéro de l'ordonnance à vérifier
     * @return true si l'ordonnance existe, false sinon
     */
    public boolean ordonnanceExiste(int numOrdonnance) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(url, user, password);
            String query = "SELECT COUNT(*) as count FROM Ordonnance WHERE numOrdonnance = ?";
            
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, numOrdonnance);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("count") > 0;
            }
            return false;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
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

    /**
     * Récupère les détails d'une ordonnance avant suppression
     * 
     * @param numOrdonnance Le numéro de l'ordonnance
     * @return String contenant les détails de l'ordonnance, null si non trouvée
     */
    public String getDetailsOrdonnance(int numOrdonnance) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(url, user, password);
            String query = "SELECT o.numOrdonnance, o.datePresc, o.details, " +
                          "CONCAT(m.nom, ' ', m.prenom) AS medecin, " +
                          "CONCAT(p.nom, ' ', p.prenom) AS patient " +
                          "FROM Ordonnance o " +
                          "JOIN Personne m ON o.idMedecin = m.idPersonne " +
                          "JOIN Personne p ON o.idPatient = p.idPersonne " +
                          "WHERE o.numOrdonnance = ?";
            
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, numOrdonnance);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                StringBuilder details = new StringBuilder();
                details.append("Numéro: ").append(rs.getInt("numOrdonnance")).append("\n");
                details.append("Date: ").append(rs.getString("datePresc")).append("\n");
                details.append("Médecin: ").append(rs.getString("medecin")).append("\n");
                details.append("Patient: ").append(rs.getString("patient")).append("\n");
                details.append("Détails: ").append(rs.getString("details"));
                
                return details.toString();
            }
            return null;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
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

    /**
     * Supprime plusieurs ordonnances en une seule transaction
     * 
     * @param numerosOrdonnances Tableau des numéros d'ordonnances à supprimer
     * @return Nombre d'ordonnances supprimées avec succès
     */
    public int supprimerOrdonnancesParLot(int[] numerosOrdonnances) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int totalSupprimees = 0;

        try {
            conn = DriverManager.getConnection(url, user, password);
            conn.setAutoCommit(false); // Démarrer la transaction
            
            String query = "DELETE FROM Ordonnance WHERE numOrdonnance = ?";
            pstmt = conn.prepareStatement(query);
            
            for (int numOrdonnance : numerosOrdonnances) {
                pstmt.setInt(1, numOrdonnance);
                int rowsAffected = pstmt.executeUpdate();
                
                if (rowsAffected > 0) {
                    totalSupprimees++;
                }
            }
            
            conn.commit(); // Valider la transaction
            return totalSupprimees;
            
        } catch (SQLException e) {
            try {
                if (conn != null) conn.rollback(); // Annuler la transaction en cas d'erreur
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            
            e.printStackTrace();
            return 0;
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
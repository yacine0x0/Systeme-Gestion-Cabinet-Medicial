package com.gestion_cabinet_medical.couche_DAO.PatientDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import com.gestion_cabinet_medical.couche_metier.model.Personne;

public class Rechercher_Patient_DAO {
    private final String url = "jdbc:mysql://localhost:3306/ClinicaPro";
    private final String user = "root";
    private final String password = "";

    // Method 1: Simple search by nom and prenom - returns boolean
    public boolean rechercheSimple(String nom, String prenom) {
        // If both nom and prenom are empty, return false
        if ((nom == null || nom.trim().isEmpty()) && (prenom == null || prenom.trim().isEmpty())) {
            return false;
        }
        
        String query = "SELECT COUNT(*) FROM Personne WHERE typePersonne = 'Patient' ";
        
        // Build query based on which parameters are provided
        if (nom != null && !nom.trim().isEmpty() && prenom != null && !prenom.trim().isEmpty()) {
            // Both nom and prenom provided
            query += "AND nom LIKE ? AND prenom LIKE ?";
        } else if (nom != null && !nom.trim().isEmpty()) {
            // Only nom provided
            query += "AND nom LIKE ?";
        } else if (prenom != null && !prenom.trim().isEmpty()) {
            // Only prenom provided
            query += "AND prenom LIKE ?";
        }
        
        try (
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(query)
        ) {
            int paramIndex = 1;
            
            // Set parameters
            if (nom != null && !nom.trim().isEmpty() && prenom != null && !prenom.trim().isEmpty()) {
                pst.setString(paramIndex++, "%" + nom.trim() + "%");
                pst.setString(paramIndex++, "%" + prenom.trim() + "%");
            } else if (nom != null && !nom.trim().isEmpty()) {
                pst.setString(paramIndex++, "%" + nom.trim() + "%");
            } else if (prenom != null && !prenom.trim().isEmpty()) {
                pst.setString(paramIndex++, "%" + prenom.trim() + "%");
            }
            
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0; // Returns true if at least one patient found
            }
            
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la recherche simple: " + ex.getMessage());
            ex.printStackTrace();
        }
        
        return false; // Default return if no results or error
    }

    // Method 2: Advanced search with Personne object - returns boolean
    public boolean rechercheAvancee(Personne personne) {
        // If all fields are empty, return false
        if (isPersonneEmpty(personne)) {
            return false;
        }
        
        String query = "SELECT COUNT(*) FROM Personne WHERE typePersonne = 'Patient' ";
        
        // Build WHERE clause dynamically based on non-empty fields
        StringBuilder whereClause = new StringBuilder();
        boolean hasCondition = false;
        
        if (personne.getNom() != null && !personne.getNom().trim().isEmpty()) {
            whereClause.append("nom LIKE ?");
            hasCondition = true;
        }
        
        if (personne.getPrenom() != null && !personne.getPrenom().trim().isEmpty()) {
            if (hasCondition) whereClause.append(" AND ");
            whereClause.append("prenom LIKE ?");
            hasCondition = true;
        }
        
        if (personne.getdateNaissance() != null && !personne.getdateNaissance().trim().isEmpty()) {
            if (hasCondition) whereClause.append(" AND ");
            whereClause.append("DATE(dateNaissance) = ?");
            hasCondition = true;
        }
        
        if (personne.getAge() > 0) {
            if (hasCondition) whereClause.append(" AND ");
            whereClause.append("age = ?");
            hasCondition = true;
        }
        
        if (personne.getSexe() != null && !personne.getSexe().trim().isEmpty()) {
            if (hasCondition) whereClause.append(" AND ");
            whereClause.append("sexe = ?");
            hasCondition = true;
        }
        
        if (personne.getGroupe_sanguin() != null && !personne.getGroupe_sanguin().trim().isEmpty()) {
            if (hasCondition) whereClause.append(" AND ");
            whereClause.append("groupeSanguin = ?");
            hasCondition = true;
        }
        
        if (personne.getAdresse() != null && !personne.getAdresse().trim().isEmpty()) {
            if (hasCondition) whereClause.append(" AND ");
            whereClause.append("adresse LIKE ?");
            hasCondition = true;
        }
        
        if (personne.getTel() != null && !personne.getTel().trim().isEmpty()) {
            if (hasCondition) whereClause.append(" AND ");
            whereClause.append("tel LIKE ?");
            hasCondition = true;
        }
        
        // Add WHERE clause if there are conditions
        if (hasCondition) {
            query += "AND (" + whereClause.toString() + ")";
        } else {
            return false; // No search criteria provided
        }
        
        try (
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(query)
        ) {
            int paramIndex = 1;
            
            // Set parameters in the same order as they appear in WHERE clause
            if (personne.getNom() != null && !personne.getNom().trim().isEmpty()) {
                pst.setString(paramIndex++, "%" + personne.getNom().trim() + "%");
            }
            
            if (personne.getPrenom() != null && !personne.getPrenom().trim().isEmpty()) {
                pst.setString(paramIndex++, "%" + personne.getPrenom().trim() + "%");
            }
            
            if (personne.getdateNaissance() != null && !personne.getdateNaissance().trim().isEmpty()) {
                try {
                    // Convert dd/MM/yyyy to yyyy-MM-dd for SQL
                    String dateStr = personne.getdateNaissance().trim();
                    SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
                    SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date utilDate = inputFormat.parse(dateStr);
                    pst.setString(paramIndex++, outputFormat.format(utilDate));
                } catch (Exception e) {
                    // If date format is invalid, skip this condition
                    System.out.println("Format de date invalide: " + personne.getdateNaissance());
                    // Remove this condition by not incrementing paramIndex
                }
            }
            
            if (personne.getAge() > 0) {
                pst.setInt(paramIndex++, personne.getAge());
            }
            
            if (personne.getSexe() != null && !personne.getSexe().trim().isEmpty()) {
                pst.setString(paramIndex++, personne.getSexe().trim());
            }
            
            if (personne.getGroupe_sanguin() != null && !personne.getGroupe_sanguin().trim().isEmpty()) {
                pst.setString(paramIndex++, personne.getGroupe_sanguin().trim());
            }
            
            if (personne.getAdresse() != null && !personne.getAdresse().trim().isEmpty()) {
                pst.setString(paramIndex++, "%" + personne.getAdresse().trim() + "%");
            }
            
            if (personne.getTel() != null && !personne.getTel().trim().isEmpty()) {
                pst.setString(paramIndex++, "%" + personne.getTel().trim() + "%");
            }
            
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0; // Returns true if at least one patient found
            }
            
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la recherche avancée: " + ex.getMessage());
            ex.printStackTrace();
        }
        
        return false; // Default return if no results or error
    }
    
    // Helper method to check if Personne object is empty (no search criteria)
    private boolean isPersonneEmpty(Personne personne) {
        return (personne.getNom() == null || personne.getNom().trim().isEmpty()) &&
               (personne.getPrenom() == null || personne.getPrenom().trim().isEmpty()) &&
               (personne.getdateNaissance() == null || personne.getdateNaissance().trim().isEmpty()) &&
               (personne.getAge() <= 0) &&
               (personne.getSexe() == null || personne.getSexe().trim().isEmpty()) &&
               (personne.getGroupe_sanguin() == null || personne.getGroupe_sanguin().trim().isEmpty()) &&
               (personne.getAdresse() == null || personne.getAdresse().trim().isEmpty()) &&
               (personne.getTel() == null || personne.getTel().trim().isEmpty());
    }

    // Optional: Method to get exact match (for duplication check)
    public boolean patientExactExiste(Personne personne) {
        String query = "SELECT COUNT(*) FROM Personne WHERE typePersonne = 'Patient' " +
                      "AND nom = ? AND prenom = ? AND DATE(dateNaissance) = ?";
        
        try (
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(query)
        ) {
            pst.setString(1, personne.getNom());
            pst.setString(2, personne.getPrenom());
            
            // Convert date
            String dateStr = personne.getdateNaissance();
            if (dateStr != null && !dateStr.trim().isEmpty()) {
                try {
                    SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
                    SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date utilDate = inputFormat.parse(dateStr.trim());
                    pst.setString(3, outputFormat.format(utilDate));
                } catch (Exception e) {
                    // If date invalid, use null
                    pst.setNull(3, java.sql.Types.DATE);
                }
            } else {
                pst.setNull(3, java.sql.Types.DATE);
            }
            
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la vérification d'existence: " + ex.getMessage());
        }
        
        return false;
    }

    // Optional: Method to search by ID only
    public boolean rechercheParId(int id) {
        String query = "SELECT COUNT(*) FROM Personne WHERE typePersonne = 'Patient' AND idPersonne = ?";
        
        try (
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(query)
        ) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la recherche par ID: " + ex.getMessage());
        }
        
        return false;
    }
}
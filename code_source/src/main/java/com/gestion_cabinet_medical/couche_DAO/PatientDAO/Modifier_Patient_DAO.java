package com.gestion_cabinet_medical.couche_DAO.PatientDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gestion_cabinet_medical.couche_metier.model.Personne;

public class Modifier_Patient_DAO {


    private final String url = "jdbc:mysql://localhost:3306/ClinicaPro";
    private final String user = "root";
    private final String password = "";


    public boolean modifierPatient(Personne personne) {
    // First, check if the patient exists
    String checkQuery = "SELECT COUNT(*) FROM Personne WHERE idPersonne = ? AND typePersonne = 'Patient'";
    String updateQuery = "UPDATE Personne SET nom = ?, prenom = ?, age = ?, sexe = ?, groupeSanguin = ?, adresse = ?, tel = ? WHERE idPersonne = ? AND typePersonne = 'Patient'";
    
    try (
        Connection con = DriverManager.getConnection(url, user, password);
        PreparedStatement checkStmt = con.prepareStatement(checkQuery);
        PreparedStatement updateStmt = con.prepareStatement(updateQuery)
    ) {
        // Check if the patient exists
        checkStmt.setInt(1, personne.getId_personne());
        ResultSet rs = checkStmt.executeQuery();
        
        if (rs.next() && rs.getInt(1) > 0) {
            // Patient exists, proceed with update
            updateStmt.setString(1, personne.getNom());
            updateStmt.setString(2, personne.getPrenom());
            updateStmt.setInt(3, personne.getAge());
            updateStmt.setString(4, personne.getSexe());
            updateStmt.setString(5, personne.getGroupe_sanguin());
            updateStmt.setString(6, personne.getAdresse());  // Note: Fixed from your ajoutPersonne method
            updateStmt.setString(7, personne.getTel());
            updateStmt.setInt(8, personne.getId_personne()); // WHERE clause parameter
            
            int rowsAffected = updateStmt.executeUpdate();
            
            rs.close();
            checkStmt.close();
            updateStmt.close();
            con.close();
            
            return rowsAffected > 0;
        } else {
            // Patient doesn't exist or is not a Patient type
            System.out.println("Patient avec ID " + personne.getId_personne() + " non trouvé.");
            rs.close();
            checkStmt.close();
            updateStmt.close();
            con.close();
            return false;
        }
        
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la modification: " + ex.getMessage());
        return false;
    }
}
    
}

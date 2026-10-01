package com.gestion_cabinet_medical.couche_DAO.PatientDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Supprimer_Patient_DAO {

    private final String url = "jdbc:mysql://localhost:3306/ClinicaPro";
    private final String user = "root";
    private final String password = "";

    public boolean supprimerPatient(int idPersonne) {
    // First, check if the person exists and is of type "Patient"
    String checkQuery = "SELECT COUNT(*) FROM Personne WHERE idPersonne = ? AND typePersonne = 'Patient'";
    String deleteQuery = "DELETE FROM Personne WHERE idPersonne = ? AND typePersonne = 'Patient'";
    
    try (
        Connection con = DriverManager.getConnection(url, user, password);
        // First, check if the record exists
        PreparedStatement checkStmt = con.prepareStatement(checkQuery);
        // Then, prepare the delete statement
        PreparedStatement deleteStmt = con.prepareStatement(deleteQuery)
    ) {
        // Check if the record exists
        checkStmt.setInt(1, idPersonne);
        ResultSet rs = checkStmt.executeQuery();
        
        if (rs.next() && rs.getInt(1) > 0) {
            // Record exists, proceed with deletion
            deleteStmt.setInt(1, idPersonne);
            int rowsAffected = deleteStmt.executeUpdate();
            
            rs.close();
            checkStmt.close();
            deleteStmt.close();
            con.close();
            
            return rowsAffected > 0;
        } else {
            // Record doesn't exist or is not a Patient
            rs.close();
            checkStmt.close();
            deleteStmt.close();
            con.close();
            return false;
        }
        
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la suppression: " + ex.getMessage());
        return false;
    }
}
    
}

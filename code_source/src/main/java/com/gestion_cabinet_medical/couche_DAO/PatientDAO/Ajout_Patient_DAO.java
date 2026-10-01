package com.gestion_cabinet_medical.couche_DAO.PatientDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import com.gestion_cabinet_medical.couche_metier.model.Personne;

public class Ajout_Patient_DAO {
    private final String url = "jdbc:mysql://localhost:3306/ClinicaPro";
    private final String user = "root";
    private final String password = "";
    
    public boolean ajoutPersonne(Personne personne){
        
        String query = "INSERT INTO Personne (idPersonne, typePersonne, nom, prenom, dateNaissance, age, sexe, groupeSanguin, adresse, tel) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pst = con.prepareStatement(query)
        ) {
            pst.setInt(1, personne.getId_personne());
            pst.setString(2, "Patient");
            pst.setString(3, personne.getNom());
            pst.setString(4, personne.getPrenom());
            
            // Convert dd/mm/yyyy to YYYY-MM-DD for MySQL DATE column
            if (personne.getdateNaissance() != null && !personne.getdateNaissance().trim().isEmpty()) {
                String dateStr = personne.getdateNaissance().trim();
                
                try {
                    // Parse dd/mm/yyyy format
                    SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
                    inputFormat.setLenient(false); // Strict parsing
                    java.util.Date utilDate = inputFormat.parse(dateStr);
                    
                    // Convert to java.sql.Date (which uses YYYY-MM-DD format internally)
                    Date sqlDate = new Date(utilDate.getTime());
                    pst.setDate(5, sqlDate);
                    
                } catch (Exception e) {
                    System.out.println("Format de date invalide: " + dateStr + 
                                     ". Attendu: dd/mm/yyyy. Erreur: " + e.getMessage());
                    pst.setNull(5, java.sql.Types.DATE);
                }
            } else {
                pst.setNull(5, java.sql.Types.DATE);
            }
            
            pst.setInt(6, personne.getAge());
            pst.setString(7, personne.getSexe());
            pst.setString(8, personne.getGroupe_sanguin());
            pst.setString(9, personne.getAdresse());
            pst.setString(10, personne.getTel());

            int rowsAffected = pst.executeUpdate();
            pst.close();
            con.close();

            return rowsAffected > 0;

        } catch (SQLException ex) {
            System.out.println("Erreur SQL: " + ex.getMessage());
            return false;
        }
    }
}
package com.gestion_cabinet_medical.couche_DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;


public class Couche_données {
    private final String url = "jdbc:mysql://localhost:3306/ClinicaPro";
    private final String user = "root";
    private final String password = "";

    public boolean testConnexion() {
        try (Connection con = DriverManager.getConnection(url, user, password)) {
            if (con != null) {
                System.out.println("Connexion réussie à la base de données.");
                return true;
            } else {
                System.out.println("Échec de la connexion à la base de données.");
                return false;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

   

    public boolean ajoutOrdonnance(int numOrdo, int idMedecin,int idPatient, String details, String datePresc){

        String query = "INSERT INTO Ordonnance (numOrdonnance, idMedecin, idPatient, details, datePresc) VALUES (?, ?, ?, ?, ?)";
        try (
            
            Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setInt(1, numOrdo);
            pst.setInt(2, idMedecin);
            pst.setInt(3, idPatient);
            pst.setString(4, details);
            pst.setString(5, datePresc);

            int rowsAffected = pst.executeUpdate();
            pst.close();
           con.close();

           if (rowsAffected>0) {
            return true;
           }

           else  { return  false;}

          

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }

    }

}

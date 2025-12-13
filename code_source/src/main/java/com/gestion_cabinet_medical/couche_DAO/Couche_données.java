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

    public boolean ajoutPersonne(int idPersonne, String typePersonne, String nom, String prenom, int age, String sexe, String groupeSanguin, String adresse, String tel){
        
        String query = "INSERT INTO Personne (idPersonne, typePersonne, nom, prenom, age, sexe, groupeSanguin, adresse, tel) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (
            
            Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setInt(1, idPersonne);
            pst.setString(2, typePersonne);
            pst.setString(3, nom);
            pst.setString(4, prenom);
            pst.setInt(5, age);
            pst.setString(6, sexe);
            pst.setString(7, groupeSanguin);
            pst.setString(8, adresse);
            pst.setString(9, tel);

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

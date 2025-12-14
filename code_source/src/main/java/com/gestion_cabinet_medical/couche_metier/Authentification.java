package com.gestion_cabinet_medical.couche_metier;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Authentification {
    
    private static String getRuntimePath(String relativePath) {
    try {
        // Directory where the JAR is running
        File jarDir = new File(Authentification.class
                .getProtectionDomain()
                .getCodeSource()
                .getLocation()
                .toURI())
                .getParentFile();

        return new File(jarDir, relativePath).getAbsolutePath();

    } catch (Exception e) {
        return relativePath; // fallback when running in IDE
    }
  }

  public static boolean S_authentifier(String nom, String prenom, String password ){
  
    File file = new File(getRuntimePath("pass.txt"));
    boolean nom_valid = false,prenom_valid = false,password_valid=false;
    String input = "";
    int count_users = 0;

    try {
        Scanner scan = new Scanner(file);

        while (scan.hasNextLine()) {
            input = scan.nextLine();

            if (input.equals("Médecin") || input.equals("Réceptioniste")) {
                continue;
            }

            if (input.equals(nom)) {
                nom_valid = true;
                continue;
            }

            if (input.equals(prenom)) {
                prenom_valid = true;
                continue;
            }

            if (input.equals(password)) {
                password_valid = true;
            }

            if (input.isEmpty() || input.isBlank()) {
                if (nom_valid && prenom_valid && password_valid) {
                    break;
                }
                
                nom_valid = prenom_valid = password_valid = false;
                continue;

            }


        }


    } catch (FileNotFoundException e) {
        e.printStackTrace();
        
    }


    return (nom_valid && prenom_valid && password_valid);
  }



}

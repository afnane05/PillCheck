package com.pillcheck.medicalapp.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDonneeConnexion {
    private static final String URL = "jdbc:mysql://localhost:3306/pillcheck_database?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // ou ton mot de passe si tu en as mis un

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    public static Connection seConnecter() {
        try {
            // Chargement explicite du driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Connexion à la base de données
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }    
}

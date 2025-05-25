
package com.pillcheck.medicalapp.Model;

import com.pillcheck.medicalapp.Model.User;
import com.pillcheck.medicalapp.Model.BaseDonneeConnexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignUpDAO {
    

    public boolean addUser(User user) {
        String query = "INSERT INTO Utilisateur (nom, email, motDePasse) VALUES (?, ?, ?)";
        
        try (Connection conn = BaseDonneeConnexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, user.getNom());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getMotDePasse());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.err.println("Error inserting user: " + e.getMessage());
            return false;
        }
    }
}

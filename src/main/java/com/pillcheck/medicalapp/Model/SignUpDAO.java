package com.pillcheck.medicalapp.Model;

import java.sql.*;

public class SignUpDAO {

    public User addUser(User user) {
        String query = "INSERT INTO Utilisateur (nom, email, motDePasse) VALUES (?, ?, ?)";

        try (Connection conn = BaseDonneeConnexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, user.getNom());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getMotDePasse());

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1)); // Set the generated ID
                    return user;
                }
            }

        } catch (SQLException e) {
            System.err.println("Error inserting user: " + e.getMessage());
        }

        return null; // Return null if insertion failed
    }
}

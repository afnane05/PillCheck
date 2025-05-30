package com.pillcheck.medicalapp.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

    public static User getUserByEmailAndPassword(String email, String password) {
        String sql = "SELECT * FROM utilisateur WHERE email = ? AND motDePasse = ?";
        
        try (Connection conn = BaseDonneeConnexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, email);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id")); 
                user.setNom(rs.getString("nom"));
                user.setEmail(rs.getString("email"));
                user.setMotDePasse(rs.getString("motDePasse"));
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // return null if user not found
    }
}

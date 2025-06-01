package com.pillcheck.medicalapp.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO{
    public static boolean deleteUser(int userId) {
        String deletePatients = "DELETE FROM patient WHERE user_id = ?";
        String deleteTreatments = "DELETE FROM traitement WHERE user_id = ?";
        String deleteUser = "DELETE FROM utilisateur WHERE id = ?";

        try (Connection conn = BaseDonneeConnexion.getConnection()) {
            conn.setAutoCommit(false); // Begin transaction

            try (
                PreparedStatement stmt1 = conn.prepareStatement(deletePatients);
                PreparedStatement stmt2 = conn.prepareStatement(deleteTreatments);
                PreparedStatement stmt3 = conn.prepareStatement(deleteUser)
            ) {
                stmt1.setInt(1, userId);
                stmt1.executeUpdate();

                stmt2.setInt(1, userId);
                stmt2.executeUpdate();

                stmt3.setInt(1, userId);
                int rowsDeleted = stmt3.executeUpdate();

                conn.commit(); // Commit transaction

                return rowsDeleted > 0;
            } catch (SQLException e) {
                conn.rollback(); // Roll back if error
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public static boolean updateUser(User user) {
        String sql = "UPDATE utilisateur SET nom = ?, email = ?, motDePasse = ? WHERE id = ?";
        try (Connection conn = BaseDonneeConnexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getNom());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getMotDePasse());
            stmt.setInt(4, user.getId());

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}

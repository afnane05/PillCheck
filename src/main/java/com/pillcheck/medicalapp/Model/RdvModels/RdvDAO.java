package com.pillcheck.medicalapp.Model.RdvModels;

import com.pillcheck.medicalapp.Model.BaseDonneeConnexion;
import com.pillcheck.medicalapp.Model.Session;
import com.pillcheck.medicalapp.Model.User;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RdvDAO {

    public boolean ajouterRdv(Rdv rdv) {
        String sql = "INSERT INTO rdv (DATE_RDV, MOTIF_RDV, STATUT, user_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = BaseDonneeConnexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            User currentUser = Session.getInstance().getCurrentUser();
            if (currentUser == null) {
                System.out.println("No user in session.");
                return false;
            }

            stmt.setTimestamp(1, Timestamp.valueOf(rdv.getDateRdv()));
            stmt.setString(2, rdv.getMotifRdv());
            stmt.setString(3, rdv.getStatut());
            stmt.setInt(4, currentUser.getId());

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Rdv> getAllRdv() {
        List<Rdv> Rdvs = new ArrayList<>();
        String sql = "SELECT * FROM rdv WHERE user_id = ?";

        try (Connection conn = BaseDonneeConnexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            User currentUser = Session.getInstance().getCurrentUser();
            if (currentUser == null) {
                System.out.println("No user in session.");
                return Rdvs;
            }

            stmt.setInt(1, currentUser.getId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Rdv rdv = new Rdv();
                rdv.setIdRdv(rs.getInt("id"));
                rdv.setDateRdv(rs.getTimestamp("DATE_RDV").toLocalDateTime());
                rdv.setMotifRdv(rs.getString("MOTIF_RDV"));
                rdv.setStatut(rs.getString("STATUT"));

                Rdvs.add(rdv);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Rdvs;
    }

    public static boolean supprimerRdv(int id) {
        String sql = "DELETE FROM rdv WHERE id = ? AND user_id = ?";

        try (Connection conn = BaseDonneeConnexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            User currentUser = Session.getInstance().getCurrentUser();
            if (currentUser == null) {
                System.out.println("No user in session.");
                return false;
            }

            stmt.setInt(1, id);
            stmt.setInt(2, currentUser.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean modifierRdv(Rdv rdv) {
        String sql = "UPDATE rdv SET DATE_RDV = ?, MOTIF_RDV = ?, STATUT = ? WHERE id = ? AND user_id = ?";

        try (Connection conn = BaseDonneeConnexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            User currentUser = Session.getInstance().getCurrentUser();
            if (currentUser == null) return false;

            stmt.setTimestamp(1, Timestamp.valueOf(rdv.getDateRdv()));
            stmt.setString(2, rdv.getMotifRdv());
            stmt.setString(3, rdv.getStatut());
            stmt.setInt(4, rdv.getIdRdv());
            stmt.setInt(5, currentUser.getId());  

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("SQL Error updating RDV: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public List<Rdv> getRdvsByDate(LocalDate date) {
    List<Rdv> rdvs = new ArrayList<>();
    String sql = "SELECT * FROM rdv WHERE DATE(DATE_RDV) = ? AND user_id = ?";

    try (Connection conn = BaseDonneeConnexion.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setDate(1, java.sql.Date.valueOf(date));
        stmt.setInt(2, Session.getInstance().getCurrentUser().getId());

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Rdv rdv = new Rdv();
            rdv.setIdRdv(rs.getInt("id"));
            rdv.setMotifRdv(rs.getString("MOTIF_RDV"));
            rdv.setStatut(rs.getString("STATUT"));
            rdv.setDateRdv(rs.getTimestamp("DATE_RDV").toLocalDateTime());
            rdvs.add(rdv);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return rdvs;
}

    
}

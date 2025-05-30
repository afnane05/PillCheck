package com.pillcheck.medicalapp.Model.PatientModels;

import com.pillcheck.medicalapp.Model.BaseDonneeConnexion;
import com.pillcheck.medicalapp.Model.Session;
import com.pillcheck.medicalapp.Model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {

    // Insert patient into the database
    public boolean ajouterPatient(Patient patient) {
        String sql = "INSERT INTO patient (cin, nom, prenom, date_naissance, telephone, etat, sexe, traitement, user_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = BaseDonneeConnexion.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            User currentUser = Session.getInstance().getCurrentUser();
            if (currentUser == null) {
                System.out.println("No user in session.");
                return false;
            }

            stmt.setString(1, patient.getCin());
            stmt.setString(2, patient.getNom());
            stmt.setString(3, patient.getPrenom());
            stmt.setDate(4, Date.valueOf(patient.getDateNaissance()));
            stmt.setString(5, patient.getTelephone());
            stmt.setString(6, patient.getEtat());
            stmt.setString(7, patient.getSexe());
            stmt.setString(8, patient.getNomTraitement());
            stmt.setInt(9, currentUser.getId()); 

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

  
    public static List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM patient WHERE user_id = ?";

        try (Connection conn = BaseDonneeConnexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            User currentUser = Session.getInstance().getCurrentUser();
            if (currentUser == null) {
                System.out.println("No user in session.");
                return patients;
            }

            stmt.setInt(1, currentUser.getId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Patient patient = new Patient();
                patient.setCin(rs.getString("cin"));
                patient.setNom(rs.getString("nom"));
                patient.setPrenom(rs.getString("prenom"));
                patient.setDateNaissance(rs.getDate("date_naissance").toLocalDate());
                patient.setTelephone(rs.getString("telephone"));
                patient.setEtat(rs.getString("etat"));
                patient.setSexe(rs.getString("sexe"));
                patient.setNomTraitement(rs.getString("traitement"));
                patient.setId(rs.getInt("id")); 

                patients.add(patient);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patients;
    }

  
    public static boolean supprimerPatient(int id) {
        String sql = "DELETE FROM patient WHERE id = ? AND user_id = ?";

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


    public boolean modifierPatient(Patient patient) {
        String sql = "UPDATE patient SET cin = ?, nom = ?, prenom = ?, date_naissance = ?, telephone = ?, etat = ?, sexe = ?, traitement = ? WHERE id = ? AND user_id = ?";

        try (Connection conn = BaseDonneeConnexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            User currentUser = Session.getInstance().getCurrentUser();
            if (currentUser == null) {
                System.out.println("No user in session.");
                return false;
            }

            stmt.setString(1, patient.getCin());
            stmt.setString(2, patient.getNom());
            stmt.setString(3, patient.getPrenom());
            stmt.setDate(4, Date.valueOf(patient.getDateNaissance()));
            stmt.setString(5, patient.getTelephone());
            stmt.setString(6, patient.getEtat());
            stmt.setString(7, patient.getSexe());
            stmt.setString(8, patient.getNomTraitement());
            stmt.setInt(9, patient.getId());              
            stmt.setInt(10, currentUser.getId());         

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}

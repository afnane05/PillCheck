package com.pillcheck.medicalapp.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {

    // Insert patient into the database
    public boolean ajouterPatient(Patient patient) {
        String sql = "INSERT INTO patient (cin, nom, prenom, date_naissance, telephone, etat, sexe, traitement) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = BaseDonneeConnexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, patient.getCin());
            stmt.setString(2, patient.getNom());
            stmt.setString(3, patient.getPrenom());
            stmt.setDate(4, Date.valueOf(patient.getDateNaissance()));
            stmt.setString(5, patient.getTelephone());
            stmt.setString(6, patient.getEtat());
            stmt.setString(7, patient.getSexe());
            stmt.setString(8, patient.getNomTraitement());

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Fetch all patients from the database
    public static List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM patient";

        try (Connection conn = BaseDonneeConnexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

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

                patients.add(patient);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patients;
    }

    // Delete a patient by CIN
    public static boolean supprimerPatient(String cin) {
        String sql = "DELETE FROM patient WHERE cin = ?";

        try (Connection conn = BaseDonneeConnexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cin);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update an existing patient
    public boolean modifierPatient(Patient patient) {
        String sql = "UPDATE patient SET nom = ?, prenom = ?, date_naissance = ?, telephone = ?, etat = ?, sexe = ?, traitement = ? WHERE cin = ?";

        try (Connection conn = BaseDonneeConnexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, patient.getNom());
            stmt.setString(2, patient.getPrenom());
            stmt.setDate(3, Date.valueOf(patient.getDateNaissance()));
            stmt.setString(4, patient.getTelephone());
            stmt.setString(5, patient.getEtat());
            stmt.setString(6, patient.getSexe());
            stmt.setString(7, patient.getNomTraitement());
            stmt.setString(8, patient.getCin());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

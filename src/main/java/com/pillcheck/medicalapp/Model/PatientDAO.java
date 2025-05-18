package com.pillcheck.medicalapp.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PatientDAO {

    public boolean ajouterPatient(Patient patient) {
        String sql = "INSERT INTO patient (cin, nom, prenom, date_naissance, telephone, etat, sexe, traitement) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = BaseDonneeConnexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, patient.getCin());
            stmt.setString(2, patient.getNom());
            stmt.setString(3, patient.getPrenom());
            stmt.setDate(4, java.sql.Date.valueOf(patient.getDateNaissance()));
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
}


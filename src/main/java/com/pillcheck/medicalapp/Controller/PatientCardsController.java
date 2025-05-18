package com.pillcheck.medicalapp.Controller;

import com.pillcheck.medicalapp.Model.Patient;
import java.time.format.DateTimeFormatter;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class PatientCardsController {

    @FXML private TextField cinLabel;
    @FXML private TextField nomLabel;
    @FXML private TextField prenomLabel;
    @FXML private TextField telephoneLabel;
    @FXML private TextField etatLabel;
    @FXML private TextField sexeLabel;
    @FXML private TextField traitementLabel;
    @FXML private TextField dateNaissanceLabel;

    public void setPatientInfo(Patient patient) {
        cinLabel.setText(patient.getCin());
        nomLabel.setText(patient.getNom());
        prenomLabel.setText(patient.getPrenom());
        telephoneLabel.setText(patient.getTelephone());
        etatLabel.setText(patient.getEtat());
        sexeLabel.setText(patient.getSexe());
        traitementLabel.setText(patient.getNomTraitement());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        dateNaissanceLabel.setText(patient.getDateNaissance().format(formatter));


    }
}

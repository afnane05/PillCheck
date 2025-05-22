package com.pillcheck.medicalapp.Controller;

import com.pillcheck.medicalapp.Model.PatientDAO;
import com.pillcheck.medicalapp.Model.Patient;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import java.time.LocalDate;

public class AddPatientFormController {

    @FXML private TextField cinField;
    @FXML private TextField nomField;
    @FXML private TextField prenomField;
    @FXML private TextField telephoneField;
    @FXML private TextField etatField;
    @FXML private RadioButton femmeRadio;
    @FXML private RadioButton hommeRadio;
    @FXML private ToggleGroup sexeGroup;
    @FXML private ChoiceBox<String> traitementChoiceBox;
    @FXML private DatePicker dateNaissancePicker;
    @FXML private Button ajouterButton ;
    private PatientController parentController;
    private Patient patient ;

    public void setParentController(PatientController controller) {
        this.parentController = controller;
    }

    @FXML
    private void initialize() {
        // Lier les radios au groupe
        sexeGroup = new ToggleGroup();
        femmeRadio.setToggleGroup(sexeGroup);
        hommeRadio.setToggleGroup(sexeGroup);
        traitementChoiceBox.getItems().addAll("Renomicine", "Doliprane", "Spasfon");
    }

    
    @FXML
    private void handleAddPatient(ActionEvent event) {
        // Récupération des champs
        String cin = cinField.getText();
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        LocalDate dateNaissance = dateNaissancePicker.getValue();
        String telephone = telephoneField.getText();
        String etat = etatField.getText();
        String traitement = traitementChoiceBox.getValue();

        String sexe = null;
        if (sexeGroup.getSelectedToggle() != null) {
            RadioButton selectedRadio = (RadioButton) sexeGroup.getSelectedToggle();
            sexe = selectedRadio.getText();
        }

        // Validation
        if (cin.isEmpty() || nom.isEmpty() || prenom.isEmpty() || dateNaissance == null ||
            telephone.isEmpty() || etat.isEmpty() || sexe == null || traitement == null) {
            showAlert("Champs manquants", "Veuillez remplir tous les champs.");
            return;
        }

        // Création de l'objet Patient
        Patient updatedPatient = new Patient(cin, nom, prenom, dateNaissance, telephone, etat, sexe, traitement);
        PatientDAO patientDAO = new PatientDAO();

        boolean success;

        if (this.patient != null) {
            // ===> C’est une modification
            success = patientDAO.modifierPatient(updatedPatient);
        } else {
            // ===> C’est un ajout
            success = patientDAO.ajouterPatient(updatedPatient);
        }

        if (success) {
            parentController.handleRefresh(); // recharge la liste
            closeWindow(event);
        } else {
            showAlert("Erreur", "Une erreur est survenue lors de l'enregistrement du patient.");
        }
    }


    private void closeWindow(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    private void showAlert(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public void prefillForm(Patient patient) {
        this.patient = patient; // Store the reference if needed later (e.g. for update logic)

        cinField.setText(patient.getCin());
        cinField.setDisable(true); // Primary key, should not be changed

        nomField.setText(patient.getNom());
        prenomField.setText(patient.getPrenom());
        dateNaissancePicker.setValue(patient.getDateNaissance());
        telephoneField.setText(patient.getTelephone());
        etatField.setText(patient.getEtat());
        if ("Homme".equalsIgnoreCase(patient.getSexe())) {
            sexeGroup.selectToggle(hommeRadio);
        } else if ("Femme".equalsIgnoreCase(patient.getSexe())) {
            sexeGroup.selectToggle(femmeRadio);
        }
        traitementChoiceBox.setValue(patient.getNomTraitement());

        // Optional: Change the submit button label from "Ajouter" to "Modifier"
        ajouterButton.setText("Modifier");
    }

}

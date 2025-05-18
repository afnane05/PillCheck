package com.pillcheck.medicalapp.Controller;

import com.pillcheck.medicalapp.Model.Patient;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class PatientCardIconController {

    @FXML private TextField nomField;
    @FXML private TextField prenomField;
    @FXML private Pane cardContainer;
    @FXML private Button addPatientButton ;  
    private Patient patient;
    private PatientController parentController;
    private Node rootNode;  // Add this field

    public void setPatientInfo(Patient patient, Node rootNode) {
        this.patient = patient;
        this.rootNode = rootNode;
        nomField.setText(patient.getNom());
        prenomField.setText(patient.getPrenom());
    }



    public void setParentController(PatientController parentController) {
        this.parentController = parentController;
    }

    // Let the parent set the root node reference (needed to replace it later)
    public void setRootNode(Node root) {
        this.rootNode = root;
    }

    @FXML
    private void initialize() {
        cardContainer.setOnMouseClicked(this::handleCardClick);

    }

    @FXML
    private void handleCardClick(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/PatientCards.fxml"));
            Scene scene = new Scene(loader.load());

            // Get the controller and set patient data
            PatientCardsController controller = loader.getController();
            controller.setPatientInfo(patient);

            // Create and show a new stage
            Stage stage = new Stage();
            stage.setTitle("Détails du Patient");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL); // Block interaction with other windows
            stage.showAndWait(); // Wait until this window is closed

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    

}

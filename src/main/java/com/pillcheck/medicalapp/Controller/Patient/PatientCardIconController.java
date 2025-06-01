package com.pillcheck.medicalapp.Controller.Patient;

import com.pillcheck.medicalapp.Controller.Patient.AddPatientFormController;
import com.pillcheck.medicalapp.Model.PatientModels.Patient;
import com.pillcheck.medicalapp.Model.PatientModels.PatientDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class PatientCardIconController {

    @FXML private TextField nomField;
    @FXML private TextField prenomField;
    @FXML private Pane cardContainer;
    @FXML private Button addPatientButton ;  
    @FXML private MenuItem deletePatient;
    @FXML private MenuItem modifyItem;
    @FXML private MenuButton optionsMenuButton;

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
    
    @FXML
    private void handleDelete() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText(null);
        alert.setContentText("Voulez-vous vraiment supprimer ce patient ?");
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            boolean success = PatientDAO.supprimerPatient(patient.getId());
            
        }
    }


    @FXML
    private void handleModify() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/AddPatientForm.fxml"));
            Scene scene = new Scene(loader.load());

            AddPatientFormController controller = loader.getController();
            // Passer le patient complet avec son ID
            controller.prefillForm(patient); 
            controller.setParentController(parentController); 

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Modifier Patient");
            stage.setScene(scene);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}

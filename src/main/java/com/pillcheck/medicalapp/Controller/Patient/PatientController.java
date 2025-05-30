package com.pillcheck.medicalapp.Controller.Patient;

import com.pillcheck.medicalapp.Controller.Patient.PatientCardIconController;
import com.pillcheck.medicalapp.Controller.Patient.AddPatientFormController;
import com.pillcheck.medicalapp.Model.PatientModels.Patient;
import com.pillcheck.medicalapp.Model.PatientModels.PatientDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.Parent;

public class PatientController implements Initializable {

    @FXML
    private FlowPane patientCardsContainer;

    @FXML
    private TextField searchBar;

    @FXML
    private Button addPatientButton;

    @FXML
    private Button acceuilButton;

    @FXML
    private Button mesPatientButton;

    @FXML
    private Button mesTraitementButton;

    @FXML
    private Button mesRdvButton;

    @FXML
    private Button statistiqueButton;

    @FXML
    private Button compteButton;

    @FXML
    private Button parametreButton;

    @FXML
    private Button refreshButton;

    @FXML
    public void handleAddPatientButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/AddPatientForm.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Ajouter un Patient");
            stage.setScene(scene);

            AddPatientFormController controller = loader.getController();
            controller.setParentController(this);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ajouterCartePatient(Patient patient) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/PatientCardIcon.fxml"));
            Node card = loader.load();

            PatientCardIconController controller = loader.getController();
            controller.setPatientInfo(patient, card);
            controller.setParentController(this);

            patientCardsContainer.getChildren().add(0, card);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FlowPane getPatientCardsContainer() {
        return patientCardsContainer;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadPatientsFromDatabase();

        // Icons
        FontIcon icon1 = new FontIcon(FontAwesomeSolid.USER_PLUS);
        icon1.setIconSize(20);
        addPatientButton.setGraphic(icon1);
        addPatientButton.setText("");

        FontIcon icon2 = new FontIcon(FontAwesomeSolid.HOME);
        icon2.setIconSize(20);
        acceuilButton.setGraphic(icon2);
        acceuilButton.setText("");

        FontIcon icon3 = new FontIcon(FontAwesomeSolid.HOSPITAL_USER);
        icon3.setIconSize(20);
        mesPatientButton.setGraphic(icon3);
        mesPatientButton.setText("");

        FontIcon icon4 = new FontIcon(FontAwesomeSolid.STETHOSCOPE);
        icon4.setIconSize(20);
        mesTraitementButton.setGraphic(icon4);
        mesTraitementButton.setText("");

        FontIcon icon5 = new FontIcon(FontAwesomeSolid.CALENDAR_CHECK);
        icon5.setIconSize(20);
        mesRdvButton.setGraphic(icon5);
        mesRdvButton.setText("");

        FontIcon icon6 = new FontIcon(FontAwesomeSolid.CHART_BAR);
        icon6.setIconSize(20);
        statistiqueButton.setGraphic(icon6);
        statistiqueButton.setText("");

        FontIcon icon7 = new FontIcon(FontAwesomeSolid.USER_CIRCLE);
        icon7.setIconSize(20);
        compteButton.setGraphic(icon7);
        compteButton.setText("");

        FontIcon icon8 = new FontIcon(FontAwesomeSolid.COG);
        icon8.setIconSize(20);
        parametreButton.setGraphic(icon8);
        parametreButton.setText("");

        FontIcon icon9 = new FontIcon(FontAwesomeSolid.SYNC_ALT);
        icon9.setIconSize(20);
        refreshButton.setGraphic(icon9);
        refreshButton.setText("");
    }

    private void loadPatientsFromDatabase() {
        patientCardsContainer.getChildren().clear();
        List<Patient> patients = PatientDAO.getAllPatients();
        
        for (Patient patient : patients) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/PatientCardIcon.fxml"));
                Pane card = loader.load();
                PatientCardIconController cardController = loader.getController();
                cardController.setPatientInfo(patient, card);
                cardController.setParentController(this); // Important: définir le parent controller
                patientCardsContainer.getChildren().add(card);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void handleRefresh() {
        loadPatientsFromDatabase();
    }
    
    @FXML
    void handleAcceuil(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/View/HomePage.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    void handleCompte(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/View/CompteView.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    void handleTraitement(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/View/TraitView.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    void handleParametre(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/View/parametreView.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
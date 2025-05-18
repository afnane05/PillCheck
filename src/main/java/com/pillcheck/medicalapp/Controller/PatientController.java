package com.pillcheck.medicalapp.Controller;

import com.pillcheck.medicalapp.Model.Patient;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
        //  addPatientButton
        FontIcon icon1 = new FontIcon("fas-user-plus");
        icon1.setIconSize(20); // Adjust as needed

        addPatientButton.setGraphic(icon1);
        addPatientButton.setText(""); // hide the text
        
        //  acceuilButtton
        FontIcon icon2 = new FontIcon("fas-home");
        icon2.setIconSize(20); // Adjust as needed

        acceuilButton.setGraphic(icon2);
        acceuilButton.setText(""); // hide the text
        
        //  mesPatientButtton
        FontIcon icon3 = new FontIcon("fas-hospital-user");
        icon3.setIconSize(20); // Adjust as needed

        mesPatientButton.setGraphic(icon3);
        mesPatientButton.setText(""); // hide the text
        //  mesTraitementButton
        FontIcon icon4 = new FontIcon("fas-stethoscope");
        icon4.setIconSize(20); // Adjust as needed

        mesTraitementButton.setGraphic(icon4);
        mesTraitementButton.setText(""); // hide the text
        //  mesRdvButton
        FontIcon icon5 = new FontIcon("fas-calendar-check");
        icon5.setIconSize(20); // Adjust as needed

        mesRdvButton.setGraphic(icon5);
        mesRdvButton.setText(""); // hide the text
        //  statistiqueButton
        FontIcon icon6 = new FontIcon("fas-chart-bar");
        icon6.setIconSize(20); // Adjust as needed

        statistiqueButton.setGraphic(icon6);
        statistiqueButton.setText(""); // hide the text
        //  compteButton
        FontIcon icon7 = new FontIcon("fas-user-circle");
        icon7.setIconSize(20); // Adjust as needed

        compteButton.setGraphic(icon7);
        compteButton.setText(""); // hide the text
        //  parametreButton
        FontIcon icon8 = new FontIcon("fas-cog");
        icon8.setIconSize(20); // Adjust as needed

        parametreButton.setGraphic(icon8);
        parametreButton.setText(""); // hide the text
    }
}

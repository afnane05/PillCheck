package com.pillcheck.medicalapp.Controller.Parametre;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.javafx.FontIcon;

public class ParametreController implements Initializable {


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
    private Button helpButton;

    @FXML
    private Button mainButton;
    @FXML
    private void handleAddTraitementButton() {
    // Code à exécuter lorsque le bouton "Ajouter traitement" est cliqué
    System.out.println("Ajout traitement cliqué");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
    }

    @FXML
    private void openHelp() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/HelpView.fxml"));
           // FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/HelpView.fxml"));
            Parent root = loader.load();
            
            Stage helpStage = new Stage();
            helpStage.setTitle("Aide - PillCheck");
            helpStage.setScene(new Scene(root, 600, 500));
            helpStage.initModality(Modality.APPLICATION_MODAL);
            helpStage.setResizable(false);
            helpStage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleMainButton() {
        // Action pour le bouton principal
        System.out.println("Bouton principal cliqué");
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
    @FXML
    void handlePatient(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/View/PatientView.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


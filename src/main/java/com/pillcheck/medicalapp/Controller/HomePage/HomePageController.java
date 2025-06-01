package com.pillcheck.medicalapp.Controller.HomePage;

import com.pillcheck.medicalapp.Model.Session;
import com.pillcheck.medicalapp.Model.User;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;
import javafx.scene.control.ContentDisplay;

public class HomePageController implements javafx.fxml.Initializable {

    @FXML
    private Button mesPatientsButton;

    @FXML
    private Button mesTraitementsButton;

    @FXML
    private Button mesRdvButton;

    @FXML
    private Button statistiqueButton;

    @FXML
    private Button compteButton;

    @FXML
    private Button parametreButton;

    @FXML
    private TextField nameField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // 🟢 Retrieve user from session
        User currentUser = Session.getInstance().getCurrentUser();

        if (currentUser != null) {
            nameField.setText(currentUser.getNom());
        } else {
            nameField.setText("Utilisateur inconnu");
            System.out.println("⚠️ Aucun utilisateur en session !");
        }

        // Icons
        FontIcon icon3 = new FontIcon(FontAwesomeSolid.HOSPITAL_USER);
        icon3.setIconSize(40);

        mesPatientsButton.setGraphic(icon3);
        mesPatientsButton.setText("Mes Patients");
        mesPatientsButton.setContentDisplay(ContentDisplay.TOP);
       


        FontIcon icon4 = new FontIcon(FontAwesomeSolid.STETHOSCOPE);
        icon4.setIconSize(40);
        mesTraitementsButton.setGraphic(icon4);
        mesTraitementsButton.setText("Mes Traitements");
        mesTraitementsButton.setContentDisplay(ContentDisplay.TOP);

        
        FontIcon icon5 = new FontIcon(FontAwesomeSolid.CALENDAR_CHECK);
        icon5.setIconSize(40);
        mesRdvButton.setGraphic(icon5);
        mesRdvButton.setText("Mes Rendez-vous");
        mesRdvButton.setContentDisplay(ContentDisplay.TOP);

        FontIcon icon6 = new FontIcon(FontAwesomeSolid.CHART_BAR);
        icon6.setIconSize(40);
        statistiqueButton.setGraphic(icon6);
        statistiqueButton.setText("Statistique");
        statistiqueButton.setContentDisplay(ContentDisplay.TOP);

        FontIcon icon7 = new FontIcon(FontAwesomeSolid.USER_CIRCLE);
        icon7.setIconSize(40);
        compteButton.setGraphic(icon7);
        compteButton.setText("Compte");
        compteButton.setContentDisplay(ContentDisplay.TOP);

        FontIcon icon8 = new FontIcon(FontAwesomeSolid.COG);
        icon8.setIconSize(40);
        parametreButton.setGraphic(icon8);
        parametreButton.setText("Paramètres");
        parametreButton.setContentDisplay(ContentDisplay.TOP);
    }

    @FXML
    void handleMesPatients(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/View/PatientView.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void handleMesTraitements(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/View/TraitView.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
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
        } catch (IOException e) {
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
    void handleRdv(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/View/RdvView.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void handleStatistics(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/View/StatisticsView.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setUserName(String userName) {
        nameField.setText(userName);
    }
}

package com.pillcheck.medicalapp.Controller.HomePage;

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

public class HomePageController{


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
    private TextField nameField;
    
    public void initialize(URL location, ResourceBundle resources) {
        

        // Icons

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
    void handleMesPatients(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/View/PatientView.fxml"));
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
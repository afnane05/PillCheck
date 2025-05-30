package com.pillcheck.medicalapp.Controller.Compte;

import com.pillcheck.medicalapp.Model.Session;
import com.pillcheck.medicalapp.Model.User;
import com.pillcheck.medicalapp.Model.UserDAO;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.javafx.FontIcon;

public class CompteController implements Initializable {
    @FXML 
    private Button modifyCompte ; 
    @FXML 
    private Button supprimerCompte ; 
    @FXML 
    private Button LogOut ; 

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
    private TextField accountNameField;

    @Override
    public void initialize(URL location , ResourceBundle resources){
        User currentUser = Session.getInstance().getCurrentUser();
        
        if (currentUser != null) {
            accountNameField.setText(currentUser.getNom());
        } else {
            accountNameField.setText("Utilisateur inconnu");
            System.out.println("⚠️ Aucun utilisateur en session !");
        }
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
        
        FontIcon icon9 = new FontIcon(FontAwesomeSolid.USER_EDIT);
        icon9.setIconSize(40);
        modifyCompte.setGraphic(icon9);
        modifyCompte.setText("modifier utilisateur"); // or whatever label you want
        modifyCompte.setContentDisplay(ContentDisplay.TOP);
        
        FontIcon icon10 = new FontIcon(FontAwesomeSolid.USER_SLASH);
        icon10.setIconSize(40);
        supprimerCompte.setGraphic(icon10);
        supprimerCompte.setText("supprimmer Utilisateur"); // or your preferred label
        supprimerCompte.setContentDisplay(ContentDisplay.TOP);

        FontIcon icon11 = new FontIcon(FontAwesomeSolid.SIGN_OUT_ALT);
        icon11.setIconSize(40);
        LogOut.setGraphic(icon11);
        LogOut.setText("Déconnexion"); // French for "Logout"
        LogOut.setContentDisplay(ContentDisplay.TOP);

    }

    public void setUserName(String userName) {
        accountNameField.setText(userName);
    }
    
    @FXML
    void handleModifyAccount(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/View/modifyAccount.fxml"));
            Stage newStage = new Stage();
            newStage.setTitle("Modify Account");
            newStage.setScene(new Scene(root));
            newStage.initModality(Modality.WINDOW_MODAL);
            newStage.initOwner(((Node)event.getSource()).getScene().getWindow()); // parent window
            newStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void handleDeleteAccount(ActionEvent event) {
        User currentUser = Session.getInstance().getCurrentUser();

        if (currentUser != null) {
            int userId = currentUser.getId();
            boolean success = UserDAO.deleteUser(userId);

            if (success) {
                Session.getInstance().clear();
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/LoginUser.fxml"));
                    Parent root = loader.load();

                    // Redirect to LoginUser.fxml by replacing the current scene
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException e) {
                    e.printStackTrace();
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Could not load the login screen.");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Account deletion failed.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Aucun utilisateur en session.");
            alert.showAndWait();
        }
    }
    @FXML
    public void handleSignOut(ActionEvent event) {
        try {
   
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/LoginUser.fxml"));

        
            Pane root = new Pane(); 
            loader.setRoot(root);

            loader.load();

            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            System.out.println("Error loading LoginUser.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    
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
}





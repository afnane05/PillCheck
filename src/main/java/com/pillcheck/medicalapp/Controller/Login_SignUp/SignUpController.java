package com.pillcheck.medicalapp.Controller.Login_SignUp;

import com.pillcheck.medicalapp.Controller.HomePage.HomePageController;
import com.pillcheck.medicalapp.Model.Session;
import com.pillcheck.medicalapp.Model.User;
import com.pillcheck.medicalapp.Model.SignUpDAO;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Alert;

public class SignUpController {

    @FXML
    private TextField nomField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField;

    @FXML
    private void handleCreateAccount(ActionEvent event) throws IOException {
        String nom = nomField.getText();
        String email = emailField.getText();
        String motDePasse = passwordField.getText();

        if (!isValidEmail(email)) {
            showAlert("Format email invalide", "L'email doit contenir '@' et '.'");
            return;
        }

        User newUser = new User(nom, email, motDePasse);
        SignUpDAO dao = new SignUpDAO();

        User success = dao.addUser(newUser);

        if (success != null) {
            System.out.println("User registered successfully!");

            Session.getInstance().setCurrentUser(success);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/HomePage.fxml"));
            Parent root = loader.load();
            HomePageController homeController = loader.getController();
            homeController.setUserName(success.getNom());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            showAlert("Erreur", "L'inscription a échoué.");
        }
    }

    @FXML
    void handleAnnuler(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/LoginUser.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            System.out.println("Error loading the Login Page: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private boolean isValidEmail(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
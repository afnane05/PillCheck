package com.pillcheck.medicalapp.Controller.Login_SignUp;

import com.pillcheck.medicalapp.Controller.HomePage.HomePageController;
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
import javafx.stage.Stage;



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

        // Create the user object
        User newUser = new User(nom, email, motDePasse);
        SignUpDAO dao = new SignUpDAO();

        // Attempt to insert user into the database
        boolean success = dao.addUser(newUser);

        if (success) {
            System.out.println("User registered successfully!");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/HomePage.fxml"));
            Parent root = loader.load();
            HomePageController homeController = loader.getController();
            homeController.setUserName(newUser.getNom());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            System.out.println("Registration failed.");
            // TODO: Show an error alert here
        }
    }

}


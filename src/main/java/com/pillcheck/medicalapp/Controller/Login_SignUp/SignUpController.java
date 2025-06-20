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
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
<<<<<<< HEAD
=======
import javafx.scene.control.Alert;
>>>>>>> 7fec08144c9572542af357eae6250e70760e4f44

public class SignUpController {

    @FXML
    private TextField nomField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField;

    @FXML
    private void handleCreateAccount(ActionEvent event) throws IOException {
         // Vérification que tous les champs sont remplis
        if (nomField.getText().isEmpty() || emailField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            showAlert("Champs manquants", "Tous les champs doivent être remplis !");
            return;
        }
        String nom = nomField.getText();
        String email = emailField.getText();
        String motDePasse = passwordField.getText();

<<<<<<< HEAD
        
=======
>>>>>>> 7fec08144c9572542af357eae6250e70760e4f44
        if (!isValidEmail(email)) {
            showAlert("Format email invalide", "L'email doit contenir '@' et '.'");
            return;
        }
<<<<<<< HEAD
=======
         // Validation du mot de passe
        /*if (!isValidPassword(motDePasse)) {
            showAlert("Mot de passe invalide", "Le mot de passe doit contenir: au mions 8 caracteres\n"
                    + "- y compris au moins un caractere special(@$!%*?&) et un chiffre(0-9)\n");
                   // + "- Au moins 1 majuscule\n"
                    //+ "- Au moins 1 minuscule\n"
                    //+ "- Au moins 1 chiffre\n"
                    //+ "- Au moins 1 caractère spécial (@$!%*?&)");
            return;
        }
*/
        
>>>>>>> 7fec08144c9572542af357eae6250e70760e4f44

        User newUser = new User(nom, email, motDePasse);
        SignUpDAO dao = new SignUpDAO();

<<<<<<< HEAD

        User success = dao.addUser(newUser);

=======
        User success = dao.addUser(newUser);

>>>>>>> 7fec08144c9572542af357eae6250e70760e4f44
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
<<<<<<< HEAD
            System.out.println("Registration failed.");
=======
            showAlert("Erreur", "L'inscription a échoué.");
>>>>>>> 7fec08144c9572542af357eae6250e70760e4f44
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
            System.out.println("Error loading LoginUser.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }
    private boolean isValidEmail(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }

<<<<<<< HEAD
=======
    private boolean isValidEmail(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }
    /* private boolean isValidPassword(String password) {
        // Au moins 8 caractères, 1 majuscule, 1 minuscule, 1 chiffre et 1 caractère spécial
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return password != null && password.matches(passwordRegex);
    }
*/

>>>>>>> 7fec08144c9572542af357eae6250e70760e4f44
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 7fec08144c9572542af357eae6250e70760e4f44

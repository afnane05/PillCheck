package com.pillcheck.medicalapp.Controller.Login_SignUp;

import com.pillcheck.medicalapp.Controller.HomePage.HomePageController;
import com.pillcheck.medicalapp.Model.LoginDAO;
import com.pillcheck.medicalapp.Model.Session;
import com.pillcheck.medicalapp.Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button createAccountButton;

    @FXML
    void handleLogin(ActionEvent event) {
        String email = emailField.getText();
        String password = passwordField.getText();
     
        if (!isValidEmail(email)) {
            showAlert("Format email invalide", "L'email doit contenir '@' et '.'");
            return;
        }
        User user = LoginDAO.getUserByEmailAndPassword(email, password);
        if (user != null) {
            Session.getInstance().setCurrentUser(user);

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/HomePage.fxml"));
                Parent root = loader.load();

                HomePageController homeController = loader.getController();
                homeController.setUserName(user.getNom());

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            
            System.out.println("Email ou mot de passe invalide !");

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de connexion");
            alert.setHeaderText(null);
            alert.setContentText("Email ou mot de passe invalide !");
            alert.showAndWait();
        }
    }

    @FXML
    void handleCreateAccount(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/View/SignUpUser.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private boolean isValidEmail(String email) {
        return email != null && email.contains("@") && email.contains(".")  ;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}

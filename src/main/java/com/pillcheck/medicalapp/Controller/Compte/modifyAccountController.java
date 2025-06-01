package com.pillcheck.medicalapp.Controller.Compte;

import com.pillcheck.medicalapp.Model.Session;
import com.pillcheck.medicalapp.Model.User;
import com.pillcheck.medicalapp.Model.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class modifyAccountController {

    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private TextField passwordField;

    @FXML
    public void initialize() {
        User user = Session.getInstance().getCurrentUser();
        if (user != null) {
            nameField.setText(user.getNom());
            emailField.setText(user.getEmail());
        }
    }

    @FXML
    void handleSave(ActionEvent event) {
        User currentUser = Session.getInstance().getCurrentUser();
        if (currentUser != null) {
            currentUser.setNom(nameField.getText());
            currentUser.setEmail(emailField.getText());
            currentUser.setMotDePasse(passwordField.getText());

            boolean updated = UserDAO.updateUser(currentUser);

            if (updated) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Compte mis à jour avec succès !");
                alert.showAndWait();
                ((Stage) nameField.getScene().getWindow()).close(); // Close the modal
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Échec de la mise à jour du compte.");
                alert.showAndWait();
            }
        }
    }
}

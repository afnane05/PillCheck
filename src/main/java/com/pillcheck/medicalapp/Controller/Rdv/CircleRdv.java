package com.pillcheck.medicalapp.Controller.Rdv;

import com.pillcheck.medicalapp.Model.RdvModels.Rdv;
import com.pillcheck.medicalapp.Model.RdvModels.RdvDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class CircleRdv {

    @FXML private Label nomLabel;
    @FXML private Label statutLabel;

    private Rdv rdv;
    private Node rootNode;
    private DailyRdvController parentController;
    private LocalDate selectedDate;

    public void setRdvData(Rdv data) {
        this.rdv = data;
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        nomLabel.setText(data.getDateRdv().format(timeFormatter));
        statutLabel.setText(data.getStatut());
    }

    public void setRootNode(Node root) {
        this.rootNode = root;
    }

    public void setParentController(DailyRdvController controller) {
        this.parentController = controller;
    }

    public void setSelectedDate(LocalDate date) {
        this.selectedDate = date;
    }

    @FXML
    private void handleCardClick(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/RdvDetails.fxml"));
            Scene scene = new Scene(loader.load());

            RdvDetailsController controller = loader.getController();
            controller.setRdvInfo(rdv);

            Stage stage = new Stage();
            stage.setTitle("Détails du RDV");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleDelete() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText(null);
        alert.setContentText("Voulez-vous vraiment supprimer ce rendez-vous ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            boolean success = RdvDAO.supprimerRdv(rdv.getIdRdv());
            if (success && parentController != null) {
                parentController.refresh();
            }
        }
    }

    @FXML
    private void handleModify() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/AddRdvForm.fxml"));
            Scene scene = new Scene(loader.load());

            FormulaireRdvController controller = loader.getController();
            controller.setParentController(parentController);
            controller.setData(rdv, selectedDate);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Modifier RDV");
            stage.setScene(scene);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

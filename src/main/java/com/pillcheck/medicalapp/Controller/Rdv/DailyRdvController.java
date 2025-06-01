package com.pillcheck.medicalapp.Controller.Rdv;

import com.pillcheck.medicalapp.Model.RdvModels.Rdv;
import com.pillcheck.medicalapp.Model.RdvModels.RdvDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class DailyRdvController {

    @FXML private ScrollPane scrollPane;
    @FXML private Label dateLabel;
    @FXML private Button AddRdvButton;
    @FXML private FlowPane rdvContainer;

    private LocalDate selectedDate;

    public void setDate(LocalDate date) {
        this.selectedDate = date;
        dateLabel.setText("Rendez-vous du " + date.toString());
        refresh();
    }

    public void setSelectedDate(LocalDate selectedDate) {
        this.selectedDate = selectedDate;
    }

    @FXML
    private void openFormulaireRdv(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/AddRdvForm.fxml"));
            Parent root = loader.load();

            FormulaireRdvController controller = loader.getController();
            controller.setParentController(this);
            controller.setSelectedDate(this.selectedDate);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Nouveau Rendez-vous");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Impossible d'ouvrir le formulaire.").showAndWait();
        }
    }

    public void refresh() {
        rdvContainer.getChildren().clear();

        List<Rdv> rdvs = new RdvDAO().getRdvsByDate(selectedDate);

        for (Rdv rdv : rdvs) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/RdvCercle.fxml"));
                Pane rdvPane = loader.load();

                CircleRdv controller = loader.getController();
                controller.setRdvData(rdv);
                controller.setRootNode(rdvPane);
                controller.setParentController(this);
                controller.setSelectedDate(selectedDate);

                rdvContainer.getChildren().add(rdvPane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

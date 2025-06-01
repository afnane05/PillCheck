package com.pillcheck.medicalapp.Controller.Rdv;

import com.pillcheck.medicalapp.Model.RdvModels.Rdv;
import com.pillcheck.medicalapp.Model.RdvModels.RdvDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class FormulaireRdvController {

    @FXML private ComboBox<String> timeComboBox;
    @FXML private TextField motifRdvField;
    @FXML private ComboBox<String> statutComboBox;
    @FXML private Button SaveButton;

    private DailyRdvController parentController;
    private LocalDate selectedDate;
    private Rdv rdv;

    public void setParentController(DailyRdvController controller) {
        this.parentController = controller;
    }

    public void setSelectedDate(LocalDate date) {
        this.selectedDate = date;
    }

    public void setData(Rdv rdv, LocalDate selectedDate) {
        this.rdv = rdv;
        this.selectedDate = selectedDate;
        prefillForm(rdv);
    }

    @FXML
    public void initialize() {
        statutComboBox.getItems().addAll("En attente", "Confirmé", "Annulée");
        ObservableList<String> heures = FXCollections.observableArrayList();
        for (int h = 8; h <= 18; h++) {
            heures.add(String.format("%02d:00", h));
            heures.add(String.format("%02d:30", h));
        }
        timeComboBox.setItems(heures);
    }

    @FXML
    private void EnregistrerRdv() {
        String motif = motifRdvField.getText();
        String statut = statutComboBox.getValue();
        String timeStr = timeComboBox.getValue();

        if (motif == null || motif.trim().isEmpty() || statut == null || timeStr == null) {
            new Alert(Alert.AlertType.WARNING, "Veuillez remplir tous les champs.").showAndWait();
            return;
        }

        try {
            LocalTime time = LocalTime.parse(timeStr);
            LocalDateTime dateRdv = LocalDateTime.of(selectedDate, time);

            RdvDAO dao = new RdvDAO();
            boolean success;

            if (rdv != null && rdv.getIdRdv() != 0) {
                rdv.setMotifRdv(motif);
                rdv.setStatut(statut);
                rdv.setDateRdv(dateRdv);
                success = dao.modifierRdv(rdv);
            } else {
                Rdv newRdv = new Rdv(motif, statut, dateRdv);
                success = dao.ajouterRdv(newRdv);
            }

            if (!success) {
                new Alert(Alert.AlertType.ERROR, "Erreur lors de la sauvegarde du rendez-vous.").showAndWait();
                return;
            }

            // Refresh the full list
            parentController.refresh();

            ((Stage) SaveButton.getScene().getWindow()).close();

        } catch (DateTimeParseException e) {
            new Alert(Alert.AlertType.ERROR, "Heure invalide. Format attendu : HH:mm").showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Erreur inattendue : " + e.getMessage()).showAndWait();
        }
    }

    public void prefillForm(Rdv rdv) {
        motifRdvField.setText(rdv.getMotifRdv());
        statutComboBox.setValue(rdv.getStatut());
        timeComboBox.setValue(rdv.getDateRdv().toLocalTime().toString());
        SaveButton.setText("Modifier");
    }
}

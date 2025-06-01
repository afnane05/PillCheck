package com.pillcheck.medicalapp.Controller.Rdv;

import com.pillcheck.medicalapp.Model.RdvModels.Rdv;
import java.time.format.DateTimeFormatter;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class RdvDetailsController {

    @FXML private TextField motifField;
    @FXML private TextField statutField;
    @FXML private TextField dateField;
    @FXML private TextField heureField;

    public void setRdvInfo(Rdv rdv) {
        motifField.setText(rdv.getMotifRdv());
        statutField.setText(rdv.getStatut());

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        dateField.setText(rdv.getDateRdv().toLocalDate().format(dateFormatter));
        heureField.setText(rdv.getDateRdv().toLocalTime().format(timeFormatter));
    }
}

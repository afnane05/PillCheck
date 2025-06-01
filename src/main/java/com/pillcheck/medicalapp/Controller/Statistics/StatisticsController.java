package com.pillcheck.medicalapp.Controller.Statistics;

import com.pillcheck.medicalapp.Model.PatientModels.Patient;
import com.pillcheck.medicalapp.Model.PatientModels.PatientDAO;
import com.pillcheck.medicalapp.Model.TraitementModels.Traitement;
import com.pillcheck.medicalapp.Model.TraitementModels.TraitementDao;
import com.pillcheck.medicalapp.Model.RdvModels.Rdv;
import com.pillcheck.medicalapp.Model.RdvModels.RdvDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.javafx.FontIcon;

public class StatisticsController implements Initializable {

    // Labels
    @FXML private Label totalPatientsLabel;
    @FXML private Label PatientsLabel;
    @FXML private Label totalTraitementsLabel;
    @FXML private Label TraitementsLabel;
    @FXML private Label totalRdvLabel;
    @FXML private Label RdvLabel;

    // Charts
    @FXML private PieChart patientsChart;
    @FXML private PieChart traitementsChart;
    @FXML private BarChart<String, Number> rdvChart;
    
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadPatientsStats();
        loadTraitementsStats();
        loadRdvStats();

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
    }

    private void loadPatientsStats() {
        List<Patient> patients = PatientDAO.getAllPatients();
        Map<String, Long> traitementCount = patients.stream()
                .collect(Collectors.groupingBy(Patient::getNomTraitement, Collectors.counting()));

        patientsChart.getData().clear();
        for (Map.Entry<String, Long> entry : traitementCount.entrySet()) {
            patientsChart.getData().add(new PieChart.Data(entry.getKey(), entry.getValue()));
        }

        totalPatientsLabel.setText("Total Patients:");
        PatientsLabel.setText(String.valueOf(patients.size()));
    }

    private void loadTraitementsStats() {
        List<Traitement> traitements = TraitementDao.getAllTraitements();
        Map<String, Long> etatCount = traitements.stream()
                .collect(Collectors.groupingBy(Traitement::getETAT, Collectors.counting()));

        traitementsChart.getData().clear();
        for (Map.Entry<String, Long> entry : etatCount.entrySet()) {
            traitementsChart.getData().add(new PieChart.Data(entry.getKey(), entry.getValue()));
        }

        totalTraitementsLabel.setText("Total Traitements:");
        TraitementsLabel.setText(String.valueOf(traitements.size()));
    }

    private void loadRdvStats() {
        List<Rdv> rdvs = RdvDAO.getAllRdv(); // Assurez-vous que cette méthode existe et fonctionne
        Map<String, Long> statutCount = rdvs.stream()
                .collect(Collectors.groupingBy(Rdv::getStatut, Collectors.counting()));

        rdvChart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Statut des RDV");

        for (Map.Entry<String, Long> entry : statutCount.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        rdvChart.getData().add(series);

        totalRdvLabel.setText("Total Rendez-vous:");
        RdvLabel.setText(String.valueOf(rdvs.size()));
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
    void handleCompte(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/View/CompteView.fxml"));
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
    void handleRdv(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/View/RdvView.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

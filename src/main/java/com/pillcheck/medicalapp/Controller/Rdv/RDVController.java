package com.pillcheck.medicalapp.Controller.Rdv;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Locale;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.javafx.FontIcon;


public class RDVController {

    @FXML
    private GridPane calendarGrid;

    @FXML
    private Button btnPrevMonth;

    @FXML
    private Button btnNextMonth;

    @FXML
    private Label labelMonth;

    @FXML
    private Label labelYear;

    @FXML
    private Button btnPrevYear;

    @FXML
    private Button btnNextYear;

    @FXML
    private ImageView filterIcon;

    @FXML
    private Pane yearBox;
    @FXML
    private TextField searchBar;

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
    
    
    private LocalDate currentDate = LocalDate.now();

    @FXML
    public void initialize() {
        
        updateMonthLabel();
        updateYearLabel();
        remplirCalendrier(currentDate);

        btnPrevMonth.setOnAction(e -> {
            currentDate = currentDate.minusMonths(1);
            updateMonthLabel();
            updateYearLabel();
            remplirCalendrier(currentDate);
        });

        btnNextMonth.setOnAction(e -> {
            currentDate = currentDate.plusMonths(1);
            updateMonthLabel();
            updateYearLabel();
            remplirCalendrier(currentDate);
        });

        btnPrevYear.setOnAction(e -> {
            currentDate = currentDate.minusYears(1);
            updateYearLabel();
            updateMonthLabel();
            remplirCalendrier(currentDate);
        });

        btnNextYear.setOnAction(e -> {
            currentDate = currentDate.plusYears(1);
            updateYearLabel();
            updateMonthLabel();
            remplirCalendrier(currentDate);
        });

        filterIcon.setOnMouseClicked(e -> {
            yearBox.setVisible(!yearBox.isVisible());
        });

        yearBox.setVisible(false); // Caché par défaut
        
        
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

    private void updateMonthLabel() {
        String monthName = currentDate.getMonth()
                .getDisplayName(java.time.format.TextStyle.FULL, Locale.FRENCH);
        labelMonth.setText(capitalize(monthName));
    }

    private void updateYearLabel() {
        labelYear.setText(String.valueOf(currentDate.getYear()));
    }

    private String capitalize(String text) {
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }

    private void remplirCalendrier(LocalDate date) {
        calendarGrid.getChildren().clear();

        // Affichage des noms des jours de la semaine sur la ligne 0
        String[] joursSemaine = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche"};
        for (int i = 0; i < joursSemaine.length; i++) {
            Label lbl = new Label(joursSemaine[i]);
            lbl.setStyle("-fx-font-weight: bold;");
            lbl.setMaxWidth(Double.MAX_VALUE);
            lbl.setAlignment(Pos.CENTER);
            calendarGrid.add(lbl, i, 0);
        }

        LocalDate premierJour = date.withDayOfMonth(1);
        int jourSemaineDebut = premierJour.getDayOfWeek().getValue(); // 1 = lundi, 7 = dimanche
        int nbJoursMois = date.lengthOfMonth();

        int compteurJour = 1;

        for (int ligne = 1; ligne <= 6; ligne++) {
            for (int col = 0; col < 7; col++) {
                if (ligne == 1 && col + 1 < jourSemaineDebut) {
                    continue;
                }
                if (compteurJour > nbJoursMois) {
                    return;
                }

                TextField tf = new TextField(String.valueOf(compteurJour));
                tf.setEditable(false);
                tf.setAlignment(Pos.CENTER);
                tf.setCursor(Cursor.HAND);
                tf.setStyle("-fx-background-color: #f0f0f0; -fx-font-weight: bold;");

                final int jourClique = compteurJour;
                tf.setOnMouseClicked((MouseEvent e) -> showRDV(jourClique));

                calendarGrid.add(tf, col, ligne);
                compteurJour++;
            }
        }
    }

    // Méthode corrigée pour afficher les RDVs du jour sélectionné
    @FXML
    private void showRDV(int jour) {
        //System.out.println("showRDV appelé pour jour : " + jour);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Rdvs.fxml"));
            Parent root = loader.load();
            DailyRdvController  controller = loader.getController();
            controller.setDate(LocalDate.of(currentDate.getYear(), currentDate.getMonth(), jour));

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.setTitle("Rendez-vous du " + jour + " " +
                    currentDate.getMonth().getDisplayName(java.time.format.TextStyle.FULL, Locale.FRENCH));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Erreur de chargement de la vue des RDVs.");
            alert.showAndWait();
        }
    }

    @FXML
    private void showYear(MouseEvent event) {
        yearBox.setVisible(!yearBox.isVisible());
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
    void handleStatistics(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/View/StatisticsView.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


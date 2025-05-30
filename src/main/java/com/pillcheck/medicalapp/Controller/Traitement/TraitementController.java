package com.pillcheck.medicalapp.Controller.Traitement;

import com.pillcheck.medicalapp.Model.TraitementModels.Traitement;
import com.pillcheck.medicalapp.Model.TraitementModels.TraitementDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;

public class TraitementController implements Initializable {

    @FXML
    private FlowPane traitementCardsContainer;

    @FXML
    private TextField searchBar;

    @FXML
    private Button addTraitementButton;

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
    
    @FXML
    private Button refreshButton;

    @FXML
    public void handleAddTraitementButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/AddTraitementForm.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Ajouter un Traitement");
            stage.setScene(scene);

            AddTraitementFormController controller = loader.getController();
            controller.setParentController(this);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ajouterCarteTraitement(Traitement traitement) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/TraitementCardIcon.fxml"));
            Node card = loader.load();

            TraitementCardIconController controller = loader.getController();
            controller.setTraitementInfo(traitement,card);
            controller.setParentController(this);

            traitementCardsContainer.getChildren().add(0, card);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public FlowPane getTraitementCardsContainer()
    {
        return traitementCardsContainer;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadTraitementsFromDatabase();

      
        // Icons
        FontIcon icon1 = new FontIcon(FontAwesomeSolid.PILLS);
        icon1.setIconSize(20);
        addTraitementButton.setGraphic(icon1);
        addTraitementButton.setText("");

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

        FontIcon icon9 = new FontIcon(FontAwesomeSolid.SYNC_ALT);
        icon9.setIconSize(20);
        
        refreshButton.setGraphic(icon9);
        refreshButton.setText("");
        
    }

    private void loadTraitementsFromDatabase(){
        traitementCardsContainer.getChildren().clear();//clear existing cards before loading the news ones
        List<Traitement> liste = TraitementDao.getAllTraitements(); // méthode fictive
        for (Traitement traitement : liste) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/TraitementCardIcon.fxml"));
                Pane card = loader.load();
                TraitementCardIconController cardController = loader.getController();
                cardController.setTraitementInfo(traitement,card);
                traitementCardsContainer.getChildren().add(card);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
     @FXML
    public void handleRefresh(){
     
        loadTraitementsFromDatabase();
        
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
    void handlePatients(ActionEvent event) {
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
}

 
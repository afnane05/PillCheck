package com.pillcheck.medicalapp.Controller.Traitement;

import com.pillcheck.medicalapp.Model.TraitementModels.TraitementDao;
import com.pillcheck.medicalapp.Model.TraitementModels.Traitement;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javafx.scene.layout.FlowPane;

public class AddTraitementFormController {

 //   @FXML private FlowPane traitementCardsContainer;
    @FXML private TextField NomTraitementLabel;
    @FXML private TextField TypeTraitementField;
    @FXML private TextField MaladieField;
    @FXML private TextField DescriptionField;
    @FXML private DatePicker DateDebutPicker;
    @FXML private DatePicker DateFinPicker;
    @FXML private TextField DureeEstimeeField;
    @FXML private TextField PosologieField;
    @FXML private TextField EffetSecondaireField;
    //FXML private TextField EtatField;
    @FXML private ChoiceBox<String> EtatField;
    
    @FXML private Button ajouterTraitementButton;
    
    private Traitement traitement;
    private TraitementController parentController;
  
    public void setParentController(TraitementController controller) {
        this.parentController = controller;
    }


    @FXML
    private void initialize() {
        // Configuration des dates par défaut
        DateDebutPicker.setValue(LocalDate.now());
        DateFinPicker.setValue(LocalDate.now().plusDays(7)); // Par défaut, une semaine de traitement
        
        // Calcul automatique de la durée estimée lorsque les dates changent
        DateDebutPicker.valueProperty().addListener((obs, oldVal, newVal) -> updateDureeEstimee());
        DateFinPicker.valueProperty().addListener((obs, oldVal, newVal) -> updateDureeEstimee());
        EtatField.getItems().addAll("en cours","suspendue","terminé");
        
        // Configuration du bouton
       // ajouterTraitementButton.setOnAction(this::handleAddTraitement);
    }
    
    private void updateDureeEstimee() {
        LocalDate dateDebut = DateDebutPicker.getValue();
        LocalDate dateFin = DateFinPicker.getValue();
        
        if (dateDebut != null && dateFin != null) {
            long jours = ChronoUnit.DAYS.between(dateDebut, dateFin);
            DureeEstimeeField.setText(String.valueOf(jours) + " jours");
        }
    }

    @FXML
    private void handleAddTraitement(ActionEvent event) {
        // Récupération des champs
        String nomTraitement = NomTraitementLabel.getText();
        String typeTraitement = TypeTraitementField.getText();
        String maladie = MaladieField.getText();
        String description = DescriptionField.getText();
        LocalDate dateDebut = DateDebutPicker.getValue();
        LocalDate dateFin = DateFinPicker.getValue();
        String dureeEstimee = DureeEstimeeField.getText();
        String posologie = PosologieField.getText();
        String effetsSecondaires =EffetSecondaireField.getText();
        String etat=EtatField.getValue();
       
        if (nomTraitement.isEmpty() || typeTraitement.isEmpty() || maladie.isEmpty() || 
            dateDebut == null || dateFin == null 
            ) {
            showAlert("Champs manquants", "Veuillez remplir tous les champs obligatoires.");
            return;
        }
       
        if (dateDebut.isAfter(dateFin)) {
            showAlert("Erreur de date", "La date de fin doit être postérieure à la date de début.");
            return;
        }
            Traitement traitement = new Traitement(
                nomTraitement,
                typeTraitement,
                maladie,
                description,
                dateDebut,
                dateFin,
                dureeEstimee,
                posologie ,
                effetsSecondaires,
                etat
            );
            
            TraitementDao traitementDAO = new TraitementDao();
         //   boolean success = traitementDAO.ajouterTraitement(traitement);
             boolean success;
            if (this.traitement!=null) {
                
                    this.traitement.setNOM_TRAITEMENT(nomTraitement);
                    this.traitement.setTYPE_TRAITEMENT(typeTraitement);
                    this.traitement.setMALADIE(maladie);
                    this.traitement.setDESCRIPTION(description);
                    this.traitement.setDATE_DEBUT(dateDebut);
                    this.traitement.setDATE_FIN(dateFin);
                    this.traitement.setDUREE_ESTIMEE(dureeEstimee);
                    this.traitement.setPOSOLOGIE(posologie);
                    this.traitement.setEFFETS_SECONDAIRES(effetsSecondaires);
                    this.traitement.setETAT(etat);
               
               
                
                success=traitementDAO.modifierTraitement(traitement);
                if (parentController != null) {
                    parentController.ajouterCarteTraitement(traitement);
                    closeWindow(event);
                }
                closeWindow(event);
            } else {
                success=traitementDAO.ajouterTraitement(traitement);
               
            }
              
            if(success)
            {
                parentController.handleRefresh();
                closeWindow(event);
            }
            else
            {
                showAlert("Erreur", "Erreur est survenue lors de l'enregistrement du traitement.");

        }
             
        }
     private void closeWindow(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    private void showAlert(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    
    public void prefilForm(Traitement traitement)
    {
        this.traitement=traitement;
        NomTraitementLabel.setText(traitement.getNOM_TRAITEMENT());
        TypeTraitementField.setText(traitement.getTYPE_TRAITEMENT());
        MaladieField.setText(traitement.getMALADIE());
        DescriptionField.setText(traitement.getDESCRIPTION());
        DateDebutPicker.setValue(traitement.getDATE_DEBUT());
        DateFinPicker.setValue(traitement.getDATE_FIN());
        DureeEstimeeField.setText(traitement.getDUREE_ESTIMEE());
        PosologieField.setText(traitement.getPOSOLOGIE());
        EffetSecondaireField.setText(traitement.getEFFETS_SECONDAIRES());
        EtatField.setValue(traitement.getETAT());
        
        ajouterTraitementButton.setText("modifier");
        
   
    }

   
}
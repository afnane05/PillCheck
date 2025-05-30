package com.pillcheck.medicalapp.Controller.Traitement;



import com.pillcheck.medicalapp.Controller.Traitement.AddTraitementFormController;
import com.pillcheck.medicalapp.Model.TraitementModels.Traitement;
import com.pillcheck.medicalapp.Model.TraitementModels.TraitementDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class TraitementCardIconController {
    
    

    @FXML private TextField NomTraitementLabel;
    @FXML private TextField TypeTraitementField;
    @FXML private TextField PosologieField;
    @FXML private TextField MaladieField ;  
    @FXML private TextField DureeEstimeeField ;
    
    @FXML private Button addTraitementButton ;
    @FXML private MenuItem deleteTraitement;
    @FXML private MenuItem modifyItem;
     @FXML private MenuButton optionsMenuButton ;
    
   
    @FXML private Pane cardTraitContainer;
    private Traitement traitement;
    private TraitementController parentController;
    private Node rootNode;  // Add this field

   public void setTraitementInfo(Traitement traitement, Node rootNode) {
    this.traitement = traitement;
    this.rootNode = rootNode;
    
    NomTraitementLabel.setText(traitement.getNOM_TRAITEMENT());
    TypeTraitementField.setText(traitement.getTYPE_TRAITEMENT());
    PosologieField.setText(traitement.getPOSOLOGIE());
    MaladieField.setText(traitement.getMALADIE());
    
    // Calcul de la durée pour l'affichage dans la carte
    if (traitement.getDATE_DEBUT() != null && traitement.getDATE_FIN() != null) {
        long jours = ChronoUnit.DAYS.between(traitement.getDATE_DEBUT(), traitement.getDATE_FIN()) + 1;
        DureeEstimeeField.setText(jours + " jour(s)");
    } else {
        DureeEstimeeField.setText(traitement.getDUREE_ESTIMEE());
    }
}
    



    public void setParentController(TraitementController parentController) {
        this.parentController = parentController;
    }

    // Let the parent set the root node reference (needed to replace it later)
    public void setRootNode(Node root) {
        this.rootNode = root;
    }

    @FXML
    private void initialize() {
        cardTraitContainer.setOnMouseClicked(this::handleCardClick);

    }
    public void setTraitement(Traitement traitement) {
        this.traitement = traitement;
}


    @FXML
    private void handleCardClick(MouseEvent event) {
        try {
            
            
 
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/TraitementCards.fxml"));
            Scene scene = new Scene(loader.load());

            // Get the controller and set patient data
            TraitementCardsController controller = loader.getController();
            controller.setTraitementInfo(traitement);

            // Create and show a new stage
            Stage stage = new Stage();
            stage.setTitle("Details DE Traitement");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL); // Block interaction with other windows
            stage.showAndWait(); // Wait until this window is closed

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
      @FXML
        private void handleDelete()
                {
                    
                    
                    Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("confirmation de suppression");
                    alert.setHeaderText(null);
                    alert.setContentText("voulez-vous vraiment suppeimer ce traitement ?");
                    
                    Optional<ButtonType> result =alert.showAndWait();
                   if(result.isPresent() && result.get()==ButtonType.OK)
                    {
                        boolean success = TraitementDao.supprimerTraitement(traitement.getId());       
                    }
                      
                    
                    
                    
                }
        
        
    @FXML
    
    
    private void handleModify(){

        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/AddTraitementForm.fxml"));
            Scene scene = new Scene(loader.load());
            
            AddTraitementFormController controller = loader.getController();
            controller.prefilForm(traitement);
            
            controller.setParentController(parentController); //to refresh code
            
            Stage stage=new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Modifier Traitement");
            stage.setScene(scene);
            stage.showAndWait();
            
            
        }
        catch(IOException e)
        {
            e.printStackTrace();
            
        }
        
      
    }


    

}

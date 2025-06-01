package com.pillcheck.medicalapp.Controller.Traitement;


import com.pillcheck.medicalapp.Model.TraitementModels.Traitement;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class TraitementCardsController
{
    @FXML private TextField  NomTraitementLabel;
    @FXML private TextField  TypeTraitementField;
    @FXML private TextField  MaladieField;
    @FXML private TextField  DescriptionField;
    @FXML private TextField  EffetSecondaireField;
    @FXML private DatePicker DateDebutPicker;
    @FXML private DatePicker DateFinPicker;
    @FXML private TextField  DureeEstimeeField;
    @FXML private TextField  PosologieField;
    @FXML private TextField  EtatField;
    
   
        
    public void setTraitementInfo(Traitement traitement) {
    NomTraitementLabel.setText(traitement.getNOM_TRAITEMENT());
    TypeTraitementField.setText(traitement.getTYPE_TRAITEMENT());
    MaladieField.setText(traitement.getMALADIE());
    DescriptionField.setText(traitement.getDESCRIPTION());
    EffetSecondaireField.setText(traitement.getEFFETS_SECONDAIRES());
    DateDebutPicker.setValue(traitement.getDATE_DEBUT());
    DateFinPicker.setValue(traitement.getDATE_FIN());
         if (traitement.getDATE_DEBUT() != null && traitement.getDATE_FIN() != null) {
        long jours = ChronoUnit.DAYS.between(traitement.getDATE_DEBUT(), traitement.getDATE_FIN()) + 1;
         DureeEstimeeField.setText(jours + " jour(s)");
    } else {
        DureeEstimeeField.setText(traitement.getDUREE_ESTIMEE());
    }
    
    PosologieField.setText(traitement.getPOSOLOGIE());
    EtatField.setText(traitement.getETAT());
}
        
    }        
    
 


package com.pillcheck.medicalapp.Controller.Traitement;


import com.pillcheck.medicalapp.Model.TraitementModels.Traitement;
import java.time.format.DateTimeFormatter;
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
    
    public void setTraitementInfo(Traitement traitement)
    {
        NomTraitementLabel.setText(traitement.getNOM_TRAITEMENT());
        TypeTraitementField.setText(traitement.getTYPE_TRAITEMENT());
        MaladieField.setText(traitement.getMALADIE());
        DescriptionField.setText(traitement.getDESCRIPTION());
        EffetSecondaireField.setText(traitement.getEFFETS_SECONDAIRES());
       // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateDebutPicker.setValue(traitement.getDATE_DEBUT());
        DateFinPicker.setValue(traitement.getDATE_FIN());
        DureeEstimeeField.setText(traitement.getDUREE_ESTIMEE());
        PosologieField.setText(traitement.getPOSOLOGIE());
        EtatField.setText(traitement.getETAT());
        
    }        
    
 
}

package com.pillcheck.medicalapp.Controller.Parametre;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class HelpViewController implements Initializable {

    @FXML
    private TextArea helpTextArea;

    @FXML
    private Button closeButton;
    
    @FXML
    private Button patientQuestionButton;
    
    @FXML
    private Button traitementQuestionButton;
    
    @FXML
    private Button rdvQuestionButton;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupHelpContent();
    }

    private void setupHelpContent() {
        String helpText = """
            🏥 PILLCHECK - VOTRE E-CLINIQUE
            ================================
            
            📋 DESCRIPTION DE L'APPLICATION
            
            PillCheck est une application de gestion médicale moderne conçue pour 
            faciliter le suivi des patients et la gestion des traitements médicaux.
            
            ✨ FONCTIONNALITÉS PRINCIPALES :
            
            👥 GESTION DES PATIENTS
            • Ajout et modification des informations patients
            • Historique médical complet
            • Recherche rapide et filtrage
            
            💊 SUIVI DES TRAITEMENTS
            • Prescription de médicaments
            • Calendrier de prise
            • Alertes et rappels
            
            📅 RENDEZ-VOUS
            • Planification des consultations
            • Gestion du calendrier médical
            • Notifications automatiques
            
            📊 STATISTIQUES
            • Rapports détaillés
            • Analyse des données patients
            • Indicateurs de performance
            
            ⚙️ PARAMÈTRES
            • Configuration personnalisée
            • Gestion des utilisateurs
            • Préférences système
            
            🎯 OBJECTIF
            Améliorer la qualité des soins en digitalisant et centralisant 
            la gestion médicale pour une meilleure efficacité.
            
        
            📧 Support Assistance
            Pour toute question ou demande d'assistance, notre équipe support est à votre disposition.
            N'hésitez pas à nous contacter par e-mail à l'une des adresses suivantes :
                          
              📧 ta.tajmout@gmail.com                  
              📧 afnanelamriss475@gmail.com              
              📧 merzakromaissae@gmail.com
                          
            
             Nous nous engageons à vous répondre dans les plus brefs délais.
                     
            
            © 2025 PillCheck - Tous droits réservés
            """;
        
        helpTextArea.setText(helpText);
        helpTextArea.setEditable(false);
        helpTextArea.setWrapText(true);
        
        // Ajouter l'événement de clic sur le TextArea
        helpTextArea.setOnMouseClicked(event -> openDescriptionWindow());
    }
    
    @FXML
    private void handlePatientQuestion() {
        showAnswerDialog("Comment ajouter un patient ?", 
            "Pour ajouter un patient :\n\n" +
            "1. Cliquez sur 'Mes Patients' dans le menu de navigation\n" +
            "2. Cliquez sur le bouton 'Ajouter Patient'\n" +
            "3. Remplissez le formulaire avec les informations du patient :\n" +
            "   - Nom et prénom\n" +
            "   - Date de naissance\n" +
            "   - Numéro de téléphone\n" +
            "   - Adresse email\n" +
            "   - Adresse\n" +
            "4. Cliquez sur 'Enregistrer' pour confirmer\n\n" +
            "Le patient sera automatiquement ajouté à votre liste de patients.");
    }
    
    @FXML
    private void handleTraitementQuestion() {
        showAnswerDialog("Comment ajouter un traitement ?", 
            "Pour ajouter un traitement :\n\n" +
            "1. Allez dans 'Mes Traitements' depuis le menu principal\n" +
            "2. Cliquez sur 'Ajouter Traitement'\n" +
            "3. Sélectionnez le patient concerné\n" +
            "4. Remplissez les informations du traitement :\n" +
            "   - Nom du médicament\n" +
            "   - Dosage\n" +
            "   - Fréquence de prise\n" +
            "   - Durée du traitement\n" +
            "   - Instructions spéciales\n" +
            "5. Définissez les rappels si nécessaire\n" +
            "6. Validez en cliquant sur 'Enregistrer'\n\n" +
            "Le traitement sera ajouté au dossier du patient.");
    }
    
    @FXML
    private void handleRdvQuestion() {
        showAnswerDialog("Comment ajouter un rendez-vous ?", 
            "Pour programmer un rendez-vous :\n\n" +
            "1. Accédez à la section 'Rendez-Vous'\n" +
            "2. Cliquez sur 'Nouveau Rendez-vous'\n" +
            "3. Choisissez le patient dans la liste\n" +
            "4. Sélectionnez la date et l'heure\n" +
            "5. Ajoutez le motif de consultation\n" +
            "6. Définissez la durée prévue\n" +
            "7. Activez les notifications si souhaité\n" +
            "8. Confirmez en cliquant sur 'Programmer'\n\n" +
            "Le rendez-vous apparaîtra dans votre calendrier médical.\n" +
            "Une notification automatique sera envoyée si configurée.");
    }
    
    private void openDescriptionWindow() {
        try {
            // Créer une nouvelle fenêtre pour la description complète
            Stage descriptionStage = new Stage();
            descriptionStage.setTitle("Description Complète - PillCheck");
            
            // Créer le contenu de la fenêtre
            VBox root = new VBox(10);
            root.setPadding(new javafx.geometry.Insets(20));
            root.setStyle("-fx-background-color: #f8f9fa;");
            
            // Titre
            Label titleLabel = new Label("PILLCHECK - DESCRIPTION COMPLÈTE");
            titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");
            
            // TextArea avec la description complète
            TextArea descriptionArea = new TextArea();
            descriptionArea.setText(helpTextArea.getText());
            descriptionArea.setEditable(false);
            descriptionArea.setWrapText(true);
            descriptionArea.setPrefHeight(400);
            descriptionArea.setPrefWidth(600);
            descriptionArea.setStyle("-fx-font-size: 13px; -fx-background-color: white; -fx-border-color: #dee2e6; -fx-border-radius: 5;");
            
            // Bouton de fermeture
            Button closeBtn = new Button("Fermer");
            closeBtn.setStyle("-fx-background-color: #dc3545; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 5; -fx-padding: 10px 20px;");
            closeBtn.setOnAction(e -> descriptionStage.close());
            
            // Centrer le bouton
            VBox buttonContainer = new VBox();
            buttonContainer.setAlignment(javafx.geometry.Pos.CENTER);
            buttonContainer.getChildren().add(closeBtn);
            
            // Ajouter tous les éléments au conteneur principal
            root.getChildren().addAll(titleLabel, descriptionArea, buttonContainer);
            
            // Créer et afficher la scène
            Scene scene = new Scene(root, 650, 500);
            descriptionStage.setScene(scene);
            descriptionStage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
            descriptionStage.setResizable(true);
            descriptionStage.show();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void showAnswerDialog(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Aide PillCheck");
        alert.setHeaderText(title);
        alert.setContentText(content);
        alert.getDialogPane().setPrefWidth(500);
        alert.getDialogPane().setPrefHeight(400);
        alert.showAndWait();
    }

    @FXML
    private void closeHelp() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
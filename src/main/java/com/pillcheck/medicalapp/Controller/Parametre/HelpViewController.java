
package com.pillcheck.medicalapp.Controller.Parametre;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class HelpViewController implements Initializable {

    @FXML
    private TextArea helpTextArea;

    @FXML
    private Button closeButton;

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
            Pour toute question ou demande d’assistance, notre équipe support est à votre disposition.
            N’hésitez pas à nous contacter par e-mail à l’une des adresses suivantes :
                          
              📧 ta.tajmout@gmail.com                  
              📧 afnanelamriss475@gmail.com              
              📧 merzakromaissae@gmail.com
            
             Nous nous engageons à vous répondre dans les plus brefs délais.
                     
            
            © 2025 PillCheck - Tous droits réservés
            """;
        
        helpTextArea.setText(helpText);
        helpTextArea.setEditable(false);
        helpTextArea.setWrapText(true);
    }

    @FXML
    private void closeHelp() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
package com.pillcheck.medicalapp;

import javafx.scene.image.Image;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/PatientView.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setTitle("PillCheck - Gestion des Patients");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/logoIcon.png")) {});
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

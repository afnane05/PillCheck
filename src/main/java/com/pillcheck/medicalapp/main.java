package com.pillcheck.medicalapp;

import javafx.scene.image.Image;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/LoginUser.fxml"));
        Pane root = new Pane();
        loader.setRoot(root);
        loader.load();
        
        Scene scene = new Scene(root);
        primaryStage.setTitle("PillCheck - Gestion des Patients");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/logoIcon.png")) {});
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

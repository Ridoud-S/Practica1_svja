package org.svja.top.practica1_sevj;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CalculateCaloriesApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/svja/top/practica1_sevj/view/simulator.fxml"));
        Scene scene = new Scene(loader.load(), 900, 600);
        stage.setTitle("Weight Loss Simulator");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

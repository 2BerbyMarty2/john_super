package com.example.john_super;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("John's Super Application");
        stage.setScene(scene);
        stage.show();
        stage.toFront();
        stage.requestFocus();
        stage.setResizable(false);
        stage.setOnCloseRequest(e -> {
            e.consume();
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit?");
            Optional<ButtonType> result = confirm.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                stage.close();
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}
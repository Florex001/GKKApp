package com.example.gepkocsikolcsonzes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class GKKApp extends Application {
    public static Stage login_window;
    @Override
    public void start(Stage login_stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GKKApp.class.getResource("login-view.fxml"));
        Scene login_scene = new Scene(fxmlLoader.load(), 767, 460);
        login_window = login_stage;
        login_stage.setResizable(false);
        login_stage.initStyle(StageStyle.UTILITY.UNDECORATED);
        login_stage.setScene(login_scene);
        login_stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
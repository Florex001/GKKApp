package com.example.gepkocsikolcsonzes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WelcomeCon implements Initializable {
    public static Stage profile_window;
    public static Stage reservation_window;

    @FXML
    private void reservation_btn() throws IOException {//foglalások ablak

        Rectangle2D screenBounds = Screen. getPrimary(). getBounds();

        FXMLLoader reservation_view = new FXMLLoader(GKKApp.class.getResource("reservation-view.fxml"));
        Scene reservation_scene = new Scene(reservation_view.load(), screenBounds.getWidth(), screenBounds.getHeight() - 70);
        Stage reservation_stage = new Stage();
        reservation_window = reservation_stage;
        reservation_stage.setScene(reservation_scene);
        reservation_stage.setMinWidth(700);
        reservation_stage.setMinHeight(500);
        reservation_stage.show();
        LoginCon.welcome_window.close();
    }

    @FXML
    private void account_btn() throws IOException {//profil
        FXMLLoader profile_view = new FXMLLoader(GKKApp.class.getResource("profile-view.fxml"));
        Scene profile_scene = new Scene(profile_view.load(), 400, 525);
        Stage profile_stage = new Stage();
        profile_window = profile_stage;
        profile_stage.initModality(Modality.WINDOW_MODAL);
        profile_stage.initOwner(LoginCon.welcome_window);
        profile_stage.setResizable(false);
        profile_stage.setScene(profile_scene);
        profile_stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}

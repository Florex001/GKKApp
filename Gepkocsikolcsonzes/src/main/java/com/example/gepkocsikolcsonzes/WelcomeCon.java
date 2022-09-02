package com.example.gepkocsikolcsonzes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import static com.example.gepkocsikolcsonzes.LoginCon.welcome_window;

public class WelcomeCon implements Initializable {
    public static Stage profile_window;

    @FXML
    private void account_btn() throws IOException {
        FXMLLoader profile_view = new FXMLLoader(GKKApp.class.getResource("profile-view.fxml"));
        Scene profile_scene = new Scene(profile_view.load(), 400, 500);
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

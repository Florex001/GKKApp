package com.example.gepkocsikolcsonzes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WelcomeCon implements Initializable {
    public static Stage profile_window;
    public static Stage reservation_window;
    public static Stage company_window;
    public static Stage cars_window;

    @FXML
    private Button profile_FXID;


    @FXML
    private void logout_btn(){
        LoginCon.welcome_window.close();
        GKKApp.login_window.show();
    }
    @FXML
    private void reservation_btn() throws IOException {

        Rectangle2D screenBounds = Screen. getPrimary(). getBounds();

        FXMLLoader reservation_view = new FXMLLoader(GKKApp.class.getResource("reservation-view.fxml"));
        Scene reservation_scene = new Scene(reservation_view.load(), screenBounds.getWidth(), screenBounds.getHeight() - 70);
        Stage reservation_stage = new Stage();
        reservation_window = reservation_stage;
        reservation_stage.setScene(reservation_scene);
        reservation_stage.setMinWidth(1120);
        reservation_stage.setMinHeight(600);
        reservation_stage.show();
        LoginCon.welcome_window.close();
    }//ha erre a gombra kattint akkor jelenjen meg a reservation ablak

    @FXML
    private void cars_btn() throws IOException {
        Rectangle2D screenBounds = Screen. getPrimary(). getBounds();

        FXMLLoader cars_view = new FXMLLoader(GKKApp.class.getResource("cars-view.fxml"));
        Scene cars_scene = new Scene(cars_view.load(), screenBounds.getWidth(), screenBounds.getHeight() - 70);
        Stage cars_stage = new Stage();
        cars_window = cars_stage;
        cars_stage.setScene(cars_scene);
        cars_stage.setMinWidth(1120);
        cars_stage.setMinHeight(625);
        cars_stage.show();
        LoginCon.welcome_window.close();
    }//ha erre a gombra kattint akkor jelenjen meg a gépjárművek ablak

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
    }//profil megtekintése és jelszó változtatás

    @FXML
    private void company_information_BTN() throws IOException{
        FXMLLoader company_view = new FXMLLoader(GKKApp.class.getResource("company-view.fxml"));
        Scene company_scene = new Scene(company_view.load(), 927, 592);
        Stage company_stage = new Stage();
        company_window = company_stage;
        company_stage.initModality(Modality.WINDOW_MODAL);
        company_stage.initStyle(StageStyle.UTILITY.UNDECORATED);
        company_stage.initOwner(LoginCon.welcome_window);
        company_stage.setResizable(false);
        company_stage.setScene(company_scene);
        company_stage.show();
    }// cég információi itt jelennek meg

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (LoginCon.global_rank.equals("admin")){
            profile_FXID.setText("Profil/Admin");
        }
    }
}

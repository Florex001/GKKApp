package com.example.gepkocsikolcsonzes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.example.gepkocsikolcsonzes.GKKApp.login_window;

public class LoginCon implements Initializable {

    public static String uName;
    public static String pass;

    public static String global_rank;
    public static Stage welcome_window;

    @FXML
    private TextField uname_textfield;
    @FXML
    private PasswordField pass_textfield;


    @FXML
    private void login_button() throws IOException {
        login_action();
    }

    @FXML
    private void exit_btn(){
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private void login_action() throws IOException {
        String username = uname_textfield.getText();
        String password = pass_textfield.getText();
        uName = username;
        pass = password;

        if (username.equals("") && password.equals("")){
            Alert no_data_alert = new Alert(Alert.AlertType.INFORMATION);//figyelmeztető ablak jelenik meg hogy töltse ki a mezőket
            no_data_alert.setTitle("Bejelentkezés");
            no_data_alert.setHeaderText("Írja be felhasználónevét és jelszavát!");
            no_data_alert.setContentText("Próbálja újra!");
            no_data_alert.initOwner(login_window);
            no_data_alert.show();
        }else{
            try {//létrehozzuk a kapcsolatot az adatbázissal és le kérdezzük az adatokat
                Connection con = DBConnector.getConnection();

                ResultSet login = con.createStatement().executeQuery("SELECT * FROM user WHERE username = ('"+ username +"' AND password = '"+ password +"');");

                if (login.next()){
                    ResultSet rank = con.createStatement().executeQuery("SELECT rank FROM `user` WHERE (username = '"+ username +"')");

                    if (rank.next()){
                        global_rank = rank.getString("rank");

                        if (rank.getString("rank").equals("worker") || rank.getString("rank").equals("admin")){

                            Rectangle2D screenBounds = Screen. getPrimary(). getBounds();

                            FXMLLoader welcome_view = new FXMLLoader(GKKApp.class.getResource("welcome-view.fxml"));
                            Scene welcome_scene = new Scene(welcome_view.load(), screenBounds.getWidth(), screenBounds.getHeight() - 70);
                            Stage welcome_stage = new Stage();
                            welcome_window = welcome_stage;
                            welcome_stage.setScene(welcome_scene);
                            welcome_stage.setMinWidth(600);
                            welcome_stage.setMinHeight(470);
                            welcome_stage.show();
                            uname_textfield.setText("");
                            pass_textfield.setText("");
                            uname_textfield.requestFocus();
                            login_window.close();
                        }

                    }else {

                        Alert no_permission_alert = new Alert(Alert.AlertType.CONFIRMATION);//ha az accountnak nincsen jogosultsága akkor megjelenik egy alert
                        no_permission_alert.setTitle("Hiba");
                        no_permission_alert.setHeaderText("Nincs megfelelő jogosultsága!");
                        no_permission_alert.initOwner(login_window);
                        no_permission_alert.show();
                        uname_textfield.setText("");
                        pass_textfield.setText("");
                        uname_textfield.requestFocus();

                    }
                }else {
                    Alert bad_data_alert = new Alert(Alert.AlertType.INFORMATION);
                    bad_data_alert.setTitle("Hiba");//hiba ablak jelenik meg a rosszul megadott információk miatt
                    bad_data_alert.setHeaderText("Hibás adatok.");
                    bad_data_alert.setContentText("Próbálja újra.");
                    bad_data_alert.initOwner(login_window);
                    bad_data_alert.show();
                }

            } catch (SQLException e) {
                Alert no_connection = new Alert(Alert.AlertType.ERROR);
                no_connection.setHeaderText("Sajnos nem tudunk az adatbázishoz csatlakozni.");
                no_connection.setContentText("Ellenőrizze az internet kapcsolatot.");
                no_connection.initOwner(login_window);
                no_connection.show();
            }
        }
    }

}
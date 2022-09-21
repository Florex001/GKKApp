package com.example.gepkocsikolcsonzes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ProfileCon implements Initializable {

    public static Stage admin_window;
    @FXML
    private Label mail_lab;
    @FXML
    private Label name_lab;
    @FXML
    private Label phonenum_lab;
    @FXML
    private Label rank_lab;
    @FXML
    private Label registrationdate_lab;
    @FXML
    private Label username_lab;

    @FXML
    private Label new_pass_lbl;

    @FXML
    private PasswordField new_PF_fx;

    @FXML
    private Button new_pass_fx_btn;

    @FXML
    private Button admin_btnfx;

    @FXML
    private void new_pass_change_btn(){
        new_pass_lbl.setVisible(true);
        new_PF_fx.setVisible(true);
        new_pass_fx_btn.setVisible(true);
    }

    @FXML
    private void new_pass_ok_btn(){
        if (new_PF_fx.equals("")){
            Alert empty_new_pass_alert = new Alert(Alert.AlertType.INFORMATION);//hiba ablak jelenik meg a nem megadott információ miatt
            empty_new_pass_alert.setTitle("Hiba!");
            empty_new_pass_alert.setHeaderText("Adjon meg egy jelszót!");
            empty_new_pass_alert.setContentText("Próbálja újra!");
            empty_new_pass_alert.initOwner(WelcomeCon.profile_window);
            empty_new_pass_alert.show();
        }else {
            try {
                Connection con = DBConnector.getConnection();

                Statement st = con.createStatement();

                String pass_change_sql = ("UPDATE `user` SET `password` = '" + new_PF_fx.getText() + "' WHERE username = '" + LoginCon.uName + "';");

                int i = st.executeUpdate(pass_change_sql);

                if (i == 1){
                    Alert done_new_pass_alert = new Alert(Alert.AlertType.INFORMATION);//hiba ablak jelenik meg a nem megadott információ miatt
                    done_new_pass_alert.setTitle("");
                    done_new_pass_alert.setHeaderText("Jelszava megváltozott");
                    done_new_pass_alert.setContentText("Gratulálunk!");
                    done_new_pass_alert.initOwner(WelcomeCon.profile_window);
                    done_new_pass_alert.show();

                    new_PF_fx.setText("");

                    new_PF_fx.setVisible(false);
                    new_pass_fx_btn.setVisible(false);
                    new_pass_lbl.setVisible(false);
                }else {
                    Alert bad_connect = new Alert(Alert.AlertType.INFORMATION);//hiba ablak jelenik meg a nem megadott információ miatt
                    bad_connect.setTitle("Hiba!");
                    bad_connect.setHeaderText("Jelszavát nem tudtuk megváltoztatni!");
                    bad_connect.setContentText("Próbálja újra, később!");
                    bad_connect.initOwner(WelcomeCon.profile_window);
                    bad_connect.show();
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    private void logout_btn(){//kijelentkezés gomb
        LoginCon.welcome_window.close();
        WelcomeCon.profile_window.close();
        GKKApp.login_window.show();
    }

    @FXML
    private void admin_btn() throws IOException {
        Rectangle2D screenBounds = Screen. getPrimary(). getBounds();

        FXMLLoader admin_view = new FXMLLoader(GKKApp.class.getResource("admin-view.fxml"));
        Scene admin_scene = new Scene(admin_view.load(), screenBounds.getWidth(), screenBounds.getHeight() - 70);
        Stage admin_stage = new Stage();
        admin_window = admin_stage;
        admin_stage.initModality(Modality.WINDOW_MODAL);
        admin_stage.setScene(admin_scene);
        admin_stage.show();
        LoginCon.welcome_window.close();
        WelcomeCon.profile_window.close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (LoginCon.global_rank.equals("admin")){
            admin_btnfx.setVisible(true);
        }
        username_lab.setText(LoginCon.uName);
        email_sql();
        name_sql();
        phone_num_sql();
        rank_lab.setText(LoginCon.global_rank);
        registration_date_sql();
    }

    private void email_sql(){
        try {
            Connection con = DBConnector.getConnection();

            ResultSet email = con.createStatement().executeQuery("SELECT email FROM `user` WHERE (username = '" + LoginCon.uName + "');");

            if (email.next()){
                String mail = email.getString("email");
                mail_lab.setText(mail);
            }
        } catch (SQLException e) {
            Alert no_connection = new Alert(Alert.AlertType.ERROR);
            no_connection.setHeaderText("Sajnos nem tudunk az adatbázishoz csatlakozni.");
            no_connection.setContentText("Ellenőrizze az internet kapcsolatot.");
            no_connection.initOwner(GKKApp.login_window);
            no_connection.show();
        }
    }

    private void name_sql(){
        try {
            Connection con = DBConnector.getConnection();

            ResultSet name = con.createStatement().executeQuery("SELECT first_name, last_name FROM `user` WHERE (username = '"+ LoginCon.uName +"');");

            if (name.next()){
                String firstname = name.getString("first_name");
                String lastname = name.getString("last_name");

                name_lab.setText(firstname + " " + lastname);
            }

        } catch (SQLException e) {
            Alert no_connection = new Alert(Alert.AlertType.ERROR);
            no_connection.setHeaderText("Sajnos nem tudunk az adatbázishoz csatlakozni.");
            no_connection.setContentText("Ellenőrizze az internet kapcsolatot.");
            no_connection.initOwner(GKKApp.login_window);
            no_connection.show();
        }
    }

    private void phone_num_sql(){
        try {
            Connection con = DBConnector.getConnection();

            ResultSet phone_num = con.createStatement().executeQuery("SELECT phone_number FROM `user` WHERE (username = '"+LoginCon.uName+"');");

            if (phone_num.next()){
                String phonenum = phone_num.getString("phone_number");
                phonenum_lab.setText(phonenum);
            }
        } catch (SQLException e) {
            Alert no_connection = new Alert(Alert.AlertType.ERROR);
            no_connection.setHeaderText("Sajnos nem tudunk az adatbázishoz csatlakozni.");
            no_connection.setContentText("Ellenőrizze az internet kapcsolatot.");
            no_connection.initOwner(GKKApp.login_window);
            no_connection.show();
        }
    }

    private void registration_date_sql(){
        try {
            Connection con = DBConnector.getConnection();

            ResultSet reg_date = con.createStatement().executeQuery("SELECT registration_date FROM `user` WHERE (username = '"+LoginCon.uName+"');");

            if (reg_date.next()){
                String regis_date = reg_date.getString("registration_date");
                registrationdate_lab.setText(regis_date);
            }

        } catch (SQLException e) {
            Alert no_connection = new Alert(Alert.AlertType.ERROR);
            no_connection.setHeaderText("Sajnos nem tudunk az adatbázishoz csatlakozni.");
            no_connection.setContentText("Ellenőrizze az internet kapcsolatot.");
            no_connection.initOwner(GKKApp.login_window);
            no_connection.show();
        }
    }


}

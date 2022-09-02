package com.example.gepkocsikolcsonzes;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.example.gepkocsikolcsonzes.GKKApp.login_window;

public class ProfileCon implements Initializable {
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
            no_connection.initOwner(login_window);
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
            no_connection.initOwner(login_window);
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
            no_connection.initOwner(login_window);
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
            no_connection.initOwner(login_window);
            no_connection.show();
        }
    }


}

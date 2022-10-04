package com.example.gepkocsikolcsonzes;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CompanyCon implements Initializable {

    @FXML
    private void back_btn(){
        LoginCon.welcome_window.show();
        WelcomeCon.company_window.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

}

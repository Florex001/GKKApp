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
    private Label registration_num;

    @FXML
    private Label company_name;

    @FXML
    private Label hq_lab;

    @FXML
    private Label tax_number;

    @FXML
    private void back_btn(){
        LoginCon.welcome_window.show();
        WelcomeCon.company_window.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        registration_num.setText("");
        company_name.setText("");
        hq_lab.setText("");
        tax_number.setText("");
        company_info_sql();
    }

    private void company_info_sql(){

        try {
            Connection con = DBConnector.getConnection();

            String company = "SELECT * FROM `company` WHERE id = 1;";

            ResultSet company_com = con.createStatement().executeQuery(company);

            if (company_com.next()){
                registration_num.setText("Regisztrációs szám: " + company_com.getString("registration_number"));
                company_name.setText("Cég név: " + company_com.getString("company_name"));
                hq_lab.setText("Székhely: " + company_com.getString("headquarter"));
                tax_number.setText("Adó szám: " + company_com.getString("tax_number"));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

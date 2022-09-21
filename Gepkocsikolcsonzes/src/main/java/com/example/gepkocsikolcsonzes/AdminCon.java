package com.example.gepkocsikolcsonzes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminCon implements Initializable {
    @FXML
    private TableView<Users> users_table;

    @FXML
    private TableColumn<Users, String> email_tc;

    @FXML
    private TableColumn<Users, String> id_tc;

    @FXML
    private TableColumn<Users, String> name_tc;

    @FXML
    private TableColumn<Users, String> pass_tc;

    @FXML
    private TableColumn<Users, String> phone_tc;

    @FXML
    private TableColumn<Users, String> rank_tc;

    @FXML
    private TableColumn<Users, String> regdat_tc;

    @FXML
    private TableColumn<Users, String> uname_tc;

    @FXML
    private AnchorPane mainpage_fx;
    @FXML
    private AnchorPane message_fx;
    @FXML
    private AnchorPane users_fx;

    int index = -1;

    ObservableList<Users> userslist = FXCollections.observableArrayList();

    @FXML
    private void users_selected(){
        index = users_table.getSelectionModel().getSelectedIndex();
        if (index <= -1){
            return;
        }
    }//kiválasztott felhasználónak az indexe eltárolódik


    @FXML
    private void back_btn(){
        ProfileCon.admin_window.close();
        LoginCon.welcome_window.show();
    }//visszalépés a welcome pagera

    @FXML
    private void mainpage_BTN(){
        mainpage_fx.setVisible(true);
        message_fx.setVisible(false);
        users_fx.setVisible(false);
    }//main page anchor pane töltődik csak be

    @FXML
    private void message_btn(){
        mainpage_fx.setVisible(false);
        message_fx.setVisible(true);
        users_fx.setVisible(false);
    }//message page anchor pane töltődik csak be

    @FXML
    private void users_btn(){
        mainpage_fx.setVisible(false);
        message_fx.setVisible(false);
        users_fx.setVisible(true);
    }//users page anchor pane töltődik csak be

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usertable();
        mainpage_fx.setVisible(true);
        message_fx.setVisible(false);
        users_fx.setVisible(false);
    }

    private void usertable(){
        try {
            Connection con = DBConnector.getConnection();

            String usersDB = "SELECT * FROM `user`";

            ResultSet user = con.createStatement().executeQuery(usersDB);

            while (user.next()){
                String keresztnev = user.getString("first_name");
                String vezeteknev = user.getString("last_name");

                String nev = vezeteknev + " " + keresztnev;

                userslist.add(new Users(user.getString("id"), user.getString("username"), nev, user.getString("password"), user.getString("email"), user.getString("phone_number"), user.getString("registration_date"), user.getString("rank")));
            }

            id_tc.setCellValueFactory(new PropertyValueFactory<>("id"));
            uname_tc.setCellValueFactory(new PropertyValueFactory<>("username"));
            name_tc.setCellValueFactory(new  PropertyValueFactory<>("name"));
            pass_tc.setCellValueFactory(new  PropertyValueFactory<>("password"));
            email_tc.setCellValueFactory(new PropertyValueFactory<>("email"));
            phone_tc.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
            regdat_tc.setCellValueFactory(new PropertyValueFactory<>("registration_date"));
            rank_tc.setCellValueFactory(new PropertyValueFactory<>("rank"));

            users_table.setItems(userslist);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

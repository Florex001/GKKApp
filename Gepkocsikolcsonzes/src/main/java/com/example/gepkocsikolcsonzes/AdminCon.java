package com.example.gepkocsikolcsonzes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

    @FXML
    private TextField id_tf;

    @FXML
    private TextField username_tf;

    @FXML
    private TextField name_tf;
    @FXML
    private TextField pass_tf;

    @FXML
    private TextField email_tf;
    @FXML
    private TextField phone_tf;
    @FXML
    private TextField regdate_tf;
    @FXML
    private TextField rank_tf;

    @FXML
    private TextField search_tf;

    int index = -1;

    ObservableList<Users> userslist = FXCollections.observableArrayList();

    @FXML
    private void users_selected(){
        index = users_table.getSelectionModel().getSelectedIndex();
        if (index <= -1){
            return;
        }

        id_tf.setText(id_tc.getCellData(index).toString());
        username_tf.setText(uname_tc.getCellData(index).toString());
        name_tf.setText(name_tc.getCellData(index).toString());
        pass_tf.setText(pass_tc.getCellData(index).toString());
        email_tf.setText(email_tc.getCellData(index).toString());
        phone_tf.setText(phone_tc.getCellData(index).toString());
        regdate_tf.setText(regdat_tc.getCellData(index).toString());
        rank_tf.setText(rank_tc.getCellData(index).toString());
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

    @FXML
    private void search_btn(){
        String keres = search_tf.getText();

        boolean volt = false;
        for (Users elem : userslist){
           if (elem.username.equals(keres) || elem.name.equals(keres) || elem.email.equals(keres)){
               index = Integer.parseInt(elem.id) -1;
               volt = true;
           }
        }

        if (!volt) {
            Alert error_alert = new Alert(Alert.AlertType.ERROR);
            error_alert.setTitle("Hiba");
            error_alert.setHeaderText("Nem található ezekkel az adatokkal felhasználó!");
            error_alert.show();
            search_tf.setText("");
            search_tf.setPromptText("Keresés felhasználónév, Név vagy Email alapján...");
        }else{
            id_tf.setText(id_tc.getCellData(index).toString());
            username_tf.setText(uname_tc.getCellData(index).toString());
            name_tf.setText(name_tc.getCellData(index).toString());
            pass_tf.setText(pass_tc.getCellData(index).toString());
            email_tf.setText(email_tc.getCellData(index).toString());
            phone_tf.setText(phone_tc.getCellData(index).toString());
            regdate_tf.setText(regdat_tc.getCellData(index).toString());
            rank_tf.setText(rank_tc.getCellData(index).toString());
            search_tf.setText("");
            search_tf.setPromptText("Keresés felhasználónév, Név vagy Email alapján...");
        }

        index = -1;
    }//keresni lehet felhasználónév/email/név szerint a felhasználók között

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
    }//megjeleniti a felhasználó minden adatát egy táblában
}

package com.example.gepkocsikolcsonzes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class AdminCon implements Initializable {

    public static int messid;
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
    @FXML
    private TableView<Messages> message_table;
    @FXML
    private TableColumn<Messages, String> id_message_tc;
    @FXML
    private TableColumn<Messages, String> messages_tc;
    @FXML
    private TableColumn<Messages, String> name_messages_tc;
    @FXML
    private TableColumn<Messages, String> user_name_tc;
    @FXML
    private TableView<Messages> message_table1;
    @FXML
    private TableColumn<Messages, String> id_message_tc1;
    @FXML
    private TableColumn<Messages, String> messages_tc1;
    @FXML
    private TableColumn<Messages, String> name_messages_tc1;
    @FXML
    private TableColumn<Messages, String> user_name_tc1;
    @FXML
    private TableView<Messages> message_table2;
    @FXML
    private TableColumn<Messages, String> id_message_tc2;
    @FXML
    private TableColumn<Messages, String> messages_tc2;
    @FXML
    private TableColumn<Messages, String> name_messages_tc2;
    @FXML
    private TableColumn<Messages, String> user_name_tc2;
    @FXML
    private TextArea message_ta;

    int index = -1;

    ObservableList<Users> userslist = FXCollections.observableArrayList();
    ObservableList<Messages> sendmessages = FXCollections.observableArrayList();
    ObservableList<Messages> confirmmessages = FXCollections.observableArrayList();
    ObservableList<Messages> badmessages = FXCollections.observableArrayList();

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
    }//kiv??lasztott felhaszn??l??nak az indexe elt??rol??dik
    @FXML
    private void selected_message(){
        index = message_table.getSelectionModel().getSelectedIndex();
        if (index <= -1){
            return;
        }

        messid = Integer.parseInt(id_message_tc.getCellData(index));
        message_ta.setText(messages_tc.getCellData(index).toString());
    }//a kiv??lasztott ??zenetet megjelen??ti a text areaban
    @FXML
    private void back_btn(){
        ProfileCon.admin_window.close();
        LoginCon.welcome_window.show();
    }//visszal??p??s a welcome pagera

    @FXML
    private void mainpage_BTN(){
        mainpage_fx.setVisible(true);
        message_fx.setVisible(false);
        users_fx.setVisible(false);
    }//main page anchor pane t??lt??dik csak be

    @FXML
    private void message_btn(){
        mainpage_fx.setVisible(false);
        message_fx.setVisible(true);
        users_fx.setVisible(false);
    }//message page anchor pane t??lt??dik csak be

    @FXML
    private void users_btn(){
        mainpage_fx.setVisible(false);
        message_fx.setVisible(false);
        users_fx.setVisible(true);
    }//users page anchor pane t??lt??dik csak be

    @FXML
    private void search_btn(){
        String keres = search_tf.getText();

        boolean volt = false;
        for (Users elem : userslist){
           if (elem.username.equals(keres) || elem.name.equals(keres) || elem.email.equals(keres)){
               index = Integer.parseInt(String.valueOf(elem.sorszam));
               volt = true;
           }
        }

        if (!volt) {
            Alert error_alert = new Alert(Alert.AlertType.ERROR);
            error_alert.setTitle("Hiba");
            error_alert.setHeaderText("Nem tal??lhat?? ezekkel az adatokkal felhaszn??l??!");
            error_alert.show();
            search_tf.setText("");
            search_tf.setPromptText("Keres??s felhaszn??l??n??v, N??v vagy Email alapj??n...");
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
            search_tf.setPromptText("Keres??s felhaszn??l??n??v, N??v vagy Email alapj??n...");
        }

        index = -1;
    }//keresni lehet felhaszn??l??n??v/email/n??v szerint a felhaszn??l??k k??z??tt

    @FXML
    private void user_add_btn(){
        Date datum = new Date();
        DateFormat forma =new SimpleDateFormat("yyyy-MM-dd");

        String username = username_tf.getText();
        String name = name_tf.getText();
        String password = pass_tf.getText();
        String email = email_tf.getText();
        String phone_num = phone_tf.getText();
        String date = forma.format(datum);
        String rank = rank_tf.getText();

        String[] name_destroy = name.split(" ");
        String first_name = name_destroy[0];
        String last_name = name_destroy[1];

        boolean volt = false;
        for (Users elem : userslist) {
            if (elem.username.equals(username)) {
                volt = true;
            }
        }

        if (volt){
            Alert error_alert = new Alert(Alert.AlertType.ERROR);
            error_alert.setTitle("Hiba");
            error_alert.setHeaderText("Ez a felhaszn??l??n??v m??r foglalt.");
            error_alert.show();
            username_tf.setText("");
        }else {

            if (rank.equals("admin") || rank.equals("worker") || rank.equals("user")) {
                try {
                    Connection con = DBConnector.getConnection();

                    String add_sql = "INSERT INTO `user` (`id`, `username`, `first_name`, `last_name`, `password`, `email`, `phone_number`, `registration_date`, `rank`)" +
                            " VALUES (NULL, '" + username + "', '" + last_name + "', '" + first_name + "', '" + password + "', '" + email + "', '" + phone_num + "', '" + date + "', '" + rank + "');";

                    if (username.equals("") || name.equals("") || password.equals("") || email.equals("") || phone_num.equals("") || rank.equals("")) {
                        Alert error_alert = new Alert(Alert.AlertType.CONFIRMATION);
                        error_alert.setTitle("Hiba");
                        error_alert.setHeaderText("T??ltse ki az ??sszes mez??t!");
                        error_alert.initOwner(ProfileCon.admin_window);
                        error_alert.show();
                    } else {
                        PreparedStatement pst = null;

                        pst = con.prepareStatement(add_sql);
                        pst.execute();
                        users_table.getItems().clear();
                        usertable();

                        Alert apply_del_alert = new Alert(Alert.AlertType.CONFIRMATION);
                        apply_del_alert.setTitle("Sikeres felhaszn??l?? l??trehoz??s..");
                        apply_del_alert.setHeaderText("Sikeresen l??trehozta a/az " + username + " nev?? felhaszn??l??t!");
                        apply_del_alert.initOwner(ProfileCon.admin_window);
                        apply_del_alert.show();

                        id_tf.setText("");
                        username_tf.setText("");
                        name_tf.setText("");
                        pass_tf.setText("");
                        email_tf.setText("");
                        phone_tf.setText("");
                        regdate_tf.setText("");
                        rank_tf.setText("");

                    }

                } catch (SQLException e) {
                    Alert error_update_alert = new Alert(Alert.AlertType.ERROR);
                    error_update_alert.setTitle("Hiba");
                    error_update_alert.setHeaderText("Az adatb??zis nem tud csatlakozni!");
                    error_update_alert.setContentText("Pr??b??lja ??jra!");
                    error_update_alert.initOwner(ProfileCon.admin_window);
                    error_update_alert.show();
                }
            }else {
                Alert error_update_alert = new Alert(Alert.AlertType.ERROR);
                error_update_alert.setTitle("Hiba");
                error_update_alert.setHeaderText("Nem megfelel?? jogos??ltts??got adott meg.");
                error_update_alert.setContentText("Jogosults??gok: user, worker, admin");
                error_update_alert.initOwner(ProfileCon.admin_window);
                error_update_alert.show();
            }
        }


    }//felhaszn??l??t lehet hozz??adni

    @FXML
    private void update_user_btn(){
        String id = id_tf.getText();
        String username = username_tf.getText();
        String name = name_tf.getText();
        String password = pass_tf.getText();
        String email = email_tf.getText();
        String phone_num = phone_tf.getText();
        String rank = rank_tf.getText();

        String[] name_destroy = name.split(" ");
        String first_name = name_destroy[0];
        String last_name = name_destroy[1];
        if ((rank.equals("worker"))||(rank.equals("admin"))||(rank.equals("user"))) {

            String update_user = "UPDATE `user` SET `username` = '" + username + "', `first_name` = '" + last_name + "', `last_name` = '" + first_name + "', `email` = '" + email + "', `phone_number` = '" + phone_num + "', `rank` = '" + rank + "' WHERE `user`.`id` = " + id + ";";

            PreparedStatement pst = null;

            try {
                Connection con = DBConnector.getConnection();

                pst = con.prepareStatement(update_user);
                pst.execute();
                users_table.getItems().clear();
                usertable();

                Alert done_update_alert = new Alert(Alert.AlertType.CONFIRMATION);
                done_update_alert.setTitle("Sikeres friss??t??s!");
                done_update_alert.setHeaderText("Az adatb??zis sikeresen friss??lt!");
                done_update_alert.initOwner(ProfileCon.admin_window);
                done_update_alert.show();

            } catch (SQLException e) {
                Alert error_update_alert = new Alert(Alert.AlertType.CONFIRMATION);
                error_update_alert.setTitle("Hiba");
                error_update_alert.setHeaderText("Az adatb??zis nem tud csatlakozni!");
                error_update_alert.setContentText("Pr??b??lja ??jra, vagy tegyen bejelent??st! +36709312755");
                error_update_alert.initOwner(ProfileCon.admin_window);
                error_update_alert.show();
            }
        }else {
            Alert error_update_alert = new Alert(Alert.AlertType.ERROR);
            error_update_alert.setTitle("Hiba");
            error_update_alert.setHeaderText("Nem megfelel?? jogos??ltts??got adott meg.");
            error_update_alert.setContentText("Jogosults??gok: user, worker, admin");
            error_update_alert.initOwner(ProfileCon.admin_window);
            error_update_alert.show();
        }

        id_tf.setText("");
        username_tf.setText("");
        name_tf.setText("");
        pass_tf.setText("");
        email_tf.setText("");
        phone_tf.setText("");
        regdate_tf.setText("");
        rank_tf.setText("");

    }//frissiteni tudja a felhaszn??l?? adatait ha az sz??ks??ges, illetve l??tez?? felhaszn??l??nak itt tud rankot adni

    @FXML
    private void user_delete(){
        String id = id_tf.getText();

        String delete_user = "DELETE FROM `user` WHERE `user`.`id` = "+id+"";

        PreparedStatement pst = null;

        try {
            Connection con = DBConnector.getConnection();

            pst = con.prepareStatement(delete_user);
            pst.execute();
            users_table.getItems().clear();
            usertable();

            Alert apply_del_alert = new Alert(Alert.AlertType.CONFIRMATION);
            apply_del_alert.setTitle("Sikeres t??rl??s.");
            apply_del_alert.setHeaderText("Sikeresen t??r??lte a felhaszn??l??t!");
            apply_del_alert.initOwner(ProfileCon.admin_window);
            apply_del_alert.show();

        } catch (SQLException e) {
            Alert error_update_alert = new Alert(Alert.AlertType.CONFIRMATION);
            error_update_alert.setTitle("Hiba");
            error_update_alert.setHeaderText("Az adatb??zis nem tud csatlakozni!");
            error_update_alert.setContentText("Pr??b??lja ??jra!");
            error_update_alert.initOwner(WelcomeCon.reservation_window);
            error_update_alert.show();
        }

        id_tf.setText("");
        username_tf.setText("");
        name_tf.setText("");
        pass_tf.setText("");
        email_tf.setText("");
        phone_tf.setText("");
        regdate_tf.setText("");
        rank_tf.setText("");

    }//t??r??lni lehet a felhaszn??l??t

    @FXML
    private void confirmmessagebtn(){
        PreparedStatement pst = null;

        try {
            Connection con = DBConnector.getConnection();

            String sql = "UPDATE `message` SET `status` = 'elfogadva' WHERE `message`.`id` = "+messid+";";

            pst = con.prepareStatement(sql);
            pst.execute();
            message_table.getItems().clear();
            sendmessagestable();
            message_table1.getItems().clear();
            confirmmessagestable();


            Alert done_update_alert = new Alert(Alert.AlertType.CONFIRMATION);
            done_update_alert.setTitle("Sikeres ??thelyez??s!");
            done_update_alert.setHeaderText(messid +" azonos??t??ju ??zenet ??thelyez??sre ker??lt az elfogadott ??zenetekhez!");
            done_update_alert.initOwner(ProfileCon.admin_window);
            done_update_alert.show();
        } catch (SQLException e) {
            Alert error_update_alert = new Alert(Alert.AlertType.CONFIRMATION);
            error_update_alert.setTitle("Hiba");
            error_update_alert.setHeaderText("Az adatb??zis nem tud csatlakozni!");
            error_update_alert.setContentText("Pr??b??lja ??jra, vagy tegyen bejelent??st! +36709312755");
            error_update_alert.initOwner(WelcomeCon.reservation_window);
            error_update_alert.show();
        }
        message_ta.setText("");
    }//ha az ??zenetet meg lehet val??s??tani akkor az elfogadott v??gleges??tett ??zenetekhez ??thelyez??dik

    @FXML
    private void badmessagebtn(){
        PreparedStatement pst = null;

        try {
            Connection con = DBConnector.getConnection();

            String sql = "UPDATE `message` SET `status` = 'elutasitva' WHERE `message`.`id` = "+messid+";";

            pst = con.prepareStatement(sql);
            pst.execute();
            message_table.getItems().clear();
            sendmessagestable();
            message_table2.getItems().clear();
            badmessagestable();


            Alert done_update_alert = new Alert(Alert.AlertType.CONFIRMATION);
            done_update_alert.setTitle("Sikeres ??thelyez??s!");
            done_update_alert.setHeaderText(messid +" azonos??t??ju ??zenet ??thelyez??sre ker??lt az elutasitott ??zenetekhez!");
            done_update_alert.initOwner(ProfileCon.admin_window);
            done_update_alert.show();
        } catch (SQLException e) {
            Alert error_update_alert = new Alert(Alert.AlertType.CONFIRMATION);
            error_update_alert.setTitle("Hiba");
            error_update_alert.setHeaderText("Az adatb??zis nem tud csatlakozni!");
            error_update_alert.setContentText("Pr??b??lja ??jra, vagy tegyen bejelent??st! +36709312755");
            error_update_alert.initOwner(WelcomeCon.reservation_window);
            error_update_alert.show();
        }
        message_ta.setText("");
    }//ha az ??zenetnek nincs val??s??galapja vagy nem megval??s??that?? k??r??s j??n akkor elutas??t??sra ker??l

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usertable();
        sendmessagestable();
        confirmmessagestable();
        badmessagestable();
        mainpage_fx.setVisible(true);
        message_fx.setVisible(false);
        users_fx.setVisible(false);
    }

    private void usertable(){
        try {
            Connection con = DBConnector.getConnection();

            String usersDB = "SELECT id , username , first_name , last_name , md5(password) , email , phone_number , registration_date , rank FROM `user`;";

            ResultSet user = con.createStatement().executeQuery(usersDB);

            int ssz = -1;

            while (user.next()){
                String keresztnev = user.getString("first_name");
                String vezeteknev = user.getString("last_name");

                String nev = vezeteknev + " " + keresztnev;

                ssz++;

                userslist.add(new Users(ssz, user.getString("id"), user.getString("username"), nev, user.getString("md5(password)"), user.getString("email"), user.getString("phone_number"), user.getString("registration_date"), user.getString("rank")));
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
    }//megjeleniti a felhaszn??l?? minden adat??t egy t??bl??ban

    private void sendmessagestable(){
        String sendmessagessql = "SELECT * FROM `message` WHERE status = 'k??ldve';";

        try {
            Connection con = DBConnector.getConnection();

            ResultSet newmessages = con.createStatement().executeQuery(sendmessagessql);

            while (newmessages.next()){
                sendmessages.add(new Messages(newmessages.getString("id"), newmessages.getString("user_name"), newmessages.getString("name"),newmessages.getString("message"), newmessages.getString("status")));
            }

            id_message_tc.setCellValueFactory(new PropertyValueFactory<>("id"));
            user_name_tc.setCellValueFactory(new PropertyValueFactory<>("user_name"));
            name_messages_tc.setCellValueFactory(new PropertyValueFactory<>("name"));
            messages_tc.setCellValueFactory(new PropertyValueFactory<>("message"));

            message_table.setItems(sendmessages);



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }//megjelennek a be??rkez?? ??zenetek

    private void confirmmessagestable(){
        String confirmmessagessql = "SELECT * FROM `message` WHERE status = 'elfogadva';";

        try {
            Connection con = DBConnector.getConnection();

            ResultSet confirm = con.createStatement().executeQuery(confirmmessagessql);

            while (confirm.next()){
                confirmmessages.add(new Messages(confirm.getString("id"), confirm.getString("user_name"), confirm.getString("name"),confirm.getString("message"), confirm.getString("status")));
            }

            id_message_tc1.setCellValueFactory(new PropertyValueFactory<>("id"));
            user_name_tc1.setCellValueFactory(new PropertyValueFactory<>("user_name"));
            name_messages_tc1.setCellValueFactory(new PropertyValueFactory<>("name"));
            messages_tc1.setCellValueFactory(new PropertyValueFactory<>("message"));

            message_table1.setItems(confirmmessages);



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }//megjelennek a j??v??hagyott ??zenetek

    private void badmessagestable(){
        String badmessagessql = "SELECT * FROM `message` WHERE status = 'elutasitva';";

        try {
            Connection con = DBConnector.getConnection();

            ResultSet bad = con.createStatement().executeQuery(badmessagessql);

            while (bad.next()){
                badmessages.add(new Messages(bad.getString("id"), bad.getString("user_name"), bad.getString("name"),bad.getString("message"), bad.getString("status")));
            }

            id_message_tc2.setCellValueFactory(new PropertyValueFactory<>("id"));
            user_name_tc2.setCellValueFactory(new PropertyValueFactory<>("user_name"));
            name_messages_tc2.setCellValueFactory(new PropertyValueFactory<>("name"));
            messages_tc2.setCellValueFactory(new PropertyValueFactory<>("message"));

            message_table2.setItems(badmessages);



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }//megjelennek az elutas??tott ??zenetek
}

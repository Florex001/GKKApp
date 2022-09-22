package com.example.gepkocsikolcsonzes;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageCon {

    @FXML
    private TextArea message_ta;


    @FXML
    private void send_btn(){
        String valid_name = "";
        String message = message_ta.getText();

        PreparedStatement pst = null;

        try {
            Connection con = DBConnector.getConnection();

            ResultSet name = con.createStatement().executeQuery("SELECT first_name, last_name FROM `user` WHERE (username = '"+ LoginCon.uName +"');");

            if (name.next()){
                String firstname = name.getString("first_name");
                String lastname = name.getString("last_name");

                valid_name = firstname + " " + lastname;
            }//lekérdezés eredményét elttároljuk változóban

            String send_message = "INSERT INTO `message` (`id`, `user_name`, `name`, `message`, `status`) VALUES (NULL, '"+LoginCon.uName+"', '"+valid_name+"', '"+message+"', 'küldve');";

            if (message.equals("")){
                Alert error_alert = new Alert(Alert.AlertType.CONFIRMATION);
                error_alert.setTitle("Hiba");
                error_alert.setHeaderText("Töltse ki a mezőt!");
                error_alert.initOwner(WelcomeCon.message_window);
                error_alert.show();
            }else {
                pst = con.prepareStatement(send_message);
                pst.execute();

                Alert apply_del_alert = new Alert(Alert.AlertType.CONFIRMATION);
                apply_del_alert.setTitle("Sikeres üzenet küldés.");
                apply_del_alert.setHeaderText("Sikeresen elküldte az üzenetet.");
                apply_del_alert.initOwner(WelcomeCon.message_window);
                apply_del_alert.show();
            }

        } catch (SQLException e) {
            Alert no_connection = new Alert(Alert.AlertType.ERROR);
            no_connection.setHeaderText("Sajnos nem tudunk az adatbázishoz csatlakozni.");
            no_connection.setContentText("Ellenőrizze az internet kapcsolatot.");
            no_connection.initOwner(GKKApp.login_window);
            no_connection.show();
        }

        message_ta.setText("");
    }//üzenetet lehet vele elküldeni

}

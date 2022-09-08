package com.example.gepkocsikolcsonzes;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.w3c.dom.events.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ReservationCon implements Initializable {

    @FXML
    private TableView<Booking> booking_TW;
    @FXML
    private TableColumn<Booking, String> borrowend_TC;
    @FXML
    private TableColumn<Booking, String> borrowstart_TC;
    @FXML
    private TableColumn<Booking, String> id_TC;
    @FXML
    private TableColumn<Booking, String> idcard_TC;
    @FXML
    private TableColumn<Booking, String> price_TC;
    @FXML
    private TableColumn<Booking, String> status_TC;
    @FXML
    private TableColumn<Booking, String> uid_TC;
    @FXML
    private TableColumn<Booking, String> vehiclei_TC;

    @FXML
    private Label bookingid_lab;

    @FXML
    private Label car_lab;

    @FXML
    private Label endd_lab;

    @FXML
    private Label idc_lab;

    @FXML
    private Label name_lab;

    @FXML
    private Label price_lab;

    @FXML
    private Label start_lab;

    @FXML
    private Label status_lab;

    @FXML
    private TextField car_id_TF;

    @FXML
    private TextField end_TF;

    @FXML
    private TextField pid_TF;

    @FXML
    private TextField price_TF;

    @FXML
    private TextField start_tf;

    @FXML
    private TextField status_tf;

    int index = -1;

    ObservableList<Booking> bookinglist = FXCollections.observableArrayList();

    @FXML
    private void help_btn(){
        Alert help_alert = new Alert(Alert.AlertType.INFORMATION);
        help_alert.setTitle("Help");
        help_alert.setHeaderText("Olvass el!");
        help_alert.setContentText("Foglalás törlése: A törlés megkezdéséhez válassza ki a törölni kívánt elemet majd kattintson a Foglalás törlése gombra.\n\t\tFontos: csak akkor kattintson a Foglalás törlése gombra hogyha biztos benne hogy azt az elemet szeretné törölni!\n" +
                "Adatok módosítása: Adatok módosításához válassza ki azt a sort amelyet módosítani szeretne. A Kezdete mezőt, Vége mezőt, Szig-szám mezőt, Fizetendő mezőt illetve Állapot mező módosítására van lehetőség. Amelyiket módosítani szeretné azt írja át és kattintson az Adatok módosítása gombra.\n\t\t Fontos: Ha a foglaló másik gépjárművet szeretne ahoz le kell mondani a foglalást, és új foglalást indítani.\n" +
                "Foglalás hozzáadása: A foglalás hozzáadásához az összes mezőt ki kell töltenie! A Gépjármű azonosító-t a főoldalon a Személygépjárművek, Lakóautók vagy Teherautóknál tudja megnézni. A többi mezőt a foglalást kérő személy adja meg az által töltse ki! Fizetendő mezőt ön adja meg bérelt napok száma szorozva gépjármű bérlés napi díjjával. Az állapothoz 3 variációt adhat meg: Foglalva --> Magánszemélyeknek, tartos_berlet --> Magánszemélyeknek hosszútávra, ceges_berlet --> Cégeknek hosszútávra. Ha minden adat egyezik és töbször átnézte kattintson a Foglalás hozzáadása gombra.\n" +
                "\t\tFontos: Az Állapot megadásakor figyeljen a kis és nagy betűkre illetve a _ szimbólumra!");
        help_alert.show();
    }

    @FXML
    private void back_btn(){
        WelcomeCon.reservation_window.close();
        LoginCon.welcome_window.show();
    }//vissza a főoldalra

    @FXML
    private void booking_selected() {
        index = booking_TW.getSelectionModel().getSelectedIndex();
        if (index <= -1){
            return;
        }

        bookingid_lab.setText("Foglalás azonosító: " + id_TC.getCellData(index).toString());
        name_lab.setText("Név: " + uid_TC.getCellData(index).toString());
        car_lab.setText("Gépjármű: " + vehiclei_TC.getCellData(index).toString());
        start_lab.setText("Kezdete: " + borrowstart_TC.getCellData(index).toString());
        endd_lab.setText("Vége: " + borrowend_TC.getCellData(index).toString());
        idc_lab.setText("Szig-szám: " + idcard_TC.getCellData(index).toString());
        price_lab.setText("Fizetendő: " + price_TC.getCellData(index).toString() + " Ft.");
        status_lab.setText("Állapot: " + status_TC.getCellData(index).toString());
        start_tf.setText(borrowstart_TC.getCellData(index).toString());
        end_TF.setText(borrowend_TC.getCellData(index).toString());
        pid_TF.setText(idcard_TC.getCellData(index).toString());
        price_TF.setText(price_TC.getCellData(index).toString());
        status_tf.setText(status_TC.getCellData(index).toString());

    }//booking táblábol való kiválasztás

    @FXML
    private void booking_add(){//a dolgozó e programkód által tud majd hozzá adni helyben foglalást
        String car_id_add  = car_id_TF.getText();
        String start_add = start_tf.getText();
        String end_add = end_TF.getText();
        String pid_add = pid_TF.getText();
        String price_add = price_TF.getText();
        String status_add = status_tf.getText();

        if (car_id_add.equals("") || start_add.equals("") || end_add.equals("") || pid_add.equals("") || price_add.equals("") || status_add.equals("")){
            Alert error_alert = new Alert(Alert.AlertType.CONFIRMATION);
            error_alert.setTitle("Hiba");
            error_alert.setHeaderText("Töltse ki az össze mezőt!");
            error_alert.initOwner(WelcomeCon.reservation_window);
            error_alert.show();
        }else{
            PreparedStatement pst = null;
            try {
                Connection con = DBConnector.getConnection();

                String sql = "INSERT INTO `bookings` (`id`, `user_id`, `borrowed_vehicle_id`, `borrow_start`, `borrow_end`, `indenty_card_number`, `price`, `status`) VALUES (NULL, '4', '"+ car_id_add +"', '"+start_add+"', '"+ end_add +"', '"+ pid_add +"', '"+ price_add +"', '"+ status_add +"');";

                pst = con.prepareStatement(sql);
                pst.execute();
                booking_TW.getItems().clear();
                bookingtable();

            } catch (SQLException e) {
                Alert error_update_alert = new Alert(Alert.AlertType.CONFIRMATION);
                error_update_alert.setTitle("Hiba");
                error_update_alert.setHeaderText("Az adatbázis nem tud csatlakozni!");
                error_update_alert.setContentText("Próbálja újra!");
                error_update_alert.initOwner(WelcomeCon.reservation_window);
                error_update_alert.show();
            }
        }
        car_id_TF.setText("");
        start_tf.setText("");
        end_TF.setText("");
        pid_TF.setText("");
        price_TF.setText("");
        status_tf.setText("");
    }

    @FXML
    private void booking_del(){
        index = booking_TW.getSelectionModel().getSelectedIndex();
        if (index <= -1){
            return;
        }

        String car_id_del = id_TC.getCellData(index).toString();

        PreparedStatement pst = null;

        try {
            Connection con = DBConnector.getConnection();

            String sql = "DELETE FROM bookings WHERE `bookings`.`id` = "+ car_id_del +";";

            pst = con.prepareStatement(sql);
            pst.execute();
            booking_TW.getItems().clear();
            bookingtable();

            Alert apply_del_alert = new Alert(Alert.AlertType.CONFIRMATION);
            apply_del_alert.setTitle("Sikeres törlés.");
            apply_del_alert.setHeaderText("Sikeresen törölte az adatokat!");
            apply_del_alert.initOwner(WelcomeCon.reservation_window);
            apply_del_alert.show();

        } catch (SQLException e) {
            Alert error_update_alert = new Alert(Alert.AlertType.CONFIRMATION);
            error_update_alert.setTitle("Hiba");
            error_update_alert.setHeaderText("Az adatbázis nem tud csatlakozni!");
            error_update_alert.setContentText("Próbálja újra!");
            error_update_alert.initOwner(WelcomeCon.reservation_window);
            error_update_alert.show();
        }

    }//foglalás törlése

    @FXML
    private void booking_update(){
        index = booking_TW.getSelectionModel().getSelectedIndex();
        if (index <= -1){
            return;
        }

        PreparedStatement pst = null;

        String id = id_TC.getCellData(index).toString();
        String start = start_tf.getText();
        String end = end_TF.getText();
        String pid = pid_TF.getText();
        String price = price_TF.getText();
        String status = status_tf.getText();

        try {
            Connection con = DBConnector.getConnection();

            String sql = "UPDATE `bookings` SET `borrow_start` = '"+start+"', `borrow_end` = '"+end+"', `indenty_card_number` = '"+pid+"', `price` = '"+price+"', `status` = '"+status+"' WHERE `bookings`.`id` = "+id+";";

            pst = con.prepareStatement(sql);
            pst.execute();
            booking_TW.getItems().clear();
            bookingtable();

            Alert done_update_alert = new Alert(Alert.AlertType.CONFIRMATION);
            done_update_alert.setTitle("Sikeres frissítés!");
            done_update_alert.setHeaderText("Az adatbázis sikeresen frissült!");
            done_update_alert.initOwner(WelcomeCon.reservation_window);
            done_update_alert.show();

        } catch (SQLException e) {
            Alert error_update_alert = new Alert(Alert.AlertType.CONFIRMATION);
            error_update_alert.setTitle("Hiba");
            error_update_alert.setHeaderText("Az adatbázis nem tud csatlakozni!");
            error_update_alert.setContentText("Próbálja újra, vagy tegyen bejelentést! +36709312755");
            error_update_alert.initOwner(WelcomeCon.reservation_window);
            error_update_alert.show();
        }
    }//Adatokat lehet módosítani

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bookingtable();
    }

    private void bookingtable(){//tábla megjelenítéséhez szükséges információk lekérdezése
        try {
            Connection con = DBConnector.getConnection();

            ResultSet booking = con.createStatement().executeQuery("SELECT * FROM `bookings`");



            while (booking.next()){
                String userid = booking.getString("user_id");
                ResultSet user_name = con.createStatement().executeQuery("SELECT first_name, last_name FROM `user` WHERE id = '"+ userid +"';");
                if (user_name.next()){
                    String fname = user_name.getString("first_name");
                    String lname = user_name.getString("last_name");
                    String name = lname + " " + fname;

                    String vehicleid = booking.getString("borrowed_vehicle_id");

                    ResultSet vehicle_name = con.createStatement().executeQuery("SELECT car FROM `vehicles` WHERE id = "+ vehicleid +";");

                    if (vehicle_name.next()){
                        String vname = vehicle_name.getString("car");
                        bookinglist.add(new Booking(booking.getString("id"), name, vname, booking.getString("borrow_start"), booking.getString("borrow_end"), booking.getString("indenty_card_number"), booking.getString("price"), booking.getString("status")));

                    }
                }

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        id_TC.setCellValueFactory(new PropertyValueFactory<>("id"));
        uid_TC.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        vehiclei_TC.setCellValueFactory(new PropertyValueFactory<>("borrowed_vehicle_id"));
        borrowstart_TC.setCellValueFactory(new PropertyValueFactory<>("borrow_start"));
        borrowend_TC.setCellValueFactory(new PropertyValueFactory<>("borrow_end"));
        idcard_TC.setCellValueFactory(new PropertyValueFactory<>("indenty_card_number"));
        price_TC.setCellValueFactory(new PropertyValueFactory<>("price"));
        status_TC.setCellValueFactory(new PropertyValueFactory<>("status"));

        booking_TW.setItems(bookinglist);

    }
}

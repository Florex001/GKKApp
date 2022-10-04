package com.example.gepkocsikolcsonzes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private TableColumn<Booking, String> uid_TC;
    @FXML
    private TableColumn<Booking, String> vehiclei_TC;

    @FXML
    private TableView<Booking> booking_TW1;
    @FXML
    private TableColumn<Booking, String> borrowend_TC1;
    @FXML
    private TableColumn<Booking, String> borrowstart_TC1;
    @FXML
    private TableColumn<Booking, String> id_TC1;
    @FXML
    private TableColumn<Booking, String> idcard_TC1;
    @FXML
    private TableColumn<Booking, String> price_TC1;
    @FXML
    private TableColumn<Booking, String> uid_TC1;
    @FXML
    private TableColumn<Booking, String> vehiclei_TC1;

    @FXML
    private TableView<Booking> booking_TW11;
    @FXML
    private TableColumn<Booking, String> borrowend_TC11;
    @FXML
    private TableColumn<Booking, String> borrowstart_TC11;
    @FXML
    private TableColumn<Booking, String> id_TC11;
    @FXML
    private TableColumn<Booking, String> idcard_TC11;
    @FXML
    private TableColumn<Booking, String> price_TC11;
    @FXML
    private TableColumn<Booking, String> uid_TC11;
    @FXML
    private TableColumn<Booking, String> vehiclei_TC11;

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
    private TextField searchbooking_tf;

    @FXML
    private TextField searchbookingnext_tf;
    @FXML
    private TextField searchbookingfinish_tf1;

    int index = -1;

    ObservableList<Booking> bookinglist = FXCollections.observableArrayList();
    ObservableList<Booking> bookinglist2 = FXCollections.observableArrayList();
    ObservableList<Booking> bookinglist3 = FXCollections.observableArrayList();

    @FXML
    private void help_btn(){
        Alert help_alert = new Alert(Alert.AlertType.INFORMATION);
        help_alert.setTitle("Help");
        help_alert.setHeaderText("Olvass el!");
        help_alert.setContentText("Foglalás törlése: A törlés megkezdéséhez válassza ki a törölni kívánt elemet majd kattintson a Foglalás törlése gombra.\n\t\tFontos: csak akkor kattintson a Foglalás törlése gombra hogyha biztos benne hogy azt az elemet szeretné törölni!\n" +
                "Adatok módosítása: Adatok módosításához válassza ki azt a sort amelyet módosítani szeretne. A Kezdete mezőt, Vége mezőt, Szig-szám mezőt, Fizetendő mezőt illetve Állapot mező módosítására van lehetőség. Amelyiket módosítani szeretné azt írja át és kattintson az Adatok módosítása gombra.\n\t\t Fontos: Ha a foglaló másik gépjárművet szeretne ahoz le kell mondani a foglalást, és új foglalást indítani.\n" +
                "Foglalás hozzáadása: A foglalás hozzáadásához az összes mezőt ki kell töltenie! A Gépjármű azonosító-t a főoldalon a Személygépjárműveknél tudja megnézni. A többi mezőt a foglalást kérő személy adja meg az által töltse ki! Fizetendő mezőt ön adja meg bérelt napok száma szorozva gépjármű bérlés napi díjjával.\n" +
                "\t\tFontos: Az Állapot megadásakor figyeljen a kis és nagy betűkre illetve a _ szimbólumra!");
        help_alert.show();
    }//a help button a felhasználónak nyújt segítséget

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
        idc_lab.setText("V. eng, szám: " + idcard_TC.getCellData(index).toString());
        price_lab.setText("Fizetendő: " + price_TC.getCellData(index).toString() + " Ft.");
        start_tf.setText(borrowstart_TC.getCellData(index).toString());
        end_TF.setText(borrowend_TC.getCellData(index).toString());
        pid_TF.setText(idcard_TC.getCellData(index).toString());
        price_TF.setText(price_TC.getCellData(index).toString());

    }//booking táblábol való kiválasztás

    @FXML
    private void booking_selected1() {
        index = booking_TW1.getSelectionModel().getSelectedIndex();
        if (index <= -1){
            return;
        }

        bookingid_lab.setText("Foglalás azonosító: " + id_TC1.getCellData(index).toString());
        name_lab.setText("Név: " + uid_TC1.getCellData(index).toString());
        car_lab.setText("Gépjármű: " + vehiclei_TC1.getCellData(index).toString());
        start_lab.setText("Kezdete: " + borrowstart_TC1.getCellData(index).toString());
        endd_lab.setText("Vége: " + borrowend_TC1.getCellData(index).toString());
        idc_lab.setText("V. eng, szám: " + idcard_TC1.getCellData(index).toString());
        price_lab.setText("Fizetendő: " + price_TC1.getCellData(index).toString() + " Ft.");
        start_tf.setText(borrowstart_TC1.getCellData(index).toString());
        end_TF.setText(borrowend_TC1.getCellData(index).toString());
        pid_TF.setText(idcard_TC1.getCellData(index).toString());
        price_TF.setText(price_TC1.getCellData(index).toString());

    }//az elvitt gépjárművek foglalása táblábol való kiválasztás

    @FXML
    private void booking_selected2() {
        index = booking_TW11.getSelectionModel().getSelectedIndex();
        if (index <= -1){
            return;
        }

        bookingid_lab.setText("Foglalás azonosító: " + id_TC11.getCellData(index).toString());
        name_lab.setText("Név: " + uid_TC11.getCellData(index).toString());
        car_lab.setText("Gépjármű: " + vehiclei_TC11.getCellData(index).toString());
        start_lab.setText("Kezdete: " + borrowstart_TC11.getCellData(index).toString());
        endd_lab.setText("Vége: " + borrowend_TC11.getCellData(index).toString());
        idc_lab.setText("V. eng, szám: " + idcard_TC11.getCellData(index).toString());
        price_lab.setText("Fizetendő: " + price_TC11.getCellData(index).toString() + " Ft.");
        start_tf.setText(borrowstart_TC11.getCellData(index).toString());
        end_TF.setText(borrowend_TC11.getCellData(index).toString());
        pid_TF.setText(idcard_TC11.getCellData(index).toString());
        price_TF.setText(price_TC11.getCellData(index).toString());

    }//az teljesített gépjárművek foglalása táblábol való kiválasztás

    @FXML
    private void booking_add(){
        String car_id_add  = car_id_TF.getText();
        String start_add = start_tf.getText();
        String end_add = end_TF.getText();
        String pid_add = pid_TF.getText();
        String price_add = price_TF.getText();

        if (car_id_add.equals("") || start_add.equals("") || end_add.equals("") || pid_add.equals("") || price_add.equals("")){
            Alert error_alert = new Alert(Alert.AlertType.CONFIRMATION);
            error_alert.setTitle("Hiba");
            error_alert.setHeaderText("Töltse ki az összes mezőt!");
            error_alert.initOwner(WelcomeCon.reservation_window);
            error_alert.show();
        }else{
            PreparedStatement pst = null;
            try {
                Connection con = DBConnector.getConnection();

                String sql = "INSERT INTO `bookings` (`id`, `user_id`, `borrowed_vehicle_id`, `borrow_start`, `borrow_end`, `driver_license_number`, `price`, `status`) VALUES (NULL, '4', '"+ car_id_add +"', '"+start_add+"', '"+ end_add +"', '"+ pid_add +"', '"+ price_add +"', 'foglalva');";

                String carstatusupdate = "UPDATE `vehicles` SET `status` = 'nem_elerheto' WHERE `vehicles`.`id` = "+car_id_add+"";

                pst = con.prepareStatement(carstatusupdate);
                pst.execute();

                pst = con.prepareStatement(sql);
                pst.execute();
                booking_TW.getItems().clear();
                bookingtableRES();

                Alert apply_del_alert = new Alert(Alert.AlertType.CONFIRMATION);
                apply_del_alert.setTitle("Sikeres foglalás hozzáadás..");
                apply_del_alert.setHeaderText("Sikeresen feltöltötte a foglalást!!");
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
        }
        car_id_TF.setText("");
        start_tf.setText("");
        end_TF.setText("");
        pid_TF.setText("");
        price_TF.setText("");
    }//a dolgozó e programkód által tud majd hozzá adni helyben foglalást

    @FXML
    private void booking_del(){

        String car_id_del = id_TC.getCellData(index);

        PreparedStatement pst = null;

        try {
            Connection con = DBConnector.getConnection();

            ResultSet selectbookings = con.createStatement().executeQuery("SELECT * FROM `bookings` WHERE id = "+car_id_del+";");

            if (selectbookings.next()){
                String carid = selectbookings.getString("borrowed_vehicle_id");
                String carstatusupdate = "UPDATE `vehicles` SET `status` = 'elerheto' WHERE `vehicles`.`id` = "+carid+"";

                pst = con.prepareStatement(carstatusupdate);
                pst.execute();

                String sql = "DELETE FROM bookings WHERE `bookings`.`id` = "+ car_id_del +";";

                pst = con.prepareStatement(sql);
                pst.execute();
                booking_TW.getItems().clear();
                bookingtableRES();

                Alert apply_del_alert = new Alert(Alert.AlertType.CONFIRMATION);
                apply_del_alert.setTitle("Sikeres törlés.");
                apply_del_alert.setHeaderText("Sikeresen törölte az adatokat!");
                apply_del_alert.initOwner(WelcomeCon.reservation_window);
                apply_del_alert.show();
            }

        } catch (SQLException e) {
            Alert error_update_alert = new Alert(Alert.AlertType.CONFIRMATION);
            error_update_alert.setTitle("Hiba");
            error_update_alert.setHeaderText("Az adatbázis nem tud csatlakozni!");
            error_update_alert.setContentText("Próbálja újra!");
            error_update_alert.initOwner(WelcomeCon.reservation_window);
            error_update_alert.show();
        }

        car_id_TF.setText("");
        start_tf.setText("");
        end_TF.setText("");
        pid_TF.setText("");
        price_TF.setText("");

    }//foglalás törlése

    @FXML
    private void booking_update(){

        PreparedStatement pst = null;

        String id = id_TC.getCellData(index);
        String start = start_tf.getText();
        String end = end_TF.getText();
        String pid = pid_TF.getText();
        String price = price_TF.getText();


        try {
            Connection con = DBConnector.getConnection();

            String sql = "UPDATE `bookings` SET `borrow_start` = '"+start+"', `borrow_end` = '"+end+"', `driver_license_number` = '"+pid+"', `price` = '"+price+"' WHERE `bookings`.`id` = "+id+";";

            pst = con.prepareStatement(sql);
            pst.execute();
            booking_TW.getItems().clear();
            bookingtableRES();

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

        car_id_TF.setText("");
        start_tf.setText("");
        end_TF.setText("");
        pid_TF.setText("");
        price_TF.setText("");

    }//Adatokat lehet módosítani

    @FXML
    private void booking_next_step(){

        PreparedStatement pst = null;

        String id = id_TC.getCellData(index);

        try {
            Connection con = DBConnector.getConnection();

            String elvitte = "UPDATE `bookings` SET `status` = 'elvitte' WHERE `bookings`.`id` = "+ id +";";

            pst = con.prepareStatement(elvitte);
            pst.execute();
            booking_TW.getItems().clear();
            bookingtableRES();
            booking_TW1.getItems().clear();
            bookingtableNEXT();

            Alert done_update_alert = new Alert(Alert.AlertType.CONFIRMATION);
            done_update_alert.setTitle("Sikeres áthelyezés!");
            done_update_alert.setHeaderText("A "+id+" -es/as foglalás sikeresen áthelyezésre került!");
            done_update_alert.initOwner(WelcomeCon.reservation_window);
            done_update_alert.show();

            bookingid_lab.setText("Foglalás azonosító: ");
            name_lab.setText("Név: ");
            car_lab.setText("Gépjármű: ");
            start_lab.setText("Kezdete: ");
            endd_lab.setText("Vége: ");
            idc_lab.setText("Szig-szám: ");
            price_lab.setText("Fizetendő: ");

        } catch (SQLException e) {
            Alert error_update_alert = new Alert(Alert.AlertType.CONFIRMATION);
            error_update_alert.setTitle("Hiba");
            error_update_alert.setHeaderText("Az adatbázis nem tud csatlakozni!");
            error_update_alert.setContentText("Próbálja újra, vagy tegyen bejelentést! +36709312755");
            error_update_alert.initOwner(WelcomeCon.reservation_window);
            error_update_alert.show();
        }

        car_id_TF.setText("");
        start_tf.setText("");
        end_TF.setText("");
        pid_TF.setText("");
        price_TF.setText("");

    }//A foglalások fül alatti táblából áthelyezzük az adott foglalást az elvitte fül alatti táblába

    @FXML
    private void foglalt_tab(){
        bookingid_lab.setText("Foglalás azonosító: ");
        name_lab.setText("Név: ");
        car_lab.setText("Gépjármű: ");
        start_lab.setText("Kezdete: ");
        endd_lab.setText("Vége: ");
        idc_lab.setText("Szig-szám: ");
        price_lab.setText("Fizetendő: ");
    }//ha megnyitja ezt a tabot akkor a labelekből kitörlődik az információ
    @FXML
    private void elvitt_tab(){
        bookingid_lab.setText("Foglalás azonosító: ");
        name_lab.setText("Név: ");
        car_lab.setText("Gépjármű: ");
        start_lab.setText("Kezdete: ");
        endd_lab.setText("Vége: ");
        idc_lab.setText("Szig-szám: ");
        price_lab.setText("Fizetendő: ");
    }//ha megnyitja ezt a tabot akkor a labelekből kitörlődik az információ és megjelenik egy gomb a baloldali sávban
    @FXML
    private void finish_tab(){
        bookingid_lab.setText("Foglalás azonosító: ");
        name_lab.setText("Név: ");
        car_lab.setText("Gépjármű: ");
        start_lab.setText("Kezdete: ");
        endd_lab.setText("Vége: ");
        idc_lab.setText("Szig-szám: ");
        price_lab.setText("Fizetendő: ");
    }//ha megnyitja ezt a tabot akkor a labelekből kitörlődik az információ

    @FXML
    private void booking_finish(){

        PreparedStatement pst = null;

        String id = id_TC1.getCellData(index);

        try {
            Connection con = DBConnector.getConnection();

            String finish = "UPDATE `bookings` SET `status` = 'teljesitve' WHERE `bookings`.`id` = "+ id +";";
            String carid = "SELECT borrowed_vehicle_id FROM `bookings` WHERE id = "+id+";";

            ResultSet cars = con.createStatement().executeQuery(carid);

            if (cars.next()){
                String car = cars.getString("borrowed_vehicle_id");

                String statusupdate = "UPDATE `vehicles` SET `status` = 'elerheto' WHERE `vehicles`.`id` = "+car+";";

                pst = con.prepareStatement(statusupdate);
                pst.execute();
            }

            pst = con.prepareStatement(finish);
            pst.execute();
            booking_TW1.getItems().clear();
            bookingtableNEXT();
            booking_TW11.getItems().clear();
            bookingtableFINISH();

            Alert done_update_alert = new Alert(Alert.AlertType.CONFIRMATION);
            done_update_alert.setTitle("Sikeres áthelyezés!");
            done_update_alert.setHeaderText("A "+id+" -es/as foglalás sikeresen áthelyezésre került!");
            done_update_alert.initOwner(WelcomeCon.reservation_window);
            done_update_alert.show();

            bookingid_lab.setText("Foglalás azonosító: ");
            name_lab.setText("Név: ");
            car_lab.setText("Gépjármű: ");
            start_lab.setText("Kezdete: ");
            endd_lab.setText("Vége: ");
            idc_lab.setText("Szig-szám: ");
            price_lab.setText("Fizetendő: ");

        } catch (SQLException e) {
            Alert error_update_alert = new Alert(Alert.AlertType.CONFIRMATION);
            error_update_alert.setTitle("Hiba");
            error_update_alert.setHeaderText("Az adatbázis nem tud csatlakozni!");
            error_update_alert.setContentText("Próbálja újra, vagy tegyen bejelentést! +36709312755");
            error_update_alert.initOwner(WelcomeCon.reservation_window);
            error_update_alert.show();
        }
    }//ha rákattint arra a gombra akkor az elvitt járművet amit visszahoztak átkerül a teljesített foglalásokhoz

    @FXML
    private void searchbooking_btn(){
        String keres = searchbooking_tf.getText();

        boolean volt = false;

        for (Booking elem : bookinglist){
            if (elem.driver_license_number.equals(keres) || elem.id.equals(keres) || elem.user_id.equals(keres)){
                index = elem.sorszam;
                volt = true;
            }
        }
        if (!volt) {
            Alert error_alert = new Alert(Alert.AlertType.ERROR);
            error_alert.setTitle("Hiba");
            error_alert.setHeaderText("Nem található ezekkel az adatokkal foglalás!");
            error_alert.show();
            searchbooking_tf.setText("");
            searchbooking_tf.setPromptText("Keresés foglalási azonosító, név vagy vezetői engedély szám alapján...");
        }else{
            bookingid_lab.setText("Foglalás azonosító: " + id_TC.getCellData(index).toString());
            name_lab.setText("Név: " + uid_TC.getCellData(index).toString());
            car_lab.setText("Gépjármű: " + vehiclei_TC.getCellData(index).toString());
            start_lab.setText("Kezdete: " + borrowstart_TC.getCellData(index).toString());
            endd_lab.setText("Vége: " + borrowend_TC.getCellData(index).toString());
            idc_lab.setText("V. eng, szám: " + idcard_TC.getCellData(index).toString());
            price_lab.setText("Fizetendő: " + price_TC.getCellData(index).toString() + " Ft.");
            start_tf.setText(borrowstart_TC.getCellData(index).toString());
            end_TF.setText(borrowend_TC.getCellData(index).toString());
            pid_TF.setText(idcard_TC.getCellData(index).toString());
            price_TF.setText(price_TC.getCellData(index).toString());
            searchbooking_tf.setText("");
            searchbooking_tf.setPromptText("Keresés foglalási azonosító, név vagy vezetői engedély szám alapján...");
        }
    }//foglalások között lehet keresni azonosító vagy vezetői engedély szám alapján

    @FXML
    private void searchbookingnext_btn(){
        String keres = searchbookingnext_tf.getText();

        index = -1;

        boolean volt = false;

        for (Booking elem : bookinglist2){
            if (elem.driver_license_number.equals(keres) || elem.id.equals(keres) || elem.user_id.equals(keres)){
                index = elem.sorszam;
                volt = true;
            }
        }

        if (!volt) {
            Alert error_alert = new Alert(Alert.AlertType.ERROR);
            error_alert.setTitle("Hiba");
            error_alert.setHeaderText("Nem található ezekkel az adatokkal elvitt gépjármű foglalás!");
            error_alert.show();
            searchbookingnext_tf.setText("");
            searchbookingnext_tf.setPromptText("Keresés foglalási azonosító, név vagy vezetői engedély szám alapján...");
        }else{
            bookingid_lab.setText("Foglalás azonosító: " + id_TC1.getCellData(index).toString());
            name_lab.setText("Név: " + uid_TC1.getCellData(index).toString());
            car_lab.setText("Gépjármű: " + vehiclei_TC1.getCellData(index).toString());
            start_lab.setText("Kezdete: " + borrowstart_TC1.getCellData(index).toString());
            endd_lab.setText("Vége: " + borrowend_TC1.getCellData(index).toString());
            idc_lab.setText("V. eng, szám: " + idcard_TC1.getCellData(index).toString());
            price_lab.setText("Fizetendő: " + price_TC1.getCellData(index).toString() + " Ft.");
            start_tf.setText(borrowstart_TC1.getCellData(index).toString());
            end_TF.setText(borrowend_TC1.getCellData(index).toString());
            pid_TF.setText(idcard_TC1.getCellData(index).toString());
            price_TF.setText(price_TC1.getCellData(index).toString());
            searchbookingnext_tf.setText("");
            searchbookingnext_tf.setPromptText("Keresés foglalási azonosító, név vagy vezetői engedély szám alapján...");
        }

    }//az elvitt gépjármű foglalások között lehet keresni

    @FXML
    private void searchbookingfinish_btn(){
        String keres = searchbookingfinish_tf1.getText();

        boolean volt = false;

        for (Booking elem : bookinglist3){
            if (elem.driver_license_number.equals(keres) || elem.id.equals(keres) || elem.user_id.equals(keres)){
                index = elem.sorszam;
                volt = true;
            }
        }
        if (!volt) {
            Alert error_alert = new Alert(Alert.AlertType.ERROR);
            error_alert.setTitle("Hiba");
            error_alert.setHeaderText("Nem található ezekkel az adatokkal teljesített foglalás!");
            error_alert.show();
            searchbooking_tf.setText("");
            searchbooking_tf.setPromptText("Keresés foglalási azonosító, név vagy vezetői engedély szám alapján...");
        }else{
            bookingid_lab.setText("Foglalás azonosító: " + id_TC11.getCellData(index).toString());
            name_lab.setText("Név: " + uid_TC11.getCellData(index).toString());
            car_lab.setText("Gépjármű: " + vehiclei_TC11.getCellData(index).toString());
            start_lab.setText("Kezdete: " + borrowstart_TC11.getCellData(index).toString());
            endd_lab.setText("Vége: " + borrowend_TC11.getCellData(index).toString());
            idc_lab.setText("V. eng, szám: " + idcard_TC11.getCellData(index).toString());
            price_lab.setText("Fizetendő: " + price_TC11.getCellData(index).toString() + " Ft.");
            start_tf.setText(borrowstart_TC11.getCellData(index).toString());
            end_TF.setText(borrowend_TC11.getCellData(index).toString());
            pid_TF.setText(idcard_TC11.getCellData(index).toString());
            price_TF.setText(price_TC11.getCellData(index).toString());
            searchbookingfinish_tf1.setText("");
            searchbookingfinish_tf1.setPromptText("Keresés foglalási azonosító, név vagy vezetői engedély szám alapján...");
        }
    }//a teljesített foglalások között lehet keresni

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bookingtableRES();
        bookingtableNEXT();
        bookingtableFINISH();
    }

    private void bookingtableRES(){//tábla megjelenítéséhez szükséges információk lekérdezése
        try {
            Connection con = DBConnector.getConnection();

            ResultSet booking = con.createStatement().executeQuery("SELECT * FROM `bookings` WHERE status = 'foglalva';");

            int bookingssz = -1;

            while (booking.next()){
                String userid = booking.getString("user_id");
                ResultSet user_name = con.createStatement().executeQuery("SELECT first_name, last_name FROM `user` WHERE id = '"+ userid +"';");

                bookingssz++;
                if (user_name.next()){
                    String fname = user_name.getString("first_name");
                    String lname = user_name.getString("last_name");
                    String name = lname + " " + fname;
                    String vname = "";

                    String vehicleid = booking.getString("borrowed_vehicle_id");

                    ResultSet vehicle_name = con.createStatement().executeQuery("SELECT car FROM `vehicles` WHERE id = "+ vehicleid +";");

                    if (vehicle_name.next()){
                         vname = vehicle_name.getString("car");

                    }
                    bookinglist.add(new Booking(booking.getString("id"), bookingssz, name, vname, booking.getString("borrow_start"), booking.getString("borrow_end"), booking.getString("driver_license_number"), booking.getString("price")));
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
        idcard_TC.setCellValueFactory(new PropertyValueFactory<>("driver_license_number"));
        price_TC.setCellValueFactory(new PropertyValueFactory<>("price"));

        booking_TW.setItems(bookinglist);

    }//foglalva fül alatt megjelenő gépjárművek

    private void bookingtableNEXT(){
        try {
            Connection con = DBConnector.getConnection();

            ResultSet booking = con.createStatement().executeQuery("SELECT * FROM `bookings` WHERE status = 'elvitte';");

            int nextssz = -1;

            while (booking.next()){
                String userid = booking.getString("user_id");
                ResultSet user_name = con.createStatement().executeQuery("SELECT first_name, last_name FROM `user` WHERE id = '"+ userid +"';");
                nextssz++;
                if (user_name.next()){
                    String fname = user_name.getString("first_name");
                    String lname = user_name.getString("last_name");
                    String name = lname + " " + fname;
                    String vname = "";

                    String vehicleid = booking.getString("borrowed_vehicle_id");

                    ResultSet vehicle_name = con.createStatement().executeQuery("SELECT car FROM `vehicles` WHERE id = "+ vehicleid +";");

                    if (vehicle_name.next()){
                        vname = vehicle_name.getString("car");

                    }
                    bookinglist2.add(new Booking(booking.getString("id"), nextssz, name, vname, booking.getString("borrow_start"), booking.getString("borrow_end"), booking.getString("driver_license_number"), booking.getString("price")));
                }

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        id_TC1.setCellValueFactory(new PropertyValueFactory<>("id"));
        uid_TC1.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        vehiclei_TC1.setCellValueFactory(new PropertyValueFactory<>("borrowed_vehicle_id"));
        borrowstart_TC1.setCellValueFactory(new PropertyValueFactory<>("borrow_start"));
        borrowend_TC1.setCellValueFactory(new PropertyValueFactory<>("borrow_end"));
        idcard_TC1.setCellValueFactory(new PropertyValueFactory<>("driver_license_number"));
        price_TC1.setCellValueFactory(new PropertyValueFactory<>("price"));

        booking_TW1.setItems(bookinglist2);
    }//elvitt járművek alatt megjelenő járművek

    private void bookingtableFINISH(){
        try {
            Connection con = DBConnector.getConnection();

            ResultSet booking = con.createStatement().executeQuery("SELECT * FROM `bookings` WHERE status = 'teljesitve';");

            int finishssz = -1;

            while (booking.next()){
                String userid = booking.getString("user_id");
                ResultSet user_name = con.createStatement().executeQuery("SELECT first_name, last_name FROM `user` WHERE id = '"+ userid +"';");
                finishssz++;
                if (user_name.next()){
                    String fname = user_name.getString("first_name");
                    String lname = user_name.getString("last_name");
                    String name = lname + " " + fname;
                    String vname = "";

                    String vehicleid = booking.getString("borrowed_vehicle_id");

                    ResultSet vehicle_name = con.createStatement().executeQuery("SELECT car FROM `vehicles` WHERE id = "+ vehicleid +";");

                    if (vehicle_name.next()){
                        vname = vehicle_name.getString("car");

                    }
                    bookinglist3.add(new Booking(booking.getString("id"),finishssz, name, vname, booking.getString("borrow_start"), booking.getString("borrow_end"), booking.getString("driver_license_number"), booking.getString("price")));
                }

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        id_TC11.setCellValueFactory(new PropertyValueFactory<>("id"));
        uid_TC11.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        vehiclei_TC11.setCellValueFactory(new PropertyValueFactory<>("borrowed_vehicle_id"));
        borrowstart_TC11.setCellValueFactory(new PropertyValueFactory<>("borrow_start"));
        borrowend_TC11.setCellValueFactory(new PropertyValueFactory<>("borrow_end"));
        idcard_TC11.setCellValueFactory(new PropertyValueFactory<>("driver_license_number"));
        price_TC11.setCellValueFactory(new PropertyValueFactory<>("price"));

        booking_TW11.setItems(bookinglist3);
    }//teljesített foglalások itt jelennek meg
}

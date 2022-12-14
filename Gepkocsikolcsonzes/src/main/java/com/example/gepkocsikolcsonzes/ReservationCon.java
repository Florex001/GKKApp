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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private void help_btn() {
        Alert help_alert = new Alert(Alert.AlertType.INFORMATION);
        help_alert.setTitle("Help");
        help_alert.setHeaderText("Olvass el!");
        help_alert.setContentText("Foglal??s t??rl??se: A t??rl??s megkezd??s??hez v??lassza ki a t??r??lni k??v??nt elemet majd kattintson a Foglal??s t??rl??se gombra.\n\t\tFontos: csak akkor kattintson a Foglal??s t??rl??se gombra hogyha biztos benne hogy azt az elemet szeretn?? t??r??lni!\n" +
                "Adatok m??dos??t??sa: Adatok m??dos??t??s??hoz v??lassza ki azt a sort amelyet m??dos??tani szeretne. A Kezdete mez??t, V??ge mez??t, Szig-sz??m mez??t, Fizetend?? mez??t illetve ??llapot mez?? m??dos??t??s??ra van lehet??s??g. Amelyiket m??dos??tani szeretn?? azt ??rja ??t ??s kattintson az Adatok m??dos??t??sa gombra.\n\t\t Fontos: Ha a foglal?? m??sik g??pj??rm??vet szeretne ahoz le kell mondani a foglal??st, ??s ??j foglal??st ind??tani.\n" +
                "Foglal??s hozz??ad??sa: A foglal??s hozz??ad??s??hoz az ??sszes mez??t ki kell t??ltenie! A G??pj??rm?? azonos??t??-t a f??oldalon a Szem??lyg??pj??rm??vekn??l tudja megn??zni. A t??bbi mez??t a foglal??st k??r?? szem??ly adja meg az ??ltal t??ltse ki! Fizetend?? mez??t ??n adja meg b??relt napok sz??ma szorozva g??pj??rm?? b??rl??s napi d??jj??val.\n" +
                "\t\tFontos: Az ??llapot megad??sakor figyeljen a kis ??s nagy bet??kre illetve a _ szimb??lumra!");
        help_alert.show();
    }//a help button a felhaszn??l??nak ny??jt seg??ts??get

    @FXML
    private void back_btn() {
        WelcomeCon.reservation_window.close();
        LoginCon.welcome_window.show();
    }//vissza a f??oldalra

    @FXML
    private void booking_selected() {
        index = booking_TW.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }

        bookingid_lab.setText("Foglal??s azonos??t??: " + id_TC.getCellData(index).toString());
        name_lab.setText("N??v: " + uid_TC.getCellData(index).toString());
        car_lab.setText("G??pj??rm??: " + vehiclei_TC.getCellData(index).toString());
        start_lab.setText("Kezdete: " + borrowstart_TC.getCellData(index).toString());
        endd_lab.setText("V??ge: " + borrowend_TC.getCellData(index).toString());
        idc_lab.setText("V. eng, sz??m: " + idcard_TC.getCellData(index).toString());
        price_lab.setText("Fizetend??: " + price_TC.getCellData(index).toString() + " Ft.");
        start_tf.setText(borrowstart_TC.getCellData(index).toString());
        end_TF.setText(borrowend_TC.getCellData(index).toString());
        pid_TF.setText(idcard_TC.getCellData(index).toString());
        price_TF.setText(price_TC.getCellData(index).toString());

    }//booking t??bl??bol val?? kiv??laszt??s

    @FXML
    private void booking_selected1() {
        index = booking_TW1.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }

        bookingid_lab.setText("Foglal??s azonos??t??: " + id_TC1.getCellData(index).toString());
        name_lab.setText("N??v: " + uid_TC1.getCellData(index).toString());
        car_lab.setText("G??pj??rm??: " + vehiclei_TC1.getCellData(index).toString());
        start_lab.setText("Kezdete: " + borrowstart_TC1.getCellData(index).toString());
        endd_lab.setText("V??ge: " + borrowend_TC1.getCellData(index).toString());
        idc_lab.setText("V. eng, sz??m: " + idcard_TC1.getCellData(index).toString());
        price_lab.setText("Fizetend??: " + price_TC1.getCellData(index).toString() + " Ft.");
        start_tf.setText(borrowstart_TC1.getCellData(index).toString());
        end_TF.setText(borrowend_TC1.getCellData(index).toString());
        pid_TF.setText(idcard_TC1.getCellData(index).toString());
        price_TF.setText(price_TC1.getCellData(index).toString());

    }//az elvitt g??pj??rm??vek foglal??sa t??bl??bol val?? kiv??laszt??s

    @FXML
    private void booking_selected2() {
        index = booking_TW11.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }

        bookingid_lab.setText("Foglal??s azonos??t??: " + id_TC11.getCellData(index).toString());
        name_lab.setText("N??v: " + uid_TC11.getCellData(index).toString());
        car_lab.setText("G??pj??rm??: " + vehiclei_TC11.getCellData(index).toString());
        start_lab.setText("Kezdete: " + borrowstart_TC11.getCellData(index).toString());
        endd_lab.setText("V??ge: " + borrowend_TC11.getCellData(index).toString());
        idc_lab.setText("V. eng, sz??m: " + idcard_TC11.getCellData(index).toString());
        price_lab.setText("Fizetend??: " + price_TC11.getCellData(index).toString() + " Ft.");
        start_tf.setText(borrowstart_TC11.getCellData(index).toString());
        end_TF.setText(borrowend_TC11.getCellData(index).toString());
        pid_TF.setText(idcard_TC11.getCellData(index).toString());
        price_TF.setText(price_TC11.getCellData(index).toString());

    }//az teljes??tett g??pj??rm??vek foglal??sa t??bl??bol val?? kiv??laszt??s

    @FXML
    private void booking_add() {
        String car_id_add = car_id_TF.getText();
        String start_add = start_tf.getText();
        String end_add = end_TF.getText();
        String pid_add = pid_TF.getText();
        String price_add = price_TF.getText();



        if (car_id_add.equals("") || start_add.equals("") || end_add.equals("") || pid_add.equals("") || price_add.equals("")) {
            Alert error_alert = new Alert(Alert.AlertType.CONFIRMATION);
            error_alert.setTitle("Hiba");
            error_alert.setHeaderText("T??ltse ki az ??sszes mez??t!");
            error_alert.initOwner(WelcomeCon.reservation_window);
            error_alert.show();
        } else {

            String pid_numbers = pid_add.substring(0, 6);

            try {
                Integer.parseInt(pid_numbers);


                System.out.println(pid_numbers.length());
                String pid_chars = pid_add.substring(6, 8);
                System.out.println(pid_chars);
                System.out.print(pid_add.length() + "" + pid_chars.length());
                if ((pid_chars.length() == 2) && (pid_add.length() == 8) || (pid_numbers.length() == 6)) {

                    try {
                        SimpleDateFormat dateInput = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = dateInput.parse(end_add);

                        try {
                            Date date1 = dateInput.parse(start_add);

                            try {
                                Integer.parseInt(price_add);
                                Integer.parseInt(car_id_add);

                                PreparedStatement pst = null;
                                try {
                                    Connection con = DBConnector.getConnection();

                                    String sql = "INSERT INTO `bookings` (`id`, `user_id`, `borrowed_vehicle_id`, `borrow_start`, `borrow_end`, `driver_license_number`, `price`, `status`) VALUES (NULL, '4', '" + car_id_add + "', '" + start_add + "', '" + end_add + "', '" + pid_add + "', '" + price_add + "', 'foglalva');";

                                    String carstatusupdate = "UPDATE `vehicles` SET `status` = 'nem_elerheto' WHERE `vehicles`.`id` = " + car_id_add + "";

                                    pst = con.prepareStatement(carstatusupdate);
                                    pst.execute();

                                    pst = con.prepareStatement(sql);
                                    pst.execute();
                                    booking_TW.getItems().clear();
                                    bookingtableRES();

                                    Alert apply_del_alert = new Alert(Alert.AlertType.CONFIRMATION);
                                    apply_del_alert.setTitle("Sikeres foglal??s hozz??ad??s..");
                                    apply_del_alert.setHeaderText("Sikeresen felt??lt??tte a foglal??st!!");
                                    apply_del_alert.initOwner(WelcomeCon.reservation_window);
                                    apply_del_alert.show();

                                } catch (SQLException e) {
                                    Alert error_update_alert = new Alert(Alert.AlertType.CONFIRMATION);
                                    error_update_alert.setTitle("Hiba");
                                    error_update_alert.setHeaderText("Az adatb??zis nem tud csatlakozni!");
                                    error_update_alert.setContentText("Pr??b??lja ??jra!");
                                    error_update_alert.initOwner(WelcomeCon.reservation_window);
                                    error_update_alert.show();
                                }
                            } catch (NumberFormatException e) {
                                Alert error_alert = new Alert(Alert.AlertType.ERROR);
                                error_alert.setTitle("Hiba");
                                error_alert.setHeaderText("Az ??rhoz meg az azonos??t??hoz csak sz??mot adhat meg!");
                                error_alert.initOwner(WelcomeCon.reservation_window);
                                error_alert.show();
                            }
                        } catch (ParseException e) {
                            Alert error_alert = new Alert(Alert.AlertType.ERROR);
                            error_alert.setTitle("Hiba");
                            error_alert.setHeaderText("A d??tumot csak ebben a form??tumban adhatja meg: ????????-hh-nn");
                            error_alert.initOwner(WelcomeCon.reservation_window);
                            error_alert.show();
                        }
                    } catch (ParseException e) {
                        Alert error_alert = new Alert(Alert.AlertType.ERROR);
                        error_alert.setTitle("Hiba");
                        error_alert.setHeaderText("A d??tumot csak ebben a form??tumban adhatja meg: ????????-hh-nn");
                        error_alert.initOwner(WelcomeCon.reservation_window);
                        error_alert.show();
                    }
                } else {
                    Alert error_alert = new Alert(Alert.AlertType.ERROR);
                    error_alert.setTitle("Hiba");
                    error_alert.setHeaderText("A vezet??ienged??lysz??ma a k??vetkez?? furm??tumunak kell lennie : 000000AA");
                    error_alert.initOwner(WelcomeCon.reservation_window);
                    error_alert.show();
                }
            } catch (NumberFormatException e) {
                Alert error_alert = new Alert(Alert.AlertType.ERROR);
                error_alert.setTitle("Hiba");
                error_alert.setHeaderText("A vezet??ienged??lysz??ma a k??vetkez?? furm??tumunak kell lennie : 000000AA");
                error_alert.initOwner(WelcomeCon.reservation_window);
                error_alert.show();
            }

        }
        car_id_TF.setText("");
        start_tf.setText("");
        end_TF.setText("");
        pid_TF.setText("");
        price_TF.setText("");
    }//a dolgoz?? e programk??d ??ltal tud majd hozz?? adni helyben foglal??st

    @FXML
    private void booking_del() {

        String car_id_del = id_TC.getCellData(index);

        PreparedStatement pst = null;


        try {
            Connection con = DBConnector.getConnection();

            ResultSet selectbookings = con.createStatement().executeQuery("SELECT * FROM `bookings` WHERE id = " + car_id_del + ";");

            if (selectbookings.next()) {
                String carid = selectbookings.getString("borrowed_vehicle_id");
                String carstatusupdate = "UPDATE `vehicles` SET `status` = 'elerheto' WHERE `vehicles`.`id` = " + carid + "";

                pst = con.prepareStatement(carstatusupdate);
                pst.execute();

                String sql = "DELETE FROM bookings WHERE `bookings`.`id` = " + car_id_del + ";";

                pst = con.prepareStatement(sql);
                pst.execute();
                booking_TW.getItems().clear();
                bookingtableRES();

                Alert apply_del_alert = new Alert(Alert.AlertType.CONFIRMATION);
                apply_del_alert.setTitle("Sikeres t??rl??s.");
                apply_del_alert.setHeaderText("Sikeresen t??r??lte az adatokat!");
                apply_del_alert.initOwner(WelcomeCon.reservation_window);
                apply_del_alert.show();
            }

        } catch (SQLException e) {
            Alert error_update_alert = new Alert(Alert.AlertType.CONFIRMATION);
            error_update_alert.setTitle("Hiba");
            error_update_alert.setHeaderText("Az adatb??zis nem tud csatlakozni!");
            error_update_alert.setContentText("Pr??b??lja ??jra!");
            error_update_alert.initOwner(WelcomeCon.reservation_window);
            error_update_alert.show();
        }

        car_id_TF.setText("");
        start_tf.setText("");
        end_TF.setText("");
        pid_TF.setText("");
        price_TF.setText("");

    }//foglal??s t??rl??se

    @FXML
    private void booking_update() {



        String id = id_TC.getCellData(index);
        String start = start_tf.getText();
        String end = end_TF.getText();
        String pid = pid_TF.getText();
        String price = price_TF.getText();


        String pid_numbers = pid.substring(0, 6);

        try {
            Integer.parseInt(pid_numbers);


            System.out.println(pid_numbers.length());
            String pid_chars = pid.substring(6, 8);
            System.out.println(pid_chars);
            System.out.print(pid.length() + "" + pid_chars.length());
            if ((pid_chars.length() == 2) && (pid.length() == 8) || (pid_numbers.length() == 6)) {

                try {
                    SimpleDateFormat dateInput = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = dateInput.parse(end);

                    try {
                        Date date1 = dateInput.parse(start);

                        try {
                            Integer.parseInt(price);
                            Integer.parseInt(id);

                            PreparedStatement pst = null;
                            try {
                                Connection con = DBConnector.getConnection();

                                String sql = "UPDATE `bookings` SET `borrow_start` = '" + start + "', `borrow_end` = '" + end + "', `driver_license_number` = '" + pid + "', `price` = '" + price + "' WHERE `bookings`.`id` = " + id + ";";

                                pst = con.prepareStatement(sql);
                                pst.execute();
                                booking_TW.getItems().clear();
                                bookingtableRES();

                                Alert done_update_alert = new Alert(Alert.AlertType.CONFIRMATION);
                                done_update_alert.setTitle("Sikeres friss??t??s!");
                                done_update_alert.setHeaderText("Az adatb??zis sikeresen friss??lt!");
                                done_update_alert.initOwner(WelcomeCon.reservation_window);
                                done_update_alert.show();

                            } catch (SQLException e) {
                                Alert error_update_alert = new Alert(Alert.AlertType.CONFIRMATION);
                                error_update_alert.setTitle("Hiba");
                                error_update_alert.setHeaderText("Az adatb??zis nem tud csatlakozni!");
                                error_update_alert.setContentText("Pr??b??lja ??jra, vagy tegyen bejelent??st! +36709312755");
                                error_update_alert.initOwner(WelcomeCon.reservation_window);
                                error_update_alert.show();
                            }

                        } catch (NumberFormatException e) {
                            Alert error_alert = new Alert(Alert.AlertType.ERROR);
                            error_alert.setTitle("Hiba");
                            error_alert.setHeaderText("Az ??rhoz meg az azonos??t??hoz csak sz??mot adhat meg!");
                            error_alert.initOwner(WelcomeCon.reservation_window);
                            error_alert.show();
                        }
                    } catch (ParseException e) {
                        Alert error_alert = new Alert(Alert.AlertType.ERROR);
                        error_alert.setTitle("Hiba");
                        error_alert.setHeaderText("A d??tumot csak ebben a form??tumban adhatja meg: ????????-hh-nn");
                        error_alert.initOwner(WelcomeCon.reservation_window);
                        error_alert.show();
                    }
                } catch (ParseException e) {
                    Alert error_alert = new Alert(Alert.AlertType.ERROR);
                    error_alert.setTitle("Hiba");
                    error_alert.setHeaderText("A d??tumot csak ebben a form??tumban adhatja meg: ????????-hh-nn");
                    error_alert.initOwner(WelcomeCon.reservation_window);
                    error_alert.show();
                }
            }else{
                Alert error_alert = new Alert(Alert.AlertType.ERROR);
                error_alert.setTitle("Hiba");
                error_alert.setHeaderText("A vezet??ienged??lysz??ma a k??vetkez?? furm??tumunak kell lennie : 000000AA");
                error_alert.initOwner(WelcomeCon.reservation_window);
                error_alert.show();
                }
        }catch (NumberFormatException e) {
            Alert error_alert = new Alert(Alert.AlertType.ERROR);
            error_alert.setTitle("Hiba");
            error_alert.setHeaderText("A vezet??ienged??lysz??ma a k??vetkez?? furm??tumunak kell lennie : 000000AA");
            error_alert.initOwner(WelcomeCon.reservation_window);
            error_alert.show();
        }



        car_id_TF.setText("");
        start_tf.setText("");
        end_TF.setText("");
        pid_TF.setText("");
        price_TF.setText("");
}
    //Adatokat lehet m??dos??tani

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
            done_update_alert.setTitle("Sikeres ??thelyez??s!");
            done_update_alert.setHeaderText("A "+id+" -es/as foglal??s sikeresen ??thelyez??sre ker??lt!");
            done_update_alert.initOwner(WelcomeCon.reservation_window);
            done_update_alert.show();

            bookingid_lab.setText("Foglal??s azonos??t??: ");
            name_lab.setText("N??v: ");
            car_lab.setText("G??pj??rm??: ");
            start_lab.setText("Kezdete: ");
            endd_lab.setText("V??ge: ");
            idc_lab.setText("Szig-sz??m: ");
            price_lab.setText("Fizetend??: ");

        } catch (SQLException e) {
            Alert error_update_alert = new Alert(Alert.AlertType.CONFIRMATION);
            error_update_alert.setTitle("Hiba");
            error_update_alert.setHeaderText("Az adatb??zis nem tud csatlakozni!");
            error_update_alert.setContentText("Pr??b??lja ??jra, vagy tegyen bejelent??st! +36709312755");
            error_update_alert.initOwner(WelcomeCon.reservation_window);
            error_update_alert.show();
        }

        car_id_TF.setText("");
        start_tf.setText("");
        end_TF.setText("");
        pid_TF.setText("");
        price_TF.setText("");

    }//A foglal??sok f??l alatti t??bl??b??l ??thelyezz??k az adott foglal??st az elvitte f??l alatti t??bl??ba

    @FXML
    private void foglalt_tab(){
        bookingid_lab.setText("Foglal??s azonos??t??: ");
        name_lab.setText("N??v: ");
        car_lab.setText("G??pj??rm??: ");
        start_lab.setText("Kezdete: ");
        endd_lab.setText("V??ge: ");
        idc_lab.setText("Szig-sz??m: ");
        price_lab.setText("Fizetend??: ");
    }//ha megnyitja ezt a tabot akkor a labelekb??l kit??rl??dik az inform??ci??
    @FXML
    private void elvitt_tab(){
        bookingid_lab.setText("Foglal??s azonos??t??: ");
        name_lab.setText("N??v: ");
        car_lab.setText("G??pj??rm??: ");
        start_lab.setText("Kezdete: ");
        endd_lab.setText("V??ge: ");
        idc_lab.setText("Szig-sz??m: ");
        price_lab.setText("Fizetend??: ");
    }//ha megnyitja ezt a tabot akkor a labelekb??l kit??rl??dik az inform??ci?? ??s megjelenik egy gomb a baloldali s??vban
    @FXML
    private void finish_tab(){
        bookingid_lab.setText("Foglal??s azonos??t??: ");
        name_lab.setText("N??v: ");
        car_lab.setText("G??pj??rm??: ");
        start_lab.setText("Kezdete: ");
        endd_lab.setText("V??ge: ");
        idc_lab.setText("Szig-sz??m: ");
        price_lab.setText("Fizetend??: ");
    }//ha megnyitja ezt a tabot akkor a labelekb??l kit??rl??dik az inform??ci??

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
            done_update_alert.setTitle("Sikeres ??thelyez??s!");
            done_update_alert.setHeaderText("A "+id+" -es/as foglal??s sikeresen ??thelyez??sre ker??lt!");
            done_update_alert.initOwner(WelcomeCon.reservation_window);
            done_update_alert.show();

            bookingid_lab.setText("Foglal??s azonos??t??: ");
            name_lab.setText("N??v: ");
            car_lab.setText("G??pj??rm??: ");
            start_lab.setText("Kezdete: ");
            endd_lab.setText("V??ge: ");
            idc_lab.setText("Szig-sz??m: ");
            price_lab.setText("Fizetend??: ");

        } catch (SQLException e) {
            Alert error_update_alert = new Alert(Alert.AlertType.CONFIRMATION);
            error_update_alert.setTitle("Hiba");
            error_update_alert.setHeaderText("Az adatb??zis nem tud csatlakozni!");
            error_update_alert.setContentText("Pr??b??lja ??jra, vagy tegyen bejelent??st! +36709312755");
            error_update_alert.initOwner(WelcomeCon.reservation_window);
            error_update_alert.show();
        }
    }//ha r??kattint arra a gombra akkor az elvitt j??rm??vet amit visszahoztak ??tker??l a teljes??tett foglal??sokhoz

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
            error_alert.setHeaderText("Nem tal??lhat?? ezekkel az adatokkal foglal??s!");
            error_alert.show();
            searchbooking_tf.setText("");
            searchbooking_tf.setPromptText("Keres??s foglal??si azonos??t??, n??v vagy vezet??i enged??ly sz??m alapj??n...");
        }else{
            bookingid_lab.setText("Foglal??s azonos??t??: " + id_TC.getCellData(index).toString());
            name_lab.setText("N??v: " + uid_TC.getCellData(index).toString());
            car_lab.setText("G??pj??rm??: " + vehiclei_TC.getCellData(index).toString());
            start_lab.setText("Kezdete: " + borrowstart_TC.getCellData(index).toString());
            endd_lab.setText("V??ge: " + borrowend_TC.getCellData(index).toString());
            idc_lab.setText("V. eng, sz??m: " + idcard_TC.getCellData(index).toString());
            price_lab.setText("Fizetend??: " + price_TC.getCellData(index).toString() + " Ft.");
            start_tf.setText(borrowstart_TC.getCellData(index).toString());
            end_TF.setText(borrowend_TC.getCellData(index).toString());
            pid_TF.setText(idcard_TC.getCellData(index).toString());
            price_TF.setText(price_TC.getCellData(index).toString());
            searchbooking_tf.setText("");
            searchbooking_tf.setPromptText("Keres??s foglal??si azonos??t??, n??v vagy vezet??i enged??ly sz??m alapj??n...");
        }
    }//foglal??sok k??z??tt lehet keresni azonos??t?? vagy vezet??i enged??ly sz??m alapj??n

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
            error_alert.setHeaderText("Nem tal??lhat?? ezekkel az adatokkal elvitt g??pj??rm?? foglal??s!");
            error_alert.show();
            searchbookingnext_tf.setText("");
            searchbookingnext_tf.setPromptText("Keres??s foglal??si azonos??t??, n??v vagy vezet??i enged??ly sz??m alapj??n...");
        }else{
            bookingid_lab.setText("Foglal??s azonos??t??: " + id_TC1.getCellData(index).toString());
            name_lab.setText("N??v: " + uid_TC1.getCellData(index).toString());
            car_lab.setText("G??pj??rm??: " + vehiclei_TC1.getCellData(index).toString());
            start_lab.setText("Kezdete: " + borrowstart_TC1.getCellData(index).toString());
            endd_lab.setText("V??ge: " + borrowend_TC1.getCellData(index).toString());
            idc_lab.setText("V. eng, sz??m: " + idcard_TC1.getCellData(index).toString());
            price_lab.setText("Fizetend??: " + price_TC1.getCellData(index).toString() + " Ft.");
            start_tf.setText(borrowstart_TC1.getCellData(index).toString());
            end_TF.setText(borrowend_TC1.getCellData(index).toString());
            pid_TF.setText(idcard_TC1.getCellData(index).toString());
            price_TF.setText(price_TC1.getCellData(index).toString());
            searchbookingnext_tf.setText("");
            searchbookingnext_tf.setPromptText("Keres??s foglal??si azonos??t??, n??v vagy vezet??i enged??ly sz??m alapj??n...");
        }

    }//az elvitt g??pj??rm?? foglal??sok k??z??tt lehet keresni

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
            error_alert.setHeaderText("Nem tal??lhat?? ezekkel az adatokkal teljes??tett foglal??s!");
            error_alert.show();
            searchbooking_tf.setText("");
            searchbooking_tf.setPromptText("Keres??s foglal??si azonos??t??, n??v vagy vezet??i enged??ly sz??m alapj??n...");
        }else{
            bookingid_lab.setText("Foglal??s azonos??t??: " + id_TC11.getCellData(index).toString());
            name_lab.setText("N??v: " + uid_TC11.getCellData(index).toString());
            car_lab.setText("G??pj??rm??: " + vehiclei_TC11.getCellData(index).toString());
            start_lab.setText("Kezdete: " + borrowstart_TC11.getCellData(index).toString());
            endd_lab.setText("V??ge: " + borrowend_TC11.getCellData(index).toString());
            idc_lab.setText("V. eng, sz??m: " + idcard_TC11.getCellData(index).toString());
            price_lab.setText("Fizetend??: " + price_TC11.getCellData(index).toString() + " Ft.");
            start_tf.setText(borrowstart_TC11.getCellData(index).toString());
            end_TF.setText(borrowend_TC11.getCellData(index).toString());
            pid_TF.setText(idcard_TC11.getCellData(index).toString());
            price_TF.setText(price_TC11.getCellData(index).toString());
            searchbookingfinish_tf1.setText("");
            searchbookingfinish_tf1.setPromptText("Keres??s foglal??si azonos??t??, n??v vagy vezet??i enged??ly sz??m alapj??n...");
        }
    }//a teljes??tett foglal??sok k??z??tt lehet keresni

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bookingtableRES();
        bookingtableNEXT();
        bookingtableFINISH();
    }

    private void bookingtableRES(){//t??bla megjelen??t??s??hez sz??ks??ges inform??ci??k lek??rdez??se
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

    }//foglalva f??l alatt megjelen?? g??pj??rm??vek

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
    }//elvitt j??rm??vek alatt megjelen?? j??rm??vek

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
    }//teljes??tett foglal??sok itt jelennek meg
}

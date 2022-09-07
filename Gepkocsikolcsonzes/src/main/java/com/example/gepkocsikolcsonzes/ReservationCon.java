package com.example.gepkocsikolcsonzes;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
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

    ObservableList<Booking> bookinglist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bookingtable();
    }

    private void bookingtable(){
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

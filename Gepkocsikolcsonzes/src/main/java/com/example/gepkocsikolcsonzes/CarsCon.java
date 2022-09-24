package com.example.gepkocsikolcsonzes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.Base64;
import java.util.ResourceBundle;

public class CarsCon implements Initializable {
    @FXML
    private Label CID_lab;

    @FXML
    private TableColumn<Cars, String> CarName_TC;

    @FXML
    private TableView<Cars> cars_TW;

    @FXML
    private TableColumn<Cars, String> dailyprice_TC;

    @FXML
    private Label dailyprice_lab;

    @FXML
    private TableColumn<Cars, String> id_TC;

    @FXML
    private TableColumn<Cars, String> info_TC;
    @FXML
    private TableColumn<Cars, Blob> image_tc;

    @FXML
    private Label info_lab;

    @FXML
    private Label name_lab;

    @FXML
    private TextArea Info_update_TA;

    @FXML
    private TextField Price_update_TF;

    @FXML
    private Button open;

    @FXML
    private ImageView carsimage;

    @FXML
    private TextField cars_name_tf;
    File file = null;

    int index = -1;

    ObservableList<Cars> carslist = FXCollections.observableArrayList();

    @FXML
    private void back_btn() {
        WelcomeCon.cars_window.close();
        LoginCon.welcome_window.show();
    }//visszalépés a welcome pagera

    @FXML
    private void cars_selected() {
        index = cars_TW.getSelectionModel().getSelectedIndex();
        if (index <= -1){
            return;
        }

        CID_lab.setText("Gépjármű azonosító: " + id_TC.getCellData(index).toString());
        name_lab.setText("Neve: " + CarName_TC.getCellData(index).toString());
        dailyprice_lab.setText("Információ: " + info_TC.getCellData(index).toString());
        info_lab.setText("Napi ára: " + dailyprice_TC.getCellData(index).toString() + "Ft.");
        Info_update_TA.setText(info_TC.getCellData(index).toString());
        Price_update_TF.setText(dailyprice_TC.getCellData(index).toString());
        cars_name_tf.setText(CarName_TC.getCellData(index));
        carsimage.setImage((Image) image_tc.getCellData(index));
    }//kiválasztott elem bekerül a labelek mellé

    @FXML
    private void help_btn() {
        Alert help_alert = new Alert(Alert.AlertType.INFORMATION);
        help_alert.setTitle("Help");
        help_alert.setHeaderText("Olvass el!");
        help_alert.setContentText("A kiválasztott gépjárműre kattintva az információ dobozban illetve az ár megváltoztatásánál\n" +
                "a szöveget/árat átírva tudja azt módosítani. Módosításnál figyeljen hogy pontosan töltse ki az adatokat\n" +
                "és csak az után kattintson a Módosítás gombra miután meggyőződött hogy minden adat jól szerepel.\n" +
                "\tFontos: Az adatokat csak akkor módosítsa ha biztos benne.");
        help_alert.show();
    }

    @FXML
    private void delete_btn(){
        String car_id_del = id_TC.getCellData(index);

        PreparedStatement pst = null;

        try {
            Connection con = DBConnector.getConnection();

            String sql = "DELETE FROM `vehicles` WHERE `vehicles`.`id` = "+ car_id_del +";";

            pst = con.prepareStatement(sql);
            pst.execute();
            cars_TW.getItems().clear();
            carstable();

            Alert apply_del_alert = new Alert(Alert.AlertType.CONFIRMATION);
            apply_del_alert.setTitle("Sikeres törlés.");
            apply_del_alert.setHeaderText("Sikeresen törölte az adatokat!");
            apply_del_alert.initOwner(WelcomeCon.cars_window);
            apply_del_alert.show();

        } catch (SQLException e) {
            Alert error_update_alert = new Alert(Alert.AlertType.CONFIRMATION);
            error_update_alert.setTitle("Hiba");
            error_update_alert.setHeaderText("Az adatbázis nem tud csatlakozni!");
            error_update_alert.setContentText("Próbálja újra!");
            error_update_alert.initOwner(WelcomeCon.cars_window);
            error_update_alert.show();
        }
        cars_name_tf.setText("");
        Info_update_TA.setText("");
        Price_update_TF.setText("");
    }

    @FXML
    private void update_btn(){
        index = cars_TW.getSelectionModel().getSelectedIndex();
        if (index <= -1){
            return;
        }

        PreparedStatement pst = null;

        String id = id_TC.getCellData(index).toString();
        String car_name = cars_name_tf.getText();
        String info = Info_update_TA.getText();
        String price = Price_update_TF.getText();


        try {
            Connection con = DBConnector.getConnection();

            String update = "UPDATE `vehicles` SET `car` = '"+car_name+"', `info` = '"+info+"', `daily_price` = '"+price+"' WHERE `vehicles`.`id` = "+id+";";


            pst = con.prepareStatement(update);
            pst.execute();
            cars_TW.getItems().clear();
            carstable();

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

        Info_update_TA.setText("");
        Price_update_TF.setText("");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        carstable();
        open.setOnAction(e->{
            chooseFile();
        });
    }

    private void carstable(){
        try {
            Connection con = DBConnector.getConnection();

            ResultSet cars = con.createStatement().executeQuery("SELECT * FROM `vehicles`");

            while (cars.next()){
                Blob blob = cars.getBlob("image");
                InputStream inputStream = blob.getBinaryStream();
                Image image = new Image(inputStream);

                carslist.add(new Cars(cars.getString("id"), cars.getString("car"), cars.getString("info"), cars.getString("daily_price"), image ));
            }


            id_TC.setCellValueFactory(new PropertyValueFactory<>("id"));
            CarName_TC.setCellValueFactory(new PropertyValueFactory<>("car"));
            info_TC.setCellValueFactory(new PropertyValueFactory<>("info"));
            dailyprice_TC.setCellValueFactory(new PropertyValueFactory<>("daily_price"));
            image_tc.setCellValueFactory(new PropertyValueFactory<>("image"));


            cars_TW.setItems(carslist);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }//lekérdezek mindent a vehicles táblából és azokat eltárolom a carslist ben és utána kiiratom a táblázatban

    private void chooseFile() {
        FileChooser filechooser = new FileChooser();
        file = filechooser.showOpenDialog(open.getScene().getWindow());

        try {
            FileInputStream fileInputStream = new FileInputStream(file);

            Image image = new Image(fileInputStream);
            carsimage.setImage(image);


        } catch (FileNotFoundException e) {
            Alert no_file = new Alert(Alert.AlertType.ERROR);
            no_file.setTitle("Error");
            no_file.setHeaderText("Nem választott ki fájlt.");
            no_file.setContentText(".JPEG, .JPG fájl tölthető fel.");
            no_file.initOwner(WelcomeCon.cars_window);
            no_file.show();
        }
    }//fájl kiválasztáshoz

}


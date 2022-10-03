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
import org.w3c.dom.events.MouseEvent;

import java.io.*;
import java.net.URL;
import java.sql.*;
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
    private TextField g_name_tf;
    @FXML
    private ImageView image_iv;
    @FXML
    private Button open_folder;
    @FXML
    private TextField searchbooking_tf;


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
        g_name_tf.setText(CarName_TC.getCellData(index));
        dailyprice_lab.setText("Információ: " + info_TC.getCellData(index).toString());
        info_lab.setText("Napi ára: " + dailyprice_TC.getCellData(index).toString() + "Ft.");
        Info_update_TA.setText(info_TC.getCellData(index).toString());
        Price_update_TF.setText(dailyprice_TC.getCellData(index).toString());
        image_iv.setImage((Image) image_tc.getCellData(index));
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
    }//segítséget nyújt


    @FXML
    private void update_btn(){

        PreparedStatement pst = null;

        String id = id_TC.getCellData(index).toString();
        String name = g_name_tf.getText();
        String info = Info_update_TA.getText();
        String price = Price_update_TF.getText();


        try {
            Connection con = DBConnector.getConnection();

            String update = "UPDATE `vehicles` SET car = '"+name+"',  `info` = '"+info+"', `daily_price` = '"+price+"' WHERE `vehicles`.`id` = "+id+";";

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
            e.printStackTrace();
        }

        Info_update_TA.setText("");
        Price_update_TF.setText("");
        g_name_tf.setText("");
        image_iv.setImage(null);

    }//frissiteni lehet vele

    @FXML
    private void addcars_btn(){
        String name = g_name_tf.getText();
        String info = Info_update_TA.getText();
        int price = Integer.parseInt(Price_update_TF.getText());
        String sql = "INSERT INTO `vehicles` (`id`, `car`, `info`, `daily_price`, `image`, `status`) VALUES (NULL, ? , ? , ? , ?, ?); ";
        PreparedStatement pst;
        FileChooser fc = new FileChooser();
        File file = fc.showOpenDialog(open_folder.getScene().getWindow());
        try {
            if (name.equals("") || info.equals("")){
                Alert error_alert = new Alert(Alert.AlertType.CONFIRMATION);
                error_alert.setTitle("Hiba");
                error_alert.setHeaderText("Töltse ki az összes mezőt!");
                error_alert.initOwner(WelcomeCon.reservation_window);
                error_alert.show();
            }else {
                Connection con = DBConnector.getConnection();
                FileInputStream fis = new FileInputStream(file);

                pst = con.prepareStatement(sql);
                pst.setString(1, name);
                pst.setString(2, info);
                pst.setInt(3, price);
                pst.setBinaryStream(4, fis, fis.available());
                pst.setString(5, "elerheto");
                pst.execute();
                Image image = new Image(fis);
                image_iv.setImage(image);
                cars_TW.getItems().clear();
                carstable();

                Alert apply_del_alert = new Alert(Alert.AlertType.CONFIRMATION);
                apply_del_alert.setTitle("Sikeres gépjármű hozzáadás..");
                apply_del_alert.setHeaderText("Ellenőrizze a hozzáadott elemet.");
                apply_del_alert.initOwner(WelcomeCon.cars_window);
                apply_del_alert.show();
            }

        } catch (SQLException | IOException e) {
            Alert error_update_alert = new Alert(Alert.AlertType.CONFIRMATION);
            error_update_alert.setTitle("Hiba");
            error_update_alert.setHeaderText("Az adatbázis nem tud csatlakozni!");
            error_update_alert.setContentText("Próbálja újra!");
            error_update_alert.initOwner(WelcomeCon.cars_window);
            error_update_alert.show();
            e.printStackTrace();
        }
        g_name_tf.setText("");
        Info_update_TA.setText("");
        Price_update_TF.setText("");
        image_iv.setImage(null);

    }//hozzá lehet adni gépjárművet

    @FXML
    private void cars_delete(){
        int id = Integer.parseInt(id_TC.getCellData(index));
        PreparedStatement pst;
        String delete_cars = "DELETE FROM `vehicles` WHERE `vehicles`.`id` = ?";

        try {
            Connection con = DBConnector.getConnection();

            pst = con.prepareStatement(delete_cars);
            pst.setInt(1, id);
            pst.execute();
            cars_TW.getItems().clear();
            carstable();

            Alert apply_del_alert = new Alert(Alert.AlertType.CONFIRMATION);
            apply_del_alert.setTitle("Sikeres gépjármű törlés...");
            apply_del_alert.setHeaderText("Sikeresen törölte a gépjárművet.");
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
        g_name_tf.setText("");
        Info_update_TA.setText("");
        Price_update_TF.setText("");
        image_iv.setImage(null);
    }//törölni lehet vele gépjárművet

    @FXML
    private void searchbooking_btn(){
        String keres = searchbooking_tf.getText();

        boolean volt =false;
        for (Cars elem : carslist){
            if (elem.id.equals(keres) || elem.car.equals(keres)){
                index = elem.sorszam;
                volt = true;
            }
        }

        if (!volt){
            Alert error_alert = new Alert(Alert.AlertType.ERROR);
            error_alert.setTitle("Hiba");
            error_alert.setHeaderText("Nem található ezekkel az adatokkal foglalás!");
            error_alert.show();
            searchbooking_tf.setText("");
            searchbooking_tf.setPromptText("Keresés azonosító vagy gépjármű név alapján...");
        }else {
            CID_lab.setText("Gépjármű azonosító: " + id_TC.getCellData(index).toString());
            name_lab.setText("Neve: " + CarName_TC.getCellData(index).toString());
            g_name_tf.setText(CarName_TC.getCellData(index));
            dailyprice_lab.setText("Információ: " + info_TC.getCellData(index).toString());
            info_lab.setText("Napi ára: " + dailyprice_TC.getCellData(index).toString() + "Ft.");
            Info_update_TA.setText(info_TC.getCellData(index).toString());
            Price_update_TF.setText(dailyprice_TC.getCellData(index).toString());
            image_iv.setImage((Image) image_tc.getCellData(index));
            searchbooking_tf.setText("");
            searchbooking_tf.setPromptText("Keresés azonosító vagy gépjármű név alapján...");
        }

    }//keresni lehet azonosító vagy gépkocsi név alapján

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        carstable();
    }

    private void carstable(){
        try {
            Connection con = DBConnector.getConnection();

            ResultSet cars = con.createStatement().executeQuery("SELECT * FROM `vehicles`");

            int ssz = -1;

            while (cars.next()){
                Blob blob = cars.getBlob("image");
                InputStream inputStream = blob.getBinaryStream();
                Image image = new Image(inputStream);

                ssz++;

                carslist.add(new Cars(ssz, cars.getString("id"), cars.getString("car"), cars.getString("info"), cars.getString("daily_price"), image ));
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



}


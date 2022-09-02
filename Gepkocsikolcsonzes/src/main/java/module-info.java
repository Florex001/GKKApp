module com.example.gepkocsikolcsonzes {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.gepkocsikolcsonzes to javafx.fxml;
    exports com.example.gepkocsikolcsonzes;
}
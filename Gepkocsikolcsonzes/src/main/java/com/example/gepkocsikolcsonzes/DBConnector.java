package com.example.gepkocsikolcsonzes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    public static Connection getConnection() throws SQLException {
        Connection con =  DriverManager.getConnection("jdbc:mysql://localhost/gkkdb", "root", "");
        return con;
    }
}

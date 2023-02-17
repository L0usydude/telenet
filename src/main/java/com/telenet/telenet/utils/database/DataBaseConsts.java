package com.telenet.telenet.utils.database;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Repository
public class DataBaseConsts {
    static String url = "jdbc:postgresql://localhost:5432/telenet";
    static String username = "postgres";
    static String password = "QWpo10@bdfy";
    static Connection connection = null;
    static{
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

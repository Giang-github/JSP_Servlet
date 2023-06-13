package com.example.demo.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    static final String DB_URL = "jdbc:mysql://localhost:3307/postmanager";
    static final String USER = "root";
    static final String PASS = "giang2001";
    public static Connection getConnectDB(){
        Connection connect = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(DB_URL,USER ,PASS );
            System.out.println("Connected to database.");
        } catch (Exception e) {
            System.out.println("Failed to connect to database.");
            e.printStackTrace();
        }
        return connect;

    }
}

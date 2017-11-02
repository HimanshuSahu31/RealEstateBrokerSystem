package com.MainApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnect {
    private final String url = "jdbc:postgresql://localhost/brokerSystem";
    private final String user = "postgres";
    private final String password = "h";
    Connection connection = null;
    public DatabaseConnect() {
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    void setAutoCommit(boolean f) {
        connection.setAutoCommit(f);
    }
    void createStatement() {
        connection.createStatement();
    }
}

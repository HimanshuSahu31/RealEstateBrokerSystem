package com.MainApp;

import java.sql.*;

public class DatabaseConnect {
    private final String url = "jdbc:postgresql://localhost/brokerSystem";
    private final String user = "postgres";
    private final String password = "h";
    Connection connection = null;

    public DatabaseConnect() {
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
    }

    void setAutoCommit(boolean f) {
        try {
            connection.setAutoCommit(f);
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
    }

    Statement createStatement() {
        try {
            Statement stmt = connection.createStatement();
            return stmt;
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
        return null;
    }

    ResultSet selectQuery(String str) {
        try {
            setAutoCommit(false);
            Statement stmt = createStatement();
            ResultSet rs = stmt.executeQuery(str);
            return rs;
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
        return null;
    }

    void close() {
        try {
            connection.close();
        } catch(SQLException exp) {
            System.out.println(exp.getMessage());
        }
    }
}

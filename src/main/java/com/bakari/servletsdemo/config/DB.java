package com.bakari.servletsdemo.config;

import java.sql.*;
import java.util.logging.Logger;

public class DB {
    private static volatile DB _instance = null;
    private Connection _con = null;

    private DB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            _con = DriverManager.getConnection(
                    Properties.get("db.url"),
                    Properties.get("db.username"),
                    Properties.get("db.password")
            );

            Logger.getLogger("DB").info("Connected to database");
        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger("DB").severe("Failed to connect to database");
            throw new RuntimeException(e);
        }
    }

    public static DB getInstance() {
        if (_instance == null) {
            synchronized (DB.class) {
                if (_instance == null) {
                    _instance = new DB();
                }
            }
        }
        return _instance;
    }

    public Connection getConnection() {
        return _con;
    }

    public boolean execute(String query) {
        this.validateQuery(query);

        try (Statement statement = _con.createStatement()) {
            return statement.execute(query);
        } catch (SQLException e) {
            Logger.getLogger("DB").severe("Failed to execute query");
            throw new RuntimeException(e);
        }
    }

    public PreparedStatement prepareStatement(String query) {
        this.validateQuery(query);

        try {
            return _con.prepareStatement(query);
        } catch (SQLException e) {
            Logger.getLogger("DB").severe("Failed to prepare statement");
            throw new RuntimeException(e);
        }
    }

    private void validateQuery(String query) {
        if (query == null || query.isEmpty()) {
            throw new IllegalArgumentException("Query cannot be null or empty");
        }
    }

    public void closeConnection() {
        try {
            _con.close();
        } catch (SQLException e) {
            Logger.getLogger("DB").severe("Failed to close connection");
            throw new RuntimeException(e);
        }
    }
}

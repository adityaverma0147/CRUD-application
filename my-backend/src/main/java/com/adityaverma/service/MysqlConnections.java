package com.adityaverma.service;

import java.io.FileReader;	
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.adityaverma.my_backend.App;

public class MysqlConnections {
    public static Connection connection;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;

    // Method to load MySQL connection settings from config.json
    private static JSONObject loadConfig() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader("config.json")) {
            return (JSONObject) parser.parse(reader);
        }
    }

    // Method to establish the MySQL connection
    public static void connect() throws SQLException, IOException, ParseException {
//        JSONObject config = loadConfig();
//        String url = (String) ((JSONObject) config.get("mysql")).get("url");
//        String username = (String) ((JSONObject) config.get("mysql")).get("username");
//        String password = (String) ((JSONObject) config.get("mysql")).get("password");
    	String url = App.config.getMysql().getUrl();
    	String username = App.config.getMysql().getName();
    	String password = App.config.getMysql().getPassword();
        
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to MySQL database.");
        } catch (SQLException e) {
            System.out.println("Connection to MySQL database failed.");
            e.printStackTrace();
        }
    }

    // Method to close the MySQL connection
    public static void disconnect() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Disconnected from MySQL database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to execute an insert query
    public static boolean insert(Usr usr) throws SQLException, IOException, ParseException {
        connect();
        try {
            String query = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, usr.getName());
            preparedStatement.setString(2, usr.getEmail());
            preparedStatement.setString(3, usr.getPassword());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            disconnect();
        }
    }
}

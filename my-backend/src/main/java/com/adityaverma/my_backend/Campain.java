package com.adityaverma.my_backend;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import com.adityaverma.service.MysqlConnections;
import com.adityaverma.service.Usr;
import com.google.gson.Gson;

public class Campain extends AbstractHandler {

    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        baseRequest.setHandled(true);
        if ("OPTIONS".equals(request.getMethod())) {
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
            response.setHeader("Access-Control-Allow-Headers", "Content-Type");
            response.setStatus(HttpServletResponse.SC_OK);
            baseRequest.setHandled(true);
            return;
        }
        
        List<Usr> users = new ArrayList<>();
        try {
            MysqlConnections.connect();
            String query = "SELECT * FROM users";
            try (PreparedStatement preparedStatement = MysqlConnections.connection.prepareStatement(query)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                
                while (resultSet.next()) {
                    Usr user = new Usr();
                    user.setId(resultSet.getInt("id"));
                    user.setName(resultSet.getString("name"));
                    user.setEmail(resultSet.getString("email"));
                    user.setPassword(resultSet.getString("password"));
                    users.add(user);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Serialize the list of users to JSON
        Gson gson = new Gson();
        String jsonUsers = gson.toJson(users);
        
        // Set response headers
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        
        // Write JSON string to response output stream
        response.getWriter().write(jsonUsers);
    }
}

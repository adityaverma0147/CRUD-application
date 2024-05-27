package com.adityaverma.my_backend;

import com.adityaverma.service.Mysql;
import com.adityaverma.service.MysqlConnections;
import com.adityaverma.service.Usr;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import java.io.IOException;
import java.sql.SQLException;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;

public class LoginController extends AbstractHandler {

    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
    	baseRequest.setHandled(true);
    	if ("OPTIONS".equals(request.getMethod())) {
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
            response.setHeader("Access-Control-Allow-Headers", "Content-Type");
            response.setStatus(HttpServletResponse.SC_OK);
            baseRequest.setHandled(true);
            return;
        }
        // Extract login parameters from request
    	StringBuilder requestBody = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                requestBody.append(line);
            }
        }
        
     // Convert JSON to Java object
        Gson gson = new Gson();
//        JsonObject json = gson.fromJson(requestBody.toString(), JsonObject.class);

        // Extract username and password
//        String username = json.get("username").getAsString();
//        String password = json.get("password").getAsString();

//        Usr a=  gson.fromJson(json,Usr.class);
        Usr a=  gson.fromJson(requestBody.toString(),Usr.class);
        System.out.println("Usr a :"+ a.toString());
        System.out.println("requestBody.toString() ::"+ requestBody.toString());
        Boolean inserted = false;
        try {
			 inserted = MysqlConnections.insert(a);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     
        
        // Perform validation (example: hardcoded values for demonstration)
        if (inserted) {
            // Successful login
        	
        	response.setContentType("application/json");
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("success", true);
            jsonResponse.put("message", "Login successful");
            response.getWriter().println(jsonResponse.toString());       } 
        else {
            // Failed login
        	 response.setContentType("application/json");
             JSONObject jsonResponse = new JSONObject();
             jsonResponse.put("success", false);
             jsonResponse.put("message", "Invalid username or password");
             response.getWriter().println(jsonResponse.toString());
        }
    }
}

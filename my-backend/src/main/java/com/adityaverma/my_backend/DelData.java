package com.adityaverma.my_backend;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import com.adityaverma.service.MysqlConnections;
import com.adityaverma.service.Usr;
import com.google.gson.Gson;

public class DelData extends AbstractHandler{
	
	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException{
		
		 baseRequest.setHandled(true);
	        if ("OPTIONS".equals(request.getMethod())) {
	            response.setHeader("Access-Control-Allow-Origin", "*");
	            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
	            response.setHeader("Access-Control-Allow-Headers", "Content-Type");
	            response.setStatus(HttpServletResponse.SC_OK);
	            baseRequest.setHandled(true);
	            return;
	        	
	        }
	        
//	        	Map<String, String> getReqParam(HttpServletRequest request) {
//	    		Map<String, String[]> params = request.getParameterMap();
//	    		Map<String, String> reqParam = new HashMap<String, String>();
//
//	    		Iterator<String> i = params.keySet().iterator();
//	    		while (i.hasNext()) {
//	    			String key = (String) i.next();
//	    			String value = ((String[]) params.get(key))[0];
//	    			reqParam.put(key, value);
//	    		}
//    		
//	        	}
	        List<Usr> users = new ArrayList<>();
	        try {	
	        	
	        	MysqlConnections.connect();  
	        	String deletequery = "DELETE FROM users WHERE id = ?";
	        	String updatequery = "SELECT * FROM users";
	        			try (PreparedStatement deleteStatement = MysqlConnections.connection.prepareStatement(deletequery);
	        				     PreparedStatement updateStatement = MysqlConnections.connection.prepareStatement(updatequery)) {
	        				deleteStatement.setInt(1,Integer.parseInt(request.getParameterMap().get("usid")[0]));
	        				deleteStatement.executeUpdate();
	        				
	        				 ResultSet resultSet = updateStatement.executeQuery();
	                        
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



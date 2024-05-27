package com.adityaverma.my_backend;

import com.adityaverma.service.Jetty;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import com.adityaverma.my_backend.Config; 


public class App {
	public static Config config;
	public static Server server;
    public static void main(String[] args) throws Exception {
        // Load configuration from config.jsonn
    	String configPath = args[0];
        config = loadConfig(configPath);

        // Use the configuration as needed
        System.out.println("Loaded configuration:");
        System.out.println("url: " + config.getMysql().getUrl());
        System.out.println("username: " + config.getMysql().getName());
        System.out.println("password: " + config.getMysql().getPassword());

        server = new Server(config.getJetty().getJettyport());
        
        ContextHandlerCollection contexts = new ContextHandlerCollection();

        ContextHandler cxCampaign = new ContextHandler("/login");
		cxCampaign.setHandler(new LoginController());
		cxCampaign.setAllowNullPathInfo(true);


		  ContextHandler campaign = new ContextHandler("/users");
	        campaign.setHandler(new Campain());
	        campaign.setAllowNullPathInfo(true);
	    
	        
	        ContextHandler  deldata= new ContextHandler("/delete");
	        deldata.setHandler(new DelData());	
	        deldata.setAllowNullPathInfo(true);
    
//		
		contexts.setHandlers(new  Handler[] {cxCampaign, campaign , deldata});

        // Set the ContextHandlerCollection as the server's handler
        server.setHandler(contexts);

        // Start the server
        server.start();
        server.join();
    }
    

    private static Config loadConfig(String configFile) {
        try (FileReader reader = new FileReader(configFile)) {
            Gson gson = new Gson();
            return gson.fromJson(reader, Config.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Handle error
        }
    }
}

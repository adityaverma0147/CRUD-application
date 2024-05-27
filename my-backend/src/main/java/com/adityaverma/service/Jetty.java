package com.adityaverma.service;

import com.google.gson.annotations.SerializedName;

public class Jetty{

	@SerializedName("jetty_host")
    private String jettyhost;
	
	public  String getJettyhost() {
		return jettyhost;
	}

	public void setJettyhost(String jettyhost) {
		this.jettyhost = jettyhost;
	}

	@SerializedName("jetty_port")
    private Integer jettyport;

	
	

	public Integer getJettyport() {
		return jettyport;
	}

	public void setJettyport(Integer jettyport) {
		this.jettyport = jettyport;
	}

	@Override
	public String toString() {
		return "Jetty [jettyhost=" + jettyhost + ", jettyport=" + jettyport + "]";
	}
	
	
	
}

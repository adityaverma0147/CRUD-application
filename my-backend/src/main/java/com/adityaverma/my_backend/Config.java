package com.adityaverma.my_backend;

import com.adityaverma.service.Jetty;
import com.adityaverma.service.Mysql;
import com.adityaverma.service.Usr;

public class Config {
    public Mysql mysql;
    
   
    
    public Jetty jetty;

    public Jetty getJetty() {
		return jetty;
	}

	public void setJetty(Jetty jetty) {
		this.jetty = jetty;
	}


	public Mysql getMysql() {
        return mysql;
    }

    public void setMysql(Mysql mysql) {
        this.mysql = mysql;
    }

	@Override
	public String toString() {
		return "Config [mysql=" + mysql  + ", jetty=" + jetty + "]";
	}

    
}

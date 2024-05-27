package com.adityaverma.service;

import com.google.gson.annotations.SerializedName;

public class Mysql {
	   
	    @SerializedName("username")
	    private String name;
	    
	    @SerializedName("password")
	    private String password;

	    private String url;
	    
		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		@Override
		public String toString() {
			return "Mysql [name=" + name + ", password=" + password + ", url=" + url + "]";
		}
	        
	

}

package com.adityaverma.service;

import com.google.gson.annotations.SerializedName;

public class Usr {
	
		private Integer id;
	 	
	    public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		private String name;
	    
	    @SerializedName("password")
	    private String password;

	    @SerializedName("email")
	    private String email;

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

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		@Override
		public String toString() {
			return "Usr [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + "]";
		}


}

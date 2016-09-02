package com.crossover.techtrial.airline.context;

import java.util.List;

public class User {
 
	private String name;
	private String username;
	private String password;
	private SocialNetwork socialNetwork;
	private Rol rol;
	 
	public User(Builder builder) {
		this.name = builder.name;
		this.username = builder.username;
		this.password = builder.password;
		this.socialNetwork = builder.socialNetwork;
		this.rol = builder.rol;
	}

	public static class Builder {
		
		private final String username;
		private final String password;
		private String name;
		private SocialNetwork socialNetwork;
		private Rol rol;

		public Builder(String username, String password){
			this.username = username;
			this.password = password;
		}
		
		public Builder setName(String name){
			this.name = name;
			return this;
		}

		public Builder setSocialNetwork(SocialNetwork socialNetwork){
			this.socialNetwork = socialNetwork;
			return this;
		}
		
		public Builder setRol(Rol rol){
			this.rol = rol;
			return this;
		}
		
		public User build(){
			return new User(this);
		}
	}
		
	public String getName() {
		return this.name;
	}
	 
	public String getUserName() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
	 
	public SocialNetwork getSocialNetwork() {
		return this.socialNetwork;
	}
	
	public Rol getRol() {
		return this.rol;
	}

}
 

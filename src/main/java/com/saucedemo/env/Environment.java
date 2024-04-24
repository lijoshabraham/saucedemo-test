package com.saucedemo.env;

public enum Environment {

	DEV("https://www.saucedemo.com/dev"),
	STAGE("https://www.saucedemo.com/stage"),
	PROD("https://www.saucedemo.com/");

	String envUrl;

	Environment(String envUrl) {
		this.envUrl = envUrl;
	}

	public String getEnvUrl() {
		return envUrl;
	}
	
	

}

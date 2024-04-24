package com.saucedemo.browsers;

public enum Browsers {
	
	CHROME("Google Chrome"),
	EDGE("Microsoft Edge"),
	FIREFOX("Mozilla Firefox");
	
	public String browserName;

	Browsers(String browser) {
		this.browserName=browser;
	}

	public String getBrowserName() {
		return browserName;
	}

}

package com.saucedemo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.saucedemo.TestBases.TestBase;
import com.saucedemo.utility.Utility;

public class LoginPage extends TestBase {

	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "user-name")
	private WebElement usernameField;

	@FindBy(id = "password")
	private WebElement passwordField;

	@FindBy(id = "login-button")
	private WebElement loginButton;

	@FindBy(css = ".login_logo")
	private WebElement loginPageHeader;

	private void enterUsername(String username) {
		logger.info("Entering username " + username);
		Utility.sendText(usernameField, username);
	}

	private void enterPassword(String password) {
		logger.info("Entering password " + password);
		Utility.sendText(passwordField, password);
	}

	private void clickLoginBtn() {
		logger.info("Clicking login button");
		Utility.clickOnElement(loginButton);
	}

	public InventoryPage submitLogin(String username, String password) {
		enterUsername(username);
		enterPassword(password);
		clickLoginBtn();
		return new InventoryPage();
	}

	public String getLoginPageHeader() {
		return Utility.getTextFromWebelement(loginPageHeader);
	}

}

package com.saucedemo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.saucedemo.TestBases.TestBase;
import com.saucedemo.utility.Utility;

public class checkoutCompletePage extends TestBase {

	public checkoutCompletePage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "back-to-products")
	WebElement homeBtn;

	@FindBy(css = ".complete-header")
	WebElement completeHeader;

	@FindBy(id = "react-burger-menu-btn")
	WebElement menuIcon;

	@FindBy(id = "logout_sidebar_link")
	WebElement logoutBtn;

	public String getCompleteHeader() {
		return Utility.getTextFromWebelement(completeHeader);
	}

	public InventoryPage clickHomeBtn() {
		Utility.clickOnElement(homeBtn);
		return new InventoryPage();
	}

	private void clickMenuIcon() {
		Utility.clickOnElement(menuIcon);
	}

	private void clickLogoutBtn() {
		Utility.clickOnElement(logoutBtn);
	}

	public LoginPage submitLogout() {
		clickMenuIcon();
		clickLogoutBtn();
		return new LoginPage();
	}

}

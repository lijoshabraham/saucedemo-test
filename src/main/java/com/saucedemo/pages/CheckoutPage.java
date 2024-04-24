package com.saucedemo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.saucedemo.TestBases.TestBase;
import com.saucedemo.utility.Utility;

public class CheckoutPage extends TestBase {

	public CheckoutPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".header_secondary_container .title")
	WebElement CheckoutPageTitle;

	@FindBy(id = "first-name")
	WebElement firstName;

	@FindBy(id = "last-name")
	WebElement lastName;

	@FindBy(id = "postal-code")
	WebElement postalCode;

	@FindBy(id = "continue")
	WebElement continueBtn;

	private void enterFistName(String name) {
		logger.info("Entering firstname " + name);
		Utility.sendText(firstName, name);
	}

	private void enterLastName(String lName) {
		logger.info("Entering lastname " + lName);
		Utility.sendText(lastName, lName);
	}

	private void enterPostalCode(String pCode) {
		logger.info("Entering postal code " + pCode);
		Utility.sendText(postalCode, pCode);
	}

	public CheckoutOverviewPage clickContinueBtn(String name, String lName, String pCode) {
		enterFistName(name);
		enterLastName(lName);
		enterPostalCode(pCode);
		Utility.clickOnElement(continueBtn);
		return new CheckoutOverviewPage();

	}

	public String getCheckOutPageTitle() {
		return Utility.getTextFromWebelement(CheckoutPageTitle);

	}

}

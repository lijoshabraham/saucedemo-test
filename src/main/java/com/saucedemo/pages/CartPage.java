package com.saucedemo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.saucedemo.TestBases.TestBase;
import com.saucedemo.utility.Utility;

public class CartPage extends TestBase {

	public CartPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "#item_1_title_link .inventory_item_name")
	WebElement firstItemTitle;

	@FindBy(css = "#item_3_title_link .inventory_item_name")
	WebElement secondItemTitle;

	@FindBy(id = "checkout")
	WebElement checkoutBtn;

	public String getFirstItemTitle() {
		return Utility.getTextFromWebelement(firstItemTitle);
	}

	public String getSecondItemTitle() {
		return Utility.getTextFromWebelement(secondItemTitle);
	}

	public CheckoutPage clickCheckoutBtn() {
		Utility.clickOnElement(checkoutBtn);
		return new CheckoutPage();

	}

}

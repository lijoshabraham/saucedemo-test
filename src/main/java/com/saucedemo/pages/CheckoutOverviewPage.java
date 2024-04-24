package com.saucedemo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.saucedemo.TestBases.TestBase;
import com.saucedemo.utility.Utility;

public class CheckoutOverviewPage extends TestBase {

	public CheckoutOverviewPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".header_secondary_container .title")
	WebElement CheckoutOverviewPageTitle;

	@FindBy(css = ".summary_info div:nth-of-type(2)")
	WebElement paymentInformation;

	@FindBy(css = ".summary_info div:nth-of-type(8)")
	WebElement totalPrice;

	@FindBy(id = "finish")
	WebElement finishBtn;

	public String getCheckOutOverviewPageTitle() {
		return Utility.getTextFromWebelement(CheckoutOverviewPageTitle);
	}

	public String getPaymentInformation() {
		return Utility.getTextFromWebelement(paymentInformation);
	}

	public String getTotalPrice() {
		return Utility.getTextFromWebelement(totalPrice);
	}

	public checkoutCompletePage clickFinishBtn() {
		Utility.clickOnElement(finishBtn);
		return new checkoutCompletePage();
	}

}

package saucedemo.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.saucedemo.TestBases.TestBase;
import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.CheckoutOverviewPage;
import com.saucedemo.pages.CheckoutPage;
import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.checkoutCompletePage;
import com.saucedemo.utility.Utility;

public class CheckoutCompletePageTest extends TestBase {
	LoginPage loginPage;
	InventoryPage inventoryPage;
	CartPage cartPage;
	CheckoutPage checkoutPage;
	CheckoutOverviewPage checkoutOverviewPage;
	checkoutCompletePage completePage;

	@BeforeMethod
	public void launchBrowser() {
		initialization();
		loginPage = new LoginPage();
	}

	@Test(dataProvider = "LoginData", dataProviderClass = InventoryPageTest.class)
	public void validateCompleteOrder(String username, String password) {
		inventoryPage = loginPage.submitLogin(username, password);
		inventoryPage.submitSort("Price (high to low)");
		inventoryPage.addFirstItemToCart();
		inventoryPage.addSecondItemToCart();
		cartPage = inventoryPage.submitCartIconClick();
		checkoutPage = cartPage.clickCheckoutBtn();
		checkoutOverviewPage = checkoutPage.clickContinueBtn(Utility.generateRandomString(5),
				Utility.generateRandomString(5), Utility.generateRandomString(6));
		completePage = checkoutOverviewPage.clickFinishBtn();
		String completeHeader = completePage.getCompleteHeader();
		Assert.assertEquals(completeHeader, "Thank you for your order!", "Actual and Expected text don't match");

	}

	@AfterMethod
	public void closeBrowser() {
		tearDown();
	}

}

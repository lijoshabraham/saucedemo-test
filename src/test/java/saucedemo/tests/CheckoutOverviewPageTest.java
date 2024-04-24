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
import com.saucedemo.utility.Utility;

public class CheckoutOverviewPageTest extends TestBase {
	LoginPage loginPage;
	InventoryPage inventoryPage;
	CartPage cartPage;
	CheckoutPage checkoutPage;
	CheckoutOverviewPage checkoutOverviewPage;

	@BeforeMethod
	public void launchBrowser() {
		initialization();
		loginPage = new LoginPage();

	}

	@Test(dataProvider = "LoginData", dataProviderClass = InventoryPageTest.class)
	public void validatePaymentInformation(String username, String password) {
		inventoryPage = loginPage.submitLogin(username, password);
		inventoryPage.submitSort("Price (high to low)");
		inventoryPage.addFirstItemToCart();
		inventoryPage.addSecondItemToCart();
		cartPage = inventoryPage.submitCartIconClick();
		checkoutPage = cartPage.clickCheckoutBtn();

		checkoutOverviewPage = checkoutPage.clickContinueBtn(Utility.generateRandomString(5),
				Utility.generateRandomString(5), Utility.generateRandomString(6));

		String paymentInfoText = checkoutOverviewPage.getPaymentInformation();
		Assert.assertEquals(paymentInfoText, "SauceCard #31337", "Actual and Expected text don't match");

	}

	@Test(dataProvider = "LoginData", dataProviderClass = InventoryPageTest.class)
	public void validateTotalPriceWithoutSort(String username, String password) {
		inventoryPage = loginPage.submitLogin(username, password);
		inventoryPage.addFirstItemToCart();
		inventoryPage.addSecondItemToCart();
		cartPage = inventoryPage.submitCartIconClick();
		checkoutPage = cartPage.clickCheckoutBtn();

		checkoutOverviewPage = checkoutPage.clickContinueBtn(Utility.generateRandomString(5),
				Utility.generateRandomString(5), Utility.generateRandomString(6));

		String totalPriceText = checkoutOverviewPage.getTotalPrice();
		Assert.assertEquals(totalPriceText, "Total: $34.54", "Actual and Expected price don't match");

	}

	@Test(dataProvider = "LoginData", dataProviderClass = InventoryPageTest.class)
	public void validateTotalPrice(String username, String password) {
		inventoryPage = loginPage.submitLogin(username, password);
		inventoryPage.submitSort("Price (high to low)");
		inventoryPage.addFirstItemToCart();
		inventoryPage.addSecondItemToCart();
		cartPage = inventoryPage.submitCartIconClick();
		checkoutPage = cartPage.clickCheckoutBtn();

		checkoutOverviewPage = checkoutPage.clickContinueBtn(Utility.generateRandomString(5),
				Utility.generateRandomString(5), Utility.generateRandomString(6));

		String totalPriceText = checkoutOverviewPage.getTotalPrice();
		Assert.assertEquals(totalPriceText, "Total: $34.54", "Actual and Expected price don't match");

	}

	@AfterMethod
	public void closeBrowser() {
		tearDown();
	}

}

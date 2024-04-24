package saucedemo.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.saucedemo.TestBases.TestBase;
import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.LoginPage;

public class CartPageTest extends TestBase {
	LoginPage loginPage;
	InventoryPage inventoryPage = new InventoryPage();;
	CartPage cartPage;

	@BeforeMethod()
	public void launchBrowser() {
		initialization();
		loginPage = new LoginPage();
	}

	@Test(dataProvider = "LoginData", dataProviderClass = InventoryPageTest.class)
	public void validateFirstItemTitle(String username, String password) {
		inventoryPage = loginPage.submitLogin(username, password);
		inventoryPage.submitSort("Price (high to low)");
		inventoryPage.addFirstItemToCart();
		cartPage = inventoryPage.submitCartIconClick();
		String titleText = cartPage.getFirstItemTitle();
		Assert.assertEquals(titleText, "Sauce Labs Bolt T-Shirt", "Actual and Expected header text don't match");
	}

	@Test(dataProvider = "LoginData", dataProviderClass = InventoryPageTest.class)
	public void validateSecondItemTitle(String username, String password) {
		inventoryPage = loginPage.submitLogin(username, password);
		inventoryPage.submitSort("Price (high to low)");
		inventoryPage.addSecondItemToCart();
		cartPage = inventoryPage.submitCartIconClick();
		String titleText = cartPage.getSecondItemTitle();
		Assert.assertEquals(titleText, "Test.allTheThings() T-Shirt (Red)",
				"Actual and Expected header text don't match");
	}

	@AfterMethod
	public void closeBrowser() {
		tearDown();
	}
}

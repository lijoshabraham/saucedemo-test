package saucedemo.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.saucedemo.utility.ExcelUtils;
import com.saucedemo.TestBases.TestBase;
import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.LoginPage;

public class InventoryPageTest extends TestBase {
	LoginPage loginPage;
	InventoryPage inventoryPage;

	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage = new LoginPage();
	}

	@Test
	public void validateLogin() {

		String actualTitle = inventoryPage.getPageTitleText();
		Assert.assertEquals(actualTitle, "Products", "Inventory page title does not match expected.");
	}

	@DataProvider(name = "LoginData")
	public String[][] loginInfoProvider() throws IOException {
		String filePath = "Resources\\LoginDetails.xlsx";
		int rowCount = ExcelUtils.getRowCount(filePath, "Sheet1");
		int colCount = ExcelUtils.getColumnCount(filePath, "Sheet1", rowCount);
		String[][] loginData = new String[rowCount][colCount];
		for (int i = 1; i <= rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				loginData[i - 1][j] = ExcelUtils.getCellValue(filePath, "Sheet1", i, j);
			}
		}
		return loginData;
	}

	@Test(dataProvider = "LoginData")
	public void validateProductSort(String username, String password) {
		inventoryPage = loginPage.submitLogin(username, password);
		inventoryPage.submitSort("Price (high to low)");
		String firstProductName = inventoryPage.getFirstProductNameAfterSort();
		Assert.assertEquals(firstProductName, "Sauce Labs Fleece Jacket", "Item title does not match after sort");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}

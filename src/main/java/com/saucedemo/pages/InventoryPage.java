package com.saucedemo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.saucedemo.TestBases.TestBase;
import com.saucedemo.utility.Utility;

public class InventoryPage extends TestBase {

	// Constructor
	public InventoryPage() {

		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".header_secondary_container .title")
	WebElement pageTitle;

	@FindBy(css = ".product_sort_container")
	WebElement sortDropdown;

	@FindBy(css = "#item_5_title_link .inventory_item_name")
	WebElement firstItemName;

	@FindBy(css = ".inventory_list div:nth-of-type(3) #add-to-cart-sauce-labs-bolt-t-shirt")
	WebElement addToCartButtonFirstItem;

	@FindBy(css = ".inventory_list div:nth-of-type(4) .pricebar button")
	WebElement addToCartButtonSecondItem;

	@FindBy(css = ".shopping_cart_link")
	WebElement cartIcon;

	private void clickAddToCartFirstItem() {
		Utility.clickOnElement(addToCartButtonFirstItem);
	}

	private void clickAddToCartSecondItem() {
		Utility.clickOnElement(addToCartButtonSecondItem);
	}

	private void clickSortBtn(String name) {
		Utility.selectFromDropDownUsingVisibleText(sortDropdown, name);
	}

	private void clickCartIcon() {
		Utility.clickOnElement(cartIcon);
	}

	public String getFirstProductNameAfterSort() {
		return Utility.getTextFromWebelement(firstItemName);
	}

	public String getPageTitleText() {
		return Utility.getTextFromWebelement(pageTitle);
	}

	public InventoryPage submitSort(String name) {
		clickSortBtn(name);
		return new InventoryPage();
	}

	public InventoryPage addFirstItemToCart() {
		clickAddToCartFirstItem();
		return new InventoryPage();
	}

	public InventoryPage addSecondItemToCart() {
		clickAddToCartSecondItem();
		return new InventoryPage();
	}

	public CartPage submitCartIconClick() {
		clickCartIcon();
		return new CartPage();
	}

}

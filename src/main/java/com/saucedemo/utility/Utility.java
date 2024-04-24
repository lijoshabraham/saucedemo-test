package com.saucedemo.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.saucedemo.TestBases.TestBase;

public class Utility extends TestBase {

	public static void takeFailedTestScreenShot(String testCaseName) {
		// Get the current date and Time
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

		// Save the screen shot in a file
		File screenShotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		// Save the screenshot
		try {
			FileUtils.copyFile(screenShotFile,
					new File("./FailedScreenShot\\" + "_" + testCaseName + "_" + timeStamp + ".jpeg"));
		} catch (IOException e) {
			System.out.println("Unable to save or take screen shot of failed test " + testCaseName);

		}

	}

	public static String generateRandomEmail() {
		String email = RandomStringUtils.randomNumeric(3);
		String emailID = "Lijosh" + email + "@gmail.com";
		return emailID;
	}

	public static int generateRandomInt(int length) {
		Random random = new Random();
		return random.nextInt(length);
	}

	public static String generateRandomString(int length) {

		String uuid = UUID.randomUUID().toString();
		return uuid.replaceAll("-", "").substring(0, length);
	}

	public static Date generateRandomDate() {
		return new Date();
	}

	public static String generateRandomPassword() {
		String randomStringForPassword = RandomStringUtils.randomNumeric(3);
		String password = "Manager@" + randomStringForPassword;
		return password;
	}

	public static void sleep(int seconds) {
		try {
			Thread.sleep(seconds);
		} catch (InterruptedException e) {

		}
	}

	// Explicit waits
	public static WebElement waitForElementToBeClickable(WebElement element, int timeOutInSeconds) {
		return new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(element));
	}

	public static WebElement waitForElementToBeVisible(WebElement element, int timeOutInSeconds) {
		return new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.visibilityOf(element));
	}

	public static Boolean waitForElementToBeSelectable(WebElement element, int timeOutInSeconds) {
		return new WebDriverWait(driver, timeOutInSeconds)
				.until(ExpectedConditions.elementSelectionStateToBe(element, false));
	}

	public static Alert waitForAlertToBePresent(int timeOutInSeconds) {
		return new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.alertIsPresent());
	}

	// methods to perform actions
	public static void clickOnElement(WebElement element) {
		waitForElementToBeClickable(element, 15).click();
	}


	public static void selectFromDropDownUsingVisibleText(WebElement element, String text) {
		Select sc = new Select(element);

		try {
			waitForElementToBeClickable(element, 10);
			sc.selectByVisibleText(text);
		} catch (Exception e) {
			sc.selectByValue(text);
		}
	}
	
	public static void moveToElement(WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).build().perform();
	}

	public void acceptAlert() {
		if (waitForAlertToBePresent(5) != null) {
			driver.switchTo().alert().accept();
		}
	}

	public void dismissAlert() {
		if (waitForAlertToBePresent(5) != null) {
			driver.switchTo().alert().dismiss();
		}
	}

	public static void sendText(WebElement element, String text) {
		waitForElementToBeVisible(element, 10).sendKeys(text);

	}

	public void switchToWindowHandle(WebElement element) {
		String parentHandle = driver.getWindowHandle();
		element.click();
		Set<String> getAllHandles = driver.getWindowHandles();
		for (String individualHandle : getAllHandles) {
			if (!individualHandle.equalsIgnoreCase(parentHandle)) {
				driver.switchTo().window(individualHandle);
			}
		}
	}

	public void switchToFrame(WebElement element) {
		driver.switchTo().frame(waitForElementToBeVisible(element, 10));
	}

	public static String getTextFromWebelement(WebElement element) {
		return waitForElementToBeVisible(element, 10).getText();
	}

}

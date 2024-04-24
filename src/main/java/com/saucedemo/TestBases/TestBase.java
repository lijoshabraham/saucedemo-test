package com.saucedemo.TestBases;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.BeforeClass;

import com.saucedemo.browsers.Browsers;
import com.saucedemo.env.Environment;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	private static Browsers DEFAULT_BROWSER = Browsers.EDGE;
    private static Environment DEFAULT_ENV = Environment.PROD;
    public static Logger logger;
    private EventFiringWebDriver eDriver;
    public static WebDriver driver;

    @BeforeClass
    public void setUpLogger() {
        logger = Logger.getLogger(TestBase.class);
        PropertyConfigurator.configure("log4j.properties");
        BasicConfigurator.configure();
        logger.setLevel(Level.ALL);
    }

    public void initialization() {
        setBrowserForTesting();
        driverManagement();
        logger.info("Loading Page in Browser");
        driver.get(DEFAULT_ENV.getEnvUrl());
    }

    private void driverManagement() {
        driver.manage().window().maximize();
    }

    private void setBrowserForTesting() {
    	
    	//Retrieve value of "browser" 
        String browserName = System.getProperty("browser");
        
        if (browserName == null) {
        	
        	DEFAULT_BROWSER = Browsers.EDGE;	
        	
        }
        
        else {
        	
        	 DEFAULT_BROWSER = Browsers.valueOf(browserName.toUpperCase());
        	
        }
  

        switch (DEFAULT_BROWSER) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                logger.info("Launching Chrome Browser");
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                logger.info("Launching Firefox Browser");
                driver = new FirefoxDriver();
                break;
            case EDGE:
                WebDriverManager.edgedriver().setup();
                logger.info("Launching Edge Browser");
                driver = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }
        
        
      
        eDriver = new EventFiringWebDriver(driver);
        driver = eDriver;
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

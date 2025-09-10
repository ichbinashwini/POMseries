package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.CommonsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.pages.SearchResultPage;

//@Listeners({ChainTestListener.class, TestAllureListener.class})
public class BaseTest {

	WebDriver driver;
	// DriverFactory df;
	protected LoginPage loginPage;
	protected AccountPage accountPage;
	protected SearchResultPage searchResultPage;
	protected ProductInfoPage productInfoPage;
	protected RegistrationPage registrationPage;
	protected CommonsPage commonsPage;
	public Properties prop;
	 
	@Parameters({"browser"})
	@BeforeTest
	public void setUp(@Optional("edge") String browserName) {

		
		DriverFactory df = new DriverFactory();
		prop = df.initProp();
		
		if(browserName!=null) {
			prop.setProperty("browser", browserName);
		}
		
		driver = df.initDriver(prop);
		loginPage = new LoginPage(driver);
		commonsPage = new CommonsPage(driver);
	}
	
	@AfterMethod // will be running after each @test method
	public void attachScreenshot(ITestResult result) {
		
		if (!result.isSuccess()) {// only for failure test cases -- true
			ChainTestListener.embed(DriverFactory.getScreenshotFile(), "image/png");
		}

		//ChainTestListener.embed(DriverFactory.getScreenshotFile(), "image/png");

	}
	

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}

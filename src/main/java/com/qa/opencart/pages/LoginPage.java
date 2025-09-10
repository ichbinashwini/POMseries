package com.qa.opencart.pages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utilities.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	ElementUtil eUtil;
	private static final Logger log = LogManager.getLogger(LoginPage.class); 


	final By emailId = By.xpath("//input[@placeholder='E-Mail Address']");
	final By password = By.xpath("//input[@placeholder='Password']");
	final By loginBtn = By.xpath("//input[@type='submit']");
	final By forgotPasswordLink = By.linkText("Forgotten Password");
	final By registrationLInk = By.linkText("Register");
	private final By loginErrorMessg = By.cssSelector("div.alert.alert-danger.alert-dismissible");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		 eUtil = new ElementUtil(driver);
		 
	}

	@Step("Getting login page title")
	public String getLoginPagetitle() {
		
		String title = driver.getTitle();
		log.info("Login page title is = " + title);
		return title;
	}

	@Step("Getting login page URL")
	public String getLoginPageURL() {
		String url = eUtil.getPageURL();
		log.info("Login page title is = " + url);
		return url;
	}
	

	@Step("Logging in with correct username- {0} and password- {1}")
	public AccountPage doLogin(String userEmail, String userPassword){
		eUtil.doSendKeys(emailId, userEmail);
		eUtil.doSendKeys(password, userPassword);
		eUtil.doClick(loginBtn);
		log.info("Logging with email "+userEmail+" and password "+userPassword);
		return new AccountPage(driver);
		
	}

	@Step("Checking forgot password link available..")
	public boolean isFogotPasswordLinkExists() {

		try {
			driver.findElement(forgotPasswordLink).isDisplayed();
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}

	}
	
	@Step("login with in-correct username: {0} and password: {1}")
	public boolean doLoginWithInvalidCredentails(String invalidUN, String invalidPWD) {
		log.info("Invalid application credentials: " + invalidUN + " : " + invalidPWD);
		WebElement emailEle = eUtil.WaitForElementVisibility(emailId, AppConstants.DEFAULT_MEDIUM_WAIT);
		emailEle.clear();
		emailEle.sendKeys(invalidUN);
		eUtil.doSendKeys(password, invalidPWD);
		eUtil.doClick(loginBtn);
		String errorMessg = eUtil.getElementText(loginErrorMessg);
		log.info("invalid creds error messg: " + errorMessg);
		if (errorMessg.contains(AppConstants.LOGIN_BLANK_CREDS_MESSG)) {
			return true;
		}
		else if (errorMessg.contains(AppConstants.LOGIN_INVALID_CREDS_MESSG)) {
			return true;
		}
		return false;
	}
	
	@Step("Navigetting to regidtration page...")
	public RegistrationPage navigateToRegistration() {
		eUtil.doClick(registrationLInk);
		return new RegistrationPage(driver);
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utilities.ElementUtil;

public class RegistrationPage {
	WebDriver driver;
	ElementUtil eUtil;
	
	public RegistrationPage(WebDriver driver) {
		this.driver= driver;
		eUtil = new ElementUtil(driver);
	}
	private final By firstNameEle = By.id("input-firstname");
	private final By lastNameEle = By.id("input-lastname");
	private final By email = By.id("input-email");
	private final By telephone = By.id("input-telephone");
	private final By password = By.id("input-password");
	private final By confirmPassword = By.id("input-confirm");
	
	private final By subscribeYes = By.xpath("(//label[@class='radio-inline'])[position()=1]/input[@type='radio']");
	private final By subscribeNo = By.xpath("(//label[@class='radio-inline'])[position()=2]/input[@type='radio']");

	private final By agreeCheckBox = By.name("agree");
	private final By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");
	private final By successMessg = By.cssSelector("div#content h1");
	private final By logoutLink = By.linkText("Logout");
	final By registrationLInk = By.linkText("Register");
	
	public String getRegistrationPageTitle() {
		String title = eUtil.getPageTitle();
		System.out.println("Account page Title--> "+ title);
		 return title;
	}
	
	
	public boolean registerUser(String firstName, String lastName, String email,
							String telephone, String password, String subscribe )  {
		eUtil.WaitForElementVisibility(firstNameEle, AppConstants.DEFAULT_SHORT_WAIT).sendKeys(firstName);;
		eUtil.doSendKeys(lastNameEle, lastName);
		eUtil.doSendKeys(this.email, email);
		eUtil.doSendKeys(this.telephone, telephone);
		eUtil.doSendKeys(this.password, password);
		eUtil.doSendKeys(this.confirmPassword, password);
		
		if(subscribe.equalsIgnoreCase("yes")) {
			eUtil.doClick(subscribeYes);
			
		}else {
			eUtil.doClick(subscribeNo);
		}
		eUtil.doClick(agreeCheckBox);
		eUtil.doClick(continueButton);
		String successText = eUtil.WaitForElementVisibility(successMessg,AppConstants.DEFAULT_SHORT_WAIT).getText();
		
		if(successText.contains(AppConstants.USER_REGISTER_SUCCESS_MESSAGE)) {
			eUtil.doClick(logoutLink);
			eUtil.doClick(registrationLInk);
			return true;
		}else return false;
		
		
	}
	
	
	
	
	
	

}

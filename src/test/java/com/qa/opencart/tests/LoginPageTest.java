package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;



@Epic("EP-20 Login page test")
@Feature("F-101")
@Story("US- 101: Develop login features")
public class LoginPageTest extends BaseTest {
	
	
	
	@Description("login page title test..")
	@Severity(SeverityLevel.NORMAL)
	@Owner("Ashwini")
	
	@Test(priority = 1)
	public void loginPageURLTest() {
		String actualURL = loginPage.getLoginPageURL();
		Assert.assertTrue(actualURL.contains(AppConstants.LOGIN_PAGE_FRACTION_URL));
	}

	@Description("login page title test..")
	@Severity(SeverityLevel.NORMAL)
	@Owner("Ashwini")
	@Test(priority = 2)
	public void loginPageTitleTest() {
		String actualTitle = loginPage.getLoginPagetitle();
		Assert.assertEquals(actualTitle, "Account Login1");

	}
	
	
	@Description("login page title test..")
	@Severity(SeverityLevel.BLOCKER)
	@Owner("Ashwini")
	@Test(priority = 4)
	public void doLoginTest(){
		accountPage = loginPage.doLogin(prop.getProperty("userName"), prop.getProperty("password"));
		String actualTitle = accountPage.accountPageTitle();
		Assert.assertEquals(actualTitle, "My Account");
	}

	@Description("login page title test..")
	@Severity(SeverityLevel.CRITICAL)
	@Owner("Ashwini")
	@Test(priority = 3)
	public void isForgetPasswordExistsTest() {
		Assert.assertTrue(loginPage.isFogotPasswordLinkExists());

	}

}

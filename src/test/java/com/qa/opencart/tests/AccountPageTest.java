package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.pages.AccountPage;

public class AccountPageTest extends BaseTest {

	@BeforeClass
	public void AccountPageTestSetup() {
		accountPage = loginPage.doLogin(prop.getProperty("userName"),prop.getProperty("password"));

	}

	@Test
	public void accountPageTitleTest() {
		String actualAccountPageTitle = accountPage.accountPageTitle();
		Assert.assertEquals(actualAccountPageTitle, "My Account");

	}

	@Test
	public void accountPageMainHeadersTest() {
		List<String> actualHeadersList = accountPage.accountPageMainHeaders();
		Assert.assertEquals(actualHeadersList, AppConstants.EXPECTED_ACCCOUNT_PAGE_HEADERS_lIST);
	}
	
	@Test
	public void doSearchProductTest() throws InterruptedException {
		searchResultPage = accountPage.doSearchProduct("MacBook");
		String actualTitle = searchResultPage.SearchResultPagetitle();
		Assert.assertEquals(actualTitle,"Search - MacBook");
	}

}

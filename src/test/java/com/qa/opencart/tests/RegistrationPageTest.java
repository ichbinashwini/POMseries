package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utilities.CsvUtil;
import com.qa.opencart.utilities.ExcelUtil;
import com.qa.opencart.utilities.StringUtil;

public class RegistrationPageTest extends BaseTest {

	@BeforeClass
	public void registrationPageSetUp() {
		registrationPage = loginPage.navigateToRegistration();
	}

	@Test
	public void registrationPageTitleTest() {
		String actualPageTitle = registrationPage.getRegistrationPageTitle();
		Assert.assertEquals(actualPageTitle, "Register Account");
	}

	@DataProvider
	public Object[][] registrationData() {
		return new Object[][] {
			{"John", "zedar","1224567890", "12345678", "yes"},
			{"Anna", "klerk","1233456890", "12345678", "no"},
			{"Nemo", "zack","1245678900", "12345678", "yes"}
			
		};
		
	}
	
	@DataProvider (name = "test")
	public Object[][] getRegisterSheetData() {
		return ExcelUtil.getTestData("Users");
	}
	
	@DataProvider
	public Object[][] getCSVData(){
		return CsvUtil.csvData("register");
	}
	
	
	@Test (dataProvider = "test")
	public void registerUserTest(String fName,String lname, 
								String telephone, String password,
								String subscribe) {
		boolean actualSuccessHeader = registrationPage.registerUser(fName, lname, 
															StringUtil.getRandomEmail(),
															telephone, password,subscribe);
		Assert.assertTrue(actualSuccessHeader);
	}

}

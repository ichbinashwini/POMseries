package com.qa.opencart.pages;

import org.openqa.selenium.WebDriver;

import com.qa.opencart.utilities.ElementUtil;

public class CartPage {

	WebDriver driver;
	ElementUtil eUtil;
	
	public CartPage(WebDriver driver) {
		this.driver = driver;
		eUtil = new ElementUtil(driver);
	}
}

package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utilities.ElementUtil;

public class SearchResultPage {
	
	WebDriver driver;
	ElementUtil eUtil;
	
	By header = By.tagName("h1");
	
	public SearchResultPage (WebDriver driver) {
		this.driver = driver;
		eUtil = new ElementUtil(driver);
		
	}

	public String SearchResultPagetitle() throws InterruptedException {
		return eUtil.waitforTitleContains("MacBook", AppConstants.DEFAULT_MEDIUM_WAIT);
	}
	
	public String SearchResultPageHeader() {
		return eUtil.getElementText(header);
		
	}
	
	public ProductInfoPage selectProduct(String productName) {
		System.out.println("Selected product is --> "+productName);
		eUtil.doClick(By.linkText(productName));
		return new ProductInfoPage(driver);
	}
}

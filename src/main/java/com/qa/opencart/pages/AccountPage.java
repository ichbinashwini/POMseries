package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utilities.ElementUtil;

public class AccountPage {
	WebDriver driver;
	ElementUtil eUtil;

	private By headers = By.tagName("h2");
	private By searchInputField = By.cssSelector("input[name='search']");
	private By searchIcon = By.xpath("//div[@id='search']//button");

	public AccountPage(WebDriver driver) {
		this.driver = driver;
		eUtil = new ElementUtil(driver);
	}

	public String accountPageTitle() {
		String title = eUtil.getPageTitle();
		System.out.println("Accout page title is " + title);
		return title;
	}

	public List<String> accountPageMainHeaders() {
		List<WebElement> headerWebEle = eUtil.waitForElementsPresent(headers, AppConstants.DEFAULT_SHORT_WAIT);
		List<String> headerList = new ArrayList<>();
		for (WebElement each : headerWebEle) {
			String header = each.getText();
			headerList.add(header);
		}
		System.out.println("Headers on Account page are " + headerList);
		return headerList;
	}
	
	public SearchResultPage doSearchProduct(String searchKey) {
		System.out.println("Searched product is --> "+ searchKey);
		WebElement searchEle = eUtil.WaitForElementVisibility(searchInputField, AppConstants.DEFAULT_SHORT_WAIT);
		searchEle.clear();
		eUtil.doSendKeys(searchInputField, searchKey);
		eUtil.doClick(searchIcon);
		System.out.println("This is "+ searchKey +"search page title "+ eUtil.getPageTitle());
		return new SearchResultPage(driver);
		
	}
	
	
	
	
	
	

}




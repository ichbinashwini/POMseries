package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utilities.ElementUtil;

public class ProductInfoPage {

	WebDriver driver;
	ElementUtil eUtil;
	
	private By quantity = By.xpath("//input[@id='input-quantity']");
	private By header = By.tagName("h1");
	private By images = By.cssSelector(".thumbnails img");
	private By productMeta = By.xpath("(//div[@id='content']//ul)[3]/li");
	private By productPrice = By.xpath("(//div[@id='content']//ul)[4]/li");
	
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eUtil = new ElementUtil(driver);
		
	}
	public String productQuantityinCart() {
		
		String defaultQuantity = driver.findElement(quantity).getDomAttribute("value");
		//String defaultQuantity = eUtil.getElementText(quantity);
		System.out.println("Default quantity while on product info page --> "+ defaultQuantity);
		return defaultQuantity;
	}
	
	public String productHeader() {
		String headervalue = eUtil.getElementText(header);
		System.out.println("Product header after search is --> "+ headervalue);
		return headervalue;
		
	}
	
	
	public int productImageCount() {
		List<WebElement> imageList = eUtil.WaitForElementsVisibility(images, AppConstants.DEFAULT_SHORT_WAIT);
		int imageCount =  imageList.size();
		System.out.println("Number of images of the product --> "+imageCount);
		return imageCount;
	}
	
	
	public void productMetaData() {
		List<WebElement> data = eUtil.WaitForElementsVisibility(productMeta, AppConstants.DEFAULT_SHORT_WAIT);
		
		Map<String, String> metaDataMap = new HashMap<>();
		
		for (WebElement each : data) {
			String dataString = each.getText();
			String[] meta = dataString.split(":");
			String metaKey = meta[0];
			String metaValue = meta[1];
			metaDataMap.put(metaKey, metaValue);
			
		}
		
		System.out.println("This is product Meta data --> "+metaDataMap);
	}
	
	public void productPriceData() {
		List<WebElement> priceData = eUtil.WaitForElementsVisibility(productPrice, AppConstants.DEFAULT_SHORT_WAIT);
		//[602.00 , ex tax:500.00]
		Map<String, String> priceMap = new HashMap<>();
		String price = priceData.get(0).getText(); //$602.00
		String exPrice = priceData.get(1).getText(); //ex tax:500.00
		String[] exPriceKeyValue = exPrice.split(":"); //["ex tax", "500.00"]
		//String exP = exPriceKeyValue[0]; //"ex tax"
		//String exP = exPriceKeyValue[1]; //"500.00"
		
		priceMap.put("Price", price);
		priceMap.put(exPriceKeyValue[0], exPriceKeyValue[1]);
		System.out.println("This is product price data--> "+priceMap);
		
	}
	
	
	

}

package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class SearchResultPageTest extends BaseTest {

	@BeforeClass
	public void SearchResultPageSetup() {
		accountPage = loginPage.doLogin(prop.getProperty("userName"), prop.getProperty("password"));

	}

	@Test
	public void searchResultPagetitleTest() throws InterruptedException {
		searchResultPage = accountPage.doSearchProduct("MacBook");
		String actualSearchResultPagetitle = searchResultPage.SearchResultPagetitle();
		Assert.assertEquals(actualSearchResultPagetitle, "Search - MacBook");
	}
	
	@Test
	public void searchResultPageHeadertest() {
		searchResultPage = accountPage.doSearchProduct("macbook");
		String header = searchResultPage.SearchResultPageHeader();
		Assert.assertEquals(header, "Search - macbook");
		
	}
	
	
	

//	@DataProvider(name = "quantityData")
//	public Object[][] getproductQuantityinCartData() {
//		return new Object[][] { { "MacBook", "MacBook", 1 },{ "iphone", "iPhone", 1 },
//				//{ "canon", "Canon EOS 5D", 1 }
//			};
//
//	}
//
//	@Test (dataProvider = "quantityData")
//	public void productQuantityinCartTest(String searchKey, String productName, int cartQuantity) {
//		searchResultPage = accountPage.doSearchProduct(searchKey);
//		productInfoPage = searchResultPage.selectProduct(productName);
//		String quantity = productInfoPage.productQuantityinCart();
//		int acqualQuantity = Integer.parseInt(quantity);
//		Assert.assertEquals(acqualQuantity, cartQuantity);
//	}

}

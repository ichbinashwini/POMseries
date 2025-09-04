package com.qa.opencart.tests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductInfoPageTest extends BaseTest{
	
	@BeforeClass
	public void ProductInfoPageTestSetup() {
		accountPage = loginPage.doLogin(prop.getProperty("userName"),prop.getProperty("password"));
	}
	
	
	@DataProvider(name = "productData") 
	public Object[][] getProductdata() {
		return new Object[][] {
				{"MacBook", "MacBook", 5},
				{"MacBook", "MacBook Air", 4},
				{"MacBook", "MacBook Pro", 4},
				{"canon", "Canon EOS 5D", 3}
				};
	}
	
	
	@Test (dataProvider = "productData")
	public void productImageCountTest(String searchKey, String productname, int expImgCount) {
		searchResultPage = accountPage.doSearchProduct(searchKey);
		productInfoPage = searchResultPage.selectProduct(productname);
		int actualCount = productInfoPage.productImageCount();
		Assert.assertEquals(actualCount, expImgCount);
	}

	@Test
	public void productMetaData() {
		searchResultPage = accountPage.doSearchProduct("MacBook");
		productInfoPage = searchResultPage.selectProduct("MacBook");
		productInfoPage.productMetaData();
		productInfoPage.productPriceData();
		
	}
	
	@DataProvider(name = "quantityData")
	public Object[][] getproductQuantityinCartData() {
		return new Object[][] { { "MacBook", "MacBook", 1 },{ "iphone", "iPhone", 1 },
				//{ "canon", "Canon EOS 5D", 1 }
			};

	}

	@Test (dataProvider = "quantityData")
	public void productQuantityinCartTest(String searchKey, String productName, int cartQuantity) {
		searchResultPage = accountPage.doSearchProduct(searchKey);
		productInfoPage = searchResultPage.selectProduct(productName);
		String quantity = productInfoPage.productQuantityinCart();
		int acqualQuantity = Integer.parseInt(quantity);
		Assert.assertEquals(acqualQuantity, cartQuantity);
	}
	
	
}

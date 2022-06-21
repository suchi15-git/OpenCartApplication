package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.CommonPage;
import com.qa.opencart.ProductInfoPage;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.DescriptionConstants;

public class ProductInfoTest extends BaseTest {
	
	@BeforeClass
	public void productInfosetUp() {
		comPage = new CommonPage(driver);
		productInfoPage = new ProductInfoPage(driver);
	}
	
	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] {
			{"Macbook", "MacBook Pro",4},
			{"Macbook", "MacBook Air", 4},
			{"Samsung", "Samsung SyncMaster 941BW",1}
			
		};
	}

	@Test (dataProvider = "getProductData")
	public void productImagesCountTest(String searchKey, String productName,int imagesCount ) {	
		searchResultsPage = comPage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProductName(productName);
		Assert.assertEquals(productInfoPage.getProductImageCount(), imagesCount);
	}
	
	@Test(enabled=false)
	public void productDescriptionTest() {
		searchResultsPage = comPage.doSearch("Macbook");
		productInfoPage = searchResultsPage.selectProductName("MacBook Pro");
		String actDes = productInfoPage.getProductDescription();
		System.out.println("Product Desc:"  +actDes);
		
		softAssert.assertTrue(actDes!=null);
		softAssert.assertFalse(actDes.isEmpty());
		softAssert.assertTrue(actDes.contains(DescriptionConstants.MACBOOK_PRO_DESCRIPTION));
		softAssert.assertAll();
	}
	
	@Test
	public void productDataTest() {
		searchResultsPage = comPage.doSearch("Macbook");
		productInfoPage = searchResultsPage.selectProductName("MacBook Pro");
		Map<String , String> actProductInforMap = productInfoPage.getProductInfo();
		actProductInforMap.forEach((k,v)-> System.out.println(k + ":" + v));
		
		softAssert.assertEquals(actProductInforMap.get("Brand"), "Apple");
		softAssert.assertAll();
	}

}

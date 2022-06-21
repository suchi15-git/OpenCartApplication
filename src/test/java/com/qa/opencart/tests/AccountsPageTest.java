package com.qa.opencart.tests;

import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.AccountsPage;
import com.qa.opencart.CommonPage;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accSetUp() {
		//accPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		accPage = new AccountsPage(driver);
	}
	
	@Test (enabled=false)
	public void accountPageTitleTest() {
		
		Assert.assertEquals(accPage.getAccountPageTitle(), Constants.ACCOUNT_PAGE_TITLE);
	}
	
	@Test(enabled=false)
	public void accountPageHeaderTest() {
		Assert.assertEquals(accPage.getAccountPageHeader(), Constants.ACCOUNT_PAGE_HEADER);
	}
	
	@Test(enabled=false)
	public void accPageSectionsHeaderTest() {
		List<String>  accAccountListScreen = accPage.getAccountSectionList();
		System.out.println("Actual accounts header list :" +accAccountListScreen);
		Collections.sort(accAccountListScreen);
		Collections.sort( Constants.ACCOUNTS_PAGE_SECTION_HEADER_LIST);
		Assert.assertEquals(accAccountListScreen, Constants.ACCOUNTS_PAGE_SECTION_HEADER_LIST);
	}
	
	@Test(enabled=false)
	public void isUserLoggedOut() {
		accPage.clickLogoutLink();
		Assert.assertEquals(loginPage.getLogoutMessage(), Constants.USER_LOGOUT_MESSAGE);
	}
	
	@DataProvider
	public Object[][] getProductName() {
		return new Object[][] {
			{"macbook"},
			{"iMac"},
			{"Samsung"},
			{"Apple"}
		};
	
	}
	
	@DataProvider
	public Object[][] getMainProductData() {
//		return new Object[][] {
//			{"Macbook","MacBook Pro"},
//			{"Macbook", "MacBook Air"},
//		};
		
		
		return ExcelUtil.getTestData("product");
	}
	
	
	@Test(dataProvider ="getProductName")
	public void searchTest(String productName) {
		comPage = new CommonPage(driver);
		searchResultsPage = comPage.doSearch(productName);
		String resultsPageHeader = searchResultsPage.getResultsPageHeader();
		Assert.assertTrue(resultsPageHeader.contains(productName));
	}
	
	@Test(dataProvider ="getMainProductData")
	public void productInfoTest(String productName, String mainProductName) {
		comPage = new CommonPage(driver);
		searchResultsPage = comPage.doSearch(productName);
		String resultsPageHeader = searchResultsPage.getResultsPageHeader();
		productInfoPage = searchResultsPage.selectProductName(mainProductName);
		String mainProductNameValue  = productInfoPage.getMainProductName();
		System.out.println(mainProductNameValue);
		Assert.assertEquals(mainProductNameValue, mainProductName);
	}
	
}
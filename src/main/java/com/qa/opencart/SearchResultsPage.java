package com.qa.opencart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultsPage {

	private WebDriver driver;
	private ElementUtil eUtil;
	
	
	private By resultsPageHeader = By.cssSelector("div#content h1");
	
	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eUtil = new ElementUtil(driver);
	}
	
	public String getResultsPageHeader() {
		String header  = eUtil.waitForElementVisible(resultsPageHeader, Constants.DEFAULT_ELEMENT_TIME_OUT)
		.getText();
		return header;
	}
	
	public ProductInfoPage selectProductName(String productName) {
		WebElement mainProductEle = eUtil.waitForElementVisible(By.linkText(productName), Constants.DEFAULT_ELEMENT_TIME_OUT);
		mainProductEle.click();
		return new ProductInfoPage(driver);
	}
}

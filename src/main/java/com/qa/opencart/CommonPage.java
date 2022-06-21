package com.qa.opencart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class CommonPage {
	
	protected WebDriver driver;
	private ElementUtil eUtil;
	
	private By search = By.name("search");
	private By searchButton = By.cssSelector("div#search button");
	
	
	public CommonPage(WebDriver driver) {
		this.driver = driver;
		eUtil = new ElementUtil(driver);
	}
	
	public SearchResultsPage doSearch(String productName) {
		WebElement searchEle = eUtil.waitForElementVisible(search, Constants.DEFAULT_ELEMENT_TIME_OUT);
		searchEle.clear();
		searchEle.sendKeys(productName);
		eUtil.doClick(searchButton);
		
		return new SearchResultsPage(this.driver);
	}
	
	
	
	

}

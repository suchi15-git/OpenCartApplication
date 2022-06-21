package com.qa.opencart;

import java.sql.Array;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eUtil;


	private By mainProductName = By.cssSelector("div#content h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By productDescription = By.cssSelector("div#tab-description");
	private By productMetaData = By.xpath("//div[@id='content']//ul[@class='list-unstyled'][position()=1]");
	private By productPriceData = By.xpath("//div[@id='content']//ul[@class='list-unstyled'][position()=2]");

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eUtil = new ElementUtil(driver);
	}

	public String getMainProductName() {
		return eUtil.doGetElementText(mainProductName);
	}
	
	public int getProductImageCount() {
		return eUtil.waitForElementsVisible(productImages, Constants.DEFAULT_ELEMENT_TIME_OUT).size();
	}
	
	public String getProductDescription() {
		//return eUtil.doGetElementText(productDescription);
		return eUtil.waitForElementVisible(productDescription, Constants.DEFAULT_ELEMENT_TIME_OUT).getText();
	}
	
	public Map<String,String> getProductInfo() {
		
		//Map<String, String> productInfoMap = new HashMap<String,String>(); //randomOrder
		//Map<String, String> productInfoMap = new LinkedHashMap<String,String>(); //order based
		Map<String, String> productInfoMap = new TreeMap<String,String>(); //sorted order 
		productInfoMap.put("name", getMainProductName());
		
		List<WebElement> metaList = eUtil.getElements(productMetaData);
		for(WebElement e:metaList) {
			String metaData = e.getText();
			String metaKey =  metaData.split(":")[0].trim();
			String metaValue =  metaData.split(":")[1].trim();
			
			productInfoMap.put(metaKey,metaValue);
		
		}
		
		List<WebElement> priceList = eUtil.getElements(productPriceData);
		String price = priceList.get(0).getText().trim();
		String exTaxPrice = priceList.get(1).getText().trim();
		productInfoMap.put("price", price);
		productInfoMap.put("exTaxPrice", exTaxPrice);
		
		return productInfoMap;
		
	}

}

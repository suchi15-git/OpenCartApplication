package com.qa.opencart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {

	private WebDriver driver;
	
	//1. private by locators
	By forgottenPasswordMessage = By.cssSelector("div#content h1");
	By emailAddress = By.id("input-email");
	By continueBtn = By.xpath("//input[@value='Continue']");
	
	
	//2. public page class constructor
	public ForgotPasswordPage(WebDriver driver) {
		this.driver = driver;
	}
	
	//3. public page actions/method
	public String getForgotPasswordPageTitle() {
		String title = driver.getTitle();
		System.out.println("ForgotPassword page title is:" + title);
		return title;
	}
	
	
	public String getForgotPasswordURL() {
		String url = driver.getCurrentUrl();
		System.out.println("ForgotPassword page url is:"+url);
		return url;
	}
	
	public void enterEmailAddress(String emailAdd) {
		driver.findElement(emailAddress).sendKeys(emailAdd);
	}
	
	public void clickContinueBtn() {
		driver.findElement(continueBtn).click();
	}
}

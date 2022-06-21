package com.qa.opencart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {
	
	private WebDriver driver;
	private ElementUtil eUtil;
	
	//locators
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By emailAdd = By.id("input-email");
	private By telephoneNum = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmPassword = By.id("input-confirm");
	private By confirmCheckbox = By.name("agree");
	
	private By subscribeYes = By.xpath("(//label[@class='radio-inline'])[position()=1]/input[@type='radio']");
	private By subscribeNo = By.xpath("(//label[@class='radio-inline'])[position()=2]/input[@type='radio']");
	private By continueBtn = By.xpath("//input[@class='btn btn-primary']");
	
	private By successMessage = By.cssSelector("div#content h1");
	private By registerLink = By.linkText("Register");
	private By logoutLink = By.linkText("Logout");
	
			

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eUtil = new ElementUtil(driver);
	}
	
	public boolean userRegister(String firstname, String lastname
			,String email,String telephone, String password, String subscribe) {
		
		eUtil.doSendKeys(this.firstName, firstname);
		eUtil.doSendKeys(this.lastName, lastname);
		eUtil.doSendKeys(this.emailAdd, email);
		eUtil.doSendKeys(this.telephoneNum, telephone);
		eUtil.doSendKeys(this.password, password);
		eUtil.doSendKeys(this.confirmPassword, password);
		
		if(subscribe.equalsIgnoreCase("Yes")) {
			eUtil.doClick(subscribeYes);
		}
		else {
			eUtil.doClick(subscribeNo);
		}
		
		eUtil.doClick(confirmCheckbox);
		eUtil.doClick(continueBtn);
		
		String successmessage = eUtil.waitForElementVisible(successMessage, Constants.DEFAULT_ELEMENT_TIME_OUT)
				.getText();
		System.out.println(successMessage);
		if(successmessage.contains(Constants.REGISTER_SUCCESS_MESSAGE)) {
			eUtil.doClick(logoutLink);
			eUtil.doClick(registerLink);
			return true;
		}
		else {
		return false;
		}
	
	}
	


}

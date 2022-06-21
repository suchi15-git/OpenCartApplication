package com.qa.opencart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eUtil;
	
	//1. private by locators
	
	private By emailID = By.id("input-email");
	private By pwd = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPWLink = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");
	private By accountLogout = By.cssSelector("div#content h1");
	private By forgotPasswordLink = By.linkText("Forgotten Password");
	
	//2. public page class constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eUtil = new ElementUtil(this.driver);
	}

	//3. public page actions/method
	@Step("getting login page title")
	public String getLoginPageTitle() {
		String title = eUtil.waitForTitleIs(Constants.LOGIN_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
		System.out.println("Login page title is:" + title);
		return title;
	}
	@Step("getting login page URL")
	public String getLoginPageURL() {
		String URL =eUtil.waitForURLContains(Constants.LOGIN_PAGE_URL_FRACTION, Constants.DEFAULT_TIME_OUT);
		//String URL = eUtil.waitForURLContains(Constants.LOGIN_PAGE_URL_FRACTION, Constants.DEFAULT_TIME_OUT);
		System.out.println("Login page URL is:" + URL);
		return URL;
	}
	
	@Step("checking forgot password link")
	public Boolean isForgotPasswordLinkExist() {
		return eUtil
				.waitForElementVisible(forgotPasswordLink, Constants.DEFAULT_ELEMENT_TIME_OUT)
				.isDisplayed();
	
	}
	@Step("checking registration link")
	public Boolean isRegisterLinkExist() {
		return eUtil.waitForElementVisible(registerLink, Constants.DEFAULT_ELEMENT_TIME_OUT)
		.isDisplayed();
	
	}
	
	@Step("login with user{0} and password {1}")
	public AccountsPage doLogin(String username, String password) {
		eUtil.waitForElementVisible(emailID, Constants.DEFAULT_ELEMENT_TIME_OUT)
		.sendKeys(username);
		eUtil.doSendKeys(pwd, password);
		eUtil.doClick(loginBtn);
		return new AccountsPage(driver);
	}
	
	@Step("checking logout link")
	public String getLogoutMessage() {
		String logoutMessage = eUtil.waitForElementVisible(accountLogout, Constants.DEFAULT_TIME_OUT).getText();
		System.out.println("Logout successful message"+logoutMessage);
		return logoutMessage;
	}
	
	public ForgotPasswordPage forgotPasswordLinkClick() {
		eUtil.waitForElementVisible(forgotPasswordLink, Constants.DEFAULT_ELEMENT_TIME_OUT).click();
		return new ForgotPasswordPage(driver);
	}
	
	public RegisterPage navigateToRegisterPage() {
		eUtil.doClick(registerLink);
		return new RegisterPage(driver);
	}
}

package com.qa.opencart;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	private WebDriver driver;
	private ElementUtil eUtil;

	private By header = By.linkText("Account");
	private By accountSectionHeader = By.cssSelector("div#content h2");
	private By logoutLink = By.linkText("Logout");
	private By search = By.name("search");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eUtil = new ElementUtil(this.driver);

	}

	public String getAccountPageTitle() {
		return eUtil.waitForTitleIs(Constants.ACCOUNT_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);

	}

	public String getAccountPageURL() {
		return eUtil.waitForTitleURLIs(Constants.LOGIN_PAGE_URL_FRACTION, Constants.DEFAULT_TIME_OUT);

	}

	public String getAccountPageHeader() {
		return eUtil.waitForElementVisible(header, Constants.DEFAULT_ELEMENT_TIME_OUT).getText();

	}

	public List<String> getAccountSectionList() {
		List<WebElement> acc = eUtil.waitForElementsVisible(accountSectionHeader ,Constants.DEFAULT_ELEMENT_TIME_OUT);

		List<String> headers = new ArrayList<String>();
		for (WebElement e : acc) {
			String text = e.getText();
			headers.add(text);
		}

		return headers;
	}

	public Boolean isLogoutLinkExist() {
		return eUtil.waitForElementVisible(logoutLink, Constants.DEFAULT_ELEMENT_TIME_OUT).isDisplayed();

	}

	public Boolean isSearchLinkExist() {
		return eUtil.waitForElementVisible(search, Constants.DEFAULT_ELEMENT_TIME_OUT).isDisplayed();

	}

	public LoginPage clickLogoutLink() {
		if (isLogoutLinkExist()) {

			eUtil.doClick(logoutLink);
			return new LoginPage(driver);

		}

	return null;
	}
}

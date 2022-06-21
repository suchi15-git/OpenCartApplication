package com.qa.opencart.tests;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.AccountsPage;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic-100 Design login page for open cart application")
@Story("US-101: Design login page features")
public class LoginPageTest extends BaseTest {

	WebDriver driver;

	@Description("Login page title test")
	@Severity (SeverityLevel.NORMAL)
	@Test (priority=1)
	public void loginPageTitleTest() {
		String actualTtitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actualTtitle, Constants.LOGIN_PAGE_TITLE);
	}

	@Description("Verify login page URL test")
	@Severity(SeverityLevel.MINOR)
	@Test (priority=2)
	public void loginPageURLTest() {
		String actualURL = loginPage.getLoginPageURL();
		Assert.assertTrue(actualURL.contains(Constants.LOGIN_PAGE_URL_FRACTION));
	}
	
	@Description("Verify forgot password link test")
	@Severity(SeverityLevel.CRITICAL)
	@Test (priority=3)
	public void forgotPWlinkExistTest() {
		Assert.assertTrue(loginPage.isForgotPasswordLinkExist());
	}
	
	@Description("Verify registration link test")
	@Severity(SeverityLevel.CRITICAL)
	@Test (priority=4)
	public void registerLinkExistTest() {
		Assert.assertTrue(loginPage.isRegisterLinkExist());
	}
	
	@Description("Verify that user is able to login into application with correct creds")
	@Severity(SeverityLevel.CRITICAL)
	@Test (priority=5)
	public void loginTest() {
		AccountsPage accPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		String title  = accPage.getAccountPageTitle();
		System.out.println(title);
		Assert.assertEquals(title, Constants.ACCOUNT_PAGE_TITLE);
		
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
}

package com.qa.opencart.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;

public class ForgotPasswordPageTest extends BaseTest{
	
	WebDriver driver;


	@Test (priority=1)
	public void forgotPasswordPageTitleTest() {
		String actualTtitle = forgotPasswordPage.getForgotPasswordPageTitle();
		System.out.println(actualTtitle);
		//Assert.assertEquals(actualTtitle, Constants.LOGIN_PAGE_TITLE);
	}
	

	@Test (priority=2)
	public void forgotPasswordPageURLTest() {
		String actualURL = forgotPasswordPage.getForgotPasswordURL();
		System.out.println(actualURL);
		//Assert.assertTrue(actualURL.contains(Constants.LOGIN_PAGE_URL_FRACTION));
	}
	
	@Test (priority=3)
	public void enterEmailAddress(String emailAdd) {
		forgotPasswordPage.enterEmailAddress(emailAdd);
	}
	
	@Test (priority=3)
	public void clickContinue() {
		forgotPasswordPage.clickContinueBtn();
	}
	
	

}

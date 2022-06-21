package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.AccountsPage;
import com.qa.opencart.CommonPage;
import com.qa.opencart.ForgotPasswordPage;
import com.qa.opencart.LoginPage;
import com.qa.opencart.ProductInfoPage;
import com.qa.opencart.RegisterPage;
import com.qa.opencart.SearchResultsPage;
import com.qa.opencart.factory.DriverFactory;

public class BaseTest {
	protected LoginPage loginPage;
	protected AccountsPage accPage;
	protected ForgotPasswordPage forgotPasswordPage;
	protected CommonPage comPage;
	protected SearchResultsPage searchResultsPage;
	protected  ProductInfoPage productInfoPage;
	protected RegisterPage registerPage;
	DriverFactory df;
	protected Properties prop;
	protected WebDriver driver;
	
	protected SoftAssert softAssert;
	
	@BeforeTest
	public void setUp() {
		df = new DriverFactory();
		prop = df.init_properties();
		driver = df.init_driver(prop);
		loginPage = new LoginPage(driver);
		accPage = new AccountsPage(driver);
		forgotPasswordPage = new ForgotPasswordPage(driver);
		comPage = new CommonPage(driver);
		searchResultsPage = new SearchResultsPage(driver);
		productInfoPage = new ProductInfoPage(driver);
		registerPage = new RegisterPage(driver);
		softAssert = new SoftAssert();
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}

package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest {

	@BeforeClass

	public void regSetUp() {
		registerPage = loginPage.navigateToRegisterPage();
	}
	
	public String getRandomEmailID() {
		Random random = new Random();
		String email = "febautomation_suchi" +random.nextInt(1000)+ "@gmail.com";
		return email;
	}

	@DataProvider
	public Object[][] getRegisterTestData() {
		Object regData [][] = ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
		return regData;
	}
	
	@Test(dataProvider="getRegisterTestData")
	public void userRegisterTest(String firstname, String lastname, String telephone, String password , String subscribe) {
		Assert.assertTrue(registerPage.userRegister(firstname, lastname, getRandomEmailID(), telephone, password, subscribe));
		
	}
}

package com.qa.opencart.utils;

import java.util.Arrays;
import java.util.List;

public class Constants {

	public static final String LOGIN_PAGE_TITLE="Account Login";
	public static final String ACCOUNT_PAGE_TITLE="My Account";
	public static final String ACCOUNT_PAGE_HEADER="Account";
	public static final String LOGIN_PAGE_URL_FRACTION="account/login";
	public static final String REGISTER_SUCCESS_MESSAGE = "Your Account Has Been Created!";
	
	/***********sheet names***************/
	public static final String REGISTER_SHEET_NAME="register";
	
	public static final List<String> ACCOUNTS_PAGE_SECTION_HEADER_LIST = Arrays.asList("My Account",
			"My Orders",
			"My Affiliate Account",
			"Newsletter");
	
	public static final String USER_LOGOUT_MESSAGE = "Account Logout";
	public static final int DEFAULT_TIME_OUT=5;
	public static final int DEFAULT_ELEMENT_TIME_OUT=5;
}

package com.qa.opencart.utils;

import java.util.Arrays;
import java.util.List;

public class AppConstants {
	
	public static final String LOGIN_PAGE_TITLE = "Account Login";
	
	public static final String LOGIN_PAGE_FRACTION_URL = "?route=account/login";
	
	public static final String ACCOUNT_PAGE_TITLE = "My Account";
	
	public static final String ACC_PAGE_FRACTION_URL = "?route=account/account";
	
	public static final List<String> EXPECTED_ACC_HEADERS_LIST=Arrays.asList("My Account", "My Orders", "My Affiliate Account", "Newsletter");
	
	public static final String SEARCH_RESULTS_PAGE_TITLE = "Search -";
	
	public static final String ACCOUNT_REGISTER_SUCCESS_MESSAGE = "Your Account Has Been Created!";

	public static final String REGISTER_SHEET_NAME = "register";
}

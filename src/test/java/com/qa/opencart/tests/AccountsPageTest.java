package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.AppErrors;

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accSetUp() {

		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));

	}

	@Test
	public void accPageTitleTest() {
		String actPageTitle = accPage.getAccPageTitle();
		System.out.println("actual Page Title is " + actPageTitle);
		Assert.assertEquals(actPageTitle, AppConstants.ACCOUNT_PAGE_TITLE);
	}

	@Test
	public void AccountPageUrlTest() {
		String actUrl = accPage.getAccPageUrl();
		System.out.println("Account page url is : " + actUrl);
		Assert.assertTrue(actUrl.contains(AppConstants.ACC_PAGE_FRACTION_URL), AppErrors.NO_URL_MATCHED);
	}

	@Test
	public void searchExistTest() {
		System.out.println("----------------searchExistTest");
		Assert.assertTrue(accPage.isSearchExist());
	}

	@Test
	public void logoutExistTest() {
		System.out.println("----------------logoutExistTest");
		Assert.assertTrue(accPage.isLogOutExist());
	}

	@Test
	public void accPageHeadersTest() {
		System.out.println("----------------accPageHeadersTest");
		List<String> actheadersLists = accPage.getAccountsPageSectionHeaders();
		Assert.assertEquals(actheadersLists, AppConstants.EXPECTED_ACC_HEADERS_LIST);
	}

	@DataProvider(name = "getProductName")
	public Object[][] getProductName() {
		return new Object[][] { { "MacBook" }, { "iMac" }, { "Samsung" }

		};

	}

	// TDD - Test Driven Development Approach
	@Test(dataProvider = "getProductName")
	public void productSearchTest(String productName) {
		System.out.println("Data provider works fine - " + productName);
		resultsPage = accPage.performSearch(productName);
		String actTitle = resultsPage.getSearchResultPageTitle(productName);
		System.out.println("search result page title : " + actTitle);
		softAssert.assertEquals(actTitle, AppConstants.SEARCH_RESULTS_PAGE_TITLE + " " + productName);
		Assert.assertTrue(resultsPage.getSearchProductsCount() > 0);
	}

}

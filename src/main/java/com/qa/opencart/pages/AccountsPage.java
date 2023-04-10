package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	

	private By search = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");// -------------------xpath//div[@id='search']//button
	private By logOutLink = By.linkText("Logout");
	private By accountSecHeaders = By.cssSelector("div#content h2");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil= new ElementUtil(driver);
	}

	public String getAccPageTitle() {

		return eleUtil.waitForTitleIs(AppConstants.ACCOUNT_PAGE_TITLE, TimeUtil.DEFAULT_TIMEOUT);

	}

	public String getAccPageUrl() {

		return eleUtil.waitForurlContains(AppConstants.ACC_PAGE_FRACTION_URL, TimeUtil.DEFAULT_TIMEOUT);

	}

	public boolean isSearchExist() {

		return eleUtil.waitForElementVisible(search, TimeUtil.DEFAULT_TIMEOUT).isDisplayed();

	}

	public boolean isLogOutExist() { 

		return eleUtil.waitForElementVisible(logOutLink, TimeUtil.DEFAULT_TIMEOUT).isDisplayed();

	}

	public List<String> getAccountsPageSectionHeaders() {

		List<WebElement> secHeaderList = eleUtil.waitForElementsVisible(accountSecHeaders, TimeUtil.DEFAULT_TIMEOUT);
		List<String> headerValList = new ArrayList<String>();
		for (WebElement e : secHeaderList) {

			String text = e.getText();
			headerValList.add(text);
		}

		return headerValList;

	}

	public ResultsPage performSearch(String productName) {
		System.out.println("product search for " + productName);
		if (isSearchExist()) {
			eleUtil.doSendKeys(search, productName);
			eleUtil.doClick(searchIcon);
			return new ResultsPage(driver);
		}
		return null;
	}
}

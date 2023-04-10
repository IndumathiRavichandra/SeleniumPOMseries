package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	// 1. private By locators

	private By emailId = By.id("input-email");
	private By psswrd = By.id("input-password");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By loginButton = By.xpath("//input[@value='Login']");
	private By registerLink = By.linkText("Register");

	// 2.page constructor
	public LoginPage(WebDriver driver) {

		this.driver = driver;
		eleUtil = new ElementUtil(driver);

	}

	// 3.page actions

	@Step("Getting login page title")
	public String getLoginPageTitle() {

		return eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, TimeUtil.DEFAULT_TIMEOUT);

	}
	
	@Step("Getting login page url")
	public String getLoginPageUrl() {

		return eleUtil.waitForurlContains(AppConstants.LOGIN_PAGE_FRACTION_URL, TimeUtil.DEFAULT_TIMEOUT);

	}
	
	@Step("login with user name : {0} and password {1}")
	public AccountsPage doLogin(String username, String password) {

		System.out.println("Creds are : " + username + " " + password);
		eleUtil.waitForElementVisible(emailId, TimeUtil.DEFAULT_TIMEOUT).sendKeys(username);
		eleUtil.doSendKeys(psswrd, password);
		eleUtil.doClick(loginButton);
		return new AccountsPage(driver);

	}
	@Step("checking forgot pwd link exist")
	public boolean isforgotPwdLinkExist() {
		return eleUtil.doIsDisplayed(forgotPwdLink);
	}

	@Step("navigating to the Register Page")
	public RegPage navigateToRegisterPage() {

		eleUtil.doClick(registerLink);
		return new RegPage(driver);
	}
}

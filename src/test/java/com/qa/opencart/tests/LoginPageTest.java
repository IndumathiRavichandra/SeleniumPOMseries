package com.qa.opencart.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.AppErrors;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic - 100: Design Lopgin Page for open cart shopping application")
@Story("US-101: Create login page functionality for open cart logi page")
public class LoginPageTest extends BaseTest {

	WebDriver driver;

	@Description("login page title test") // --------------------allure features to add
	@Severity(SeverityLevel.TRIVIAL)
	@Test
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		System.out.println("Login page title is : " + actTitle);
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE, AppErrors.NO_TITLE_MATCHED);
	}

	@Description("login page url test") // --------------------allure features to add
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void loginPageUrlTest() {
		String actUrl = loginPage.getLoginPageUrl();
		System.out.println("Login page url is : " + actUrl);
		Assert.assertTrue(actUrl.contains(AppConstants.LOGIN_PAGE_FRACTION_URL), AppErrors.NO_URL_MATCHED);
	}

	@Description("forgot Pwd Link Exist on Login page")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void forgotPwdLinkExistTest() {
		System.out.println("ForgotPwdLinkExistTest testing................");
		Assert.assertTrue(loginPage.isforgotPwdLinkExist());

	}

	@Description("User able to login on Login page")
	@Severity(SeverityLevel.BLOCKER)
	@Test
	public void loginTest() {
		System.out.println("loginTest testing....................");
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(accPage.isLogOutExist(), AppErrors.LOGIN_UNSUCCESSFUL);
	}

}

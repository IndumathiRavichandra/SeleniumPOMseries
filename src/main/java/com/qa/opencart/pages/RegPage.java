package com.qa.opencart.pages;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class RegPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	

	private By newCusTagHeader = By.tagName("h2");
	private By fpConLink= By.linkText("Continue");
	private By fName = By.name("firstname");
	private By lName = By.id("input-lastname");
	private By email = By.cssSelector("#input-email");
	private By telep = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmPassword = By.id("input-confirm");
	
	private By agreeCheckBox = By.name("agree");
	private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");
	private By subscribeYes = By.xpath("//label[@class='radio-inline']/input[@type='radio' and @value='1']");
	private By subscribeNo = By.xpath("//label[@class='radio-inline']/input[@type='radio' and @value='0']");
	
	
	private By registerSuccessMesg = By.cssSelector("div#content h1");
	private By logOutLink= By.linkText("Logout");
	private By registerLink=By.linkText("Register");
	
	
	public RegPage(WebDriver driver) {
		this.driver = driver;
		eleUtil= new ElementUtil(driver);
	}
	
	
	public boolean registerUser(String frstName, String lastName, String email, String telephone, String password, String subscribe) {
		
		eleUtil.waitForElementVisible(this.fName, TimeUtil.DEFAULT_TIMEOUT).sendKeys(frstName);
		eleUtil.doSendKeys(this.lName, lastName);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.telep, telephone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.confirmPassword, password);
		
		if(subscribe.equalsIgnoreCase("yes")) {
			
			eleUtil.doClick(subscribeYes);
		}
		else {
			eleUtil.doClick(subscribeNo);
		}
			
		eleUtil.doClick(agreeCheckBox);
		eleUtil.doClick(continueButton);
		
		String successMesg = eleUtil.waitForElementVisible(registerSuccessMesg, TimeUtil.DEFAULT_TIMEOUT).getText();
		if(successMesg.contains(AppConstants.ACCOUNT_REGISTER_SUCCESS_MESSAGE)) {
			eleUtil.doClick(logOutLink);
			eleUtil.doClick(registerLink);
			return true;
		}
		else {
			
			eleUtil.doClick(registerLink);
		}
		return false;
	}

}

package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtil {
	private WebDriver driver;
	private JavaScriptUtil jsUtil;

	public ElementUtil(WebDriver driver) {

		this.driver = driver;
		jsUtil = new JavaScriptUtil(driver);
	}

	public WebElement getElement(By locator) {
		//return driver.findElement(locator);

		WebElement element = driver.findElement(locator);
		if(Boolean.parseBoolean(DriverFactory.highlight)){
			jsUtil.flash(element);
		}
		
		return element;
	}

	
	public void doSendKeys(By locator, String value) {
		WebElement ele = getElement(locator);
		ele.clear();
		ele.sendKeys(value);

	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public String doGetElementText(By locator) {
		return getElement(locator).getText();
	}
	
	

	public String doGetAttributeValue(By locator, String name) {

		return getElement(locator).getAttribute(name);
	}

	public boolean doIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();

	}

	public List<WebElement> getElements(By locator) {

		return driver.findElements(locator);

	}
	
	

	public void doClickOnElement(By locator, String linkText) {

		List<WebElement> footerLinklist = driver.findElements(locator);

		System.out.println(footerLinklist.size());

		for (WebElement e : footerLinklist) {

			String text = e.getText();
			System.out.println(text);

			if (text.contains("linkText")) {

				e.click();

			}

		}
	}

	public boolean IsSingleElementexists(By locator) {

		int actualCount = getElements(locator).size();
		System.out.println("actualCount" + " " + actualCount);
		if (actualCount == 1) {
			return true;

		}
		return false;

	}

	public boolean IsTwoElementexists(By locator) {

		int actualCount = getElements(locator).size();
		System.out.println("actualCount" + " " + actualCount);
		if (actualCount == 2) {
			return true;

		}
		return false;

	}

	public boolean IsMultipleElementexists(By locator, int expectedElementCount) { // or use if (actualCount >1) {

		int actualCount = getElements(locator).size();
		System.out.println("actualCount" + " " + actualCount);
		if (actualCount == expectedElementCount) {
			return true;

		}
		return false;

	}

	public List<String> getElementsTextList(By locator) {

		List<WebElement> footerList = getElements(locator);
		List<String> eleTextList = new ArrayList<String>();

		for (WebElement e : footerList) {

			String text = e.getText();
			eleTextList.add(text);

		}

		return eleTextList;

	}

	// *************************drop down utils - select based Drop
	// down*************************//

	public void doSelectDropDownValue(By locator, String value) {

		Select select = new Select(getElement(locator));
		select.selectByValue(value);

	}

	public void doSelectDropDownByVisbleText(By locator, String text) {

		Select select = new Select(getElement(locator));
		select.selectByVisibleText(text);

	}

	public void doSelectDropDownIndex(By locator, int index) {

		Select select = new Select(getElement(locator));
		select.selectByIndex(index);

	}

	public void doSelectDropDownByValue(By locator, String value) {

		Select select = new Select(getElement(locator));

		List<WebElement> optionsList = select.getOptions();
		System.out.println(optionsList.size());

		for (WebElement e : optionsList) {
			String text = e.getText();
			System.out.println(text);

			if (text.equals(value)) {

				e.click();
				break;

			}

		}

	}

	// *************************Action Class*************************//

	public void selectSubMenu(String htmltag, String parentMenu, String childMenu) throws InterruptedException {

		By parentMenuLocator = By.xpath("//" + htmltag + "[text()='" + parentMenu + "']");

		By childMenuLocator = By.xpath("//" + htmltag + "[text()='" + childMenu + "']"); // " + --- + " ---- if div
																							// changes later

		WebElement parentMenuElement = getElement(parentMenuLocator);

		Actions act = new Actions(driver);

		act.moveToElement(parentMenuElement).build().perform();

		Thread.sleep(2000);

		doClick(childMenuLocator);
	}

	public void doActionSendKeys(By locator, String value) {

		Actions act = new Actions(driver);

		act.sendKeys(getElement(locator), value).build().perform();
	}

	public void doActionClick(By locator) {

		Actions act = new Actions(driver);

		act.click(getElement(locator)).build().perform();
	}

	// ******************************************Wait
	// Utils**************************************

	public WebElement waitForElementPresence(By locator, int timeOut) {///////////////////// ElementPresent

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public WebElement waitForElementVisible(By locator, int timeOut) {///////////////////// Element Visible

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public String waitForTitleContains(String titleFractionValue, int timeOut) { ////////////////// title Contains

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if (wait.until(ExpectedConditions.titleContains(titleFractionValue))) {

			return driver.getTitle();
		}

		else {

			System.out.println("not visible");
			return null;
		}

	}

	public String waitForTitleIs(String titleValue, int timeOut) {////////////////// Exact title Is

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if (wait.until(ExpectedConditions.titleIs(titleValue))) {

			return driver.getTitle();
		}

		else {

			System.out.println("not visible");
			return null;
		}

	}

	public String waitForurlContains(String urlValue, int timeOut) {// url Contains

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if (wait.until(ExpectedConditions.urlContains(urlValue))) {

			return driver.getCurrentUrl();
		}

		else {

			System.out.println("incorrect url");
			return null;
		}

	}

	public String waitForurlIs(String urlValue, int timeOut) {// Exact url

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if (wait.until(ExpectedConditions.urlToBe(urlValue))) {

			return driver.getCurrentUrl();
		}

		else {

			System.out.println("incorrect url");
			return null;
		}

	}

	// WEBDRIVER WAIT
	public Alert waitForAlertPresentAndSwitch(int timeOut) {// Exact title

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.alertIsPresent());

	}

	// FLUENTWAIT
	public Alert waitForAlertPresentAndSwitchWithFluentWait(int timeOut, int intervalTimeOut) {// Exact title

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(intervalTimeOut)).ignoring(ElementNotInteractableException.class)
				.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class)
				.withMessage("Element Not Found");

		return wait.until(ExpectedConditions.alertIsPresent());

	}

	public String getAlertText(int timeOut) {
		return waitForAlertPresentAndSwitch(timeOut).getText();

	}

	public void acceptAlert(int timeOut) {
		waitForAlertPresentAndSwitch(timeOut).accept();

	}

	public void dismissAlert(int timeOut) {
		waitForAlertPresentAndSwitch(timeOut).dismiss();

	}

	public void alertSendKeys(int timeOut, String value) {
		waitForAlertPresentAndSwitch(timeOut).sendKeys(value);

	}

	public void waitForFramePresentAndSwitch(int frameIndex, int timeOut) {// Exact title

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));

	}

	public void waitForFramePresentAndSwitch(By framelocator, int timeOut) {// Exact title

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(framelocator));

	}

	public void waitForFramePresentAndSwitch(WebElement frameElement, int timeOut) {// Exact title

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));

	}

	public void waitForFramePresentAndSwitch(String nameOrId, int timeOut) {// Exact title

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(nameOrId));

	}

	public List<WebElement> waitForElementsVisible(By locator, int timeout) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));

		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));

	}

	public List<WebElement> waitForElementsPresence(By locator, int timeout) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));

		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));

	}

	public void ClickElementWhenReady(By locator, int timeOut) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();

	}

	public void ClickElementWhenReady(By locator, int timeOut, long intervalTime) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofMillis(intervalTime));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
		wait.ignoring(NoSuchElementException.class);////////////// CAN IMPLEMENT EXCEPTION WITH WEBDRIVER WAIT AS WELL

	}

	public WebElement waitForElementToBeVisibleWithFluentWait(By locator, int timeOut, int intervalTime) {

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(intervalTime)).ignoring(ElementNotInteractableException.class)
				.withMessage("Element Not Found");

		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}

//	***************************************Custom Wait************************************************************

	public WebElement retryingElement(By locator, int timeOut) {

		WebElement element = null;
		int attempts = 0;

		while (attempts < timeOut) {

			try {

				element = getElement(locator);
				System.out.println("Element is found in attempt : " + attempts);
				break;
			}

			catch (NoSuchElementException e) {

				System.out.println("Element is not found in attempt : " + attempts + " for " + locator);
				TimeUtil.applyWait(500);

			}

			attempts++;

		}

		if (element == null) {

			System.out.println(
					"element is not found... tried for : " + timeOut + "secs" + " with the interval of 500 milli sec");
			throw new FrameworkException("TimeOutException");

		}

		return element;

	}

	// METHOD OVERLOAD
	public WebElement retryingElement(By locator, int timeOut, int intervalTime) {

		WebElement element = null;
		int attempts = 0;

		while (attempts < timeOut) {

			try {

				element = getElement(locator);
				System.out.println("Element is found in attempt : " + attempts);
				break;
			}

			catch (NoSuchElementException e) {

				System.out.println("Element is not found in attempt : " + attempts);

				TimeUtil.applyWait(intervalTime);

//				try { ------------------------------------------ just created a separate time util class to avoid exception here
//
//					Thread.sleep(intervalTime * 1000);
//				} catch (InterruptedException e1) {
//
//					e1.printStackTrace();
//				}
			}

			attempts++;

		}

		if (element == null) {

			System.out.println("element is not null... tried for : " + timeOut + " secs" + " with the interval of  "
					+ intervalTime + " secs");
			throw new FrameworkException("TimeOutException");

		}

		return element;

	}
	// ***********************************************///Wait for Page
	// Load*********************************************************

	public void waitforPageLoad(int timeOut) {

		long endTime = System.currentTimeMillis() + timeOut;

		while (System.currentTimeMillis() < endTime) {

			JavascriptExecutor js = (JavascriptExecutor) driver;
			String pageState = js.executeScript("return document.readyState").toString();

			if (pageState.equals("complete")) {

				System.out.println("Page DOM is fully loaded");
				break;
			}

		}

	}

}

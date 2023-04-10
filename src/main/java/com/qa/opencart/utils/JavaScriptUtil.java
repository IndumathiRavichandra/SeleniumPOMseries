package com.qa.opencart.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class JavaScriptUtil {

	private WebDriver driver;

	public JavaScriptUtil(WebDriver driver) {

		this.driver = driver;
	}

	public String getTitleByJS() {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("return document.title;").toString();

	}
	
	public String getPageInnerText() {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("return document.documentElement.innerText;").toString();
	}

	public void goBackByJS() {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("history.go(-1)");
	}

	public void goForwardByJS() {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("history.go(1)");
	}

	public void refreshBrowserByJS() {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("history.go(0)");
	}
	
	public void scrollPageDown() {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");

	}
	public void scrollPageUp() {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(document.body.scrollHeight,0)");

	}

	
	
	public void generateAlert(String message) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("alert('" + message + "')");
	}

	public void generateConfirmPopUp(String message) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("confirm('" + message + "')");
	}

	public void drawBorder(WebElement element) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='3px solid red'", element);
	}

	public void flash(WebElement element) {

		String bgcolor = element.getCssValue("backgroundColor");
		for (int i = 0; i < 10; i++) {
			changeColor("rgb(0,200,0)", element);// 1
			changeColor(bgcolor, element);// 2
		}
	}

	public void changeColor(String color, WebElement element) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.backgroundColor='" + color + "'", element);
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {

		}
	}

}
/**
 * Called in JavaScriptTest Mail Method
 */
//	public static void main(String[] args) {
//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("--remote-allow-origins=*");
//
//		driver = new ChromeDriver(options);
//
//		driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
//		
//		JavaScriptUtil util = new JavaScriptUtil();
//		String title = util.getTitleByJS();
//		System.out.println(title);
//
//		
//		

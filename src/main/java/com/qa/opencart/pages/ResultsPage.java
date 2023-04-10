package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class ResultsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By searchProducts = By.xpath("//div[contains(@class,'product-layout')]");

	public ResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getSearchResultPageTitle(String ProductName) {

		return eleUtil.waitForTitleContains(ProductName, TimeUtil.DEFAULT_TIMEOUT);
	}

	public int getSearchProductsCount() {

		int productCount = eleUtil.waitForElementsVisible(searchProducts, TimeUtil.DEFAULT_TIMEOUT).size();
		System.out.println("Search Products Count is : " + productCount);
		return productCount;
	}
	
	public ProductInfoPage selectProduct(String mainProductName) {
		System.out.println("MainProductName : " + mainProductName);
		eleUtil.doClick(By.linkText(mainProductName));// since its a dynamic name we cannot initialize in the PRIVATE declaration page 
		return new ProductInfoPage(driver);
	}

}

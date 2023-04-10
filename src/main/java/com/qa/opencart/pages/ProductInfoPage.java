package com.qa.opencart.pages;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By productHeader = By.cssSelector("div#content h1");
	private By productImages = By.xpath("//a[@class='thumbnail']");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
	private By productpriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");

	private Map<String, String> productMap;

	public ProductInfoPage(WebDriver driver) {

		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getProductHeader() {

		return eleUtil.doGetElementText(productHeader);

	}

	public int getProductImagesCount() {

		int imagesCount = eleUtil.waitForElementsVisible(productImages, TimeUtil.DEFAULT_TIMEOUT).size();
		System.out.println("imagesCount---------->" + imagesCount);
		return imagesCount;
	}

	public Map<String, String> getProductInformation() {
		productMap = new HashMap<String, String>();
		//productMap = new LinkedHashMap<String, String>();
		//productMap = new TreeMap<String, String>();// alphabet order
		getProductMetaData();
		getProductPriceData();
		System.out.println(productMap);
		return productMap;

	}

//	Brand: Apple
//	Product Code: Product 18
//	Reward Points: 800
//	Availability: In Stock
//	********************************HASHMAP CONCEPT - KEY VALUE
	private void getProductMetaData() {

		List<WebElement> metaDataList = eleUtil.getElements(productMetaData);
		System.out.println("product meta data count------->" + metaDataList.size());

		for (WebElement e : metaDataList) {

			String meta = e.getText();
			System.out.println(meta);
			String[] metaData = meta.split(":");
			String metaKey = metaData[0].trim();
			String metaValue = metaData[1].trim();
			System.out.println("metaKey" + metaKey);
			System.out.println("metaValue"+ metaValue);
			productMap.put(metaKey, metaValue);

		}

	}

//	
//	$2,000.00
//	Ex Tax: $2,000.00
	// ALready initialized ---- >productMap = new HashMap<String, String>();
	private void getProductPriceData() {

		List<WebElement> metaPriceList = eleUtil.getElements(productpriceData);
		System.out.println("product meta price count------->" + metaPriceList.size());
		String price = metaPriceList.get(0).getText().trim();
		String ExTaxprice = metaPriceList.get(1).getText().trim();

		productMap.put("actual price", price);
		productMap.put("actualextaxprice", ExTaxprice);

	}
}

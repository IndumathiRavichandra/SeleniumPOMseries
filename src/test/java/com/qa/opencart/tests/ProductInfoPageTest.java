package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.qa.opencart.base.BaseTest;

public class ProductInfoPageTest extends BaseTest {

	@BeforeClass
	public void prodInfoSetUp() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@DataProvider(name = "getProductTestData")
	public Object[][] getProductTestData() {
		return new Object[][] { { "MacBook", "MacBook" }, { "MacBook", "MacBook Air" }, { "MacBook", "MacBook Pro" },
				{ "iMac", "iMac" }, { "Samsung", "Samsung SyncMaster 941BW" },

		};

	}

	@Test(dataProvider = "getProductTestData")
	public void productHeaderTest(String searchkey, String mainProductName) {
		resultsPage = accPage.performSearch(searchkey);
		prodInfoPage = resultsPage.selectProduct(mainProductName);
		String actHeader = prodInfoPage.getProductHeader();
		Assert.assertEquals(actHeader, mainProductName);

	}

	@DataProvider(name = "getProductImaagesTestData")
	public Object[][] getProductImaagesTestData() {
		return new Object[][] { { "MacBook", "MacBook", 5 }, { "MacBook", "MacBook Air", 4 },
				{ "MacBook", "MacBook Pro", 4 }, { "iMac", "iMac", 3 }, { "Samsung", "Samsung SyncMaster 941BW", 1 },

		};

	}

	@Test(dataProvider = "getProductImaagesTestData")
	public void productImagesTest(String searchkey, String mainProductName, int imagesCount) {
		resultsPage = accPage.performSearch(searchkey);
		prodInfoPage = resultsPage.selectProduct(mainProductName);
		int actImagesCount = prodInfoPage.getProductImagesCount();
		Assert.assertEquals(actImagesCount, imagesCount);
	}
	
	@Test
	public void ProductMetaDataTest() {
		resultsPage = accPage.performSearch("MacBook");
		prodInfoPage = resultsPage.selectProduct("MacBook Pro");
		Map<String, String> actproductInforMap= prodInfoPage.getProductInformation();
		softAssert.assertEquals(actproductInforMap.get("Brand"), "Apple");
		softAssert.assertEquals(actproductInforMap.get("Availability"), "In Stock");
		softAssert.assertEquals(actproductInforMap.get("actual price"), "$2,000.00");
		softAssert.assertEquals(actproductInforMap.get("Reward Points"), "800");
		softAssert.assertAll();

	}
	

}

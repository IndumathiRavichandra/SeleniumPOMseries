package com.qa.opencart.tests;


import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class RegPageTest extends BaseTest{
	
	
	@BeforeClass
	public void regPageSetUp() {
		regPage = loginPage.navigateToRegisterPage();
	}
	
	
	public String getrandomEmail() {// random mail generation if you need to test more
		Random random = new Random();
		String email = "batchname" +random.nextInt(5000) + "@gmail.com";
		return email;
	}
	

	@DataProvider
	public Object[] getRegTestData() {
		Object regData[] = ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
		return regData;
	}
	
	@Test(dataProvider = "getRegTestData")
	public void registerUserTest(String Fname, String Lname, String email, String tphone, String password, String subscribe) {
		
		boolean flag = regPage.registerUser(Fname, Lname, getrandomEmail(), tphone, password, subscribe);
		Assert.assertTrue(flag);
	}
}

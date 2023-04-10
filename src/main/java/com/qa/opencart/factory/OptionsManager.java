package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {

	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	
	public OptionsManager(Properties prop) {

		this.prop = prop;
	}

	public ChromeOptions getChromeOptions() {

		if (Boolean.parseBoolean(prop.getProperty("headless"))) {
			// co.setHeadless(true);
			co.addArguments("--remote-allow-origins=*");
			co.addArguments("--headless");
			System.out.println("----------------Headless browser");
		}

		if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
			// co.setHeadless(true);
			co.addArguments("--incognito");
			System.out.println("----------------Incognito mode browser");

		}
		return co;
	}
	
	
	public FirefoxOptions getFirefoxOptions() {

		if (Boolean.parseBoolean(prop.getProperty("headless"))) {
			// co.setHeadless(true);
			fo.addArguments("--remote-allow-origins=*");
			fo.addArguments("--headless");

		}

		if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
			// fo.setHeadless(true);
			fo.addArguments("--incognito");

		}
		return fo;
	}
}

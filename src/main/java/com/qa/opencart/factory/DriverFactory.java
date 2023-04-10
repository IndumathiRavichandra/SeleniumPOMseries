package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsManager;
	public static String highlight;
	
	static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	
	public WebDriver initDriver(Properties prop) {
		
		String browserName = prop.getProperty("browser").trim();

		System.out.println("Browser Name is : " + browserName);
		
		highlight = prop.getProperty("highlight");
		
		
		optionsManager= new OptionsManager(prop);

		if (browserName.equalsIgnoreCase("chrome")) {

			
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");

			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			
			tlDriver.set(new ChromeDriver(options));
			
		} else if (browserName.equalsIgnoreCase("firefox")) {

			//driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		}

		else if (browserName.equalsIgnoreCase("safari")) {

			//driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
			
		}
		

		else if (browserName.equalsIgnoreCase("edge")) {

			//driver = new EdgeDriver();
			tlDriver.set(new EdgeDriver());
		}
		else {
			System.out.println("Pls pass the right browser ........... " + browserName);
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().fullscreen();
		getDriver().get(prop.getProperty("url"));
		
		return tlDriver.get();
	}
	
	//get the local thread copy of the driver
	public synchronized static WebDriver getDriver() {
		
		return tlDriver.get();
	}

	
	public Properties initProp() {
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
			try {
				prop.load(ip);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}
	
	public static String getScreenshot() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileHandler.copy(srcFile, destination);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;

	}

}
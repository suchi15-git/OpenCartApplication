package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.exceptions.FrameworkException;

import io.github.bonigarcia.wdm.WebDriverManager;

/*
 * @author: suchita kadge
 */

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	/*
	 * This method is used to initialize the driver on the basis of given browser
	 * 
	 * @param properties prop
	 * 
	 * @return WebDriver
	 * 
	 */

	public WebDriver init_driver(Properties prop) {
		String browserName = prop.getProperty("browser");

		System.out.println("The browsername is:" + browserName);

		optionsManager = new OptionsManager(prop);

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			// driver = new ChromeDriver( optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		}

		else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		}

		else if (browserName.equalsIgnoreCase("safari")) {
			WebDriverManager.safaridriver().setup();
			driver = new SafariDriver();
		}

		else {
			System.out.println("Please pass the correct browser name..." + browserName);
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}

	public synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	/**
	 * This method is used to initialize the properties from the respective config
	 * file
	 * 
	 * @return this returns properties class object with all the config properties
	 */

	public Properties init_properties() {
		FileInputStream ip = null;
		prop = new Properties();

		// mvn command line argument
		// mvn clean install -Denv="qa"

		String envName = System.getProperty("env");
		System.out.println("Running tests on environment:" + envName);

		if (envName == null) {
			System.out.println("No env is given....,hence running it on QA env");
			try {
				ip = new FileInputStream(
						"C:\\Users\\Suchita Kadge\\eclipse-workspace\\Feb2022POMSessions\\src\\testresources\\ConfigFolder\\config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			try {
				switch (envName.toLowerCase()) {
				case "qa":

					ip = new FileInputStream(
							"C:\\Users\\Suchita Kadge\\eclipse-workspace\\Feb2022POMSessions\\src\\testresources\\ConfigFolder\\qa.config.properties");
					break;
				case "stage":
					ip = new FileInputStream(
							"C:\\Users\\Suchita Kadge\\eclipse-workspace\\Feb2022POMSessions\\src\\testresources\\ConfigFolder\\stage.config.properties");

					break;
				case "prod":
					ip = new FileInputStream(
							"C:\\Users\\Suchita Kadge\\eclipse-workspace\\Feb2022POMSessions\\src\\testresources\\ConfigFolder\\config.properties");

					break;
				default:
					System.out.println("Please pass the right environment value..." + envName);
					throw new FrameworkException("No env found...");
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			catch(FrameworkException e) {
				e.printStackTrace();
			}
			
			try {
				prop.load(ip);
			}catch(IOException e) {
				e.printStackTrace();
			}

//		try {
//			 ip = new FileInputStream("C:\\Users\\Suchita Kadge\\eclipse-workspace\\Feb2022POMSessions\\src\\testresources\\ConfigFolder\\config.properties");
//		
//			prop.load(ip);
//		} catch (FileNotFoundException e) {
//
//			e.printStackTrace();
//		} catch (IOException e) {
//
//			e.printStackTrace();
//		}

	
		}
		return prop;

	}

	/*
	 * Takes screenshot
	 */

	public String getScreenshot() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = "./screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}
}

package com.opencart.Factroy;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.opencart.exception.FrameworkException;

public class DriverFactory {
	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsmanager;
	public static String highlight;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	public WebDriver initDriver(Properties prop) {
		
		optionsmanager = new OptionsManager(prop);
		
		highlight = prop.getProperty("highlight").trim();
		String browserName = prop.getProperty("browser").trim().toLowerCase();
		System.out.println("browser name is ...."+browserName);
		
		if(browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver","/Users/pavanivemula/Documents/Drivers/chromedriver");
			//driver = new ChromeDriver(optionsmanager.getchromeoptions());	
			tlDriver.set(new ChromeDriver(optionsmanager.getchromeoptions()));
		}
		else if(browserName.equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.gecko.driver","/Users/pavanivemula/Documents/Drivers/geckodriver");
			tlDriver.set(new FirefoxDriver(optionsmanager.getfirefoxptions()));
		}else {
			System.out.println("plz pass the right browser name...." + browserName);
			throw new FrameworkException("NO BROWSER FOUND EXCEPTION....");
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}
	
	public synchronized static WebDriver getDriver() {
		return tlDriver.get();
		
	}
    public Properties initprop() {
    	prop = new Properties();
    	FileInputStream ip = null;
    	String envName = System.getProperty("env");
		System.out.println("Running test cases on Env: " + envName);
    	try {
			if (envName == null) {
				System.out.println("no env is passed....Running tests on QA env...");
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
			} else {
				switch (envName.toLowerCase().trim()) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
					break;
				case "prod":
					ip = new FileInputStream("./src/test/resources/config/config.properties");
					break;

				default:
					System.out.println("....Wrong env is passed....No need to run the test cases....");
					throw new FrameworkException("WRONG ENV IS PASSED...");
				// break;
				}

			}
		} catch (FileNotFoundException e) {

		}

		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}

}

package com.opencart.Factroy;
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
	public ChromeOptions getchromeoptions() {
		co = new ChromeOptions();
		co.addArguments("--remote-allow-origins=*");
		if(Boolean.parseBoolean(prop.getProperty("headless").trim())) {
			System.out.println("running chrome in headless mode ....");
			co.addArguments("---headless");	
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
			System.out.println("running chrome in incognito mode ....");
			co.addArguments("---incognito");
		}
		return co;
	}
	public FirefoxOptions getfirefoxptions() {
		fo = new FirefoxOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless").trim())) co.addArguments("---headless");
		if(Boolean.parseBoolean(prop.getProperty("incognito").trim())) co.addArguments("---incognito");
		return fo;
	}
	
}

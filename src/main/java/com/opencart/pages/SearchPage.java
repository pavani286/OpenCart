package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.opencart.Constants.AppConstants;
import com.opencart.Utils.ElementUtil;

public class SearchPage {
	// wedriver instances
	private WebDriver driver;
	private ElementUtil elemutil;

	// private locators 
	By productcount = By.xpath("//div[@id='content']/div[3]/div");
	
	// page constructor 
	public SearchPage(WebDriver driver) {
		this.driver = driver;
		elemutil = new ElementUtil(driver);
	}
	//page actions 
	public int SearchProductCount() {
		int count= elemutil.getElements(productcount).size();
		System.out.println("Product Count : "+count);
		return count;
	}	
	public ProductInfoPage SelectProduct(String productname) {
		By productLocator = By.linkText(productname);
		elemutil.waitForElementVisible(productLocator,AppConstants.DEFAULT_MEDIUM_TIME_OUT).click();
		return new ProductInfoPage(driver);
	}
	
}

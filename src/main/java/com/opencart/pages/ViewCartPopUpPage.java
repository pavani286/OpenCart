package com.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.opencart.Constants.AppConstants;
import com.opencart.Utils.ElementUtil;

public class ViewCartPopUpPage {

	private ElementUtil elemutil;
	private WebDriver driver;
	
  public ViewCartPopUpPage(WebDriver driver) {
		this.driver = driver;
		elemutil = new ElementUtil(driver);
	}
  
  private By ViewTotalCartItems = By.xpath("//table[@class='table table-striped']/tbody/tr/td[position()=2]/a");
 
  public void ViewTotalCart() { 
     List<WebElement> cartlist = elemutil.waitForElementsVisible(ViewTotalCartItems,AppConstants.DEFAULT_MEDIUM_TIME_OUT);
	 ArrayList<String> cartProdList = new ArrayList<String>();
	 for(WebElement e: cartlist) {
		 String text = e.getText();
		 cartProdList.add(text);
	 }
	 System.out.println("cartprod list of items : "+cartProdList);
  }


}

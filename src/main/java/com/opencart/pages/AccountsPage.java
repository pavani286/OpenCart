package com.opencart.pages;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.opencart.Constants.AppConstants;
import com.opencart.Utils.ElementUtil;

public class AccountsPage {
	// wedriver instances 
  private WebDriver driver;
  private ElementUtil elemutil;
  
  // private locators 
	private By AccsHeaders = By.xpath("//div[@id='account-account']/div/div/h2");
	private By LogoutLink = By.linkText("Logout");
	private By SearchBar = By.name("search");
	private By SearchIcon= By.cssSelector("#search button");
	private By SearchPgLabel = By.xpath("//label[@class='control-label']");
	
	// page constructor 
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		elemutil = new ElementUtil(driver);
	}
	// page actions 
	public String AccountPgTitle() {
		String AccTitle = elemutil.waitForTitleIsAndFetch(AppConstants.DEFAULT_MEDIUM_TIME_OUT, AppConstants.ACCOUNT_PAGE_TITLE_VALUE);
		System.out.println("Account Page tilte:"+AccTitle);
		return AccTitle;	
	}
	public String AccountPageURL() {
		String AccUrl= elemutil.waitForURLContainsAndFetch(AppConstants.DEFAULT_MEDIUM_TIME_OUT, AppConstants.ACCOUNT_PAGE_URL_FRACTION_VALUE);
		System.out.println("Account Page URL:"+AccUrl);
		return AccUrl;
	}
	public boolean LogoutLinkExist() {
		return elemutil.waitForElementPresence(LogoutLink,AppConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
	}
    public boolean isSearchExist() {
		return elemutil.waitForElementVisible(SearchBar,AppConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
	}
	public List<String> getAccHeaders() {
	 return elemutil.getElementsTextList(AccsHeaders);
	}
	public boolean SearchPageExists() {
		return elemutil.waitForElementVisible(SearchPgLabel,AppConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
		}
	public SearchPage PerformSearch(String searchkey) {
		if(isSearchExist()) {
			elemutil.doSendKeys(SearchBar, searchkey);
			elemutil.doClick(SearchIcon);
		}
		else {
			System.out.println("element is not present ...");
		}
	  return new SearchPage(driver);
	}
}

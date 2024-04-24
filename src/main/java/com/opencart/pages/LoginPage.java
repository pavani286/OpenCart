package com.opencart.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.opencart.Constants.AppConstants;
import com.opencart.Utils.ElementUtil;

public class LoginPage {
	private WebDriver driver;
	private ElementUtil elemutil;
	
	//1. private by locators
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@class='btn btn-primary']");
	private By forgotPwdLink = By.linkText("Forgotten Password");	
	private By registerLink = By.linkText("Register");
	
	//2. page constructor
	public LoginPage(WebDriver driver) {
		this.driver= driver;
		elemutil = new ElementUtil(driver);
	}
	//3. page actions 
	 public String getLoginPageTitle() {
		String title = elemutil.waitForTitleContainsAndFetch(AppConstants.DEFAULT_MEDIUM_TIME_OUT,AppConstants.LOGIN_PAGE_TITLE_VALUE);   
		System.out.println("Login Page tilte:"+title);
		return title;
	}
	 public String getLoginPageURL() {
		 String Url= elemutil.waitForURLContainsAndFetch(AppConstants.DEFAULT_MEDIUM_TIME_OUT,AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE);
		 System.out.println("Login Page url : "+Url);
		return Url;
	 }
	 public boolean ForgotPwdLinkExist() {
		return elemutil.waitForElementPresence(forgotPwdLink,AppConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
	}
	 public AccountsPage DoLogin(String us,String pwd) throws InterruptedException {
		 elemutil.waitForElementVisible(emailId, AppConstants.DEFAULT_MEDIUM_TIME_OUT).sendKeys(us);
		 elemutil.doSendKeys(password, pwd);
		 elemutil.doClick(loginBtn);
		 return new AccountsPage(driver);
	}
	public RegistrationPage navigateToRegisterPage() {
		elemutil.doClick(registerLink);
		return new RegistrationPage(driver);
	}
	public NegativeRegistrationPg navigateToRegisterPg() {
		elemutil.doClick(registerLink);
		return new NegativeRegistrationPg(driver);
	}
}

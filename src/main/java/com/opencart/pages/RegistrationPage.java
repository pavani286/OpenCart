package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.opencart.Constants.AppConstants;
import com.opencart.Utils.ElementUtil;

public class RegistrationPage {
	private WebDriver driver;
	private ElementUtil elemutil;

	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmpassword = By.id("input-confirm");

	private By agreeCheckBox = By.name("agree");
	private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");

	private By subscribeYes = By.xpath("//label[normalize-space()='Yes']/input[@type='radio']");
	private By subscribeNo = By.xpath("//label[normalize-space()='No']/input[@type='radio']");

	private By registerSuccessMesg = By.cssSelector("div#content h1");

	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	private By logoutsuccessmess = By.xpath("//div[@id='content']/p[position()=1]");

	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		elemutil = new ElementUtil(driver);
	}
	public String RegisterPgTitle() {
		elemutil.doClick(registerLink);
		String title = elemutil.waitForURLContainsAndFetch(AppConstants.DEFAULT_MEDIUM_TIME_OUT,AppConstants.REGISTER_PAGE_URL_FRACTION_VALUE);
		System.out.println(title);
		return title;
	}
	public boolean Registration(String firstName, String lastName, 
			String email, String telephone, String password, String subscribe) {
	
		// entering the details 
		elemutil.doSendKeys(this.firstName, firstName);
		elemutil.doSendKeys(this.lastName,lastName);
		elemutil.doSendKeys(this.email,email);
		elemutil.doSendKeys(this.telephone,telephone);
		elemutil.doSendKeys(this.password,password);
		elemutil.doSendKeys(this.confirmpassword, password);
		if(subscribe.equalsIgnoreCase("yes")) {
			elemutil.doClick(subscribeYes);
		}
		else {
			elemutil.doClick(subscribeNo);
		}
		
		elemutil.doClick(agreeCheckBox);
		elemutil.doClick(continueButton);
		
		String successmess = elemutil.waitForElementPresence(registerSuccessMesg,AppConstants.DEFAULT_MEDIUM_TIME_OUT).getText();
		System.out.println(successmess);
		if(successmess.contains(AppConstants.USER_REG_SUCCESS_MESSG)) {
		//logout 
		elemutil.doClick(logoutLink);
		String logoutmess = elemutil.waitForElementPresence(logoutsuccessmess, AppConstants.DEFAULT_MEDIUM_TIME_OUT).getText();
		System.out.println(logoutmess);
		elemutil.doClick(registerLink);
	    return true;
		}
		return false;
	}
	
}

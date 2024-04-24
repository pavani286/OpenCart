package com.opencart.pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.opencart.Constants.AppConstants;
import com.opencart.Utils.ElementUtil;

public class NegativeRegistrationPg {
	private WebDriver driver;
	private ElementUtil elemutil;

	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmpassword = By.id("input-confirm");
	
	private By FirstNameMess = By.xpath("//div[contains(text(),'First Name must')]//ancestor::div[@class='text-danger']");
	private By LastNameMess = By.xpath("//div[contains(text(),'Last Name must')]//ancestor::div[@class='text-danger']");
	private By EmailMess = By.xpath("//div[contains(text(),'E-Mail Address')]//ancestor::div[@class='text-danger']");
	private By TelephoneMess = By.xpath("//div[contains(text(),'Telephone must')]//ancestor::div[@class='text-danger']");
	private By PasswordMess = By.xpath("//div[contains(text(),'Password must')]//ancestor::div[@class='text-danger']");
	private By PrivacyMess = By.xpath("//div[@class='alert alert-danger alert-dismissible']");
	private By agreeCheckBox = By.name("agree");
	private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");
	private By NegativeMessLink = By.xpath("//div[@class='text-danger']");
	
	public NegativeRegistrationPg(WebDriver driver) {
		this.driver = driver;
		elemutil = new ElementUtil(driver);
	}
	public void NegativeTestcaseRegistration(String firstName, String lastName, 
			String email, String telephone, String password) {
		
		elemutil.doSendKeys(this.firstName, firstName);
		elemutil.doSendKeys(this.lastName,lastName);
		elemutil.doSendKeys(this.email,email);
		elemutil.doSendKeys(this.telephone,telephone);
		elemutil.doSendKeys(this.password,password);
		elemutil.doSendKeys(this.confirmpassword,password);
		
		//elemutil.doClick(agreeCheckBox);
		elemutil.doClick(continueButton);
	}
	public String FirstNameMess() {
		return elemutil.getElement(FirstNameMess).getText();
	}
	public String LastNameMess() {
		return elemutil.getElement(LastNameMess).getText();
	}
	public String EmailMess() {
		return elemutil.getElement(EmailMess).getText();
	}
	public String TelephoneMess() {
		return elemutil.getElement(TelephoneMess).getText();
	}
	public String PasswordMess() {
		return elemutil.getElement(PasswordMess).getText();
	}
	public String PrivacyMess() {
		String successMess = elemutil.getElement(PrivacyMess).getText();
		System.out.println(successMess);
		return successMess;
	}
	public List<String> NegativeMessages() {
		List<WebElement> eleList = elemutil.getElements(NegativeMessLink);
		List<String> actualmesslist = new ArrayList<String>();
		for (WebElement e : eleList) {
			String text = e.getText();
			actualmesslist.add(text);
			System.out.println(text);
		}
		return actualmesslist;
	}
}

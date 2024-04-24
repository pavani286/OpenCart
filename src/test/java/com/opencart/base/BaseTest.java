package com.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.opencart.Factroy.DriverFactory;
import com.opencart.pages.AccountsPage;
import com.opencart.pages.LoginPage;
import com.opencart.pages.NegativeRegistrationPg;
import com.opencart.pages.ProductInfoPage;
import com.opencart.pages.RegistrationPage;
import com.opencart.pages.SearchPage;
import com.opencart.pages.ViewCartPopUpPage;

public class BaseTest {
	DriverFactory df;
	WebDriver driver;
	protected Properties prop;
	protected LoginPage loginpg;
	protected AccountsPage accountpg;
	protected SearchPage searchpg;
	protected ProductInfoPage productinfopg;
	protected ViewCartPopUpPage viewCartPopUpPg	;
	protected SoftAssert softAssert;
	protected RegistrationPage registerpage;
	protected NegativeRegistrationPg registerpg;
	
	@BeforeTest
	public void setup() {
		df = new DriverFactory();
		prop = df.initprop();
		driver = df.initDriver(prop);
		loginpg = new LoginPage(driver);
		softAssert = new SoftAssert();
	}
	
	@AfterTest
	public void teardown(){
		driver.quit();
	}
}

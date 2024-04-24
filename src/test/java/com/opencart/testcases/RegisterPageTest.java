package com.opencart.testcases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.Random;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencart.Constants.AppConstants;
import com.opencart.base.BaseTest;

import DataUtil.ReadingData_Excel;


public class RegisterPageTest extends BaseTest{
	
	@BeforeClass
	public void RegisterPageSetup() {
		registerpage = loginpg.navigateToRegisterPage();
	}

	@Test
	public void RegisterPgTitleTest() {
		String ActualTitle = registerpage.RegisterPgTitle();
		AssertJUnit.assertTrue(ActualTitle.contains(AppConstants.REGISTER_PAGE_URL_FRACTION_VALUE));
		
	}
	@DataProvider
	public Object[][] gettestdata() throws IOException {
		return ReadingData_Excel.getTestData("Register");
	}
	
	public String getRandomEmail() {
		Random random = new Random();
		//String email = "automation" + random.nextInt(1000) + "@gmail.com";
		
		String email = "automation" + System.currentTimeMillis() + "@gmail.com";
		//automation12121212121@gmail.com
		//automation232323232323@gmail.com
		
		return email;
	}
	@Test(dataProvider="gettestdata")
	public void RegistrationTest(String firstName, String lastName, 
			 String telephone, String password,String subscribe) {
		
		 registerpage.Registration(firstName, lastName, getRandomEmail(), telephone, password, subscribe);
	}
	
}

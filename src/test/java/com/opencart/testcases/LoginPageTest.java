package com.opencart.testcases;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import com.opencart.Constants.AppConstants;
import com.opencart.base.BaseTest;

public class LoginPageTest extends BaseTest{

	@Test(priority=1)
	public void LoginPageTilteTest() {
		String ActualTitle = loginpg.getLoginPageTitle();
		AssertJUnit.assertEquals(ActualTitle,AppConstants.LOGIN_PAGE_TITLE_VALUE);
	}
	@Test(priority=2)
	public void LoginPageURLTest() {
		String actualURL = loginpg.getLoginPageURL();
		AssertJUnit.assertTrue(actualURL.contains(AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE));
	}
	@Test(priority=3)
	public void forgotPwdLinkExistTest() {
		AssertJUnit.assertTrue(loginpg.ForgotPwdLinkExist());
	}
	@Test(priority=4)
	public void logintest() throws InterruptedException {
		accountpg = loginpg.DoLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
		AssertJUnit.assertTrue(accountpg.LogoutLinkExist());
	}
}

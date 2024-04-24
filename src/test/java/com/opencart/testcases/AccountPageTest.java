package com.opencart.testcases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.AssertJUnit;
import java.util.List;
import org.testng.Assert;
import com.opencart.Constants.AppConstants;
import com.opencart.base.BaseTest;

public class AccountPageTest extends BaseTest{
     
	@BeforeClass
	public void Accountspgsetup() throws InterruptedException {
   accountpg= loginpg.DoLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
	}
	@Test
	public void AccountPgTitleTest() {
		String accountpgtilte =accountpg.AccountPgTitle();
		AssertJUnit.assertEquals(accountpgtilte,AppConstants.ACCOUNT_PAGE_TITLE_VALUE);
	}
	@Test
	public void AccountPageURLTest() {
		String accountURL = accountpg.AccountPageURL();
		AssertJUnit.assertTrue(accountURL.contains(AppConstants.ACCOUNT_PAGE_URL_FRACTION_VALUE));
	}
	@Test
	public void LogoutLinkExistTest() {
		AssertJUnit.assertTrue(accountpg.LogoutLinkExist());
	}
	@Test
	public void AccHeadersTest() {
		List<String> headerlist = accountpg.getAccHeaders();
		System.out.println("Actual header list... "+headerlist);
		System.out.println("Expected header list ..."+ AppConstants.EXPECTED_ACCOUNTS_HEADERS_LIST);
		AssertJUnit.assertEquals(headerlist,AppConstants.EXPECTED_ACCOUNTS_HEADERS_LIST);
	}
	@Test(dataProvider="gettestdata")
	public void PerformSearchTest(String searchKey) {
		searchpg = accountpg.PerformSearch(searchKey);
	}
	@DataProvider
	public Object [][] gettestdata() {
		return new Object[][] {{"MacBook"},{"MacBook Pro"}};
	}
	
	// below methods are from search page
	@Test
	public void SearchPageExistsTest() {
		AssertJUnit.assertTrue(accountpg.SearchPageExists());
	}
	@Test(dataProvider="gettestdata")
	public void SearchProductCountTest(String searchkey) {
		searchpg = accountpg.PerformSearch(searchkey);
		AssertJUnit.assertTrue(searchpg.SearchProductCount()>0);
	}
	@Test(dataProvider="gettestdata")
	public void SelectProductTest(String productname) {
		searchpg = accountpg.PerformSearch(productname);
		AssertJUnit.assertTrue(searchpg.SearchProductCount()>0);
		productinfopg = searchpg.SelectProduct(productname);
		String ActProductHeader = productinfopg.ProductPgLabel();
		AssertJUnit.assertEquals(ActProductHeader,productname);
	}
	
}

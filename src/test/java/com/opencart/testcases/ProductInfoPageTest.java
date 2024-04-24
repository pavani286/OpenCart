package com.opencart.testcases;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.LinkedHashMap;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.opencart.base.BaseTest;
import DataUtil.ReadingData_Excel;

public class ProductInfoPageTest extends BaseTest{

	@BeforeClass
	public void ProductInfoPgSetup() throws InterruptedException {
        accountpg= loginpg.DoLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
	}
	@DataProvider
	public Object [][] getProductImageTestData() {
		return new Object[][] {{"MacBook","MacBook Air",2},
		{"MacBook","MacBook Pro",4}};
	}
	@DataProvider
	public Object [][] getProductTestData() {
		return new Object[][] {{"MacBook","MacBook Air","Product 17","$1,202.00"},
		{"Samsung","Samsung SyncMaster 941BW","Product 6","$242.00"}};
	}
	@Test(dataProvider="getProductImageTestData")
	public void ProdImageCountTest(String searchKey,String productname,int productcount) {
		searchpg = accountpg.PerformSearch(searchKey);
		productinfopg = searchpg.SelectProduct(productname);
		productinfopg.ProdImageCount(productcount);
	}
	@DataProvider
	public Object[][] gettestdata() throws IOException {
		return ReadingData_Excel.getTestData("Product");
	}
	@Test(dataProvider="gettestdata")
	public void getProductInfoTest(String SearchKey,String ProductName,
			String ProdCode,String ProdPrice) {
		
		searchpg = accountpg.PerformSearch(SearchKey);
		productinfopg = searchpg.SelectProduct(ProductName);
		LinkedHashMap<String,String> FinalProdDetails = productinfopg.getProductDetails();
		//softAssert.assertEquals(FinalProdDetails.get("Brand"),ProdBrand);
		softAssert.assertEquals(FinalProdDetails.get("Product Code"),ProdCode);
		softAssert.assertEquals(FinalProdDetails.get("Product Name"),ProductName);
		softAssert.assertEquals(FinalProdDetails.get("ProdPrice"),ProdPrice);
		softAssert.assertAll();
	}
	@Test(dataProvider="getProductImageTestData")
	public void AddToCartTest(String searchKey,String productname,int qty) {
		searchpg = accountpg.PerformSearch(searchKey);
		productinfopg = searchpg.SelectProduct(productname);
		productinfopg.EnterProductQuaninty(qty);
		String addCartMessage = productinfopg.AddToCart();
		softAssert.assertTrue(addCartMessage.indexOf("Success")>=0);
		softAssert.assertEquals(addCartMessage,"Success: You have added "+productname+" to your shopping cart!");
		
		// viewing the cart items and vaildating the data 
		viewCartPopUpPg = productinfopg.openCart();
		viewCartPopUpPg.ViewTotalCart();
		
	}
	
	@DataProvider
	public Object [][] getProductDecsriptionData() {
		return new Object[][] {{"MacBook","MacBook Air"},
		{"MacBook","MacBook Pro"}};
	}
	
	@Test(dataProvider="getProductDecsriptionData")
	public void ProdDecsriptionTest(String searchKey,String productname) {
		searchpg = accountpg.PerformSearch(searchKey);
		productinfopg = searchpg.SelectProduct(productname);
	    String description = productinfopg.ProdDescription();
		AssertJUnit.assertTrue(description.length()>0);
		
	}
}

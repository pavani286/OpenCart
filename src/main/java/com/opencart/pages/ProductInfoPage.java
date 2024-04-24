package com.opencart.pages;

import java.util.LinkedHashMap;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.opencart.Constants.AppConstants;
import com.opencart.Utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil elemutil;
	
	//constructor
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		elemutil = new ElementUtil(driver);
	}
	//private By Loctors 
	 private By ProductPgLabel = By.tagName("h1");
	 private By ProdImageCount = By.xpath("//li[@class='image-additional']");
	 private By ProductQuantity = By.id("input-quantity");
	 private By ProductAddToCart = By.id("button-cart");
	 private By ProdCartTotal = By.id("cart-total");
	 private By ProductSucessMess = By.xpath("//div[@class='alert alert-success alert-dismissible']");
	 private By ProductDescription = By.xpath("//div[@class='tab-content']");
	 private By ProductInfo = By.xpath("//div[@class='col-sm-4']/ul[position()=1]/li");
	 private By ProductPriceInfo = By.xpath("//div[@class='col-sm-4']/ul[position()=2]/li");
	 
	private  LinkedHashMap<String,String> ProductInfoData ;
	
	//Actions
	public String ProductPgLabel() {
		String ProductHeader = elemutil.doElementGetText(ProductPgLabel);
		return ProductHeader;
	}
	public int ProdImageCount(int productcount) {
		int count= elemutil.getElements(ProdImageCount).size();
		System.out.println("Product Images Count : "+count);
		return count;
	}
	public void EnterProductQuaninty(int qty) {
		elemutil.doSendKeys(ProductQuantity,String.valueOf(qty));  
	}
	public LinkedHashMap<String,String> getProductDetails() {
		ProductInfoData =  new LinkedHashMap<String,String>();
		getProductInfo();
		getProductPriceInfo();
		System.out.println(ProductInfoData);
		return ProductInfoData;
	}
	public void getProductInfo(){
		ProductInfoData.put("Product Name",ProductPgLabel());
		List<WebElement> eleList = elemutil.getElements(ProductInfo);
		for (WebElement e : eleList) {
			String text = e.getText();
			String metainfo[] = text.split(":");
			String key = metainfo[0].trim();
			String value = metainfo[1].trim();
			ProductInfoData.put(key,value);
		}
	}
	public void getProductPriceInfo() {
		List<WebElement> elePriceList = elemutil.getElements(ProductPriceInfo);
		String Price = elePriceList.get(0).getText();
		String exTax = elePriceList.get(1).getText();
		String exvalue = exTax.split(":")[1].trim();
		ProductInfoData.put("ProdPrice", Price);
		ProductInfoData.put("exTax", exvalue);
	}
	public String AddToCart() {
		elemutil.doClick(ProductAddToCart);
		String SucessMessage= elemutil.getElement(ProductSucessMess,AppConstants.DEFAULT_LONG_TIME_OUT).getText();
	    StringBuilder sb = new StringBuilder(SucessMessage);
		String mesg = sb.substring(0, SucessMessage.length()-1).replace("\n", "").toString();
		return mesg;	
	}
	public String ProdDescription() {
		return  elemutil.doElementGetText(ProductDescription);
		//System.out.println(descr);
	}
	public ViewCartPopUpPage openCart() {
		elemutil.doClick(ProdCartTotal);
		return new ViewCartPopUpPage(driver);
	}
}

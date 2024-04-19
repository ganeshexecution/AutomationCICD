package Udemy_Selenium._SeleniumFrameworkDesign.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.Utilities;

public class CartPage extends Utilities
{
	WebDriver driver;
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartproducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkOutEle;
	
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	public Boolean verifyCartProducts(String productname)
	{
		Boolean match = cartproducts.stream().anyMatch(cartproduct-> 
		cartproduct.getText().equalsIgnoreCase(productname));
		return match;
	}

	public CheckOutPage goToCheckout() throws InterruptedException
	{
		Thread.sleep(3000);
		checkOutEle.click();
		return new CheckOutPage(driver);
		 
		
	}
}

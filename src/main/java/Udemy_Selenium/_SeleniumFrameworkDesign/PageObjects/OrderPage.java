package Udemy_Selenium._SeleniumFrameworkDesign.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.Utilities;

public class OrderPage extends Utilities
{
	WebDriver driver;
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> productnames;
	
	@FindBy(css=".totalRow button")
	WebElement checkOutEle;
	
	public OrderPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	public Boolean verifyOrderProducts(String productname)
	{
		Boolean match = productnames.stream().anyMatch(cartproduct-> 
		cartproduct.getText().equalsIgnoreCase(productname));
		return match;
	}

	
		 
		
	}


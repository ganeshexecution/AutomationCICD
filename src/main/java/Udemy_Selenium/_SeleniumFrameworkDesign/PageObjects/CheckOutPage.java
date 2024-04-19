package Udemy_Selenium._SeleniumFrameworkDesign.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import AbstractComponents.Utilities;

public class CheckOutPage extends Utilities
{
	WebDriver driver;
	
	public CheckOutPage(WebDriver driver)
	{
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	By results = By.cssSelector(".ta-results");
	@FindBy(css="[placeholder = 'Select Country']")
	WebElement country;
	
	@FindBy(xpath="(//button[contains(@class, 'ta-item')])[2]")
	WebElement countryname;
	
	@FindBy(css=".action__submit")
	WebElement placeOrderbtn;

	public void selectCountry(String CountryName)
	{
		Actions a= new Actions(driver);
		a.sendKeys(country,CountryName).build().perform();
		ElementToGetVisible(results);
		countryname.click();
	}
	
	public ConfirmationPage submitOrder() throws InterruptedException
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,300)");
		Thread.sleep(3000);
		ElementToGetLoad(placeOrderbtn);
		placeOrderbtn.click();
	//	js.executeScript("document.querySelector('.action_submit').click()");
		return new ConfirmationPage(driver);
		 
	
		
	}
}

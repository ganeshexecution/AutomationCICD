package AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Udemy_Selenium._SeleniumFrameworkDesign.PageObjects.CartPage;
import Udemy_Selenium._SeleniumFrameworkDesign.PageObjects.OrderPage;

public class Utilities 
{

	WebDriver driver;
	
	//Cart header WebElement
	@FindBy(css ="[routerlink*='cart']")
	WebElement cartHeader;
	
	//Orders header WebElement
	@FindBy(css ="[routerlink*='myorders']")
	WebElement orderHeader;
	
	
	//Constructor
	public Utilities(WebDriver driver)
	{
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	//Methods
	public void ElementToGetVisible(By findby) 
	{
	
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findby));
		
	}
	
	public void ElementToGetLoad(WebElement Ele)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(Ele));
	}
	
	public void ElementToGetInvisible(WebElement Ele)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(Ele));
	}
	
	//This is header section and reusable one
	public CartPage goToCartSection()
	{
		cartHeader.click();
		CartPage cartPageObj = new CartPage(driver);
		return cartPageObj;
	}
	
	public OrderPage goToOrdersSection()
	{
		orderHeader.click();
		OrderPage orderPageObj = new OrderPage(driver);
		return orderPageObj;
	}
}

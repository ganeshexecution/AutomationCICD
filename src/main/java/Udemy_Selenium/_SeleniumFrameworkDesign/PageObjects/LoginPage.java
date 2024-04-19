package Udemy_Selenium._SeleniumFrameworkDesign.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.Utilities;

public class LoginPage extends Utilities
{

	WebDriver driver;
	
	//Constructor
	public  LoginPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	//PageFactory
	//using @FindBy instead of driver.findElement
	//Elements in the login page
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement passwordEle;
	
	@FindBy(id="login")
	WebElement lgnbtn;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMsg;
	
	//Methods
	public ProductsPage LoginApplication(String email, String password)
	{
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		lgnbtn.click();
		return new ProductsPage(driver);
		 
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMessage()
	{
		ElementToGetLoad(errorMsg);
	return 	errorMsg.getText();
		
	}
}

package Udemy_Selenium._SeleniumFrameworkDesign.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.Utilities;

public class ProductsPage extends Utilities
{

	WebDriver driver;
	
	//Constructor
	public  ProductsPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//Elements in products page
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css =".ng-animating")
	WebElement animation;
	
	By productsby = By.cssSelector(".mb-3");
	By addprodtocart = By.cssSelector(".card-body button:last-of-type");
	By toastmsg = By.cssSelector("#toast-container");
	
	//To get list of all the products
	public List<WebElement> getProductList() 
	{
		ElementToGetVisible(productsby);
		return products;
	}
	
	//To get the required product by name
	public WebElement getProductByName(String productname)
	{
		WebElement prod =	products.stream().filter(product-> 
		product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst().orElse(null);
		return prod;
	}
	
	//Adding product to cart
	public void addProductToCart(String productname)
	{
		WebElement prod = getProductByName(productname);
		prod.findElement(addprodtocart).click();
		ElementToGetVisible(toastmsg);
		ElementToGetInvisible(animation);
	}
}

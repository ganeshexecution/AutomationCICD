package seleniumFrameworkDesign.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Udemy_Selenium._SeleniumFrameworkDesign.PageObjects.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandaloneTest_Original {

	public static void main(String[] args)  
	{
	
		String productname = "ADIDAS ORIGINAL";
		WebDriverManager.chromedriver().setup();
	//	System.setProperty("webdriver.chrome.driver", "C:\\Users\\sperike\\Downloads\\chromedriver.exe");	
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		
		LoginPage loginPageObj = new LoginPage(driver);
		//login page
		driver.findElement(By.id("userEmail")).sendKeys("tonystark123@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Tony@123");
		driver.findElement(By.id("login")).click();
		
		//using wait for the page to so that driver to find the list of products
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		//products homepage
		List <WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		//to filter particular product on homepage and add it to cart
	WebElement prod =	products.stream().filter(product-> 
		product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		//using wait so that the driver can execute after the loading animation is gone 
		//and for the toast message to appear
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
	wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
	driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
	
	//checking whether the added product is present in cart by taking all the products in the cart and checking from them
	List <WebElement> cartproducts = driver.findElements(By.cssSelector(".cartSection h3"));
	Boolean match = cartproducts.stream().anyMatch(cartproduct-> 
	cartproduct.getText().equalsIgnoreCase(productname));
	
	//checking whether the added product is matched using assertion
	Assert.assertTrue(match);
	
	//clicking on checkout
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".totalRow button")));
	driver.findElement(By.cssSelector(".totalRow button")).click();
	
	//selecting country from dropdown using actions
	Actions a= new Actions(driver);
	a.sendKeys(driver.findElement(By.cssSelector("[placeholder = 'Select Country']")),"India").build().perform();
	
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
	
	driver.findElement(By.xpath("(//button[contains(@class, 'ta-item')])[2]")).click();
	
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".action__submit")));	
	WebElement placeOrder = driver.findElement(By.cssSelector(".action__submit"));
	
	placeOrder.click();
	
	//confirming the final page using assertions
	String confirmMesage = driver.findElement(By.cssSelector(".hero-primary")).getText();
	Assert.assertTrue(confirmMesage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

}

package seleniumFrameworkDesign.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import Udemy_Selenium._SeleniumFrameworkDesign.PageObjects.CartPage;
import Udemy_Selenium._SeleniumFrameworkDesign.PageObjects.CheckOutPage;
import Udemy_Selenium._SeleniumFrameworkDesign.PageObjects.ConfirmationPage;
import Udemy_Selenium._SeleniumFrameworkDesign.PageObjects.ProductsPage;
import seleniumFrameworkDesign.TestComponents.BaseTest;

public class ErrorValidationsTest extends BaseTest
{

	@Test(groups= {"ErrorHandling"},retryAnalyzer=seleniumFrameworkDesign.TestComponents.Retry.class)
	public void LoginPageErrorValidations() throws IOException, InterruptedException
	{
	
		loginPageObj.LoginApplication("tonystark123@gmail.com", "Ton123");		
		Assert.assertEquals("Incorrect email or password.", loginPageObj.getErrorMessage());
			
	}
	
	@Test
	public void ProductPageErrorValidations() throws IOException, InterruptedException
	{
	
		String productname = "ADIDAS ORIGINAL";
		
		ProductsPage productPageObj = loginPageObj.LoginApplication("tonystark123@gmail.com", "Tony@123");		
		
		//Getting products list and adding product to cart and going to cart section
		List <WebElement> products = productPageObj.getProductList();		
		productPageObj.addProductToCart(productname);		
		
		//Getting cartpage obj form products page class
		CartPage cartPageObj= productPageObj.goToCartSection();	
		Boolean match = cartPageObj.verifyCartProducts("ADIDAS ORIGINALS");
		
		//checking whether the added product is matched using assertion
		Assert.assertFalse(match);
		
	}

}

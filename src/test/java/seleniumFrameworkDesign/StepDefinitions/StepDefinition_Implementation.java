package seleniumFrameworkDesign.StepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Udemy_Selenium._SeleniumFrameworkDesign.PageObjects.CartPage;
import Udemy_Selenium._SeleniumFrameworkDesign.PageObjects.CheckOutPage;
import Udemy_Selenium._SeleniumFrameworkDesign.PageObjects.ConfirmationPage;
import Udemy_Selenium._SeleniumFrameworkDesign.PageObjects.LoginPage;
import Udemy_Selenium._SeleniumFrameworkDesign.PageObjects.ProductsPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import seleniumFrameworkDesign.TestComponents.BaseTest;

public class StepDefinition_Implementation extends BaseTest
{

	public LoginPage loginPageObj;
	public ProductsPage productPageObj;
	public CheckOutPage checkOutPageObj;
	public ConfirmationPage cnfrmPageObj;
	
	//Background step
	@Given("Landed on the ECommerce website")
	public void Landed_on_the_ECommerce_website() throws IOException
	{
		loginPageObj= launchApplication();
	}
	
	//Here we are using regx as username and password are dynamic
	@Given("^Logged in with username (.+) and password (.+)$")
	public  void Logged_in_with_username_and_password(String username, String password)
	{
		productPageObj = loginPageObj.LoginApplication(username,password);	
	}
	
	@When("^I add product (.+) to cart$")
	public void I_add_product_to_cart(String product)
	{
		List <WebElement> products = productPageObj.getProductList();		
		productPageObj.addProductToCart(product);
	}
	
	@When("^Checkout with product (.+) and submit the order$")
	public void Checkout_with_product_and_submit_the_order(String product) throws InterruptedException
	{
		CartPage cartPageObj= productPageObj.goToCartSection();	
		Boolean match = cartPageObj.verifyCartProducts(product);
		Assert.assertTrue(match);
		 checkOutPageObj =cartPageObj.goToCheckout();
		 checkOutPageObj.selectCountry("india");
		 cnfrmPageObj = checkOutPageObj.submitOrder();
	}
	
	//Here string is a static variable.As it is a static variable and not written in Example we put it in {}. 
	@Then("{string} message is displayed on the screen")
	public void message_is_displayed_on_the_screen(String string)
	{
		String confirmMesage = cnfrmPageObj.getConfirmationMessage();
		Assert.assertTrue(confirmMesage.equalsIgnoreCase(string));
	}
	
	@Then("{string} message is displayed")
	public void message_is_displayed(String string)
	{
		Assert.assertEquals(string, loginPageObj.getErrorMessage());
	}
}

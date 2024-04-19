package seleniumFrameworkDesign.Tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Udemy_Selenium._SeleniumFrameworkDesign.PageObjects.CartPage;
import Udemy_Selenium._SeleniumFrameworkDesign.PageObjects.CheckOutPage;
import Udemy_Selenium._SeleniumFrameworkDesign.PageObjects.ConfirmationPage;
import Udemy_Selenium._SeleniumFrameworkDesign.PageObjects.LoginPage;
import Udemy_Selenium._SeleniumFrameworkDesign.PageObjects.OrderPage;
import Udemy_Selenium._SeleniumFrameworkDesign.PageObjects.ProductsPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import seleniumFrameworkDesign.TestComponents.BaseTest;

public class StandaloneTest2_Improvised extends BaseTest
{


	
	@Test(dataProvider="getData",groups ={"Purchase"})
	public void SubmitOrder(HashMap<String, String> input) throws IOException, InterruptedException
	{
	
		//getting data from hashmaps using keys instead of sending data and catching them
		ProductsPage productPageObj = loginPageObj.LoginApplication(input.get("email"), input.get("password"));		
		
		//Getting products list and adding product to cart and going to cart section
		List <WebElement> products = productPageObj.getProductList();		
		productPageObj.addProductToCart( input.get("product"));		
		
		//Getting cartpage obj form products page class
		CartPage cartPageObj= productPageObj.goToCartSection();	
		Boolean match = cartPageObj.verifyCartProducts(input.get("product"));
		
		//checking whether the added product is matched using assertion
		Assert.assertTrue(match);
		CheckOutPage checkOutPageObj =cartPageObj.goToCheckout();
		
		//selecting country from dropdown using actions
		checkOutPageObj.selectCountry("india");
		ConfirmationPage cnfrmPageObj = checkOutPageObj.submitOrder();
		
		//confirming the final page using assertions
		String confirmMesage = cnfrmPageObj.getConfirmationMessage();
		Assert.assertTrue(confirmMesage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		
	}
	
	@Test(dependsOnMethods= {"SubmitOrder"})
	public void OrderHistoryTest(HashMap<String,String> input)
	{
		ProductsPage productPageObj = loginPageObj.LoginApplication(input.get("email"), input.get("password"));
		OrderPage orderPageObj=  productPageObj.goToOrdersSection();
		Assert.assertTrue(orderPageObj.verifyOrderProducts(input.get("product")));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		//the method getJsonDataToMap is obtained from BaseTest class not from DataReader class. SO we have made it Generic.
		List<HashMap<String, String>> data = getJsonDataToMap("C://Users//sperike//eclipse-workspace//4_SeleniumFrameworkDesign//src//test//java//seleniumFrameworkDesign//Data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)}, {data.get(1)}};
				
	}

}

//for dataprovider
		//  @DataProvider
		//  public Object[][] getData() {
		//	return new Object[][]{{"tonystark123@gmail.com","Tony@123","ADIDAS ORIGINAL"},{"shetty@gmail.com","Iamking@000","ZARA COAT 3"}}; }
		
		//Hashmaps are used when multiple sets of variables are need to be validated		
		//		  HashMap<String,String> map1 = new HashMap<String,String>();
		//		  map1.put("email","tonystark123@gmail.com"); 
		//		  map1.put("password", "Tony@123");
		//		  map1.put("product", "ADIDAS ORIGINAL");
		//		 
		//		
		//		HashMap<String,String> map2 = new HashMap<String,String>();
		//		map2.put("email", "shetty@gmail.com");
		//		map2.put("password", "Iamking@000");
		//		map2.put("product", "ZARA COAT 3");

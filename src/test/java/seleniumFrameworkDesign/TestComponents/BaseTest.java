package seleniumFrameworkDesign.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Udemy_Selenium._SeleniumFrameworkDesign.PageObjects.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest 
{

	public WebDriver driver;
	public LoginPage loginPageObj;
	
	public WebDriver initializeDriver() throws IOException
	{
		//properties class
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\Resources\\GlobalData.properties");
		prop.load(fis);
		//incase want to give browser dynamically while running in command prompt
		String BrowserName = System.getProperty("browser")!=null ? System.getProperty("browser") :prop.getProperty("browser");
	//	String BrowserName = prop.getProperty("browser");
		
		if(BrowserName.contains("chrome"))
		{
			ChromeOptions options = new  ChromeOptions();
			WebDriverManager.chromedriver().setup();	
			
			//to run browser in headless mode
			if(BrowserName.contains("headless")){	
			options.addArguments("headless");}
			
			driver = new ChromeDriver(options);
			
		}
		
		else if(BrowserName.equals("edge"))
		{
			//code to initialize edge browser
		}
		
		else if(BrowserName.equals("firefox"))
		{
			//code to initialize firefox browser		
		}
		
		//these are common for any browser so we are writing here
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().setSize(new Dimension(1920,1080));
		return driver;
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException
	{
		//1.to read the Json file and top covert it into String varibles
		//to remover deprecation error we have passed the format for the string characters to be converted which is StandardCharsets.UTF_8
		String jsondata = FileUtils.readFileToString(new File(filepath),StandardCharsets.UTF_8);
				
		
		//2.to convert string into hashmap
		ObjectMapper mapper = new ObjectMapper();
		//all the data in the jsonfile which is converted into string is now converted into a list containing hashmaps
		List<HashMap<String, String>> data = mapper.readValue(jsondata, new TypeReference<List<HashMap<String, String>>>(){});
		
		return data;
		
	}
	
	//utility to take screenshots when TCs are failed and 
		public String getScreenShot(String testCaseName,WebDriver driver) throws IOException
		{
			TakesScreenshot ts = (TakesScreenshot)driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			File file = new File(System.getProperty("user.dir")+"//reports//"+ testCaseName + ".png");
			FileUtils.copyFile(source,file);
			return System.getProperty("user.dir")+"//reports//"+ testCaseName + ".png";
		}
		
	
	@BeforeMethod(alwaysRun=true)
	public LoginPage launchApplication() throws IOException
	{
		driver=initializeDriver();
		loginPageObj = new LoginPage(driver);
		loginPageObj.goTo();
		return loginPageObj;
	}
//Latest vesrion of selenium have issue with driver.close..Getting a lot of errors	
//	@AfterMethod(alwaysRun=true)
//	public void breakdown()
//	{
//		driver.quit();
//	}
}

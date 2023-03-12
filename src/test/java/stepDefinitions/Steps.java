package stepDefinitions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import cucumber.api.java.Before;
import cucumber.api.java.en.*;
import junit.framework.Assert;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

public class Steps extends BaseClass
{	
	@Before
	public void setup() throws IOException
	{
		logger=Logger.getLogger("nopCommerce");
		PropertyConfigurator.configure("log4j.properties");
		 logger.info("......Reading Property file.....");
		configProp=new Properties();
		FileInputStream configPropFile=new FileInputStream("config.properties");
		configProp.load(configPropFile);
		String br=configProp.getProperty("browser");
		if(br.equals("chrome"))
		{
		System.setProperty("webdriver.chrome.driver",configProp.getProperty("chromepath"));
		ldriver=new ChromeDriver();
		}else if(br.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver",configProp.getProperty("firefoxpath"));
			ldriver=new FirefoxDriver();
		}
		else if(br.equals("ie"))
		{
			System.setProperty("webdriver.ie.driver",configProp.getProperty("chromepath"));
			ldriver=new InternetExplorerDriver();
		}
	}
	@Given("Userlaunch chrome browser")
	public void userlaunch_chrome_browser() {
	    logger.info("......launching browser.....");
	    ldriver.manage().window().maximize();
	    lp=new LoginPage(ldriver);
	  
	}

	@When("User open URL {string}")
	public void user_open_URL(String url) {
		logger.info("......launching browser.....");
		ldriver.get(url);
	}

	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_Email_as_and_Password_as(String email, String password) {
		logger.info("......providing login details......");
		lp.setUsername(email);
	   lp.setPassword(password);
	}

	@When("Click on Login")
	public void click_on_Login() throws Exception {
		logger.info("......started login.....");
		lp.clickLogin();
	   Thread.sleep(3000);
	}

	@Then("Page Title should be {string}")
	public void page_Title_should_be(String title) throws Exception {
	   if(ldriver.getPageSource().contains("Login was unsuccessfull."))
	   {
		   logger.info("......login passed.....");
		   ldriver.close();
		   Assert.assertTrue(false);
	   }else {
		   logger.info("......login failed.....");
		   Assert.assertEquals(title,ldriver.getTitle());
	   }
	   Thread.sleep(3000);
	}

	@When("User clcik on Log out link")
	public void user_clcik_on_Log_out_link() throws Exception {
		logger.info("......logout passed.....");
       lp.clickLogout();
       Thread.sleep(3000);
	}
	@Then("close browser")
	public void close_browser() {
		logger.info("......close browser.....");
		ldriver.quit();
	}
	
	//Customers feature step Definition.....

@Then("User can view Dashboard")
public void user_can_view_Dashboard() {
    addcust=new AddCustomerPage(ldriver);
    Assert.assertEquals("Dashboard / nopCommerce administration",addcust.gettitle());  
}
@When("User click on customers Menu")
public void user_click_on_customers_Menu() throws Exception {
    Thread.sleep(3000);
	addcust.clickOnCustomerMenu();
}
@When("clcik on customers Menu Item")
public void clcik_on_customers_Menu_Item() throws InterruptedException {
	Thread.sleep(2000);
	addcust.clickOnCustomerMenuItem();
}
@When("click on Add new button")
public void click_on_Add_new_button() throws InterruptedException {
	Thread.sleep(2000);
	addcust.clickOnAddnew();
}
@Then("User can view Add new customer page")
public void user_can_view_Add_new_customer_page() {
   Assert.assertEquals("Add a new customer / nopCommerce administration",addcust.gettitle());
}
@When("User enter customer info")
public void user_enter_customer_info() throws Exception
{
	logger.info("......entering costomer details.....");
	String email=randomstring()+"@gmail.com";
	addcust.setEmail(email);
	Thread.sleep(2000);
	addcust.setPassword("test123");
	Thread.sleep(2000);
	addcust.setCustomerRoles("Guests");
	Thread.sleep(2000);
	addcust.setManagerOfVendor("Vendor 2");
	Thread.sleep(2000);
	addcust.setGender("Male");
	Thread.sleep(2000);
	addcust.setFirstName("veeresh");
	Thread.sleep(2000);
	addcust.setLastName("sk");
	Thread.sleep(2000);
	addcust.setDob("1/06/1994");
	Thread.sleep(2000);
	addcust.setCompanyName("busyQA");
	Thread.sleep(2000);
	addcust.setAdminContent("this is for testing.......");
}
@When("click on save button")
public void click_on_save_button() throws InterruptedException {  
	logger.info("......click on save button.....");
	addcust.clickOnSave();
	Thread.sleep(2000);
}

@Then("User can view confirmation message {string}")
public void user_can_view_confirmation_message(String msg) {
   Assert.assertTrue(ldriver.findElement(By.tagName("body")).getText().contains("The new customer has been added successfully"));
}


// Steps for search customer mail

@When("Enter Customer Email")
public void enter_Customer_Email() {
	logger.info("......searching for customer by email.....");
   searchcust=new SearchCustomerPage(ldriver);
		searchcust.setEmail("victoria_victoria@nopCommerce.com"); 
}

@When("clcik on Search button")
public void clcik_on_Search_button() throws InterruptedException {
searchcust.clickSearch();
Thread.sleep(3000);
}

@Then("User should found Email in the search table")
public void user_should_found_Email_in_the_search_table() throws InterruptedException {
	boolean status=searchcust.SearchCustomerByEmail("victoria_victoria@nopCommerce.com");
Assert.assertEquals(true, status);
}

//Steps for searching Customer by firstname and lastname
@When("Enter Customer FirstName")
public void enter_Customer_FirstName() {
	logger.info("......searching for customer by first and last name.....");
	searchcust=new SearchCustomerPage(ldriver);
	searchcust.setFirstName("Victoria");
}

@When("Enter Customer LastName")
public void enter_Customer_LastName() {
    searchcust.setLastName("Terces");
}

@Then("User should found Name in the search table")
public void user_should_found_Name_in_the_search_table() {
   boolean status=searchcust.SearchCustomerByName("Victoria Terces");
   Assert.assertEquals(true,status);
}
}



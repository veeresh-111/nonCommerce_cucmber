package stepDefinitions;

import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

public class BaseClass {
	public WebDriver ldriver;
	public LoginPage lp;
	public AddCustomerPage addcust;
	public SearchCustomerPage searchcust;
	public static Logger logger;
	public Properties configProp;
	
	//created for generating random string for Unique email
	public static String randomstring()
	{
		String generatedstring1=RandomStringUtils.randomAlphabetic(5);
		return (generatedstring1);
	}
}

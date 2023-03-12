package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import stepDefinitions.BaseClass;

public class AddCustomerPage extends BaseClass
{
		public AddCustomerPage(WebDriver rdriver)
		{
			 ldriver=rdriver;
			PageFactory.initElements(rdriver,this);
		}
		By lnkCustomers_Menu=By.xpath("//a[@href='#']/p[contains(text(),'Customers')]");
		By lnkCustomers_MenuItem=By.xpath("//a[@href='/Admin/Customer/List']/p[contains(text(),'Customers')]");
		By btnAddNew=By.xpath("//a[@href='/Admin/Customer/Create']");
		By txtEmail=By.xpath("//input[@id='Email']");
		By txtPassword=By.xpath("//input[@id='Password']");
		By txtCustomerRoles=By.xpath("//div[@class='k-multiselect-wrap k-floatwrap']");
		By lstitemAdmistrators=By.xpath("//li[contains(text(),'Administrators')]");
		By lstitemRegistered=By.xpath("//li[contains(text(),'Registered')]");
		By lstitemGuests=By.xpath("//li[contains(text(),'Guests')]");
		By lstitemVendors=By.xpath("//li[contains(text(),'Vendors')]");
		By drpMngofVendor=By.xpath("//select[@id='VendorId']");
		By rdMaleGender=By.id("Gender_Male");
		By rdFemaleGender=By.id("Gender_Female");
		By txtFirstName=By.xpath("//input[@id='FirstName']");
		By txtLastName=By.xpath("//input[@id='LastName']");
		By txtDob=By.xpath("//input[@id='DateOfBirth']");
		By txtCompanyName=By.xpath("//input[@id='Company']");
		By txtAdminContent=By.xpath("//textarea[@id='AdminComment']");
		By btnSave=By.xpath("//button[@name='save']");
		
		//Action Methods
		public String gettitle()
		{
			return ldriver.getTitle();
		}
		public void clickOnCustomerMenu()
		{
			ldriver.findElement(lnkCustomers_Menu).click();
		}
		public void clickOnCustomerMenuItem()
		{
			ldriver.findElement(lnkCustomers_MenuItem).click();
		}
		public void clickOnAddnew()
		{
			ldriver.findElement(btnAddNew).click();
		}
		public void setEmail(String email)
		{
			ldriver.findElement(txtEmail).sendKeys(email);
		}
		public void setPassword(String password)
		{
			ldriver.findElement(txtPassword).sendKeys(password);
		}
		public void setCustomerRoles(String role) throws Exception
		{
			if(!role.equals("Vendors"))
			{
				ldriver.findElement(By.xpath("//*[@id='SelectedCustomerRoleIds_taglist']/li/span[@title='delete']")).click();;
			}
			ldriver.findElement(txtCustomerRoles).click();
			WebElement listitem;
			Thread.sleep(3000);
			if(role.equals("Administrator"))
			{
				listitem=ldriver.findElement(lstitemAdmistrators);
			}else if(role.equals("Guests"))
			{
				listitem=ldriver.findElement(lstitemGuests);
			}else if(role.equals("Registered"))
			{
				listitem=ldriver.findElement(lstitemRegistered);
			}else if(role.equals("Vendors"))
			{
				listitem=ldriver.findElement(lstitemVendors);
			}else
			{
				listitem=ldriver.findElement(lstitemGuests);
			}
			//listitem.click();
			//Thread.sleep(3000);
			JavascriptExecutor js=(JavascriptExecutor)ldriver;
			js.executeScript("arguments[0].click()",listitem);
		}
		public void setManagerOfVendor(String value)
		{
			Select drp=new Select(ldriver.findElement(drpMngofVendor));
			drp.selectByVisibleText(value);
		}
		public void setGender(String gender)
		{
			if(gender.equals("Male"))
			{
				ldriver.findElement(rdMaleGender).click();
			}else if(gender.equals("Female"))
			{
				ldriver.findElement(rdFemaleGender).click();
			}else
			{
				ldriver.findElement(rdMaleGender).click(); //default
			}
		}
		public void setFirstName(String fname)
		{
			ldriver.findElement(txtFirstName).sendKeys(fname);
		}
		public void setLastName(String lname)
		{
			ldriver.findElement(txtLastName).sendKeys(lname);
		}
		public void setDob(String dob)
		{
			ldriver.findElement(txtDob).sendKeys(dob);
		}
		public void setCompanyName(String comname)
		{
			ldriver.findElement(txtCompanyName).sendKeys(comname);
		}
		public void setAdminContent(String content)
		{
			ldriver.findElement(txtAdminContent).sendKeys(content);
		}
		public void clickOnSave()
		{
			ldriver.findElement(btnSave).click();
		}	
		
}

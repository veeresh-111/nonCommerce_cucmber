package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitHelper
{
	public WebDriver ldriver;
public WaitHelper(WebDriver ldriver)
{
	this.ldriver=ldriver;
}
public void WaitforElement(WebElement element,long timeoutinseconds)
{
	WebDriverWait wait=new WebDriverWait(ldriver,timeoutinseconds);
	wait.until(ExpectedConditions.visibilityOf(element));
}
}

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;


public class FleetSelect {

	public static void main(String[] args) {
		try
		{
		
			System.setProperty("webdriver.gecko.driver", "////amxserver//amx-share//STW_QA//Jar files//geckodriver.exe");
			WebDriver driver = new FirefoxDriver();
		
		driver.manage().window().maximize();
		driver.get("http://rtwebcluster.tms-orbcomm.com");
		driver.findElement(By.xpath(".//*[@id='userName']")).sendKeys("stindia");
		driver.findElement(By.xpath(".//*[@id='password']")).sendKeys("d0v1l");
		driver.findElement(By.xpath(".//*[@id='login']")).click();
	
		
		Thread.sleep(20000);
	
		Select pageno = new Select(driver.findElement(By.xpath(".//*[@id='cmbRecordsperPage']")));
		pageno.selectByValue("300");
		while(isElementPresent(By.xpath(".//*[@id='loadMsg']"),driver))
		{
		Thread.sleep(2000);
		}
		WebElement  ele = driver.findElement(By.xpath(".//*[@id='cmbFleet']"));
		List<WebElement>page=ele.findElements(By.tagName("option"));
		System.out.println("Company count:" + page.size());
		for (int k=0; k<=page.size()-1;k++)
		{
			Select dropdown = new Select(driver.findElement(By.id("cmbFleet")));
			dropdown.selectByIndex(k);
			while(isElementPresent(By.xpath(".//*[@id='loadMsg']"),driver))
			{
			Thread.sleep(2000);
			}
			WebElement strCurrentValue = dropdown.getFirstSelectedOption();
			System.out.println(k+ "-"+strCurrentValue.getText());
			if (driver.findElement(By.xpath(".//*[@id='totalPage']")).getText() != null);
			{
			if(isElementPresent(By.xpath(".//*[@id='reeferStatusDataGrid']/tr"),driver))
			{
				Thread.sleep(3000);
			driver.findElement(By.xpath(".//*[@id='selectAllCheck']/a[1]/span")).click();
			while(isElementPresent(By.xpath(".//*[@id='loadMsg']"),driver))
			{
			Thread.sleep(2000);
			}
			driver.findElement(By.xpath(".//*[@id='viewMap']/a/img")).click();
			Thread.sleep(5000);
			ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
			Thread.sleep(2000);
			driver.switchTo().window(tabs2.get(1));
		    driver.close();
		    driver.switchTo().window(tabs2.get(0));
			}
			else
			System.out.println("No data found...");
			}
						
		}		
		//driver.close();
		}catch(Exception e)
		{e.printStackTrace();}
		
		
	}
	public static boolean isElementPresent(By by, WebDriver driver)
	{
		try
		{
			driver.findElement(by).click();
			System.out.println("Element found...");
			return true;
		}
		catch (Exception e)
		{
			System.out.println("Element not found...");
			return false;
		}
	}

}

import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;


public class Geofence {

	@Test
	
 public Map<String, Object[]> Geofence (Map<String, Object[]> data, int rc) throws Exception 
    {
  	  WebDriver driver;
  	  WebElement ele, w;
  	  String count;
  	  int c;
  	  Support s = new Support();
  	  driver = new FirefoxDriver();
	  String [][] input = s.readData("D:\\Test Data\\Test Data.xls",4);
  	  System.out.println(rc);
  	  rc ++;
  	  System.out.println(input.length);
  	  if(s.login(driver, input[0][2], input[0][3]))
  	  	{
  	  	 w =driver.findElement(By.xpath(".//*[@id='FEMenu1_HyperLinkFEMenuGeofence']"));
  	  	 ((JavascriptExecutor) driver).executeScript("arguments[0].click();", w);
  	  	if(s.isAlertPresent(driver))
			{
				Alert alert = driver.switchTo().alert();
				alert.accept();
			}
  	  driver.manage().window().maximize();
	     
	  	 for(int i=0;i<=input.length;i++)
	  		 	{
	  	        try {  	  		 	
	  				if(input[i][4].equalsIgnoreCase("Y"))
	  				{
	  					driver.findElement(By.xpath(".//*[@id='btnNewFence']")).click();
	  					driver.findElement(By.xpath(".//*[@id='MainContainer_txtNewFence']")).clear();
	  			    	driver.findElement(By.xpath(".//*[@id='MainContainer_txtNewFence']")).sendKeys(input[i][5]);
	  					driver.findElement(By.xpath(".//*[@id='btnCreateFence']")).click();
	  				    Thread.sleep(3000);
	 	  	  				  	  				}
	  				
	  				
	  			}catch(Exception e)
	  	{
	System.out.println(e);
	  	}
	  		 	}
  	  	}
  	  	  	
	driver.quit();
  	  return data;
    }
}

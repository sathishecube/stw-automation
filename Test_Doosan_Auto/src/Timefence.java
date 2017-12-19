import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;


public class Timefence {

	@Test
	
	 public Map<String, Object[]> Timefence (Map<String, Object[]> data, int rc) throws Exception 
	    {
	  	  WebDriver driver;
	  	  Support s = new Support();
	  	  driver = new FirefoxDriver();
		  String [][] input = s.readData("D:\\Test Data\\Test Data.xls",5);
	  	  System.out.println(rc);
	  	  rc ++;
	  	  //String s2;
	  	  System.out.println(input.length);
	  	  if(s.login(driver, input[0][2], input[0][3]))
	  	  	{
	  	  	 driver.findElement(By.xpath(".//*[@id='FEMenu1_HyperLinkFEMenuFleet']")).click();
	  	  	 driver.findElement(By.xpath(".//*[@id='FEMenu1_HyperLinkFEMenuTimefence']")).click();
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

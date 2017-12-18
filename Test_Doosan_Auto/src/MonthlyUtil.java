import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


public class MonthlyUtil {

	public Map<String, Object[]> Monthly (Map<String, Object[]> data, int rc) throws Exception 
    {
		WebDriver driver;
	      WebElement ele;
		  String count;
		  int c;
		  Support s = new Support();
		  driver = new FirefoxDriver();
		  String [][] input = s.readData("D:\\Test Data\\Test Data.xls",6);
		  System.out.println(rc);
		  rc ++;
		  System.out.println(input.length);
		  if(s.login(driver, input[0][2], input[0][3]))
		  	{
		  	 driver.findElement(By.xpath(".//*[@id='FEMenu1_HyperLinkFEMenuUtilizationReport']")).click();
		  	driver.findElement(By.xpath(".//*[@id='FEMenu1_HyperLinkFEMenuMonthlyReport']")).click();
		  	if(s.isAlertPresent(driver))
				{
					Alert alert = driver.switchTo().alert();
					alert.accept();
				}
			
	    }
		  driver.quit();
		  return data;
		
    }
}

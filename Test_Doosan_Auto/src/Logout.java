import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


public class Logout {
	public Map<String, Object[]> log (Map<String, Object[]> data, int rc) throws Exception 
    {WebDriver driver;
    WebElement ele;
	  String count;
	  int c;
	  String Acop = null;
	  Support s = new Support();
	  driver = new FirefoxDriver();
	  String [][] input = s.readData("D:\\Test Data\\Test Data.xls",14);
	  System.out.println(rc);
	  rc ++;
	  System.out.println(input.length);
	  try{
		  
	  if(s.login(driver, input[0][2], input[0][3]))
	  	{
		  //long stime=System.currentTimeMillis();
		 driver.findElement(By.xpath(".//*[@id='FEMenu1_HyperLinkFEMenuLogout']")).click();
	  	 Acop = "Login Successful";
	  	if(s.isAlertPresent(driver))
			{
				Alert alert = driver.switchTo().alert();
				alert.accept();
			}
	  	}
	  }
	  	
	  catch(Exception e)
	  {
		  if(s.testStatus(input[0][4], Acop))
		  {
	  	  				data.put(""+rc, new Object[] {input[0][0],"Logout", input[0][1], input[0][4],Acop, "Pass" });
		  }
	  	  			else{
	  	  				data.put(""+rc, new Object[] {input[0][0],"Logout", input[0][1], input[0][4], Acop, "Fail" });
	  	  			}
	  }
		
	  return data;
    
    }
}

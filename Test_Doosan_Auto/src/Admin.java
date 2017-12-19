import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Admin {

	public Map<String, Object[]> Admn (Map<String, Object[]> data, int rc) throws Exception 
    {
	  WebDriver driver;
      WebElement ele;
	  String acop;
	  int c;
	  String eopt,tc,tcdesc;
	  Support s = new Support();
	  driver = new FirefoxDriver();
	  String [][] input = s.readData("D:\\Test Data\\Test Data.xls",13);
	  System.out.println(rc);
	  rc ++;
	  if(s.login(driver, input[0][2], input[0][3]))
	  	{
		   driver.manage().window().maximize();
	  		driver.findElement(By.xpath(".//*[@id='FEMenu1_HyperLinkFEMenuAdminReport']")).click();
	  		driver.findElement(By.xpath(".//*[@id='FEMenu1_HyperLinkSendMsg']")).click();
	  			int i=0;
	  	for(;i<input.length; i++)
	  		{
	  		
	  		      if(input[i][4].equalsIgnoreCase("Y"))
	  		       {
	  		    	System.out.println("Send SMS block");
	  		    	System.out.println(input[i][0]);
	  	  		    System.out.println(input[i][8]);
	  		    	  driver.findElement(By.xpath(".//*[@id='btnClear']")).click();
	  		          driver.findElement(By.xpath(".//*[@id='btnSMS']")).click();
		           if(!(input[i][6].equalsIgnoreCase("")))
		           {
		        	   System.out.println(input[i][6]);
		           driver.findElement(By.xpath(".//*[@id='txtSearch']")).sendKeys(input[i][6]);
		           driver.findElement(By.xpath(".//*[@id='btnSearch']")).click();
		           driver.findElement(By.xpath(".//*[@id='tblFenceName']/tbody/tr[1]/td[1]/div")).click();
		           driver.findElement(By.xpath(".//*[@id='tblFenceName']/tbody/tr[1]/td[1]/div")).click();
		           }
		           System.out.println(input[i][7]);
		           driver.findElement(By.xpath(".//*[@id='btnCancel']")).click();
		           driver.findElement(By.xpath(".//*[@id='MainContainer_txtSMS']")).sendKeys(input[i][7]);
		           driver.findElement(By.xpath(".//*[@id='btnSend']")).click();
		           Thread.sleep(2000);
		           int f=0;
		           if(s.isAlertPresent(driver)){
		        	  f=1;
						try {
					        Alert alert = driver.switchTo().alert();
					        acop=alert.getText();
					        System.out.println(acop);
					        alert.accept();}
						    catch (Exception e) {
					        System.out.println(e);
						    }
		           }
						if(f!=1)
						{
							Thread.sleep(3000);
		                    driver.findElement(By.xpath("html/body/div[1]/div[11]/div/button")).click();
		           
						}
		           
		           
		           
	  		       }else if(input[i][5].equalsIgnoreCase("Y"))
		  	  		       {
		  		    		driver.findElement(By.xpath(".//*[@id='btnClear']")).click();
		  	  		       System.out.println("Send SAT block");
		  	  		       System.out.println(input[i][0]);
		  	  		       System.out.println(input[i][8]);
		  	  		       driver.findElement(By.xpath(".//*[@id='btnSAT']")).click();
		  	  		        if(!(input[i][6].equalsIgnoreCase(""))){
		  		           driver.findElement(By.xpath(".//*[@id='txtSearch']")).sendKeys(input[i][6]);
		  		           driver.findElement(By.xpath(".//*[@id='btnSearch']")).click();
		  		           driver.findElement(By.xpath(".//*[@id='tblFenceName']/tbody/tr[1]/td[1]/div")).click();
		  		           driver.findElement(By.xpath(".//*[@id='tblFenceName']/tbody/tr[1]/td[1]/div")).click();
		  		           }
		  	  		        driver.findElement(By.xpath(".//*[@id='btnCancel']")).click();
		  		           driver.findElement(By.xpath(".//*[@id='MainContainer_txtSubject']")).sendKeys(input[i][8]);
		  		           driver.findElement(By.xpath(".//*[@id='MainContainer_txtBody']")).sendKeys(input[i][9]);
		  		           driver.findElement(By.xpath(".//*[@id='btnSend']")).click();
		  	  		       int f=0;
		  		           Thread.sleep (2000);
		  		           if(s.isAlertPresent(driver)){
			        	 f=1;
							try {
						        Alert alert = driver.switchTo().alert();
						        acop=alert.getText();
						        System.out.println(acop);
						        alert.accept();}
							    catch (Exception e) {
						        System.out.println(e);
							    }
		  		           }
							if(f!=1)
							{
								Thread.sleep (2000);
		  		                driver.findElement(By.xpath("html/body/div[1]/div[11]/div/button")).click();
							}
		  		           
		  	  		       }		           
		                    	  		
	  	 }
	  	
	   }
	 driver.quit();
	  	return data;
	 }
	}

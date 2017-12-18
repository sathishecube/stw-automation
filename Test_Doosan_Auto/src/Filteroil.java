import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class Filteroil {
	@Test
	 public Map<String, Object[]> Filter(Map<String, Object[]> data, int rc) throws Exception 
	    {
	  	  WebDriver driver;
	  	  WebElement ele;
	  	int last=11;
		int next=1;
		int tpage;
		int totpages;
		int pages;
		int count;
		int col=0;
	
	  	  Support s = new Support();
	  	  driver = new FirefoxDriver();
		  String acop;
	  	  String [][] input = s.readData("D:\\Test Data\\Test Data.xls",10);
	  	  System.out.println(rc);
	  	  rc ++;
	  	  System.out.println(input.length);
	  	  if(s.login(driver, input[0][2], input[0][3]))
	  	  	{
	  		driver.findElement(By.xpath(".//*[@id='FEMenu1_HyperLinkFEMenuFilterOilManagementReport']")).click();
            }
	  	for(int i=1;i<=input.length;i++){
  			 long stime=System.currentTimeMillis();
  			 
/****************** Equipment id search **********************/ 			 
	  	 System.out.println(input[i-1][5]);
	 	  	  	if(input[i-1][5] != null)
	 	  	  	{
	 	  	  		try
	 	  	  		{
	 	  	  			driver.findElement(By.xpath(".//*[@id='MainContainer_txtEquipmentID']")).sendKeys(input[i-1][5]);
	 	  	  	        driver.findElement(By.xpath(".//*[@id='MainContainer_SearchButton']")).click();
	 	  	  	        tpage=Integer.parseInt(driver.findElement(By.xpath(".//*[@id='MainContainer_dpLvRpt_ctl00_lblTotalItems']")).getText());
	 		  		    System.out.println(tpage);
	 		  		 String aopt = "Equipment ID search success";
 		data.put(""+rc, new Object[] {input[i-1][0],"FilterOil", input[i-1][1], input[i-1][7], aopt, "Pass", s.timestamp(stime) });	 	  	  	        
	 	  	  		} 
	 	  	  		catch (Exception e)
	 	  	  		{
	 	  	  			String aopt="Equipment id field not found";
	 	  	  						rc++;
	 	  	  			i++;
	 	  	  		    driver.findElement(By.xpath(".//*[@id='MainContainer_txtEquipmentID']")).clear();
	 	  	  			e.printStackTrace();
	 	  	  			driver.findElement(By.xpath(".//*[@id='MainContainer_ClearButton']")).click();
	 	  	  		if(s.testStatus(input[i-1][7], aopt))
 	  	  				data.put(""+rc, new Object[] {input[i-1][0],"FilterOil", input[i-1][1], input[i-1][7], aopt, "Pass", s.timestamp(stime) });
 	  	  			else
 	  	  				data.put(""+rc, new Object[] {input[i-1][0],"FilterOil", input[i-1][1], input[i-1][7], aopt, "Fail", s.timestamp(stime) });
	 	  	  			break;
	 	  	  		}
	 	  	  	driver.findElement(By.xpath(".//*[@id='MainContainer_ClearButton']")).click();
	 	  	    String aopt = "Equipment id search success";
	 	  	    data.put(""+rc, new Object[] {input[i-1][0],"FilterOil", input[i-1][1], input[i-1][7], aopt, "Pass", s.timestamp(stime) });
	 	  	  	}
	 	  	  	

/******************* Equipment  Type search **********************/ 
	 	  	 System.out.println(input[i-1][4]);
	 	  	 if(input[i-1][4] != null){
	  	 	  	  		try
	  	 	  	  		{
	  	 	  	  			Select model = new Select(driver.findElement(By.xpath(".//*[@id='MainContainer_drpSearchModel']")));
	  	 	  	  			model.selectByValue(input[i-1][4]);
	  	 	  	  			System.out.println(input[i-1][4]);
	  	 	  	  	        driver.findElement(By.xpath(".//*[@id='MainContainer_SearchButton']")).click();
	  	 	  	  	        tpage=Integer.parseInt(driver.findElement(By.xpath(".//*[@id='MainContainer_dpLvRpt_ctl00_lblTotalItems']")).getText());
	 		  		        System.out.println(tpage);
	 		  		     String aopt = "Equipment type search success";
	 		 data.put(""+rc, new Object[] {input[i-1][0],"FilterOil", input[i-1][1], input[i-1][7], aopt, "Pass", s.timestamp(stime) });
	  	 	  	  	        
	  	 	  	  		}
	  	 	  	  		catch (Exception e)
	  	 	  	  		{
	  	 	  	  			String aopt="Search Failed.";
	  	 	  	  			if(s.testStatus(input[i-1][13], aopt))
	  	 	  	  				data.put(""+rc, new Object[] {input[i-1][0],"FilterOil", input[i-1][1], input[i-1][7], aopt, "Pass", s.timestamp(stime) });
	  	 	  	  			else
	  	 	  	  				data.put(""+rc, new Object[] {input[i-1][0],"FilterOil", input[i-1][1], input[i-1][7], aopt, "Fail", s.timestamp(stime) });
	  	 	  	  			rc++;
	  	 	  	  			e.printStackTrace();
	  	 	  	  			i++;
	  	 	  	  			driver.findElement(By.xpath(".//*[@id='MainContainer_ClearButton']")).click();
	  	 	  	  			break;
	  	 	  	  		}
	  	 	  	  	driver.findElement(By.xpath(".//*[@id='MainContainer_ClearButton']")).click();
	  	 	  	    String aopt = "Equipment type search success";
	  	 	  	    data.put(""+rc, new Object[] {input[i-1][0],"FilterOil", input[i-1][1], input[i-1][7], aopt, "Pass", s.timestamp(stime) });
	  	 	  	  	}

/******************* Data Validation **********************/ 	
	 	  	System.out.println(input[i-1][9]);
 	  	  	if(input[i-1][9].equalsIgnoreCase("Y"))
 	  	  	{
 	  	  		try
 	  	  		{
 	  	  			driver.findElement(By.xpath(".//*[@id='MainContainer_txtEquipmentID']")).sendKeys(input[i-1][5]);
 	  	  	        driver.findElement(By.xpath(".//*[@id='MainContainer_SearchButton']")).click();
 	  	  	        
 	  	  	        driver.findElement(By.xpath(".//*[@id='tblFilterAndOil']/tbody/tr[2]/td[4]")).click();
 	  	  	        
 	  	  	        String a =driver.findElement(By.xpath(".//*[@id='divFilterAndOilDetails']/table/tbody/tr/td[6]")).getText();
 	  	  	        String b = driver.findElement(By.xpath(".//*[@id='divFilterAndOilDetails']/table/tbody/tr/td[12]")).getText();
 	  	  	        
 	  	  	   
 	  	  	   String c = driver.findElement(By.xpath(".//*[@id='divFilterAndOilDetails']/table/tbody/tr/td[15]")).getText();
 	  	  	  
 	  	  	if(driver.findElement(By.xpath(".//*[@id='divFilterAndOilDetails']/table/tbody/tr/td[3]")).getText().equals("Doosan") && (a.equalsIgnoreCase(driver.findElement(By.xpath(".//*[@id='tblFilterAndOil']/tbody/tr[2]/td[3]")).getText()))&&(b.equalsIgnoreCase(driver.findElement(By.xpath(".//*[@id='tblFilterAndOil']/tbody/tr[2]/td[3]")).getText()))&& (c.equalsIgnoreCase(driver.findElement(By.xpath(".//*[@id='tblFilterAndOil']/tbody/tr[2]/td[5]")).getText())) )
	  	        {
	  	        System.out.println("Validation match");
	  	        }
 	  	  	String aopt = "Data Validation success";
 	  	 data.put(""+rc, new Object[] {input[i-1][0],"FilterOil", input[i-1][1], input[i-1][7], aopt, "Pass", s.timestamp(stime) });
	  	        
 	  	  		}
				
 	  	  		catch (Exception e)
 	  	  		{
 	  	  			String aopt="Equipment id field not found";
 	  	  			if(s.testStatus(input[i-1][7], aopt))
 	  	  				data.put(""+rc, new Object[] {input[i-1][0],"FilterOil", input[i-1][1], input[i-1][7], aopt, "Pass", s.timestamp(stime) });
 	  	  			else
 	  	  				data.put(""+rc, new Object[] {input[i-1][0],"FilterOil", input[i-1][1], input[i-1][7], aopt, "Fail", s.timestamp(stime) });
 	  	  			rc++;
 	  	  			i++;
 	  	  		    driver.findElement(By.xpath(".//*[@id='MainContainer_txtEquipmentID']")).clear();
 	  	  			e.printStackTrace();
 	  	  			driver.findElement(By.xpath(".//*[@id='MainContainer_ClearButton']")).click();
 	  	  			break;
 	  	  		}
 	  	  	driver.findElement(By.xpath(".//*[@id='MainContainer_ClearButton']")).click();
 	  	  	}
		  	
 	  	  	}
 	  	  	
 		  	  	  	

	 	driver.quit();
		return data;
	    }
}

import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;


public class FleetDetail {
	 @Test
	 
	    public Map<String, Object[]> fleetDetail(Map<String, Object[]> data, int rc) throws Exception 
	    {
	  	  	WebDriver driver;
	  	  	WebElement ele;
	  	  	Support s = new Support();
	  	  	driver = new FirefoxDriver();
			String acop;
	  	  	String [][] input = s.readData("D:\\Test Data\\Test Data.xls",3);
	  	  	System.out.println(rc);
	  	  	rc ++;
	  	  	FleetHelp helper = new FleetHelp();
	  	  	
	  	  	//System.out.println(input.length);
	  	  	if(s.login(driver, input[0][2], input[0][3]))
	  	  	{
	  	  		driver.findElement(By.xpath(".//*[@id='FEMenu1_HyperLinkFEMenuFleet']")).click();
	  	  		driver.findElement(By.xpath(".//*[@id='FEMenu1_HyperLinkFEMenuFleetDetail']")).click();
		 	
	  	  		try
	  	  		{
	  	  			int i =1;
	  	  			while(i<=input.length)
	  	  			{
	  	  				
	  	  				for(;i<=input.length;i++)
	  	  				{
	  	  					long stime=System.currentTimeMillis();
	  	  					if(s.isAlertPresent(driver))
	  	  					{
	  	  						Alert alert = driver.switchTo().alert();
	  	  						alert.accept();
	  	  					}
	  	  					System.out.println("Hedar check");
	  	  					//System.out.println(input[i-1][14]);
	  	  					if(input[i-1][14].equalsIgnoreCase("Y"))
	  	  					{
	  	  						//Fleet_detail_Helper helper = new Fleet_detail_Helper();
	  	  						int res = helper.headerCount(driver, "D:\\Test Data\\Column header.xls");
	  	  						if(res==0)
	  	  						{
	  	  							data.put(""+rc, new Object[] {input[i-1][0],"FleetDetail", input[i-1][1], input[i-1][13], "All Headers match.", "Pass",s.timestamp(stime) });
	  	  							rc++;
	  	  							i++;
	  	  							driver.findElement(By.xpath(".//*[@id='MainContainer_Clear']")).click();
	  	  						}
	  	  						else
	  	  						{
	  	  							data.put(""+rc, new Object[] {input[i-1][0],"FleetDetail", input[i-1][1], input[i-1][13], "Headers doesn't match...", "Fail", s.timestamp(stime) });
	  	  							rc++;
	  	  							i++;
	  	  							driver.findElement(By.xpath(".//*[@id='MainContainer_Clear']")).click();
	  	  						}
	  	  						break;
	  	  					}
	  	  				
	  	  					
	  	  					try
	  	  	  	 	  	  	{
	  	  	  	 	  	  	 ele = (new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='MainContainer_StartDate']")));
	  	  	  	 	  	  		if(input[i-1][9]!="")
	  	  	  	 	  	  		{
	  	  	  	 	  	  			ele=driver.findElement(By.xpath(".//*[@id='MainContainer_StartDate']"));
	  	  	  	 	  	  			String date = input[i-1][9].substring(0,2);
	  	  	  	 	  	  			String year = input[i-1][9].substring(7);
	  	  	  	 	  	  			String month = input[i-1][9].substring(3,6);
	  	  	  	 	  	  			System.out.println(date+" "+month+" "+year);
	  	  	  	 	  	  			s.dateSelector(driver,ele,month,year,date);
	  	  	  	 	  	  		}
	  	  	  	 	  	  		else
	  	  	  	 	  	  		{
	  	  	  	 	  	  			System.out.println("No Start date provided...");
	  	  	  	 	  	  		}
	  	  	  	 	  	  	}
	  	  	  	 	  	  	catch(Exception e)
	  	  	  	 	  	  	{
	  	  	  	 	  	  		data.put(""+rc, new Object[] {input[i-1][0],"FleetDetail", input[i-1][1], input[i-1][13], "Error selecting Start date...", "Fail", s.timestamp(stime) });
	  	  	  	 	  	  		rc++;
	  	  	  	 	  	  		e.printStackTrace();
	  	  	  	 	  	  		i++;
	  	  	  	 	  	  		driver.findElement(By.xpath(".//*[@id='MainContainer_Clear']")).click();
	  	  	  	 	  	  		break;
	  	  	  	 	  	  		
	  	  	  	 	  	  	}
	  	  	  	 	  	  	try
	  	  	  	 	  	  	{
	  	  	  	 	  	  		if(input[i-1][10]!="")
	  	  	  	 	  	  		{
	  	  	  	 	  	  			ele = driver.findElement(By.xpath(".//*[@id='MainContainer_EndDate']"));
	  	  	  	 	  	  			String date = input[i-1][10].substring(0,2);
	  	  	  	 	  	  			String year = input[i-1][10].substring(7);
	  	  	  	 	  	  			String month = input[i-1][10].substring(3,6);
	  	  	  	 	  	  			System.out.println(date+" "+month+" "+year);
	  	  	  	 	  	  			s.dateSelector(driver,ele,month,year,date);
	  	  	  	 	  	  		}
	  	  	  	 	  	  		else
	  	  	  	 	  	  		{
	  	  	  	 	  	  			
	  	  	  	 	  	  			System.out.println("No End date provided...");
	  	  	  	 	  	  			
	  	  	  	 	  	  		}
	  	  	  	 	  	  	}
	  	  	  	 	  	  	catch(Exception e)
	  	  	  	 	  	  	{
	  	  	  	 	  	  		data.put(""+rc, new Object[] {input[i-1][0],"FleetDetail", input[i-1][1], input[i-1][13], "End date should be greater than start date!", "Fail", s.timestamp(stime) });
	  	  	  	 	  	  		rc++;
	  	  	  	 	  	  		i++;
	  	  	  	 	  	  		e.printStackTrace();
	  	  	  	 	  	  		driver.findElement(By.xpath(".//*[@id='MainContainer_Clear']")).click();
	  	  	  	 	  	  		break;
	  	  	  	 	  	  	}
	  				
	  	  	  	 	  	  	if(input[i-1][4] != null)
	  	  	  	 	  	  	{
	  	  	  	 	  	  		try
	  	  	  	 	  	  		{
	  	  	  	 	  	  			Select category = new Select(driver.findElement(By.xpath(".//*[@id='MainContainer_drpCategory']")));
	  	  	  	 	  	  			category.selectByValue(input[i-1][4]);
	  	  	  	 	  	  			System.out.println(input[i-1][4]);
	  	  	  	 	  	  		}
	  	  	  	 	  	  		catch (Exception e)
	  	  	  	 	  	  		{
	  	  	  	 	  	  			String aopt="Search Failed.";
	  	  	  	 	  	  			if(s.testStatus(input[i-1][13], aopt))
	  	  	  	 	  	  				data.put(""+rc, new Object[] {input[i-1][0],"FleetDetail", input[i-1][1], input[i-1][13], aopt, "Pass", s.timestamp(stime) });
	  	  	  	 	  	  			else
	  	  	  	 	  	  				data.put(""+rc, new Object[] {input[i-1][0],"FleetDetail", input[i-1][1], input[i-1][13], aopt, "Fail", s.timestamp(stime) });
	  	  	  	 	  	  			rc++;
	  	  	  	 	  	  			i++;
	  	  	  	 	  	  			e.printStackTrace();
	  	  	  	 	  	  			driver.findElement(By.xpath(".//*[@id='MainContainer_Clear']")).click();
	  	  	  	 	  	  			break;
	  	  	  	 	  	  		}
	  	  	  	 	  	  	}
	  	  			
	  	  	  	 	  	  	if(input[i-1][5] != null)
	  	  	  	 	  	  	{
	  	  	  	 	  	  		try
	  	  	  	 	  	  		{
	  	  	  	 	  	  			Select model = new Select(driver.findElement(By.xpath(".//*[@id='MainContainer_drpSearchModel']")));
	  	  	  	 	  	  			model.selectByValue(input[i-1][5]);
	  	  	  	 	  	  			System.out.println(input[i-1][5]);
	  	  	  	 	  	  		}
	  	  	  	 	  	  		catch (Exception e)
	  	  	  	 	  	  		{
	  	  	  	 	  	  			String aopt="Search Failed.";
	  	  	  	 	  	  			if(s.testStatus(input[i-1][13], aopt))
	  	  	  	 	  	  				data.put(""+rc, new Object[] {input[i-1][0],"FleetDetail", input[i-1][1], input[i-1][13], aopt, "Pass", s.timestamp(stime) });
	  	  	  	 	  	  			else
	  	  	  	 	  	  				data.put(""+rc, new Object[] {input[i-1][0],"FleetDetail", input[i-1][1], input[i-1][13], aopt, "Fail", s.timestamp(stime) });
	  	  	  	 	  	  			rc++;
	  	  	  	 	  	  			e.printStackTrace();
	  	  	  	 	  	  			i++;
	  	  	  	 	  	  			driver.findElement(By.xpath(".//*[@id='MainContainer_Clear']")).click();
	  	  	  	 	  	  			break;
	  	  	  	 	  	  		}
	  	  	  	 	  	  	}
	  	  	  	 	  	  	
	  	  	  	 	  	  	if(input[i-1][6] != null)
	  	  	  	 	  	  	{
	  	  	  	 	  	  		try
	  	  	  	 	  	  		{
	  	  	  	 	  	  			driver.findElement(By.xpath(".//*[@id='MainContainer_txtEquipmentID']")).sendKeys(input[i-1][6]);
	  	  						}
	  	  	  	 	  	  		catch (Exception e)
	  	  	  	 	  	  		{
	  	  	  	 	  	  			String aopt="Equipment id field not found";
	  	  	  	 	  	  			if(s.testStatus(input[i-1][13], aopt))
	  	  	  	 	  	  				data.put(""+rc, new Object[] {input[i-1][0],"FleetDetail", input[i-1][1], input[i-1][13], aopt, "Pass", s.timestamp(stime) });
	  	  	  	 	  	  			else
	  	  	  	 	  	  				data.put(""+rc, new Object[] {input[i-1][0],"FleetDetail", input[i-1][1], input[i-1][13], aopt, "Fail", s.timestamp(stime) });
	  	  	  	 	  	  			rc++;
	  	  	  	 	  	  			i++;
	  	  	  	 	  	  			driver.findElement(By.xpath(".//*[@id='MainContainer_txtEquipmentID']")).clear();
	  	  	  	 	  	  			e.printStackTrace();
	  	  	  	 	  	  			driver.findElement(By.xpath(".//*[@id='MainContainer_Clear']")).click();
	  	  	  	 	  	  			break;
	  	  	  	 	  	  		}
	  	  	  	 	  	  	}
	  	  	  	 	  	  	System.out.println(input[i-1][7]);
	  	  	  	 	  	  	
	  	  	  	 	  	  	if(input[i-1][7] != null)
	  	  	  	 	  	  	{
	  	  	  	 	  	  		try{
	  	  	  	 	  	  			driver.findElement(By.xpath(".//*[@id='MainContainer_txtOwner']")).sendKeys(input[i-1][7]);
	  	  	  	 	  	  		}
	  	  	  	 	  	  		catch (Exception e)
	  	  	  	 	  	  		{
	  	  	  	 	  	  			data.put(""+rc, new Object[] {input[i-1][0],"FleetDetail", input[i-1][1], input[i-1][13], "Owner not found", "Fail", s.timestamp(stime) });
	  	  	  	 	  	  			rc++;
	  	  	  	 	  	  			i++;
	  	  	  	 	  	  			e.printStackTrace();
	  	  	  	 	  	  			driver.findElement(By.xpath(".//*[@id='MainContainer_txtOwner']")).clear();
	  	  	  	 	  	  			driver.findElement(By.xpath(".//*[@id='MainContainer_Clear']")).click();
	  	  	  	 	  	  			break;
	  	  	  	 	  	  		}
	  	  					}
	  	  			
	  	  	  	 	  	  	if(input[i-1][8] != null)
	  	  	  	 	  	  	{
	  	  	  	 	  	  		try
	  	  	  	 	  	  		{
	  	  	  	 	  	  			driver.findElement(By.xpath(".//*[@id='MainContainer_txtSerialNo']")).sendKeys(input[i-1][8]);
	  	  	  	 	  	  		}
	  	  	  	 	  	  		catch (Exception e)
	  	  	  	 	  	  		{
		  							data.put(""+rc, new Object[] {input[i-1][0],"FleetDetail", input[i-1][1], input[i-1][13], "Serial no not found", "Fail", s.timestamp(stime) });
		  							rc++;
		  							i++;
		  							e.printStackTrace();
		  							driver.findElement(By.xpath(".//*[@id='MainContainer_Clear']")).click();
		  							break;
	  	  	  	 	  	  		}
	  	  	  	 	  	  	}
	  	  			
	  	  	  	 	  	  	if(input[i-1][15].equalsIgnoreCase("Y"))
	  	  	  	 	  	  	{
	  	  	  	 	  	  		driver.findElement(By.xpath(".//*[@id='MainContainer_Clear']")).click();
	  	  	  	 	  	  		System.out.println(driver.findElement(By.xpath(".//*[@id='MainContainer_txtSerialNo']")).getText());
	  	  	  	 	  	  		System.out.println(driver.findElement(By.xpath(".//*[@id='MainContainer_txtOwner']")).getText());
	  	  	  	 	  	  		System.out.println(driver.findElement(By.xpath(".//*[@id='MainContainer_txtEquipmentID']")).getText());
	  	  	  	 	  	  		
	  	  	  	 	  	  		if(driver.findElement(By.xpath(".//*[@id='MainContainer_txtSerialNo']")).getText().isEmpty() && 
	  	  	  	 	  	  		   driver.findElement(By.xpath(".//*[@id='MainContainer_txtOwner']")).getText().isEmpty() && 
	  	  	  	 	  	  		   driver.findElement(By.xpath(".//*[@id='MainContainer_txtEquipmentID']")).getText().isEmpty())
	  	  	  	 	  	  		{
	  	  	  	 	  	  			data.put(""+rc, new Object[] {input[i-1][0],"FleetDetail", input[i-1][1], input[i-1][13], "Clear function successful", "Pass", s.timestamp(stime) });
	  	  	  	 	  	  			rc++;
	  	  	  	 	  	  			i++;
	  	  	  	 	  	  			break;
	  	  	  	 	  	  		}
	  	  	  	 	  	  		else
	  	  	  	 	  	  		{
	  	  	  	 	  	  			data.put(""+rc, new Object[] {input[i-1][0],"FleetDetail", input[i-1][1], input[i-1][13], "Clear function Failed", "Fail", s.timestamp(stime) });
	  	  	  	 	  	  			rc++;
	  	  	  	 	  	  			i++;
	  	  	  	 	  	  			break;
	  	  	  	 	  	  		}
	  	  	  	 	  	  	}
	  	  	  	 	  	  	
	  	  	  	 	  	  	driver.findElement(By.xpath(".//*[@id='MainContainer_RefreshButton']")).click();
	  	  	  	 	  	  	
	  	  	  	 	  	  	if(!s.isAlertPresent(driver))
	  	  	  	 	  	  	{
	  	  	  	 	  	  	try
	  	  	  	 	  	  	{
	  	  	  	 	  	  		if(driver.findElement(By.xpath(".//*[@id='MainContainer_lbl_NoRecords']")).isDisplayed())
	  	  	  	 	  	  		{
	  	  	  	 	  	  			String aopt="Search Failed.";
	  	  	  	 	  	  			if(s.testStatus(input[i-1][13], aopt))
	  	  	  	 	  	  				data.put(""+rc, new Object[] {input[i-1][0],"FleetDetail", input[i-1][1], input[i-1][13], aopt, "Pass", s.timestamp(stime) });
	  	  	  	 	  	  			else
	  	  	  	 	  	  				data.put(""+rc, new Object[] {input[i-1][0],"FleetDetail", input[i-1][1], input[i-1][13], aopt, "Fail", s.timestamp(stime) });
	  	  	  	 	  	  			rc++;
	  	  	  	 	  	  		}
	  	  	  	 	  	  	}
	  	  	  	 	  	  	catch (Exception E)
	  	  	  	 	  	  	{
	  	  	  	 	  	  		
	  	  	  	 	  	  		boolean count_check=false;
	  	  	  	 	  	  		if(!input[i-1][4].equalsIgnoreCase(""))
	  	  	  	 	  	  			count_check = helper.countChecker(driver, input[i-1][4], "Category");
	  	  	  	 	  	  		else if(!input[i-1][5].equalsIgnoreCase(""))
	  	  	  	 	  	  			count_check = helper.countChecker(driver, input[i-1][5], "Model");
	  	  	  	 	  	  		else if(!input[i-1][6].equalsIgnoreCase(""))
	  	  	  	 	  	  			count_check = helper.countChecker(driver, input[i-1][6], "EquipmentID");
	  	  	  	 	  	  		else if(!input[i-1][7].equalsIgnoreCase(""))
	  	  	  	 	  	  			count_check = helper.countChecker(driver, input[i-1][7], "Owner");
	  	  	  	 	  	  		else if(!input[i-1][8].equalsIgnoreCase(""))
	  	  	  	 	  	  			count_check = helper.countChecker(driver, input[i-1][8], "Serial No");
	  	  	  	 	  	  		else
	  	  	  	 	  	  		{
	  	  	  	 	  	  			count_check = helper.countChecker(driver, "", "");
	  	  	  	 	  	  			if(count_check)
	  	  	  	 	  	  			{
	  	  	  	 	  	  				data.put(""+rc, new Object[] {input[i-1][0],"FleetDetail", input[i-1][1], input[i-1][13], "Count matches", "Pass", s.timestamp(stime) });
	  	  	  	 	  	  				rc ++;
	  	  	  	 	  	  			}
	  	  	  	 	  	  			else
	  	  	  	 	  	  			{
	  	  	  	 	  	  				data.put(""+rc, new Object[] {input[i-1][0],"FleetDetail", input[i-1][1], input[i-1][13], "Count doesn't match...", "Fail", s.timestamp(stime) });
	  	  	  	 	  	  				rc ++;
	  	  	  	 	  	  			}
	  	  	  	 	  	  		}
	  	  	  	 	  	  			
	  	  	  	 	  	  		if(input[i-1][12].equalsIgnoreCase("Y"))
	  	  	  	 	  	  		{
	  	  	  	 	  	  			driver.findElement(By.xpath(".//*[@id='MainContainer_ExportButton']")).click();
	  	  	  	 	  	  			s.saveAsDialogue();
	  	  	  	 	  	  		}
		  				
	  	  	  	 	  	  		if(input[i-1][11].equalsIgnoreCase("Y"))
	  	  	  	 	  	  		{
	  	  	  	 	  	  			driver.findElement(By.xpath(".//*[@id='MainContainer_PrintButton']")).click();
	  	  	  	 	  	  			s.print();
	  	  	  	 	  	  			
	  	  	  	 	  	  		}
	  	  	  	 	  	  		if(count_check)
	  	  	  	 	  	  		{
	  	  	  	 	  	  			data.put(""+rc, new Object[] {input[i-1][0],"FleetDetail", input[i-1][1], input[i-1][13], "Search Successful...", "Pass", s.timestamp(stime) });
	  	  	  	 	  	  			rc ++;
	  	  	  	 	  	  		}
	  	  	  	 	  	  		else
	  	  	  	 	  	  		{
	  	  	  	 	  	  			data.put(""+rc, new Object[] {input[i-1][0],"FleetDetail", input[i-1][1], input[i-1][13], "Count doesn't match...", "Fail", s.timestamp(stime) });
	  	  	  	 	  	  			rc ++;
	  	  	  	 	  	  		}
	  	  	  	 	  	  	}
	  	  	  	 	  	  	driver.findElement(By.xpath(".//*[@id='MainContainer_Clear']")).click();
	  	  	  	 	  	  	}
	  	  	  	 	  	  	else
	  	  	  	 	  	  	{
	  	  	  	 	  	  		Alert alert = driver.switchTo().alert();
	  	  	  	 	  	  		acop=alert.getText();
	  	  	  	 	  	  		alert.accept();
	  	  	  	 	  	  		if(acop.equalsIgnoreCase(input[i-1][13]))
	  	  	  	 	  	  			data.put(""+rc, new Object[] {input[i-1][0],"FleetDetail", input[i-1][1], input[i-1][13], acop, "Pass", s.timestamp(stime) });
	  	  	  	 	  	  		else
	  	  	  	 	  	  			data.put(""+rc, new Object[] {input[i-1][0],"FleetDetail", input[i-1][1], input[i-1][13], acop, "Fail", s.timestamp(stime) });
	  	  	  	 	  	  		i++;
	  	  	  	 	  	  		rc++;
	  	  	  	 	  	  		driver.findElement(By.xpath(".//*[@id='MainContainer_Clear']")).click();
	  	  	  	 	  	  		break;
	  	  	  	 	  	  	}
	  	  		
	  	  				}
	  	  				  	  				driver.findElement(By.xpath(".//*[@id='MainContainer_Clear']")).click();
	  	  				
	  	  				
	  	  			}
	  	  		driver.quit();
	  	  		return data;
	  	  	}
	  	  	catch (Exception e)
	  	  	{
	  	  		e.printStackTrace();
		  	
	  	  	}
	  	  } 
	  	  return data;
	    }
}

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
public class FleetStatus {
	 @Test
	 
	    public Map<String, Object[]> fleetstatus(Map<String, Object[]> data, int rc) throws Exception 
	    {
	    	WebDriver driver;
	  	  	WebElement ele;
	  	  	Support s = new Support();
	  	  	driver = new FirefoxDriver();
	  	    String acop;
	  	    String [][] input = s.readData("D:\\Test Data\\Test Data.xls",2);
	  	  	System.out.println(rc);
	  	  	rc ++;
	  	    FleetstatusHelp sup = new FleetstatusHelp();
	  	    System.out.println(input.length);
	  	    
	  	  	if(s.login(driver, input[0][2], input[0][3]))
	  	  	{
	  	  	 driver.findElement(By.xpath(".//*[@id='FEMenu1_HyperLinkFEMenuFleet']")).click();
	  	  	 driver.findElement(By.xpath(".//*[@id='FEMenu1_HyperLinkFEMenuFleetStatus']")).click();	
	  	     try {
	  	  		 int i =1;
	  	  		  while(i<=input.length){
	  	  			for(;i<=input.length;i++){
	  	  			 long stime=System.currentTimeMillis();
	  	  				if(s.isAlertPresent(driver)){
	  	  				 Alert alert = driver.switchTo().alert();
	  	  				 alert.accept();
	  	  				 }

//***************************Owner search*********************************//    	  	  	 	  	  	
  	  	  	 	  	  	System.out.println(input[i-1][4]);
  	  	  	 	  	  	if(input[i-1][4] != null)
  	  	  	 	  	  	{
  	  	  	 	  	  		try{
  	  	  	 	  	  			driver.findElement(By.xpath(".//*[@id='MainContainer_Clear']")).click();
  	  	  	 	  	  			driver.findElement(By.xpath(".//*[@id='MainContainer_txtOwner']")).sendKeys(input[i-1][4]);
  	  	  	 	  	  			driver.findElement(By.xpath(".//*[@id='MainContainer_Search']")).click();
  	  	  	 	  	  		}
  	  	  	 	  	  		catch (Exception e)
  	  	  	 	  	  		{
  	  	  	 	  	  			data.put(""+rc, new Object[] {input[i-1][0],"FleetStatus", input[i-1][1], input[i-1][10], "Owner not found", "Fail", s.timestamp(stime) });
  	  	  	 	  	  			rc++;
  	  	  	 	  	  			i++;
  	  	  	 	  	  			e.printStackTrace();
  	  	  	 	  	  			driver.findElement(By.xpath(".//*[@id='MainContainer_txtOwner']")).clear();
  	  	  	 	  	  			driver.findElement(By.xpath(".//*[@id='MainContainer_Clear']")).click();
  	  	  	 	  	  			break;
  	  	  	 	  	  		}
  	  					}
//***************************Model search*********************************//  	   	  				
  	  				
  	  	  	 	  	  	if(input[i-1][5] != null){
  	  	  	 	  	  		try
  	  	  	 	  	  		{
  	  	  	 	  	  	       driver.findElement(By.xpath(".//*[@id='MainContainer_Clear']")).click();
  	  	  	 	  	  			Select model = new Select(driver.findElement(By.xpath(".//*[@id='MainContainer_drpSearchModel']")));
  	  	  	 	  	  			model.selectByValue(input[i-1][5]);
  	  	  	 	  	  			System.out.println(input[i-1][5]);
  	  	  	 	  	  	        driver.findElement(By.xpath(".//*[@id='MainContainer_Clear']")).click();
  	  	  	 	  	  		}
  	  	  	 	  	  		catch (Exception e)
  	  	  	 	  	  		{
  	  	  	 	  	  			String aopt="Search Failed.";
  	  	  	 	  	  			if(s.testStatus(input[i-1][13], aopt))
  	  	  	 	  	  				data.put(""+rc, new Object[] {input[i-1][0],"FleetStatus", input[i-1][1], input[i-1][10], aopt, "Pass", s.timestamp(stime) });
  	  	  	 	  	  			else
  	  	  	 	  	  				data.put(""+rc, new Object[] {input[i-1][0],"FleetStatus", input[i-1][1], input[i-1][10], aopt, "Fail", s.timestamp(stime) });
  	  	  	 	  	  			rc++;
  	  	  	 	  	  			e.printStackTrace();
  	  	  	 	  	  			i++;
  	  	  	 	  	  			driver.findElement(By.xpath(".//*[@id='MainContainer_Clear']")).click();
  	  	  	 	  	  			break;
  	  	  	 	  	  		}
  	  	  	 	  	  	}
  	  	  	 	  	  	
//***************************Equipment search*********************************//   
  	  	  	 	  	  	
  	  	  	 	        System.out.println(input[i-1][6]);
  	  	  	 	  	  	if(input[i-1][6] != null)
  	  	  	 	  	  	{
  	  	  	 	  	  		try
  	  	  	 	  	  		{
  	  	  	 	  	         	driver.findElement(By.xpath(".//*[@id='MainContainer_Clear']")).click();
  	  	  	 	  	  			driver.findElement(By.xpath(".//*[@id='MainContainer_txtSearchEquipmentID']")).sendKeys(input[i-1][6]);
  	  	  	 	  	  	        driver.findElement(By.xpath(".//*[@id='MainContainer_Search']")).click();
  	  						}
  	  	  	 	  	  		catch (Exception e)
  	  	  	 	  	  		{
  	  	  	 	  	  			String aopt="Equipment id field not found";
  	  	  	 	  	  			if(s.testStatus(input[i-1][13], aopt))
  	  	  	 	  	  				data.put(""+rc, new Object[] {input[i-1][0],"FleetStatus", input[i-1][1], input[i-1][10], aopt, "Pass", s.timestamp(stime) });
  	  	  	 	  	  			else
  	  	  	 	  	  				data.put(""+rc, new Object[] {input[i-1][0],"FleetStatus", input[i-1][1], input[i-1][10], aopt, "Fail", s.timestamp(stime) });
  	  	  	 	  	  			rc++;
  	  	  	 	  	  			i++;
  	  	  	 	  	  			driver.findElement(By.xpath(".//*[@id='MainContainer_txtSearchEquipmentID']")).clear();
  	  	  	 	  	  			e.printStackTrace();
  	  	  	 	  	  			driver.findElement(By.xpath(".//*[@id='MainContainer_Clear']")).click();
  	  	  	 	  	  			break;
  	  	  	 	  	  		}
  	  	  	 	  	  	}
  	  	  	 	  	  	

//***************************Serial number search *********************************//      	
  	  	  	 	  	  	
  	  	  	 	        System.out.println(input[i-1][7]);
  	  	  	 	  	  	if(input[i-1][7] != null)
  	  	  	 	  	  	{
  	  	  	 	  	  		try
  	  	  	 	  	  		{
  	  	  	 	  	  	        driver.findElement(By.xpath(".//*[@id='MainContainer_Clear']")).click();
  	  	  	 	  	  			driver.findElement(By.xpath(".//*[@id='MainContainer_txtSerialNo']")).sendKeys(input[i-1][7]);
  	  	  	 	  	        	driver.findElement(By.xpath(".//*[@id='MainContainer_Search']")).click();
  	  	  	 	  	  		}
  	  	  	 	  	  		catch (Exception e)
  	  	  	 	  	  		{
	  							data.put(""+rc, new Object[] {input[i-1][0],"FleetStatus", input[i-1][1], input[i-1][10], "Serial no not found", "Fail", s.timestamp(stime) });
	  							rc++;
	  							i++;
	  							e.printStackTrace();
	  							driver.findElement(By.xpath(".//*[@id='MainContainer_Clear']")).click();
	  							break;
  	  	  	 	  	  		}
  	  	  	 	  	  	}
//***************************Print report functionality check *********************************//   	  	  	  	 	  	  	
	  	  	 	  	  		if(input[i-1][8].equalsIgnoreCase("Y"))
	  	  	 	  	  		{
	  	  	 	  	  			driver.findElement(By.xpath(".//*[@id='MainContainer_PrintButton']")).click();
	  	  	 	  	  			s.print();
	  	  	 	  	  		}	
  	  	  	 	  	  		
//***************************Export Excel functionality check *********************************//   	  	  	 	  	  		
  	  	  	 	  	  		if(input[i-1][9].equalsIgnoreCase("Y"))
  	  	  	 	  	  		{
  	  	  	 	  	  			driver.findElement(By.xpath(".//*[@id='MainContainer_ExportButton']")).click();
  	  	  	 	  	  			s.saveAsDialogue();
  	  	  	 	  	  		}
//***************************Column Header search*********************************//  	  	
  	  	  			        System.out.println(input[i-1][11]);
  	  	  					if(input[i-1][11].equalsIgnoreCase("Y")){ 	  					
  	  	  					 int res = sup.fleetstcol(driver , "D:\\Test Data\\Column header.xls");
  	  	  					  if(res==0){
  	  	  					   data.put(""+rc, new Object[] {input[i-1][0],"FleetStatus", input[i-1][1], input[i-1][10], "Header search successful", "Pass",s.timestamp(stime) });
  	  	  					   rc++;
  	  	  					   i++;
  	  	  					            } 
  	  	  					  else {
  	  	  					   data.put(""+rc, new Object[] {input[i-1][0],"FleetStatus", input[i-1][1], input[i-1][10], "Headers doesn't match...", "Fail", s.timestamp(stime) });
  	  	  					   rc++;
  	  	  					   i++;
  	  	  					       }
  	  	  					   break;
  	  	  					                                       }
//***************************clear function check *********************************//       	  	  	 	  	  	
  	  	  	  	 	        System.out.println(input[i-1][12]); 			
  	  	  	  	 	  	  	if(input[i-1][12].equalsIgnoreCase("Y"))
  	  	  	  	 	  	  	{
  	  	  	  	 	  	  		driver.findElement(By.xpath(".//*[@id='MainContainer_Clear']")).click();
  	  	  	  	 	  	  		System.out.println(driver.findElement(By.xpath(".//*[@id='MainContainer_txtSerialNo']")).getText());
  	  	  	  	 	  	  		System.out.println(driver.findElement(By.xpath(".//*[@id='MainContainer_txtOwner']")).getText());
  	  	  	  	 	  	  		System.out.println(driver.findElement(By.xpath(".//*[@id='MainContainer_txtSearchEquipmentID']")).getText());
  	  	  	  	 	  	  		
  	  	  	  	 	  	  		if(driver.findElement(By.xpath(".//*[@id='MainContainer_txtSerialNo']")).getText().isEmpty() && 
  	  	  	  	 	  	  		   driver.findElement(By.xpath(".//*[@id='MainContainer_txtOwner']")).getText().isEmpty() && 
  	  	  	  	 	  	  		   driver.findElement(By.xpath(".//*[@id='MainContainer_txtSearchEquipmentID']")).getText().isEmpty())
  	  	  	  	 	  	  		{
  	  	  	  	 	  	  			data.put(""+rc, new Object[] {input[i-1][0],"FleetStatus", input[i-1][1], input[i-1][10], "Clear function successful", "Pass", s.timestamp(stime) });
  	  	  	  	 	  	  			rc++;
  	  	  	  	 	  	  			i++;
  	  	  	  	 	  	  			break;
  	  	  	  	 	  	  		}
  	  	  	  	 	  	  		else
  	  	  	  	 	  	  		{
  	  	  	  	 	  	  			data.put(""+rc, new Object[] {input[i-1][0],"FleetStatus", input[i-1][1], input[i-1][10], "Clear function Failed", "Fail", s.timestamp(stime) });
  	  	  	  	 	  	  			rc++;
  	  	  	  	 	  	  			i++;
  	  	  	  	 	  	  			break;
  	  	  	  	 	  	  		}
  	  	  	  	 	  	  	}
  	  	  	  	 	 driver.findElement(By.xpath(".//*[@id='MainContainer_Search']")).click();
  	  	  	  	 	 boolean count_check=false;	  	
  	  	  	  	 	 if(input[i-1][13].equalsIgnoreCase("Y"))
  	 	 	  	  		{
  	  	  	  	 	  	  	if(!s.isAlertPresent(driver))
  	  	  	  	 	  	  	{
  	  	  	  	 	  	  	try
  	  	  	  	 	  	  	{
  	  	  	  	 	  	  		if(driver.findElement(By.xpath(".//*[@id='MainContainer_lbl_NoRecords']")).isDisplayed())
  	  	  	  	 	  	  		{
  	  	  	  	 	  	  			String aopt="Search Failed.";
  	  	  	  	 	  	  			if(s.testStatus(input[i-1][11], aopt))
  	  	  	  	 	  	  				data.put(""+rc, new Object[] {input[i-1][0],"FleetStatus", input[i-1][1], input[i-1][10], aopt, "Pass", s.timestamp(stime) });
  	  	  	  	 	  	  			else
  	  	  	  	 	  	  				data.put(""+rc, new Object[] {input[i-1][0],"FleetStatus", input[i-1][1], input[i-1][10], aopt, "Fail", s.timestamp(stime) });
  	  	  	  	 	  	  			rc++;
  	  	  	  	 	  	  		}
  	  	  	  	 	  	  	}
  	  	  	  	 	catch (Exception E)
	  	  	  	 	  	  	{
	  	  	  	 	  	  		
	  	  	  	 	  	  		boolean count=false;
	  	  	  	 	  	  		if(!input[i-1][5].equalsIgnoreCase(""))
	  	  	  	 	  	  			count_check = sup.countChecker(driver, input[i-1][5], "Model");
	  	  	  	 	  	  		else if(!input[i-1][6].equalsIgnoreCase(""))
	  	  	  	 	  	  			count_check = sup.countChecker(driver, input[i-1][6], "EquipmentID");
	  	  	  	 	  	  		else if(!input[i-1][4].equalsIgnoreCase(""))
	  	  	  	 	  	  			count_check =sup.countChecker(driver, input[i-1][4], "Owner");
	  	  	  	 	  	  		else if(!input[i-1][7].equalsIgnoreCase(""))
	  	  	  	 	  	  			count_check =sup.countChecker(driver, input[i-1][7], "Serial No");
	  	  	  	 	  	  		else
	  	  	  	 	  	  		{
	  	  	  	 	  	  			count = sup.countChecker(driver, "", "");
	  	  	  	 	  	  			if(count)
	  	  	  	 	  	  			{

		  	  	 	  	  				data.put(""+rc, new Object[] {input[i-1][0],"FleetStatus", input[i-1][1], input[i-1][10], "Count matches", "Pass", s.timestamp(stime) });
		  	  	 	  	  				rc ++;
		  	  	 	  	  			}
		  	  	 	  	  			else
		  	  	 	  	  			{
		  	  	 	  	  				data.put(""+rc, new Object[] {input[i-1][0],"FleetStatus", input[i-1][1], input[i-1][10], "Count doesn't match...", "Fail", s.timestamp(stime) });
		  	  	 	  	  				rc ++;
		  	  	 	  	  			}
		  	  	 	  	  		 }
		  	  	 	  	  		}	
		  	  	 	  	  		if(count_check)
		  	  	 	  	  		{
		  	  	 	  	  			data.put(""+rc, new Object[] {input[i-1][0],"FleetStatus", input[i-1][1], input[i-1][10], "Search Successful...", "Pass", s.timestamp(stime) });
		  	  	 	  	  			rc ++;
		  	  	 	  	  		}
		  	  	 	  	  		else
		  	  	 	  	  		{
		  	  	 	  	  			data.put(""+rc, new Object[] {input[i-1][0],"FleetStatus", input[i-1][1], input[i-1][10], "Count doesn't match...", "Fail", s.timestamp(stime) });
		  	  	 	  	  			rc ++;
		  	  	 	  	  		}
		  	  	 	  	  	}
		  	  	 	  	  		  	  	 	  	  	
		  	  	 	  	  	else
		  	  	 	  	  	{
		  	  	 	  	  		Alert alert = driver.switchTo().alert();
		  	  	 	  	  		acop=alert.getText();
		  	  	 	  	  		alert.accept();
		  	  	 	  	  		if(acop.equalsIgnoreCase(input[i-1][13]))
		  	  	 	  	  			data.put(""+rc, new Object[] {input[i-1][0],"FleetStatus", input[i-1][1], input[i-1][10], acop, "Pass", s.timestamp(stime) });
		  	  	 	  	  		else
		  	  	 	  	  			data.put(""+rc, new Object[] {input[i-1][0],"FleetStatus", input[i-1][1], input[i-1][10], acop, "Fail", s.timestamp(stime) });
		  	  	 	  	  		i++;
		  	  	 	  	  		rc++;
		  	  	 	  	  		driver.findElement(By.xpath(".//*[@id='MainContainer_Clear']")).click();
		  	  	 	  	  		break;
		  	  	 	  	  	}
	  	  	  	 		return data;
		  				}
	  	  	  
	  	  			}
	  	  		  }
	  	  	  }  
	  	  	catch (Exception e)
	  	  	{
	  	  		e.printStackTrace();
		  	
	  	  	}
	  	  	}
		return data;
	  
	    }
	}
	  	 

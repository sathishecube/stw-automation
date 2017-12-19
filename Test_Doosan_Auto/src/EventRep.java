import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;


public class EventRep {
	@Test
	 
    public Map<String, Object[]> EventReport (Map<String, Object[]> data, int rc) throws Exception 
    {
    	WebDriver driver;
	  	  WebElement ele;
    	Support s = new Support();
	  	  driver = new FirefoxDriver();
		  String acop;
	  	  String [][] input = s.readData("D:\\Test Data\\Test Data.xls",11);
	  	  System.out.println(rc);
	  	  rc ++;
	  	  System.out.println(input.length);
	  	  if(s.login(driver, input[0][2], input[0][3]))
	  	  	{
	  		driver.findElement(By.xpath(".//*[@id='FEMenu1_HyperLinkFEMenuEventReport']")).click();
          }
	  	  
	  	for(int i=1;i<=input.length;i++){
 			 long stime=System.currentTimeMillis();
 			 if(input[i-1][12].equalsIgnoreCase("Y"))
 			 {
 				 driver.findElement(By.xpath(".//*[@id='MainContainer_btn_Event']")).click();
 			 }
 			 if(input[i-1][13].equalsIgnoreCase("Y"))
 			 {
 				 driver.findElement(By.xpath(".//*[@id='MainContainer_btn_Warning']")).click();
 			 }
 			 if(input[i-1][14].equalsIgnoreCase("Y"))
 			 {
 				 driver.findElement(By.xpath(".//*[@id='MainContainer_btn_Fault']")).click();
 			 }
 			 if(input[i-1][15].equalsIgnoreCase("Y"))
 			 {
 				 driver.findElement(By.xpath(".//*[@id='MainContainer_btn_Fence']")).click();
 			 }

/************************Start date Selection*******************************/ 			 
	  	if(input[i-1][4]!="")
	  	  		{
	  	  			ele=driver.findElement(By.xpath(".//*[@id='MainContainer_StartDate']"));
	  	  			String date = input[i-1][4].substring(0,2);
	  	  			if(date.startsWith("0"))
	  	  			{
	  	  				date=date.substring(1);
	  	  			}
	  	  			System.out.println(date);
	  	  			String month = input[i-1][4].substring(3,6);
	  	  		    String year = input[i-1][4].substring(7,11);
	  	  			System.out.println(date+" "+month+" "+year);
	  	  			s.dateSelector(driver,ele,month,year,date);
	  	  		
	  	  		}
	  	  		else
	  	  		{
	  	  			System.out.println("No Start date provided...");
	  	  		}
/************************End date Selection*******************************/ 	  	
	  	if(input[i-1][5]!="")
	  		{
	  			ele=driver.findElement(By.xpath(".//*[@id='MainContainer_EndDate']"));
	  			String date = input[i-1][5].substring(0,2);
	  			if(date.startsWith("0"))
	  			{
	  				date=date.substring(1);
	  			}
	  			System.out.println(date);
	  			String month = input[i-1][5].substring(3,6);
	  		    String year = input[i-1][5].substring(7,11);
	  			System.out.println(date+" "+month+" "+year);
	  			s.dateSelector(driver,ele,month,year,date);
	  		
	  		}
	  		else
	  		{
	  			System.out.println("No End date provided...");
	  		}
	  	
/***************************Model Search**************************/
	  	 if(input[i-1][6] != null)
	 	  	  	{
	 	  	  			Select model = new Select(driver.findElement(By.xpath(".//*[@id='MainContainer_drpSearchModel']")));
	 	  	  			model.selectByValue(input[i-1][6]);
	 	  	  			System.out.println(input[i-1][6]);
	 	  	  	}

/*************************Equipment Search**************************/
	  	 if(input[i-1][7] != null)
	  	 {
	  		 System.out.println(input[i-1][7]);
	  		 String T = input[i-1][7];
	  		 if(T.endsWith(".0"))
	  		 {
	  			String[] T1=T.split("\\.");
	  			System.out.println("inside loop");
	  			String T2 = T1[0];
	  			System.out.println(T2);
	  			driver.findElement(By.xpath(".//*[@id='MainContainer_Clear']")).click();
	  			driver.findElement(By.xpath(".//*[@id='MainContainer_txtEquipmentID']")).sendKeys(T2);
	  			
	  		 }
	  		 else{
	  			 System.out.println(T);
	  			 driver.findElement(By.xpath(".//*[@id='MainContainer_Clear']")).click();
	  			 driver.findElement(By.xpath(".//*[@id='MainContainer_txtEquipmentID']")).sendKeys(T);
	  		 }
	  		 }
/**********************************Serial Number************************/
	  	 if(input[i-1][8] != null)
	  	 {
	  		 System.out.println(input[i-1][8]);
	  		 String T = input[i-1][8];
	  		 if(T.endsWith(".0"))
	  		 {
	  			String[] T1=T.split("\\.");
	  			System.out.println("inside loop");
	  			String T2 = T1[0];
	  			System.out.println(T2);
	  			driver.findElement(By.xpath(".//*[@id='MainContainer_txtSerialNo']")).sendKeys(T2);
	  			
	  		 }
	  		 else{
	  			 System.out.println(T);
	  			 driver.findElement(By.xpath(".//*[@id='MainContainer_txtSerialNo']")).sendKeys(T);
	  		 }
	  		 }
/**************************************Refresh Report*****************/
	  	if(input[i-1][9].equalsIgnoreCase("Y"))
	  	 {
	  		driver.findElement(By.xpath(".//*[@id='MainContainer_RefreshButton']")).click();
	  		driver.findElement(By.xpath(".//*[@id='MainContainer_Clear']")).click();
	  		 
	  		 }
/*************************************Export to Excel*****************/
	  	if(input[i-1][10].equalsIgnoreCase("Y"))
	  	  		{
	  	  			driver.findElement(By.xpath(".//*[@id='MainContainer_ExportButton']")).click();
	  	  			s.saveAsDialogue();
	  	  		}
/*************************************Print Button*****************/	
	  	  		if(input[i-1][11].equalsIgnoreCase("Y"))
	  	  		{
	  	  			driver.findElement(By.xpath(".//*[@id='MainContainer_PrintButton']")).click();
	  	  			s.print();
	  	  		}
	  	
	  	
	  	}
	  	driver.quit();
		return data;
    }
  	  	
}

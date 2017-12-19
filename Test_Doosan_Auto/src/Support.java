

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class Support {
	 /*This method will login into the Doosan Site */
	 public boolean login(WebDriver driver, String uname, String pwd)
		{
		 boolean result=false;
		 System.out.println("Inside the login method......");
		 driver.get("http://doosantest.tms-orbcomm.com/");
		 driver.findElement(By.xpath(".//*[@id='MainContainer_TextBoxUserID']")).clear();
		 driver.findElement(By.xpath(".//*[@id='MainContainer_TextBoxUserID']")).sendKeys(uname);
		 driver.findElement(By.xpath(".//*[@id='MainContainer_TextBoxPassword']")).clear();
		 driver.findElement(By.xpath(".//*[@id='MainContainer_TextBoxPassword']")).sendKeys(pwd);
		 driver.findElement(By.xpath(".//*[@id='MainContainer_LoginButton']")).click();
		  if(isAlertPresent(driver))
		  	{
	  	  	  try {
	  	  			Alert alert = driver.switchTo().alert();
	  	  			System.out.println(alert.getText());
	  	  			alert.accept();
	  	  			System.out.println("Login Unsuccessful...\nUser Name : "+uname+"\nPassword :"+pwd);
	  	  			result = false;
	  	  		  } catch (Exception e) {
	  	  				System.out.println(e);
	  	  			                      }
		  		    }else{
		  				result = true;
		  		         }
				     return result;
		       }
	 
	 /* This method finds and returns if there is an alert present. */
	 public boolean isAlertPresent(WebDriver driver)
		{ 
		 try 
		    { 
		        driver.switchTo().alert(); 
		        return true; 
		    }  
		    catch (NoAlertPresentException Ex) 
		    { 
		        return false; 
		    }   
		} 
	 
	/* This method is used to write the output result status to a Excel. */
	 public void writeoutput(Map<String, Object[]> data, String str)
		{
			//Creating a Workbook and Sheet to write output
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet osheet = workbook.createSheet("Test Status");
			
			Set<String> keyset = data.keySet();
			int rownum = 0;
			
			//Copying the data from the Map to the sheet.
			for (String key : keyset)
			{
				Row orow = osheet.createRow(rownum++);
				Object [] objArr = data.get(key);
				int cellnum = 0;
				for (Object obj : objArr)
				{
					Cell cell = orow.createCell(cellnum);
					cellnum++;
					cell.setCellValue((String)obj);
				}
			}
			try
			{
				FileOutputStream out = new FileOutputStream(new File("D:\\Test Output\\"+str+".xls"));
				workbook.write(out);
				out.close();
				System.out.println("output written successfully...");
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	 
	/*This method sets any date to that has been passed to the web element.*/
	 public void dateSelector(WebDriver driver, WebElement ele,String mon, String yr, String dt)
		{
			int flag=0;
			ele.click();
			Select year = new Select(driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select[2]")));   
			year.selectByVisibleText(yr);
			Select month = new Select(driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div/div/select[1]")));   
			month.selectByVisibleText(mon);
				for(int i = 1; i<=5; i++)
				{
				for(int j = 1;j<=7; j++)
				{
					System.out.println(i+" "+j);
					if(i+j == 2)
					{
						if(driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/table/tbody/tr["+i+"]/td["+j+"]")).getText().equalsIgnoreCase(dt))
						{
							driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/table/tbody/tr["+i+"]/td["+j+"]")).click();
							System.out.println("Got date at first row");
							flag = 1;
							break;
						}
					}
					else 
					{
						if(driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/table/tbody/tr["+i+"]/td["+j+"]")).getAttribute("class").equalsIgnoreCase(" ui-datepicker-unselectable ui-state-disabled "))
						{
							System.out.println("unselectable");
							break;
						}else {
							if(driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/table/tbody/tr["+i+"]/td["+j+"]")).getText().equalsIgnoreCase(dt)){
								System.out.println("Got date here at last block");
								driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/table/tbody/tr["+i+"]/td["+j+"]/a")).click();
								flag = 1;
								break;
							}
						}
					}
				}
				if(flag == 1)
					break;
			}
		}
	 
	/* This method is used to read input data from an Excel. */
	 public String[][] readData (String fname, int pnum) throws Exception
		{
			InputStream input = new BufferedInputStream(new FileInputStream(fname));
			POIFSFileSystem fs = new POIFSFileSystem( input );
	        HSSFWorkbook wb = new HSSFWorkbook(fs);
	        HSSFSheet sheet = wb.getSheetAt(pnum);
	        Row row;
	        Cell c;
	        //System.out.println(sheet.getLastRowNum());
	        //System.out.println(sheet.getRow(0).getLastCellNum());
	        String[][] d1 =new String[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
	            
	        for(int i=1;i<=sheet.getLastRowNum();i++){
	        	row = sheet.getRow(i);
	        	//System.out.println("i "+i);
	        	for(int j=0;j<sheet.getRow(0).getLastCellNum();)
	        	{
	        		c = row.getCell(j);
	        		//System.out.println(i+":"+j);
	        		//System.out.println(c.toString());
				   d1[i-1][j]=c.toString();
	     		j++;
	     	  }
	     	
	     	
	       }
	        //System.out.println("Finished reading");
	     return d1;
		}
	 
	 
	/* This method is used to save a file using save as dialogue box. */
	 public void saveAsDialogue() throws Exception
		{
			Robot robot = new Robot();
			robot.delay(1000);
			robot.keyPress(KeyEvent.VK_DOWN);
			robot.keyRelease(KeyEvent.VK_DOWN);
			robot.mouseMove(450, 400);
			robot.delay(1000);
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.delay(1000);
			robot.keyPress(KeyEvent.VK_ALT);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			robot.keyPress(KeyEvent.VK_S);
			robot.keyRelease(KeyEvent.VK_ALT);
			robot.delay(1000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.delay(5000);
		}
	 
	 /* This method is used to compare expected with Actuel */
	 public boolean testStatus(String eopt, String aopt)
		{
			System.out.println(eopt+":"+aopt);
			if(eopt.equalsIgnoreCase(aopt))
			{
				System.out.println("Pass");
				return true;
			}
			else
				return false;
		}
	 
	/* This method is used to get the exceution time of each function */ 
	 public String timestamp(long Stime)
		{
			long Etime = System.currentTimeMillis();
			long Ttime = Etime - Stime;
			long Exetime =Ttime /1000 ;
			return Long.toString(Exetime);
		}
	 
	 /* This method is used to print report function */
	 public void print() throws Exception
		{
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ENTER);
		}
		

	 /* This method is used to validate the date function (i.e. End date should be greater than start date) */
		public Boolean dateValidate (WebDriver driver)
		{ 
			Boolean r =false;
			if(isAlertPresent(driver))
			{ 
		 	  	try 
		 	  	{
		 	  		Alert alert = driver.switchTo().alert();
		 	  		String acop=alert.getText();
			        alert.accept();
			        //System.out.println(acop);
			        if(acop.equalsIgnoreCase("End date should be greater than start date!"))
			        {
			        	r = true;
			        }else
			        {
			        	r= false;
			        }
			    } 
		 	  	
		 	  	catch (Exception e) 
		 	  	{
			        System.out.println(e);
			    }
		 	  }
			return r;
			}
	 
}

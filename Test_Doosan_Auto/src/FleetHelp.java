import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class FleetHelp {
	public int headerCount(WebDriver driver, String fname) throws Exception
	{
		InputStream input = new BufferedInputStream(new FileInputStream(fname));
		POIFSFileSystem fs = new POIFSFileSystem( input );
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFSheet sheet = wb.getSheetAt(0);
		Row row;
		Cell cell;
		String s;
		int flag=0;
		WebElement w;
		
		// Column header Comparison 
		row =sheet.getRow(1);
		cell = row.getCell(1);
		s=cell.toString();
		String[] s1 = s.split("//");
		System.out.println(s1.length);
		try
		{
		for (int i=1;i<=s1.length;i++)
		{
      		w= driver.findElement(By.xpath(".//*[@id='tblFleetDetailHeaderCopy']/th["+i+"]/div/span"));
      		 if(w.isEnabled())
    	 {
    		while(!w.isDisplayed()) 
    		{
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", w);
			}
    	
           
    	 if(!(s1[i-1].equalsIgnoreCase(w.getText())))
    	 {
    			 flag=1;
    			 }
    	 else
    	 {
    		 System.out.println("pass");
    	 }
    	 System.out.println(flag);
    	}
		}
		}catch(Exception e){
			System.out.println("Data not found...");
			flag=1;
		}
  
	return flag;

   }

	public boolean ADTsearch(WebDriver driver, int r) throws Exception
	{
		WebElement w1;
		int flag=1;
        if(r==2)
        {
        	for (int i=36;i<=41;i++)
        	{
        		w1 = driver.findElement(By.xpath(".//*[@id='tblFleetDetail']/tbody/tr["+r+"]/td["+i+"]/div"));
        	      		
        		if(w1.isEnabled())
        		{
        			while(!w1.isDisplayed()) 
        			{
        				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", w1);
        				String w = w1.getText();
        				if(w.equalsIgnoreCase("N/A"))
        				{
        			
        					System.out.println(w);
        				}
        				else
        				{
        					flag =0;
	    					System.out.println(w);
        				}
        			}
	    		
        		}
        		
	    		}
        		if(flag == 1)
        			return true;
        }
        else
        {
        	
        	for (int i=36;i<=41;i++)
    	    {
    	    	w1 = driver.findElement(By.xpath(".//*[@id='tblFleetDetail']/tbody/tr["+r+"]/td["+i+"]"));
    	    	
    	    	if(w1.isEnabled())
    	    	{
    	    		while(!w1.isDisplayed()) 
    	    		{
    	    			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", w1);
    	    			String w = w1.getText();
    	    			
    	    			if(w.equalsIgnoreCase("N/A"))
    	    			{
    	    		
    	    				System.out.println(w);
    	    			}
    	    			else
    	    			{
    	    				flag =0;
    	    				System.out.println(w);
    	    			}
    	    		}
    	    		
    	    	}
    	    	
    	    }
        	if(flag == 1)
    			return true;
        }
		return false;
		
	}
	
	public boolean countChecker(WebDriver driver, String search, String column)
	{
		int last=11;
		int next=1;
		int totpages;
		int pages;
		//boolean adt_res=false;
		WebElement ele;
		int col=0;
		if(search.equalsIgnoreCase(""))
			System.out.println("Blind Search done...");
		else
			System.out.println("Search string is :"+search);
		if(column.equalsIgnoreCase("Category"))
		{
			System.out.println("Total no of rows : "+driver.findElement(By.xpath(".//*[@id='MainContainer_dpLvRpt_ctl00_lblTotalItems']")).getText());
			int count=0;
			
			totpages=Integer.parseInt(driver.findElement(By.xpath(".//*[@id='MainContainer_dpLvRpt_ctl00_lblTotalPages']")).getText());
			pages=Integer.parseInt(driver.findElement(By.xpath(".//*[@id='MainContainer_dpLvRpt_ctl00_lblCurrentPage']")).getText());
			System.out.println(next+":"+last);
			while(next<last)
			{
				int flag=0;
				for (int i=2;i<=11;i++)
				{
	  		
					if(i==2)
					{
						ele=driver.findElement(By.xpath(".//*[@id='tblFleetDetail']/tbody/tr["+i+"]/td[6]/div"));
					
						while(!ele.isDisplayed()) {
							((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
							
						}
						if(ele.getText().equalsIgnoreCase(search))
						{
							System.out.println(driver.findElement(By.xpath(".//*[@id='tblFleetDetailFreeze']/tbody/tr["+i+"]/td[4]/div")).getText()+"	-	"+driver.findElement(By.xpath(".//*[@id='tblFleetDetail']/tbody/tr["+i+"]/td[6]/div")).getText());
	  				try 
	  				{
	  					if(!search.equalsIgnoreCase("ADT"))
	  					{
	  						if(!ADTsearch(driver,i))
	  							System.out.println("N/A present... Failed...");
	  						  					}
	  				}catch (Exception e)
	  				{
	  					e.printStackTrace();
	  				}
	  			}
	  			count++;
	  		}
	  		
	  		else
	  		{
	  		try
	  		{
	  			
 				ele=driver.findElement(By.xpath(".//*[@id='tblFleetDetail']/tbody/tr["+i+"]/td[6]"));
 				while(!ele.isDisplayed()) {
	  				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
	  			}
 				
	  			if(ele.getText().equalsIgnoreCase(search))
 				{
	  				System.out.println(driver.findElement(By.xpath(".//*[@id='tblFleetDetailFreeze']/tbody/tr["+i+"]/td[4]")).getText()+"	-	"+driver.findElement(By.xpath(".//*[@id='tblFleetDetail']/tbody/tr["+i+"]/td[6]")).getText());
	  				count++;
	  				try 
	  				{
	  					if(!search.equalsIgnoreCase("ADT"))
	  					{
	  						if(!ADTsearch(driver,i))
	  							System.out.println("N/A present... Failed...");
	  					}
	  				}
	  				catch (Exception e)
	  				{
	  							e.printStackTrace();
	  				}
 				}
	  			
	  		}
	  		catch(Exception e)
	  		{
	  			System.out.println("End of Test results...");
	  			e.printStackTrace();
	  				flag=1;
	  				break;
	  		}
	  		}
	  		System.out.println(count);
	  	}
	  	if(flag==1)
	  			break;
	  	if(driver.findElement(By.xpath(".//*[@id='MainContainer_dpLvRpt']/a["+next+"]")).isEnabled())
	  		driver.findElement(By.xpath(".//*[@id='MainContainer_dpLvRpt']/a["+next+"]")).click();
	  	pages++;
		next ++;
	  	System.out.println(next);
	  	if(next == last)
	  	{
	  		System.out.println(next+":"+last);
	  		System.out.println("One set done");
	  		next = 2;
	  		last = 12;
	  		//i=2;
	  		System.out.println(next+":"+last);
	  	}
	  		
	  	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  		
	  	}
	  		System.out.println(count);
	  		if(count==Integer.parseInt(driver.findElement(By.xpath(".//*[@id='MainContainer_dpLvRpt_ctl00_lblTotalItems']")).getText()))
	  		{
	  			return true;
	  		}
	  		else
	  		{
	  			return false;
	  		}
		}else{
			int count=0;
			if(column.equalsIgnoreCase("Owner"))
				col=1;
			else if(column.equalsIgnoreCase("Model"))
				col=3;
			else if (column.equalsIgnoreCase("EquipmentID"))
				col=4;
			else if(column.equalsIgnoreCase("Serial No"))
				col=5;
			else if(column.equalsIgnoreCase(""))
				col=0;
			if(col!=0)
			{
				System.out.println("Total no of rows : "+driver.findElement(By.xpath(".//*[@id='MainContainer_dpLvRpt_ctl00_lblTotalItems']")).getText());
			  	totpages=Integer.parseInt(driver.findElement(By.xpath(".//*[@id='MainContainer_dpLvRpt_ctl00_lblTotalPages']")).getText());
			
			  	pages=Integer.parseInt(driver.findElement(By.xpath(".//*[@id='MainContainer_dpLvRpt_ctl00_lblCurrentPage']")).getText());
			  	System.out.println(next+":"+last);
			  	while(next<last)
			  	{
			  	int flag=0;
			  	for (int i=2;i<=11;i++)
			  	{
			  		
			  		if(i==2)
			  		{
			  			ele=driver.findElement(By.xpath(".//*[@id='tblFleetDetail']/tbody/tr["+i+"]/td["+col+"]/div"));
			  											
			  			while(!ele.isDisplayed()) {
			  				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
			  			}
			  			if(ele.getText().equalsIgnoreCase(search))
			  			{
			  				System.out.println(driver.findElement(By.xpath(".//*[@id='tblFleetDetailFreeze']/tbody/tr["+i+"]/td[4]/div")).getText()+"	-	"+driver.findElement(By.xpath(".//*[@id='tblFleetDetailFreeze']/tbody/tr["+i+"]/td["+col+"]/div")).getText());
			  					  				count++;
			  			}
			  		}
			  		else
			  		{
			  		try
			  		{
			  			while(!driver.findElement(By.xpath(".//*[@id='tblFleetDetail']/tbody/tr["+i+"]/td["+col+"]")).isDisplayed()) {
		 					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(".//*[@id='tblFleetDetailFreeze']/tbody/tr["+i+"]/td["+col+"]")));
		 				}
			  			if(driver.findElement(By.xpath(".//*[@id='tblFleetDetail']/tbody/tr["+i+"]/td["+col+"]")).getText().equalsIgnoreCase(search))
		 				{
		 					System.out.println(driver.findElement(By.xpath(".//*[@id='tblFleetDetailFreeze']/tbody/tr["+i+"]/td[4]")).getText()+"	-	"+driver.findElement(By.xpath(".//*[@id='tblFleetDetailFreeze']/tbody/tr["+i+"]/td["+col+"]")).getText());
		 								count++;
		 				}
			  			}catch(Exception e)
			  			{
			  				System.out.println("End of Test results...");
			  				e.printStackTrace();
			  				flag=1;
			  				break;
			  			}
			  		}
			  		System.out.println(count);
			  		}if(flag==1)
			  			break;
			  		if(driver.findElement(By.xpath(".//*[@id='MainContainer_dpLvRpt']/a["+next+"]")).isEnabled())
			  			driver.findElement(By.xpath(".//*[@id='MainContainer_dpLvRpt']/a["+next+"]")).click();
			  		pages++;
			  		System.out.println("Before increment "+next);
					next ++;
					System.out.println("After increment "+next);
			  		if(next == last)
			  		{
			  			System.out.println(next+":"+last);
			  			System.out.println("One set done");
			  			next = 2;
			  			last = 12;
			  				  			System.out.println(next+":"+last);
			  		}
			  		
			  		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			  		
			  		}
			}
			else
			{
				System.out.println("Total no of rows : "+driver.findElement(By.xpath(".//*[@id='MainContainer_dpLvRpt_ctl00_lblTotalItems']")).getText());
			totpages=Integer.parseInt(driver.findElement(By.xpath(".//*[@id='MainContainer_dpLvRpt_ctl00_lblTotalPages']")).getText());
			  	
			  pages=Integer.parseInt(driver.findElement(By.xpath(".//*[@id='MainContainer_dpLvRpt_ctl00_lblCurrentPage']")).getText());
			  	System.out.println(next+":"+last);
			  	while(next<last)
			  	{
			  		System.out.println(pages);
			  		for(int i=2;i<=11;i++)
			  		{
			  			try
			  			{
			  			if(driver.findElement(By.xpath(".//*[@id='tblFleetDetailFreeze']/tbody/tr["+i+"]/td[1]")).isEnabled())
			  				count++;
			  			}catch(Exception e){
			  				System.out.println("Element list complete...");
			  				break;
			  			}
			  			System.out.println(count);
			  		}
			  		if(driver.findElement(By.xpath(".//*[@id='MainContainer_dpLvRpt']/a["+next+"]")).isEnabled())
			  			driver.findElement(By.xpath(".//*[@id='MainContainer_dpLvRpt']/a["+next+"]")).click();
			  		pages++;
			  		System.out.println("Before increment "+next);
					next ++;
					System.out.println("After increment "+next);
			  		if(next == last)
			  		{
			  			System.out.println(next+":"+last);
			  			System.out.println("One set done");
			  			next = 2;
			  			last = 12;
			  
			  			System.out.println(next+":"+last);
			  		}
			  		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			  	}
			}
			
		  		System.out.println(count);
		  		if(count==Integer.parseInt(driver.findElement(By.xpath(".//*[@id='MainContainer_dpLvRpt_ctl00_lblTotalItems']")).getText()))
		  		{
		  			return true;
		  		}
		  		else
		  		{
		  			return false;
		  		}
		}
	}
}

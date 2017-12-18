import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;


public class login {
	@Test 
	public Map<String, Object[]> exec(Map<String, Object[]> data, int rc) throws Exception 
	{
		//Creating a Firefox WebDriver
		WebDriver driver;
		driver = new FirefoxDriver(); 
	  	System.out.println("Browser openning....");
	  	
		//Instantiating the support class
		Support s = new Support();
		System.out.println(rc);
		

	    String [][] inputData = s.readData("D:\\Test Data\\Test Data.xls",1);
	    
	   	data.put(""+rc, new Object[] {"TC No", "Module", "Description", "Expected Result", "Actual Result", "Status", "Execution Time(Sec.)"});
	     rc ++;
	        
	    //Input data values
		
		String tc;
		String tcdesc;
		String pw;
		String un;
		String lang;
		String tz;
		String eopt;
		String acop="-";
		
		//Executing the test case
		for (int i=0;i<inputData.length;i++)
		{
			long stime=System.currentTimeMillis();
			tc = inputData[i][0];
			tcdesc = inputData[i][1];	
			un = inputData[i][2];	
			pw = inputData[i][3];
			lang = inputData[i][4];
			tz = inputData[i][5];	
			eopt = inputData[i][6];	
			System.out.println(tc+" "+tcdesc+" "+un+" "+pw+" "+lang+" "+tz+" "+eopt);		
			driver.get("http://doosantest.tms-orbcomm.com/");
			driver.findElement(By.xpath(".//*[@id='MainContainer_TextBoxUserID']")).clear();
			driver.findElement(By.xpath(".//*[@id='MainContainer_TextBoxUserID']")).sendKeys(un);
			driver.findElement(By.xpath(".//*[@id='MainContainer_TextBoxPassword']")).clear();
			driver.findElement(By.xpath(".//*[@id='MainContainer_TextBoxPassword']")).sendKeys(pw);
			Select period = new Select(driver.findElement(By.xpath(".//*[@id='MainContainer_DropDownListLanguagePreference']")));
			period.selectByVisibleText(lang);
			Select period1 = new Select(driver.findElement(By.xpath(".//*[@id='DropDownListGMTDifference']")));
			period1.selectByVisibleText(tz);
			driver.findElement(By.xpath(".//*[@id='MainContainer_LoginButton']")).click();		
			if(s.isAlertPresent(driver)){
				try {
			        Alert alert = driver.switchTo().alert();
			        acop=alert.getText();
			        alert.accept();
			        System.out.println("Login Unsuccessful...\nUser Name : "+un+"\nPassword :"+pw);
			        if(acop.equalsIgnoreCase(eopt)){
			        	data.put(""+rc, new Object[] {tc, "Login", tcdesc, eopt, acop, "Pass", s.timestamp(stime)});
			        }else{
			        	data.put(""+rc, new Object[] {tc, "Login",  tcdesc, eopt, acop, "Fail", s.timestamp(stime)});
			        }
			        rc++;
			    } catch (Exception e) {
			        System.out.println(e);
			    }
			}else{
				System.out.println("Login Successful...\nUser Name : "+un+"\nPassword :"+pw);
				driver.findElement(By.xpath(".//*[@id='FEMenu1_HyperLinkFEMenuLogout']")).click();
				acop = "Login Successful";
				 //if(acop.equalsIgnoreCase(eopt) && (tc.equals("TC1")||tc.equals("TC2")))
				if(s.testStatus(acop, eopt))
				  {
					  
						  data.put(""+rc, new Object[] {tc, "Login", tcdesc, eopt, acop, "Pass", s.timestamp(stime) });
					  
			      }
				  else
				  {
			        	data.put(""+rc, new Object[] {tc, "Login", tcdesc, eopt, acop, "Fail", s.timestamp(stime) });
			      }
			       rc++;
		        rc++;
				}
			
			}
			
			driver.quit();
			System.out.println(rc);
			return data;
		}
}

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class companysearch {

	public static void main(String[] args) {
		
		
		try
		{

			System.setProperty("webdriver.gecko.driver", "\\\\AMXSERVER\\amx-share\\STW_QA\\Jar files\\geckodriver.exe");
	        WebDriver driver = new FirefoxDriver();
	        driver.get("http://fleetedgetest.tms-orbcomm.com");
	      //*[@id="ContentPH_TextBoxUserID"]
	        driver.findElement(By.xpath("//*[@id='ContentPH_TextBoxUserID']")).sendKeys("sathishb");
	        driver.findElement(By.xpath("//*[@id='ContentPH_TextBoxPassword']")).sendKeys("verizon11!");
	        driver.findElement(By.xpath("//*[@id='ContentPH_LoginButton']")).click();
	        
	        WebElement myDynamicElement = (new WebDriverWait(driver, 100000))
	        		  .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='lblMenuPath2']")));
	        
	        while (driver.findElement(By.xpath("//*[@id='DivOverlayChild']")).isDisplayed())
	        {
	        	
	        }
	        driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/div[1]/table/tbody/tr/td[2]/div/div/ul/li[3]/a")).click();
	        Thread.sleep(1000);
	        driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/div[1]/table/tbody/tr/td[2]/div/div/ul/li[3]/div/div/ul/li[5]/a")).click();
	        WebElement myDynamicElement1 = (new WebDriverWait(driver, 100000))
	        		  .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='lblMenuPath2']")));
	        Thread.sleep(1000);
	        while (driver.findElement(By.xpath("//*[@id='DivOverlayChild']")).isDisplayed())
	        {
	        	
	        }
	        driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/div[2]/div[2]/table/tbody/tr/td[2]/div/div[1]/div/fieldset/div[2]/div/table/tbody/tr[11]/td/div[2]/div/div[2]/input")).sendKeys("Ecotec");
	        
	        Thread.sleep(2000);
	        List<WebElement> optionsToSelect = driver.findElements(By.xpath("/html/body/ul[3]/li"));
	        System.out.println(optionsToSelect.size());
	        String textToSelect = "Ecotec";
	        for(WebElement option : optionsToSelect){
	            System.out.println(option);
	            if(option.getText().equals(textToSelect)) {
	                System.out.println("Trying to select: "+textToSelect);
	                option.click();
	                break;
	            }
	        }
	        while (driver.findElement(By.xpath("//*[@id='DivOverlayChild']")).isDisplayed())
	        {
	        	
	        }
	        
	        
	        
	      
	        
	       /* driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/div[1]/table/tbody/tr/td[2]/div/div/ul/li[3]/a")).click();
	        Thread.sleep(1000);
	        driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/div[1]/table/tbody/tr/td[2]/div/div/ul/li[3]/div/div/ul/li[5]/a")).click();
	        WebElement myDynamicElement1 = (new WebDriverWait(driver, 100000))
	        		  .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='lblMenuPath2']")));
	        WebElement myDynamicElement2 = (new WebDriverWait(driver, 100000))
	        		  .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='divGroupInfoNonEditLoading']")));
	        Thread.sleep(5000);
	        driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/div[2]/div[2]/table/tbody/tr/td[1]/div/fieldset/div[2]/ul/li/i")).click();
	        Thread.sleep(5000);
	        driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/div[2]/div[2]/table/tbody/tr/td[1]/div/fieldset/div[2]/ul/li/ul/li[2]/i")).click();
	        WebElement tree = driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/div[2]/div[2]/table/tbody/tr/td[1]/div/fieldset/div[2]/ul/li/ul/li[2]/ul"));
	        List<WebElement> treeCnt = tree.findElements(By.tagName("i"));
	        System.out.println("No. of sub tress are: "+treeCnt.size());
	       
	        
	       
	        for (int j = 2 ; j<=treeCnt.size() ; j++)
	        {
	        	 WebElement tree1 = driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/div[2]/div[2]/table/tbody/tr/td[1]/div/fieldset/div[2]/ul/li/ul/li[2]/ul/li["+j+"]/ul"));
	 	        List<WebElement> treeCnt1 = tree.findElements(By.tagName("i"));
	 	        System.out.println("No. of sub tress are: "+treeCnt1.size());
	        	driver.findElement(By.xpath(" /html/body/form/table[1]/tbody/tr/td/div[2]/div[2]/table/tbody/tr/td[1]/div/fieldset/div[2]/ul/li/ul/li[2]/ul/li["+j+"]/i")).click();
	        	if (treeCnt1.size() != 0)
	        	{
	        	for (int s = 1;s<= treeCnt1.size(); s++)
	        	{
	        		driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/div[2]/div[2]/table/tbody/tr/td[1]/div/fieldset/div[2]/ul/li/ul/li[2]/ul/li["+j+"]/ul/li["+s+"]/i")).click();
	        	}
	        	}
	        	
	        	//Thread.sleep(1000);
	        }
	        WebElement test = driver.findElement(By.cssSelector("ul.jstree-children:nth-child(3)"));
	        List<WebElement> rows = test.findElements(By.tagName("li"));
	        System.out.println("No. of Rows in the WebTable: "+rows.size());
	        System.out.println(test.getText());
	        ArrayList<String> rowData = new ArrayList<String>();
	        rowData.add(test.getText().toString());
	        String sathish = "Powerscreen";*/
	       /* WebElement tree = driver.findElement(By.cssSelector("#j1_5"));
	        List<WebElement> treeCnt = tree.findElements(By.tagName("i"));
	        System.out.println("No. of sub tress are: "+treeCnt.size());
	        for (int j = 2 ; j<=rows.size() ; j++)
	        {
	        	driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr/td/div[2]/div[2]/table/tbody/tr/td[1]/div/fieldset/div[2]/ul/li/ul/li[2]/ul/li["+j+"]/i")).click();
	        }*/

	      /*  for (int i = 1 ; i<=rows.size() ; i++)
	        {
	        	//String pageNav = driver.findElement(By.xpath(".//*[@id='pagination-holder']/ul/li["+rnum+"]")).getText();
	        	String pageNav = driver.findElement(By.xpath("//*[@id='j1_"+i+"_anchor']")).getText();
	        	if (sathish.equals(pageNav))
	        	{
	        		
	        		System.out.println("Powerscreen");
	        		driver.findElement(By.xpath("//*[@id='j1_"+i+"_anchor']")).click();
	        	}
	        //System.out.println(test1);
	        
	        }
	        
	        
	        
	      
	        
	        
	        System.out.println("TEST PASS");
			}
			catch (Exception e)
			{
			
			e.printStackTrace();
			 
			}
		
	}
	
	public static boolean isElementPresentcheck(By by, WebDriver driver)
	{
		
		try
		{
			System.out.println("searching for "+driver.findElement(by).getAttribute("id"));
			if(driver.findElement(by).isDisplayed())
			{
				System.out.println(driver.findElement(by).getAttribute("id")+" is present...");
				return true;
			}
			else
			{
				System.out.println(driver.findElement(by).getAttribute("id")+" is not present...");
				return false;
			}
		}
		catch (Exception e)
		{
			System.out.println("Element is not present...");
			return false;
		}
	}*/
		}
		catch (Exception e)
		{
		
		e.printStackTrace();
		}
	}
	
}

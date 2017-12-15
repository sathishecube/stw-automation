package jenkinsPackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class jenkingJava {
	
	
	@Test
public void testJenkins(){
	
	System.out.println("JENKINS TEsting");
	System.setProperty("webdriver.gecko.driver", "\\\\AMXSERVER\\amx-share\\STW_QA\\Jar files\\geckodriver.exe");
	WebDriver driver = new FirefoxDriver();
	driver.get("http://www.cricinfo.com");
	System.out.println("Sathish");
	driver.close();
}

}

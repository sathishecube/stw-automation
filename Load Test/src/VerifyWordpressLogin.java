import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;


public class VerifyWordpressLogin {
	@Test
	public void verifyValidLogin()
	{
		System.setProperty("webdriver.gecko.driver", "\\\\AMXSERVER\\amx-share\\STW_QA\\Jar files\\geckodriver.exe");
	WebDriver driver=new FirefoxDriver();

	driver.manage().window().maximize();

	driver.get("http://demosite.center/wordpress/wp-login.php");

	LoginPage login=new LoginPage(driver);



	login.clickOnLoginButton();


	driver.quit();

	}

}

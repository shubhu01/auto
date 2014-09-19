package SeleniumTestCases;

import org.openqa.selenium.server.SeleniumServer;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.SeleneseTestBase;
import com.thoughtworks.selenium.Selenium;

public class RcDemo extends SeleneseTestBase {
 
	SeleniumServer server;	
	@BeforeTest
	public void setUp() throws Exception{
		server = new SeleniumServer();
		server.start();
		selenium = new DefaultSelenium("localhost", 4444, "*chrome", "https://wordpress.com/");
		selenium.start();
	}
	
	@Test public void testCase() {
		try{
			selenium.open("/wp-login.php?redirect_to=https%3A%2F%2Fwordpress.com%2F");
		}catch(Exception e){
			System.out.println("Error log is " + e.getMessage());
		}
		selenium.type("id=user_login", "shubhu01");
		selenium.type("id=user_pass", "newuser@123");
		selenium.click("id=wp-submit");
		selenium.waitForPageToLoad("30000");
		String actualTitle = selenium.getTitle();
		String expectedTitle = "Blogs I Follow — WordPress.com";
		assertEquals(expectedTitle, actualTitle);
	}
	@AfterTest
	public void tearDown(){
		server.stop();
	}	
	
	
}

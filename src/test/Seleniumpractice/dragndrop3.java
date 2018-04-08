package Seleniumpractice;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;


public class dragndrop3 {
	WebDriver driver;

	@BeforeMethod
	public void Browserlaunch(){
	
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\chromedriver.exe");
		 driver = new ChromeDriver();
		driver.get("http://keenthemes.com/preview/metronic/theme/admin_1/ui_tree.html");
		driver.manage().window().maximize();
		
		
		
	}
	
	
	@Test
	public void Dragdrop() throws Exception
	{
		

		WebElement from = driver.findElement(By.id("j3_7_anchor"));
		
		WebElement To = driver.findElement(By.id("j3_1_anchor"));
		
		Actions bulider= new Actions(driver);
		
	Action dragndrop =bulider.clickAndHold(from).moveToElement(To).release(To).build();
	dragndrop.perform();
			
		
			
	
	
	}
	
	
	
	public void BrowserClose(){
	
		driver.quit();
	}
		
	
	
	
	
}

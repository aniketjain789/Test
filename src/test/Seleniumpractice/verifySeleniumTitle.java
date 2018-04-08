package Seleniumpractice;

import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.xml.dom.Tag;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

public class verifySeleniumTitle {
	
	@Test
	public void verifyTitle()
	{
		
		ExtentReports Extend = ExtentReports.get(verifySeleniumTitle.class);
				
		Extend.init("C:\\Anu C drive  Data\\workspace\\Selenium\\src\\Seleniumpractice\\Report\\report.html",true);
		Extend.startTest("Verify Page Title");
		
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\BrowserDrivers\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
				
		driver.manage().window().maximize();
				
		Extend.log(LogStatus.INFO, "Browser is up running");
				
		driver.get("http://learn-automation.com");
		
		Extend.log(LogStatus.INFO, "Application  is up and  running");		
				
		String title = driver.getTitle();
		
		Extend.log(LogStatus.INFO, "Title Captured");
		Assert.assertTrue(title.contains("fail"));
		
		Extend.log(LogStatus.FAIL, "Title is verified");
		
		driver.quit();
		Extend.endTest();
	
	}
}

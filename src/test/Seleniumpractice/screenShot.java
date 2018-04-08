package Seleniumpractice;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;


public class screenShot {

	
	
	public WebDriver driver;
	@Test
	public void TC_1() throws IOException{
		//driver = new FirefoxDriver();
		
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\chromedriver.exe");
		 driver = new ChromeDriver();
		
		driver.get("http://www.facebook.com");
		
		File  scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile,new File("C:\\Softwares\\screenshot1.png"));
		driver.close();
		
	}
}

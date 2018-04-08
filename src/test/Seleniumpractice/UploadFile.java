package Seleniumpractice;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class UploadFile {

	@Test
	public void FileDemo() throws IOException, Exception{
		
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\chromedriver.exe");
		WebDriver  driver=new ChromeDriver();
		
		
		driver.get("file:///E:/Traning%20Video/Testing/selenium/kumar/AutoIT/fileupload.html");
		
		//to click on upload button
		Thread.sleep(2000);
		driver.findElement(By.xpath("html/body/input")).click();
		
		
		//to run the script of AutoIT
		//Runtime.getRuntime().exec("C:\\Users\\HI AB\\Desktop\\File Upload.exe");
		Thread.sleep(6000);
		 String command ="E:\\Traning Video\\Testing\\selenium\\kumar\\AutoIT\\AutoIT\\File Upload.exe";
		  Runtime.getRuntime().exec(command);
		  
	}

}

package Seleniumpractice;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;





public class test {

	public static void main(String[] args) throws IOException
	{
		//System.setProperty("WebDriver.chrome.driver","C:\\Users\\HI AB\\workspace\\Selenium\\chromedriver.exe");
		
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\chromedriver.exe");
			//WebDriver driver = new ChromeDriver();
			WebDriver driver= new FirefoxDriver();
		
		driver.get("file:///E:/Traning%20Video/Testing/selenium/kumar/AutoIT/fileupload.html");
		driver.findElement(By.xpath("html/body/input")).click();
		//Runtime.getRuntime().exec("E:\\Traning Video\\Testing\\selenium\\kumar\\AutoIT\\AutoIT\\File Upload.exe");
		
		
		
		/*float k= 10.12665653433F;
		 System.out.println(k); 
*/
	}	
	
  
}
   

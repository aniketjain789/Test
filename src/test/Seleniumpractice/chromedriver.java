package Seleniumpractice;

import java.sql.Driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class chromedriver {

	
	public static void main(String[] args) throws Exception {
		
	
		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\HI AB\\workspace\\Selenium\\chromedriver.exe");
		//we can also use this 
		
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\test\\java\\com\\richfeel\\BrowserDrivers\\chromedriver.exe");
		WebDriver d = new ChromeDriver();
		d.get("http://www.facebook.com");
	
		d.manage().window().maximize();
		Thread.sleep(1000);
		d.quit();

	}

}

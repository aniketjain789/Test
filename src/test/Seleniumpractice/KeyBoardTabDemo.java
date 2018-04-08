package Seleniumpractice;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class KeyBoardTabDemo {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		
		driver.manage().window().maximize();
		
		driver.get("https://www.google.com/advanced_search");
		
		//Tab opration
		driver.findElement(By.id("_dKg")).sendKeys(Keys.TAB);
	
		Thread.sleep(400);
		driver.findElement(By.id("_aKg")).sendKeys(Keys.TAB);
		
	}

}

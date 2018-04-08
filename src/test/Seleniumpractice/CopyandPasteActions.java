package Seleniumpractice;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CopyandPasteActions {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		
		driver.manage().window().maximize();
		
		driver.get("https://www.google.com/advanced_search");
		
		// Enter the data on the first textbox
		driver.findElement(By.id("_dKg")).sendKeys("Selenium");
		
		Thread.sleep(400);
		
		//copy the data and Paste it on next text box 
		driver.findElement(By.id("_dKg")).sendKeys(Keys.CONTROL+"a");
		driver.findElement(By.id("_dKg")).sendKeys(Keys.CONTROL+"c");
			
		Thread.sleep(400);
		
		//Paste the data
		driver.findElement(By.id("_aKg")).sendKeys(Keys.CONTROL+ "v");
		driver.findElement(By.id("_aKg")).sendKeys(Keys.CONTROL+ "v");
		driver.findElement(By.id("_aKg")).sendKeys(Keys.CONTROL+ "v");
		driver.findElement(By.id("_aKg")).sendKeys(Keys.CONTROL+ "v");
		
	}

}

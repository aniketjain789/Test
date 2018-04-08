package Seleniumpractice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RadioButton {
	WebDriver driver;
	@BeforeMethod
	public void Browserlaunch(){
	
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\chromedriver.exe");
		 driver = new ChromeDriver();
		driver.get("http://www.calculator.net/mortgage-payoff-calculator.html");
		driver.manage().window().maximize();
		
		
		
	}
	
	@Test
	public void Radio(){
		driver.findElement(By.id("cpayoff1")).click();
		System.out.println("The output of the isDisplayed==>>"+driver.findElement(By.id("cpayoff1")).isDisplayed());
		System.out.println("The output of the isEnabled==>>"+driver.findElement(By.id("cpayoff1")).isEnabled());
		System.out.println("The output of the ISselected==>>"+driver.findElement(By.id("cpayoff1")).isSelected());
		
	}
	
	
	
	
	@AfterMethod
	public void BrowserClose(){
	
		driver.quit();
	}
		
}

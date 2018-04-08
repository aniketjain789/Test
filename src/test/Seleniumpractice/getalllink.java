package Seleniumpractice;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class getalllink {

	public static void main(String[] args) throws Exception {
	
		WebDriver driver = new FirefoxDriver();
		driver.get("http://127.0.0.1");
		WebElement element = driver.findElement(By.id("username"));
		element.sendKeys("admin");
		Thread.sleep(2000);
		
        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("manager");
        Thread.sleep(2000);
		
		//to click on login
		driver.findElement(By.id("loginButton")).click();
		Thread.sleep(2000);
		
	
		List <WebElement> links= driver.findElements(By.tagName("a"));
		System.out.println("no of link-"+links.size());
		
		for(int i=1;i<=links.size();i=i+1)
		{
			System.out.println("Name of Links#="+ i+ "-" + links.get(i).getText());
		}
		driver.quit();
	
	}
	
}

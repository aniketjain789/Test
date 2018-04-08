package Seleniumpractice;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class handleCheckBox {

	
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\chromedriver.exe");
		WebDriver driver =new ChromeDriver();
		driver.get("file:///C:/Users/HI%20AB/Desktop/practice/practise%20page.html");
		
		List<WebElement> check= driver.findElements(By.xpath(".//input[@name='lang' and @type='checkbox']"));
		
		
		for(int i=0;i<check.size();i++)
		{
			WebElement local_check=check.get(i);
			String value= local_check.getAttribute("value");
			System.out.println("VAlues from radio buttons="+value);
		
		}
		
		driver.close();
		
		
		
		
		
		
		
		
		
		
		
	}

}

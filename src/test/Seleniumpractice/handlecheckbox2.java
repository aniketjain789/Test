package Seleniumpractice;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class handlecheckbox2 {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\chromedriver.exe");
		WebDriver driver =new ChromeDriver();
		driver.get("http://seleniumpractise.blogspot.in/2016/08/how-to-automate-radio-button-in.html");
		
	List<WebElement> checkbox= driver.findElements(By.xpath("//input[@type='checkbox']"));
		 
	for(int i=0;i<checkbox.size();i++)
	{
		 WebElement ele=checkbox.get(i);
	
	
		 String  id=ele.getAttribute("id");
		if(id.equalsIgnoreCase("code"))
		{
			ele.click();
			break;
		}
	
	
	}
	
			

	}

}

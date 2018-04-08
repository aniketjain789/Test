package Seleniumpractice;

import java.util.*;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;



public class ChildBrowser {
	
	

		public static void main(String[] args) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\chromedriver.exe");
			//launching browser
			WebDriver od = new ChromeDriver();
			od.get("http://www.tutorialspoint.com/index.htm");
			od.manage().window().maximize();
			od.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		
			od.findElement(By.xpath("//a[@title='tutorialspoint @ Facebook']")).click();
			Set<String> windowIds= od.getWindowHandles(); 
			System.out.println("no of windows"+windowIds.size());
			Iterator<String> itrwindowids = windowIds.iterator();
			String Parent=itrwindowids.next();
			String Child=itrwindowids.next();
			System.out.println(Parent);
			System.out.println(Child);
			od.switchTo().window(Child);	
			od.close();
			od.switchTo().window(Parent);
		
		
				}

	}

	
	
	
	
	



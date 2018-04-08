package Seleniumpractice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class multiplebrowserstring {
	
	
	
	public static void main(String[] args) throws NumberFormatException, IOException, InterruptedException
	{
		System.out.println("1:firefox");
		System.out.println("2:chrome");
		System.out.println("3:IE");
		System.out.println("Enter your choice");
		
		BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));
		String n= br.readLine();
		String d = n.toLowerCase();
		
		     switch (d) 
		     {
		       case  "firefox"  :
		       {
		    	   WebDriver driver=new  FirefoxDriver();
		    	   driver.get("http://127.0.0.1");
		    	   driver.manage().window().maximize();
		    	   Thread.sleep(1000);
		    	   driver.manage().window().maximize();
		    	   break;
		       }
		     case  "chrome" :
				{
					System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\chromedriver.exe");
					WebDriver driver=new ChromeDriver();
					driver.manage().window().maximize();
					WebElement element = driver.findElement(By.id("username"));
					element.sendKeys("admin");
					driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("manager");
					driver.findElement(By.id("loginButton")).click();
					
					
				 
				}//case	
				
		       default:
		    		  
		    	   System.out.println("ldjskdj");
		             
		}//switch
	}//main
}//class

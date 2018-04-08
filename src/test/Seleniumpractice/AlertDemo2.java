package Seleniumpractice;

import org.openqa.selenium.Alert;		
import org.openqa.selenium.By;		
import org.openqa.selenium.NoAlertPresentException;		
import org.openqa.selenium.WebDriver;		
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;		




public class AlertDemo2 {
	        public static void main(String[] args) throws NoAlertPresentException {									
	            //WebDriver driver = new FirefoxDriver();	
	            
	            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\chromedriver.exe");
	    		WebDriver driver = new ChromeDriver();
	            		
	            // Alert Message handling
	                        		
	            driver.get("http://demo.guru99.com/V4/");			
	                                		
	            driver.findElement(By.name("uid")).sendKeys("mngr30127");					
	            driver.findElement(By.name("password")).sendKeys("EzAtAqy");					
	            driver.findElement(By.name("btnLogin")).submit();			
	            driver.findElement(By.linkText("Delete Customer")).click();			
	            driver.findElement(By.name("cusid")).sendKeys("53920");					
	            driver.findElement(By.name("AccSubmit")).submit();			
	            		
	                // Switching to Alert        
	            Alert alert=driver.switchTo().alert();		
	            		
	            // Capturing alert message.    
	            String alertMessage=driver.switchTo().alert().getText();		
	            		
	            // Displaying alert message		
	            System.out.println(alertMessage);			
	            		
	            // Accepting alert		
	            alert.accept();		
	                		
	        }		
	}			
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	



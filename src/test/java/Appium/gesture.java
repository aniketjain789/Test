package Appium;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

public class gesture  {
	 AndroidDriver<WebElement> driver; 
	 DesiredCapabilities capab = new DesiredCapabilities();
	 Define d; 

	

	@Test
	  public void gesture1() throws InterruptedException, IOException 
	  {
	  driver.findElement(By.xpath("//android.widget.TextView[@text='Views']")).click();
	  
	  //Tap
	  TouchAction t= new TouchAction(driver);
	  t.tap(driver.findElementByXPath("//android.widget.TextView[@text='Expandable Lists']")).perform();
	  driver.findElementByXPath("//android.widget.TextView[@text='1. Custom Adapter']").click();
	 t.press( driver.findElement(By.xpath("//android.widget.TextView[@text='People Names']"))).waitAction(Duration.ofSeconds(10)).release().perform();	  
	  }
	
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  

		 @BeforeClass
		  public void beforeClass() throws InterruptedException, MalformedURLException 
		  {   
		    capab = new DesiredCapabilities();    
		    capab.setCapability("BROWSER_NAME","Android");
		    capab.setCapability("VERSION","4.4.2");
		    capab.setCapability("deviceName","Itg Guru (SM-G350E)");
		    capab.setCapability("platformName","Android");
		    capab.setCapability("appPackage","io.appium.android.apis");
		    capab.setCapability("appActivity","io.appium.android.apis.ApiDemos"); 
		    Thread.sleep(2000); 
		    driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"),capab);
		    d = new Define(driver);
		  }
		

}

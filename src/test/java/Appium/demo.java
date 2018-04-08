package Appium;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

public class demo 
{
 AndroidDriver<WebElement> driver; 
 Define d;
 DesiredCapabilities capab = new DesiredCapabilities();
 
 
  @Test(priority=1,enabled=true)
  public void sanity_test() throws InterruptedException 
  {
	
	  
	  //xpath syantax 
	  //tagname[@attribute='value']
	  //tagname=classname
	  //driver.findElementById("android:id/text1").click();
	  driver.findElement(By.xpath("//android.widget.TextView[@text='Preference']")).click();
	  driver.findElement(By.xpath("//android.widget.TextView[@text='3. Preference dependencies']")).click();
	  driver.findElement(By.id("checkbox")).click();
	  driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
	  driver.findElement(By.className("android.widget.EditText")).sendKeys("Aniket Jain");
	  driver.findElements(By.className("android.widget.Button")).get(1).click();
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

  @AfterClass
  public void afterClass() 
  {
   
  }

}
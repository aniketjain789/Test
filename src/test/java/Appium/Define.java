package Appium;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class Define
{
public AndroidDriver<WebElement> driver;

	
	boolean internet;
	File file;
	FileInputStream fis;
	FileOutputStream fos;	
	HSSFWorkbook wrkbook;
	HSSFSheet sheet;
	HSSFRow shRow;
	HSSFCell shCells;
	String shname;
	HSSFCellStyle style;
	
	Define d;
	DesiredCapabilities capab ;
	 
	


	public Define(AndroidDriver<WebElement> driver) {
		this.driver = driver;
		
	}
	protected void init() throws InterruptedException, IOException
	  {
		capab = new DesiredCapabilities();    
	    capab.setCapability("BROWSER_NAME","Android");
	    capab.setCapability("VERSION","4.4.2");
	    capab.setCapability("deviceName","Itg Guru (SM-G350E)");
	    capab.setCapability("platformName","Android");
	    capab.setCapability("appPackage","io.appium.android.apis");
	    capab.setCapability("appActivity","io.appium.android.apis.ApiDemos"); 
	 
	    driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"),capab);
		  d = new Define(driver);
		
		//  d.device_permission();
		  //d.masterLogin();
	  }
	protected void API_click(By locator) throws InterruptedException
	  {
	  	internet = true;
		   int i=0;
	  	do
	      {
	     	 try
	     	 {
	     		 click(locator);
	     		 internet = false;   		 
	     	 }
	     	 catch (Exception e) 
	     	 {
	     		 System.out.println("waiting for response.");
	     		 Thread.sleep(10000);    		    		 
	     	 }
	     	 i++;
	 	    if(i==7)
	 	    {internet=false;
	 	     System.out.println(locator + " :- Does not responding.");}
	      }while(internet);
	  }	
	
	protected void click(By locator)
	{
		getElement(locator).click();
	}	
	
	
	protected WebElement getElement(By locator) {
		return driver.findElement(locator);
	}
	
	
	protected void type(By locator, String text)
	{
		 getElement(locator).sendKeys(text);
	}
	protected void clear(By locator)
	{
		getElement(locator).clear();
	}
	/*protected void scrollDown() 
	  {
		    //Dimension size = driver.manage().window().getSize();
		   // int x = size.getWidth() / 2;
		    //int starty = (int) (size.getHeight() * 0.60);
		    //int endy = (int) (size.getHeight() * 0.10);
		   // driver.swipe(x, starty, x, endy, 2000);
		}
	protected void swipe(int startX, int startY, int endX, int endY)
	{
		//  driver.swipe(startX, startY, endX, endY, 1500);		  
	}
	*/	
	protected void closekeyboard()
	{
		driver.navigate().back();
	}
	
	protected void device_permission()
	{
		 /////////////////////////////////////For Permission
		  boolean permission = true;
		  do{
		  try 
		  {
			  String SS = driver.findElement(By.id("login_et_username")).getText();
			 System.out.println(SS);
			  permission=false;		  
		  }catch (Exception e) 
		  	{
			  	driver.findElement(By.id("com.android.packageinstaller:id/permission_allow_button")).click();
		  	}
		 }while(permission);
		  ////////////////////////////////////////For Permission
	}

	
	
	

}

package login;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

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
	
	By drawer = By.className("android.widget.ImageButton");
	By logout = By.name("Logout");
	By logout_yes = By.id("btn_ok");
	
	By username = By.id("login_et_username");
	By pwd = By.id("login_et_password");
	By signIn_btn = By.id("login_button_container");

	public Define(AndroidDriver<WebElement> driver2) {
		this.driver = driver2;
		// TODO Auto-generated constructor stub
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
	protected void scrollDown() 
	  {
		    Dimension size = driver.manage().window().getSize();
		    int x = size.getWidth() / 2;
		    int starty = (int) (size.getHeight() * 0.60);
		    int endy = (int) (size.getHeight() * 0.10);
		    driver.swipe(x, starty, x, endy, 2000);
		}
	protected void swipe(int startX, int startY, int endX, int endY)
	{
		  driver.swipe(startX, startY, endX, endY, 1500);		  
	}
		
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
			  driver.findElement(By.id("login_et_username")).getText();
			  permission=false;		  
		  }catch (Exception e) 
		  	{
			  	driver.findElement(By.id("com.android.packageinstaller:id/permission_allow_button")).click();
		  	}
		 }while(permission);
		  ////////////////////////////////////////For Permission
	}
	public void masterLogin(int row_no) throws IOException, InterruptedException
	  {
//		  int row_no = 12;
		  Thread.sleep(8000);
		  file = new File("E:\\DoctoPlus\\Docto_Excel_Sheet\\Credentials.xls");	 
		  fis = new FileInputStream(file);
		  wrkbook = new HSSFWorkbook(fis);
		  shname = wrkbook.getSheetName(0);
		  sheet = wrkbook.getSheet(shname);
		  
		 device_permission();
		  
//		  API_click("email_edit","Email Edit");
		  shRow = sheet.getRow(row_no);
		  shCells = shRow.getCell(0);
		  System.out.println("Module:- "+shCells.getStringCellValue());
		  
		  shRow = sheet.getRow(row_no);
		  shCells = shRow.getCell(1);
//		  System.out.println(shCells.getStringCellValue());		  
		  //Thread.sleep(10000);		  
//		  driver.findElement(By.id("email_edit")).sendKeys(shCells.getStringCellValue());
		  type(username, shCells.getStringCellValue());
		  
		  shRow = sheet.getRow(row_no);
		  shCells = shRow.getCell(2);
//		  System.out.println(shCells.getStringCellValue());
//		  driver.findElement(By.id("password_edit")).sendKeys(shCells.getStringCellValue());
		  type(pwd, shCells.getStringCellValue());
		  closekeyboard();
//		  driver.findElement(By.id("btn_signin")).click();
		  click(signIn_btn);
				 // com_list.add(cm);
		  fis.close();
	  }
	
	protected void logout() throws InterruptedException
	{
		Thread.sleep(2000);
		click(drawer);
		Thread.sleep(500);
		click(logout);
		Thread.sleep(500);
		click(logout_yes);
		
	}
	
	
	

}

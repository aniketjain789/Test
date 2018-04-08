package docto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
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
	 By year_click = By.id("date_picker_header_year");
	  By year_list_id = By.id("text1");
	  By month_prev = By.id("prev");
	  By month_next = By.id("next");
		By cal_done_btn = By.id("button1");

	  
	public Define(AndroidDriver<WebElement> driver2) {
		this.driver = driver2;
		// TODO Auto-generated constructor stub
	}
	public void API_click(By locator) throws InterruptedException
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
	
	public void click(By locator)
	{
		getElement(locator).click();
	}	
	
	
	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}
	public void scrollup() {
		Dimension dimensions = driver.manage().window().getSize();
		Double screenHeightStart = dimensions.getHeight() * 0.5;
		int scrollStart = screenHeightStart.intValue();
		Double screenHeightEnd = dimensions.getHeight() * 0.2;
		int scrollEnd = screenHeightEnd.intValue();
		driver.swipe(0,scrollStart,0,scrollEnd,2000);
		}

	
	public void type(By locator, String text)
	{
		 getElement(locator).sendKeys(text);
	}
	public void clear(By locator)
	{
		getElement(locator).clear();
	}
	public void scrollDown() 
	  {
		    Dimension size = driver.manage().window().getSize();
		    int x = size.getWidth() / 2;
		    int starty = (int) (size.getHeight() * 0.60);
		    int endy = (int) (size.getHeight() * 0.10);
		    driver.swipe(x, starty, x, endy, 2000);
		}
	public void swipe(int startX, int startY, int endX, int endY)
	{
		  driver.swipe(startX, startY, endX, endY, 1500);		  
	}
		
	public void closekeyboard()
	{
		driver.navigate().back();
	}
	
	public String get_text(By locator)
	{
		return getElement(locator).getText();
	}	
	public void device_back()
	{
		driver.navigate().back();
	}
	public void device_permission()
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
	public void setDate(Date strDate) throws InterruptedException
	  {  boolean  select = true;
		  Thread.sleep(3000);		  
		 try 
		  {
			  driver.findElement(By.id("android:id/date_picker_header_year")).click();
			  System.out.println("Android 6.0");
			  select = false;
			  Marshmallow(strDate);		
		} catch (Exception e){}
		  if(select)
		  {
		  try {
			  driver.findElement(By.id("android:id/date_picker_year")).click();
				System.out.println("Android 5.0");
				select = false;
				Lollipop(strDate);
				
		} catch (Exception e) {	}
		  }	  
		  if(select)
		  {
			  	
		  }
		 }
	 public void Marshmallow(Date dob_date) throws InterruptedException
	  {
		  SimpleDateFormat day = new SimpleDateFormat("dd");	  
		  SimpleDateFormat month = new SimpleDateFormat("MM");
		  SimpleDateFormat year = new SimpleDateFormat("yyyy");
		  String dob_day = day.format(dob_date),dob_month = month.format(dob_date),dob_year = year.format(dob_date);
//		  System.out.println("DOB:- "+dob_day+"."+dob_month+"."+dob_year);	  
		  Date curr_date = new Date();
		  SimpleDateFormat d1 = new SimpleDateFormat("MM");	  
		  String current_month = d1.format(curr_date);	  	  
		  click(year_click);	 
		  //year
		  boolean flag1=true;
		  int count=0;
			 do
			 {	
			  List<WebElement> year_list = driver.findElements(year_list_id);
//			  System.out.println(year_list.size());			 
				 for(WebElement we : year_list)
				 {		 
					 String temp = we.getText();
					 if(temp.equalsIgnoreCase(dob_year))
					 {
						 we.click();			 					 
						 flag1=false;
						 break;
					 }
				 }
				 if(flag1)
				 {	
					  Point point = year_list.get(0).getLocation();
					  int startX = point.getX();
					  int startY = point.getY();
					  point = year_list.get(year_list.size()-1).getLocation();				  
					  int endX = point.getX();
					  int endY = point.getY();
					  if(count>15)
					  {swipe(endX, endY, startX, startY);}
					  else
					  {swipe(startX, startY, endX, endY);}
				 }		 
				 count++;
			 }while(flag1);
			 //year		 
			 int int_curr_month = Integer.parseInt(current_month);
			 int int_dob_month = Integer.parseInt(dob_month);
			 int int_day = Integer.parseInt(dob_day);
			 Thread.sleep(1000);
			 //month and day 12 < 3
			 if(int_curr_month==int_dob_month)
			 {
				 driver.findElement(By.xpath("//android.view.View[@index='"+(int_day-1)+"']")).click();	 
			 }
			 else
			 {
				 if(int_curr_month<int_dob_month)
				 {
					 for(int i=0;i<(int_dob_month-int_curr_month);i++)
					 {click(month_next);}
				 }		 
				 else
				 {
					 for(int i=0;i<(int_curr_month - int_dob_month);i++)
					 {click(month_prev);}
				 }
				 driver.findElement(By.xpath("//android.view.View[@index='"+(int_day-1)+"']")).click();	 
			 }
			 //month		
			 click(cal_done_btn);		 
	  }
	 public void Lollipop(Date dob_date) throws InterruptedException
		{
			 SimpleDateFormat day = new SimpleDateFormat("dd");	  
			  SimpleDateFormat month = new SimpleDateFormat("MMM");
			  SimpleDateFormat year = new SimpleDateFormat("yyyy");
			  String date_day_string = day.format(dob_date),date_month = month.format(dob_date),dob_year = year.format(dob_date);;
			
			int date_yr = Integer.parseInt(dob_year);
//			String date_month = "MAR";
			int date_day = Integer.parseInt(date_day_string);		
			boolean flag1=true;
			boolean flag= true;	
			boolean flag2 = true;
			String curr_month;
			 
			 driver.findElement(By.id("date_picker_year")).click();
			 do
			 { 
				 List<WebElement> month_list = driver.findElements(By.id("month_text_view"));
//				 System.out.println(month_list.size());			 
				 for(int k=1;k<=3;k++)
				 {				 
					 String temp = month_list.get(k).getText();				 
//					 System.out.println(temp);
					 Thread.sleep(100);
					 if(temp.equalsIgnoreCase(Integer.toString(date_yr)))
					 {
						 month_list.get(k).click();
//						System.out.println(month_list.get(k));
						 flag1=false;
						 break;
					 }				 
				}
				 if(flag1==false)
				 {break;}
				 int cur_yr = Integer.parseInt(month_list.get(2).getText());
//				 System.out.println(cur_yr);
//				 if()
				 if(flag1)
					 {
						  Point point = month_list.get(3).getLocation();
						  int startX = point.getX();
						  int startY = point.getY();
						  point = month_list.get(1).getLocation();
						  int endX = point.getX();
						  int endY = point.getY();
						  Thread.sleep(300);
						  if(cur_yr<date_yr) 
						  {swipe(startX, startY, endX, endY);}
						  else
						  {swipe(endX, endY,startX, startY);}					  
						 }
		}while(flag1);
			 	
			 	Thread.sleep(1500);		
			 	driver.findElement(By.xpath("//android.view.View[@index='0']")).click();	 		
		 		curr_month = driver.findElement(By.id("date_picker_month")).getText();
		 		if(curr_month.equalsIgnoreCase(date_month))
		 		{
		 			flag=false;
		 		}
			 
			 while(flag)
			 	{
				 	if(driver.findElement(By.id("date_picker_month")).getText().equalsIgnoreCase("DEC"))
				 	{flag2=false;}
				 	Point point = driver.findElement(By.xpath("//android.view.View[@index='27']")).getLocation();
			 		int startX = point.getX();
			 		int startY = point.getY();
			 		point = driver.findElement(By.xpath("//android.view.View[@index='0']")).getLocation();
			 		int endX = point.getX();
			 		int endY = point.getY();
			 		Thread.sleep(1000);
			 		if(flag2)
			 		{driver.swipe(startX, startY, endX, endY, 1500);}
			 		else
			 		{driver.swipe(endX, endY, startX, startY, 1500);}
			 		
			 		Thread.sleep(800);
			 		driver.findElement(By.xpath("//android.view.View[@index='0']")).click();		 		
			 		curr_month = driver.findElement(By.id("date_picker_month")).getText();
			 		if(curr_month.equalsIgnoreCase(date_month))
			 		{
			 			flag=false;
			 		}		
			 		
			 		Thread.sleep(800);
			 	}
		 		driver.findElement(By.xpath("//android.view.View[@index='"+(date_day-1)+"']")).click();
		 		Thread.sleep(800);
		 		driver.findElement(By.id("button1")).click();


		}
	 public void logout() throws InterruptedException
	{
		Thread.sleep(2000);
		click(drawer);
		Thread.sleep(500);
		click(logout);
		Thread.sleep(500);
		click(logout_yes);
		
	}
	
	
	

}

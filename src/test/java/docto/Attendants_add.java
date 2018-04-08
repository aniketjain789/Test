package docto;

import org.testng.annotations.Test;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;

public class Attendants_add 
{
	AndroidDriver<WebElement> driver;
	Define d;
	File file;
	FileOutputStream fos;
	FileInputStream fis;
	HSSFCellStyle style;
	HSSFFont ifPass, ifFail;	
	HSSFWorkbook wrkbook ;
	HSSFSheet sheet;
	HSSFRow shRow;
	HSSFCell shCells;
	String shname;
	int rCount, cCount, row=1,col=7;
	
	By drawer = By.className("android.widget.ImageButton");
	By buttons_list = By.id("item_name");
	
	By add_attend = By.id("menu_add_attendant");	
	
	By save_btn = By.id("actionbar_icon_save");
	By cancel_btn = By.id("actionbar_icon_cancel");
	
	By att_name_id = By.id("attendant_name_edt");
	By cont_no_id = By.id("attendant_mobile_edt");
	By address_id = By.id("attendant_address_edt");
	
	By gender_m = By.id("male");
	By gender_f = By.id("female");
	
	By dob_btn = By.id("attendant_DOB"); 
	By notes_id = By.id("attendant_notes_edt");
	  By year_click = By.id("date_picker_header_year");
	  By year_list_id = By.id("text1");
	  By month_prev = By.id("prev");
	  By month_next = By.id("next");
	By cal_done_btn = By.id("button1");
	
	  By txt_confirm = By.id("txt_confirm");
	  By txt_unique_code = By.id("Unique_code");
	  By txt_pass_code = By.id("pass_code");
	  By close_pop_up = By.id("txt_cancel");
	
	 	 By name_list = By.id("opdlist_item_tv_name");
	 	 By del_btn = By.id("icon_delete");
	 	 By ok = By.id("btn_ok");
	  
	  List<String> attendant_list = new ArrayList<String>();
	  String att_name;
  @Test(dataProvider = "dp")
  public void add_attendants(String at_name, String cont_no, String Address, String dob, String gender, String notes) throws InterruptedException, ParseException, IOException 
  {
	  att_name = at_name;
	  d.API_click(add_attend);
	  
	  d.type(att_name_id, at_name);
	  d.type(cont_no_id, cont_no);
	  d.closekeyboard();
	  d.type(address_id, Address);
	  d.closekeyboard();
	  d.API_click(dob_btn);
	  Thread.sleep(800);
	  Date dob_date= new SimpleDateFormat("dd/MM/yyyy").parse(dob);
	  d.setDate(dob_date);
	  //gender
	  if(gender.equalsIgnoreCase("f"))
	  {d.click(gender_f);}
	  else{d.click(gender_m);}
	  //gender	  
	  d.type(notes_id, notes);
	  d.click(save_btn);
	  check();
	  
  }
  public void delete(String attendt_name) throws InterruptedException, IOException
  {
// 	 d.API_click(title_click);
// 	 d.API_click(back_btn);
 	 open_tab();
 	 int count = 0;
 	 boolean flag1=true;
 	 do
 	 {	count = 0;
 		 List<WebElement> cli_list = driver.findElements(name_list);
 		 List<WebElement> del_list = driver.findElements(del_btn);
 		 System.out.println(cli_list.size());
 		 
 		 for(WebElement we : cli_list)
 		 {		 
 			 String temp = we.getText();
 			 if(temp.equalsIgnoreCase(attendt_name))
 			 {
 				 System.out.println("clicking "+count);
 				 del_list.get(count).click();
 				 Thread.sleep(1000);
 				 d.click(ok);
 				 flag1=false;
 				 break;
 			 }
 			 count++;
 		 }
 		 if(flag1)
 		 {
 			  Point point = cli_list.get(cli_list.size()-1).getLocation();
 			  int startX = point.getX();
 			  int startY = point.getY();
 			  point = cli_list.get(0).getLocation();
 			  int endX = point.getX();
 			  int endY = point.getY();
 			  d.swipe(startX, startY, endX, endY);
 		 }		 
 	 }while(flag1);
 	 pass();
 	 
 			 
  }
  public void check() throws InterruptedException, IOException
  {
	  Thread.sleep(2000);
	try 
	{
		try { d.click(txt_confirm);
			
		} catch (Exception e) 
		{
			Thread.sleep(2000);
			d.click(txt_confirm);
		}
		System.out.println(d.getElement(txt_confirm).getText());
		System.out.println(d.getElement(txt_unique_code).getText());
		System.out.println(d.getElement(txt_pass_code).getText());
		d.click(close_pop_up);
		System.out.println("Pass");
		pass();
		attendant_list.add(att_name);
		
	} catch (Exception e) 
	{
		System.out.println("Fail");
		fail("Fail to add");
	}
  }
 /* public void setdate(Date dob_date) throws InterruptedException
  {
	  SimpleDateFormat day = new SimpleDateFormat("dd");	  
	  SimpleDateFormat month = new SimpleDateFormat("MM");
	  SimpleDateFormat year = new SimpleDateFormat("yyyy");
	  String dob_day = day.format(dob_date),dob_month = month.format(dob_date),dob_year = year.format(dob_date);;
//	  System.out.println("DOB:- "+dob_day+"."+dob_month+"."+dob_year);	  
	  Date curr_date = new Date();
	  SimpleDateFormat d1 = new SimpleDateFormat("MM");	  
	  String current_month = d1.format(curr_date);	  	  
	  d.click(year_click);	 
	  //year
	  boolean flag1=true;
		 do
		 {	
		  List<WebElement> year_list = driver.findElements(year_list_id);
//		  System.out.println(year_list.size());			 
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
				  d.swipe(startX, startY, endX, endY);
			 }		 
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
				 {d.click(month_next);}
			 }		 
			 else
			 {
				 for(int i=0;i<(int_curr_month - int_dob_month);i++)
				 {d.click(month_prev);}
			 }
			 driver.findElement(By.xpath("//android.view.View[@index='"+(int_day-1)+"']")).click();	 
		 }
		 //month		
		 d.click(cal_done_btn);		 
  }*/
  @DataProvider
  public Object[][] dp() throws Exception {
    /*return new Object[][] {
        new Object[] { "pandu2", "9658965896", "Pune","31/01/1980","F","Notes Test" },
        new Object[] { "pandu", "9658965896", "Pune","19/09/1990","F","Notes Test" },
//      new Object[] { 2, "b" },
    };*/
	  file = new File("E:\\DoctoPlus\\Docto_Excel_Sheet\\attendant_add.xls");	  
	  fis = new FileInputStream(file);
	  wrkbook = new HSSFWorkbook(fis);
	  shname = wrkbook.getSheetName(0);
	  sheet = wrkbook.getSheet(shname);
//	  System.out.println(shname);
	  Thread.sleep(2000);	  
	  rCount = sheet.getPhysicalNumberOfRows()-1;  //1
	  cCount = sheet.getRow(0).getPhysicalNumberOfCells()-2; //2
//	  System.out.println(rCount +" "+ cCount);
	  Object[][] data = new Object [rCount][cCount];	  
	  for(int i=0;i<rCount;i++)
	  {		  
		  shRow = sheet.getRow(i+1);		  
		  for(int j=0;j<cCount;j++)
		  {
			  shCells = shRow.getCell(j+1);
			  data[i][j] = shCells.getStringCellValue();		 
		  }
	  }
//		  fis.close();	  
	  return data;	
  }
  @BeforeClass
  public void beforeClass() throws Exception, InterruptedException 
  {
	  DesiredCapabilities capab = new DesiredCapabilities();	  	
	  capab.setCapability("BROWSER_NAME","Android");
	  capab.setCapability("VERSION","4.4.2");
	  capab.setCapability("deviceName","Itg Guru (SM-G350E)");
	  capab.setCapability("platformName","Android");	  
	  capab.setCapability("appPackage","innomationtech.doctoplus.doctor");
	  capab.setCapability("appActivity","activity.LoginActivity"); 
	  driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"),capab);
	  d = new Define(driver);
	  d.masterLogin(6);
	  open_tab();
  }
  private void  open_tab() throws InterruptedException
  {
    	d.API_click(drawer);	  
    	List<WebElement> btn_list = driver.findElements(buttons_list);
//  	System.out.println(btn_list.size());		
  	btn_list.get((6)).click();
  	Thread.sleep(800);
  }
  public void pass() throws IOException, InterruptedException
  {
	  fos = new FileOutputStream(file);	  
	  style = wrkbook.createCellStyle();	  
	  ifPass = wrkbook.createFont();
	  
	  	ifPass.setBold(true);
	  	ifPass.setColor(HSSFColor.GREEN.index);
	  	  
		shRow = sheet.getRow(row);
		shCells = shRow.createCell(col);
		
		shCells.setCellValue("Pass");		
		style.setFont(ifPass);
		shCells.setCellStyle(style);
		
		wrkbook.write(fos);
		row++;
		fos.close();
		//logout();	  
  }
  public void fail(String message) throws IOException
  {
	    fos = new FileOutputStream(file);
	    style= wrkbook.createCellStyle();
	    ifFail = wrkbook.createFont();
	    
		  ifFail.setBold(true);
		  ifFail.setItalic(true);
		  ifFail.setColor(HSSFColor.RED.index);
	    
		 shRow = sheet.getRow(row);
		 shCells = shRow.createCell(col);
		 shCells.setCellValue("Fail:- " + message);		
		 style.setFont(ifFail);	
		 shCells.setCellStyle(style);

		 wrkbook.write(fos);
		 row++;
		 fos.close();  
  }
  @AfterClass
  public void afterClass() throws InterruptedException, IOException  
  {
	 /* for(int k=0;k<attendant_list.size();k++)		  
	  {delete(attendant_list.get(k));}*/
	  try {
		  d.logout();		  
	} finally {
		driver.quit();	
	} 	  
  }

}

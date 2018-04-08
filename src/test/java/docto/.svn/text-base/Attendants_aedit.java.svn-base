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
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;

public class Attendants_aedit 
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
	int rCount, cCount, row=1,col=8;
	
	By drawer = By.className("android.widget.ImageButton");
	By buttons_list = By.id("item_name");
	By name_list = By.id("opdlist_item_tv_name");
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
	By cal_clear_btn = By.id("button2");
	
	By title_click = By.xpath("//android.widget.TextView[@index='1']");


  @Test(dataProvider = "dp")
  public void edit(String attendt_name, String new_attn_name, String cont_no, String address, String DOB, String gender, String notes) throws InterruptedException, ParseException 
  {
//	  d.API_click(title_click);
	  Thread.sleep(2500);
	  select(attendt_name);
	  
	  d.type(att_name_id, new_attn_name);
	  d.type(cont_no_id, cont_no);
	  d.closekeyboard();
	  d.type(address_id, address);
	  d.closekeyboard();
	  d.API_click(dob_btn);
	  Thread.sleep(800);
	  //// for edit
	  d.click(cal_clear_btn);
	  d.API_click(dob_btn);
	  //// for edit
	  Date dob_date= new SimpleDateFormat("dd/MM/yyyy").parse(DOB);
	  d.setDate(dob_date);
	  
//	  //gender
	  if(gender.equalsIgnoreCase("f"))
	  {d.click(gender_f);}
	  else{d.click(gender_m);}
//	  //gender	  
	  d.type(notes_id, notes);
	  d.click(save_btn);	  
	  
  }

  @DataProvider
  public Object[][] dp() throws Exception 
  {
 /*   return new Object[][] {
      new Object[] {"pandu","pandu","9874563214", "nagar", "16/10/2003","Male","Notes" },
      new Object[] {"pandu2","pandu2","9874563214", "nagar", "16/10/2004","Male","Notes" },
    };*/
	  file = new File("E:\\DoctoPlus\\Docto_Excel_Sheet\\attendant_edit.xls");	  
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
  public void select(String attendt_name) throws InterruptedException
  {
	  int count = 0;
	 	 boolean flag1=true;
	 	 do
	 	 {	count = 0;
	 		 List<WebElement> cli_list = driver.findElements(name_list);
	 		 System.out.println(cli_list.size());
	 		 
	 		 for(WebElement we : cli_list)
	 		 {		 
	 			 String temp = we.getText();
	 			 if(temp.equalsIgnoreCase(attendt_name))
	 			 {
	 				 System.out.println("clicking "+count);
	 				 we.click();
	 				 Thread.sleep(1000);
	 				 flag1=false;
	 				 break;
	 			 }
	 			 count++;
	 		 }
	 		 if(flag1)
	 		 {
	 			  try 
	 			  {
	 				 Point point = cli_list.get(cli_list.size()-1).getLocation();
		 			  int startX = point.getX();
		 			  int startY = point.getY();
		 			  point = cli_list.get(0).getLocation();
		 			  int endX = point.getX();
		 			  int endY = point.getY();
		 			  d.swipe(startX, startY, endX, endY);
				} catch (Exception e) 
	 			  {System.out.println("in catch");
					Thread.sleep(2000);
				}
	 		 }		 
	 	 }while(flag1);
  }
  @BeforeClass
  public void beforeClass() throws InterruptedException, IOException 
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
  @AfterClass
  public void afterClass() throws InterruptedException 
  {
	  try {
		  d.logout();		  
	} finally {
		driver.quit();	
	}
  }

}

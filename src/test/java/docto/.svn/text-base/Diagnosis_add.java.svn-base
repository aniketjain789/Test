package docto;

import org.testng.annotations.Test;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;

public class Diagnosis_add 
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
	int rCount, cCount;
	int row=1,col=9,loop=0;
	
	By drawer = By.className("android.widget.ImageButton");
	By buttons_list = By.id("item_name");
	
	By add_btn = By.id("menu_dadd");
	By diagnsis_name_id = By.id("diagnosis_name_edt");
	By age_grp_id = By.id("diagnosis_age_group_edt");
	By medicine_name_id = By.id("edittext_medicinename");
	
	By morning = By.id("btn_sunhalf");
	By afternoon = By.id("btn_sunfull");
	By night = By.id("btn_moon");
	
	By none = By.id("swipe_none");
	By before = By.id("swipe_before_food");
	By after = By.id("swipe_after_food");
	 
	By unit_id = By.id("medicine_quantity_edt");
	By days_id = By.id("medicine_days_edt");
	
	By right_btn = By.id("btn_right");
	By additional_details_id = By.id("edt_additional_note");
	By notes_id = By.id("edittext_notes");
	
	By save_btn = By.id("actionbar_icon_save");
	By cancel_btn = By.id("actionbar_icon_cancel");
	
	By search_box = By.id("edt_search");
	By search_res = By.id("diagnosis_name_txt");
	
	By refresh_btn = By.id("lay_refresh11");
	
	String old_name = "xxx";
	boolean flag = false,flag_1=true;
  @Test(dataProvider = "dp")
  public void add_diagnosis(String diag_name, String age_grp, String medicine_name, String time, String after_before, String unit_days, String additional_details, String notes)throws Exception   
  {
	  flag_1=true;
	  if(old_name.equalsIgnoreCase(diag_name))
	  {add_medicine(medicine_name, time, after_before,unit_days, additional_details, notes);
	  loop++;}
	  else
	  {			  	  
		  if(flag)	
		  { d.click(save_btn); 
		  	check(old_name);
		  	loop=0;}
//		  open_tab();
			  d.click(add_btn);
		  
		  d.type(diagnsis_name_id, diag_name);	  
		  d.type(age_grp_id, age_grp);
		  d.closekeyboard();
		  add_medicine(medicine_name, time, after_before,unit_days, additional_details, notes);
		  if(!notes.equalsIgnoreCase("None"))
		  {
			  d.click(notes_id);
			  d.type(notes_id, notes);
			  d.closekeyboard();
		  }
	  }	  
	  old_name = diag_name;
	  flag=true;	  
//	  row++;
  }
  public void add_medicine(String medicine_name, String time, String after_before, String unit_days, String additional_details, String notes)
  {
	  d.type(medicine_name_id, medicine_name);
	  d.closekeyboard();
	  
	  String arr1[] = time.split("/");	  
	  for(int i=0;i<arr1.length;i++)
	  {		   
		  switch (arr1[i])
		  {
		  	case "m": d.click(morning);			
			break;
		  	case "a": d.click(afternoon);			
			break;
		  	case "n": d.click(night);			
			break;
		  }
	  }
	  switch (after_before)
	  {
	  	case "none": d.click(none);			
		break;
	  	case "after": d.click(after);			
		break;
	  	case "before": d.click(before);			
		break;
	  }
	  
	  String arr2[] = unit_days.split("/");
	  
	  d.click(unit_id);
	  d.type(unit_id, arr2[0]);
	  d.closekeyboard();
	  
	  d.click(days_id);
	  d.type(days_id, arr2[1]);
	  d.closekeyboard();
	  
	  if(!additional_details.equalsIgnoreCase("None"))
	  {
		  d.click(additional_details_id);
		  d.type(additional_details_id, additional_details);
		  d.closekeyboard();
	  }	  
	
	  d.click(right_btn);

  }

  public void check(String name) throws Exception
  {
	  System.out.println("Loop value "+loop);
	  Thread.sleep(1200);
	  try 
	  {
		  	open_tab();	
			d.click(search_box);
			d.type(search_box, name);
			Thread.sleep(700);			
			d.click(search_res);
			Thread.sleep(500);
			d.click(cancel_btn);			
			d.clear(search_box);
			System.out.println("Pass");
			for(int i=0;i<=loop;i++){pass();}
	  }
	  catch (Exception e) 
	  {
		  System.out.println("Medicine already Exists");
			for(int i=0;i<=loop;i++){fail("Medicine already Exists");}
		  Thread.sleep(500);
		  d.API_click(cancel_btn);
	  } 
	
  }
  @DataProvider
  public Object[][] dp() throws FileNotFoundException, Exception 
  {
/*    return new Object[][] {
//      new Object[] { "Diagnosis Name", "age group", "Medicine Name", "m/a/n", "after", "2.5/4", "Additional details", "notes" },      
    	new Object[] { "test auto24", "67", "qwwey3diqw 500", "m/a/n", "after", "2.5/4", "nothing", "test notes" },
    	new Object[] { "test auto24", "67", "dicypo223oq 250", "n", "before", "5/5", "nothing", "test notes" },      
    	new Object[] { "test auto25", "5", "qw3zyid223fsdf 250", "n", "before", "5/5", "nothing2", "test notes2" },      

    };*/
	  file = new File("E:\\DoctoPlus\\Docto_Excel_Sheet\\Diagnosis_add.xls");	  
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
  public void beforeClass() throws Exception 
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
	  d.masterLogin(4);
	  d.API_click(refresh_btn);
	  open_tab();
	    
	}
  private void  open_tab() throws InterruptedException
  {
	  	d.click(drawer);	  
	  	List<WebElement> btn_list = driver.findElements(buttons_list);
//		System.out.println(btn_list.size());		
		btn_list.get((3)).click();
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
  public void afterClass() throws Exception 
  {
	  	d.click(save_btn);
	  	Thread.sleep(500);
	  	check(old_name);
//	  	d.click(cancel_btn);
		  try
		  {
			  d.logout();
		  }finally 
		  {
			  driver.quit();
		  }
  }
  

}

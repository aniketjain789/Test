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

public class OPD_edit 
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
	int row=1,col=9;
	
	By drawer = By.className("android.widget.ImageButton");
	By buttons_list = By.id("item_name");
	
	By clinic_name_list = By.id("clinic_name");
	By title_click = By.id("actionbar_title_text");

	By clinic_name_id = By.id("add_opd_et_clinic_name");
	By locality_id = By.id("edt_locality");
	By address_id = By.id("add_opd_et_address");
	By pincode_id = By.id("add_opd_et_pincode");
	By contact_no_id = By.id("add_opd_et_phoneNo");
	
	By day_time_tab = By.id("edit_opd_avail_expand");
	By days_list = By.id("lay_weekdays");
	By start_time = By.id("lay_fromTime");
	By end_time = By.id("lay_toTime");
	By ok_btn = By.id("ok");
	
	By max_apointment_plus = By.id("item_max_plus");
	By opd_delete_btn = By.id("item_tv_delete");
	
	By opd_details_tab = By.id("icon_opd_expand");
	By save_btn = By.id("actionbar_icon_save");
	By cancel_btn = By.id("actionbar_icon_cancel");
	By back_btn = By.id("actionbar_icon_back");
	boolean found=true;
  @Test(dataProvider = "dp")
  public void edit_opd(String opd_name, String new_opd_name, String locality, String address, String pincode, String OPD_cont, String days, String max_appoint_id) throws InterruptedException, IOException 
  {
	  select_opd(opd_name);
	  if(found)
	  {
		  d.API_click(clinic_name_id);
		  d.type(clinic_name_id, new_opd_name);
		  d.type(locality_id, locality);
		  d.click(address_id);
		  d.type(address_id, address);
		  d.closekeyboard();
		  d.type(pincode_id, pincode);
		  Thread.sleep(1000);	  
		  d.closekeyboard();  
		  try 
		  {
			  d.type(contact_no_id, OPD_cont);
		  } catch (Exception e) 
		  {
			  Thread.sleep(1000);
			  d.closekeyboard();
			  d.type(contact_no_id, OPD_cont);
		  }	  
		  d.closekeyboard();
		  d.click(opd_details_tab);
		  add(days, max_appoint_id);
		  d.click(save_btn);
		  Thread.sleep(2500);
	  	  check();
	  }
	  else
	  {
		  fail("opd not found");
	  }
  }
  public void check() throws IOException
  {
	  try 
	  {
		d.API_click(title_click);
		System.out.println("Pass");
		pass();
	} catch (Exception e) 
	  {
		d.click(cancel_btn);
		System.out.println("fail");
		fail("didn't update");
	}
  }
  public void add(String days, String max_appoint_id) throws InterruptedException
  {  
	  d.click(day_time_tab);
	  d.click(opd_delete_btn);
	  Thread.sleep(800);
	  String arr[] = days.split(",");	  
	  for(int i=0;i<arr.length;i++)
	  {
		  switch (arr[i])
		  {
		  	case "mon":  d.click(By.id("lay_day_monday"));			
			break;
		  	case "tue":  d.click(By.id("lay_day_theusday"));			
			break;
		  	case "wed":  d.click(By.id("lay_day_wednesday"));			
			break;
		  	case "thu":  d.click(By.id("lay_day_theursday"));			
			break;
		  	case "fri":  d.click(By.id("lay_day_friday"));			
			break;
		  	case "sat":  d.click(By.id("lay_day_saturday"));			
			break;
		  	case "sun":  d.click(By.id("lay_day_sunday"));			
			break;
		  }
	  }
	  d.click(start_time);
	  d.click(ok_btn);	  
	  d.click(end_time);
	  d.click(ok_btn);
	  
	  int max = Integer.parseInt(max_appoint_id);
	  for(int i=0; i<max; i++)
	  {
		  d.click(max_apointment_plus);
	  }
  }
  public void select_opd(String opd_name) throws InterruptedException, IOException
  {	 int count=0;
	 d.API_click(title_click);
 	 boolean flag1=true;
 	 do
 	 {	
 		 List<WebElement> cli_list = driver.findElements(clinic_name_list);
// 		 System.out.println(cli_list.size());
 		 for(WebElement we : cli_list)
 		 {		 
 			 String temp = we.getText();
 			 if(temp.equalsIgnoreCase(opd_name))
 			 {
 				 we.click();
 				 flag1=false;
 				 found=true;
 				 break;
 			 } 			 
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
 		 count++;
 		 if(count==6){flag1=false;found=false;}
 	 }while(flag1);		 
  }
  @DataProvider
  public Object[][] dp() throws Exception 
  {
  /*  return new Object[][] {
//      new Object[] { "opd_name", "new_opd_name", "locality", "Address", "Pincode", "cont_no", "days", "max_apoint" },
    	new Object[] {"akshay", "akshay", "warje", "pune", "411051", "9658965896", "mon,fri", "4" },
    };*/
	  file = new File("E:\\DoctoPlus\\Docto_Excel_Sheet\\opd_edit.xls");	  
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
  public void beforeClass() throws IOException, InterruptedException 
  {  DesiredCapabilities capab = new DesiredCapabilities();	  	
  capab.setCapability("BROWSER_NAME","Android");
  capab.setCapability("VERSION","4.4.2");
  capab.setCapability("deviceName","Itg Guru (SM-G350E)");
  capab.setCapability("platformName","Android");	  
  capab.setCapability("appPackage","innomationtech.doctoplus.doctor");
  capab.setCapability("appActivity","activity.LoginActivity"); 
  driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"),capab);
  d = new Define(driver);
  d.masterLogin(4);
//  d.API_click();
  open_tab();
    
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
private void  open_tab() throws InterruptedException
{
  	d.API_click(drawer);	  
  	List<WebElement> btn_list = driver.findElements(buttons_list);
//	System.out.println(btn_list.size());		
	btn_list.get((4)).click();
	Thread.sleep(800);
}
  @AfterClass
  public void afterClass() throws InterruptedException 
  {
	  d.click(back_btn);
	  try
	  {
		  d.logout();
	  }finally 
	  {
		  driver.quit();
	  }  }

}

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
import java.util.ArrayList;
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

public class OPD_list 
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
	int row=1,col=8,loop=0;
	
	By drawer = By.className("android.widget.ImageButton");
	By buttons_list = By.id("item_name");
	
	By back_btn = By.id("actionbar_icon_back");
	By add_btn = By.id("actionbar_icon_add");
	By del_btn = By.id("icon_delete");
	By clinic_name_list = By.id("clinic_name");
	By delete_panel = By.id("lay_name");
	By add_one_more_opd = By.id("add_opd_add");
	
	By clinic_name_id = By.id("add_opd_et_clinic_name");
	By locality_id = By.id("edt_locality");
	By address_id = By.id("add_opd_et_address");
	By pincode_id = By.id("add_opd_et_pincode");
	By contact_no_id = By.id("add_opd_et_phoneNo");
	
	By close_btn = By.id("actionbar_icon_cancel");
	By next = By.id("add_opd_bt_next_container");
	
	By days_list = By.id("lay_weekdays");
	By start_time = By.id("lay_fromTime");
	By end_time = By.id("lay_toTime");
	By ok_btn = By.id("ok");
	
	By max_apointment_plus = By.id("item_max_plus");
	By opd_delete_btn = By.id("item_tv_delete");
	By done_btn = By.id("add_opd_done_bt_container");
	
	By txt_pageload = By.id("txt_clinic");
	By ok = By.id("btn_ok");
	String old_name = "xxx";
	
	By title_click = By.id("actionbar_title_text");
	List<String> opd_name_list = new ArrayList<String>();

	
	boolean flag = false;
	
  @Test(dataProvider = "dp")
  public void add_opd(String clinic_name, String locality, String address, String pincode, String OPD_cont, String days, String max_appoint_id) throws InterruptedException 
  {
	  if(old_name.equalsIgnoreCase(clinic_name))
	  {
		  d.API_click(add_one_more_opd);		  
		  add(days, max_appoint_id);
		  d.click(done_btn);
	  }
	  else
	  {
		  d.API_click(add_btn);
		  Thread.sleep(800);
		  d.type(clinic_name_id, clinic_name);
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
		  	d.click(next);
		  	add(days, max_appoint_id);
		  	d.click(done_btn);
	  }
	  old_name = clinic_name;
	  opd_name_list.add(clinic_name);	  
  }
  
  public void add(String days, String max_appoint_id) throws InterruptedException
  {
	  d.API_click(txt_pageload);
	  
	  String arr[] = days.split(",");
	  
	  for(int i=0;i<arr.length;i++)
	  {
		  switch (arr[i])
		  {
		  	case "mon":  d.click(By.id("lay_day_"+"monday"));			
			break;
		  	case "tue":  d.click(By.id("lay_day_"+"theusday"));			
			break;
		  	case "wed":  d.click(By.id("lay_day_"+"wednesday"));			
			break;
		  	case "thu":  d.click(By.id("lay_day_"+"theursday"));			
			break;
		  	case "fri":  d.click(By.id("lay_day_"+"friday"));			
			break;
		  	case "sat":  d.click(By.id("lay_day_"+"saturday"));			
			break;
		  	case "sun":  d.click(By.id("lay_day_"+"sunday"));			
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
  
 public void delete(String clinic_name) throws InterruptedException, IOException
 {
//	 d.API_click(title_click);
//	 d.API_click(back_btn);
//	 open_tab();
	 d.API_click(title_click);
	 int count = 0;
	 boolean flag1=true;
	 do
	 {	count = 0;
		 List<WebElement> cli_list = driver.findElements(clinic_name_list);
		 List<WebElement> del_list = driver.findElements(del_btn);
		 System.out.println(cli_list.size());
		 
		 for(WebElement we : cli_list)
		 {		 
			 String temp = we.getText();
			 if(temp.equalsIgnoreCase(clinic_name))
			 {
				 System.out.println("clicking "+count);
				 del_list.get(count).click();
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
  @DataProvider
  public Object[][] dp() throws Exception {
    /*return new Object[][] {
//      new Object[] { "clinic_name", "locality", "address", "411001", "1234657980","tuesday,monday,wednesday,thursday,friday,saturday,sunday","4"},
//      new Object[] { "clinic_name", "locality", "address", "411001", "1234657980","monday","4"},
        new Object[] { "clinic_name", "locality", "address", "411001", "1234657980","saturday,sunday","2"},
        new Object[] { "zclinic_name1", "locality", "address", "411001", "1234657980","monday,friday","3"},
    };*/
	  file = new File("E:\\DoctoPlus\\Docto_Excel_Sheet\\opd_add_del.xls");	  
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
	  d.masterLogin(2);
//	  d.API_click();
	  open_tab();
	    
	}
  private void  open_tab() throws InterruptedException
  {
	  	d.API_click(drawer);	  
	  	List<WebElement> btn_list = driver.findElements(buttons_list);
//		System.out.println(btn_list.size());		
		btn_list.get((4)).click();
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
	  for(int k=0;k<opd_name_list.size();k++)		  
	  {delete(opd_name_list.get(k));}
	  try {
		  d.click(back_btn);
	} catch (Exception e) 
	  {
		Thread.sleep(3000);
		d.click(back_btn);
	}
	  try
	  {
		  d.logout();
	  }finally 
	  {
		  driver.quit();
	  }
  }

}

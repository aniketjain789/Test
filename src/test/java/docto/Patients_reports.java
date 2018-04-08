package docto;

import org.testng.annotations.Test;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;

public class Patients_reports 
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
	int row=1,col=10;
	
	By drawer = By.className("android.widget.ImageButton");
	By patients_tab = By.name("Patients");
	
	By search_box = By.id("edt_search");
	By search_result_patient_list = By.id("txtPatientName");
	
	By reports_tab = By.name("Reports");
	By add_btn = By.id("actionbar_icon_add");
	
	By report_title_id = By.id("txtReportTitle");
	By report_type_id = By.id("medicalRecordType");
	By date_id = By.id("lay_date");
	By date_ok = By.id("ok");
	By notes_id = By.id("txtNotes");
	By next_btn = By.id("add_opd_bt_next_container");
	
	By done = By.id("add_opd_done_bt_container");
	By report_list = By.xpath("\\android.widget.RelativeLayout[@index='0']");
	
	By delete_report = By.id("icon_delete");
	By yes_btn = By.id("btn_ok");
	By close_btn = By.id("actionbar_icon_cancel");

	By account_tab = By.name("Accounts");
	By bill_amnt = By.id("rbt_pending");
	By ammount_id = By.id("edtamount");
	By remark_id = By.id("edtNotes");
	
	By recived_amnt = By.id("rbt_received");
	
	By save = By.id("actionbar_icon_save");
	
  @Test(dataProvider = "dp")
  public void test_reports(String p_name, String report_title, String report_type, String notes, String amount_type, String bill_amt, String bRemark) throws InterruptedException 
  {
	  d.API_click(search_box);
	  d.clear(search_box);
	  d.type(search_box, p_name);
	  d.closekeyboard();
	  Thread.sleep(1000);
	  d.click(search_result_patient_list);
	  
	  d.API_click(reports_tab);
	  d.API_click(add_btn);
	  
	  d.click(report_title_id);
	  d.type(report_title_id, report_title);
	  d.closekeyboard();
	  
	  d.click(report_type_id);
	  d.type(report_type_id, report_type);
	  d.closekeyboard();
	  
	  d.click(date_id);
	  d.click(date_ok);
	  
	  d.click(notes_id);
	  d.type(notes_id, notes);
	  d.closekeyboard();
	  
	  d.click(next_btn);
	  Thread.sleep(800);
	  d.API_click(done); //made change here
	  Thread.sleep(800);
	  
	  d.click(account_tab);

	  d.click(add_btn);
	  
	  if(amount_type.equalsIgnoreCase("Received Amount"))
	  {d.click(recived_amnt);}
	  else
	  {d.click(bill_amnt);}
	  
//	  d.API_click(bill_amnt);
	  
	  d.click(ammount_id);
	  d.type(ammount_id, bill_amt);
	  d.closekeyboard();
	  
	  d.click(remark_id);
	  d.type(remark_id, bRemark);
	  d.closekeyboard();
	  
	  d.click(save);
	  
	  Thread.sleep(1500);
	  d.click(close_btn);
  }

  @DataProvider
  public Object[][] dp() throws Exception 
  {
    /*return new Object[][] {
      new Object[] { "Helan", "Test", "Biopsy", "test notes","Bill Amount", "500", "test"},
      new Object[] { "Nana Patekar", "Test", "Biopsy", "test notes", "Received Amount", "500", "rem  test" },
    };*/
	  file = new File("E:\\DoctoPlus\\Docto_Excel_Sheet\\Patient_reports.xls");	  
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
//			  System.out.println(shCells.getStringCellValue());
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
	  d.masterLogin(3);
	  d.API_click(drawer);
	  d.click(patients_tab);
	  
  }

  @AfterClass
  public void afterClass() throws InterruptedException 
  {
	  
		  
	  d.logout();	  

  }

}

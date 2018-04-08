package docto;

import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.List;
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

public class Patients_delete_records 
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
	
	By pres_text = By.name("Prescription");
	By delete_btn = By.id("icon_delete");
//	By delete_btn = By.xpath("//innomationtech.doctoplus.doctor:id/icon_delete[@index='1']");

	By ok_btn = By.id("btn_ok");
	

	By reports_tab = By.name("Reports");	
	By account_tab = By.name("Accounts");
	
	By close_btn = By.id("actionbar_icon_cancel");
	
	By record_count = By.xpath("//android.widget.RelativeLayout[@index='0']");

  @Test(dataProvider = "dp")
  public void delete_records(String p_name) throws Exception 
  {
	  d.API_click(search_box);
	  d.clear(search_box);
	  d.type(search_box, p_name);
	  d.closekeyboard();
	  Thread.sleep(1000);
	  d.click(search_result_patient_list);
	  
	  d.API_click(pres_text);
	  Thread.sleep(500);
	  delete();
	  Thread.sleep(1000);
	  
	  d.click(reports_tab);
	  Thread.sleep(500);
	  delete();
	  Thread.sleep(1000);
	  
	  d.click(account_tab);
	  Thread.sleep(500);
	  delete();
	  Thread.sleep(1000);
	  
	  d.click(close_btn);
	  
  }
  private void delete()
  {	  
	  try 
	  {
		List<WebElement> records_list = driver.findElements(delete_btn);
		System.out.println(records_list.size());
		if((records_list.size()/2)==0)
		{
			System.out.println("Record not found");
		}
		for(int i=0;i<(records_list.size()/2);i++)
		{
			records_list.get(i).click();
			Thread.sleep(800);
			d.click(ok_btn);
		}
	  } catch (Exception e) 
	  {
		  System.out.println("Record not found");
	  }
  }

  @DataProvider
  public Object[][] dp() throws Exception {
    /*return new Object[][] {
      new Object[] { "Helan"},
    };*/
	  file = new File("E:\\DoctoPlus\\Docto_Excel_Sheet\\Patient_prescription.xls");	  
	  fis = new FileInputStream(file);
	  wrkbook = new HSSFWorkbook(fis);
	  shname = wrkbook.getSheetName(0);
	  sheet = wrkbook.getSheet(shname);
//	  System.out.println(shname);
	  Thread.sleep(2000);	  
	  rCount = sheet.getPhysicalNumberOfRows()-1;  //1
	  cCount = sheet.getRow(0).getPhysicalNumberOfCells()-10; //2
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

package docto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.logging.Logger;

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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

public class Settings {
	static Logger log =Logger.getLogger(Settings.class.getName());
	

	AndroidDriver<WebElement> driver;
	Define d;
	File file;
	FileOutputStream fos;
	FileInputStream fis;
	HSSFCellStyle style;
	HSSFFont ifPass, ifFail;	
	HSSFWorkbook wrkbook;
	HSSFSheet sheet;
	HSSFRow shRow;
	HSSFCell shCells;
	String shname;
	int rCount, cCount;
	int row=1,col=10;
	
	By drawer = By.className("android.widget.ImageButton");
	
	By firstvisittime = By.id("edt_firstVisit_time");
	By followupvisittime = By.id("edt_followupVisit_time");
	By advopdtime = By.id("edt_advanceOpd_time");
	By devicename = By.id("edt_device_name");
	
	By opdyes = By.id("yes");
	By opdno = By.id("no");
	By patientyes = By.id("yes_modify_Patient");
	By patientno = By.id("no_modify_Patient");
	By groupyes = By.id("yes_onoff_group");
	By groupno = By.id("no_onoff_group");
	By savemenu = By.id("menu_save");
	
	
	
	@DataProvider
	public Object[][] settingData() throws Exception 
	{
		  file = new File("E:\\Excel sheet\\setting.xls");	  
		 
		  fis = new FileInputStream(file);
		  wrkbook = new HSSFWorkbook(fis);
		  shname = wrkbook.getSheetName(0);
		 
		  sheet = wrkbook.getSheet(shname);

		  Thread.sleep(2000);	  
		  rCount = sheet.getPhysicalNumberOfRows()-1;  //1
		  cCount = sheet.getRow(0).getPhysicalNumberOfCells()-2; //2
		 System.out.println("Row :"+rCount);
		 System.out.println("col :"+cCount);
		  Object[][] data = new Object [rCount][cCount];	  
		  for(int i=0;i<rCount;i++)
		  {		  
			  shRow = sheet.getRow(i+1);

			  for(int j=0;j<cCount;j++)
			  {
				  shCells = shRow.getCell(j+1);
				  System.out.println(shCells);
				 data[i][j] = shCells.getStringCellValue();	
			  }
		  }
//			  fis.close();	  
		  return data;	
	  }
	
	
	
	
	
	
	public void open_tab() throws InterruptedException
	{
		//open drawer
				d.API_click(drawer);
				Thread.sleep(100);
				List<WebElement> s = driver.findElements(By.id("item_name"));
				s.get(7).click();
				Thread.sleep(500);
	}

	
	@Test(dataProvider = "settingData")
	public void settings(String sr, String Firstvisit,String Followup,String advopd,String devicenm) throws InterruptedException, IOException 
	{
		
		
		open_tab();
		d.type(firstvisittime,Firstvisit);
		d.type(followupvisittime,Followup);
		d.type(advopdtime, advopd);
		d.type(devicename,devicenm);
		d.closekeyboard();
		d.click(opdyes);
		d.click(opdno);
		d.click(patientyes);
		d.click(patientno);
		d.click(groupyes);
		d.click(groupno);
		d.click(savemenu);
	
	}
	@BeforeClass
	public void beforeClass () throws InterruptedException, IOException 
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
			
			
			  d.masterLogin(8);
			
			//d.init();
		}//beforeclass
	
	@AfterClass
	public void afterClass() throws InterruptedException 
	  {
		log.info("after class.........!");
		
		 Thread.sleep(200);
		d.logout();
	  }


}

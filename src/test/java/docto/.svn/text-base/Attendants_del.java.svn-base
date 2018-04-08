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
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;

public class Attendants_del
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
	
	By attndt_name_list = By.id("opdlist_item_tv_name");
	By delt_btn_list = By.id("icon_delete");
	By ok_btn = By.id("btn_ok");
	
  @Test(dataProvider = "dp")
  public void delt_attendnt(String attendnt_name) throws InterruptedException, IOException 
  {
	  delete(attendnt_name);;
	  
  }
  public void delete(String attendnt_name) throws InterruptedException, IOException
  {
// 	 d.API_click(title_click);
// 	 d.API_click(back_btn);
// 	 open_tab();
// 	 d.API_click(title_click);
 	 int count = 0;
 	 boolean flag1=true;
 	 do
 	 {	count = 0;
 		 List<WebElement> cli_list = driver.findElements(attndt_name_list);
 		 List<WebElement> del_list = driver.findElements(delt_btn_list);
 		 System.out.println(cli_list.size());
 		 if(cli_list.size()==0){Thread.sleep(3500);}
 		 for(WebElement we : cli_list)
 		 {		 
 			 String temp = we.getText();
 			 if(temp.equalsIgnoreCase(attendnt_name))
 			 {
 				 System.out.println("clicking "+count);
 				 del_list.get(count).click();
 				 d.click(ok_btn);
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
// 	 pass();
 	 
 			 
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
	  cCount = sheet.getRow(0).getPhysicalNumberOfCells()-8; //2
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

package login;

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

public class opd {
	
	
	
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
	int row=1,col=4;
	
	By appoint_label = By.name("Appointments");
	By opd_list = By.id("txt_clinic_name");
	
	By max_alw_tokn = By.id("icon_editMaxAllowdToken");
	By set_count = By.id("txt_max_patient_count");
	By ok_btn = By.id("btn_ok");
	By activate_opd = By.id("activte_opd_box");
	
	By cancel_opd = By.id("cancel_opd_box");
	By cancel_opd_yes = By.id("btn_ok");
	
	By canceled_text = By.name("CANCELLED");
	
	int swipe_count=1;
  @Test(dataProvider = "dp")  
  public void opd_check(String opd_name, String status, String max_count) throws InterruptedException, IOException 
  {  
	  swipe_count=0;
	  d.API_click(appoint_label);
	  
//////selecting OPD	  
	  List<WebElement> opd = driver.findElements(opd_list);
//	  System.out.println(opd.size());
	  boolean flag = true;
	  do
	  {
		  for(WebElement we:opd)
		  {
			  String temp = we.getText();
//			  System.out.println(temp);
			  if(temp.equalsIgnoreCase(opd_name))
			  {
				  we.click();
				  flag=false;
				  break;
			  }			
		  }
		  if(flag)
		  {Point point = opd.get(opd.size()-1).getLocation();
		  int startX = point.getX();
		  int startY = point.getY();
		  point = opd.get(0).getLocation();
		  int endX = point.getX();
		  int endY = point.getY();
//		  System.out.println(startX +" a "+ startY);
//		  System.out.println( endX +" b "+ endY);
		  d.swipe(startX, startY, endX, endY);
		  swipe_count++;
		  }
	  	}while(flag);	  
//////selecting OPD
  
	  if(status.equalsIgnoreCase("Open"))
	  {
		  try 
		  {
			d.click(activate_opd);
			Thread.sleep(1000);
		  } catch (Exception e) 
		  {}
		  Thread.sleep(800);
		  d.click(max_alw_tokn);		  
		  Thread.sleep(800);
		  
		  try // use to click on edit max allowed token button
		  {
			d.click(set_count);
		  } catch (Exception e) 
		  {
			  List<WebElement> btn = driver.findElements(max_alw_tokn);
//			  System.out.println("axy:- "+btn.size());
			  btn.get(1).click();
		  }
		  d.click(set_count);
		  d.clear(set_count);
		  d.type(set_count, max_count);		  
		  d.closekeyboard();
		  d.click(ok_btn);
		  
	  }
	  else
	  {
		try 
		{
			d.click(cancel_opd);
			Thread.sleep(800);
			d.click(cancel_opd_yes);
			System.out.println("OPD Cancelled");
			pass();
		} catch (Exception e) 
		{
			System.out.println("Already Cancelled");
			fail("Already Cancelled");
		}
	  }
	  /////////////reset list
	  System.out.println("Reseting");
	  opd = driver.findElements(opd_list);
	  Point point = opd.get(opd.size()-1).getLocation();
	  int startX = point.getX();
	  int startY = point.getY();
	  point = opd.get(0).getLocation();
	  int endX = point.getX();
	  int endY = point.getY();	  
	  
	  for(int i=0;i<=swipe_count;i++)
	  {  
		  d.swipe(endX, endY , startX, startY);
	  }
	  /////////////reset list
  }

  @DataProvider
  public Object[][] dp() throws IOException, InterruptedException {
  /*  return new Object[][] {
      new Object[] { "Hendre Building", "close","30" },
      new Object[] { "Opd", "Open","50" },      
    };*/
	  
	  file = new File("E:\\DoctoPlus\\Docto_Excel_Sheet\\opd.xls");	  
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
  public void afterClass() 
  {
	  
  }

}

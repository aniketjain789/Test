package docto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class changepassword {
	static Logger log =Logger.getLogger(changepassword.class.getName());

	
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
	int row=1,col=5;
	boolean flag=true;
	
	
	By drawer = By.className("android.widget.ImageButton");
	By currentpass = By.id("edtcurrentPassword");
	By newpass = By.id("edtNewPassword");
	By repass = By.id("edtReTypePassword");
	By submitbtn = By.id("btnSubmit");
	By errormsg = By.className("android.widget.TextView");
	
		
	
	String actual="xx";

	
	List<String> Current_password= new ArrayList<String>();
	
	
	@Test (dataProvider ="ChangepasswordData")
	public void Testchangepassword(String Current_pass,String New_pass,String Re_pass,String Expected_Result) throws InterruptedException, IOException
	{
		
	d.clear(currentpass);
	log.info("");
	d.type(currentpass, Current_pass);
	Current_password.add(Current_pass);
	
	d.clear(newpass);
	d.type(newpass,New_pass);
	
	d.clear(repass);
	d.type(repass,Re_pass);
	
	d.click(submitbtn);
	
	check(Expected_Result);
	}
	public void check(String Expected_Result) throws InterruptedException, IOException
	{
		log.info("iofj");
		System.out.println("exp excel : "+Expected_Result);
		do{
			try {
				actual = driver.findElement(By.xpath("//android.widget.TextView[@index='0']")).getText();	
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
			
			System.out.println("actual value :"+actual);
			if(actual.equalsIgnoreCase(Expected_Result))
			{	
				System.out.println("pass");
				pass();
				flag=false;				
			}
			else{
				System.out.println("in Fail method");
				fail();	
				flag=false;
				}
			
		}while(flag);
		
			
			
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

public void fail() throws IOException
{
   //log.info("I am in fail method");
	
//   File file1 = new File("E:\\Excel sheet\\changepassword.xls");	

   fos = new FileOutputStream(file);
   style= wrkbook.createCellStyle();
   ifFail = wrkbook.createFont();
   ifFail.setBold(true);
   ifFail.setItalic(true);
   ifFail.setColor(HSSFColor.RED.index);
   shRow = sheet.getRow(row);
   shCells = shRow.createCell(col);
   shCells.setCellValue("Fail");		
   style.setFont(ifFail);	
   shCells.setCellStyle(style);
   wrkbook.write(fos);
   row++;
   fos.close();  
}
	

	
	
	
	@DataProvider
	public Object[][] ChangepasswordData() throws Exception 
	{
		  file = new File("E:\\Excel sheet\\changepassword.xls");	  
		 
		  fis = new FileInputStream(file);
		  wrkbook = new HSSFWorkbook(fis);
		  shname = wrkbook.getSheetName(0);
		 
		  sheet = wrkbook.getSheet(shname);

		  Thread.sleep(2000);	  
		  rCount = sheet.getPhysicalNumberOfRows()-1;  //1
		  cCount = sheet.getRow(0).getPhysicalNumberOfCells()-2; //2
		 System.out.println("Row  :"+rCount);
		 System.out.println("Col  :"+cCount);
		  Object[][] data = new Object [rCount][cCount];	  
		  for(int i=0;i<rCount;i++)
		  {		  
			  shRow = sheet.getRow(i+1);
			 
			  for(int j=0;j<cCount;j++)
			  {
				  shCells = shRow.getCell(j+1);
				  //System.out.println(shCells);
				 data[i][j] = shCells.getStringCellValue();	
			  }
		  }
//			  fis.close();	  
		  return data;	
	  }
	
	
	
	
	
	
	
	

@BeforeClass
public void beforeClass() throws InterruptedException, IOException 
{
	  DesiredCapabilities capab = new DesiredCapabilities();	  	
	  capab.setCapability("BROWSER_NAME","Android");
	  capab.setCapability(MobileCapabilityType.TAKES_SCREENSHOT, "true");
	  capab.setCapability("VERSION","4.4.2");
	  capab.setCapability("deviceName","Itg Guru (SM-G350E)");
	  capab.setCapability("platformName","Android");	  
	  capab.setCapability("appPackage","innomationtech.doctoplus.doctor");
	  capab.setCapability("appActivity","activity.LoginActivity"); 
	  driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"),capab);
	  d = new Define(driver);
	  d.device_permission();
	  d.masterLogin(10);
	  d.API_click(drawer);
		Thread.sleep(100);
		List<WebElement> s = driver.findElements(By.id("item_name"));
		s.get(9).click();
		Thread.sleep(500);
	 
}//beforeclass

@AfterClass
public void afterClass() throws InterruptedException {
	log.info("after class.........!");
	Thread.sleep(200);
	d.logout();
	driver.quit();
  }
}//class



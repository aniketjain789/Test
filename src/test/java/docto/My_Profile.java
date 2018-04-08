package docto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

public class My_Profile {
	

static Logger log =Logger.getLogger(My_Profile.class.getName());
	
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
	int row=1,col=16;
	
	
	

	By drawer = By.className("android.widget.ImageButton");
	
	//profile Details
	
	By pname= By.id("add_name");
	By pdegree= By.id("edt_Degrees");
	By pspeclity= By.id("edt_speciality");
	By pemail= By.id("doctor_email_id");
	By pregno= By.id("add_resg_no");
	By pmobno= By.id("clinic_mobile_no");
	//By paddregno= By.id("add_resg_no");
	By pclinicmobno= By.id("clinic_mobile_no");
	By pconsultfee= By.id("text_res");
	By pdate= By.id("txt_practice_date");
	By feenotes= By.id("edt_fee_notes");
	By dayfee= By.id("edt_day_convered_for_fees");
	
	
	//contactDetails
	By Caddress= By.id("edt_personal_address");
	By Cpincode= By.id("edt_personal_pincode");
	By Ccity= By.id("edt_city");
	By Cotherphn= By.id("edt_presonal_phoneNo");
	By Cachievement= By.id("edt_achievement");
	By profile_img= By.id("member_img");
	By save_btn= By.id("menu_save");
	
	//date
	
	By done= By.id("button1");
	By clear= By.id("button2");
	
	By curryear= By.id("date_picker_year");
	By currmonth= By.id("date_picker_month");
	By currday= By.id("date_picker_day");
	By toolbar = By.id("toolbar");

	
	
	@DataProvider
	public Object[][] DoctorData() throws Exception 
	{
		  file = new File("E:\\Excel sheet\\My_Profile.xls");	  
		 
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
			d.API_click(drawer);
			Thread.sleep(100);
			List<WebElement> s = driver.findElements(By.id("item_name"));
			s.get(5).click();
			Thread.sleep(500);
		}

	@Test(dataProvider="DoctorData")
	public void Profile(String Name,String Degree,String Speciality,String Email_Id,String Registration_no,String Contact_Number,String Consult_fee,String fee_notes,String day_fee,String C_address,String C_pincode,String C_City,String C_OtherContactno,String C_achievement,String strDate) throws InterruptedException, IOException, ParseException 
	{	
			
		
		log.info("Profile Method........!");
		open_tab();
		
			//name
		d.clear(pname);
		d.type(pname,Name);
		d.closekeyboard();
		
		//pdegree
		d.clear(pdegree);
		d.type(pdegree,Degree);
		d.closekeyboard();
		
		//speclity
		d.scrollup();
		// driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"WebView\"));");
		d.clear(pspeclity);
		d.type(pspeclity,Speciality);
		d.closekeyboard();
		
		//email
		d.clear(pemail);
		d.type(pemail,Email_Id);
		d.closekeyboard();
		
		//regiration no
		d.scrollup();
		d.clear(pregno);
		d.type(pregno,Registration_no);
		d.closekeyboard();
		//personal phone no
		d.clear(pmobno);
		d.type(pmobno,Contact_Number);
		d.closekeyboard();
		
		//fees
		d.clear(pconsultfee);
		d.type(pconsultfee,Consult_fee);
		d.closekeyboard();
		
		d.scrollup();
		Thread.sleep(1000);
		d.click(pdate);
		
		//date(Date);
		 Date dob_date= new SimpleDateFormat("dd/MM/yyyy").parse(strDate);
		 d.setDate(dob_date);
		
		
		//fee note
		d.clear(feenotes);
		d.type(feenotes,fee_notes);
		d.closekeyboard();
		
		//day fee
		d.clear(dayfee);
		d.type(dayfee,day_fee);
		d.closekeyboard();
		System.out.println("Complete Test Case ");
		
		
		//contact details
		Thread.sleep(500);
		List<WebElement> tab = driver.findElements(By.className("android.support.v7.app.a$f"));
		tab.get(1).click();
		

		//contact address
	
		d.clear(Caddress);
		d.type(Caddress,C_address);
		d.closekeyboard();
		
		d.clear(Cpincode);
		d.type(Cpincode,C_pincode);
		d.closekeyboard();
		
		
		d.click(Cpincode);
		Thread.sleep(300);
		d.closekeyboard();
		Thread.sleep(1500);
		
		d.scrollDown();
		Thread.sleep(1000);
		d.clear(Cotherphn);
		d.type(Cotherphn,C_OtherContactno);
		d.closekeyboard();
		
		
		
		d.clear(Cachievement);
		d.type(Cachievement,C_achievement);
		d.closekeyboard();
		d.click(save_btn);
		pass();
		
}//test
	
	
	public void pass() throws IOException, InterruptedException{
		
		log.info("HERE I AM IN PASS METHOD");
		
		File file1 = new File("E:\\Excel sheet\\My_Profile.xls");	 
		fos = new FileOutputStream(file1);	
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

public void fail(String m_name) throws IOException
{
   log.info("I am in fail method");
   File file1 = new File("E:\\Excel sheet\\My_Profile.xls");	

   fos = new FileOutputStream(file1);
   style= wrkbook.createCellStyle();
   ifFail = wrkbook.createFont();
   ifFail.setBold(true);
   ifFail.setItalic(true);
   ifFail.setColor(HSSFColor.RED.index);
   shRow = sheet.getRow(row);
   shCells = shRow.createCell(col);
   shCells.setCellValue(m_name+"Already Exist");		
   style.setFont(ifFail);	
   shCells.setCellStyle(style);
   wrkbook.write(fos);     
   row++;
   fos.close();  
}
	
	
	
	
	
	
	
	
	
	
/*@AfterClass
public void afterClass() throws InterruptedException 
	 {
		log.info("after class.........!");
		Thread.sleep(200);
		d.logout();
		driver.quit();
	  }*/
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
		
		
		  //d.masterLogin(9);
		
		//d.init();
	}//beforeclass
	

}//CLASS


	
	
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
import org.openqa.selenium.Point;
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

public class zzDemodate
{
	

static Logger log =Logger.getLogger(zzDemodate.class.getName());
	
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
	
	//profile Details
	
	By pname= By.id("add_name");
	By pdegree= By.id("edt_Degrees");
	By pspeclity= By.id("edt_speciality");
	By pemail= By.id("doctor_email_id");
	By pregno= By.id("add_resg_no");
	By pmobno= By.id("clinic_mobile_no");
	By paddregno= By.id("add_resg_no");
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
	
	
	
		@DataProvider
	public Object[][] DoctorData() throws Exception 
	{
//		 
//			file = new File("E:\\Patient.xls");	  
//		 
//		  fis = new FileInputStream(file);
//		  wrkbook = new HSSFWorkbook(fis);
//		  shname = wrkbook.getSheetName(0);
//		 
//		  sheet = wrkbook.getSheet(shname);
//
//		  Thread.sleep(2000);	  
//		  rCount = sheet.getPhysicalNumberOfRows()-1;  //1
//		  cCount = sheet.getRow(0).getPhysicalNumberOfCells()-2; //2
//		 System.out.println("Row :"+rCount);
//		 System.out.println("col :"+cCount);
//		  Object[][] data = new Object [rCount][cCount];	  
//		  for(int i=0;i<rCount;i++)
//		  {		  
//			  shRow = sheet.getRow(i+1);
//
//			  for(int j=0;j<cCount;j++)
//			  {
//				  shCells = shRow.getCell(j+1);
//				  System.out.println(shCells);
//				 data[i][j] = shCells.getStringCellValue();	
//			  }
//		  }
////			  fis.close();	  
//		  return data;	
			
			  return new Object[][] {
//			      new Object[] { "Diagnosis Name", "age group", "Medicine Name", "m/a/n", "after", "2.5/4", "Additional details", "notes" },      
			    	new Object[] { "12/03/1990" },
			    	      

			    };
			}
	
	@Test(dataProvider="DoctorData")
	public void Profile(String Date1) throws InterruptedException, IOException, ParseException 
	{					
		
		log.info("Profile Method........!");
		
		d.API_click(drawer);
		Thread.sleep(100);
		List<WebElement> s = driver.findElements(By.id("item_name"));
		s.get(5).click();
		Thread.sleep(500);
		//d.click(profile_img);
//		d.scrollup();
		Thread.sleep(1000);
		//practice Date
//		String pdat= d.getElement(pdate).getText();
//		System.out.println(pdat);
		d.click(pdate);
		Date date= new SimpleDateFormat("dd/MM/yyyy").parse(Date1);

		date1(date);
		

		
}
	
	public void date1(Date dob_date) throws InterruptedException
	{
		 SimpleDateFormat day = new SimpleDateFormat("dd");	  
		  SimpleDateFormat month = new SimpleDateFormat("MMM");
		  SimpleDateFormat year = new SimpleDateFormat("yyyy");
		  String date_day_string = day.format(dob_date),date_month = month.format(dob_date),dob_year = year.format(dob_date);;
		
		int date_yr = Integer.parseInt(dob_year);
//		String date_month = "MAR";
		int date_day = Integer.parseInt(date_day_string);		
		boolean flag1=true;
		boolean flag= true;	
		String curr_month;
		 
		 driver.findElement(By.id("date_picker_year")).click();
		 do
		 { 
			 List<WebElement> month_list = driver.findElements(By.id("month_text_view"));
//			 System.out.println(month_list.size());			 
			 for(int k=1;k<=3;k++)
			 {				 
				 String temp = month_list.get(k).getText();				 
//				 System.out.println(temp);
				 Thread.sleep(100);
				 if(temp.equalsIgnoreCase(Integer.toString(date_yr)))
				 {
					 month_list.get(k).click();
//					System.out.println(month_list.get(k));
					 flag1=false;
					 break;
				 }				 
			}
			 if(flag1==false)
			 {break;}
			 int cur_yr = Integer.parseInt(month_list.get(2).getText());
//			 System.out.println(cur_yr);
//			 if()
			 if(flag1)
				 {
					  Point point = month_list.get(3).getLocation();
					  int startX = point.getX();
					  int startY = point.getY();
					  point = month_list.get(1).getLocation();
					  int endX = point.getX();
					  int endY = point.getY();
					  Thread.sleep(300);
					  if(cur_yr<date_yr) 
					  {d.swipe(startX, startY, endX, endY);}
					  else
					  {d.swipe(endX, endY,startX, startY);}					  
					 }
	}while(flag1);
		 	
		 	Thread.sleep(1500);		
		 	driver.findElement(By.xpath("//android.view.View[@index='0']")).click();	 		
	 		curr_month = driver.findElement(By.id("date_picker_month")).getText();
	 		if(curr_month.equalsIgnoreCase(date_month))
	 		{
	 			flag=false;
	 		}
		 
		 while(flag)
		 	{
			 	Point point = driver.findElement(By.xpath("//android.view.View[@index='27']")).getLocation();
		 		int startX = point.getX();
		 		int startY = point.getY();
		 		point = driver.findElement(By.xpath("//android.view.View[@index='0']")).getLocation();
		 		int endX = point.getX();
		 		int endY = point.getY();
		 		Thread.sleep(1000);		 
		 		driver.swipe(startX, startY, endX, endY, 1500);	
		 		Thread.sleep(800);
		 		driver.findElement(By.xpath("//android.view.View[@index='0']")).click();		 		
		 		curr_month = driver.findElement(By.id("date_picker_month")).getText();
		 		if(curr_month.equalsIgnoreCase(date_month))
		 		{
		 			flag=false;
		 		}		
		 		
		 		Thread.sleep(800);
		 	}
	 		driver.findElement(By.xpath("//android.view.View[@index='"+(date_day-1)+"']")).click();


	}
	
/*	//name
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
	
	
	
	//fee note
	String feenote= d.getElement(feenotes).getText();
	d.clear(feenotes);
	d.type(feenotes,fee_notes);
	d.closekeyboard();
	//day fee
	
	d.clear(dayfee);
	d.type(feenotes,day_fee);
	d.closekeyboard();
	System.out.println("Complete Test Case ");
	
	
	//contact details
	Thread.sleep(500);
	List<WebElement> tab = driver.findElements(By.className("android.support.v7.app.a$f"));
	tab.get(1).click();
	

	//contact address
	String Cadd= d.getElement(Caddress).getText();
	d.clear(Caddress);
	d.type(Caddress,Cadd);
	d.closekeyboard();
	
	//
	String Cpin= d.getElement(Cpincode).getText();
	d.clear(Cpincode);
	d.type(Cpincode,Cpin);
	d.closekeyboard();
	
	String city= d.getElement(Ccity).getText();
	d.clear(Ccity);
	d.type(Ccity,city);
	d.closekeyboard();
	
	
	String personal= d.getElement(Cotherphn).getText();
	d.clear(Cotherphn);
	d.type(Cotherphn,"9970030990");
	d.closekeyboard();
	
	
	
	String achievement= d.getElement(Cachievement).getText();
	d.clear(Cachievement);
	d.type(Cachievement,"achievement");
	d.closekeyboard();*/

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
		
//		  d.device_permission();
//		  d.masterLogin();
		
		//d.init();
	}//beforeclass
	@AfterClass
	public void afterClass() throws InterruptedException 
	  {
		log.info("after class.........!");
//		Thread.sleep(200);
//		d.logout();
//		driver.quit();
	  }

}//CLASS


	
	 /*@DataProvider
	  public Object[][] dp() throws Exception {
	    return new Object[][] {
	      new Object[] { "Rohit5", "abc" },
	      new Object[] { "Rohi5", "abc" },     
	    };*/

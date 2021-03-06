package Clinic_List;

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
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Attendants.Define;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class COPY_old_Prescription_to_new_prescription {

	static Logger log = Logger.getLogger(COPY_old_Prescription_to_new_prescription.class.getName());
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
	int row = 1, col = 7;
	int drow = 1, dcol = 8;
	
	int swipe_count;
	By Appoinment_tab = By.xpath("//android.widget.TextView[@text='Appointment List']");
	By added_name = By.id("clinicsAppointmentList_item_name");
	//copy_prescription
	By parent_list = By.id("textview_prescriptionmedicinedate");
	
	By prescription_Date = By.id("txt_prescription_date");
	By cpy_btn = By.id("btnCopy");
	
	
	@DataProvider
	public Object[][] dp_Clinic_List() {
		return new Object[][] { new Object[] { "19/03/2018", "Arjun rampal", "MNF's Ratna Clinic" } };
	}
	@Test(priority = 1, enabled = true)
	public void copy_prescription()throws ParseException, InterruptedException {
		log.info("visit copy_prescription...! ");
		
		swipe_count=0;
		
	
		  List<WebElement> opd = driver.findElements(prescription_Date);
		  System.out.println(opd.size());
		  boolean flag = true;
		  do
		  {
			  for(WebElement we:opd)
			  {
				  String temp = we.getText();
				  System.out.println(temp.trim());
				  if(temp.equalsIgnoreCase("09"))
				  {
					  Thread.sleep(2000);
					  we.click();
					  Thread.sleep(1000);
					  d.click(cpy_btn);
					  
					  //flag=false;
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
//			  System.out.println(startX +" a "+ startY);
//			  System.out.println( endX +" b "+ endY);
			  d.swipe(startX, startY, endX, endY);
			  swipe_count++;
			  
			  }
		  	}while(flag);	  		
		
	}

	@Test(dataProvider = "dp_Clinic_List", priority = 0, enabled = true)
	public void get_Clinic_List(String Date, String targetname, String Clinic)
			throws ParseException, InterruptedException {
		log.info("visit get_Clinic_List method...! ");
		int dateavailable = 0;
		List<WebElement> ee = driver.findElements(By.id("clinics_list_ListHeader_text"));
		System.out.println(ee.size());

		Thread.sleep(5000);
		ee.get(0).click();
		for (WebElement da : ee) {
			Date dob_date = new SimpleDateFormat("dd/MM/yyyy").parse(Date);
			SimpleDateFormat day = new SimpleDateFormat("dd");
			SimpleDateFormat month = new SimpleDateFormat("MMM");
			SimpleDateFormat year = new SimpleDateFormat("yyyy");
			String dob_day = day.format(dob_date), dob_month = month.format(dob_date), dob_year = year.format(dob_date);
			String date_expected = dob_day + " " + dob_month + "," + " " + dob_year;

			String date_actual = da.getText();

			if (date_actual.equalsIgnoreCase(date_expected)) {
				da.click();
				break;

			}

		}
		System.out.println("================================ for Clinic========================================");
		List<WebElement> lnner = driver.findElements(By.id("clinics_list_item_name"));
		Thread.sleep(2000);

		for (WebElement linner_list : lnner) {
			String r = linner_list.getText();

			if (r.equalsIgnoreCase(Clinic)) {
				Thread.sleep(1000);
				linner_list.click();
				dateavailable = 1;
				break;
			}

		}
		if (dateavailable == 1) {
			System.out.println("date avaliable...!");
		} else {
			System.out.println("date is not avaliable...!");
		}

		d.click(Appoinment_tab);
		List<WebElement> nm = driver.findElements(added_name);
		boolean present = true;
		int i = 0;
		for (WebElement wenm : nm) {
			// System.out.println(wenm.getText());
			if (wenm.getText().equalsIgnoreCase(targetname)) {
				wenm.click();
				present = false;
				i = 1;
				break;
			}

		}
		if (present == false && i == 1) {
			System.out.println("Element FOUND...!");
		} else {
			System.out.println("Element Not FOUND...!");

		}

	}
	
	
	
	
	@BeforeClass()
	public void beforeClass() throws InterruptedException, IOException {
		DesiredCapabilities capab = new DesiredCapabilities();

		capab.setCapability("BROWSER_NAME", "Android");
		capab.setCapability(MobileCapabilityType.TAKES_SCREENSHOT, "true");
		capab.setCapability("VERSION", "4.4.2");
		capab.setCapability("deviceName", "Itg Guru (SM-G350E)");
		capab.setCapability("platformName", "Android");
		capab.setCapability("appPackage", "innomationtech.doctoplus.doctor");
		capab.setCapability("appActivity", "activity.LoginActivity");
		driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capab);
		d = new Define(driver);
		// d.device_permission();

		// d.masterLogin1();

	}// beforeclass
}

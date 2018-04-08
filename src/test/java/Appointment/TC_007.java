package Appointment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Attendants.Define;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class TC_007 {
	static Logger log = Logger.getLogger(TC_003.class.getName());

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
	boolean bool;

	By drawer = By.className("android.widget.ImageButton");

	// availability_of_Tick (from summery activity)
	By check_allotment = By.id("clinics_summary_doctorImg");
	By clinics_doctor_img = By.id("clinics_summary_doctorImg");
	

	By Appoinment_tab = By.xpath("//android.widget.TextView[@text='Appointment List']");
	By next_patient_nm = By.id("clinics_summary_doctorName");
	By next_patient_Contactno = By.id("clinics_summary_phoneNo_text");
	By next_patient_nextno = By.id("txt_next_number");

	By tick = By.id("ripple_lay_icon_arrow");

	// verify_appoinment
	By tokn_added_name = By.id("clinicsAppointmentList_item_name");
	By tokn_no = By.id("clinicsAppointmentList_item_appointment_no");

	public void availability_of_Tick() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(3000);
		try
		{
			 bool = d.getElement(clinics_doctor_img).isDisplayed();	
		}
		catch(Exception e)
		{
		
		System.out.println("get element   :"+bool);
		//assertEquals(d.getElement(doctor_in_status1).isDisplayed(), false,"next appoinment not allocated....!");
		//assertFalse(bool);
		if(bool==false)
		{
			String next_patient_name = d.getElement(next_patient_nm).getText();
			String next_patient_Phone = d.getElement(next_patient_Contactno).getText();
			String next_patient_next = d.getElement(next_patient_nextno).getText();

			System.out.println("next_patient_name    :  " + next_patient_name);
			System.out.println("next_patient_Phone   :" + next_patient_Phone);
			System.out.println("next_patient_next    :" + next_patient_next);

			d.click(Appoinment_tab);
			List<WebElement> tickpresent = driver.findElements(tick);

			System.out.println("=============AAPPOINMENT LIST===========");

			List<WebElement> getname = driver.findElements(tokn_added_name);

			for (WebElement toclick : getname) {

				if (toclick.getText().equalsIgnoreCase(next_patient_name)) {
					System.out.println("able to verify......!");
					if (tickpresent.get(0).isDisplayed()) {
						System.out.println("Next appointment allocated to  : " + next_patient_name);
					} else {
						System.out.println("NOt able to find  :" + next_patient_name);
					}

					break;
				}
				

			}
		}
		else{
			System.out.println("next appoinment not allocated....!");
			
		}
		}		

	}

	@DataProvider
	public Object[][] dp() {
		return new Object[][] { new Object[] { "08/03/2018" } };
	}

	@Test(dataProvider = "dp")
	public void Appointment_TC_03(String Date) throws InterruptedException, IOException, ParseException {

		get_Clinic_List(Date);
		availability_of_Tick();

	}

	public void get_Clinic_List(String Date) throws ParseException, InterruptedException {
		List<WebElement> ee = driver.findElements(By.id("clinics_list_ListHeader_text"));
		// System.out.println("Total Size :" + ee.size());
		Thread.sleep(4000);

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
		System.out.println("================================Inner LIst========================================");
		List<WebElement> lnner = driver.findElements(By.id("clinics_list_item_name"));
		Thread.sleep(2000);
		// System.out.println(lnner.size());

		for (WebElement linner_list : lnner) {
			String r = linner_list.getText();

			if (r.equalsIgnoreCase("Rohit Clinic")) {
				linner_list.click();
				break;

			}

		}

	}

	@BeforeClass
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

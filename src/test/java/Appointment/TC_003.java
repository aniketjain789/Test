package Appointment;

import static org.testng.Assert.assertEquals;

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

public class TC_003 {
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

	By drawer = By.className("android.widget.ImageButton");

	// AppointmentList
	By Appoinment_tab = By.xpath("//android.widget.TextView[@text='Appointment List']");
	By addnewtoken = By.id("img_addNewToken");

	// Add_New_Appointment
	By group = By.id("clinics_addtoken_et_group");
	By Name = By.id("clinics_addtoken_et_name");
	By contact_no = By.id("clinics_addtoken_et_phoneNo");
	By address = By.id("addtoken_address_edt");
	By male = By.id("linics_addtoken_gender_m");
	By female = By.id("clinics_addtoken_gender_f");
	By tokn_age = By.id("clinics_addtoken_et_age");
	By tokn_amt = By.id("edt_amount");

	// verify_appoinment
	By tokn_added_name = By.id("dialog_addToken_name");
	By tokn_no = By.id("dialog_addToken_token_no");
	By tokn_estimatetime = By.id("dialog_addToken_estimated_time");
	By txt_cancel = By.id("txt_cancel");

	public void verify_appoinment() {
		String getname = d.getElement(tokn_added_name).getText();
		String gettokn_no = d.getElement(tokn_no).getText();
		String get_estimatetime = d.getElement(tokn_estimatetime).getText();

		System.out.println(getname);
		System.out.println(gettokn_no);
		System.out.println(get_estimatetime);
		d.click(txt_cancel);
		                   
		List<WebElement> actualtxtname = driver.findElements(By.className("android.widget.TextView"));
		List<WebElement> actualtxt_token = driver.findElements(By.className("clinicsAppointmentList_item_appointment_no"));
		
		
		//check with token
		for (WebElement txt : actualtxt_token) {
			System.out.println(txt.getText());
			if(txt.getText().equalsIgnoreCase(gettokn_no))
			{
				assertEquals(txt.getText(), gettokn_no, "we get txt");
				//break;
			}
		}
		//check with name
	/*for (WebElement txt : actualtxtname) {
			System.out.println(txt.getText());
			if(txt.getText().equalsIgnoreCase(getname))
			{
				assertEquals(txt.getText(), getname, "we get txt");
				break;
			}
		}*/
		

	}

	@Test(dataProvider = "dp")
	public void Appointment_TC_03(String Date,String group, String name, String contact, String gender, String address, String age,
			String amt) throws InterruptedException, IOException, ParseException {

		get_Clinic_List(Date);
		AppointmentList();
		Add_New_Appointment(group, name, contact, gender, address, age, amt);
		verify_appoinment();

	}

	@DataProvider
	public Object[][] dp() {
		return new Object[][] { new Object[] {"12/03/2018", "Group-rajesh", "rajesh", "9876543219", "pune", "F", "22", "200" } };
	}

	public void Add_New_Appointment(String grp, String name, String contact, String addres, String gender, String age,
			String amt) throws InterruptedException {

		d.type(group, grp);
		d.click(group);
		d.type(Name, name);
		d.click(Name);
		d.type(contact_no, contact);
		d.click(contact_no);
		d.type(address, addres);

		switch (gender) {
		case "M":
			d.click(male);
			break;
		case "F":
			d.click(female);
			break;
		}

		d.type(tokn_age, age);
		d.closekeyboard();
		d.click(tokn_amt);
		d.type(tokn_amt, amt);
		d.closekeyboard();
		// for scroll
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"ADD\"));")
				.click();
	}

	public void AppointmentList() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(3000);
		d.click(Appoinment_tab);
		d.click(addnewtoken);

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
		System.out.println(lnner.size());

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

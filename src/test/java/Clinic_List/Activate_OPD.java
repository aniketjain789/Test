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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Attendants.Define;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class Activate_OPD {
	static Logger log = Logger.getLogger(Activate_OPD.class.getName());
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

	By Appoinment_tab = By.xpath("//android.widget.TextView[@text='Appointment List']");
	By added_name = By.id("clinicsAppointmentList_item_name");

	// Activate_OPD1
	By cancel_opd = By.id("cancel_opd_box_Text");
	By y_btn = By.id("btn_ok");
	By c_btn = By.id("btn_cancel");
	By activate_OPD = By.id("activate_opd_box_Text");
	By OPD_time = By.id("txt_time");
	By doc_avilable = By.xpath("//android.widget.TextView[@text='IN']");
	By doc_NOTavilable = By.id("//android.widget.TextView[@text='NOT IN']");
	By ok = By.id("ok");
	By set_max = By.id("icon_editMaxAllowdToken");
	By max_count = By.id("txt_max_patient_count");

	By count_plus = By.id("icon_plus");
	By count_minus = By.id("icon_minus");

	@DataProvider
	public Object[][] dp_Clinic_List() {
		return new Object[][] { new Object[] { "21/03/2018", "MNF's Ratna Clinic" } };
	}

	@Test(priority = 1, enabled = true)
	public void Activate_OPD1() {
		try {
			if (driver.findElement(cancel_opd).isDisplayed()) {
				System.out.println("OPD is OPENED....!");
				System.out.println("OPD Started At :" + driver.findElement(OPD_time).getText());

				// d.click(cancel_opd);
				// d.click(y_btn);

			}

		} catch (Exception e) {
			if (driver.findElement(activate_OPD).isDisplayed()) {
				System.out.println("OPD is closed....!");
				d.click(activate_OPD);

			}
		}

	}

	@Test(priority = 2, enabled = false)
	public void doc_IN_out() {
		driver.findElement(doc_avilable).click();
		d.click(ok);
		System.out.println("Doctor is IN");

		// driver.findElement(doc_NOTavilable).click();
		// d.click(ok);
		// System.out.println("Doctor is NOT IN");

	}

	public void loop(By locator, int scount, int ecount) throws InterruptedException {
		for (int i = scount; i < ecount; i++) {
			d.click(locator);
			Thread.sleep(100);
		}
	}

	@Test(priority = 3, enabled = true)
	public void set_max_Allowed() throws InterruptedException {

		d.click(set_max);
		int systollic = Integer.parseInt(driver.findElement(max_count).getText()); // 120

		int loop = Integer.parseInt("20") - systollic;
		if (loop > 0) {
			loop(count_plus, 0, loop);
		} else {
			loop(count_minus, loop, 0);
		}
	}

	@Test(dataProvider = "dp_Clinic_List", priority = 0, enabled = true)
	public void get_Clinic_List(String Date, String Clinic) throws ParseException, InterruptedException {
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

package Clinic_List;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Attendants.Define;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class Edit_medical_Reports_added_by_Doctor {
	static Logger log = Logger.getLogger(Edit_medical_Reports_added_by_Doctor.class.getName());

	AndroidDriver<WebElement> driver;
	Define d;

	By Appoinment_tab = By.xpath("//android.widget.TextView[@text='Appointment List']");
	By added_name = By.id("clinicsAppointmentList_item_name");
	By cancle_btn = By.id("actionbar_icon_cancel");
	By Record_name = By.id("txtRecordName");
	By Record_bck_btn = By.id("actionbar_icon_back");
	By Edit_btn = By.id("actionbar_icon_edit");

	@DataProvider
	public Object[][] dp_Clinic_List() {
		return new Object[][] { new Object[] { "22/03/2018", "MNF's Ratna Clinic" } };
	}

	@Test(priority = 1, enabled = true)
	public void Report_added_by_Doctor() throws InterruptedException {
		log.info("visit Report_added_by_Doctor...! ");
		List<WebElement> nm = driver.findElements(added_name);
		System.out.println(nm.size());

		Boolean flag = true;
		for (WebElement wenm : nm) {
			Thread.sleep(2000);
			System.out.println("Patient Name :" + wenm.getText());
			wenm.click();

			List<WebElement> gettab = driver.findElements(By.id("tab_text"));
			gettab.get(2).click();// report
			do {
				List<WebElement> records = driver.findElements(Record_name);
				Thread.sleep(1000);

				for (WebElement Record_we : records) {
					Thread.sleep(1000);
					System.out.print("This Report added by Doctor  :" + Record_we.getText());
					Record_we.click();
					Thread.sleep(2000);

					try {

						driver.findElement(Edit_btn).isDisplayed();

						System.out.println(": No");
					}

					catch (Exception e) {
						System.out.println(": Yes");

					}

					d.click(Record_bck_btn);

				}
				d.click(cancle_btn);
				flag = false;
			} while (flag);

		}

	}

	@Test(dataProvider = "dp_Clinic_List", priority = 0, enabled = true)
	public void get_Clinic_List(String Date, String Clinic) throws ParseException, InterruptedException {

		int dateavailable = 0;
		List<WebElement> ee = driver.findElements(By.id("clinics_list_ListHeader_text"));

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
		// System.out.println("================================ for
		// Clinic========================================");
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
		// d.masterLogin(7);

	}// beforeclass

}

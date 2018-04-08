package AddgenericinfowithAllergies;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

public class Add_Edit_Delete_Medicine {

	static Logger log = Logger.getLogger(Add_Edit_Delete_Medicine.class.getName());
	AndroidDriver<WebElement> driver;
	Define d;
	
	By opentab = By.className("android.widget.ImageButton");
	By tab_list = By.id("item_name");

	List<String> medicine_list = new ArrayList<String>();
	By Appoinment_tab = By.xpath("//android.widget.TextView[@text='Appointment List']");
	By added_name = By.id("clinicsAppointmentList_item_name");

	// Add_medicine
	By medicinename = By.id("edittext_medicinename");

	By sunhalf = By.id("btn_sunhalf");
	By sunfull = By.id("btn_sunfull");
	By btnmoon = By.id("btn_moon");

	By swipenone = By.id("swipe_none");
	By swipebfood = By.id("swipe_before_food");
	By swipeafood = By.id("swipe_after_food");

	By unit = By.id("medicine_quantity_edt");
	By unit_plus = By.id("q_plus_img");
	By unit_minus = By.id("q_minus_img");

	By days = By.id("medicine_days_edt");
	By days_plus = By.id("d_plus_img");
	By days_minus = By.id("d_minus_img");

	By additional_note = By.id("edt_additional_note");
	By editnotes = By.id("edittext_notes");
	By save_med_btn = By.id("btn_right");
	By save = By.id("actionbar_icon_save");

	// Edit Medicine
	By find_medicine = By.id("edittext_medicinename");
	By edit_btn = By.id("btn_edit");
	By del_btn = By.id("btn_cross");
	By yes_btn = By.id("btn_ok");

	@DataProvider
	public Object[][] dp_Clinic_List() {
		return new Object[][] { new Object[] { "24/03/2018", "Ram Abc", "MNF's Ratna Clinic" } };
	}

	@DataProvider
	public Object[][] dp_Add_medicine() {
		return new Object[][] {
				new Object[] { "Dporine", "e", "after", "9/7", "take medicine with little hot water", "Take a rest" } };
	}

	@DataProvider // here you need to pass medicine name
	public Object[][] dp_Edit_medicine() {
		return new Object[][] {
				new Object[] { "Dporine", "e", "after", "9/7", "take medicine with little hot water", "Take a rest" } };
	}

	@Test(priority = 3, enabled = false)
	public void Delete_medicine() throws ParseException, InterruptedException {
		Thread.sleep(1000);

		try {
			driver.findElementByAndroidUIAutomator(
					"new UiScrollable(new UiSelector()).scrollIntoView(text(\"Additional details\"));");
		} catch (Exception e) {
			Thread.sleep(2000);
			// to find medicine
			List<WebElement> find = driver.findElements(find_medicine);
			System.out.println("find  :" + find.size());
			// for edit btn
			List<WebElement> del_btn_click = driver.findElements(del_btn);
			System.out.println("get value from ADD method" + medicine_list);

			int i = 0;
			for (WebElement we : find) {
				if (we.getText().equalsIgnoreCase(medicine_list.get(0))) {
					System.out.println("i  :" + i);
					System.out.println(we.getText());
					del_btn_click.get(i - 1).click();
					d.click(yes_btn);
					break;
				} else {
					d.scrollDown();
				}

				i++;
			}

			// here code is required to edit medicines
		}

	}

	@Test(dataProvider = "dp_Edit_medicine", priority = 2, enabled = true)
	public void Edit_medicine(String med_name, String time, String when, String unit_days, String additional_Notes,
			String Notes) throws ParseException, InterruptedException {
		Thread.sleep(1000);

		try {
			driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Additional details\"));");
		} catch (Exception e) {
			Thread.sleep(2000);
			// to find medicine
			List<WebElement> find = driver.findElements(find_medicine);
			System.out.println("find  :" + find);
			// for edit btn
			List<WebElement> edit_btn_click = driver.findElements(edit_btn);
			System.out.println("get value from ADD method" + medicine_list);

			int i = 0;
			for (WebElement we : find) {
				if (we.getText().equalsIgnoreCase("Tab One")) {
					System.out.println("i  :" + i);
					System.out.println(we.getText());
					edit_btn_click.get(i - 1).click();
					break;
				} else {
					d.scrollDown();
				}

				i++;
			}

			// here code is required to edit medicines
		}

	}

	@Test(dataProvider = "dp_Add_medicine", priority = 1, enabled = true)
	public void Add_medicine(String med_name, String time, String when, String unit_days, String additional_Notes,
			String Notes) throws ParseException, InterruptedException {
		
		d.click(medicinename);
		d.type(medicinename, med_name);
		medicine_list.add(med_name);
		d.closekeyboard();
		String arr1[] = time.split("/");
		for (int i = 0; i < arr1.length; i++) {
			switch (arr1[i]) {
			case "m":
				d.click(sunhalf);
				break;
			case "a":
				d.click(sunfull);
				break;
			case "e":
				d.click(btnmoon);
				break;

			}
			switch (when) {
			case "none":
				d.click(swipenone);
				break;
			case "after":
				d.click(swipeafood);
				break;
			case "before":
				d.click(swipebfood);
				break;
			}

		}

		String arr2[] = unit_days.split("/");

		int systollic = Integer.parseInt(driver.findElement(unit).getText()); // 120
		int diastolic = Integer.parseInt(driver.findElement(days).getText());// 80

		int loop = Integer.parseInt(arr2[0]) - systollic;
		if (loop > 0) {
			loop(unit_plus, 0, loop);
		} else {
			loop(unit_minus, loop, 0);
		}
		loop = Integer.parseInt(arr2[1]) - diastolic;
		if (loop > 0) {
			loop(days_plus, 0, loop);
		} else {
			loop(days_minus, loop, 0);
		}

		// notes
		d.type(additional_note, additional_Notes);
		d.closekeyboard();
		// d.type(editnotes, Notes);

		d.click(save_med_btn);
		d.click(save);
	}

	@Test(dataProvider = "dp_Clinic_List", priority = 0, enabled = true)
	public void get_Clinic_List(String Date, String targetname, String Clinic)
			throws ParseException, InterruptedException {
		log.info("visit get_Clinic_List method...! ");
		int dateavailable = 0;
		List<WebElement> ee = driver.findElements(By.id("clinics_list_ListHeader_text"));
		System.out.println(ee.size());
		Date date = Calendar.getInstance().getTime();
		// Display a date in day, month, year format
	    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	    String today = formatter.format(date);

		Thread.sleep(5000);
		ee.get(0).click();
		for (WebElement da : ee) {
			Date dob_date = new SimpleDateFormat("dd/MM/yyyy").parse(today);
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

	public void loop(By locator, int scount, int ecount) throws InterruptedException {
		for (int i = scount; i < ecount; i++) {
			d.click(locator);
			Thread.sleep(100);
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

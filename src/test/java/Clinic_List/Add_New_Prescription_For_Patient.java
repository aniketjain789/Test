package Clinic_List;

import java.io.IOException;
import java.net.URL;
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

public class Add_New_Prescription_For_Patient {
	static Logger log = Logger.getLogger(Add_New_Prescription_For_Patient.class.getName());
	AndroidDriver<WebElement> driver;
	Define d;

	By opentab = By.className("android.widget.ImageButton");
	By tab_list = By.id("item_name");
	By add_prescrip = By.id("actionbar_icon_add");
	By txtPatientName = By.id("txtPatientName");

	// Edit_Prescription
	By BP_Btn = By.id("bp_txt");
	By systolic_count = By.id("txt_max_patient_count_systolic");
	By plus_systolic = By.id("icon_plus_systolic");
	By minus_systolic = By.id("icon_minus_systolic");

	By diastolic_count = By.id("txt_max_patient_count_diastolic");
	By plus_diastolic = By.id("icon_plus_diastolic");
	By minus_diastolic = By.id("icon_minus_diastolic");

	By bp_ok = By.id("btn_ok");
	By filter = By.id("filter_age_limit");
	
	
	By medicinename = By.id("edittext_medicinename");
	
	By sunhalf = By.id("btn_sunhalf");
	By sunfull = By.id("btn_sunfull");
	By btnmoon = By.id("btn_moon");

	By swipenone = By.id("swipe_none");
	By swipebfood = By.id("swipe_before_food");
	By swipeafood = By.id("swipe_after_food");

	By unit = By.id("medicine_quantity_edt");
	By days = By.id("medicine_days_edt");

	By additional_note = By.id("edt_additional_note");
	By editnotes = By.id("edittext_notes");
	By save_med_btn = By.id("btn_right");
	By save = By.id("actionbar_icon_save");

	@DataProvider
	public Object[][] dp_add_Prescription() {
		return new Object[][] { new Object[] { "Ankush Katkar", "122/150", "Disporine", "e", "after", "4/5",
				"take medicine with little hot water", "Take a rest" } };
	}

	@Test(dataProvider = "dp_add_Prescription")
	public void add_new_prescription_For_Patient(String name, String bpcount, String med_name, String time, String when,
			String unit_days, String additional_Notes, String Notes) throws InterruptedException, IOException {

		log.info("add_new_prescription_For_Patient.....!");
		Thread.sleep(2000);
		d.click(opentab);
		List<WebElement> list = driver.findElements(tab_list);
		Thread.sleep(2000);
		list.get(1).click();// Patient

		List<WebElement> name_list = driver.findElements(txtPatientName);

		int del = 0;
		for (WebElement we : name_list) {
			System.out.println();
			if (we.getText().equalsIgnoreCase(name)) {
				name_list.get(del).click();
				break;
			}
			del++;
		}

		d.click(add_prescrip);
		
		//filter
		List<WebElement> list_filter = driver.findElements(filter);
		list_filter.get(1).click();
			
		//BP
		d.click(BP_Btn);
		By systolic_count = By.id("txt_max_patient_count_systolic");
		By diastolic_count = By.id("txt_max_patient_count_diastolic");

		String[] sys_dia = bpcount.split("/");// 122/150
		int systollic = Integer.parseInt(driver.findElement(systolic_count).getText()); // 120
		int diastolic = Integer.parseInt(driver.findElement(diastolic_count).getText());// 80

		int loop = Integer.parseInt(sys_dia[0]) - systollic;
		if (loop > 0) {
			loop(plus_systolic, 0, loop);
		} else {
			loop(minus_systolic, loop, 0);
		}
		loop = Integer.parseInt(sys_dia[1]) - diastolic;
		if (loop > 0) {
			loop(plus_diastolic, 0, loop);
		} else {
			loop(minus_diastolic, loop, 0);
		}
		d.click(bp_ok);
		
		//medicine
		d.click(medicinename);
		d.type(medicinename, med_name);
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
		// unit
		d.click(unit);
		d.clear(unit);
		d.type(unit, arr2[0]);
		d.closekeyboard();

		// days
		d.click(days);
		d.clear(days);
		d.type(days, arr2[1]);
		d.closekeyboard();
		// notes
		d.type(additional_note, additional_Notes);
		d.closekeyboard();
		d.type(editnotes, Notes);
		d.click(save_med_btn);
		d.click(save);

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

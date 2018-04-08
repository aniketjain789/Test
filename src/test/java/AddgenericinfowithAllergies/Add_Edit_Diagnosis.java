package AddgenericinfowithAllergies;

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
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Attendants.Define;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class Add_Edit_Diagnosis {

	static Logger log = Logger.getLogger(Add_Edit_Diagnosis.class.getName());
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

	By Open_tab = By.className("android.widget.ImageButton");
	By item_name = By.id("item_name");
	By add = By.id("menu_dadd");
	List<String> medicine_list = new ArrayList<String>();

	By d_name = By.id("diagnosis_name_edt");
	By d_g_name = By.id("diagnosis_age_group_edt");
	By d_medicine_name = By.id("edittext_medicinename");

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

	By cancle_btn = By.id("actionbar_icon_cancel");

	// Delete_Diagnosis
	By search = By.id("edt_search");
	By dia_name = By.id("diagnosis_name_txt");
	By d_delete = By.id("icon_delete");
	By btn_cancel = By.id("btn_cancel");
	By y_btn = By.id("btn_ok");

	@DataProvider
	public Object[][] dp_Delete_Diagnosis() {
		return new Object[][] { new Object[] { "Eye" } };
	}

	@DataProvider
	public Object[][] dp_Add_Diagnosis() {
		return new Object[][] { new Object[] { "Bone Density Study", "adult", "dispirine", "e", "after", "9/7",
				"take medicine with little hot water", "Take a rest" } };
	}

	@DataProvider
	public Object[][] dp_Edit_Diagnosis() {
		return new Object[][] { new Object[] { "xyz", "Density Study", "Child", "dispirine123", "a", "after", "3/5",
				"take medicine with little hot water", "Take a rest" } };
	}

	@Test(dataProvider = "dp_Add_Diagnosis", enabled = true, priority = 1)
	public void Add_Diagnosis(String D_name, String age_grp, String medicine_nm, String time, String when,
			String unit_days, String additional_Notes, String Notes) throws InterruptedException, IOException {
		log.info("Add_Diagnosis Method .....!");
		d.click(Open_tab);
		List<WebElement> list = driver.findElements(item_name);
		System.out.println(list.size());
		list.get(3).click();// Diagnosis
		d.click(add);
		d.type(d_name, D_name);
		medicine_list.add(D_name);
		d.type(d_g_name, age_grp);
		d.type(d_medicine_name, medicine_nm);
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

		if (driver.findElement(cancle_btn).isDisplayed()) {
			System.out.println(medicine_list + "Element present is alredy present....!");
			driver.findElement(cancle_btn).click();
		} else {
			System.out.println("Element is present.....!");
		}

	}

	public void loop(By locator, int scount, int ecount) throws InterruptedException {
		for (int i = scount; i < ecount; i++) {
			d.click(locator);
			Thread.sleep(100);
		}
	}

	@Test(dataProvider = "dp_Edit_Diagnosis", enabled = true, priority = 2)
	public void Edit_Diagnosis(String Target_name, String D_name, String age_grp, String medicine_nm, String time,
			String when, String unit_days, String additional_Notes, String Notes)
			throws InterruptedException, IOException {

		log.info("Edit_Diagnosis Method .....!");
		d.click(Open_tab);
		List<WebElement> list = driver.findElements(item_name);
		list.get(3).click();// Diagnosis

		// d.type(search,diagnosis_name);
		List<WebElement> name = driver.findElements(dia_name);

		int k = 0;
		boolean flag = false;
		
		for (WebElement danme : name) {
			Thread.sleep(1000);
			
			
			if (danme.getText().equalsIgnoreCase(Target_name)) {
				name.get(k).click();
				flag = true;
				break;

			} else {
				d.scrollDown();
			}

			k++;

		}

		if (flag == false) {
			System.out.println("Record not found");

		}

		// edit
		d.type(d_name, D_name);
		medicine_list.add(D_name);
		d.type(d_g_name, age_grp);
		d.click(d_g_name);
		d.type(d_medicine_name, medicine_nm);
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
		// check record present or not
		if (driver.findElement(cancle_btn).isDisplayed()) {
			System.out.println(medicine_list + "Element present is alredy present....!");
			driver.findElement(cancle_btn).click();
		} else if (driver.findElement(Open_tab).isDisplayed()) {
			System.out.println("Element is present.....!");
		}
	}

	@Test(priority = 3, enabled = false)
	public void Delete_Diagnosis() throws InterruptedException, IOException {

		log.info("Delete_Diagnosis Method .....!");
		d.click(Open_tab);
		List<WebElement> list = driver.findElements(item_name);
		list.get(3).click();// Diagnosis

		// d.type(search,diagnosis_name);
		List<WebElement> name = driver.findElements(dia_name);

		// for delete
		List<WebElement> del_icon = driver.findElements(d_delete);

		System.out.println(medicine_list);

		int i = 0;
		for (WebElement danme : name) {
			Thread.sleep(1000);
			System.out.println(danme.getText());
			if (danme.getText().equalsIgnoreCase(medicine_list.toString())) {
				del_icon.get(i).click();
				Thread.sleep(2000);
				d.click(btn_cancel);
				// d.click(y_btn);
				break;
			}
			i++;
			System.out.println(i);
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

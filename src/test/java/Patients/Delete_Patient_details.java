package Patients;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
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

public class Delete_Patient_details {

	static Logger log = Logger.getLogger(Delete_Patient_details.class.getName());
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

	By opentab = By.className("android.widget.ImageButton");
	By tab_list = By.id("item_name");
	By search = By.id("edt_search");
	By txtPatientName = By.id("txtPatientName");
	By del_btn = By.id("icon_delete");

	@DataProvider
	public Object[][] dp_Delete_petients_Details() {
		return new Object[][] { new Object[] { "Ankush Katkar" } };
	}

	@Test(dataProvider = "dp_Delete_petients_Details", priority = 1, enabled = true)
	public void Delete_petients_Details(String del_name) throws ParseException, InterruptedException {
		log.info("Delete_petients_Details.....!");
		Thread.sleep(2000);
		d.click(opentab);
		List<WebElement> list = driver.findElements(tab_list);
		Thread.sleep(2000);
		list.get(1).click();// petiants
		Thread.sleep(1000);
		d.type(search, del_name);

		List<WebElement> name_list = driver.findElements(txtPatientName);
		List<WebElement> del_btn_list = driver.findElements(del_btn);

		int del = 0;
		for (WebElement we : name_list) {
			System.out.println();
			if (we.getText().equalsIgnoreCase(del_name)) {
				del_btn_list.get(del).click();
				break;
			}
			del++;
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

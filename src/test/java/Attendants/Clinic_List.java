package Attendants;

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
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class Clinic_List {
	static Logger log = Logger.getLogger(Clinic_List.class.getName());

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

	public void call() throws InterruptedException, ParseException {
		// d.API_click(drawer);

		List<WebElement> ee = driver.findElements(By.id("clinics_list_ListHeader_text"));
		System.out.println("Total Size   :" + ee.size());
		Thread.sleep(4000);
		ee.get(0).click();
		for (WebElement da : ee) {
			Date dob_date = new SimpleDateFormat("dd/MM/yyyy").parse("11/03/2018");
			SimpleDateFormat day = new SimpleDateFormat("dd");
			SimpleDateFormat month = new SimpleDateFormat("MMM");
			SimpleDateFormat year = new SimpleDateFormat("yyyy");
			String dob_day = day.format(dob_date), dob_month = month.format(dob_date), dob_year = year.format(dob_date);
			String date_expected= dob_day + " " + dob_month + "," + " "+dob_year;
			//System.out.println("DOB:- " + dob_day + " " + dob_month + "," + dob_year);
			String date_actual = da.getText();
			
			if (date_actual.equalsIgnoreCase(date_expected)) {
				
				da.click();

				System.out.println("================================Inner LIst========================================");
				List<WebElement> lnner = driver.findElements(By.id("clinics_list_item_name"));
				System.out.println(lnner.size());

				for (WebElement linner_list : lnner) {
					String r = linner_list.getText();
					System.out.println(linner_list.getText());
					if (r.equalsIgnoreCase("Rohit Clinic")) {
						linner_list.click();
						System.out.println("CLICKED...........!");
					}

				}
				break;
			}

		}
	}

	@Test // (dataProvider = "dp")
	public void test_reports() throws InterruptedException, IOException, ParseException {

		call();
		
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

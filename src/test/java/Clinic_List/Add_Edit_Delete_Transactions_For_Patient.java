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
import Patients.Delete_Patient_details;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class Add_Edit_Delete_Transactions_For_Patient {
	static Logger log = Logger.getLogger(Delete_Patient_details.class.getName());
	AndroidDriver<WebElement> driver;
	Define d;
	By Appoinment_tab = By.xpath("//android.widget.TextView[@text='Appointment List']");
	By opentab = By.className("android.widget.ImageButton");
	By tab_list = By.id("item_name");
	By addbtn = By.id("actionbar_icon_add");
	By txtPatientName = By.id("txtPatientName");
	By search = By.id("edt_search");

	// Accounts
	By date = By.id("icon_date");
	By A_amt = By.id("edtamount");
	By A_Notes = By.id("edtNotes");

	
	
	@DataProvider
	public Object[][] dp_Add_Bill_Amount() {
		return new Object[][] { new Object[] { "22/04/2018" ,"200","PAID"} };
	}

	@Test(dataProvider = "dp_Add_Bill_Amount")
	public void Transaction(String Date,String amt,String notes) throws InterruptedException, IOException, ParseException {

		log.info("add_new_prescription_For_Patient.....!");
		Thread.sleep(2000);
		d.click(opentab);
		List<WebElement> list = driver.findElements(tab_list);
		Thread.sleep(2000);
		list.get(1).click();// accounts

		List<WebElement> name_list = driver.findElements(txtPatientName);

		for (WebElement wname : name_list) {
			System.out.println(wname.getText());
			d.type(search, "Nana Patekar");
			if (wname.getText().equalsIgnoreCase("Nana Patekar")) {
				wname.click();
				break;
			} else {
				d.scrollDown();
			}

		}
		Thread.sleep(2000);

		List<WebElement> gettab = driver.findElements(By.id("tab_text"));
		gettab.get(3).click();// report

		d.click(addbtn);

		d.click(date);

		Date dob_date = new SimpleDateFormat("dd/MM/yyyy").parse(Date);
		d.setDate(dob_date);
		d.type(A_amt, amt);
		d.type(A_Notes, notes);
		
		

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

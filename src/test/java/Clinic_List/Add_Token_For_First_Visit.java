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

public class Add_Token_For_First_Visit {
	
	static Logger log = Logger.getLogger(Add_Token_For_First_Visit.class.getName());
	AndroidDriver<WebElement> driver;
	Define d;
	
	By Appoinment_tab = By.xpath("//android.widget.TextView[@text='Appointment List']");
	By added_name = By.id("clinicsAppointmentList_item_name");
	By newtoken = By.id("ripple_img_addNewToken");
	
	
	// Add_New_Token
		By group = By.id("clinics_addtoken_et_group");
		By Name = By.id("clinics_addtoken_et_name");
		By contact_no = By.id("clinics_addtoken_et_phoneNo");
		By address = By.id("addtoken_address_edt");
		By male = By.id("linics_addtoken_gender_m");
		By female = By.id("clinics_addtoken_gender_f");
		By tokn_age = By.id("clinics_addtoken_et_age");
		By tokn_amt = By.id("edt_amount");
		By Expected_time = By.id("dialog_addToken_estimated_time");
		By cross_btn = By.id("txt_cancel");
		
		By followup_btn = By.id("followUpVisit");
		
	
		
		
	
	@DataProvider
	public Object[][] dp_New_Token() {
		return new Object[][] {
				new Object[] {  "Group-rajesh", "Aniket", "8876543415", "pune", "F", "26", "204" } };
	}
	
	@DataProvider
	public Object[][] dp_follow_up_Token() {
		return new Object[][] {
				new Object[] {"Group-rajesh", "Aniket", "8876543415", "pune", "F", "26", "204" } };
	}
	@DataProvider
	public Object[][] dp_Clinic_List() {
		return new Object[][] { new Object[] { "19/03/2018", "MNF's Ratna Clinic" } };
	}
	
	
	
	@Test(dataProvider = "dp_New_Token", priority = 1, enabled = false)
	public void new_token(String grp, String name, String contact, String addres, String gender, String age,String amt)
	{
		log.info("visit New_Token  method...! ");
		d.click(newtoken);
		
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
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"ADD\"));").click();
		System.out.println("Expected Time for new Token :: "+driver.findElement(Expected_time).getText());
		d.click(cross_btn);
		
	}
	
	
	@Test(dataProvider = "dp_follow_up_Token", priority = 2, enabled = true)
	public void follow_up_token(String grp, String name, String contact, String addres, String gender, String age,String amt)
	{
		log.info("visit follow_up_token  method...! ");
		
		d.click(newtoken);
		
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
		
		d.click(followup_btn);
		// for scroll to add 
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"ADD\"));").click();
		System.out.println("Expected Time for follow up Token :: "+driver.findElement(Expected_time).getText());
		d.click(cross_btn);
	
	}
	
		
	
	
	@Test(dataProvider = "dp_Clinic_List", priority = 0, enabled = true)
	public void get_Clinic_List(String Date, String Clinic)
			throws ParseException, InterruptedException {
		//log.info("visit get_Clinic_List method...! ");
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
			//System.out.println("date avaliable...!");
		} else {
			System.out.println("date is not avaliable...!");
		}

		d.click(Appoinment_tab);
		

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

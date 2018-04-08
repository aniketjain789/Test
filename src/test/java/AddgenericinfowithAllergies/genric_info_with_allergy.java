package AddgenericinfowithAllergies;

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

public class genric_info_with_allergy {

	static Logger log = Logger.getLogger(genric_info_with_allergy.class.getName());

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

	// generic_info
	By Appoinment_tab = By.xpath("//android.widget.TextView[@text='Appointment List']");
	By added_name = By.id("clinicsAppointmentList_item_name");
	By G_name = By.id("edtGroup");
	By P_name = By.id("txtPatientName");
	By Gender_M = By.id("clinics_addtoken_gender_m");
	By Gender_F = By.id("clinics_addtoken_gender_f");
	By age = By.id("txtAge");
	By P_cellno = By.id("txtContactNumber");
	By P_address = By.id("txtAddress");
	By edtAllergi = By.id("edtAllergi");

	// Edit_Prescription
	By BP_Btn = By.id("btn_bp");
	By systolic = By.id("txt_max_patient_count_systolic");
	By diastolic = By.id("txt_max_patient_count_diastolic");

	By plus_systolic = By.id("icon_plus_systolic");
	By minus_systolic = By.id("icon_minus_systolic");
	By plus_diastolic = By.id("icon_plus_diastolic");
	By minus_diastolic = By.id("icon_minus_diastolic");
	By sav_bn = By.id("btn_ok");
	
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
	
	
	
	
	@Test(dataProvider = "dp_Edit_Prescription",enabled=false)
	public void Edit_Prescription(String Date, String Targename, String bpcount,String med_name,String time,String when,String unit_days,String additional_Notes, String Notes)
			throws ParseException, InterruptedException {
		get_Clinic_List(Date);
		d.click(Appoinment_tab);
		List<WebElement> nm = driver.findElements(added_name);

		for (WebElement wenm : nm) {

			if (wenm.getText().equalsIgnoreCase(Targename)) {
				wenm.click();
				break;
			}
		}
		//not working plz have a look
		List<WebElement> gettab = driver.findElements(By.id("tab_text"));
		gettab.get(1).click();// GENRIC INFO
		d.click(BP_Btn);
		String[] sys_dia = bpcount.split("/");
		System.out.println("sys_dia[0]  :"+sys_dia[0]);
		System.out.println("sys_dia[1]"+sys_dia[1]);
		d.type(systolic, sys_dia[0]);
		d.type(diastolic, sys_dia[1]);
		d.click(sav_bn);
		
		d.click(medicinename);
		d.type(medicinename, med_name);
		
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
		d.type(additional_note,additional_Notes);
		d.closekeyboard();
		d.type(editnotes, Notes);
		
	}

	@DataProvider
	public Object[][] dp_Edit_Prescription() {
		return new Object[][] { new Object[] { "09/03/2018", "Reshma Nimbalkar", "22/45","Disporine","e", "after","4/5","take medicine with little hot water","Take a rest"} };
	}

	@Test(dataProvider = "dp_generic_info", enabled = true)
	public void generic_info(String Date, String targetname, String grp, String P_Name, String P_Gender, String P_age,
			String Cell_no, String address, String allergy) throws ParseException, InterruptedException {
		get_Clinic_List(Date);
		d.click(Appoinment_tab);
		List<WebElement> nm = driver.findElements(added_name);
		boolean present = true;
		for (WebElement wenm : nm) {
			// System.out.println(wenm.getText());
			if (wenm.getText().equalsIgnoreCase(targetname)) {
				wenm.click();
				break;
			}

		}
		present = false;
		if (present == false) {
			System.out.println(targetname + ": is not appointed");
		}
		Thread.sleep(2000);
		List<WebElement> gettab = driver.findElements(By.id("tab_text"));

		gettab.get(0).click();// GENRIC INFO
		Thread.sleep(4000);
		/*
		 * d.type(G_name, grp); d.click(G_name); d.type(P_name, P_Name);
		 * d.click(P_name);
		 * 
		 * switch (P_Gender) { case "M": d.click(Gender_M); break; case "F":
		 * d.click(Gender_F); break; }
		 */
		d.click(P_address);
//		d.clear(age);
//		System.out.println(P_age);
		Thread.sleep(1000);


		driver.findElement(age).sendKeys("35");
		d.click(P_cellno);

//		d.type(age, P_age);
//		driver.findElement(By.xpath("//android.widget.EditText[@text='Age']")).sendKeys(P_age);
		d.click(P_address);
		d.type(P_cellno, Cell_no);
		d.click(P_cellno);
		d.type(P_address, address);
		d.click(P_address);

		// driver.findElementByAndroidUIAutomator("new UiScrollable(new
		// UiSelector()).scrollIntoView(text(\"?\"));").click();
		d.type(edtAllergi, allergy);
		d.closekeyboard();
	}

	@DataProvider
	public Object[][] dp_generic_info() {
		return new Object[][] { new Object[] { "14"
				+ "/03/2018", "Reshma Nimbalkar", "Group-a", "Radha Nimbalkar", "M",
				"22", "9865322145", "aurangabad", "Dust Allergy" } };
	}

	public void get_Clinic_List(String Date) throws ParseException, InterruptedException {

		log.info("visit get_Clinic_List method...! ");
		List<WebElement> ee = driver.findElements(By.id("clinics_list_ListHeader_text"));

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

		for (WebElement linner_list : lnner) {
			String r = linner_list.getText();

			if (r.equalsIgnoreCase("Om Sai Clinic")) {
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

	/*
	 * @BeforeSuite public void beforesuite() throws IOException,
	 * InterruptedException { Runtime.getRuntime().
	 * exec("cmd /c start C:\\Users\\aniket.jain\\Desktop\\startappium.bat");
	 * Thread.sleep(9000); }
	 */

}

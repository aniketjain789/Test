package Patients;

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

public class Edit_patient_All {
	static Logger log = Logger.getLogger(Edit_patient_All.class.getName());
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

	// Reports
	By No_medical_record = By.id("please_add_txt");
	By cancle = By.id("actionbar_icon_cancel");
	By icon_Edit = By.id("actionbar_icon_edit");
	By record_Name = By.id("txtRecordName");
	By report_title = By.id("txtReportTitle");
	By Report_Record_Type = By.id("medicalRecordType");
	By Report_icon_date = By.id("icon_date");
	By Report_txtNotes = By.id("txtNotes");
	By Report_nxt_btn = By.id("rippleNextBtn");
	By Report_DONE_Btn = By.id("add_opd_done_bt_container");
	By Report_Back_Btn = By.id("actionbar_icon_back");
	// Genneric info
	By G_name = By.id("edtGroup");
	By P_name = By.id("txtPatientName");
	By Gender_M = By.id("clinics_addtoken_gender_m");
	By Gender_F = By.id("clinics_addtoken_gender_f");
	By age = By.id("txtAge");
	By P_cellno = By.id("txtContactNumber");
	By P_address = By.id("txtAddress");
	By edtAllergi = By.id("edtAllergi");

	// Accounts
	By A_txtgrp = By.id("txt_group_date");
	By A_internal = By.id("lay_main");
	By A_txtdate = By.id("txt_date1");
	By A_txt_status = By.id("txt_status");
	By A_edt_amt = By.id("edtamount");
	By A_edt_notes = By.id("edtNotes");
	By A_edt_save = By.id("actionbar_icon_save");
	By A_actual_rupees = By.id("txt_rupees");

	// Edit_Prescription
	By BP_Btn = By.id("btn_bp");
	By systolic_count = By.id("txt_max_patient_count_systolic");
	By plus_systolic = By.id("icon_plus_systolic");
	By minus_systolic = By.id("icon_minus_systolic");

	By diastolic_count = By.id("txt_max_patient_count_diastolic");
	By plus_diastolic = By.id("icon_plus_diastolic");
	By minus_diastolic = By.id("icon_minus_diastolic");

	By bp_ok = By.id("btn_ok");

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

	@DataProvider
	public Object[][] dp_Clinic_List() {
		return new Object[][] { new Object[] { "08/04/2018", "Ram Abc", "test" } };
	}

	@DataProvider
	public Object[][] dp_Genric_info() {
		return new Object[][] {
				new Object[] { "Group-a", "Radha Nimbalkar", "F", "22", "9865322145", "aurangabad", "Dust Allergy" } };
	}

	@DataProvider
	public Object[][] dp_report() {
		return new Object[][] { new Object[] { "13/03/2018", "Rohit Nimbalkar", "MNF's Ratna Clinic", "Jain's Lab",
				"Jain's Lab", "X-ray", "09/03/2018", "Normal" } };
	}

	@DataProvider
	public Object[][] dp_account() {
		return new Object[][] { new Object[] { "08 Apr 2017", "Bill Amount", "2000", "PAID" } };
	}

	@DataProvider
	public Object[][] dp_Edit_Prescription() {
		return new Object[][] { new Object[] { "122/150", "Disporine", "e", "after", "4/5",
				"take medicine with little hot water", "Take a rest" } };
	}

	public void loop(By locator, int scount, int ecount) throws InterruptedException {
		for (int i = scount; i < ecount; i++) {
			d.click(locator);
			Thread.sleep(100);
		}
	}

	@Test(dataProvider = "dp_Edit_Prescription", priority = 2, enabled = true)
	public void Edit_Prescription(String bpcount, String med_name, String time, String when, String unit_days,
			String additional_Notes, String Notes) throws ParseException, InterruptedException {

		List<WebElement> gettab = driver.findElements(By.id("tab_text"));
		gettab.get(1).click();// prescription

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
		// d.click(select_opd);

		/*
		 * for(int i=sys_actual1;i>sys_expected1;i--) { d.click(minus_systolic);
		 * System.out.println("i th value" +i); if(sys1==sys_expected1) { break;
		 * }
		 * 
		 * } for(int j=sys_actual1;j<sys_expected1;j++) {
		 * d.click(plus_systolic); System.out.println("i th value" +j);
		 * if(sys1==sys_expected1) { break; }
		 * 
		 * }
		 */

		/*
		 * d.click(plus_systolic); d.click(plus_systolic);
		 * d.click(plus_systolic); d.click(plus_systolic);
		 */

		/*
		 * By plus_diastolic = By.id("icon_plus_diastolic"); By minus_diastolic
		 * = By.id("icon_minus_diastolic");
		 * 
		 * 
		 * 
		 * d.type(diastolic, sys_dia[1]); d.click(ok_bn);
		 * 
		 * 
		 * 
		 * d.click(medicinename); d.type(medicinename, med_name);
		 * d.closekeyboard(); String arr1[] = time.split("/"); for (int i = 0; i
		 * < arr1.length; i++) { switch (arr1[i]) { case "m": d.click(sunhalf);
		 * break; case "a": d.click(sunfull); break; case "e": d.click(btnmoon);
		 * break;
		 * 
		 * } switch (when) { case "none": d.click(swipenone); break; case
		 * "after": d.click(swipeafood); break; case "before":
		 * d.click(swipebfood); break; }
		 * 
		 * }
		 * 
		 * String arr2[] = unit_days.split("/"); // unit d.click(unit);
		 * d.clear(unit); d.type(unit, arr2[0]); d.closekeyboard();
		 * 
		 * // days d.click(days); d.clear(days); d.type(days, arr2[1]);
		 * d.closekeyboard(); // notes d.type(additional_note,additional_Notes);
		 * d.closekeyboard(); d.type(editnotes, Notes);
		 */

	}

	@Test(dataProvider = "dp_account", priority = 4, enabled = false)
	public void Edit_patient_Generic_Accounts(String A_date, String A_status, String A_amt, String A_notes)
			throws InterruptedException, IOException, ParseException {
		log.info("visit Edit_patient_Generic_Accounts...! ");

		List<WebElement> gettab = driver.findElements(By.id("tab_text"));
		gettab.get(3).click();// Accounts
		/*
		 * d.scrollDown(); Thread.sleep(2000);
		 * 
		 * //date list List<WebElement> txt_date =
		 * driver.findElements(A_txtdate);
		 * //System.out.println("txt_date size :"+txt_date.size());
		 * 
		 * //status List<WebElement> A_txt_status1 =
		 * driver.findElements(A_txt_status);
		 * //System.out.println("A_txt_status1 size :"+A_txt_status1.size());
		 * 
		 * 
		 * int i=0; for(WebElement we : txt_date) { //webdate String date =
		 * we.getText(); String final_date =
		 * date.replaceAll(":","").replaceAll(",","").trim();
		 * if(final_date.equalsIgnoreCase(A_date)) {
		 * System.out.println("Date is found :"+A_date); String temp2 =
		 * A_txt_status1.get(i).getText();
		 * System.err.println(temp2+"  i value   "+i);
		 * if(temp2.equalsIgnoreCase(A_status)){
		 * System.out.println("in status..!"); Thread.sleep(1000); we.click();
		 * d.type(A_edt_amt, A_amt); d.type(A_edt_notes, A_notes);
		 * d.click(A_edt_save); break; } } i++; }
		 */
	}// for

	@Test(dataProvider = "dp_Genric_info", priority = 1, enabled = false)
	public void Edit_patient_Generic_info(String grp, String P_Name, String P_Gender, String P_age, String Cell_no,
			String address, String allergy) throws ParseException, InterruptedException {
		log.info("visit Edit_patient_Generic_info...! ");
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
		 * 
		 * d.click(age); driver.pressKeyCode(2); /*d.click(P_address);
		 * d.closekeyboard(); d.type(P_cellno, Cell_no); d.click(P_cellno);
		 * d.type(P_address, address); d.closekeyboard(); d.type(edtAllergi,
		 * allergy); d.closekeyboard();
		 */
	}

	@Test(dataProvider = "dp_report", priority = 3, enabled = false)
	public void Edit_patient_Report(String Date, String targetname, String Clinic, String R_search_nm, String R_title,
			String R_type, String R_Date, String R_notes) throws ParseException, InterruptedException {

		log.info("visit Edit_patient_Report...! ");

		List<WebElement> gettab = driver.findElements(By.id("tab_text"));
		gettab.get(2).click();// Report

		/*
		 * List<WebElement> R_Name = driver.findElements(record_Name); boolean
		 * flag = true; for (WebElement we_list : R_Name) {
		 * 
		 * if (we_list.getText().equalsIgnoreCase(R_search_nm)) {
		 * we_list.click(); System.out.println("FOUND....!"); break; }
		 * 
		 * } if (flag == false) { System.out.println("didn't Found record...!");
		 * }
		 * 
		 * d.click(icon_Edit); d.type(report_title, R_title);
		 * d.type(Report_Record_Type, R_type); // d.click(Report_icon_date);
		 * need to do d.type(Report_txtNotes, R_notes); d.click(Report_nxt_btn);
		 * d.click(Report_DONE_Btn); d.click(Report_Back_Btn);
		 */
	}

	@Test(dataProvider = "dp_Clinic_List", priority = 0, enabled = true)
	public void get_Clinic_List(String Date, String targetname, String Clinic)
			throws ParseException, InterruptedException {
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

package Patients;

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

public class Add_Medical_Reports_For_Patient {
	static Logger log = Logger.getLogger(Add_Medical_Reports_For_Patient.class.getName());
	AndroidDriver<WebElement> driver;
	Define d;
	
	
	By opentab = By.className("android.widget.ImageButton");
	By tab_list = By.id("item_name");
	By txtPatientName = By.id("txtPatientName");
	By add_report = By.id("actionbar_icon_add");
	
	By No_medical_record = By.id("please_add_txt");
	By cancle = By.id("actionbar_icon_cancel");
	By icon_add = By.id("actionbar_icon_add");
	By report_title = By.id("txtReportTitle");
	By Report_Record_Type = By.id("medicalRecordType");
	By Report_icon_date = By.id("icon_date");
	By Report_txtNotes = By.id("txtNotes");
	By Report_nxt_btn = By.id("rippleNextBtn");
	
	By upd_btn = By.id("btn_upload");
	By image = By.id("image");
	By img_done = By.id("menu_done");
	By add_caption = By.id("edt");
	By save_img = By.id("btn2");
	By add_container = By.id("add_opd_tv_done_text");
	//By save_Report = By.id("actionbar_icon_save");
	//edit
	By record_Name = By.id("txtRecordName");
	By arrowUp = By.id("icon_arrowUp");
	By caption_edt = By.id("edt");
	
	By del_icon = By.id("icon_delete");
	By del_cancel = By.id("btn_cancel");
	By del_ok = By.id("btn_ok");
	
	
	
	@DataProvider
	public Object[][] dp_add_patient_Details() {
		return new Object[][] { new Object[] { "Ankush Katkar","Jain's Lab","X-ray", "04/02/2018", "Normal" } };
	}

	@Test(dataProvider="dp_add_patient_Details" ,enabled=true)
	public void Medical_Reports_For_Patient(String name,  String R_title,
			String R_type, String R_Date, String R_notes) throws InterruptedException, IOException, ParseException {
	
		
		log.info("Add_Reports_For_Patient.....!");
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
		
		
		List<WebElement> gettab= driver.findElements(By.id("tab_text"));
		gettab.get(2).click();// Report
		d.click(add_report);
		
		d.type(report_title, R_title);
		d.type(Report_Record_Type, R_type);
		
		//date
		d.click(Report_icon_date); 
		Date dob_date= new SimpleDateFormat("dd/MM/yyyy").parse(R_Date);
		d.setDate(dob_date);
		
		d.type(Report_txtNotes, R_notes);
		d.click(Report_nxt_btn);
		d.click(upd_btn);
		List<WebElement> img_list = driver.findElements(image);
		img_list.get(2).click();

		Thread.sleep(2000);
		List<WebElement> inner_img = driver.findElements(By.id("image_view"));
		Thread.sleep(2000);
		inner_img.get(1).click();
		inner_img.get(2).click();
		d.click(img_done);
		d.type(add_caption, R_type);
		d.closekeyboard();
		d.click(save_img);
		Thread.sleep(2000);
		d.click(add_container);
		d.click(Report_DONE_Btn);
		Thread.sleep(2000);
			

	}
	// Reports
	By icon_Edit = By.id("actionbar_icon_edit");
	By Report_DONE_Btn = By.id("add_opd_done_bt_container");
	By Report_Back_Btn = By.id("actionbar_icon_back");

	
	@DataProvider
	public Object[][] dp_Edit_report_patient() {
		return new Object[][] { new Object[] {  
				"pawar Lab", "X-ray", "09/02/2018", "Normal" } };
	}

	
@Test(dataProvider = "dp_Edit_report_patient", priority = 2, enabled = true)
	public void Edit_patient_Report(String R_title,String R_type, String R_Date, String R_notes) throws ParseException, InterruptedException {
		log.info("visit Edit_patient_Report...! ");
		Thread.sleep(2000);
		List<WebElement> R_Name = driver.findElements(record_Name);
		for (WebElement webElement : R_Name) {
			System.out.println(webElement.getText());
		}
		R_Name.get(0).click();
		d.click(icon_Edit);
		d.type(report_title, R_title);
		d.type(Report_Record_Type, R_type); 
		d.click(Report_icon_date);
		Date dob_date= new SimpleDateFormat("dd/MM/yyyy").parse(R_Date);
		d.setDate(dob_date);
		d.type(Report_txtNotes, R_notes);
		d.click(Report_nxt_btn);
		d.click(arrowUp);
		R_Name.get(0).click();
		d.type(caption_edt, R_notes);
		d.click(del_ok);
		List<WebElement> del_count = driver.findElements(del_icon);
		del_count.get(0).click();
		d.click(del_cancel);
		d.click(Report_DONE_Btn);
		d.click(Report_Back_Btn);
		del_count.get(0).click();
		d.click(del_ok);
/*		boolean flag = true;for (WebElement we_list : R_Name) {if (we_list.getText().equalsIgnoreCase(R_search_nm)) {we_list.click();System.out.println("FOUND....!");break;
		}}if (flag == false) {System.out.println("didn't Found record...!");}				
*/		
	}


		// Accounts
		By addbtn = By.id("actionbar_icon_add");
		By date = By.id("icon_date");
		By A_amt = By.id("edtamount");
		By A_Notes = By.id("edtNotes");
		@DataProvider
		public Object[][] dp_Add_Bill_Amount() {
			return new Object[][] { new Object[] { "22/04/2018" ,"200","PAID"} };
		}

	@Test(dataProvider="dp_Add_Bill_Amount",enabled=true)
	public void Medical_Reports_For_Account(String Date,String amt,String notes) throws InterruptedException, IOException, ParseException {
	log.info("visit Medical_Reports_For_Account");
		
	Thread.sleep(5000);
		/*List<WebElement> gettab= driver.findElements(By.id("tab_text"));
		gettab.get(3).click();// Report
		*/
		
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

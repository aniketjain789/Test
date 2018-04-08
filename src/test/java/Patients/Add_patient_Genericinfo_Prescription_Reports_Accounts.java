package Patients;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;

public class Add_patient_Genericinfo_Prescription_Reports_Accounts {
	
	By No_medical_record = By.id("please_add_txt");
	By cancle = By.id("actionbar_icon_cancel");
	By icon_add = By.id("actionbar_icon_add");
	By report_title = By.id("txtReportTitle");
	By Report_Record_Type = By.id("medicalRecordType");
	By Report_icon_date = By.id("icon_date");
	By Report_txtNotes = By.id("txtNotes");
	By Report_nxt_btn = By.id("rippleNextBtn");
	//Accounts
		By A_icon_add = By.id("actionbar_icon_add");
		By A_pending = By.id("rbt_pending");
		By A_received = By.id("rbt_received");
		By A_txtdate = By.id("txtDate");
		By A_icon_date = By.id("icon_date");
		By A_amt = By.id("edtamount");
		By A_notes= By.id("edtNotes");
		
	
	@DataProvider
	public Object[][] dp_report() {
		return new Object[][] { new Object[] { "09/03/2018", "Rohit Nimbalkar", "MNF's Ratna Clinic", "Jain's Lab",
				"X-ray", "09/03/2018", "Normal" } };
	}
	
	
	
	
/*	d.click(icon_add);
	d.type(report_title, R_title);
	d.type(Report_Record_Type, R_type);
	//d.click(Report_icon_date); need to do
	d.type(Report_txtNotes, R_notes);
	d.click(Report_nxt_btn);

	
	List<WebElement> gettab= driver.findElements(By.id("tab_text"));
	gettab.get(3).click();// Accounts
	d.click(A_icon_add);
	System.out.println(driver.findElement(A_txtdate).getText());
	//d.click(A_icon_date); remining
	d.type(A_amt, "100");
	d.type(A_notes, "paid");


*/











}

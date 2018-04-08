package patient_app;

import org.testng.annotations.Test;

import docto.Define;
import io.appium.java_client.android.AndroidDriver;
import java.net.URL;
import java.util.List;
import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;

public class Family_member_medical_data_check 
{
	AndroidDriver<WebElement> driver;
	Define d;
	//drawer
	By drawer = By.className("android.widget.ImageButton");
	By buttons_list = By.id("item_name");
	By gps_ok_btn = By.id("button1");
	
	//medical data
	By medical_data_btn = By.id("btnMedicalData");
	By bottm_tab = By.id("tab_text");
	
	By prescrption_icon = By.id("iconPrescription");
	By report_click = By.id("txtRecordName");
	
	//add report
	By add_report = By.id("menu_add");
	By report_title = By.id("txtReportTitle");
	By report_type = By.id("medicalRecordType");
	By report_date = By.id("txtDate");
	By ok_btn = By.id("ok");
	By report_notes = By.id("txtNotes");
	By next = By.id("add_opd_bt_next_container");
	By done_btn = By.id("add_opd_done_bt_container");
	
	//edit mem
	By edit_btn = By.id("edit_icon");
	By edit_mem_name = By.id("member_name_edt");
	By edit_mob_no = By.id("member_mobile_edt");
	By edit_gender_m = By.id("male");
	By edit_gender_f = By.id("female");
	By edit_dob = By.id("user_DOB");
	By edit_relationship = By.id("text_spiner");
	By edit_done = By.id("btn_add");
	
	//delete record
	By del_report_title = By.id("txtRecordName");
	By del_btn = By.id("icon_delete");
	By del_ok_btn = By.id("btn_ok");
	By del_cancel_btn = By.id("btn_cancel");
	
	//prescription search
	By pres_search_btn = By.id("menu_search");
	By pres_search_text = By.id("search_src_text");
	
	//
	
  
  @Test(priority=1,enabled=true)
  public void add_member() throws InterruptedException 
  {
	  open_tab();
	  Thread.sleep(800);
	  d.click(medical_data_btn);  
	  
	  Thread.sleep(500);
	  List<WebElement> tab = driver.findElements(bottm_tab);
	  
	  tab.get(1).click();
	  
	  try {
		  d.click(prescrption_icon);
		  Thread.sleep(1500);
		  d.device_back();
	} catch (Exception e) 
	  {
		System.out.println("Data Not Found");
	}	  
	  
	  tab.get(2).click();
	  
	  try {
		  d.click(report_click);
		  Thread.sleep(2500);
		  d.device_back();
	} catch (Exception e) 
	  {
		System.out.println("Data Not Found");
	}
	  
	  Thread.sleep(800);
	  d.device_back();
	  d.device_back();
	  
  }
  
  @Test(priority=2,enabled=true)
  public void edit_member() throws InterruptedException
  {
	  open_tab();
	  Thread.sleep(800);
	  
	  List<WebElement> edit_btn_lst = driver.findElements(edit_btn);
	  edit_btn_lst.get(1).click();
	  
	  String old_name = d.get_text(edit_mem_name);
	  String old_mob = d.get_text(edit_mob_no);
	  String old_relatn = d.get_text(edit_relationship);
	  
	  d.clear(edit_mem_name);
	  d.type(edit_mem_name, "tester");
	  d.clear(edit_mob_no);
	  d.type(edit_mob_no, "1234567980");

	  	  
	  d.click(edit_done);	  
	  d.click(edit_relationship);
	  Thread.sleep(800);
	  
	  List<WebElement> relation_list = driver.findElements(edit_relationship);
	  relation_list.get(6).click();	 
	  
	  d.click(edit_done);
	  
	  Thread.sleep(1500);
	  System.out.println("Reseting");
	  edit_btn_lst.get(1).click();
	  
	  d.clear(edit_mem_name);
	  d.type(edit_mem_name, old_name);
	  d.clear(edit_mob_no);
	  d.type(edit_mob_no, old_mob);

	  	  
	  d.click(edit_done);	  
	  d.click(edit_relationship);
	  Thread.sleep(800);
	  
	  relation_list = driver.findElements(edit_relationship);
	  for(WebElement we:relation_list)
	  {
		  String temp = we.getText();
		  if(temp.equalsIgnoreCase(old_relatn))
		  {we.click();
		  break;}
	  }
	  d.click(edit_done);
	  d.device_back();
	  Thread.sleep(2000);
	  
	  
  }
  
  @Test(priority=3,enabled=true)
  public void add_report() throws InterruptedException 
  {
	  open_tab();
	  Thread.sleep(800);
	  d.click(medical_data_btn);
	  Thread.sleep(800);
	  
	  List<WebElement> tab = driver.findElements(bottm_tab);
	  tab.get(2).click();
	  d.click(add_report);
	  Thread.sleep(800);
	  d.type(report_title, "Test Report");
	  d.type(report_type, "X-ray");
	  
	  d.click(report_date);
	  d.click(ok_btn);
	  d.type(report_notes, "test");
	  d.click(next);
	  Thread.sleep(800);
	  d.click(done_btn);
	  Thread.sleep(1800);
	  d.device_back();
	  Thread.sleep(800);
	  d.device_back();	
	  Thread.sleep(2000);
	  
	  open_tab();
	  Thread.sleep(800);
	  d.click(medical_data_btn);
	  Thread.sleep(800);
	  tab.get(2).click();
	  
	  try 
	  {
		delete_record();
		d.device_back();
		Thread.sleep(800);
		d.device_back();
	} catch (Exception e) 
	  {
		d.device_back();
		Thread.sleep(800);
		d.device_back();
	}
  
  }
  
  @Test(priority=4,enabled=true)
  public void prescription_search() throws InterruptedException 
  {
	  open_tab();
	  d.click(medical_data_btn);
	  Thread.sleep(1000);	  
	  
	  List<WebElement> tab = driver.findElements(bottm_tab);
	  tab.get(1).click();
	  Thread.sleep(500);
	  d.click(pres_search_btn);
	  d.type(pres_search_text, "a");
	  d.device_back();
	  Thread.sleep(2000);
	  d.device_back();
	  Thread.sleep(500);
	  d.device_back();
	  Thread.sleep(500);
	  d.device_back();
	  
	  
  }
  
  public void delete_record() throws InterruptedException
  {
	  System.out.println("deleting reports");
	  boolean flag= true;
	  List<WebElement> del_btn_list = driver.findElements(del_btn);
	  for(WebElement we : del_btn_list)
	  {
		  we.click();
		  Thread.sleep(300);
		  if(flag)
		  {
			  d.click(del_cancel_btn);
			  Thread.sleep(300);
			  we.click();
			  flag=false;
		  }
		  d.click(del_ok_btn);		  
	  }
  }
  
  private void  open_tab() throws InterruptedException
  {
	  	d.API_click(drawer);	  
	  	List<WebElement> btn_list = driver.findElements(buttons_list);
//		System.out.println(btn_list.size());		
		btn_list.get((1)).click();
		Thread.sleep(800);
  }
  @BeforeClass
  public void beforeClass() throws Exception 
  {
	  DesiredCapabilities capab = new DesiredCapabilities();	  	
	  capab.setCapability("BROWSER_NAME","Android");
	  capab.setCapability("VERSION","4.4.2");
	  capab.setCapability("deviceName","Itg Guru (SM-G350E)");
	  capab.setCapability("platformName","Android");	  
	  capab.setCapability("appPackage","innomationtech.doctoplus.patient");
	  capab.setCapability("appActivity","activity.MainActivity"); 
	  driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"),capab);
	  d = new Define(driver);
	  
	  //gps pop up
	  
	  Thread.sleep(2800);
	  try {
		d.click(gps_ok_btn);
		System.out.println("Location service turned ON");
	} catch (Exception e) 
	  {	
	}
	  
	  //
	  
//	  d.API_click(add_new);
//	  open_tab();
	    
	}

  @AfterClass
  public void afterClass() 
  {
	  driver.quit();
  }

}

package patient_app;

import java.net.URL;
import java.util.List;
import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import docto.Define;
import io.appium.java_client.android.AndroidDriver;

public class Medical_record 
{
	AndroidDriver<WebElement> driver;
	Define d;
	//drawer
	By drawer = By.className("android.widget.ImageButton");
	By buttons_list = By.id("item_name");
	
	By bottom_tab = By.id("tab_text");
	By menu_filter = By.id("menu_filter");
	By menu_filter_list = By.id("rbt_family_member");

	//prescription
	By prescrptn = By.id("iconPrescription");
	By pres_search_btn = By.id("menu_search");
	By pres_search_txt_bx = By.id("search_src_text");
	By pres_serach_cls_btn  = By.id("search_close_btn");
	
	
	//reports
		By report_add = By.id("menu_add");
		By report = By.id("txtRecordName");
		By report_title = By.id("txtReportTitle");
		By report_type = By.id("medicalRecordType");
		By report_date = By.id("txtDate");
		By ok_btn = By.id("ok");
		By report_notes = By.id("txtNotes");
		By next = By.id("add_opd_bt_next_container");
		By done_btn = By.id("add_opd_done_bt_container");
	
	//delete record
		By del_report_title = By.id("txtRecordName");
		By del_btn = By.id("icon_delete");
		By del_ok_btn = By.id("btn_ok");
		By del_cancel_btn = By.id("btn_cancel");
		

	
  @Test(priority=2,enabled=true)
  public void check() throws InterruptedException 
  {
	  Thread.sleep(3000);
	  open_tab();
	  Thread.sleep(1000);
	  test();
	  Thread.sleep(1000);
	  d.click(menu_filter);
	  Thread.sleep(800);
	  List<WebElement> list = driver.findElements(menu_filter_list);
	  list.get(2).click();
	  test();
	  d.click(menu_filter);
	  list.get(0).click();
  }
  

  
  
  @Test(priority=1,enabled=true)
  public void add_report() throws InterruptedException
  {
	  open_tab();
	  Thread.sleep(2000);
	  List<WebElement> list = driver.findElements(bottom_tab);
	  list.get(2).click();
	  add_rep();
	  delete_record();
	  
	  Thread.sleep(4500);
	  
	  d.click(menu_filter);
	  List<WebElement> filtr_list = driver.findElements(menu_filter_list);
	  filtr_list.get(2).click();
	  add_rep();
	  delete_record();
	  d.device_back();
  }
  public void add_rep() throws InterruptedException
  {
	  d.click(report_add);
	  Thread.sleep(800);
	  d.type(report_title, "Test Report");
	  d.type(report_type, "X-ray");
	  
	  d.click(report_date);
	  d.click(ok_btn);
	  d.type(report_notes, "test");
	  d.click(next);
	  Thread.sleep(800);
	  d.click(done_btn);
	  Thread.sleep(800);
  }
  
  public void test() throws InterruptedException
  {
	  List<WebElement> tabs = driver.findElements(bottom_tab);
	  tabs.get(0).click();
	  Thread.sleep(1000);
	  tabs.get(1).click();
	  Thread.sleep(1000);
	  //new
	  d.click(pres_search_btn);
	  d.type(pres_search_txt_bx,"a");
	  d.device_back();
	  Thread.sleep(500);
	  d.click(pres_serach_cls_btn);
	  d.device_back();
	  //
	  try {
		d.click(prescrptn);
		Thread.sleep(1200);
		d.device_back();
	} catch (Exception e) 
	  {
		System.out.println("Record not found");
	}
	  
	  
	  tabs.get(2).click();
	  Thread.sleep(1000);
	  
	  try {
			d.click(report);
			Thread.sleep(1200);
			d.device_back();
		} catch (Exception e) 
		  {
			System.out.println("Record not found");
		}
	  tabs.get(0).click();  
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
		  break;
	  }
  }
  private void  open_tab() throws InterruptedException
  {
	  	d.click(drawer);	  
	  	List<WebElement> btn_list = driver.findElements(buttons_list);
//		System.out.println(btn_list.size());		
		btn_list.get((2)).click();
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
//	  d.API_click(add_new);
//	  open_tab();
	    
	}

  @AfterClass
  public void afterMethod() 
  {
	  driver.quit();
  }

}


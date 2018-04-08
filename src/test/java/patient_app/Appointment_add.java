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

public class Appointment_add 
{
	AndroidDriver<WebElement> driver;
	Define d;
	
	By add_new = By.id("fab");
	By bottm_tab = By.id("tab_text");
	By menu_btn = By.xpath("//android.widget.ImageButton[@index='0']");
	int x1,y1,x2,y2;
	
	//book
	By first_mem = By.id("member_name");
	By book_appoitmnt = By.id("btn_book");
	By book_btn = By.id("book_btn");
	
	By booked_text = By.id("appiontment_booked");
	By doc_name = By.id("doctor_name");
	By day_time = By.id("day_date_time_txt");
	By token_no = By.id("token_number");
	By appointment_list = By.id("appointment_list_btn");
	
	By edit_appoitmnt = By.id("icon_edit");
	By map_mem_list = By.id("member_name");
	By map_ok_canl_btn = By.className("android.widget.Button");
	
	By del_apoi_btn = By.id("grp_dlt_btn");
	By ok_btn = By.id("btn_ok");
	
	By doc_profile = By.id("btn_profile");
	
	By cli_location = By.id("txt_change");
	By rbt_pincode = By.id("rbtPincode");
	By rbt_city = By.id("rbtCity");
	By pin_txt_box = By.id("edtPincode");
	By cli_ok_btn = By.id("button1");
	
	//i
	By clinic_info = By.id("txt_clinic_nifo");
	By doc_info = By.id("btn_doctor_info");
	By doc_clinic_info = By.id("btn_clinic_info");
	By doc_text = By.id("txt_title");
	By title_bar = By.id("custom_app_bar");
	By search_doc = By.id("edt_search");
	
	//check
	By p_names = By.id("txt_patient_name");
	
	

  @Test(priority=1,enabled=true)
  public void check() throws InterruptedException 
  {
	  Thread.sleep(5000);
	  List<WebElement> tab = driver.findElements(bottm_tab);
	  tab.get(1).click();
	  Thread.sleep(1000);
	  tab.get(2).click();
	  Thread.sleep(2500);
	  
	  List<WebElement> p_name_list = driver.findElements(p_names);
	  
	  int x1 = p_name_list.get(p_name_list.size()-1).getLocation().getX();
	  int y1 = p_name_list.get(p_name_list.size()-1).getLocation().getY();
	  int x2 = p_name_list.get(0).getLocation().getX();
	  int y2 = p_name_list.get(0).getLocation().getY();
	  
	  d.swipe(x1, y1, x2, y2);
	  Thread.sleep(800);
	  d.swipe(x1, y1, x2, y2);
	  
	  Thread.sleep(800);
	  d.swipe(x2, y2,x1, y1);
	  Thread.sleep(800);
	  d.swipe(x2, y2,x1, y1);
	  Thread.sleep(800);
	  tab.get(0).click();
	  Thread.sleep(800);

  }
  @Test(priority=2,enabled=true)
  public void book_appo_test() throws InterruptedException 
  {
	  d.API_click(add_new);
	  Thread.sleep(2000);
	  d.API_click(first_mem);
	  Thread.sleep(2000);
	  
	  d.click(doc_profile);
	  Thread.sleep(2000);
	  d.device_back();
	  
	  //new
	  d.click(clinic_info);
//	  doc_profile();
	  Thread.sleep(2200);
	  d.click(doc_info);
//	  doc_profile();
	  d.device_back();
	  Thread.sleep(500);
	  d.type(search_doc, "a");
	  d.device_back();  
	  
	  
	  d.click(cli_location);
	  Thread.sleep(500);
	  d.click(rbt_pincode);
	  d.type(pin_txt_box, "400008");
	  d.click(cli_ok_btn);
	  Thread.sleep(2500);
	  
	  d.click(cli_location);
	  Thread.sleep(500);
	  d.click(rbt_pincode);
	  d.click(rbt_city);
	  d.click(rbt_pincode);
	  d.type(pin_txt_box, "411051");
	  d.click(cli_ok_btn);
	  
	  //
	  
	  d.API_click(book_appoitmnt);
	  Thread.sleep(2000);
	  d.API_click(book_btn);
	  
	  Thread.sleep(3500);
	  
	  try 
	  {
		  System.out.println(d.get_text(booked_text));
		  System.out.println("Doctor Name:- "+d.get_text(doc_name));
		  System.out.println("Token no.:- "+d.get_text(token_no));
		  Thread.sleep(1500);
		  d.click(appointment_list);		
		  Thread.sleep(3000);
		  System.out.println("Mapping");
		  d.click(edit_appoitmnt);
		  Thread.sleep(2000);
		  map();
		
	} catch (Exception e) 
	  {
		System.out.println("Appointment unable to booked");
	}	  
  }
  public void doc_profile() throws InterruptedException 
  {
	  Thread.sleep(4000);	  
  }
  public void map()
  {
	  List<WebElement> mem_list = driver.findElements(map_mem_list);
	  List<WebElement> btn = driver.findElements(map_ok_canl_btn);
	  
	  try {
		mem_list.get(1).click();
		btn.get(1).click();		
	} catch (Exception e) 
	  {
		System.out.println("Members are not available");
	}
  }
  
  @Test(priority=3,enabled=true)
  public void delete_appo_test() throws InterruptedException 
  {
	  System.out.println("Deleting Appointments");
	  Thread.sleep(10000);
//	  List<WebElement> del_btns = driver.findElements(del_apoi_btn);
//	  for(WebElement we :del_btns)
//	  {
//		  we.click();
	  	  d.click(del_apoi_btn);	
		  Thread.sleep(800);
		  d.click(ok_btn);
		  Thread.sleep(2000);
//	  }
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
